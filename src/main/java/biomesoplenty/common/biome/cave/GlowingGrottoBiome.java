/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.biome.cave;

import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Carvers;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class GlowingGrottoBiome extends BiomeTemplate
{
    public GlowingGrottoBiome()
    {
    }

    @Override
    protected void configureBiome(Biome.BiomeBuilder builder)
    {
        builder.precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.UNDERGROUND).depth(0.1F).scale(0.2F).temperature(0.5F).downfall(0.5F);

        builder.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.5F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(SurfaceBuilders.GRASS);

        // Structures
        BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);

        // Underground
        //BiomeDefaultFeatures.addDefaultCarvers(builder);
        BiomeDefaultFeatures.addDefaultLakes(builder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        //BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        //BiomeDefaultFeatures.addDefaultOres(builder);
        BiomeDefaultFeatures.addDefaultSoftDisks(builder);

        builder.addCarver(GenerationStep.Carving.AIR, Carvers.PROTOTYPE_CAVE);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.PROTOTYPE_CANYON);
        builder.addCarver(GenerationStep.Carving.AIR, Carvers.PROTOTYPE_CREVICE);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_DIRT);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_GRAVEL);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_GRANITE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_DIORITE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_ANDESITE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PROTOTYPE_GLOW_LICHEN);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_TUFF);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.RARE_DRIPSTONE_CLUSTER_FEATURE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, Features.RARE_SMALL_DRIPSTONE_FEATURE);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_COAL_UPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_COAL_LOWER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_IRON_UPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_IRON_MIDDLE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_IRON_SMALL);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_GOLD);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_REDSTONE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_REDSTONE_LOWER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_DIAMOND);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_DIAMOND_LARGE);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_LAPIS);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_LAPIS_BURIED);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_ORE_COPPER);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.PROTOTYPE_UNDERWATER_MAGMA);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.GLOWING_GROTTO_VEGETATION);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.GLOWING_GROTTO_MUD);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.GLOWWORM_SILK_STRANDS);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.GIANT_GLOWSHROOM_CAVE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.HUGE_GLOWSHROOM_CAVE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.MEDIUM_GLOWSHROOM_CAVE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.SMALL_GLOWSHROOM_CAVE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.EXTRA_GLOW_LICHEN);

        ////////////////////////////////////////////////////////////

        // Other Features
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnSettings.Builder builder)
    {
        // Entities
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 10, 4, 6));
        builder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 5, 1, 1));
    }
}
