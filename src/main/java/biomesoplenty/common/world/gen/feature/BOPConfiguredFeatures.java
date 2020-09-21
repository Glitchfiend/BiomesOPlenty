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
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.GenerationStage;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.placement.AtSurfaceWithExtraConfig;
import net.minecraft.world.gen.placement.Placement;

public class BOPConfiguredFeatures
{
    // Flowers
    public static final ConfiguredFeature<?, ?> LAVENDER_FLOWERS = register("lavender_flowers", BOPFeatures.LAVENDER_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));
    public static final ConfiguredFeature<?, ?> FLOWER_MEADOW_FLOWERS = register("flower_meadow_flowers", BOPFeatures.FLOWER_MEADOW_FLOWERS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> POPPY_FIELD_FLOWERS = register("poppy_field_flowers", BOPFeatures.POPPY_FEATURE.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(500)));

    // Vegetation
    public static final ConfiguredFeature<?, ?> LIGHT_SUGAR_CANE = register("light_sugar_cane", Feature.RANDOM_PATCH.configured(Features.Configs.SUGAR_CANE_CONFIG).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(10)));
    public static final ConfiguredFeature<?, ?> PASTURE_TALL_WHEAT = register("pasture_tall_wheat", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDouble(BOPBlocks.tall_wheat.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(600)));

    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_3 = register("standard_grass_3", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(3)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_6 = register("standard_grass_6", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(6)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_12 = register("standard_grass_12", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(12)));
    public static final ConfiguredFeature<?, ?> STANDARD_GRASS_24 = register("standard_grass_24", BOPFeatures.STANDARD_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(24)));

    public static final ConfiguredFeature<?, ?> FERN_GRASS_4 = register("fern_grass_4", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(4)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_8 = register("fern_grass_8", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(8)));
    public static final ConfiguredFeature<?, ?> FERN_GRASS_16 = register("fern_grass_16", BOPFeatures.FERN_GRASS.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(16)));

    public static final ConfiguredFeature<?, ?> FLOODPLAIN_GRASS = register("floodplain_grass", Feature.RANDOM_PATCH.configured(BiomeFeatureHelper.createClusterConfigurationDoubleWater(BOPBlocks.watergrass.defaultBlockState())).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(250)));

    // Features
    public static final ConfiguredFeature<?, ?> NETHER_VINES = register("nether_vines", BOPFeatures.NETHER_VINES.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(150)));
    public static final ConfiguredFeature<?, ?> LARGE_CRYSTAL = register("large_crystal", BOPFeatures.LARGE_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE).decorated(FeatureUtil.denseCount(200)));
    public static final ConfiguredFeature<?, ?> SMALL_CRYSTAL = register("small_crystal", BOPFeatures.SMALL_CRYSTAL.configured(IFeatureConfig.NONE).decorated(Features.Placements.HEIGHTMAP_DOUBLE_SQUARE.count(40)));

    // Big trees
    public static final ConfiguredFeature<?, ?> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", BOPFeatures.BIG_FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_JACARANDA_TREE = register("big_jacaranda_tree", BOPFeatures.BIG_JACARANDA_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> BIG_MAGIC_TREE = register("big_magic_tree", BOPFeatures.BIG_MAGIC_TREE.configured(Features.OAK.config()));

    // Trees
    public static final ConfiguredFeature<?, ?> FLOWERING_OAK_TREE = register("flowering_oak_tree", BOPFeatures.FLOWERING_OAK_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> FLOWERING_OAK_TREE_BEES = register("flowering_oak_tree_bees", BOPFeatures.FLOWERING_OAK_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_005))));
    public static final ConfiguredFeature<?, ?> GIANT_TREE = register("giant_tree", BOPFeatures.GIANT_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> JACARANDA_TREE = register("jacaranda_tree", BOPFeatures.JACARANDA_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> JACARANDA_TREE_BEES = register("jacaranda_tree_bees", BOPFeatures.JACARANDA_TREE.configured(Features.OAK.config().withDecorators(ImmutableList.of(Features.Placements.BEEHIVE_0002))));
    public static final ConfiguredFeature<?, ?> MAGIC_TREE = register("magic_tree", BOPFeatures.MAGIC_TREE.configured(Features.OAK.config()));
    public static final ConfiguredFeature<?, ?> TALL_SWAMP_TREE = register("tall_swamp_tree", BOPFeatures.TALL_SWAMP_TREE.configured(Features.OAK.config()));

    // Tree assortments
    public static final ConfiguredFeature<?, ?> MYSTIC_GROVE_TREES = register("mystic_grove_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(GIANT_TREE.weighted(0.01F), TALL_SWAMP_TREE.weighted(0.1F), BIG_FLOWERING_OAK_TREE.weighted(0.2F), JACARANDA_TREE.weighted(0.3F), BIG_MAGIC_TREE.weighted(0.3F)), MAGIC_TREE)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(10, 0.5F, 1))));
    public static final ConfiguredFeature<?, ?> LAVENDER_FIELD_TREES = register("lavender_field_trees", Feature.RANDOM_SELECTOR.configured(new MultipleRandomFeatureConfig(ImmutableList.of(FLOWERING_OAK_TREE_BEES.weighted(0.2F), BIG_FLOWERING_OAK_TREE.weighted(0.1F), BIG_JACARANDA_TREE.weighted(0.1F)), JACARANDA_TREE_BEES)).decorated(Features.Placements.HEIGHTMAP_SQUARE).decorated(Placement.COUNT_EXTRA.configured(new AtSurfaceWithExtraConfig(1, 0.2F, 1))));

    private static <FC extends IFeatureConfig> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(WorldGenRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
