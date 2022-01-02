/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.data;

import biomesoplenty.api.biome.BiomeProviders;
import biomesoplenty.common.worldgen.*;
import biomesoplenty.common.worldgen.surface.DifferentialSurfaceRuleSource;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.mojang.datafixers.DataFixer;
import com.mojang.datafixers.util.Pair;
import com.mojang.serialization.DataResult;
import com.mojang.serialization.Dynamic;
import com.mojang.serialization.Lifecycle;
import net.minecraft.SharedConstants;
import net.minecraft.Util;
import net.minecraft.core.Registry;
import net.minecraft.core.RegistryAccess;
import net.minecraft.data.worldgen.SurfaceRuleData;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.util.datafix.fixes.References;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeSource;
import net.minecraft.world.level.biome.Climate;
import net.minecraft.world.level.biome.MultiNoiseBiomeSource;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.*;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.Supplier;
import java.util.stream.Collectors;

public class DataPackManager
{
    public static WorldGenSettings mergeWorldGenSettings(RegistryAccess registryAccess, WorldGenSettings currentSettings, WorldGenSettings newSettings)
    {
        // Do not merge if the chunk generator isn't ours or the new settings don't use a MultiNoiseBiomeSource
        if (!(currentSettings.overworld() instanceof BOPNoiseBasedChunkGenerator) || !(newSettings.overworld().getBiomeSource() instanceof MultiNoiseBiomeSource) || currentSettings.equals(newSettings))
            return newSettings;

        MultiNoiseBiomeSource biomeSource = (MultiNoiseBiomeSource)newSettings.overworld().getBiomeSource();
        Registry<Biome> biomeRegistry = registryAccess.registryOrThrow(Registry.BIOME_REGISTRY);

        // Register biome providers from the parameters
        BiomeProviders.register("datapack", 10);

        ImmutableList<Pair<BOPClimate.ParameterPoint, Supplier<Biome>>> externalParameters = biomeSource.parameters.values().stream().map(pair -> {
            Climate.ParameterPoint point = pair.getFirst();
            Supplier<Biome> biomeSupplier = pair.getSecond();
            Biome biome = biomeSupplier.get();
            ResourceLocation biomeKey = biomeRegistry.getKey(biome);
            Climate.Parameter uniqueness = BiomeProviders.getUniquenessParameter(BiomeProviders.getIndex("datapack"));

//            float minDepth = Mth.clamp(BOPClimate.unquantizeCoord(point.depth().min()), 0.0F, 1.0F);
//            float maxDepth = Mth.clamp(BOPClimate.unquantizeCoord(point.depth().max()), 0.0F, 1.0F);
//
//            // Manually correct underground biome depth
//            if (biome.getBiomeCategory() == Biome.BiomeCategory.UNDERGROUND)
//            {
//                minDepth = 0.2F;
//                maxDepth = 0.9F;
//            }
//
//            Climate.Parameter adjustedDepth = Climate.Parameter.span(minDepth, maxDepth);
            BOPClimate.ParameterPoint bopPoint = BOPClimate.parameters(point.temperature(), point.humidity(), point.continentalness(), point.erosion(), point.depth(), point.weirdness(), uniqueness, Climate.Parameter.span(-1.0F, 0.35F), BOPClimate.unquantizeCoord(point.offset()));
            return Pair.of(bopPoint, biomeSupplier);
        }).collect(ImmutableList.toImmutableList());

        BOPNoiseBasedChunkGenerator currentOverworldChunkGenerator = (BOPNoiseBasedChunkGenerator)currentSettings.overworld();
        NoiseGeneratorSettings currentNoiseGeneratorSettings = currentOverworldChunkGenerator.settings.get();
        NoiseBasedChunkGenerator newOverworldChunkGenerator = (NoiseBasedChunkGenerator)newSettings.overworld();
        NoiseGeneratorSettings newNoiseGeneratorSettings = newOverworldChunkGenerator.settings.get();

        SurfaceRules.RuleSource mergedOverworldRuleSource = new DifferentialSurfaceRuleSource(BOPSurfaceRuleData.overworld(), ImmutableList.of(newNoiseGeneratorSettings.surfaceRule()));
        NoiseGeneratorSettings mergedNoiseGeneratorSettings = BOPNoiseGeneratorSettings.overworld(newNoiseGeneratorSettings.noiseSettings(), mergedOverworldRuleSource);
        ChunkGenerator mergedChunkGenerator = BOPWorldType.chunkGenerator(registryAccess, currentSettings.seed(), () -> mergedNoiseGeneratorSettings, externalParameters);

        BiomesOPlenty.logger.info("Merged generation settings with datapack");
        return BOPWorldType.settings(registryAccess, currentSettings.seed(), currentSettings.generateFeatures(), currentSettings.generateBonusChest(), mergedChunkGenerator);
    }
}
