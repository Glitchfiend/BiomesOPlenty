/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.gen.feature.tree.*;
import biomesoplenty.common.world.gen.feature.tree.BigTreeFeature;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPBiomeFeatures
{
	//Standard Trees
	public static final AbstractTreeFeature<NoFeatureConfig> OAK_TREE = new BasicTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> SMALL_JUNGLE_TREE = new BasicTreeFeature.Builder().maxHeight(9).log(Blocks.JUNGLE_LOG.getDefaultState()).leaves(Blocks.JUNGLE_LEAVES.getDefaultState()).trunkFruit(Blocks.COCOA.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ORIGIN_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FLOWERING_OAK_TREE = new BasicTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> YELLOW_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(BOPBlocks.yellow_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ORANGE_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(BOPBlocks.orange_autumn_leaves.getDefaultState()).minHeight(5).maxHeight(8).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAPLE_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.getDefaultState()).minHeight(5).maxHeight(10).create();
	public static final AbstractTreeFeature<NoFeatureConfig> PINK_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WHITE_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JACARANDA_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.jacaranda_log.getDefaultState()).leaves(BOPBlocks.jacaranda_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SMALL_DEAD_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).altLeaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FULL_SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAGIC_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.magic_log.getDefaultState()).leaves(BOPBlocks.magic_leaves.getDefaultState()).create();
	
	//Big Trees
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_OAK_TREE = new BigTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_ORIGIN_TREE = new BigTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_FLOWERING_OAK_TREE = new BigTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_PINK_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.pink_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_WHITE_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.getDefaultState()).leaves(BOPBlocks.white_cherry_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIG_MAGIC_TREE = new BigTreeFeature.Builder().log(BOPBlocks.magic_log.getDefaultState()).leaves(BOPBlocks.magic_leaves.getDefaultState()).create();

	public static final AbstractTreeFeature<NoFeatureConfig> GIANT_TREE = new BigTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState().with(LeavesBlock.PERSISTENT, true)).minHeight(25).maxHeight(30).trunkWidth(4).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ETHEREAL_TREE = new BigTreeFeature.Builder().log(BOPBlocks.ethereal_log.getDefaultState()).leaves(BOPBlocks.ethereal_leaves.getDefaultState()).placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.END_STONE).minHeight(10).maxHeight(20).foliageHeight(1).create();

	//Conifer Trees
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_SPRUCE_TREE = new TaigaTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).maxHeight(13).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ALPS_SPRUCE_TREE = new TaigaTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.STONE).log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).maxHeight(13).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FIR_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(5).maxHeight(28).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FIR_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.getDefaultState()).leaves(BOPBlocks.fir_leaves.getDefaultState()).minHeight(20).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SEQUOIA_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.getDefaultState()).leaves(BOPBlocks.redwood_leaves.getDefaultState()).minHeight(5).maxHeight(15).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SEQUOIA_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.getDefaultState()).leaves(BOPBlocks.redwood_leaves.getDefaultState()).minHeight(20).maxHeight(35).create();
	public static final AbstractTreeFeature<NoFeatureConfig> UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).maxHeight(20).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.getDefaultState()).leaves(BOPBlocks.umbran_leaves.getDefaultState()).minHeight(20).maxHeight(30).trunkWidth(2).create();
	
	//Poplar Trees
	public static final AbstractTreeFeature<NoFeatureConfig> SPRUCE_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> BIRCH_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(Blocks.BIRCH_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DARK_OAK_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> YELLOW_POPLAR_TREE = new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.getDefaultState()).leaves(BOPBlocks.yellow_autumn_leaves.getDefaultState()).minHeight(10).maxHeight(18).create();
	
	//Swamp Trees
	public static final AbstractTreeFeature<NoFeatureConfig> SWAMP_TREE = new BasicTreeFeature.Builder().vine(Blocks.VINE.getDefaultState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.getDefaultState()).leaves(BOPBlocks.willow_leaves.getDefaultState()).vine(BOPBlocks.willow_vine.getDefaultState()).minHeight(6).maxHeight(10).maxLeavesRadius(2).leavesOffset(0).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.getDefaultState()).leaves(BOPBlocks.willow_leaves.getDefaultState()).vine(BOPBlocks.willow_vine.getDefaultState()).minHeight(10).maxHeight(16).maxLeavesRadius(2).leavesOffset(0).create();

	//Sparse Trees
	public static final AbstractTreeFeature<NoFeatureConfig> SPARSE_OAK_TREE = new BigTreeFeature.Builder().minHeight(7).maxHeight(12).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE = new BigTreeFeature.Builder().maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();
	
	//Bushes/Twiglets
	public static final AbstractTreeFeature<NoFeatureConfig> BUSH = new BushTreeFeature.Builder().maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_BUSH = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FLOWERING_BUSH = new BushTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.getDefaultState()).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> COBWEB_BUSH = new BushTreeFeature.Builder().maxHeight(2).altLeaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> FULL_COBWEB_BUSH = new BushTreeFeature.Builder().maxHeight(2).leaves(Blocks.COBWEB.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> HELLBARK_TREE = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK).log(BOPBlocks.hellbark_log.getDefaultState()).leaves(BOPBlocks.hellbark_leaves.getDefaultState()).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ETHEREAL_BUSH = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.END_STONE).log(BOPBlocks.ethereal_log.getDefaultState()).leaves(BOPBlocks.ethereal_leaves.getDefaultState()).maxHeight(2).create();

	public static final AbstractTreeFeature<NoFeatureConfig> TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> TALL_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(2).maxHeight(4).create();
	public static final AbstractTreeFeature<NoFeatureConfig> SPRUCE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.SPRUCE_LOG.getDefaultState()).leaves(Blocks.SPRUCE_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.JUNGLE_LOG.getDefaultState()).leaves(Blocks.JUNGLE_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> OASIS_JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.JUNGLE_LOG.getDefaultState()).leaves(Blocks.JUNGLE_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DARK_OAK_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.getDefaultState()).leaves(Blocks.DARK_OAK_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET_SMALL = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> ACACIA_TWIGLET = new TwigletTreeFeature.Builder().log(Blocks.ACACIA_LOG.getDefaultState()).leaves(Blocks.ACACIA_LEAVES.getDefaultState()).minHeight(1).maxHeight(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAPLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).leaves(BOPBlocks.maple_leaves.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(1).leaves(BOPBlocks.dead_leaves.getDefaultState()).log(BOPBlocks.dead_log.getDefaultState()).create();
	
	//Special Trees
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE = new RedwoodTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_MEDIUM = new RedwoodTreeFeature.Builder().minHeight(25).maxHeight(40).trunkWidth(2).create();
	public static final AbstractTreeFeature<NoFeatureConfig> REDWOOD_TREE_LARGE = new RedwoodTreeFeature.Builder().minHeight(45).maxHeight(60).trunkWidth(3).create();
	public static final AbstractTreeFeature<NoFeatureConfig> MAHOGANY_TREE = new MahoganyTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> PALM_TREE = new PalmTreeFeature.Builder().create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE = new TrunkTreeFeature.Builder().log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE_WASTELAND = new TrunkTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_sand).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DEAD_TREE_VOLCANO = new TrunkTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRAVEL).log(BOPBlocks.dead_log.getDefaultState()).leaves(Blocks.AIR.getDefaultState()).create();
	public static final AbstractTreeFeature<NoFeatureConfig> DYING_TREE_VOLCANO = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRAVEL).maxHeight(12).log(BOPBlocks.dead_log.getDefaultState()).leaves(BOPBlocks.dead_leaves.getDefaultState()).foliageHeight(1).create();

	/////////////////////////////////////////////////////////////////////////////////
	
	//Features
	public static final Feature<SplotchConfig> SPLOTCH = new SplotchFeature(SplotchConfig::deserialize);
	public static final Feature<NoFeatureConfig> BRAMBLE = new BrambleFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> MANGROVE = new MangroveFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> PUMPKIN_PATCH = new PumpkinPatchFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BIG_PUMPKIN = new BigPumpkinFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SHORT_BAMBOO = new ShortBambooFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCRUB = new ScrubFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> HEATH = new HeathFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_ROCKS = new ScatteredRocksFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> DEAD_CORAL_SPLATTER = new DeadCoralSplatterFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> DEAD_CORAL_SPIKES = new DeadCoralSpikesFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> NETHER_VINES = new NetherVinesFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> FLESH_SPLATTER = new FleshSplatterFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> BONE_SPINE = new BoneSpineFeature(NoFeatureConfig::deserialize);
	public static final AbstractTreeFeature<NoFeatureConfig> NETHERWART_BUBBLE = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK).log(Blocks.NETHER_WART_BLOCK.getDefaultState()).leaves(Blocks.NETHER_WART_BLOCK.getDefaultState()).maxHeight(2).create();
	public static final Feature<NoFeatureConfig> ASH_SPLATTER = new AshSplatterFeature(NoFeatureConfig::deserialize);
	public static final Feature<BigMushroomFeatureConfig> HUGE_RED_MUSHROOM_NETHER = new BigRedMushroomNetherFeature(BigMushroomFeatureConfig::deserialize);
	public static final Feature<BigMushroomFeatureConfig> HUGE_BROWN_MUSHROOM_NETHER = new BigBrownMushroomNetherFeature(BigMushroomFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> SCATTERED_NETHER_WART = new NetherWartFeature(NoFeatureConfig::deserialize);
	public static final Feature<NoFeatureConfig> GLOWSTONE_SPIKES = new GlowstoneSpikesFeature(NoFeatureConfig::deserialize);

	//Flowers
	public static final FlowersFeature CHAPARRAL_FLOWERS = new ChaparralFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature CHERRY_BLOSSOM_GROVE_FLOWERS = new CherryBlossomGroveFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature CONIFEROUS_FOREST_FLOWERS = new ConiferousForestFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature EXTENDED_FLOWERS = new ExtendedFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature FLOWER_MEADOW_FLOWERS = new FlowerMeadowFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature JUNGLE_FLOWERS = new JungleFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature LAVENDER_FLOWERS = new LavenderFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature LUSH_GRASSLAND_FLOWERS = new LushGrasslandFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature LUSH_SWAMP_FLOWERS = new LushSwampFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature MEADOW_FLOWERS = new MeadowFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature MOOR_FLOWERS = new MoorFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature MYSTIC_GROVE_FLOWERS = new MysticGroveFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature OMINOUS_WOODS_FLOWERS = new OminousWoodsFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature ORIGIN_FLOWERS = new OriginFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature PRAIRIE_FLOWERS = new PrairieFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature RAINFOREST_FLOWERS = new RainforestFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature SHRUBLAND_FLOWERS = new ShrublandFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature SNOWY_FLOWERS = new SnowyFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature TROPICS_FLOWERS = new TropicsFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature WASTELAND_FLOWERS = new WastelandFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature WETLAND_FLOWERS = new WetlandFlowersFeature(NoFeatureConfig::deserialize);
	public static final FlowersFeature XERIC_SHRUBLAND_FLOWERS = new XericShrublandFlowersFeature(NoFeatureConfig::deserialize);
	
	//Surfaces
	public static final SurfaceBuilder<SurfaceBuilderConfig> BOG_SURFACE_BUILDER = new BogSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> BRUSHLAND_SURFACE_BUILDER = new BrushlandSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL_SURFACE_BUILDER = new ChaparralSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> COLD_DESERT_SURFACE_BUILDER = new ColdDesertSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> GRANITE_SURFACE_BUILDER = new GraniteSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MARSH_SURFACE_BUILDER = new MarshSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MANGROVE_SURFACE_BUILDER = new MangroveSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_SURFACE_BUILDER = new MudSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> PODZOL_SURFACE_BUILDER = new PodzolSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO_SURFACE_BUILDER = new VolcanoSurfaceBuilder(SurfaceBuilderConfig::deserialize);
	public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO_EDGE_SURFACE_BUILDER = new VolcanoEdgeSurfaceBuilder(SurfaceBuilderConfig::deserialize);

	public static final SurfaceBuilderConfig ASH_SURFACE = new SurfaceBuilderConfig(BOPBlocks.ash_block.getDefaultState(), BOPBlocks.ash_block.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig GRANITE_SURFACE = new SurfaceBuilderConfig(Blocks.GRANITE.getDefaultState(), Blocks.GRANITE.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.getDefaultState(), Blocks.MAGMA_BLOCK.getDefaultState(), BOPBlocks.ash_block.getDefaultState());
	public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(BOPBlocks.mud.getDefaultState(), BOPBlocks.mud.getDefaultState(), BOPBlocks.mud.getDefaultState());
	public static final SurfaceBuilderConfig RED_SAND_SURFACE = new SurfaceBuilderConfig(Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState(), Blocks.RED_SAND.getDefaultState());
	public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.getDefaultState(), Blocks.SNOW_BLOCK.getDefaultState(), Blocks.GRAVEL.getDefaultState());
	public static final SurfaceBuilderConfig WHITE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.white_sand.getDefaultState(), BOPBlocks.white_sand.getDefaultState(), BOPBlocks.white_sand.getDefaultState());
	public static final SurfaceBuilderConfig DRIED_SAND_GRAVEL_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_sand.getDefaultState(), BOPBlocks.dried_sand.getDefaultState(), BOPBlocks.dried_sand.getDefaultState());
}
