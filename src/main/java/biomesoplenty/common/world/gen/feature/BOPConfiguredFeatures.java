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
import net.minecraft.world.gen.placement.Placement;

public class BOPConfiguredFeatures
{
    // Flowers
    public static final ConfiguredFeature<?, ?> UNDERGROWTH_FLOWERS = register("undergrowth_flowers", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.burning_blossom.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
    public static final ConfiguredFeature<?, ?> CONIFEROUS_FOREST_FLOWERS = register("coniferous_forest_flowers", BOPFeatures.CONIFEROUS_FOREST_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> SNOWY_FLOWERS = register("snowy_flowers", BOPFeatures.SNOWY_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(2)));
    public static final ConfiguredFeature<?, ?> ORIGIN_FLOWERS = register("origin_flowers", BOPFeatures.ORIGIN_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.ADD_32).decorated(Features.Placements.HEIGHTMAP_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> LAVENDER_FLOWERS = register("lavender_flowers", BOPFeatures.LAVENDER_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));
    public static final ConfiguredFeature<?, ?> FLOWER_MEADOW_FLOWERS = register("flower_meadow_flowers", BOPFeatures.FLOWER_MEADOW_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> POPPY_FIELD_FLOWERS = register("poppy_field_flowers", BOPFeatures.POPPY_FEATURE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));

    // Foliage
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_3 = register("standard_grass_3", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_6 = register("standard_grass_6", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_12 = register("standard_grass_12", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_24 = register("standard_grass_24", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(24)));

    public static final ConfiguredFeature<?, ?> FERN_GRASS_4 = register("fern_grass_4", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_8 = register("fern_grass_8", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_16 = register("fern_grass_16", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(16)));

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

    public static final ConfiguredFeature<?, ?> DEAD_GRASS_45 = register("dead_grass_45", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.dead_grass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(45)));

    // Plants
    public static final ConfiguredFeature<?, ?> TOADSTOOL_NORMAL = register("toadstool_normal", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfiguration(BOPBlocks.toadstool.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(1)));
    public static final ConfiguredFeature<?, ?> PASTURE_TALL_WHEAT = register("pasture_tall_wheat", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.tall_wheat.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(600)));

    // Water Plants
    public static final ConfiguredFeature<?, ?> REED_5 = register("reed_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> REED_10 = register("reed_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.reed.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));

    public static final ConfiguredFeature<?, ?> WATERGRASS_5 = register("watergrass_5", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(5)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_10 = register("watergrass_10", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_25 = register("watergrass_25", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(25)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_50 = register("watergrass_50", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));
    public static final ConfiguredFeature<?, ?> WATERGRASS_250 = register("watergrass_200", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(250)));

    public static final ConfiguredFeature<?, ?> LILY_PAD_5 = register("lily_pad_5", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(5));
    public static final ConfiguredFeature<?, ?> LILY_PAD_10 = register("lily_pad_10", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(10));
    public static final ConfiguredFeature<?, ?> LILY_PAD_15 = register("lily_pad_15", Feature.RANDOM_PATCH.configured((new BlockClusterFeatureConfig.Builder(new SimpleBlockStateProvider(Blocks.LILY_PAD.defaultBlockState()), SimpleBlockPlacer.INSTANCE)).tries(10).build()).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).count(15));

    // Features
    public static final ConfiguredFeature<?, ?> NETHER_VINES = register("nether_vines", BOPFeatures.NETHER_VINES.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> LARGE_CRYSTAL = register("large_crystal", BOPFeatures.LARGE_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(200)));
    public static final ConfiguredFeature<?, ?> SMALL_CRYSTAL = register("small_crystal", BOPFeatures.SMALL_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(40)));
    public static final ConfiguredFeature<?, ?> FLESH_TENDON = register("flesh_tendon", BOPFeatures.FLESH_TENDON.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(65)));
    public static final ConfiguredFeature<?, ?> BONE_SPINE = register("bone_spine", BOPFeatures.BONE_SPINE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPFeatures.OBSIDIAN_SPLATTER.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> BRAMBLE = register("bramble", BOPFeatures.BRAMBLE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> NETHER_BRAMBLE = register("nether_bramble", BOPFeatures.BRAMBLE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(50)));

    // Big trees
    public static final ConfiguredFeature<?, ?> BIG_ORIGIN_TREE = register("big_origin_tree", BOPFeatures.BIG_ORIGIN_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", BOPFeatures.BIG_FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_JACARANDA_TREE = register("big_jacaranda_tree", BOPFeatures.BIG_JACARANDA_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_MAGIC_TREE = register("big_magic_tree", BOPFeatures.BIG_MAGIC_TREE.configured(Features.OAK.config()));

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

    // Tree assortments
    public static final ConfiguredFeature<?, ?> UNDERGROWTH_TREES = register("undergrowth_trees", HELLBARK_TREE.decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(35)));
    public static final ConfiguredFeature<?, ?> ORIGIN_HILLS_TREES = register("origin_hills_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(BIG_ORIGIN_TREE.weighted(0.1F)), ORIGIN_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(3, 0.4F, 1))));
    public static final ConfiguredFeature<?, ?> CONIFEROUS_FOREST_TREES = register("coniferous_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FIR_TREE.weighted(0.33333334F)), FIR_TREE_LARGE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> SNOWY_CONIFEROUS_FOREST_TREES = register("snowy_coniferous_forest_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FIR_TREE.weighted(0.33333334F)), FIR_TREE_LARGE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(4, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> FIR_CLEARING_TREES = register("fir_clearing_trees", FIR_TREE_SMALL.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> ALPS_FOOTHILLS_TREES = register("alps_foothills_trees", ALPS_SPRUCE_TREE.decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(2, 0.1F, 1))));
    public static final ConfiguredFeature<?, ?> MYSTIC_GROVE_TREES = register("mystic_grove_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GIANT_TREE.weighted(0.01F), TALL_SWAMP_TREE.weighted(0.1F), BIG_FLOWERING_OAK_TREE.weighted(0.2F), JACARANDA_TREE.weighted(0.3F), BIG_MAGIC_TREE.weighted(0.3F)), MAGIC_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1))));
    public static final ConfiguredFeature<?, ?> LAVENDER_FIELD_TREES = register("lavender_field_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FLOWERING_OAK_TREE_BEES.weighted(0.2F), BIG_FLOWERING_OAK_TREE.weighted(0.1F), BIG_JACARANDA_TREE.weighted(0.1F)), JACARANDA_TREE_BEES)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 1))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
