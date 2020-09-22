/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.FeatureUtil;
import biomesoplenty.common.world.biome.BiomeFeatureHelper;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.ChanceConfig;
import net.minecraft.world.gen.placement.Placement;
import net.minecraft.world.gen.placement.TopSolidRangeConfig;

public class BOPConfiguredFeatures
{
    // Trees
    public static final ConfiguredFeature<?, ?> HELLBARK_TREE = register("hellbark_tree", BOPFeatures.HELLBARK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> ORIGIN_TREE = register("origin_tree", BOPFeatures.ORIGIN_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> ALPS_SPRUCE_TREE = register("alps_spruce_tree", BOPFeatures.ALPS_SPRUCE_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FIR_TREE = register("fir_tree", BOPFeatures.FIR_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FIR_TREE_SMALL = register("fir_tree_small", BOPFeatures.FIR_TREE_SMALL.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FIR_TREE_LARGE = register("fir_tree_large", BOPFeatures.FIR_TREE_LARGE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FLOWERING_OAK_TREE = register("flowering_oak_tree", BOPFeatures.FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FLOWERING_OAK_TREE_BEES = register("flowering_oak_tree_bees", BOPFeatures.FLOWERING_OAK_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));
    public static final ConfiguredFeature<?, ?> GIANT_TREE = register("giant_tree", BOPFeatures.GIANT_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> JACARANDA_TREE = register("jacaranda_tree", BOPFeatures.JACARANDA_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> JACARANDA_TREE_BEES = register("jacaranda_tree_bees", BOPFeatures.JACARANDA_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002))));
    public static final ConfiguredFeature<?, ?> MAGIC_TREE = register("magic_tree", BOPFeatures.MAGIC_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> TALL_SWAMP_TREE = register("tall_swamp_tree", BOPFeatures.TALL_SWAMP_TREE.configured(Features.OAK.config()));

    // Big Trees
    public static final ConfiguredFeature<?, ?> BIG_ORIGIN_TREE = register("big_origin_tree", BOPFeatures.BIG_ORIGIN_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", BOPFeatures.BIG_FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_JACARANDA_TREE = register("big_jacaranda_tree", BOPFeatures.BIG_JACARANDA_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_MAGIC_TREE = register("big_magic_tree", BOPFeatures.BIG_MAGIC_TREE.configured(Features.OAK.config()));

    // Bushes
    public static final ConfiguredFeature<?, ?> BUSH = register("bush", BOPFeatures.BUSH.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> ACACIA_BUSH = register("acacia_bush", BOPFeatures.ACACIA_BUSH.configured(Features.OAK.config()));

    /////////////////////////////////////////////////////////////////////

    // Biome Tree Listings
    public static final ConfiguredFeature<?, ?> FUNGAL_JUNGLE_TREES = register("fungal_jungle_trees", BUSH.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> SHRUBLAND_TREES = register("shrubland_trees", BUSH.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.05F, 1))));
    public static final ConfiguredFeature<?, ?> UNDERGROWTH_TREES = register("undergrowth_trees", HELLBARK_TREE.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(35)));
    public static final ConfiguredFeature<?, ?> ORIGIN_HILLS_TREES = register("origin_hills_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BIG_ORIGIN_TREE.weighted(0.1F)), ORIGIN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.4F, 1))));
    public static final ConfiguredFeature<?, ?> CONIFEROUS_FOREST_TREES = register("coniferous_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FIR_TREE.weighted(0.33333334F)), FIR_TREE_LARGE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> SNOWY_CONIFEROUS_FOREST_TREES = register("snowy_coniferous_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FIR_TREE.weighted(0.33333334F)), FIR_TREE_LARGE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(4, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> FIR_CLEARING_TREES = register("fir_clearing_trees", FIR_TREE_SMALL.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> ALPS_FOOTHILLS_TREES = register("alps_foothills_trees", ALPS_SPRUCE_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> MYSTIC_GROVE_TREES = register("mystic_grove_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GIANT_TREE.weighted(0.01F), TALL_SWAMP_TREE.weighted(0.1F), BIG_FLOWERING_OAK_TREE.weighted(0.2F), JACARANDA_TREE.weighted(0.3F), BIG_MAGIC_TREE.weighted(0.3F)), MAGIC_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1))));
    public static final ConfiguredFeature<?, ?> LAVENDER_FIELD_TREES = register("lavender_field_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FLOWERING_OAK_TREE_BEES.weighted(0.2F), BIG_FLOWERING_OAK_TREE.weighted(0.1F), BIG_JACARANDA_TREE.weighted(0.1F)), JACARANDA_TREE_BEES)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 1))));

    // Biome Flower Listings
    public static final ConfiguredFeature<?, ?> UNDERGROWTH_FLOWERS = register("undergrowth_flowers", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.burning_blossom.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
    public static final ConfiguredFeature<?, ?> CONIFEROUS_FOREST_FLOWERS = register("coniferous_forest_flowers", BOPFeatures.CONIFEROUS_FOREST_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> SNOWY_FLOWERS = register("snowy_flowers", BOPFeatures.SNOWY_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> ORIGIN_FLOWERS = register("origin_flowers", BOPFeatures.ORIGIN_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> LAVENDER_FIELD_FLOWERS = register("lavender_field_flowers", BOPFeatures.LAVENDER_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));
    public static final ConfiguredFeature<?, ?> FLOWER_MEADOW_FLOWERS = register("flower_meadow_flowers", BOPFeatures.FLOWER_MEADOW_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> POPPY_FIELD_FLOWERS = register("poppy_field_flowers", BOPFeatures.POPPY_FEATURE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));

    /////////////////////////////////////////////////////////////////////

    // Foliage
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_3 = register("standard_grass_3", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_6 = register("standard_grass_6", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_12 = register("standard_grass_12", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_24 = register("standard_grass_24", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(24)));

    public static final ConfiguredFeature<?, ?> FERN_GRASS_4 = register("fern_grass_4", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_8 = register("fern_grass_8", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_16 = register("fern_grass_16", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(16)));

    public static final ConfiguredFeature<?, ?> WASTELAND_GRASS_1 = register("wasteland_grass_1", BOPFeatures.WASTELAND_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> WASTELAND_GRASS_2 = register("wasteland_grass_2", BOPFeatures.WASTELAND_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(2)));

    public static final ConfiguredFeature<?, ?> BUSH_5 = register("bush_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.bush.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> BUSH_10 = register("bush_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.bush.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> BUSH_15 = register("bush_15", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.bush.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(15)));
    public static final ConfiguredFeature<?, ?> BUSH_50 = register("bush_50", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.bush.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));

    public static final ConfiguredFeature<?, ?> SPROUTS_5 = register("sprouts_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> SPROUTS_10 = register("sprouts_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> SPROUTS_15 = register("sprouts_15", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(15)));
    public static final ConfiguredFeature<?, ?> SPROUTS_25 = register("sprouts_25", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(25)));
    public static final ConfiguredFeature<?, ?> SPROUTS_50 = register("sprouts_50", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));
    public static final ConfiguredFeature<?, ?> SPROUTS_75 = register("sprouts_75", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(75)));
    public static final ConfiguredFeature<?, ?> SPROUTS_200 = register("sprouts_200", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.sprout.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(200)));

    public static final ConfiguredFeature<?, ?> DUNE_GRASS_10 = register("dune_grass_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dune_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> DUNE_GRASS_20 = register("dune_grass_20", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dune_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(20)));
    public static final ConfiguredFeature<?, ?> DUNE_GRASS_100 = register("dune_grass_100", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dune_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(100)));

    public static final ConfiguredFeature<?, ?> DESERT_GRASS_5 = register("desert_grass_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> DESERT_GRASS_10 = register("desert_grass_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> DESERT_GRASS_20 = register("desert_grass_20", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.desert_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(20)));

    public static final ConfiguredFeature<?, ?> DEAD_GRASS_2 = register("dead_grass_2", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dead_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> DEAD_GRASS_5 = register("dead_grass_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dead_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> DEAD_GRASS_45 = register("dead_grass_45", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dead_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(45)));

    public static final ConfiguredFeature<?, ?> TALL_GRASS_6 = register("tall_grass_6", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> TALL_GRASS_12 = register("tall_grass_12", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> TALL_GRASS_24 = register("tall_grass_24", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(24)));
    public static final ConfiguredFeature<?, ?> TALL_GRASS_125 = register("tall_grass_125", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(125)));
    public static final ConfiguredFeature<?, ?> TALL_GRASS_250 = register("tall_grass_250", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.TALL_GRASS.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(FeatureUtil.denseCount(250)));

    public static final ConfiguredFeature<?, ?> LARGE_FERN_7 = register("large_fern_7", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LARGE_FERN.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(7)));
    public static final ConfiguredFeature<?, ?> LARGE_FERN_14 = register("large_fern_14", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LARGE_FERN.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(14)));

    /////////////////////////////////////////////////////////////////////

    // Plants
    public static final ConfiguredFeature<?, ?> CATTAIL_6 = register("cattail_6", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.cattail.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> CATTAIL_12 = register("cattail_12", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.cattail.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> CATTAIL_24 = register("cattail_24", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.cattail.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(24)));

    public static final ConfiguredFeature<?, ?> TOADSTOOL_NORMAL = register("toadstool_normal", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> TOADSTOOL_EXTRA = register("toadstool_extra", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> GLOWSHROOM_EXTRA = register("glowshroom_extra", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.glowshroom.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(20)));
    public static final ConfiguredFeature<?, ?> PASTURE_TALL_WHEAT = register("pasture_tall_wheat", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.tall_wheat.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(600)));

    // Water Plants
    public static final ConfiguredFeature<?, ?> REED_5 = register("reed_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> REED_10 = register("reed_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));

    public static final ConfiguredFeature<?, ?> WATERGRASS_5 = register("watergrass_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_10 = register("watergrass_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_25 = register("watergrass_25", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(25)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_50 = register("watergrass_50", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_250 = register("watergrass_200", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(250)));

    public static final ConfiguredFeature<?, ?> MANGROVE_ROOTS = register("mangrove_roots", BOPFeatures.MANGROVE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(125)));

    public static final ConfiguredFeature<?, ?> LILY_PAD_5 = register("lily_pad_5", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(5));
    public static final ConfiguredFeature<?, ?> LILY_PAD_10 = register("lily_pad_10", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(10));
    public static final ConfiguredFeature<?, ?> LILY_PAD_15 = register("lily_pad_15", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(15));

    // Large Flowers
    public static final ConfiguredFeature<?, ?> PEONY_1 = register("peony_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.PEONY.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> SUNFLOWER_1 = register("sunflower_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.SUNFLOWER.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));

    public static final ConfiguredFeature<?, ?> LILAC_1 = register("lilac_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> LILAC_2 = register("lilac_2", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> LILAC_4 = register("lilac_4", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.LILAC.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));

    public static final ConfiguredFeature<?, ?> ROSE_BUSH_1 = register("rose_bush_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.ROSE_BUSH.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> ROSE_BUSH_2 = register("rose_bush_2", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.ROSE_BUSH.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> ROSE_BUSH_4 = register("rose_bush_4", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.ROSE_BUSH.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> ROSE_BUSH_15 = register("rose_bush_15", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(Blocks.ROSE_BUSH.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(15)));

    public static final ConfiguredFeature<?, ?> GOLDENROD_1 = register("goldenrod_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.goldenrod.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> GOLDENROD_2 = register("goldenrod_2", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.goldenrod.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> GOLDENROD_8 = register("goldenrod_8", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.goldenrod.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(8)));

    public static final ConfiguredFeature<?, ?> BLUE_HYDRANGEA_1 = register("blue_hydrangea_1", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> BLUE_HYDRANGEA_4 = register("blue_hydrangea_4", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.blue_hydrangea.defaultBlockState())).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));

    /////////////////////////////////////////////////////////////////////

    // Features
    public static final ConfiguredFeature<?, ?> BIG_PUMPKIN = register("big_pumpkin", BOPFeatures.BIG_PUMPKIN.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> BLACK_SANDSTONE_COLUMN = register("black_sandstone_column", BOPFeatures.BLACK_SANDSTONE_COLUMN.configured(new ColumnConfig(FeatureSpread.fixed(1), FeatureSpread.of(1, 3))).decorated(Placement.COUNT_MULTILAYER.configured(new FeatureSpreadConfig(2))).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> BLACK_SAND_DISK = register("black_sand_disk", Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.black_sand.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(4));
    public static final ConfiguredFeature<?, ?> BONE_SPINE = register("bone_spine", BOPFeatures.BONE_SPINE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> BRAMBLE = register("bramble", BOPFeatures.BRAMBLE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> FLESH_TENDON = register("flesh_tendon", BOPFeatures.FLESH_TENDON.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(65)));
    public static final ConfiguredFeature<?, ?> GRAVEL_DISK_EXTRA = register("gravel_disk_extra", Feature.DISK.configured(new SphereReplaceConfig(Blocks.GRAVEL.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(6));
    public static final ConfiguredFeature<?, ?> HUGE_BROWN_MUSHROOM_EXTRA = register("huge_brown_mushroom_extra", Features.HUGE_BROWN_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(17)));
    public static final ConfiguredFeature<?, ?> HUGE_GLOWSHROOM = register("huge_glowshroom", BOPFeatures.HUGE_GLOWSHROOM.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(7)));
    public static final ConfiguredFeature<?, ?> HUGE_RED_MUSHROOM_EXTRA = register("huge_red_mushroom_extra", Features.HUGE_RED_MUSHROOM.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> LARGE_CRYSTAL = register("large_crystal", BOPFeatures.LARGE_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(200)));
    public static final ConfiguredFeature<?, ?> LAVA_LAKE_COMMON = register("lava_lake_common", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.LAVA.defaultBlockState())).decorated(Placement.LAVA_LAKE.configured(new ChanceConfig(10))));
    public static final ConfiguredFeature<?, ?> MUD_DISK = register("mud_disk", Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.mud.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(5));
    public static final ConfiguredFeature<?, ?> MUD_LAKE = register("mud_lake", Feature.LAKE.configured(new BlockStateFeatureConfig(BOPBlocks.mud.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(3))));
    public static final ConfiguredFeature<?, ?> MYCELIUM_SPLATTER = register("mycelium_splatter", BOPFeatures.MYCELIUM_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(14)));
    public static final ConfiguredFeature<?, ?> NETHER_BRAMBLE = register("nether_bramble", BOPFeatures.BRAMBLE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));
    public static final ConfiguredFeature<?, ?> NETHER_VINES = register("nether_vines", BOPFeatures.NETHER_VINES.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPFeatures.OBSIDIAN_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> PODZOL_SPLATTER = register("podzol_splatter", BOPFeatures.PODZOL_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(14)));
    public static final ConfiguredFeature<?, ?> PUMPKIN_PATCH = register("pumpkin_patch", BOPFeatures.PUMPKIN_PATCH.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(35)));
    public static final ConfiguredFeature<?, ?> SCATTERED_ROCKS = register("scattered_rocks", BOPFeatures.SCATTERED_ROCKS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> SCRUB_EXTRA = register("scrub_extra", BOPFeatures.SCRUB.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(35)));
    public static final ConfiguredFeature<?, ?> SCRUB_NORMAL = register("scrub_normal", BOPFeatures.SCRUB.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> SHORT_BAMBOO_10 = register("short_bamboo_10", BOPFeatures.SHORT_BAMBOO.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> SHORT_BAMBOO_5 = register("short_bamboo_5", BOPFeatures.SHORT_BAMBOO.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> SMALL_BROWN_MUSHROOM = register("small_brown_mushroom", BOPFeatures.SMALL_BROWN_MUSHROOM.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> SMALL_CRYSTAL = register("small_crystal", BOPFeatures.SMALL_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(40)));
    public static final ConfiguredFeature<?, ?> SMALL_GLOWSHROOM = register("small_glowshroom", BOPFeatures.SMALL_GLOWSHROOM.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> SMALL_RED_MUSHROOM = register("small_red_mushroom", BOPFeatures.SMALL_RED_MUSHROOM.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> VOLCANO_GRASS_SPLATTER = register("volcano_grass_splatter", BOPFeatures.GRASS_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(25)));
    public static final ConfiguredFeature<?, ?> VOLCANO_SPRING = register("volcano_spring", Feature.SPRING.configured(BOPFeatures.VOLCANO_SPRING_CONFIG).decorated(Placement.RANGE_VERY_BIASED.configured(new TopSolidRangeConfig(8, 16, 256))).squared().count(75));
    public static final ConfiguredFeature<?, ?> WATER_LAKE_COMMON = register("water_lake_common", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(1))));
    public static final ConfiguredFeature<?, ?> WATER_LAKE_UNCOMMON = register("water_lake_uncommon", Feature.LAKE.configured(new BlockStateFeatureConfig(Blocks.WATER.defaultBlockState())).decorated(Placement.WATER_LAKE.configured(new ChanceConfig(3))));
    public static final ConfiguredFeature<?, ?> WHITE_SAND_DISK = register("white_sand_disk", Feature.DISK.configured(new SphereReplaceConfig(BOPBlocks.white_sand.defaultBlockState(), FeatureSpread.of(4, 2), 1, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState()))).decorated(Features.Placements.TOP_SOLID_HEIGHTMAP_SQUARE).count(8));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
