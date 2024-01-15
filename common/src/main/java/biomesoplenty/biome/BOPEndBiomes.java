/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.biome;

import biomesoplenty.worldgen.placement.BOPEndPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndBiomes
{
    private static Biome baseEndBiome(BiomeGenerationSettings.Builder biomeBuilder) {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.endSpawns(mobSpawnBuilder);
        return new Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(0.5F)
            .downfall(0.5F)
            .specialEffects(
                new BiomeSpecialEffects.Builder()
                    .waterColor(4159204)
                    .waterFogColor(329011)
                    .fogColor(10518688)
                    .skyColor(0)
                    .ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS)
                    .build()
            )
            .mobSpawnSettings(mobSpawnBuilder.build())
            .generationSettings(biomeBuilder.build())
            .build();
    }

    public static Biome endWilds(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.FLOWER_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.PATCH_ENDERPHYTES);
        return baseEndBiome(biomeBuilder);
    }

    public static Biome endReef(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.DEAD_CORAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.BARNACLES);
        return baseEndBiome(biomeBuilder);
    }

    public static Biome endCorruption(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.ANOMALY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.NULL_LAKE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_CORRUPTION);
        return baseEndBiome(biomeBuilder);
    }
}
