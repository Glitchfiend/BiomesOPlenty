/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.data;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.common.worldgen.BOPClimate;
import biomesoplenty.common.worldgen.BOPNoiseBasedChunkGenerator;
import biomesoplenty.common.worldgen.BOPWorldType;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.util.Pair;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.levelgen.WorldGenSettings;

import java.util.List;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataPackManager
{
    public static WorldGenSettings mergeWorldGenSettings(RegistryAccess registryAccess, WorldGenSettings currentSettings, WorldGenSettings newSettings)
    {
        // Do not merge if the chunk generator isn't ours or the new settings don't use a MultiNoiseBiomeSource
        if (!(currentSettings.overworld() instanceof BOPNoiseBasedChunkGenerator) || !(newSettings.overworld().getBiomeSource() instanceof MultiNoiseBiomeSource) || currentSettings == newSettings)
            return newSettings;

        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);
        MultiNoiseBiomeSource biomeSource = (MultiNoiseBiomeSource)newSettings.overworld().getBiomeSource();

        ImmutableList<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> externalParameters = biomeSource.parameters.values().stream().filter(pair ->
        {
            ResourceLocation location = biomeRegistry.getKey(pair.getSecond().get());

            if (location == null)
            {
                BiomesOPlenty.logger.error("Location null for " + pair.toString() + ", skipping...");
                return false;
            }

            return !location.getNamespace().equals("minecraft") && !location.getNamespace().equals(BiomesOPlenty.MOD_ID);
        }).map(pair -> {
            Climate.ParameterPoint point = pair.getFirst();
            Supplier<Biome> biomeSupplier = pair.getSecond();
//            Biome biome = biomeSupplier.get();

            // FIXME
//                ResourceLocation location = biomeRegistry.getKey(biome);
//
//                if (location == null)
//                {
//                    throw new RuntimeException("Third-party biome is missing location " + biome);
//                }
//
//                String modId = location.getNamespace();

            // TODO: Fix uniqueness and offsets
            Climate.Parameter uniqueness = Climate.Parameter.point(BOPClimate.unquantizeCoord(2));
            BOPClimate.ParameterPoint bopPoint = BOPClimate.parameters(point.temperature(), point.humidity(), point.continentalness(), point.erosion(), point.depth(), point.weirdness(), uniqueness, Climate.Parameter.span(-1.0F, 1.0F), 0);

            return Pair.of(bopPoint, biomeSupplier);
        }).collect(ImmutableList.toImmutableList());

        // Register biome providers from the parameters
        Set<String> namespaces = externalParameters.stream().map((pair) -> biomeRegistry.getKey(pair.getSecond().get()).getNamespace()).collect(Collectors.toSet());
        namespaces.forEach(namespace -> BiomeProviders.register(namespace, 10));

        BiomesOPlenty.logger.info("Created merged WorldGenSettings");
        return BOPWorldType.settings(registryAccess, currentSettings.seed(), currentSettings.generateFeatures(), currentSettings.generateBonusChest(), externalParameters);
    }
}
