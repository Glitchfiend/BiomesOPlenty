/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.biome;

import biomesoplenty.init.ModParticles;
import biomesoplenty.worldgen.placement.BOPEndPlacements;
import biomesoplenty.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndBiomes
{
    public static Biome endWilds(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.endSpawns(mobSpawnBuilder);
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.FLOWER_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.PATCH_ENDERPHYTES);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.RADIANT_HANDS);

        return new Biome.BiomeBuilder()
            .hasPrecipitation(false).temperature(0.5F).downfall(0.5F)
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).ambientParticle(new AmbientParticleSettings(ModParticles.END_SPORE, 0.005F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome endReef(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.JAGGED_SANDSTONE);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.TIDEPOOL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.DEAD_CORAL);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.DEAD_CORAL_PATCH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.BARNACLES);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);

        return new Biome.BiomeBuilder()
            .hasPrecipitation(false).temperature(0.5F).downfall(0.5F)
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(0xFFFFFF).waterFogColor(0x333333).fogColor(10518688).skyColor(0).ambientParticle(new AmbientParticleSettings(ParticleTypes.MYCELIUM, 0.0075F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome endCorruption(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPEndPlacements.ANOMALY);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.NULL_LAKE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_CORRUPTION);

        return new Biome.BiomeBuilder()
            .hasPrecipitation(false).temperature(0.5F).downfall(0.5F)
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).waterFogColor(329011).fogColor(10518688).skyColor(0).ambientParticle(new AmbientParticleSettings(ModParticles.BINARY, 0.000375F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }
}
