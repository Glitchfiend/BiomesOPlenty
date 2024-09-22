/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.worldgen.placement.BOPTreePlacements;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.TreePlacements;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.PinkPetalsBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;

public class BOPVegetationFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> HIGH_GRASS = BOPFeatureUtils.createKey("high_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_DRIPLEAF = BOPFeatureUtils.createKey("big_dripleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_PUMPKIN = BOPFeatureUtils.createKey("big_pumpkin");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRAMBLE = BOPFeatureUtils.createKey("bramble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> COLD_DESERT_ROCKS = BOPFeatureUtils.createKey("cold_desert_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_LOG = BOPFeatureUtils.createKey("fallen_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_FIR_LOG = BOPFeatureUtils.createKey("fallen_fir_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_BIRCH_LOG = BOPFeatureUtils.createKey("fallen_birch_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FALLEN_JACARANDA_LOG = BOPFeatureUtils.createKey("fallen_jacaranda_log");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GOLDENROD = BOPFeatureUtils.createKey("goldenrod");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOT_SPRING_VENTS = BOPFeatureUtils.createKey("hot_spring_vents");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_CLOVER = BOPFeatureUtils.createKey("huge_clover");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_LILY_PAD = BOPFeatureUtils.createKey("huge_lily_pad");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_TOADSTOOL = BOPFeatureUtils.createKey("huge_toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DRIPSTONE_SPLATTER = BOPFeatureUtils.createKey("dripstone_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSS_SPLATTER = BOPFeatureUtils.createKey("moss_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MYCELIUM_SPLATTER = BOPFeatureUtils.createKey("mycelium_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BARLEY = BOPFeatureUtils.createKey("patch_barley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BLUE_HYDRANGEA = BOPFeatureUtils.createKey("patch_blue_hydrangea");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_BUSH = BOPFeatureUtils.createKey("patch_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CATTAIL = BOPFeatureUtils.createKey("patch_cattail");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_CLOVER = BOPFeatureUtils.createKey("patch_clover");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DEAD_GRASS = BOPFeatureUtils.createKey("patch_dead_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DESERT_GRASS = BOPFeatureUtils.createKey("patch_desert_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_DUNE_GRASS = BOPFeatureUtils.createKey("patch_dune_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_FERN = BOPFeatureUtils.createKey("patch_fern");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ICY_IRIS = BOPFeatureUtils.createKey("patch_icy_iris");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_LILAC = BOPFeatureUtils.createKey("patch_lilac");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_PEONY = BOPFeatureUtils.createKey("patch_peony");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_REED = BOPFeatureUtils.createKey("patch_reed");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SEA_OATS = BOPFeatureUtils.createKey("patch_sea_oats");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_SPROUTS = BOPFeatureUtils.createKey("patch_sprouts");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_LAVENDER = BOPFeatureUtils.createKey("patch_tall_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TALL_WHITE_LAVENDER = BOPFeatureUtils.createKey("patch_tall_white_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TINY_CACTUS = BOPFeatureUtils.createKey("patch_tiny_cactus");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_TUNDRA_SHRUBS = BOPFeatureUtils.createKey("patch_tundra_shrubs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATERGRASS = BOPFeatureUtils.createKey("patch_watergrass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_WATERLILY_FLOWER = BOPFeatureUtils.createKey("patch_waterlily_flower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PUMPKIN_PATCH = BOPFeatureUtils.createKey("pumpkin_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RED_MAPLE_LEAF_PILES = BOPFeatureUtils.createKey("red_maple_leaf_piles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_MAPLE_LEAF_PILES = BOPFeatureUtils.createKey("orange_maple_leaf_piles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_MAPLE_LEAF_PILES = BOPFeatureUtils.createKey("yellow_maple_leaf_piles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPARSE_DUNE_GRASS = BOPFeatureUtils.createKey("sparse_dune_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINFOREST_CLIFFS_VINES = BOPFeatureUtils.createKey("rainforest_cliffs_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROOTED_STUMP = BOPFeatureUtils.createKey("rooted_stump");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ROSE_BUSH = BOPFeatureUtils.createKey("rose_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCATTERED_ROCKS = BOPFeatureUtils.createKey("scattered_rocks");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SCRUB = BOPFeatureUtils.createKey("scrub");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPROUT_BONEMEAL = BOPFeatureUtils.createKey("sprout_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SUNFLOWER = BOPFeatureUtils.createKey("sunflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SHORT_BAMBOO = BOPFeatureUtils.createKey("short_bamboo");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TERMITE_MOUND = BOPFeatureUtils.createKey("termite_mound");
    public static final ResourceKey<ConfiguredFeature<?, ?>> THIN_BAMBOO = BOPFeatureUtils.createKey("thin_bamboo");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_DRIPLEAF = BOPFeatureUtils.createKey("small_dripleaf");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_BROWN_MUSHROOM = BOPFeatureUtils.createKey("small_brown_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_RED_MUSHROOM = BOPFeatureUtils.createKey("small_red_mushroom");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_TOADSTOOL = BOPFeatureUtils.createKey("small_toadstool");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TOADSTOOL_NORMAL = BOPFeatureUtils.createKey("toadstool_normal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WASTELAND_GRASS = BOPFeatureUtils.createKey("wasteland_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_CONIFEROUS_FOREST = BOPFeatureUtils.createKey("flower_coniferous_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_DEFAULT_EXTENDED = BOPFeatureUtils.createKey("flower_default_extended");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_FIELD = BOPFeatureUtils.createKey("flower_field");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_OVERGROWN_GREENS = BOPFeatureUtils.createKey("flower_overgrown_greens");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_JACARANDA_GLADE = BOPFeatureUtils.createKey("flower_jacaranda_glade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_LAVENDER = BOPFeatureUtils.createKey("flower_lavender");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_MOOR = BOPFeatureUtils.createKey("flower_moor");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_MYSTIC_GROVE = BOPFeatureUtils.createKey("flower_mystic_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_OMINOUS_WOODS = BOPFeatureUtils.createKey("flower_ominous_woods");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_ORIGIN_VALLEY = BOPFeatureUtils.createKey("flower_origin_valley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_POPPY = BOPFeatureUtils.createKey("flower_poppy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_RAINFOREST = BOPFeatureUtils.createKey("flower_rainforest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SHRUBLAND = BOPFeatureUtils.createKey("flower_shrubland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SNOWBLOSSOM_GROVE = BOPFeatureUtils.createKey("flower_snowblossom_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_SNOWY = BOPFeatureUtils.createKey("flower_snowy");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_TROPICS = BOPFeatureUtils.createKey("flower_tropics");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_VIOLET = BOPFeatureUtils.createKey("flower_violet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WASTELAND = BOPFeatureUtils.createKey("flower_wasteland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WETLAND = BOPFeatureUtils.createKey("flower_wetland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_WILDFLOWER = BOPFeatureUtils.createKey("flower_wildflower");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_ASPEN_GLADE = BOPFeatureUtils.createKey("trees_aspen_glade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_AURORAL_GARDEN = BOPFeatureUtils.createKey("trees_auroral_garden");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_BAYOU = BOPFeatureUtils.createKey("trees_bayou");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_BOG = BOPFeatureUtils.createKey("trees_bog");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_CONIFEROUS_FOREST = BOPFeatureUtils.createKey("trees_coniferous_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_DEAD_FOREST = BOPFeatureUtils.createKey("trees_dead_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_DENSE_WOODLAND = BOPFeatureUtils.createKey("trees_dense_woodland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_DRYLAND = BOPFeatureUtils.createKey("trees_dryland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_FIELD = BOPFeatureUtils.createKey("trees_field");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_FIELD_FOREST = BOPFeatureUtils.createKey("trees_field_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_FUNGAL_JUNGLE = BOPFeatureUtils.createKey("trees_fungal_jungle");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_HOT_SPRINGS = BOPFeatureUtils.createKey("trees_hot_springs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_JADE_CLIFFS = BOPFeatureUtils.createKey("trees_jade_cliffs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LAVENDER_FIELD = BOPFeatureUtils.createKey("trees_lavender_field");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_JACARANDA_GLADE = BOPFeatureUtils.createKey("trees_jacaranda_glade");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_LUSH_DESERT = BOPFeatureUtils.createKey("trees_lush_desert");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MAPLE_FOREST = BOPFeatureUtils.createKey("trees_maple_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MEDITERRANEAN_FOREST = BOPFeatureUtils.createKey("tress_mediterranean_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MUSKEG = BOPFeatureUtils.createKey("trees_muskeg");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_OLD_GROWTH_DEAD_FOREST = BOPFeatureUtils.createKey("trees_old_growth_dead_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_MYSTIC_GROVE = BOPFeatureUtils.createKey("trees_mystic_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_OMINOUS_WOODS = BOPFeatureUtils.createKey("trees_ominous_woods");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_ORCHARD = BOPFeatureUtils.createKey("trees_orchard");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_ORIGIN_VALLEY = BOPFeatureUtils.createKey("trees_origin_valley");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_PRAIRIE = BOPFeatureUtils.createKey("trees_prairie");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_RAINFOREST = BOPFeatureUtils.createKey("trees_rainforest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_RAINFOREST_CLIFFS = BOPFeatureUtils.createKey("trees_rainforest_cliffs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_RAINFOREST_FLOODPLAIN = BOPFeatureUtils.createKey("trees_rainforest_floodplain");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_REDWOOD_FOREST = BOPFeatureUtils.createKey("trees_redwood_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SCRUBLAND = BOPFeatureUtils.createKey("trees_scrubland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SEASONAL_FOREST = BOPFeatureUtils.createKey("trees_seasonal_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SEASONAL_PUMPKIN_PATCH = BOPFeatureUtils.createKey("trees_seasonal_pumpkin_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SNOWBLOSSOM_GROVE = BOPFeatureUtils.createKey("trees_snowblossom_grove");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SNOWY_CONIFEROUS_FOREST = BOPFeatureUtils.createKey("trees_snowy_coniferous_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_SNOWY_MAPLE_FOREST = BOPFeatureUtils.createKey("trees_snowy_maple_forest");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_TROPICS = BOPFeatureUtils.createKey("trees_tropics");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_TUNDRA = BOPFeatureUtils.createKey("trees_tundra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_WASTELAND = BOPFeatureUtils.createKey("trees_wasteland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_WETLAND = BOPFeatureUtils.createKey("trees_wetland");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        final Holder<PlacedFeature> FANCY_OAK_CHECKED = placedFeatureGetter.getOrThrow(TreePlacements.FANCY_OAK_CHECKED);
        final Holder<PlacedFeature> JUNGLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(TreePlacements.JUNGLE_TREE_CHECKED);
        final Holder<PlacedFeature> OAK_CHECKED = placedFeatureGetter.getOrThrow(TreePlacements.OAK_CHECKED);
        final Holder<PlacedFeature> DARK_OAK_CHECKED = placedFeatureGetter.getOrThrow(TreePlacements.DARK_OAK_CHECKED);
        final Holder<PlacedFeature> ACACIA_BUSH_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ACACIA_BUSH_TREE_CHECKED);
        final Holder<PlacedFeature> ACACIA_TWIGLET_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ACACIA_TWIGLET_CHECKED);
        final Holder<PlacedFeature> ACACIA_TWIGLET_SMALL_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ACACIA_TWIGLET_SMALL_CHECKED);
        final Holder<PlacedFeature> ASPEN_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ASPEN_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_FLOWERING_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_FLOWERING_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_JACARANDA_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_JACARANDA_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_MAGIC_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_MAGIC_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_RED_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_RED_MAPLE_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_OAK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_OAK_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_ORANGE_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_ORANGE_MAPLE_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_ORIGIN_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_ORIGIN_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_RAINBOW_BIRCH_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_RAINBOW_BIRCH_TREE_CHECKED);
        final Holder<PlacedFeature> BIG_YELLOW_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_YELLOW_MAPLE_TREE_CHECKED);
        final Holder<PlacedFeature> BAYOU_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BAYOU_TREE_CHECKED);
        final Holder<PlacedFeature> BAYOU_TREE_MEDIUM_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BAYOU_TREE_MEDIUM_CHECKED);
        final Holder<PlacedFeature> DEAD_TREE_WASTELAND_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.DEAD_TREE_WASTELAND_CHECKED);
        final Holder<PlacedFeature> DEAD_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.DEAD_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> DEAD_TWIGLET_TREE_SMALL_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.DEAD_TWIGLET_TREE_SMALL_CHECKED);
        final Holder<PlacedFeature> DYING_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.DYING_TREE_CHECKED);
        final Holder<PlacedFeature> DYING_TREE_WASTELAND_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.DYING_TREE_WASTELAND_CHECKED);
        final Holder<PlacedFeature> FIR_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FIR_TREE_CHECKED);
        final Holder<PlacedFeature> FIR_TREE_LARGE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FIR_TREE_LARGE_CHECKED);
        final Holder<PlacedFeature> FIR_TREE_SMALL_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FIR_TREE_SMALL_CHECKED);
        final Holder<PlacedFeature> FLOWERING_OAK_BUSH_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FLOWERING_OAK_BUSH_CHECKED);
        final Holder<PlacedFeature> FLOWERING_OAK_TREE_BEES_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FLOWERING_OAK_TREE_BEES_CHECKED);
        final Holder<PlacedFeature> FLOWERING_OAK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.FLOWERING_OAK_TREE_CHECKED);
        final Holder<PlacedFeature> GIANT_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.GIANT_TREE_CHECKED);
        final Holder<PlacedFeature> JACARANDA_TREE_BEES_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.JACARANDA_TREE_BEES_CHECKED);
        final Holder<PlacedFeature> JACARANDA_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.JACARANDA_TREE_CHECKED);
        final Holder<PlacedFeature> JUNGLE_BUSH_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.JUNGLE_BUSH_CHECKED);
        final Holder<PlacedFeature> JUNGLE_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.JUNGLE_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> MAGIC_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.MAGIC_TREE_CHECKED);
        final Holder<PlacedFeature> MAHOGANY_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.MAHOGANY_TREE_CHECKED);
        final Holder<PlacedFeature> MANGROVE_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.MANGROVE_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> RED_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.RED_MAPLE_TREE_CHECKED);
        final Holder<PlacedFeature> MAPLE_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.MAPLE_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> CHERRY_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.CHERRY_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> SNOWBLOSSOM_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SNOWBLOSSOM_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> OAK_BUSH_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.OAK_BUSH_CHECKED);
        final Holder<PlacedFeature> ORANGE_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ORANGE_MAPLE_TREE_CHECKED);
        final Holder<PlacedFeature> ORIGIN_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.ORIGIN_TREE_CHECKED);
        final Holder<PlacedFeature> PALM_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.PALM_TREE_CHECKED);
        final Holder<PlacedFeature> PINE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.PINE_TREE_CHECKED);
        final Holder<PlacedFeature> RAINBOW_BIRCH_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.RAINBOW_BIRCH_TREE_CHECKED);
        final Holder<PlacedFeature> REDWOOD_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_CHECKED);
        final Holder<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_LARGE_CHECKED);
        final Holder<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.REDWOOD_TREE_MEDIUM_CHECKED);
        final Holder<PlacedFeature> SMALL_DEAD_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SMALL_DEAD_TREE_CHECKED);
        final Holder<PlacedFeature> SPARSE_ACACIA_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SPARSE_ACACIA_TREE_CHECKED);
        final Holder<PlacedFeature> SPARSE_OAK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SPARSE_OAK_TREE_CHECKED);
        final Holder<PlacedFeature> SPRUCE_BUSH_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SPRUCE_BUSH_CHECKED);
        final Holder<PlacedFeature> CYPRESS_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.CYPRESS_TREE_CHECKED);
        final Holder<PlacedFeature> SPRUCE_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.SPRUCE_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> TALL_DEAD_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TALL_DEAD_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> TALL_SPRUCE_TREE_BEES_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TALL_SPRUCE_TREE_BEES_CHECKED);
        final Holder<PlacedFeature> TALL_SPRUCE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TALL_SPRUCE_TREE_CHECKED);
        final Holder<PlacedFeature> TALL_TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TALL_TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> TALL_UMBRAN_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TALL_UMBRAN_TREE_CHECKED);
        final Holder<PlacedFeature> TWIGLET_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.TWIGLET_TREE_CHECKED);
        final Holder<PlacedFeature> UMBRAN_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.UMBRAN_TREE_CHECKED);
        final Holder<PlacedFeature> WILLOW_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.WILLOW_TREE_CHECKED);
        final Holder<PlacedFeature> YELLOW_MAPLE_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.YELLOW_MAPLE_TREE_CHECKED);

        register(context, BOPVegetationFeatures.COLD_DESERT_ROCKS, Feature.FOREST_ROCK, new BlockStateConfiguration(Blocks.COBBLESTONE.defaultBlockState()));
        register(context, BOPVegetationFeatures.HIGH_GRASS, BOPBaseFeatures.HIGH_GRASS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.BIG_DRIPLEAF, BOPBaseFeatures.BIG_DRIPLEAF, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.BIG_PUMPKIN, BOPBaseFeatures.BIG_PUMPKIN, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.BRAMBLE, BOPBaseFeatures.BRAMBLE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.FALLEN_LOG, BOPBaseFeatures.FALLEN_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.FALLEN_FIR_LOG, BOPBaseFeatures.FALLEN_FIR_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.FALLEN_BIRCH_LOG, BOPBaseFeatures.FALLEN_BIRCH_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.FALLEN_JACARANDA_LOG, BOPBaseFeatures.FALLEN_JACARANDA_LOG, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.GOLDENROD, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.GOLDENROD))));
        register(context, BOPVegetationFeatures.HOT_SPRING_VENTS, BOPBaseFeatures.HOT_SPRING_VENTS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.HUGE_CLOVER, BOPBaseFeatures.HUGE_CLOVER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.HUGE_LILY_PAD, BOPBaseFeatures.HUGE_LILY_PAD, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.HUGE_TOADSTOOL, BOPBaseFeatures.HUGE_TOADSTOOL, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.DRIPSTONE_SPLATTER, BOPBaseFeatures.DRIPSTONE_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.MOSS_SPLATTER, BOPBaseFeatures.MOSS_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.MYCELIUM_SPLATTER, BOPBaseFeatures.MYCELIUM_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.PATCH_BARLEY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BARLEY))));
        register(context, BOPVegetationFeatures.PATCH_BLUE_HYDRANGEA, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLUE_HYDRANGEA))));
        register(context, BOPVegetationFeatures.PATCH_BUSH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BUSH))));
        register(context, BOPVegetationFeatures.PATCH_CATTAIL, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.CATTAIL))));
        register(context, BOPVegetationFeatures.PATCH_DEAD_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DEAD_GRASS))));
        register(context, BOPVegetationFeatures.PATCH_DESERT_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DESERT_GRASS))));
        register(context, BOPVegetationFeatures.PATCH_DUNE_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DUNE_GRASS))));
        register(context, BOPVegetationFeatures.PATCH_FERN, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))));
        register(context, BOPVegetationFeatures.PATCH_ICY_IRIS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ICY_IRIS))));
        register(context, BOPVegetationFeatures.PATCH_LILAC, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILAC))));
        register(context, BOPVegetationFeatures.PATCH_PEONY, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.PEONY))));
        register(context, BOPVegetationFeatures.PATCH_REED, Feature.RANDOM_PATCH, waterPatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.REED))));
        register(context, BOPVegetationFeatures.PATCH_SEA_OATS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SEA_OATS))));
        register(context, BOPVegetationFeatures.PATCH_SPROUTS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPROUT))));
        register(context, BOPVegetationFeatures.PATCH_TALL_LAVENDER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.TALL_LAVENDER))));
        register(context, BOPVegetationFeatures.PATCH_TALL_WHITE_LAVENDER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.TALL_WHITE_LAVENDER.defaultBlockState(), 3).add(BOPBlocks.WHITE_LAVENDER.defaultBlockState(), 1).build())), List.of(), 512));
        register(context, BOPVegetationFeatures.PATCH_TINY_CACTUS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.TINY_CACTUS))));
        register(context, BOPVegetationFeatures.PATCH_TUNDRA_SHRUBS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.TUNDRA_SHRUB))));
        register(context, BOPVegetationFeatures.PATCH_WATERGRASS, Feature.RANDOM_PATCH, waterPatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.WATERGRASS))));
        register(context, BOPVegetationFeatures.PATCH_WATERLILY_FLOWER, Feature.RANDOM_PATCH, new RandomPatchConfiguration(10, 7, 3, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.WATERLILY)))));
        register(context, BOPVegetationFeatures.PUMPKIN_PATCH, BOPBaseFeatures.PUMPKIN_PATCH, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.RED_MAPLE_LEAF_PILES, BOPBaseFeatures.RED_MAPLE_LEAF_PILE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.ORANGE_MAPLE_LEAF_PILES, BOPBaseFeatures.ORANGE_MAPLE_LEAF_PILE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.YELLOW_MAPLE_LEAF_PILES, BOPBaseFeatures.YELLOW_MAPLE_LEAF_PILE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SPARSE_DUNE_GRASS, BOPBaseFeatures.SPARSE_DUNE_GRASS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.RAINFOREST_CLIFFS_VINES, BOPBaseFeatures.RAINFOREST_CLIFFS_VINES, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.ROOTED_STUMP, BOPBaseFeatures.ROOTED_STUMP, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.ROSE_BUSH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH))));
        register(context, BOPVegetationFeatures.SCATTERED_ROCKS, BOPBaseFeatures.SCATTERED_ROCKS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SCRUB, BOPBaseFeatures.SCRUB, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SPROUT_BONEMEAL, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPROUT.defaultBlockState())));
        register(context, BOPVegetationFeatures.SUNFLOWER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.SUNFLOWER))));
        register(context, BOPVegetationFeatures.SHORT_BAMBOO, BOPBaseFeatures.SHORT_BAMBOO, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.TERMITE_MOUND, BOPBaseFeatures.TERMITE_MOUND, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.THIN_BAMBOO, BOPBaseFeatures.THIN_BAMBOO, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SMALL_DRIPLEAF, BOPBaseFeatures.SMALL_DRIPLEAF, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SMALL_BROWN_MUSHROOM, BOPBaseFeatures.SMALL_BROWN_MUSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SMALL_RED_MUSHROOM, BOPBaseFeatures.SMALL_RED_MUSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.SMALL_TOADSTOOL, BOPBaseFeatures.SMALL_TOADSTOOL, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPVegetationFeatures.TOADSTOOL_NORMAL, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.TOADSTOOL))));
        register(context, BOPVegetationFeatures.WASTELAND_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.DESERT_GRASS.defaultBlockState(), 1).add(BOPBlocks.DEAD_GRASS.defaultBlockState(), 2).build()))));

        SimpleWeightedRandomList.Builder<BlockState> builder = SimpleWeightedRandomList.builder();
        for(int i = 1; i <= 4; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder.add(BOPBlocks.CLOVER.defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }
        register(context, PATCH_CLOVER, Feature.RANDOM_PATCH, new RandomPatchConfiguration(96, 2, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder)))));

        SimpleWeightedRandomList.Builder<BlockState> builder2 = SimpleWeightedRandomList.builder();
        for(int i = 1; i <= 4; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder2.add(BOPBlocks.WHITE_PETALS.defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }
        register(context, FLOWER_SNOWBLOSSOM_GROVE, Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder2)))));

        register(context, BOPVegetationFeatures.FLOWER_CONIFEROUS_FOREST, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.CORNFLOWER.defaultBlockState(), 1).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_FIELD, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.WHITE_LAVENDER.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_OVERGROWN_GREENS, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.PINK_DAFFODIL.defaultBlockState(), 2).add(Blocks.AZURE_BLUET.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_JACARANDA_GLADE, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 3).add(Blocks.BLUE_ORCHID.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_LAVENDER, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.LAVENDER))));
        register(context, BOPVegetationFeatures.FLOWER_MOOR, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.ALLIUM.defaultBlockState(), 1).add(BOPBlocks.VIOLET.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_MYSTIC_GROVE, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.GLOWFLOWER.defaultBlockState(), 1).add(BOPBlocks.PINK_DAFFODIL.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.ALLIUM.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_OMINOUS_WOODS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.WITHER_ROSE))));
        register(context, BOPVegetationFeatures.FLOWER_ORIGIN_VALLEY, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.ROSE.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_POPPY, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.POPPY))));
        register(context, BOPVegetationFeatures.FLOWER_RAINFOREST, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.ORANGE_COSMOS.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_SHRUBLAND, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ALLIUM))));
        register(context, BOPVegetationFeatures.FLOWER_SNOWY, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.VIOLET.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_TROPICS, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.PINK_HIBISCUS.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));
        register(context, BOPVegetationFeatures.FLOWER_VIOLET, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.VIOLET))));
        register(context, BOPVegetationFeatures.FLOWER_WASTELAND, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.WILTED_LILY))));
        register(context, BOPVegetationFeatures.FLOWER_WETLAND, Feature.FLOWER, grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.BLUE_ORCHID.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64));

        SimpleWeightedRandomList.Builder<BlockState> builder3 = SimpleWeightedRandomList.builder();
        for(int i = 1; i <= 4; ++i) {
            for(Direction direction : Direction.Plane.HORIZONTAL) {
                builder3.add(BOPBlocks.WILDFLOWER.defaultBlockState().setValue(PinkPetalsBlock.AMOUNT, Integer.valueOf(i)).setValue(PinkPetalsBlock.FACING, direction), 1);
            }
        }
        register(context, BOPVegetationFeatures.FLOWER_WILDFLOWER, Feature.FLOWER, new RandomPatchConfiguration(96, 6, 2, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(builder3)))));

        register(context, BOPVegetationFeatures.TREES_ASPEN_GLADE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_YELLOW_MAPLE_TREE_CHECKED, 0.05F)), ASPEN_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_AURORAL_GARDEN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(OAK_BUSH_CHECKED, 0.1F), new WeightedPlacedFeature(FIR_TREE_SMALL_CHECKED, 0.1F), new WeightedPlacedFeature(FIR_TREE_CHECKED, 0.025F), new WeightedPlacedFeature(BIG_RAINBOW_BIRCH_TREE_CHECKED, 0.4F)), RAINBOW_BIRCH_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_BAYOU, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BAYOU_TREE_MEDIUM_CHECKED, 0.3F)), BAYOU_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_BOG, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(SPRUCE_TWIGLET_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(MAPLE_TWIGLET_TREE_CHECKED, 0.6F)), MANGROVE_TWIGLET_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_CONIFEROUS_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(FIR_TREE_CHECKED, 0.33333334F)), FIR_TREE_LARGE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_DEAD_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(DYING_TREE_CHECKED, 0.2F), new WeightedPlacedFeature(TALL_SPRUCE_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(SMALL_DEAD_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(DEAD_TWIGLET_TREE_CHECKED, 0.2F)), OAK_CHECKED));
        register(context, BOPVegetationFeatures.TREES_DENSE_WOODLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_OAK_TREE_CHECKED, 0.5F)), OAK_CHECKED));
        register(context, BOPVegetationFeatures.TREES_DRYLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(TWIGLET_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(SPARSE_OAK_TREE_CHECKED, 0.2F), new WeightedPlacedFeature(PINE_TREE_CHECKED, 0.125F)), ACACIA_TWIGLET_SMALL_CHECKED));
        register(context, BOPVegetationFeatures.TREES_FIELD, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(TALL_SPRUCE_TREE_BEES_CHECKED, 0.1F)), OAK_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_FIELD_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(OAK_BUSH_CHECKED, 0.25F)), TALL_SPRUCE_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_FUNGAL_JUNGLE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(OAK_CHECKED, 0.1F), new WeightedPlacedFeature(BIG_OAK_TREE_CHECKED, 0.05F)), JUNGLE_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_HOT_SPRINGS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(PINE_TREE_CHECKED, 0.05F)), PINE_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_JACARANDA_GLADE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_FLOWERING_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(SNOWBLOSSOM_TWIGLET_TREE_CHECKED, 0.2F), new WeightedPlacedFeature(CHERRY_TWIGLET_TREE_CHECKED, 0.075F)), BIG_JACARANDA_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_JADE_CLIFFS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(PINE_TREE_CHECKED, 0.075F)), SPRUCE_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_LAVENDER_FIELD, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(JACARANDA_TREE_BEES_CHECKED, 0.3F), new WeightedPlacedFeature(BIG_FLOWERING_TREE_CHECKED, 0.15F), new WeightedPlacedFeature(BIG_JACARANDA_TREE_CHECKED, 0.1F)), FLOWERING_OAK_TREE_BEES_CHECKED));
        register(context, BOPVegetationFeatures.TREES_LUSH_DESERT, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(PALM_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(ACACIA_BUSH_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(SPARSE_ACACIA_TREE_CHECKED, 0.2F)), ACACIA_TWIGLET_CHECKED));
        register(context, BOPVegetationFeatures.TREES_MAPLE_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(TALL_SPRUCE_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(BIG_RED_MAPLE_TREE_CHECKED, 0.3F)), RED_MAPLE_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_MEDITERRANEAN_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(SPARSE_OAK_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(DARK_OAK_CHECKED, 0.05F), new WeightedPlacedFeature(CYPRESS_TREE_CHECKED, 0.6F), new WeightedPlacedFeature(FLOWERING_OAK_BUSH_CHECKED, 0.1F)), OAK_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_MUSKEG, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(TALL_DEAD_TWIGLET_TREE_CHECKED, 0.1F)), DEAD_TWIGLET_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_OLD_GROWTH_DEAD_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(DYING_TREE_CHECKED, 0.2F), new WeightedPlacedFeature(DEAD_TWIGLET_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(TALL_SPRUCE_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(SMALL_DEAD_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(OAK_CHECKED, 0.05F)), TALL_DEAD_TWIGLET_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_MYSTIC_GROVE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_FLOWERING_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(BIG_MAGIC_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(GIANT_TREE_CHECKED, 0.025F)), MAGIC_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_OMINOUS_WOODS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(DEAD_TWIGLET_TREE_CHECKED, 0.05F), new WeightedPlacedFeature(DYING_TREE_CHECKED, 0.15F), new WeightedPlacedFeature(TALL_UMBRAN_TREE_CHECKED, 0.7F)), UMBRAN_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_ORCHARD, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_FLOWERING_TREE_CHECKED, 0.1F)), FLOWERING_OAK_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_ORIGIN_VALLEY, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_ORIGIN_TREE_CHECKED, 0.1F)), ORIGIN_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_PRAIRIE, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_OAK_TREE_CHECKED, 0.1F)), SPARSE_OAK_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_RAINFOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(JUNGLE_TREE_CHECKED, 0.1F)), MAHOGANY_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_RAINFOREST_CLIFFS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(JUNGLE_TWIGLET_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(MAHOGANY_TREE_CHECKED, 0.05F)), OAK_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_RAINFOREST_FLOODPLAIN, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(SPARSE_OAK_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(BIG_OAK_TREE_CHECKED, 0.1F)), OAK_BUSH_CHECKED));
        register(context, BOPVegetationFeatures.TREES_REDWOOD_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(REDWOOD_TREE_CHECKED, 0.3f), new WeightedPlacedFeature(REDWOOD_TREE_LARGE_CHECKED, 0.5f)), REDWOOD_TREE_MEDIUM_CHECKED));
        register(context, BOPVegetationFeatures.TREES_SCRUBLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(SPRUCE_TWIGLET_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(TALL_TWIGLET_TREE_CHECKED, 0.1F)), ACACIA_TWIGLET_CHECKED));
        register(context, BOPVegetationFeatures.TREES_SEASONAL_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(OAK_CHECKED, 0.05F), new WeightedPlacedFeature(FANCY_OAK_CHECKED, 0.025F), new WeightedPlacedFeature(RED_MAPLE_TREE_CHECKED, 0.2F), new WeightedPlacedFeature(YELLOW_MAPLE_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(BIG_RED_MAPLE_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(BIG_YELLOW_MAPLE_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(BIG_ORANGE_MAPLE_TREE_CHECKED, 0.1F)), ORANGE_MAPLE_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_SEASONAL_PUMPKIN_PATCH, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(OAK_BUSH_CHECKED, 0.2F), new WeightedPlacedFeature(ORANGE_MAPLE_TREE_CHECKED, 0.1F), new WeightedPlacedFeature(BIG_ORANGE_MAPLE_TREE_CHECKED, 0.05F)), TWIGLET_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_SNOWBLOSSOM_GROVE, Feature.TREE, snowblossom().build());
        register(context, BOPVegetationFeatures.TREES_SNOWY_CONIFEROUS_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(FIR_TREE_CHECKED, 0.4F)), FIR_TREE_LARGE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_SNOWY_MAPLE_FOREST, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(TALL_SPRUCE_TREE_CHECKED, 0.4F), new WeightedPlacedFeature(BIG_RED_MAPLE_TREE_CHECKED, 0.2F)), RED_MAPLE_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_TROPICS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(JUNGLE_TWIGLET_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(FLOWERING_OAK_TREE_CHECKED, 0.1F)), PALM_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_TUNDRA, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(DEAD_TWIGLET_TREE_SMALL_CHECKED, 0.1F)), MAPLE_TWIGLET_TREE_CHECKED));
        register(context, BOPVegetationFeatures.TREES_WASTELAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(DEAD_TREE_WASTELAND_CHECKED, 0.2F)), DYING_TREE_WASTELAND_CHECKED));
        register(context, BOPVegetationFeatures.TREES_WETLAND, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(WILLOW_TREE_CHECKED, 0.25F)), TALL_SPRUCE_TREE_CHECKED));
    }

    private static TreeConfiguration.TreeConfigurationBuilder snowblossom() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.CHERRY_LOG), new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)), BlockStateProvider.simple(BOPBlocks.SNOWBLOSSOM_LEAVES), new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    }
    
    private static RandomPatchConfiguration grassPatch(BlockStateProvider stateProvider, int tries)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.onlyWhenEmpty(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(stateProvider)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration waterPatchConfiguration(F feature, FC configuration, int tries)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, PlacementUtils.filtered(feature, configuration, BlockPredicate.matchesBlocks(BlockPos.ZERO, Blocks.WATER)));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> RandomPatchConfiguration waterPatchConfiguration(F feature, FC configuration)
    {
        return waterPatchConfiguration(feature, configuration, 96);
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
