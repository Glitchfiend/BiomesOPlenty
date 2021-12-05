/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPTreePlacements
{
    public static final PlacedFeature BIG_FLOWERING_TREE_CHECKED = register("big_flowering_tree", BOPTreeFeatures.BIG_FLOWERING_OAK_TREE.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature BIG_JACARANDA_TREE_CHECKED = register("big_jacaranda_tree", BOPTreeFeatures.BIG_JACARANDA_TREE.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final PlacedFeature BIG_MAPLE_TREE_CHECKED = register("big_maple_tree", BOPTreeFeatures.BIG_MAPLE_TREE.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final PlacedFeature BIG_ORANGE_AUTUMN_TREE_CHECKED = register("big_orange_autumn_tree", BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final PlacedFeature BIG_PINK_CHERRY_TREE_CHECKED = register("big_pink_cherry_tree", BOPTreeFeatures.BIG_PINK_CHERRY_TREE.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final PlacedFeature BIG_WHITE_CHERRY_TREE_CHECKED = register("big_white_cherry_tree", BOPTreeFeatures.BIG_WHITE_CHERRY_TREE.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final PlacedFeature BIG_YELLOW_AUTUMN_TREE_CHECKED = register("big_yellow_autumn_tree", BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));
    public static final PlacedFeature FIR_TREE_CHECKED = register("fir_tree", BOPTreeFeatures.FIR_TREE.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FIR_TREE_LARGE_CHECKED = register("fir_tree_large", BOPTreeFeatures.FIR_TREE_LARGE.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FLOWERING_OAK_TREE_BEES_CHECKED = register("flowering_oak_tree_bees", BOPTreeFeatures.FLOWERING_OAK_TREE_BEES.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature FLOWERING_OAK_BUSH_CHECKED = register("flowering_oak_bush", BOPTreeFeatures.FLOWERING_OAK_BUSH.filteredByBlockSurvival(BOPBlocks.FLOWERING_OAK_SAPLING));
    public static final PlacedFeature JACARANDA_TREE_BEES_CHECKED = register("jacaranda_tree_bees_checked", BOPTreeFeatures.JACARANDA_TREE_BEES.filteredByBlockSurvival(BOPBlocks.JACARANDA_SAPLING));
    public static final PlacedFeature MAPLE_TREE_CHECKED = register("maple_tree_checked", BOPTreeFeatures.MAPLE_TREE.filteredByBlockSurvival(BOPBlocks.MAPLE_SAPLING));
    public static final PlacedFeature ORANGE_AUTUMN_TREE_CHECKED = register("orange_autumn_tree_checked", BOPTreeFeatures.ORANGE_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.ORANGE_AUTUMN_SAPLING));
    public static final PlacedFeature PINK_CHERRY_TREE_BEES_CHECKED = register("pink_cherry_tree_bees_checked", BOPTreeFeatures.PINK_CHERRY_TREE_BEES.filteredByBlockSurvival(BOPBlocks.PINK_CHERRY_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_CHECKED = register("redwood_tree", BOPTreeFeatures.REDWOOD_TREE.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_MEDIUM_CHECKED = register("redwood_tree_medium", BOPTreeFeatures.REDWOOD_TREE_MEDIUM.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature REDWOOD_TREE_LARGE_CHECKED = register("redwood_tree_large", BOPTreeFeatures.REDWOOD_TREE_LARGE.filteredByBlockSurvival(BOPBlocks.REDWOOD_SAPLING));
    public static final PlacedFeature WHITE_CHERRY_TREE_BEES_CHECKED = register("white_cherry_tree_bees_checked", BOPTreeFeatures.WHITE_CHERRY_TREE_BEES.filteredByBlockSurvival(BOPBlocks.WHITE_CHERRY_SAPLING));
    public static final PlacedFeature YELLOW_AUTUMN_TREE_CHECKED = register("yellow_autumn_tree_checked", BOPTreeFeatures.YELLOW_AUTUMN_TREE.filteredByBlockSurvival(BOPBlocks.YELLOW_AUTUMN_SAPLING));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
