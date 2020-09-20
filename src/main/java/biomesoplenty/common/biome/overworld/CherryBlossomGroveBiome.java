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
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityClassification;
import net.minecraft.entity.EntityType;
import net.minecraft.world.biome.*;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.structure.StructureFeatures;
import net.minecraft.world.gen.placement.*;
import net.minecraft.world.gen.surfacebuilders.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;

public class CherryBlossomGroveBiome extends BiomeTemplate
{
    public CherryBlossomGroveBiome()
    {
        this.addWeight(BOPClimates.COOL_TEMPERATE, 1);
    }

    @Override
    protected void configureBiome(Biome.Builder builder)
    {
        builder.precipitation(Biome.RainType.RAIN).biomeCategory(Biome.Category.FOREST).depth(-0.1F).scale(0.1F).temperature(0.6F).downfall(0.9F);

        builder.specialEffects((new BiomeAmbience.Builder()).waterColor(4159204).waterFogColor(329011).fogColor(12638463).skyColor(calculateSkyColor(0.6F)).grassColorOverride(0x85CE71).foliageColorOverride(0x63BF66).ambientMoodSound(MoodSoundAmbience.LEGACY_CAVE_SETTINGS).build());
    }

    @Override
    protected void configureGeneration(BiomeGenerationSettingsRegistryBuilder builder)
    {
        builder.surfaceBuilder(new ConfiguredSurfaceBuilder(SurfaceBuilder.DEFAULT, SurfaceBuilder.CONFIG_GRASS));

        // Structures
        DefaultBiomeFeatures.addDefaultOverworldLandStructures(builder);
        builder.addStructureStart(StructureFeatures.RUINED_PORTAL_STANDARD);

        // Underground
        DefaultBiomeFeatures.addDefaultCarvers(builder);
        DefaultBiomeFeatures.addDefaultLakes(builder);
        DefaultBiomeFeatures.addDefaultMonsterRoom(builder);
        DefaultBiomeFeatures.addDefaultUndergroundVariety(builder);
        DefaultBiomeFeatures.addDefaultOres(builder);

        ////////////////////////////////////////////////////////////

        // Vegetation
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BOPFeatures.WHITE_CHERRY_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_002))).weighted(0.1F), BOPFeatures.PINK_CHERRY_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_002))).weighted(0.2F), BOPFeatures.BIG_WHITE_CHERRY_TREE.configured(Features.OAK.config()).weighted(0.3F), BOPFeatures.FLOWERING_OAK_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_002))).weighted(0.15F), BOPFeatures.FLOWERING_BUSH.configured(Features.OAK.config()).weighted(0.3F)), BOPFeatures.BIG_PINK_CHERRY_TREE.configured(Features.OAK.config()))).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(5, 0.3F, 1))));

        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.PEONY.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.CHERRY_BLOSSOM_GROVE_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(5)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(11)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, BOPFeatures.SHORT_BAMBOO.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.BROWN_MUSHROOM.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.chance(2)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(Blocks.RED_MUSHROOM.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.chance(4)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(15)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
        builder.addFeature(GenerationStage.Decoration.VEGETAL_DECORATION, Feature.SEAGRASS.configured(new ProbabilityConfig(0.6F)).count(32).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE));

        ////////////////////////////////////////////////////////////

        // Other Features
        DefaultBiomeFeatures.addDefaultSprings(builder);
        DefaultBiomeFeatures.addSurfaceFreezing(builder);
    }

    @Override
    protected void configureMobSpawns(MobSpawnInfo.Builder builder)
    {
        // Entities
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.SHEEP, 12, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PIG, 10, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.CHICKEN, 10, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.COW, 8, 4, 4));
        builder.addSpawn(EntityClassification.CREATURE, new MobSpawnInfo.Spawners(EntityType.PANDA, 80, 1, 2));
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
