/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import net.minecraft.core.BlockPos;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.BlockPredicateFilter;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;

public class BOPTreePlacements
{
    public static final RegistryObject<PlacedFeature> ACACIA_BUSH_TREE_CHECKED = register("acacia_bush_tree", BOPTreeFeatures.ACACIA_BUSH_TREE, () -> List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND.get()))));
    public static final RegistryObject<PlacedFeature> ACACIA_TWIGLET_CHECKED = register("acacia_twiglet", BOPTreeFeatures.ACACIA_TWIGLET, () -> List.of(BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND.get())))));
    public static final RegistryObject<PlacedFeature> ACACIA_TWIGLET_SMALL_CHECKED = register("acacia_twiglet_small", BOPTreeFeatures.ACACIA_TWIGLET_SMALL, () -> List.of(BlockPredicateFilter.forPredicate(BlockPredicate.anyOf(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), Blocks.SAND, BOPBlocks.ORANGE_SAND.get())))));
    public static final RegistryObject<PlacedFeature> BIG_FLOWERING_TREE_CHECKED = register("big_flowering_tree", BOPTreeFeatures.BIG_FLOWERING_OAK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_HELLBARK_TREE_CHECKED = register("big_hellbark_tree", BOPTreeFeatures.BIG_HELLBARK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_JACARANDA_TREE_CHECKED = register("big_jacaranda_tree", BOPTreeFeatures.BIG_JACARANDA_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_MAGIC_TREE_CHECKED = register("big_magic_tree", BOPTreeFeatures.BIG_MAGIC_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_MAPLE_TREE_CHECKED = register("big_maple_tree", BOPTreeFeatures.BIG_MAPLE_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_OAK_TREE_CHECKED = register("big_oak_tree", BOPTreeFeatures.BIG_OAK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> BIG_ORANGE_AUTUMN_TREE_CHECKED = register("big_orange_autumn_tree", BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_ORIGIN_TREE_CHECKED = register("big_origin_tree", BOPTreeFeatures.BIG_ORIGIN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> BIG_PINK_CHERRY_TREE_CHECKED = register("big_pink_cherry_tree", BOPTreeFeatures.BIG_PINK_CHERRY_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_RAINBOW_BIRCH_TREE_CHECKED = register("big_rainbow_birch_tree", BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_WHITE_CHERRY_TREE_CHECKED = register("big_white_cherry_tree", BOPTreeFeatures.BIG_WHITE_CHERRY_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> BIG_YELLOW_AUTUMN_TREE_CHECKED = register("big_yellow_autumn_tree", BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> CYPRESS_TREE_CHECKED = register("cypress_tree", BOPTreeFeatures.CYPRESS_TREE);
    public static final RegistryObject<PlacedFeature> CYPRESS_TREE_MEDIUM_CHECKED = register("cypress_tree_medium", BOPTreeFeatures.CYPRESS_TREE_MEDIUM);
    public static final RegistryObject<PlacedFeature> DARK_OAK_POPLAR_TREE_CHECKED = register("dark_oak_poplar_tree", BOPTreeFeatures.DARK_OAK_POPLAR_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> DEAD_TREE_WASTELAND_CHECKED = register("dead_tree_wasteland", BOPTreeFeatures.DEAD_TREE_WASTELAND, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> DEAD_TWIGLET_TREE_CHECKED = register("dead_twiglet_tree", BOPTreeFeatures.DEAD_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> DEAD_TWIGLET_TREE_SMALL_CHECKED = register("dead_twiglet_tree_small", BOPTreeFeatures.DEAD_TWIGLET_TREE_SMALL, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> DYING_TREE_CHECKED = register("dying_tree", BOPTreeFeatures.DYING_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> DYING_TREE_WASTELAND_CHECKED = register("dying_tree_wasteland", BOPTreeFeatures.DYING_TREE_WASTELAND, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FIR_TREE_CHECKED = register("fir_tree", BOPTreeFeatures.FIR_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FIR_TREE_LARGE_CHECKED = register("fir_tree_large", BOPTreeFeatures.FIR_TREE_LARGE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FIR_TREE_SMALL_CHECKED = register("fir_tree_small", BOPTreeFeatures.FIR_TREE_SMALL, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FLOWERING_OAK_BUSH_CHECKED = register("flowering_oak_bush", BOPTreeFeatures.FLOWERING_OAK_BUSH, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FLOWERING_OAK_TREE_BEES_CHECKED = register("flowering_oak_tree_bees", BOPTreeFeatures.FLOWERING_OAK_TREE_BEES, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> FLOWERING_OAK_TREE_CHECKED = register("flowering_oak_tree", BOPTreeFeatures.FLOWERING_OAK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> GIANT_TREE_CHECKED = register("giant_tree", BOPTreeFeatures.GIANT_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.DARK_OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> HELLBARK_TREE_CHECKED = register("hellbark_tree", BOPTreeFeatures.HELLBARK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.HELLBARK_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> JACARANDA_TREE_BEES_CHECKED = register("jacaranda_tree_bees", BOPTreeFeatures.JACARANDA_TREE_BEES, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> JACARANDA_TREE_CHECKED = register("jacaranda_tree", BOPTreeFeatures.JACARANDA_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> JUNGLE_BUSH_CHECKED = register("jungle_bush", BOPTreeFeatures.JUNGLE_BUSH, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING)));
    public static final RegistryObject<PlacedFeature> JUNGLE_TWIGLET_TREE_CHECKED = register("jungle_twiglet_tree", BOPTreeFeatures.JUNGLE_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.JUNGLE_SAPLING)));
    public static final RegistryObject<PlacedFeature> MAGIC_TREE_CHECKED = register("magic_tree", BOPTreeFeatures.MAGIC_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAGIC_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> MAHOGANY_TREE_CHECKED = register("mahogany_tree", BOPTreeFeatures.MAHOGANY_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAHOGANY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> MAPLE_TREE_CHECKED = register("maple_tree_checked", BOPTreeFeatures.MAPLE_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> MAPLE_TWIGLET_TREE_CHECKED = register("maple_twiglet_tree", BOPTreeFeatures.MAPLE_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> OAK_BUSH_CHECKED = register("oak_bush", BOPTreeFeatures.OAK_BUSH, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> ORANGE_AUTUMN_TREE_CHECKED = register("orange_autumn_tree", BOPTreeFeatures.ORANGE_AUTUMN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> ORIGIN_TREE_CHECKED = register("origin_tree", BOPTreeFeatures.ORIGIN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> PALM_TREE_CHECKED = register("palm_tree", BOPTreeFeatures.PALM_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.PALM_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> PINK_CHERRY_TREE_BEES_CHECKED = register("pink_cherry_tree_bees", BOPTreeFeatures.PINK_CHERRY_TREE_BEES, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> RAINBOW_BIRCH_TREE_CHECKED = register("rainbow_birch_tree", BOPTreeFeatures.RAINBOW_BIRCH_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.RAINBOW_BIRCH_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> REDWOOD_TREE_CHECKED = register("redwood_tree", BOPTreeFeatures.REDWOOD_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> REDWOOD_TREE_LARGE_CHECKED = register("redwood_tree_large", BOPTreeFeatures.REDWOOD_TREE_LARGE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> REDWOOD_TREE_MEDIUM_CHECKED = register("redwood_tree_medium", BOPTreeFeatures.REDWOOD_TREE_MEDIUM, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> SMALL_DEAD_TREE_CHECKED = register("small_dead_tree", BOPTreeFeatures.SMALL_DEAD_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> SPARSE_ACACIA_TREE_CHECKED = register("sparse_acacia_tree", BOPTreeFeatures.SPARSE_ACACIA_TREE, () -> List.of(BlockPredicateFilter.forPredicate(BlockPredicate.matchesBlocks(new BlockPos(0, -1, 0), BOPBlocks.ORANGE_SAND.get()))));
    public static final RegistryObject<PlacedFeature> SPARSE_OAK_TREE_CHECKED = register("sparse_oak_tree", BOPTreeFeatures.SPARSE_OAK_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> SPRUCE_BUSH_CHECKED = register("spruce_bush", BOPTreeFeatures.SPRUCE_BUSH, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<PlacedFeature> SPRUCE_POPLAR_TREE_CHECKED = register("spruce_poplar_tree", BOPTreeFeatures.SPRUCE_POPLAR_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<PlacedFeature> SPRUCE_TWIGLET_TREE_CHECKED = register("spruce_twiglet_tree", BOPTreeFeatures.SPRUCE_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<PlacedFeature> TALL_DEAD_TWIGLET_TREE_CHECKED = register("tall_dead_twiglet_tree", BOPTreeFeatures.TALL_DEAD_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.DEAD_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> TALL_SPRUCE_TREE_BEES_CHECKED = register("tall_spruce_tree_bees", BOPTreeFeatures.TALL_SPRUCE_TREE_BEES, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<PlacedFeature> TALL_SPRUCE_TREE_CHECKED = register("tall_spruce_tree", BOPTreeFeatures.TALL_SPRUCE_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.SPRUCE_SAPLING)));
    public static final RegistryObject<PlacedFeature> TALL_TWIGLET_TREE_CHECKED = register("tall_twiglet_tree", BOPTreeFeatures.TALL_TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> TALL_UMBRAN_TREE_CHECKED = register("tall_umbran_tree", BOPTreeFeatures.TALL_UMBRAN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> TWIGLET_TREE_CHECKED = register("twiglet_tree", BOPTreeFeatures.TWIGLET_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(Blocks.OAK_SAPLING)));
    public static final RegistryObject<PlacedFeature> UMBRAN_TREE_CHECKED = register("umbran_tree", BOPTreeFeatures.UMBRAN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.UMBRAN_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> WHITE_CHERRY_TREE_BEES_CHECKED = register("white_cherry_tree_bees", BOPTreeFeatures.WHITE_CHERRY_TREE_BEES, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> WILLOW_TREE_CHECKED = register("willow_tree", BOPTreeFeatures.WILLOW_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.WILLOW_SAPLING.get())));
    public static final RegistryObject<PlacedFeature> YELLOW_AUTUMN_TREE_CHECKED = register("yellow_autumn_tree", BOPTreeFeatures.YELLOW_AUTUMN_TREE, () -> List.of(PlacementUtils.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING.get())));

    public static void setup() {}
}
