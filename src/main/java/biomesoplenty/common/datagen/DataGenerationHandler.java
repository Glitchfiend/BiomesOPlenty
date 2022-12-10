/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.datagen;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.common.util.worldgen.BOPFeatureUtils;
import biomesoplenty.common.util.worldgen.BOPPlacementUtils;
import biomesoplenty.common.worldgen.feature.*;
import biomesoplenty.common.worldgen.placement.*;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModBiomes;
import com.google.gson.JsonElement;
import com.mojang.serialization.JsonOps;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraft.data.registries.VanillaRegistries;
import net.minecraft.resources.RegistryOps;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.JsonCodecProvider;
import net.minecraftforge.data.event.GatherDataEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
public class DataGenerationHandler
{
    @SubscribeEvent
    public static void onGatherData(GatherDataEvent event)
    {
        DataGenerator generator = event.getGenerator();
        PackOutput packOutput = generator.getPackOutput();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        HolderLookup.Provider lookupProvider = new RegistrySetBuilder()
                .add(Registries.CONFIGURED_FEATURE, (RegistrySetBuilder.RegistryBootstrap) BOPFeatureUtils::bootstrap)
                .add(Registries.PLACED_FEATURE, (RegistrySetBuilder.RegistryBootstrap) BOPPlacementUtils::bootstrap)
                .add(Registries.BIOME, ModBiomes::bootstrapBiomes)
                .buildPatch(RegistryAccess.fromRegistryOfRegistries(BuiltInRegistries.REGISTRY), VanillaRegistries.createLookup());

        RegistryOps<JsonElement> registryOps = RegistryOps.create(JsonOps.INSTANCE, lookupProvider);

        Map<ResourceLocation, ConfiguredFeature<?, ?>> configuredFeatureMap = createMap(Registries.CONFIGURED_FEATURE, lookupProvider, BOPCaveFeatures.class, BOPMiscOverworldFeatures.class, BOPNetherFeatures.class, BOPTreeFeatures.class, BOPVegetationFeatures.class);
        Map<ResourceLocation, PlacedFeature> placedFeatureMap = createMap(Registries.PLACED_FEATURE, lookupProvider, BOPCavePlacements.class, BOPMiscOverworldPlacements.class, BOPNetherPlacements.class, BOPTreePlacements.class, BOPVegetationPlacements.class);
        Map<ResourceLocation, Biome> biomeMap = createMap(Registries.BIOME, lookupProvider, BOPBiomes.class);

        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, BiomesOPlenty.MOD_ID, registryOps, Registries.CONFIGURED_FEATURE, configuredFeatureMap));
        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, BiomesOPlenty.MOD_ID, registryOps, Registries.PLACED_FEATURE, placedFeatureMap));
        generator.addProvider(event.includeServer(), JsonCodecProvider.forDatapackRegistry(generator, existingFileHelper, BiomesOPlenty.MOD_ID, registryOps, Registries.BIOME, biomeMap));
    }

    private static <T> Map<ResourceLocation, T> createMap(ResourceKey<? extends Registry<? extends T>> registry, HolderLookup.Provider lookupProvider, Class... classes)
    {
        Map<ResourceLocation, T> map = new HashMap<>();
        List.of(classes).forEach(clazz -> {
            for (Field field : clazz.getFields())
            {
                if (field.getType() != ResourceKey.class) continue;

                try
                {
                    ResourceKey<T> key = (ResourceKey)field.get(null);
                    HolderLookup.RegistryLookup<T> lookup = lookupProvider.lookupOrThrow(registry);
                    map.put(key.location(), lookup.getOrThrow(key).value());
                }
                catch (IllegalAccessException e)
                {
                    BiomesOPlenty.LOGGER.error(e);
                }
            }
        });
        return map;
    }
}
