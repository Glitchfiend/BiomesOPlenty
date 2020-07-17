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
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.util.Direction;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilders.SurfaceBuilderConfig;

public class BOPBiomeFeatures
{
	//Standard Trees
	public static final Feature<BaseTreeFeatureConfig> SMALL_JUNGLE_TREE = new BasicTreeFeature.Builder().maxHeight(9).log(Blocks.JUNGLE_LOG.defaultBlockState()).leaves(Blocks.JUNGLE_LEAVES.defaultBlockState()).trunkFruit(Blocks.COCOA.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> ORIGIN_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create();
	public static final Feature<BaseTreeFeatureConfig> FLOWERING_OAK_TREE = new BasicTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> RAINBOW_BIRCH_TREE = new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.rainbow_birch_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> YELLOW_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.yellow_autumn_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create();
	public static final Feature<BaseTreeFeatureConfig> ORANGE_AUTUMN_TREE = new BasicTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(BOPBlocks.orange_autumn_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create();
	public static final Feature<BaseTreeFeatureConfig> MAPLE_TREE = new BasicTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.defaultBlockState()).minHeight(5).maxHeight(10).create();
	public static final Feature<BaseTreeFeatureConfig> PINK_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.pink_cherry_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> WHITE_CHERRY_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.white_cherry_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> JACARANDA_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.jacaranda_log.defaultBlockState()).leaves(BOPBlocks.jacaranda_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> SMALL_DEAD_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).altLeaves(Blocks.COBWEB.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> FULL_SILK_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(Blocks.COBWEB.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> MAGIC_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.magic_log.defaultBlockState()).leaves(BOPBlocks.magic_leaves.defaultBlockState()).create();
	
	//Big Trees
	public static final Feature<BaseTreeFeatureConfig> BIG_OAK_TREE = new BigTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> BIG_ORIGIN_TREE = new BigTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_FLOWERING_OAK_TREE = new BigTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_RAINBOW_BIRCH_TREE = new BigTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.rainbow_birch_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_YELLOW_AUTUMN_TREE = new BigTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.yellow_autumn_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_ORANGE_AUTUMN_TREE = new BigTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(BOPBlocks.orange_autumn_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_MAPLE_TREE = new BigTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_PINK_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.pink_cherry_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_WHITE_CHERRY_TREE = new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.white_cherry_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_JACARANDA_TREE = new BigTreeFeature.Builder().log(BOPBlocks.jacaranda_log.defaultBlockState()).leaves(BOPBlocks.jacaranda_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIG_MAGIC_TREE = new BigTreeFeature.Builder().log(BOPBlocks.magic_log.defaultBlockState()).leaves(BOPBlocks.magic_leaves.defaultBlockState()).create();

	public static final Feature<BaseTreeFeatureConfig> GIANT_TREE = new BigTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)).minHeight(15).maxHeight(20).trunkWidth(4).create();

	//Conifer Trees
	public static final Feature<BaseTreeFeatureConfig> TALL_SPRUCE_TREE = new TaigaTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).maxHeight(13).create();
	public static final Feature<BaseTreeFeatureConfig> ALPS_SPRUCE_TREE = new TaigaTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.STONE).log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).maxHeight(13).create();
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE_SMALL = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(5).maxHeight(11).create();
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(5).maxHeight(28).create();
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(20).maxHeight(40).trunkWidth(2).create();
	public static final Feature<BaseTreeFeatureConfig> SEQUOIA_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.defaultBlockState()).leaves(BOPBlocks.redwood_leaves.defaultBlockState()).minHeight(5).maxHeight(15).create();
	public static final Feature<BaseTreeFeatureConfig> SEQUOIA_TREE_LARGE = new TaigaTreeFeature.Builder().log(BOPBlocks.redwood_log.defaultBlockState()).leaves(BOPBlocks.redwood_leaves.defaultBlockState()).minHeight(20).maxHeight(35).create();
	public static final Feature<BaseTreeFeatureConfig> UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.defaultBlockState()).leaves(BOPBlocks.umbran_leaves.defaultBlockState()).maxHeight(20).create();
	public static final Feature<BaseTreeFeatureConfig> TALL_UMBRAN_TREE = new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.defaultBlockState()).leaves(BOPBlocks.umbran_leaves.defaultBlockState()).minHeight(20).maxHeight(30).trunkWidth(2).create();

	//Poplar Trees
	public static final Feature<BaseTreeFeatureConfig> SPRUCE_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> BIRCH_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(Blocks.BIRCH_LEAVES.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> DARK_OAK_POPLAR = new PoplarTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> YELLOW_POPLAR_TREE = new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.yellow_autumn_leaves.defaultBlockState()).create();

	//Swamp Trees
	public static final Feature<BaseTreeFeatureConfig> CYPRESS_TREE = new CypressTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> CYPRESS_TREE_MEDIUM = new CypressTreeFeature.Builder().minHeight(15).maxHeight(25).trunkWidth(2).create();
	public static final Feature<BaseTreeFeatureConfig> TALL_SWAMP_TREE = new BasicTreeFeature.Builder().vine(Blocks.VINE.defaultBlockState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create();
	public static final Feature<BaseTreeFeatureConfig> WILLOW_TREE = new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.defaultBlockState()).leaves(BOPBlocks.willow_leaves.defaultBlockState()).vine(BOPBlocks.willow_vine.defaultBlockState()).minHeight(6).maxHeight(10).maxLeavesRadius(2).leavesOffset(0).create();

	//Sparse Trees
	public static final Feature<BaseTreeFeatureConfig> SPARSE_OAK_TREE = new BigTreeFeature.Builder().maxHeight(10).foliageHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> DYING_TREE = new BigTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).maxHeight(10).foliageHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> DYING_TREE_WASTELAND = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_salt).log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).maxHeight(10).foliageHeight(1).create();
	public static final Feature<BaseTreeFeatureConfig> DYING_TREE_VOLCANO = new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRAVEL).log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).maxHeight(10).foliageHeight(2).create();

	//Bushes/Twiglets
	public static final Feature<BaseTreeFeatureConfig> BUSH = new BushTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> ACACIA_BUSH = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> FLOWERING_BUSH = new BushTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> COBWEB_BUSH = new BushTreeFeature.Builder().altLeaves(Blocks.COBWEB.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> FULL_COBWEB_BUSH = new BushTreeFeature.Builder().leaves(Blocks.COBWEB.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> HELLBARK_TREE = new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK || world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING)).log(BOPBlocks.hellbark_log.defaultBlockState()).leaves(BOPBlocks.hellbark_leaves.defaultBlockState()).create();

	public static final Feature<BaseTreeFeatureConfig> TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> TALL_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(2).maxHeight(4).create();
	public static final Feature<BaseTreeFeatureConfig> SPRUCE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.JUNGLE_LOG.defaultBlockState()).leaves(Blocks.JUNGLE_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> OASIS_JUNGLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.JUNGLE_LOG.defaultBlockState()).leaves(Blocks.JUNGLE_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> DARK_OAK_TWIGLET_TREE = new TwigletTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET_TREE = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET_SMALL = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET = new TwigletTreeFeature.Builder().log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create();
	public static final Feature<BaseTreeFeatureConfig> MAPLE_TWIGLET_TREE = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).leaves(BOPBlocks.maple_leaves.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE_SMALL = new TwigletTreeFeature.Builder().minHeight(1).maxHeight(1).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE = new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.05F, 0.25F).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(4).maxHeight(7).create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE_TALL = new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.15F, 0.6F).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(8).maxHeight(12).create();

	//Special Trees
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE = new RedwoodTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE_MEDIUM = new RedwoodTreeFeature.Builder().minHeight(25).maxHeight(40).trunkWidth(2).create();
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE_LARGE = new RedwoodTreeFeature.Builder().minHeight(45).maxHeight(60).trunkWidth(3).create();
	public static final Feature<BaseTreeFeatureConfig> MAHOGANY_TREE = new MahoganyTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> PALM_TREE = new PalmTreeFeature.Builder().create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TREE = new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(6).maxHeight(10).create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TREE_WASTELAND = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_salt).trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(6).maxHeight(10).create();
	public static final Feature<BaseTreeFeatureConfig> DEAD_TREE_VOLCANO = new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRAVEL).trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(6).maxHeight(10).create();

	/////////////////////////////////////////////////////////////////////////////////
	
	//Features
	public static final Feature<NoFeatureConfig> BRAMBLE = new BrambleFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> MANGROVE = new MangroveFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> PUMPKIN_PATCH = new PumpkinPatchFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> BIG_PUMPKIN = new BigPumpkinFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SHORT_BAMBOO = new ShortBambooFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SCRUB = new ScrubFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SCATTERED_ROCKS = new ScatteredRocksFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> NETHER_VINES = new NetherVinesFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> BONE_SPINE = new BoneSpineFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> PODZOL_SPLATTER = new PodzolSplatterFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> MYCELIUM_SPLATTER = new MyceliumSplatterFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> OBSIDIAN_SPLATTER = new ObsidianSplatterFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SMALL_RED_MUSHROOM = new SmallRedMushroomFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SMALL_BROWN_MUSHROOM = new SmallBrownMushroomFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SMALL_GLOWSHROOM = new SmallGlowshroomFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SMALL_TOADSTOOL = new SmallToadstoolFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> HUGE_GLOWSHROOM = new HugeGlowshroomFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> HUGE_TOADSTOOL = new HugeToadstoolFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> SMALL_CRYSTAL = new SmallCrystalFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> LARGE_CRYSTAL = new LargeCrystalFeature(NoFeatureConfig.CODEC);
	public static final Feature<NoFeatureConfig> FLESH_TENDON = new FleshTendonFeature(NoFeatureConfig.CODEC);

	//Flowers
	public static final FlowersFeature CHAPARRAL_FLOWERS = new ChaparralFlowersFeature();
	public static final FlowersFeature CHERRY_BLOSSOM_GROVE_FLOWERS = new CherryBlossomGroveFlowersFeature();
	public static final FlowersFeature CONIFEROUS_FOREST_FLOWERS = new ConiferousForestFlowersFeature();
	public static final FlowersFeature EXTENDED_FLOWERS = new ExtendedFlowersFeature();
	public static final FlowersFeature FLOWER_MEADOW_FLOWERS = new FlowerMeadowFlowersFeature();
	public static final FlowersFeature JUNGLE_FLOWERS = new JungleFlowersFeature();
	public static final FlowersFeature LAVENDER_FLOWERS = new LavenderFlowersFeature();
	public static final FlowersFeature LUSH_GRASSLAND_FLOWERS = new LushGrasslandFlowersFeature();
	public static final FlowersFeature LUSH_SWAMP_FLOWERS = new LushSwampFlowersFeature();
	public static final FlowersFeature MEADOW_FLOWERS = new MeadowFlowersFeature();
	public static final FlowersFeature MOOR_FLOWERS = new MoorFlowersFeature();
	public static final FlowersFeature MYSTIC_GROVE_FLOWERS = new MysticGroveFlowersFeature();
	public static final FlowersFeature OMINOUS_WOODS_FLOWERS = new OminousWoodsFlowersFeature();
	public static final FlowersFeature ORIGIN_FLOWERS = new OriginFlowersFeature();
	public static final FlowersFeature RAINBOW_VALLEY_FLOWERS = new RainbowValleyFlowersFeature();
	public static final FlowersFeature RAINFOREST_FLOWERS = new RainforestFlowersFeature();
	public static final FlowersFeature SHRUBLAND_FLOWERS = new ShrublandFlowersFeature();
	public static final FlowersFeature SNOWY_FLOWERS = new SnowyFlowersFeature();
	public static final FlowersFeature TROPICS_FLOWERS = new TropicsFlowersFeature();
	public static final FlowersFeature WASTELAND_FLOWERS = new WastelandFlowersFeature();
	public static final FlowersFeature WETLAND_FLOWERS = new WetlandFlowersFeature();
	
	//Surfaces
	public static final SurfaceBuilder<SurfaceBuilderConfig> BRUSHLAND_SURFACE_BUILDER = new BrushlandSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> CHAPARRAL_SURFACE_BUILDER = new ChaparralSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> COLD_DESERT_SURFACE_BUILDER = new ColdDesertSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> TERRACOTTA_SURFACE_BUILDER = new TerracottaSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MARSH_SURFACE_BUILDER = new MarshSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MANGROVE_SURFACE_BUILDER = new MangroveSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> MUD_SURFACE_BUILDER = new MudSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> PODZOL_SURFACE_BUILDER = new PodzolSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO_SURFACE_BUILDER = new VolcanoSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> VOLCANO_EDGE_SURFACE_BUILDER = new VolcanoEdgeSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> DEEP_TOP_LAYER = new DeepTopLayerSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> POPPY_FIELD_SURFACE_BUILDER = new PoppyFieldSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> WITHERED_ABYSS_SURFACE_BUILDER = new WitheredAbyssSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> FLESH_SURFACE_BUILDER = new FleshSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> ORIGIN_HILLS_SURFACE_BUILDER = new OriginHillsSurfaceBuilder(SurfaceBuilderConfig.CODEC);
	public static final SurfaceBuilder<SurfaceBuilderConfig> TROPICS_SURFACE_BUILDER = new TropicsSurfaceBuilder(SurfaceBuilderConfig.CODEC);

	public static final SurfaceBuilderConfig BLACKSTONE_SURFACE = new SurfaceBuilderConfig(Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState(), Blocks.BLACKSTONE.defaultBlockState());
	public static final SurfaceBuilderConfig BASALT_SURFACE = new SurfaceBuilderConfig(Blocks.BASALT.defaultBlockState(), Blocks.BASALT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
	public static final SurfaceBuilderConfig TERRACOTTA_SURFACE = new SurfaceBuilderConfig(Blocks.TERRACOTTA.defaultBlockState(), Blocks.TERRACOTTA.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
	public static final SurfaceBuilderConfig MAGMA_SURFACE = new SurfaceBuilderConfig(Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.MAGMA_BLOCK.defaultBlockState(), Blocks.BASALT.defaultBlockState());
	public static final SurfaceBuilderConfig MUD_SURFACE = new SurfaceBuilderConfig(BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState(), BOPBlocks.mud.defaultBlockState());
	public static final SurfaceBuilderConfig RED_SAND_SURFACE = new SurfaceBuilderConfig(Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState(), Blocks.RED_SAND.defaultBlockState());
	public static final SurfaceBuilderConfig SNOW_SNOW_GRAVEL_SURFACE = new SurfaceBuilderConfig(Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.SNOW_BLOCK.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());
	public static final SurfaceBuilderConfig WHITE_SAND_SURFACE = new SurfaceBuilderConfig(BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState(), BOPBlocks.white_sand.defaultBlockState());
	public static final SurfaceBuilderConfig DRIED_SALT_SURFACE = new SurfaceBuilderConfig(BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState(), BOPBlocks.dried_salt.defaultBlockState());
	public static final SurfaceBuilderConfig ORIGIN_GRASS_SURFACE = new SurfaceBuilderConfig(BOPBlocks.origin_grass_block.defaultBlockState(), Blocks.DIRT.defaultBlockState(), Blocks.GRAVEL.defaultBlockState());

	//Vanilla Biomes Features
	public static final FlowersFeature VIOLET_FEATURE = new VioletFeature();
	public static final FlowersFeature ORANGE_COSMOS_FEATURE = new OrangeCosmosFeature();
	public static final FlowersFeature WILDFLOWER_FEATURE = new WildflowerFeature();
	public static final FlowersFeature POPPY_FEATURE = new PoppyFeature();

	//Carvers
	public static final WorldCarver<ProbabilityConfig> ORIGIN_CAVE = new OriginCaveWorldCarver(ProbabilityConfig.CODEC, 256);
}
