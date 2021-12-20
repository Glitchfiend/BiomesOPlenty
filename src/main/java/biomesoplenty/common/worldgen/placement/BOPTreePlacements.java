/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.PlacementModifier;

public class BOPTreePlacements
{
    public static final PlacedFeature ACACIA_BUSH_TREE_CHECKED = register("acacia_bush_tree", BOPTreeFeatures.ACACIA_BUSH_TREE.filtered(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0))));
    public static final PlacedFeature ACACIA_TWIGLET_CHECKED = register("acacia_twiglet", BOPTreeFeatures.ACACIA_TWIGLET.filtered(BlockPredicate.anyOf(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0)))));
    public static final PlacedFeature ACACIA_TWIGLET_SMALL_CHECKED = register("acacia_twiglet_small", BOPTreeFeatures.ACACIA_TWIGLET_SMALL.filtered(BlockPredicate.anyOf(BlockPredicate.matchesBlock(Blocks.SAND, new BlockPos(0, -1, 0)), BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0)))));
    public static final PlacedFeature BIG_FLOWERING_TREE_CHECKED = register("big_flowering_tree", BOPTreeFeatures.BIG_FLOWERING_OAK_TREE.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature BIG_JACARANDA_TREE_CHECKED = register("big_jacaranda_tree", BOPTreeFeatures.BIG_JACARANDA_TREE.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final PlacedFeature BIG_MAPLE_TREE_CHECKED = register("big_maple_tree", BOPTreeFeatures.BIG_MAPLE_TREE.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final PlacedFeature BIG_OAK_TREE_CHECKED = register("big_oak_tree", BOPTreeFeatures.BIG_OAK_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature BIG_ORANGE_AUTUMN_TREE_CHECKED = register("big_orange_autumn_tree", BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final PlacedFeature BIG_PINK_CHERRY_TREE_CHECKED = register("big_pink_cherry_tree", BOPTreeFeatures.BIG_PINK_CHERRY_TREE.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final PlacedFeature BIG_WHITE_CHERRY_TREE_CHECKED = register("big_white_cherry_tree", BOPTreeFeatures.BIG_WHITE_CHERRY_TREE.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final PlacedFeature BIG_YELLOW_AUTUMN_TREE_CHECKED = register("big_yellow_autumn_tree", BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));
    public static final PlacedFeature CYPRESS_TREE_CHECKED = register("cypress_tree", BOPTreeFeatures.CYPRESS_TREE.placed());
    public static final PlacedFeature CYPRESS_TREE_MEDIUM_CHECKED = register("cypress_tree_medium", BOPTreeFeatures.CYPRESS_TREE_MEDIUM.placed());
    public static final PlacedFeature DARK_OAK_POPLAR_TREE_CHECKED = register("dark_oak_poplar_tree", BOPTreeFeatures.DARK_OAK_POPLAR_TREE.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING));
    public static final PlacedFeature DEAD_TREE_WASTELAND_CHECKED = register("dead_tree_wasteland", BOPTreeFeatures.DEAD_TREE_WASTELAND.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature DEAD_TWIGLET_TREE_CHECKED = register("dead_twiglet_tree", BOPTreeFeatures.DEAD_TWIGLET_TREE.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature DYING_TREE_CHECKED = register("dying_tree", BOPTreeFeatures.DYING_TREE.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature DYING_TREE_WASTELAND_CHECKED = register("dying_tree_wasteland", BOPTreeFeatures.DYING_TREE_WASTELAND.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature FIR_TREE_CHECKED = register("fir_tree", BOPTreeFeatures.FIR_TREE.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FIR_TREE_LARGE_CHECKED = register("fir_tree_large", BOPTreeFeatures.FIR_TREE_LARGE.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FIR_TREE_SMALL_CHECKED = register("fir_tree_small", BOPTreeFeatures.FIR_TREE_SMALL.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FLOWERING_OAK_BUSH_CHECKED = register("flowering_oak_bush", BOPTreeFeatures.FLOWERING_OAK_BUSH.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature FLOWERING_OAK_TREE_BEES_CHECKED = register("flowering_oak_tree_bees", BOPTreeFeatures.FLOWERING_OAK_TREE_BEES.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature FLOWERING_OAK_TREE_CHECKED = register("flowering_oak_tree", BOPTreeFeatures.FLOWERING_OAK_TREE.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature JACARANDA_TREE_BEES_CHECKED = register("jacaranda_tree_bees", BOPTreeFeatures.JACARANDA_TREE_BEES.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final PlacedFeature MAHOGANY_TREE_CHECKED = register("mahogany_tree", BOPTreeFeatures.MAHOGANY_TREE.filteredByBlockSurvival(BOPBlocks.MAHOGANY_SAPLING));
    public static final PlacedFeature MAPLE_TREE_CHECKED = register("maple_tree_checked", BOPTreeFeatures.MAPLE_TREE.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final PlacedFeature MAPLE_TWIGLET_TREE_CHECKED = register("maple_twiglet_tree", BOPTreeFeatures.MAPLE_TWIGLET_TREE.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final PlacedFeature OAK_BUSH_CHECKED = register("oak_bush", BOPTreeFeatures.OAK_BUSH.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature ORANGE_AUTUMN_TREE_CHECKED = register("orange_autumn_tree", BOPTreeFeatures.ORANGE_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final PlacedFeature PINK_CHERRY_TREE_BEES_CHECKED = register("pink_cherry_tree_bees", BOPTreeFeatures.PINK_CHERRY_TREE_BEES.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_CHECKED = register("redwood_tree", BOPTreeFeatures.REDWOOD_TREE.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_LARGE_CHECKED = register("redwood_tree_large", BOPTreeFeatures.REDWOOD_TREE_LARGE.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_MEDIUM_CHECKED = register("redwood_tree_medium", BOPTreeFeatures.REDWOOD_TREE_MEDIUM.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature SMALL_DEAD_TREE_CHECKED = register("small_dead_tree", BOPTreeFeatures.SMALL_DEAD_TREE.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature SPARSE_ACACIA_TREE_CHECKED = register("sparse_acacia_tree", BOPTreeFeatures.SPARSE_ACACIA_TREE.filtered(BlockPredicate.matchesBlock(BOPBlocks.ORANGE_SAND, new BlockPos(0, -1, 0))));
    public static final PlacedFeature SPARSE_OAK_TREE_CHECKED = register("sparse_oak_tree", BOPTreeFeatures.SPARSE_OAK_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature SPRUCE_POPLAR_TREE_CHECKED = register("spruce_poplar_tree", BOPTreeFeatures.SPRUCE_POPLAR_TREE.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final PlacedFeature SPRUCE_TWIGLET_TREE_CHECKED = register("spruce_twiglet_tree", BOPTreeFeatures.SPRUCE_TWIGLET_TREE.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final PlacedFeature TALL_DEAD_TWIGLET_TREE_CHECKED = register("tall_dead_twiglet_tree", BOPTreeFeatures.TALL_DEAD_TWIGLET_TREE.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING));
    public static final PlacedFeature TALL_SPRUCE_TREE_BEES_CHECKED = register("tall_spruce_tree_bees", BOPTreeFeatures.TALL_SPRUCE_TREE_BEES.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final PlacedFeature TALL_SPRUCE_TREE_CHECKED = register("tall_spruce_tree", BOPTreeFeatures.TALL_SPRUCE_TREE.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING));
    public static final PlacedFeature TALL_TWIGLET_TREE_CHECKED = register("tall_twiglet_tree", BOPTreeFeatures.TALL_TWIGLET_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature TWIGLET_TREE_CHECKED = register("twiglet_tree", BOPTreeFeatures.TWIGLET_TREE.filteredByBlockSurvival(Blocks.OAK_SAPLING));
    public static final PlacedFeature WHITE_CHERRY_TREE_BEES_CHECKED = register("white_cherry_tree_bees", BOPTreeFeatures.WHITE_CHERRY_TREE_BEES.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final PlacedFeature YELLOW_AUTUMN_TREE_CHECKED = register("yellow_autumn_tree", BOPTreeFeatures.YELLOW_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
