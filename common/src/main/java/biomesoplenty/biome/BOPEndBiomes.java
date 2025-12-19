/*******************************************************************************
 * Copyright 2024, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.biome;

import biomesoplenty.init.ModParticles;
import biomesoplenty.worldgen.placement.BOPEndPlacements;
import biomesoplenty.worldgen.placement.BOPVegetationPlacements;
import net.minecraft.core.HolderGetter;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndBiomes
{
    private static Biome.BiomeBuilder baseBiome() {
        return new Biome.BiomeBuilder()
            .hasPrecipitation(false)
            .temperature(0.5F)
            .downfall(0.5F)
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build());
    }

    public static Biome endWilds(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.FLOWER_END_WILDS);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.PATCH_ENDERPHYTES);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.LUMALOOP);

        return baseBiome()
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ModParticles.END_SPORE, 0.005F))
            .specialEffects(new BiomeSpecialEffects.Builder().grassColorOverride(0xEAB35D).foliageColorOverride(0xEAB35D).waterColor(4159204).build())
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
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.WISPJELLY);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPVegetationPlacements.PATCH_DEAD_GRASS);

        return baseBiome()
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x434949)
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ModParticles.WISP_BUBBLE, 0.001F))
            .specialEffects(new BiomeSpecialEffects.Builder().grassColorOverride(0xE5FFFC).foliageColorOverride(0xE5FFFC).waterColor(0xE5FFFC).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome endMycosis(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);

        return baseBiome()
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(4159204).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome endFlats(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.ENDSCRAPER);

        return baseBiome()
            .setAttribute(EnvironmentAttributes.WATER_FOG_COLOR, 0x497164)
            .specialEffects(new BiomeSpecialEffects.Builder().waterColor(0x66A977).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }

    public static Biome endCorruption(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<ConfiguredWorldCarver<?>> carverGetter)
    {
        MobSpawnSettings.Builder mobSpawnBuilder = new MobSpawnSettings.Builder();
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        biomeBuilder.addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_GATEWAY_RETURN);
        biomeBuilder.addFeature(GenerationStep.Decoration.LOCAL_MODIFICATIONS, BOPEndPlacements.ANOMALY);
        biomeBuilder.addFeature(GenerationStep.Decoration.LAKES, BOPEndPlacements.LIQUID_NULL_LAKE);
        biomeBuilder.addFeature(GenerationStep.Decoration.FLUID_SPRINGS, BOPEndPlacements.LIQUID_NULL_SPRING);
        biomeBuilder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, BOPEndPlacements.MONOLITH);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.TREES_END_CORRUPTION);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPEndPlacements.PATCH_NULL_PLANTS);

        return baseBiome()
            .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ModParticles.BINARY, 0.000375F))
            .specialEffects(new BiomeSpecialEffects.Builder().grassColorOverride(0x7CBD6B).foliageColorOverride(0x48B518).waterColor(4159204).build())
            .mobSpawnSettings(mobSpawnBuilder.build()).generationSettings(biomeBuilder.build()).build();
    }
}
