/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.gen.feature.BOPConfiguredFeatures;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.Features;
import net.minecraft.data.worldgen.StructureFeatures;
import net.minecraft.data.worldgen.SurfaceBuilders;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraft.world.level.biome.*;
import net.minecraft.world.level.levelgen.GenerationStep;

public class OminousWoodsBiome extends BiomeTemplate
{
    public OminousWoodsBiome()
    {
        this.addWeight(BOPClimates.WET_BOREAL, 1);
        this.setBeachBiome(null);
        this.setRiverBiome(null);
    }

    @Override
    protected void configureBiome(Biome.BiomeBuilder builder)
    {
        builder.precipitation(Biome.Precipitation.RAIN).biomeCategory(Biome.BiomeCategory.TAIGA).depth(0.25F).scale(0.15F).temperature(0.6F).downfall(0.6F);

        builder.specialEffects((new BiomeSpecialEffects.Builder()).waterColor(0x312346).waterFogColor(0x0A030C).fogColor(0x7881A5).skyColor(0x84A1CC).grassColorOverride(0x4C4A70).foliageColorOverride(0x6B487C).ambientParticle(new AmbientParticleSettings(ParticleTypes.SMOKE, 0.00023065104F)).ambientMoodSound(AmbientMoodSettings.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(SurfaceBuilders.GRASS);

        // Structures
        builder.addStructureStart(StructureFeatures.WOODLAND_MANSION);
        builder.addStructureStart(StructureFeatures.SWAMP_HUT);
        BiomeDefaultFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        // Underground
        BiomeDefaultFeatures.addDefaultCarvers(builder);

        builder.addFeature(GenerationStep.Decoration.LAKES, Features.LAKE_WATER);

        BiomeDefaultFeatures.addDefaultCrystalFormations(builder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(builder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(builder);
        BiomeDefaultFeatures.addDefaultOres(builder);

        builder.addFeature(GenerationStep.Decoration.UNDERGROUND_ORES, BOPConfiguredFeatures.BLACK_SAND_DISK);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.OMINOUS_WOODS_TREES);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.OMINOUS_WOODS_FLOWERS);

        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.BRAMBLE);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.DEAD_GRASS_2);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.STANDARD_GRASS_6);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, Features.PATCH_PUMPKIN);
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
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.GLOW_SQUID, 10, 4, 6));
        builder.addSpawn(MobCategory.UNDERGROUND_WATER_CREATURE, new MobSpawnSettings.SpawnerData(EntityType.AXOLOTL, 10, 4, 6));
        builder.addSpawn(MobCategory.AMBIENT, new MobSpawnSettings.SpawnerData(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ILLUSIONER, 50, 2, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.ENDERMAN, 50, 1, 4));
        builder.addSpawn(MobCategory.MONSTER, new MobSpawnSettings.SpawnerData(EntityType.WITCH, 25, 1, 1));
    }
}
