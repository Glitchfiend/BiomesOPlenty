/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.data;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.common.worldgen.BOPMultiNoiseBiomeSource;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.gson.JsonElement;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.JsonOps;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.worldselection.CreateWorldScreen;
import net.minecraft.client.multiplayer.ClientChunkCache;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.RegistryReadOps;
import net.minecraft.resources.RegistryWriteOps;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.server.level.ServerChunkCache;
import net.minecraft.server.packs.PackResources;
import net.minecraft.server.packs.PackType;
import net.minecraft.server.packs.metadata.MetadataSectionSerializer;
import net.minecraft.server.packs.resources.ResourceManager;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import net.minecraft.server.packs.resources.SimpleReloadableResourceManager;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.chunk.ChunkSource;
import net.minecraft.world.level.dimension.LevelStem;
import net.minecraft.world.level.levelgen.WorldGenSettings;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.AddReloadListenerEvent;
import net.minecraftforge.event.world.WorldEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.jetbrains.annotations.Nullable;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class ReloadManager
{
//    @SubscribeEvent
    public void onAddReloadListener(AddReloadListenerEvent event)
    {
        event.addListener(new ReloadListener());
    }

    private static class ReloadListener implements ResourceManagerReloadListener
    {
        private static ImmutableList<Pair<Climate.ParameterPoint, Supplier<Biome>>> loadedParameters = ImmutableList.of();

        @Override
        public void onResourceManagerReload(ResourceManager resourceManager)
        {
            RegistryAccess.RegistryHolder registryAccess = RegistryAccess.builtin();
            RegistryWriteOps<JsonElement> registryWriteOps = RegistryWriteOps.create(JsonOps.INSTANCE, registryAccess);
            RegistryReadOps<JsonElement> registryReadOps = RegistryReadOps.createAndLoad(JsonOps.INSTANCE, resourceManager, registryAccess);
            DataResult<WorldGenSettings> worldGenSettingsDataResult = WorldGenSettings.CODEC.encodeStart(registryWriteOps, WorldGenSettings.makeDefault(registryAccess)).flatMap((element) -> {
                return WorldGenSettings.CODEC.parse(registryReadOps, element);
            });

            loadedParameters = ImmutableList.of();

            if (!worldGenSettingsDataResult.result().isPresent())
                return;

            WorldGenSettings worldGenSettings = worldGenSettingsDataResult.result().get();
            BiomeSource biomeSource = worldGenSettings.overworld().getBiomeSource();

            Set<String> namespaces = Sets.newHashSet();

            if (biomeSource instanceof MultiNoiseBiomeSource)
            {
                Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
                MultiNoiseBiomeSource multiNoiseBiomeSource = (MultiNoiseBiomeSource)biomeSource;
                loadedParameters = multiNoiseBiomeSource.parameters.values().stream().filter(pair -> {
                    ResourceLocation location = biomeRegistry.getKey(pair.getSecond().get());

                    if (location == null)
                    {
                        BiomesOPlenty.logger.error("Location null for " + pair.toString() + ", skipping...");
                        return false;
                    }

                    return !location.getNamespace().equals("minecraft") && !location.getNamespace().equals(BiomesOPlenty.MOD_ID);
                }).collect(ImmutableList.toImmutableList());

                namespaces = loadedParameters.stream().map((pair) -> biomeRegistry.getKey(pair.getSecond().get()).getNamespace()).collect(Collectors.toSet());
                namespaces.forEach(namespace -> BiomeProviders.register(namespace, 10));
            }

            if (resourceManager instanceof SimpleReloadableResourceManager)
            {
                wrapPackResources((SimpleReloadableResourceManager)resourceManager, namespaces);
            }
        }

        private static void wrapPackResources(SimpleReloadableResourceManager resourceManager, Set<String> namespacesToWrap)
        {
            List<PackResources> wrappedPackResources = Lists.newArrayList();
            Iterator<PackResources> packResourcesIterator = resourceManager.packs.iterator();

            while (packResourcesIterator.hasNext())
            {
                PackResources resources = packResourcesIterator.next();
                Set<String> packNamespaces = resources.getNamespaces(resourceManager.type);

                if (packNamespaces.stream().anyMatch(namespace -> namespacesToWrap.contains(namespace)))
                {
                    // Remove fallbacks
                    packNamespaces.forEach(namespace -> resourceManager.namespacedPacks.get(namespace).fallbacks.remove(resources));

                    // Remove the pack
                    packResourcesIterator.remove();

                    // Wrap the pack
                    wrappedPackResources.add(new WrappedPackResources(resources));
                }
            }

            for (String namespace : namespacesToWrap)
            {
                resourceManager.namespacedPacks.remove(namespace);
                resourceManager.namespaces.remove(namespace);
            }

            // Re-add preserved packs
            wrappedPackResources.forEach(resourceManager::add);
        }
    }

    private static class WrappedPackResources implements PackResources
    {
        private static final ImmutableList<ResourceLocation> BLACKLISTED_LOCATIONS = ImmutableList.of(new ResourceLocation("minecraft:dimension/overworld.json"), new ResourceLocation("minecraft:dimension_type/overworld.json"));

        private final PackResources delegate;

        private WrappedPackResources(PackResources delegate)
        {
            this.delegate = delegate;
        }

        @Nullable
        @Override
        public InputStream getRootResource(String p_10294_) throws IOException
        {
            return this.delegate.getRootResource(p_10294_);
        }

        @Override
        public InputStream getResource(PackType p_10289_, ResourceLocation p_10290_) throws IOException
        {
            return this.delegate.getResource(p_10289_, p_10290_);
        }

        @Override
        public Collection<ResourceLocation> getResources(PackType type, String path, String path2, int maxDepth, Predicate<String> filter)
        {
            Collection<ResourceLocation> resources = this.delegate.getResources(type, path, path2, maxDepth, filter).stream().filter(resource -> !BLACKLISTED_LOCATIONS.contains(resource)).toList();
            return resources;
        }

        @Override
        public boolean hasResource(PackType type, ResourceLocation location)
        {
            return !BLACKLISTED_LOCATIONS.contains(location) && this.delegate.hasResource(type, location);
        }

        @Override
        public Set<String> getNamespaces(PackType p_10283_)
        {
            return this.delegate.getNamespaces(p_10283_);
        }

        @Nullable
        @Override
        public <T> T getMetadataSection(MetadataSectionSerializer<T> p_10291_) throws IOException
        {
            return this.delegate.getMetadataSection(p_10291_);
        }

        @Override
        public String getName()
        {
            return this.delegate.getName();
        }

        @Override
        public void close()
        {
            this.delegate.close();
        }
    }
}
