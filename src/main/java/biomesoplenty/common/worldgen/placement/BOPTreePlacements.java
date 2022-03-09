/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;

public class BOPTreePlacements
{
    public static final Holder<PlacedFeature> ACACIA_BUSH_TREE_CHECKED = register("acacia_bush_tree", BOPTreeFeatures.ACACIA_BUSH_TREE, BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0))));
    public static final Holder<PlacedFeature> ACACIA_TWIGLET_CHECKED = register("acacia_twiglet", BOPTreeFeatures.ACACIA_TWIGLET, BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0)))));
    public static final Holder<PlacedFeature> ACACIA_TWIGLET_SMALL_CHECKED = register("acacia_twiglet_small", BOPTreeFeatures.ACACIA_TWIGLET_SMALL, BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlock(Blocks.SAND, new BlockPos(0, -1, 0)), BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0)))));
    public static final Holder<PlacedFeature> BIG_FLOWERING_TREE_CHECKED = register("big_flowering_tree", BOPTreeFeatures.BIG_FLOWERING_OAK_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final Holder<PlacedFeature> BIG_HELLBARK_TREE_CHECKED = register("big_hellbark_tree", BOPTreeFeatures.BIG_HELLBARK_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING));
    public static final Holder<PlacedFeature> BIG_JACARANDA_TREE_CHECKED = register("big_jacaranda_tree", BOPTreeFeatures.BIG_JACARANDA_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final Holder<PlacedFeature> BIG_MAGIC_TREE_CHECKED = register("big_magic_tree", BOPTreeFeatures.BIG_MAGIC_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING));
    public static final Holder<PlacedFeature> BIG_MAPLE_TREE_CHECKED = register("big_maple_tree", BOPTreeFeatures.BIG_MAPLE_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final Holder<PlacedFeature> BIG_OAK_TREE_CHECKED = register("big_oak_tree", BOPTreeFeatures.BIG_OAK_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> BIG_ORANGE_AUTUMN_TREE_CHECKED = register("big_orange_autumn_tree", BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final Holder<PlacedFeature> BIG_ORIGIN_TREE_CHECKED = register("big_origin_tree", BOPTreeFeatures.BIG_ORIGIN_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> BIG_PINK_CHERRY_TREE_CHECKED = register("big_pink_cherry_tree", BOPTreeFeatures.BIG_PINK_CHERRY_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final Holder<PlacedFeature> BIG_RAINBOW_BIRCH_TREE_CHECKED = register("big_rainbow_birch_tree", BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING));
    public static final Holder<PlacedFeature> BIG_WHITE_CHERRY_TREE_CHECKED = register("big_white_cherry_tree", BOPTreeFeatures.BIG_WHITE_CHERRY_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final Holder<PlacedFeature> BIG_YELLOW_AUTUMN_TREE_CHECKED = register("big_yellow_autumn_tree", BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));
    public static final Holder<PlacedFeature> CYPRESS_TREE_CHECKED = register("cypress_tree", BOPTreeFeatures.CYPRESS_TREE);
    public static final Holder<PlacedFeature> CYPRESS_TREE_MEDIUM_CHECKED = register("cypress_tree_medium", BOPTreeFeatures.CYPRESS_TREE_MEDIUM);
    public static final Holder<PlacedFeature> DARK_OAK_POPLAR_TREE_CHECKED = register("dark_oak_poplar_tree", BOPTreeFeatures.DARK_OAK_POPLAR_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));
    public static final Holder<PlacedFeature> DEAD_TREE_WASTELAND_CHECKED = register("dead_tree_wasteland", BOPTreeFeatures.DEAD_TREE_WASTELAND, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> DEAD_TWIGLET_TREE_CHECKED = register("dead_twiglet_tree", BOPTreeFeatures.DEAD_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> DEAD_TWIGLET_TREE_SMALL_CHECKED = register("dead_twiglet_tree_small", BOPTreeFeatures.DEAD_TWIGLET_TREE_SMALL, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> DYING_TREE_CHECKED = register("dying_tree", BOPTreeFeatures.DYING_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> DYING_TREE_WASTELAND_CHECKED = register("dying_tree_wasteland", BOPTreeFeatures.DYING_TREE_WASTELAND, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> FIR_TREE_CHECKED = register("fir_tree", BOPTreeFeatures.FIR_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final Holder<PlacedFeature> FIR_TREE_LARGE_CHECKED = register("fir_tree_large", BOPTreeFeatures.FIR_TREE_LARGE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final Holder<PlacedFeature> FIR_TREE_SMALL_CHECKED = register("fir_tree_small", BOPTreeFeatures.FIR_TREE_SMALL, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final Holder<PlacedFeature> FLOWERING_OAK_BUSH_CHECKED = register("flowering_oak_bush", BOPTreeFeatures.FLOWERING_OAK_BUSH, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final Holder<PlacedFeature> FLOWERING_OAK_TREE_BEES_CHECKED = register("flowering_oak_tree_bees", BOPTreeFeatures.FLOWERING_OAK_TREE_BEES, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final Holder<PlacedFeature> FLOWERING_OAK_TREE_CHECKED = register("flowering_oak_tree", BOPTreeFeatures.FLOWERING_OAK_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final Holder<PlacedFeature> GIANT_TREE_CHECKED = register("giant_tree", BOPTreeFeatures.GIANT_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));
    public static final Holder<PlacedFeature> HELLBARK_TREE_CHECKED = register("hellbark_tree", BOPTreeFeatures.HELLBARK_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING));
    public static final Holder<PlacedFeature> JACARANDA_TREE_BEES_CHECKED = register("jacaranda_tree_bees", BOPTreeFeatures.JACARANDA_TREE_BEES, PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final Holder<PlacedFeature> JACARANDA_TREE_CHECKED = register("jacaranda_tree", BOPTreeFeatures.JACARANDA_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final Holder<PlacedFeature> JUNGLE_BUSH_CHECKED = register("jungle_bush", BOPTreeFeatures.JUNGLE_BUSH, PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING));
    public static final Holder<PlacedFeature> JUNGLE_TWIGLET_TREE_CHECKED = register("jungle_twiglet_tree", BOPTreeFeatures.JUNGLE_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING));
    public static final Holder<PlacedFeature> MAGIC_TREE_CHECKED = register("magic_tree", BOPTreeFeatures.MAGIC_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING));
    public static final Holder<PlacedFeature> MAHOGANY_TREE_CHECKED = register("mahogany_tree", BOPTreeFeatures.MAHOGANY_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAHOGANY_SAPLING));
    public static final Holder<PlacedFeature> MAPLE_TREE_CHECKED = register("maple_tree_checked", BOPTreeFeatures.MAPLE_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final Holder<PlacedFeature> MAPLE_TWIGLET_TREE_CHECKED = register("maple_twiglet_tree", BOPTreeFeatures.MAPLE_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final Holder<PlacedFeature> OAK_BUSH_CHECKED = register("oak_bush", BOPTreeFeatures.OAK_BUSH, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> ORANGE_AUTUMN_TREE_CHECKED = register("orange_autumn_tree", BOPTreeFeatures.ORANGE_AUTUMN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final Holder<PlacedFeature> ORIGIN_TREE_CHECKED = register("origin_tree", BOPTreeFeatures.ORIGIN_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> PALM_TREE_CHECKED = register("palm_tree", BOPTreeFeatures.PALM_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.PALM_SAPLING));
    public static final Holder<PlacedFeature> PINK_CHERRY_TREE_BEES_CHECKED = register("pink_cherry_tree_bees", BOPTreeFeatures.PINK_CHERRY_TREE_BEES, PlacementUtils.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final Holder<PlacedFeature> RAINBOW_BIRCH_TREE_CHECKED = register("rainbow_birch_tree", BOPTreeFeatures.RAINBOW_BIRCH_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING));
    public static final Holder<PlacedFeature> REDWOOD_TREE_CHECKED = register("redwood_tree", BOPTreeFeatures.REDWOOD_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final Holder<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = register("redwood_tree_large", BOPTreeFeatures.REDWOOD_TREE_LARGE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final Holder<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = register("redwood_tree_medium", BOPTreeFeatures.REDWOOD_TREE_MEDIUM, PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final Holder<PlacedFeature> SMALL_DEAD_TREE_CHECKED = register("small_dead_tree", BOPTreeFeatures.SMALL_DEAD_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> SPARSE_ACACIA_TREE_CHECKED = register("sparse_acacia_tree", BOPTreeFeatures.SPARSE_ACACIA_TREE, BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0))));
    public static final Holder<PlacedFeature> SPARSE_OAK_TREE_CHECKED = register("sparse_oak_tree", BOPTreeFeatures.SPARSE_OAK_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> SPRUCE_BUSH_CHECKED = register("spruce_bush", BOPTreeFeatures.SPRUCE_BUSH, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final Holder<PlacedFeature> SPRUCE_POPLAR_TREE_CHECKED = register("spruce_poplar_tree", BOPTreeFeatures.SPRUCE_POPLAR_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final Holder<PlacedFeature> SPRUCE_TWIGLET_TREE_CHECKED = register("spruce_twiglet_tree", BOPTreeFeatures.SPRUCE_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final Holder<PlacedFeature> TALL_DEAD_TWIGLET_TREE_CHECKED = register("tall_dead_twiglet_tree", BOPTreeFeatures.TALL_DEAD_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final Holder<PlacedFeature> TALL_SPRUCE_TREE_BEES_CHECKED = register("tall_spruce_tree_bees", BOPTreeFeatures.TALL_SPRUCE_TREE_BEES, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final Holder<PlacedFeature> TALL_SPRUCE_TREE_CHECKED = register("tall_spruce_tree", BOPTreeFeatures.TALL_SPRUCE_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final Holder<PlacedFeature> TALL_TWIGLET_TREE_CHECKED = register("tall_twiglet_tree", BOPTreeFeatures.TALL_TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> TALL_UMBRAN_TREE_CHECKED = register("tall_umbran_tree", BOPTreeFeatures.TALL_UMBRAN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING));
    public static final Holder<PlacedFeature> TWIGLET_TREE_CHECKED = register("twiglet_tree", BOPTreeFeatures.TWIGLET_TREE, PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final Holder<PlacedFeature> UMBRAN_TREE_CHECKED = register("umbran_tree", BOPTreeFeatures.UMBRAN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING));
    public static final Holder<PlacedFeature> WHITE_CHERRY_TREE_BEES_CHECKED = register("white_cherry_tree_bees", BOPTreeFeatures.WHITE_CHERRY_TREE_BEES, PlacementUtils.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final Holder<PlacedFeature> WILLOW_TREE_CHECKED = register("willow_tree", BOPTreeFeatures.WILLOW_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.WILLOW_SAPLING));
    public static final Holder<PlacedFeature> YELLOW_AUTUMN_TREE_CHECKED = register("yellow_autumn_tree", BOPTreeFeatures.YELLOW_AUTUMN_TREE, PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));
}
