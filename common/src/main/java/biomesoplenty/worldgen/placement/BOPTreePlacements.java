/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.worldgen.feature.BOPTreeFeatures;
import biomesoplenty.util.worldgen.BOPPlacementUtils;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

import java.util.List;

public class BOPTreePlacements
{
    public static final ResourceKey<PlacedFeature> ACACIA_BUSH_TREE_CHECKED = BOPPlacementUtils.createKey("acacia_bush_tree");
    public static final ResourceKey<PlacedFeature> ACACIA_TWIGLET_CHECKED = BOPPlacementUtils.createKey("acacia_twiglet");
    public static final ResourceKey<PlacedFeature> ACACIA_TWIGLET_SMALL_CHECKED = BOPPlacementUtils.createKey("acacia_twiglet_small");
    public static final ResourceKey<PlacedFeature> ASPEN_TREE_CHECKED = BOPPlacementUtils.createKey("aspen_tree");
    public static final ResourceKey<PlacedFeature> BIG_FLOWERING_TREE_CHECKED = BOPPlacementUtils.createKey("big_flowering_tree");
    public static final ResourceKey<PlacedFeature> BIG_HELLBARK_TREE_CHECKED = BOPPlacementUtils.createKey("big_hellbark_tree");
    public static final ResourceKey<PlacedFeature> BIG_JACARANDA_TREE_CHECKED = BOPPlacementUtils.createKey("big_jacaranda_tree");
    public static final ResourceKey<PlacedFeature> BIG_MAGIC_TREE_CHECKED = BOPPlacementUtils.createKey("big_magic_tree");
    public static final ResourceKey<PlacedFeature> BIG_RED_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("big_red_maple_tree");
    public static final ResourceKey<PlacedFeature> BIG_OAK_TREE_CHECKED = BOPPlacementUtils.createKey("big_oak_tree");
    public static final ResourceKey<PlacedFeature> BIG_ORANGE_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("big_orange_maple_tree");
    public static final ResourceKey<PlacedFeature> BIG_ORIGIN_TREE_CHECKED = BOPPlacementUtils.createKey("big_origin_tree");
    public static final ResourceKey<PlacedFeature> BIG_RAINBOW_BIRCH_TREE_CHECKED = BOPPlacementUtils.createKey("big_rainbow_birch_tree");
    public static final ResourceKey<PlacedFeature> BIG_YELLOW_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("big_yellow_maple_tree");
    public static final ResourceKey<PlacedFeature> BAYOU_TREE_CHECKED = BOPPlacementUtils.createKey("bayou_tree");
    public static final ResourceKey<PlacedFeature> BAYOU_TREE_MEDIUM_CHECKED = BOPPlacementUtils.createKey("bayou_tree_medium");
    public static final ResourceKey<PlacedFeature> DEAD_TREE_WASTELAND_CHECKED = BOPPlacementUtils.createKey("dead_tree_wasteland");
    public static final ResourceKey<PlacedFeature> DEAD_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("dead_twiglet_tree");
    public static final ResourceKey<PlacedFeature> DEAD_TWIGLET_TREE_SMALL_CHECKED = BOPPlacementUtils.createKey("dead_twiglet_tree_small");
    public static final ResourceKey<PlacedFeature> DYING_TREE_CHECKED = BOPPlacementUtils.createKey("dying_tree");
    public static final ResourceKey<PlacedFeature> DYING_TREE_WASTELAND_CHECKED = BOPPlacementUtils.createKey("dying_tree_wasteland");
    public static final ResourceKey<PlacedFeature> EMPYREAL_TREE_CHECKED = BOPPlacementUtils.createKey("empyreal_tree");
    public static final ResourceKey<PlacedFeature> FIR_TREE_CHECKED = BOPPlacementUtils.createKey("fir_tree");
    public static final ResourceKey<PlacedFeature> FIR_TREE_LARGE_CHECKED = BOPPlacementUtils.createKey("fir_tree_large");
    public static final ResourceKey<PlacedFeature> FIR_TREE_SMALL_CHECKED = BOPPlacementUtils.createKey("fir_tree_small");
    public static final ResourceKey<PlacedFeature> FLOWERING_OAK_BUSH_CHECKED = BOPPlacementUtils.createKey("flowering_oak_bush");
    public static final ResourceKey<PlacedFeature> FLOWERING_OAK_TREE_BEES_CHECKED = BOPPlacementUtils.createKey("flowering_oak_tree_bees");
    public static final ResourceKey<PlacedFeature> FLOWERING_OAK_TREE_CHECKED = BOPPlacementUtils.createKey("flowering_oak_tree");
    public static final ResourceKey<PlacedFeature> GIANT_TREE_CHECKED = BOPPlacementUtils.createKey("giant_tree");
    public static final ResourceKey<PlacedFeature> HELLBARK_TREE_CHECKED = BOPPlacementUtils.createKey("hellbark_tree");
    public static final ResourceKey<PlacedFeature> JACARANDA_TREE_BEES_CHECKED = BOPPlacementUtils.createKey("jacaranda_tree_bees");
    public static final ResourceKey<PlacedFeature> JACARANDA_TREE_CHECKED = BOPPlacementUtils.createKey("jacaranda_tree");
    public static final ResourceKey<PlacedFeature> JUNGLE_BUSH_CHECKED = BOPPlacementUtils.createKey("jungle_bush");
    public static final ResourceKey<PlacedFeature> JUNGLE_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("jungle_twiglet_tree");
    public static final ResourceKey<PlacedFeature> MAGIC_TREE_CHECKED = BOPPlacementUtils.createKey("magic_tree");
    public static final ResourceKey<PlacedFeature> MAHOGANY_TREE_CHECKED = BOPPlacementUtils.createKey("mahogany_tree");
    public static final ResourceKey<PlacedFeature> MANGROVE_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("mangrove_twiglet_tree");
    public static final ResourceKey<PlacedFeature> RED_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("red_maple_tree_checked");
    public static final ResourceKey<PlacedFeature> MAPLE_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("maple_twiglet_tree");
    public static final ResourceKey<PlacedFeature> NULL_TREE_CHECKED = BOPPlacementUtils.createKey("null_tree");
    public static final ResourceKey<PlacedFeature> CHERRY_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("cherry_twiglet_tree");
    public static final ResourceKey<PlacedFeature> SNOWBLOSSOM_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("snowblossom_twiglet_tree");
    public static final ResourceKey<PlacedFeature> OAK_BUSH_CHECKED = BOPPlacementUtils.createKey("oak_bush");
    public static final ResourceKey<PlacedFeature> ORANGE_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("orange_maple_tree");
    public static final ResourceKey<PlacedFeature> ORIGIN_TREE_CHECKED = BOPPlacementUtils.createKey("origin_tree");
    public static final ResourceKey<PlacedFeature> PALM_TREE_CHECKED = BOPPlacementUtils.createKey("palm_tree");
    public static final ResourceKey<PlacedFeature> PINE_TREE_CHECKED = BOPPlacementUtils.createKey("pine_tree");
    public static final ResourceKey<PlacedFeature> RAINBOW_BIRCH_TREE_CHECKED = BOPPlacementUtils.createKey("rainbow_birch_tree");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_CHECKED = BOPPlacementUtils.createKey("redwood_tree");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = BOPPlacementUtils.createKey("redwood_tree_large");
    public static final ResourceKey<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = BOPPlacementUtils.createKey("redwood_tree_medium");
    public static final ResourceKey<PlacedFeature> SMALL_DEAD_TREE_CHECKED = BOPPlacementUtils.createKey("small_dead_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_ACACIA_TREE_CHECKED = BOPPlacementUtils.createKey("sparse_acacia_tree");
    public static final ResourceKey<PlacedFeature> SPARSE_OAK_TREE_CHECKED = BOPPlacementUtils.createKey("sparse_oak_tree");
    public static final ResourceKey<PlacedFeature> SPRUCE_BUSH_CHECKED = BOPPlacementUtils.createKey("spruce_bush");
    public static final ResourceKey<PlacedFeature> CYPRESS_TREE_CHECKED = BOPPlacementUtils.createKey("cypress_tree");
    public static final ResourceKey<PlacedFeature> SPRUCE_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("spruce_twiglet_tree");
    public static final ResourceKey<PlacedFeature> TALL_DEAD_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("tall_dead_twiglet_tree");
    public static final ResourceKey<PlacedFeature> TALL_SPRUCE_TREE_BEES_CHECKED = BOPPlacementUtils.createKey("tall_spruce_tree_bees");
    public static final ResourceKey<PlacedFeature> TALL_SPRUCE_TREE_CHECKED = BOPPlacementUtils.createKey("tall_spruce_tree");
    public static final ResourceKey<PlacedFeature> TALL_TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("tall_twiglet_tree");
    public static final ResourceKey<PlacedFeature> TALL_UMBRAN_TREE_CHECKED = BOPPlacementUtils.createKey("tall_umbran_tree");
    public static final ResourceKey<PlacedFeature> TWIGLET_TREE_CHECKED = BOPPlacementUtils.createKey("twiglet_tree");
    public static final ResourceKey<PlacedFeature> UMBRAN_TREE_CHECKED = BOPPlacementUtils.createKey("umbran_tree");
    public static final ResourceKey<PlacedFeature> WILLOW_TREE_CHECKED = BOPPlacementUtils.createKey("willow_tree");
    public static final ResourceKey<PlacedFeature> YELLOW_MAPLE_TREE_CHECKED = BOPPlacementUtils.createKey("yellow_maple_tree");

    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        final Holder<ConfiguredFeature<?, ?>> ACACIA_BUSH_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ACACIA_BUSH_TREE);
        final Holder<ConfiguredFeature<?, ?>> ACACIA_TWIGLET = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ACACIA_TWIGLET);
        final Holder<ConfiguredFeature<?, ?>> ACACIA_TWIGLET_SMALL = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ACACIA_TWIGLET_SMALL);
        final Holder<ConfiguredFeature<?, ?>> ASPEN_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ASPEN_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_FLOWERING_OAK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_FLOWERING_OAK_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_HELLBARK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_HELLBARK_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_JACARANDA_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_JACARANDA_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_MAGIC_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_MAGIC_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_RED_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_RED_MAPLE_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_OAK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_OAK_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_ORANGE_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_ORANGE_MAPLE_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_ORIGIN_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_ORIGIN_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_RAINBOW_BIRCH_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE);
        final Holder<ConfiguredFeature<?, ?>> BIG_YELLOW_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BIG_YELLOW_MAPLE_TREE);
        final Holder<ConfiguredFeature<?, ?>> BAYOU_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BAYOU_TREE);
        final Holder<ConfiguredFeature<?, ?>> BAYOU_TREE_MEDIUM = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.BAYOU_TREE_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> DEAD_TREE_WASTELAND = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.DEAD_TREE_WASTELAND);
        final Holder<ConfiguredFeature<?, ?>> DEAD_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.DEAD_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> DEAD_TWIGLET_TREE_SMALL = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.DEAD_TWIGLET_TREE_SMALL);
        final Holder<ConfiguredFeature<?, ?>> DYING_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.DYING_TREE);
        final Holder<ConfiguredFeature<?, ?>> DYING_TREE_WASTELAND = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.DYING_TREE_WASTELAND);
        final Holder<ConfiguredFeature<?, ?>> EMPYREAL_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.EMPYREAL_TREE);
        final Holder<ConfiguredFeature<?, ?>> FIR_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FIR_TREE);
        final Holder<ConfiguredFeature<?, ?>> FIR_TREE_LARGE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FIR_TREE_LARGE);
        final Holder<ConfiguredFeature<?, ?>> FIR_TREE_SMALL = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FIR_TREE_SMALL);
        final Holder<ConfiguredFeature<?, ?>> FLOWERING_OAK_BUSH = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FLOWERING_OAK_BUSH);
        final Holder<ConfiguredFeature<?, ?>> FLOWERING_OAK_TREE_BEES = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FLOWERING_OAK_TREE_BEES);
        final Holder<ConfiguredFeature<?, ?>> FLOWERING_OAK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.FLOWERING_OAK_TREE);
        final Holder<ConfiguredFeature<?, ?>> GIANT_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.GIANT_TREE);
        final Holder<ConfiguredFeature<?, ?>> HELLBARK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.HELLBARK_TREE);
        final Holder<ConfiguredFeature<?, ?>> JACARANDA_TREE_BEES = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.JACARANDA_TREE_BEES);
        final Holder<ConfiguredFeature<?, ?>> JACARANDA_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.JACARANDA_TREE);
        final Holder<ConfiguredFeature<?, ?>> JUNGLE_BUSH = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.JUNGLE_BUSH);
        final Holder<ConfiguredFeature<?, ?>> JUNGLE_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.JUNGLE_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> MAGIC_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.MAGIC_TREE);
        final Holder<ConfiguredFeature<?, ?>> MAHOGANY_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.MAHOGANY_TREE);
        final Holder<ConfiguredFeature<?, ?>> MANGROVE_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.MANGROVE_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> MAPLE_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.MAPLE_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> NULL_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.NULL_TREE);
        final Holder<ConfiguredFeature<?, ?>> CHERRY_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.CHERRY_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> SNOWBLOSSOM_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SNOWBLOSSOM_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> OAK_BUSH = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.OAK_BUSH);
        final Holder<ConfiguredFeature<?, ?>> ORANGE_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ORANGE_MAPLE_TREE);
        final Holder<ConfiguredFeature<?, ?>> ORIGIN_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.ORIGIN_TREE);
        final Holder<ConfiguredFeature<?, ?>> PALM_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.PALM_TREE);
        final Holder<ConfiguredFeature<?, ?>> PINE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.PINE_TREE);
        final Holder<ConfiguredFeature<?, ?>> RAINBOW_BIRCH_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.RAINBOW_BIRCH_TREE);
        final Holder<ConfiguredFeature<?, ?>> RED_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.RED_MAPLE_TREE);
        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE);
        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE_LARGE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE_LARGE);
        final Holder<ConfiguredFeature<?, ?>> REDWOOD_TREE_MEDIUM = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.REDWOOD_TREE_MEDIUM);
        final Holder<ConfiguredFeature<?, ?>> SMALL_DEAD_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SMALL_DEAD_TREE);
        final Holder<ConfiguredFeature<?, ?>> SPARSE_ACACIA_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SPARSE_ACACIA_TREE);
        final Holder<ConfiguredFeature<?, ?>> SPARSE_OAK_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SPARSE_OAK_TREE);
        final Holder<ConfiguredFeature<?, ?>> SPRUCE_BUSH = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SPRUCE_BUSH);
        final Holder<ConfiguredFeature<?, ?>> CYPRESS_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.CYPRESS_TREE);
        final Holder<ConfiguredFeature<?, ?>> SPRUCE_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.SPRUCE_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> TALL_DEAD_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TALL_DEAD_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> TALL_SPRUCE_TREE_BEES = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TALL_SPRUCE_TREE_BEES);
        final Holder<ConfiguredFeature<?, ?>> TALL_SPRUCE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TALL_SPRUCE_TREE);
        final Holder<ConfiguredFeature<?, ?>> TALL_TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TALL_TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> TALL_UMBRAN_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TALL_UMBRAN_TREE);
        final Holder<ConfiguredFeature<?, ?>> TWIGLET_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.TWIGLET_TREE);
        final Holder<ConfiguredFeature<?, ?>> UMBRAN_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.UMBRAN_TREE);
        final Holder<ConfiguredFeature<?, ?>> WILLOW_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.WILLOW_TREE);
        final Holder<ConfiguredFeature<?, ?>> YELLOW_MAPLE_TREE = configuredFeatureGetter.getOrThrow(BOPTreeFeatures.YELLOW_MAPLE_TREE);

        register(context, BOPTreePlacements.ACACIA_BUSH_TREE_CHECKED, ACACIA_BUSH_TREE, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND))));
        register(context, BOPTreePlacements.ACACIA_TWIGLET_CHECKED, ACACIA_TWIGLET, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND)))));
        register(context, BOPTreePlacements.ACACIA_TWIGLET_SMALL_CHECKED, ACACIA_TWIGLET_SMALL, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.SAND, BOPBlocks.ORANGE_SAND)))));
        register(context, BOPTreePlacements.ASPEN_TREE_CHECKED, ASPEN_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.BIG_FLOWERING_TREE_CHECKED, BIG_FLOWERING_OAK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING)));
        register(context, BOPTreePlacements.BIG_HELLBARK_TREE_CHECKED, BIG_HELLBARK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING)));
        register(context, BOPTreePlacements.BIG_JACARANDA_TREE_CHECKED, BIG_JACARANDA_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING)));
        register(context, BOPTreePlacements.BIG_MAGIC_TREE_CHECKED, BIG_MAGIC_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING)));
        register(context, BOPTreePlacements.BIG_RED_MAPLE_TREE_CHECKED, BIG_RED_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RED_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.BIG_OAK_TREE_CHECKED, BIG_OAK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.BIG_ORANGE_MAPLE_TREE_CHECKED, BIG_ORANGE_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.BIG_ORIGIN_TREE_CHECKED, BIG_ORIGIN_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.BIG_RAINBOW_BIRCH_TREE_CHECKED, BIG_RAINBOW_BIRCH_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING)));
        register(context, BOPTreePlacements.BIG_YELLOW_MAPLE_TREE_CHECKED, BIG_YELLOW_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.BAYOU_TREE_CHECKED, BAYOU_TREE);
        register(context, BOPTreePlacements.BAYOU_TREE_MEDIUM_CHECKED, BAYOU_TREE_MEDIUM);
        register(context, BOPTreePlacements.DEAD_TREE_WASTELAND_CHECKED, DEAD_TREE_WASTELAND, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.DEAD_TWIGLET_TREE_CHECKED, DEAD_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.DEAD_TWIGLET_TREE_SMALL_CHECKED, DEAD_TWIGLET_TREE_SMALL, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.DYING_TREE_CHECKED, DYING_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.DYING_TREE_WASTELAND_CHECKED, DYING_TREE_WASTELAND, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.EMPYREAL_TREE_CHECKED, EMPYREAL_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.EMPYREAL_SAPLING)));
        register(context, BOPTreePlacements.FIR_TREE_CHECKED, FIR_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING)));
        register(context, BOPTreePlacements.FIR_TREE_LARGE_CHECKED, FIR_TREE_LARGE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING)));
        register(context, BOPTreePlacements.FIR_TREE_SMALL_CHECKED, FIR_TREE_SMALL, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING)));
        register(context, BOPTreePlacements.FLOWERING_OAK_BUSH_CHECKED, FLOWERING_OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING)));
        register(context, BOPTreePlacements.FLOWERING_OAK_TREE_BEES_CHECKED, FLOWERING_OAK_TREE_BEES, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING)));
        register(context, BOPTreePlacements.FLOWERING_OAK_TREE_CHECKED, FLOWERING_OAK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING)));
        register(context, BOPTreePlacements.GIANT_TREE_CHECKED, GIANT_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
        register(context, BOPTreePlacements.HELLBARK_TREE_CHECKED, HELLBARK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING)));
        register(context, BOPTreePlacements.JACARANDA_TREE_BEES_CHECKED, JACARANDA_TREE_BEES, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING)));
        register(context, BOPTreePlacements.JACARANDA_TREE_CHECKED, JACARANDA_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING)));
        register(context, BOPTreePlacements.JUNGLE_BUSH_CHECKED, JUNGLE_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING)));
        register(context, BOPTreePlacements.JUNGLE_TWIGLET_TREE_CHECKED, JUNGLE_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING)));
        register(context, BOPTreePlacements.MAGIC_TREE_CHECKED, MAGIC_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING)));
        register(context, BOPTreePlacements.MAHOGANY_TREE_CHECKED, MAHOGANY_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAHOGANY_SAPLING)));
        register(context, BOPTreePlacements.MANGROVE_TWIGLET_TREE_CHECKED, MANGROVE_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.RED_MAPLE_TREE_CHECKED, RED_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RED_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.MAPLE_TWIGLET_TREE_CHECKED, MAPLE_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RED_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.NULL_TREE_CHECKED, NULL_TREE, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.UNMAPPED_END_STONE))));
        register(context, BOPTreePlacements.CHERRY_TWIGLET_TREE_CHECKED, CHERRY_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.CHERRY_SAPLING)));
        register(context, BOPTreePlacements.SNOWBLOSSOM_TWIGLET_TREE_CHECKED, SNOWBLOSSOM_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.SNOWBLOSSOM_SAPLING)));
        register(context, BOPTreePlacements.OAK_BUSH_CHECKED, OAK_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.ORANGE_MAPLE_TREE_CHECKED, ORANGE_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_MAPLE_SAPLING)));
        register(context, BOPTreePlacements.ORIGIN_TREE_CHECKED, ORIGIN_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.PALM_TREE_CHECKED, PALM_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.PALM_SAPLING)));
        register(context, BOPTreePlacements.PINE_TREE_CHECKED, PINE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.PINE_SAPLING)));
        register(context, BOPTreePlacements.RAINBOW_BIRCH_TREE_CHECKED, RAINBOW_BIRCH_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING)));
        register(context, BOPTreePlacements.REDWOOD_TREE_CHECKED, REDWOOD_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
        register(context, BOPTreePlacements.REDWOOD_TREE_LARGE_CHECKED, REDWOOD_TREE_LARGE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
        register(context, BOPTreePlacements.REDWOOD_TREE_MEDIUM_CHECKED, REDWOOD_TREE_MEDIUM, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING)));
        register(context, BOPTreePlacements.SMALL_DEAD_TREE_CHECKED, SMALL_DEAD_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.SPARSE_ACACIA_TREE_CHECKED, SPARSE_ACACIA_TREE, List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND))));
        register(context, BOPTreePlacements.SPARSE_OAK_TREE_CHECKED, SPARSE_OAK_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.SPRUCE_BUSH_CHECKED, SPRUCE_BUSH, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        register(context, BOPTreePlacements.CYPRESS_TREE_CHECKED, CYPRESS_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.CYPRESS_SAPLING)));
        register(context, BOPTreePlacements.SPRUCE_TWIGLET_TREE_CHECKED, SPRUCE_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        register(context, BOPTreePlacements.TALL_DEAD_TWIGLET_TREE_CHECKED, TALL_DEAD_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING)));
        register(context, BOPTreePlacements.TALL_SPRUCE_TREE_BEES_CHECKED, TALL_SPRUCE_TREE_BEES, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        register(context, BOPTreePlacements.TALL_SPRUCE_TREE_CHECKED, TALL_SPRUCE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
        register(context, BOPTreePlacements.TALL_TWIGLET_TREE_CHECKED, TALL_TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.TALL_UMBRAN_TREE_CHECKED, TALL_UMBRAN_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING)));
        register(context, BOPTreePlacements.TWIGLET_TREE_CHECKED, TWIGLET_TREE, List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
        register(context, BOPTreePlacements.UMBRAN_TREE_CHECKED, UMBRAN_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING)));
        register(context, BOPTreePlacements.WILLOW_TREE_CHECKED, WILLOW_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.WILLOW_SAPLING)));
        register(context, BOPTreePlacements.YELLOW_MAPLE_TREE_CHECKED, YELLOW_MAPLE_TREE, List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_MAPLE_SAPLING)));
    }
    
    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, PlacementModifier... modifiers)
    {
        register(context, placedFeatureKey, configuredFeature, List.of(modifiers));
    }

    protected static void register(BootstapContext<PlacedFeature> context, ResourceKey<PlacedFeature> placedFeatureKey, Holder<ConfiguredFeature<?, ?>> configuredFeature, List<PlacementModifier> modifiers)
    {
        context.register(placedFeatureKey, new PlacedFeature(configuredFeature, modifiers));
    }
}
