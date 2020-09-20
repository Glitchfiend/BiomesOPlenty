/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.biome.BiomeTemplate;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.common.world.gen.feature.BOPFeatures;
import biomesoplenty.common.world.gen.surfacebuilders.BOPSurfaceBuilders;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class MangroveBiome extends BiomeTemplate
{
    public MangroveBiome()
    {
        this.addWeight(BOPClimates.SUBTROPICAL, 10);
        this.setBeachBiome(null);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.SWAMP).depth(-0.25F).scale(-0.025F).temperature(0.81F).downfall(0.7F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(0x448FBD).waterFogColor(0x061326).fogColor(12638463).skyColor(calculateSkyColor(0.81F)).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(BOPSurfaceBuilders.MANGROVE, SurfaceBuilder.CONFIG_GRASS));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_SWAMP);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);

        builder.addFeature(GenerationStage.Decoration.LOCAL_MODIFICATIONS, Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(1))));

        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        builder.addFeature(GenerationStage.Decoration.UNDERGROUND_ORES, Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.mud.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(5));

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.BUSH.configured(Features.OAK.config()).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(15, 0.3F, 1))));

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.MANGROVE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(125)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(10)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityConfig(0.6F)).count(32).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(25)));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.COD, 15, 3, 6));
        builder.addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.PUFFERFISH, 15, 1, 3));
        builder.addSpawn(EntityClassification.WATER_AMBIENT, new MobSpawnInfo.Spawners(EntityType.TROPICAL_FISH, 25, 8, 8));
        builder.addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.SQUID, 10, 4, 4));
        builder.addSpawn(EntityClassification.WATER_CREATURE, new MobSpawnInfo.Spawners(EntityType.DOLPHIN, 2, 1, 2));
        builder.addSpawn(EntityClassification.AMBIENT, new MobSpawnInfo.Spawners(EntityType.BAT, 10, 8, 8));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SPIDER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE, 95, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ZOMBIE_VILLAGER, 5, 1, 1));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SKELETON, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.CREEPER, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.SLIME, 100, 4, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.ENDERMAN, 10, 1, 4));
        builder.addSpawn(EntityClassification.MONSTER, new MobSpawnInfo.Spawners(EntityType.WITCH, 5, 1, 1));
    }
}
