/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.gen.feature.tree.BasicTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.BayouTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.BigTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.BulbTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.BushTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.MahoganyTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.RedwoodTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.TaigaTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.TwigletTreeFeature;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.surfacebuilders.ExtremeHillsSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPBiomeFeatures
{
	public static final AbstractTreeFeature<NoFeatureConfig> CONIFEROUS_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(5).maxHeight(28).create();
	public static final AbstractTreeFeature<NoFeatureConfig> CONIFEROUS_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(20).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).maxHeight(20).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).minHeight(20).maxHeight(30).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> OAK_TREE = new BasicTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_OAK_TREE = new BigTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAGIC_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.magic_log.getDefaultState()).leaves(BOPBlocks.magic_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JACARANDA_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.jacaranda_log.getDefaultState()).leaves(BOPBlocks.jacaranda_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FLOWERING_OAK_TREE = new BasicTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_FLOWERING_OAK_TREE = new BigTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE = new BigTreeFeature.Builder().maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).foliageHeight(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE = new BigTreeFeature.Builder().maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).foliageHeight(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> YELLOW_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(BOPBlocks.yellow_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ORANGE_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(BOPBlocks.orange_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAPLE_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.getDefaultState()).minHeight(5).maxHeight(10).create();
	public static final AbstractTreeFeature<NoFeatureConfig> PINK_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WHITE_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_PINK_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_WHITE_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TWIGLET_TREE = new TwigletTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> DARK_OAK_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(2).maxHeight(4).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BAYOU_TREE = new BayouTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> BULB_TREE = new BulbTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAHOGANY_TREE = new MahoganyTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_SPRUCE_TREE = new TaigaTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).maxHeight(13).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SWAMP_TREE = new BasicTreeFeature.Builder().vine(Blocks.VINE.getDefaultState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> IVY_TREE = new BasicTreeFeature.Builder().vine(BOPBlocks.ivy.getDefaultState()).minHeight(5).maxHeight(9).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.getDefaultState()).leaves(BOPBlocks.willow_leaves.getDefaultState()).vine(BOPBlocks.willow_vine.getDefaultState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BUSH = new BushTreeFeature.Builder().maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE = new RedwoodTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_MEDIUM = new RedwoodTreeFeature.Builder().minHeight(25).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_LARGE = new RedwoodTreeFeature.Builder().minHeight(45).maxHeight(60).trunkWidth(3).create();
	
	public static final AbstractFlowersFeature CHERRY_BLOSSOM_GROVE_FLOWERS = new CherryBlossomGroveFlowersFeature();
	public static final AbstractFlowersFeature EXTENDED_FLOWERS = new ExtendedFlowersFeature();
	public static final AbstractFlowersFeature JUNGLE_FLOWERS = new JungleFlowersFeature();
	public static final AbstractFlowersFeature LAVENDER_FLOWERS = new LavenderFlowersFeature();
	public static final AbstractFlowersFeature LUSH_SWAMP_FLOWERS = new LushSwampFlowersFeature();
	public static final AbstractFlowersFeature MEADOW_FLOWERS = new MeadowFlowersFeature();
	public static final AbstractFlowersFeature MYSTIC_GROVE_FLOWERS = new MysticGroveFlowersFeature();
	public static final AbstractFlowersFeature SHRUBLAND_FLOWERS = new ShrublandFlowersFeature();
	public static final AbstractFlowersFeature SNOWY_FLOWERS = new SnowyFlowersFeature();
	public static final AbstractFlowersFeature WASTELAND_FLOWERS = new WastelandFlowersFeature();
	public static final AbstractFlowersFeature WETLAND_FLOWERS = new WetlandFlowersFeature();
	
	public static final ISurfaceBuilder<SurfaceBuilderConfig> BOG_SURFACE_BUILDER = new BogSurfaceBuilder();
	
	public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig DRIED_SAND_GRAVEL_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_sand.getDefaultState(), BOPBlocks.dried_sand.getDefaultState(), Blocks.GRAVEL.getDefaultState());
}
