/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class MysticPlainsBiome extends BiomeTemplate
{
    public MysticPlainsBiome()
    {
        this.setBeachBiome(null);
        this.setRiverBiome(null);
    }

    @Override
    protected void configureBiome(Biome.BiomeBuilder builder)
    {
        builder.precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.FOREST).depth(0.0F).scale(-0.05F).temperature(0.7F).downfall(0.8F);

        builder.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(0x9C3FE4).waterFogColor(0x2E0533).fogColor(0xFFC9DA).skyColor(0xAAEFFF).grassColorOverride(0x69CFDB).foliageColorOverride(0x70E0B5).ambientParticle(new AmbientParticleSettings(ParticleTypes.END_ROD, 0.00011532552F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(SurfaceBuilders.GRASS);

        // Structures
        BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);

        // Underground
        BiomeDefaultFeatures.addDefaultCarvers(builder);

        builder.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_WATER);

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, Features.DISK_CLAY);
        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPConfiguredFeatures.WHITE_SAND_DISK);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.MYSTIC_PLAINS_TREES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.MYSTIC_PLAINS_FLOWERS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.BLUE_HYDRANGEA_4);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.CLOVER_6);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.LILAC_4);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.SPROUTS_25);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.STANDARD_GRASS_24);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.TALL_GRASS_6);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.WATERGRASS_10);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_NORMAL);

        ////////////////////////////////////////////////////////////

        // Other Features
        BiomeDefaultFeatures.addDefaultSprings(builder);
        BiomeDefaultFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnSettings.Builder builder)
    {
        // Entities
        builder.addSpawn(MobCategory.CREATURE, new MobSpawnSettings.SpawnerData(EntityType.RABBIT, 4, 2, 3));
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 10, 4, 6));
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 10, 4, 6));
        builder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 20, 1, 1));
    }
}
