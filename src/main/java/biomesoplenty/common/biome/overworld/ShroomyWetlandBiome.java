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
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.Features;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilders;

public class ShroomyWetlandBiome extends BiomeTemplate
{
    public ShroomyWetlandBiome()
    {
        this.setBeachBiome(null);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.SWAMP).depth(0.0F).scale(0.0F).temperature(0.6F).downfall(0.7F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(0x272179).waterFogColor(0x0C031B).fogColor(12638463).skyColor(calculateSkyColor(0.6F)).grassColorOverride(0x5A935F).foliageColorOverride(0x4F9657).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettings.Builder builder)
    {
        builder.surfaceBuilder(ConfiguredSurfaceBuilders.GRASS);

        // Structures
        builder.addStructureStart(StructureFeatures.SWAMP_HUT);
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);

        builder.addFeature(GenerationStage.Decoration.LAKES, BOPConfiguredFeatures.WATER_LAKE_UNCOMMON);

        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);
        DefaultBiomeFeatures.addSwampClayDisk(builder);
        
        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, BOPConfiguredFeatures.MUD_DISK);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.SHROOMY_WETLAND_TREES);

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.CATTAIL_12);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.FERN_GRASS_8);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.LILY_PAD_5);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.REED_5);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.STANDARD_GRASS_3);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.TOADSTOOL_EXTRA);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.WATERGRASS_5);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.HUGE_BROWN_MUSHROOM_EXTRA);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.PODZOL_SPLATTER);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPConfiguredFeatures.SMALL_BROWN_MUSHROOM);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.BROWN_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.RED_MUSHROOM_NORMAL);
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Features.SEAGRASS_SWAMP);

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 1, 1, 1));
    }
}
