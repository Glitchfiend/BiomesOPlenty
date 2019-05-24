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
import biomesoplenty.common.world.gen.feature.tree.BigTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.BushTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.MahoganyTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.PalmTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.PoplarTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.RedwoodTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.TaigaTreeFeature;
import biomesoplenty.common.world.gen.feature.tree.TwigletTreeFeature;
import net.minecraft.init.Blocks;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.ProbabilityConfig;
import net.minecraft.world.gen.feature.SphereReplaceConfig;
import net.minecraft.world.gen.feature.SphereReplaceFeature;
import net.minecraft.world.gen.surfacebuilders.ISurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPBiomeFeatures
{
	public static final AbstractTreeFeature<NoFeatureConfig> CONIFEROUS_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(5).maxHeight(28).create();
	public static final AbstractTreeFeature<NoFeatureConfig> CONIFEROUS_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(20).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).maxHeight(20).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).minHeight(20).maxHeight(30).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SEQUOIA_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.getDefaultState()).leaves(BOPBlocks.redwood_leaves.getDefaultState()).minHeight(5).maxHeight(15).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SEQUOIA_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.getDefaultState()).leaves(BOPBlocks.redwood_leaves.getDefaultState()).minHeight(20).maxHeight(35).create();
	public static final AbstractTreeFeature<NoFeatureConfig> OAK_TREE = new BasicTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_OAK_TREE = new BigTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> COBWEB_BUSH = new BushTreeFeature.Builder().maxHeight(2).altLeaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FULL_COBWEB_BUSH = new BushTreeFeature.Builder().maxHeight(2).leaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SMALL_DEAD_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).altLeaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FULL_SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ORIGIN_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_ORIGIN_TREE = new BigTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAGIC_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.magic_log.getDefaultState()).leaves(BOPBlocks.magic_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_MAGIC_TREE = new BigTreeFeature.Builder().log(BOPBlocks.magic_log.getDefaultState()).leaves(BOPBlocks.magic_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JACARANDA_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.jacaranda_log.getDefaultState()).leaves(BOPBlocks.jacaranda_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FLOWERING_OAK_TREE = new BasicTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_FLOWERING_OAK_TREE = new BigTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE = new BigTreeFeature.Builder().maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).foliageHeight(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE = new BigTreeFeature.Builder().maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SPARSE_OAK_TREE = new BigTreeFeature.Builder().minHeight(7).maxHeight(12).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).foliageHeight(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> YELLOW_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(BOPBlocks.yellow_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_YELLOW_AUTUMN_TREE = new BigTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(BOPBlocks.yellow_autumn_leaves.getDefaultState()).maxHeight(13).foliageHeight(3).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ORANGE_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(BOPBlocks.orange_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAPLE_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.getDefaultState()).minHeight(5).maxHeight(10).create();
	public static final AbstractTreeFeature<NoFeatureConfig> PINK_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WHITE_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_PINK_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_WHITE_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(1).leaves(BOPBlocks.dead_leaves.getDefaultState()).log(BOPBlocks.dead_log.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAPLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).leaves(BOPBlocks.maple_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JACARANDA_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).leaves(BOPBlocks.jacaranda_leaves.getDefaultState()).log(BOPBlocks.jacaranda_log.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_BUSH = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET_SMALL = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET = new TwigletTreeFeature.Builder().log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SPRUCE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).minHeight(1).maxHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DARK_OAK_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(2).maxHeight(4).create();
	public static final AbstractTreeFeature<NoFeatureConfig> OAK_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.OAK_LOG.getDefaultState()).leaves(Blocks.OAK_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIRCH_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(Blocks.BIRCH_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DARK_OAK_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAHOGANY_TREE = new MahoganyTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_SPRUCE_TREE = new TaigaTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).maxHeight(13).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ALPS_SPRUCE_TREE = new TaigaTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.STONE).log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).maxHeight(13).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SWAMP_TREE = new BasicTreeFeature.Builder().vine(Blocks.VINE.getDefaultState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.getDefaultState()).leaves(BOPBlocks.willow_leaves.getDefaultState()).vine(BOPBlocks.willow_vine.getDefaultState()).minHeight(6).maxHeight(10).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.getDefaultState()).leaves(BOPBlocks.willow_leaves.getDefaultState()).vine(BOPBlocks.willow_vine.getDefaultState()).minHeight(10).maxHeight(16).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BUSH = new BushTreeFeature.Builder().maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> PALM_TREE = new PalmTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> OASIS_PALM_TREE = new PalmTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.JUNGLE_LOG.getDefaultState()).leaves(Blocks.JUNGLE_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> OASIS_JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.JUNGLE_LOG.getDefaultState()).leaves(Blocks.JUNGLE_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE = new RedwoodTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_MEDIUM = new RedwoodTreeFeature.Builder().minHeight(25).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_LARGE = new RedwoodTreeFeature.Builder().minHeight(45).maxHeight(60).trunkWidth(3).create();
	
	public static final Feature<SplotchConfig> SPLOTCH = new SplotchFeature();
	public static final Feature<NoFeatureConfig> BRAMBLE = new BrambleFeature();
	public static final Feature<NoFeatureConfig> MANGROVE = new MangroveFeature();
	
	public static final AbstractFlowersFeature CHERRY_BLOSSOM_GROVE_FLOWERS = new CherryBlossomGroveFlowersFeature();
	public static final AbstractFlowersFeature EXTENDED_FLOWERS = new ExtendedFlowersFeature();
	public static final AbstractFlowersFeature FLOWER_MEADOW_FLOWERS = new FlowerMeadowFlowersFeature();
	public static final AbstractFlowersFeature JUNGLE_FLOWERS = new JungleFlowersFeature();
	public static final AbstractFlowersFeature LAVENDER_FLOWERS = new LavenderFlowersFeature();
	public static final AbstractFlowersFeature LUSH_SWAMP_FLOWERS = new LushSwampFlowersFeature();
	public static final AbstractFlowersFeature MEADOW_FLOWERS = new MeadowFlowersFeature();
	public static final AbstractFlowersFeature MOOR_FLOWERS = new MoorFlowersFeature();
	public static final AbstractFlowersFeature MYSTIC_GROVE_FLOWERS = new MysticGroveFlowersFeature();
	public static final AbstractFlowersFeature ORIGIN_FLOWERS = new OriginFlowersFeature();
	public static final AbstractFlowersFeature PRAIRIE_FLOWERS = new PrairieFlowersFeature();
	public static final AbstractFlowersFeature RAINFOREST_FLOWERS = new RainforestFlowersFeature();
	public static final AbstractFlowersFeature SHRUBLAND_FLOWERS = new ShrublandFlowersFeature();
	public static final AbstractFlowersFeature SNOWY_FLOWERS = new SnowyFlowersFeature();
	public static final AbstractFlowersFeature TROPICS_FLOWERS = new TropicsFlowersFeature();
	public static final AbstractFlowersFeature WASTELAND_FLOWERS = new WastelandFlowersFeature();
	public static final AbstractFlowersFeature WETLAND_FLOWERS = new WetlandFlowersFeature();
	public static final AbstractFlowersFeature XERIC_SHRUBLAND_FLOWERS = new XericShrublandFlowersFeature();
	
	public static final ISurfaceBuilder<SurfaceBuilderConfig> BRUSHLAND_SURFACE_BUILDER = new BrushlandSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL_SURFACE_BUILDER = new ChaparralSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> COLD_DESERT_SURFACE_BUILDER = new ColdDesertSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> GRANITE_SURFACE_BUILDER = new GraniteSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> MARSH_SURFACE_BUILDER = new MarshSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> MANGROVE_SURFACE_BUILDER = new MangroveSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> PODZOL_SURFACE_BUILDER = new PodzolSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> VOLCANO_SURFACE_BUILDER = new VolcanoSurfaceBuilder();
	public static final ISurfaceBuilder<SurfaceBuilderConfig> VOLCANO_EDGE_SURFACE_BUILDER = new VolcanoEdgeSurfaceBuilder();
	
	public static final SurfaceBuilderConfig ASH_SURFACE = new SurfaceBuilderConfig(BOPBlocks.ash_block.getDefaultState(), BOPBlocks.ash_block.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig GRANITE_SURFACE = new SurfaceBuilderConfig(Blocks.GRANITE.getDefaultState(), Blocks.GRANITE.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.getDefaultState(), Blocks.MAGMA_BLOCK.getDefaultState(), BOPBlocks.ash_block.getDefaultState());
	public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(BOPBlocks.mud.getDefaultState(), BOPBlocks.mud.getDefaultState(), BOPBlocks.mud.getDefaultState());
	public static final SurfaceBuilderConfig PACKED_ICE_SURFACE = new SurfaceBuilderConfig(Blocks.PACKED_ICE.getDefaultState(), Blocks.PACKED_ICE.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig RED_SAND_SURFACE = new SurfaceBuilderConfig(Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState());
	public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig WHITE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.white_sand.getDefaultState(), BOPBlocks.white_sand.getDefaultState(), BOPBlocks.white_sand.getDefaultState());
	public static final SurfaceBuilderConfig DRIED_SAND_GRAVEL_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_sand.getDefaultState(), BOPBlocks.dried_sand.getDefaultState(), BOPBlocks.dried_sand.getDefaultState());
}
