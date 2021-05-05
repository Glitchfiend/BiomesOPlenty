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
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableSet;
import net.minecraft.block.Blocks;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.SaplingBlock;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Direction;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.blockplacer.SimpleBlockPlacer;
import net.minecraft.world.gen.blockstateprovider.WeightedBlockStateProvider;
import net.minecraft.world.gen.feature.*;
import net.minecraftforge.registries.ForgeRegistries;

public class BOPFeatures
{
	//Standard Trees
	public static final Feature<BaseTreeFeatureConfig> ORIGIN_TREE = register("origin_tree", new BasicTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create());
	public static final Feature<BaseTreeFeatureConfig> FLOWERING_OAK_TREE = register("flowering_oak_tree", new BasicTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> RAINBOW_BIRCH_TREE = register("rainbow_birch_tree", new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.rainbow_birch_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> YELLOW_AUTUMN_TREE = register("yellow_autumn_tree", new BasicTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.yellow_autumn_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create());
	public static final Feature<BaseTreeFeatureConfig> ORANGE_AUTUMN_TREE = register("orange_autumn_tree", new BasicTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(BOPBlocks.orange_autumn_leaves.defaultBlockState()).minHeight(5).maxHeight(8).create());
	public static final Feature<BaseTreeFeatureConfig> MAPLE_TREE = register("maple_tree", new BasicTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.defaultBlockState()).minHeight(5).maxHeight(10).create());
	public static final Feature<BaseTreeFeatureConfig> PINK_CHERRY_TREE = register("pink_cherry_tree", new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.pink_cherry_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> WHITE_CHERRY_TREE = register("white_cherry_tree", new BasicTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.white_cherry_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> JACARANDA_TREE = register("jacaranda_tree", new BasicTreeFeature.Builder().log(BOPBlocks.jacaranda_log.defaultBlockState()).leaves(BOPBlocks.jacaranda_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> SMALL_DEAD_TREE = register("small_dead_tree", new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> SILK_TREE = register("silk_tree", new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).altLeaves(Blocks.COBWEB.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> FULL_SILK_TREE = register("full_silk_tree", new BasicTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(Blocks.COBWEB.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> MAGIC_TREE = register("magic_tree", new PoplarTreeFeature.Builder().log(BOPBlocks.magic_log.defaultBlockState()).leaves(BOPBlocks.magic_leaves.defaultBlockState()).minHeight(8).maxHeight(12).create());

	//Big Trees
	public static final Feature<BaseTreeFeatureConfig> BIG_OAK_TREE = register("big_oak_tree", new BigTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> BIG_ORIGIN_TREE = register("big_origin_tree", new BigTreeFeature.Builder().leaves(BOPBlocks.origin_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", new BigTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_RAINBOW_BIRCH_TREE = register("big_rainbow_birch_tree", new BigTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.rainbow_birch_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_YELLOW_AUTUMN_TREE = register("big_yellow_autumn_tree", new BigTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(BOPBlocks.yellow_autumn_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_ORANGE_AUTUMN_TREE = register("big_orange_autumn_tree", new BigTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(BOPBlocks.orange_autumn_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_MAPLE_TREE = register("big_maple_tree", new BigTreeFeature.Builder().leaves(BOPBlocks.maple_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_PINK_CHERRY_TREE = register("big_pink_cherry_tree", new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.pink_cherry_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_WHITE_CHERRY_TREE = register("big_white_cherry_tree", new BigTreeFeature.Builder().log(BOPBlocks.cherry_log.defaultBlockState()).leaves(BOPBlocks.white_cherry_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_JACARANDA_TREE = register("big_jacaranda_tree", new BigTreeFeature.Builder().log(BOPBlocks.jacaranda_log.defaultBlockState()).leaves(BOPBlocks.jacaranda_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_MAGIC_TREE = register("big_magic_tree", new PoplarTreeFeature.Builder().log(BOPBlocks.magic_log.defaultBlockState()).leaves(BOPBlocks.magic_leaves.defaultBlockState()).minHeight(16).maxHeight(20).create());

	public static final Feature<BaseTreeFeatureConfig> GIANT_TREE = register("giant_tree", new BigTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true)).minHeight(12).maxHeight(18).trunkWidth(4).create());

	//Conifer Trees
	public static final Feature<BaseTreeFeatureConfig> TALL_SPRUCE_TREE = register("tall_spruce_tree", new TaigaTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).maxHeight(13).create());
	public static final Feature<BaseTreeFeatureConfig> ALPS_SPRUCE_TREE = register("alps_spruce_tree", new TaigaTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.STONE).log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).maxHeight(13).create());
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE_SMALL = register("fir_tree_small", new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(5).maxHeight(11).create());
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE = register("fir_tree", new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(5).maxHeight(28).create());
	public static final Feature<BaseTreeFeatureConfig> FIR_TREE_LARGE = register("fir_tree_large", new TaigaTreeFeature.Builder().log(BOPBlocks.fir_log.defaultBlockState()).leaves(BOPBlocks.fir_leaves.defaultBlockState()).minHeight(20).maxHeight(40).trunkWidth(2).create());
	public static final Feature<BaseTreeFeatureConfig> UMBRAN_TREE = register("umbran_tree", new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.defaultBlockState()).leaves(BOPBlocks.umbran_leaves.defaultBlockState()).maxHeight(20).create());
	public static final Feature<BaseTreeFeatureConfig> TALL_UMBRAN_TREE = register("tall_umbran_tree", new TaigaTreeFeature.Builder().log(BOPBlocks.umbran_log.defaultBlockState()).leaves(BOPBlocks.umbran_leaves.defaultBlockState()).minHeight(20).maxHeight(30).trunkWidth(2).create());

	//Poplar Trees
	public static final Feature<BaseTreeFeatureConfig> SPRUCE_POPLAR = register("spruce_poplar", new PoplarTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> BIRCH_POPLAR = register("birch_poplar", new PoplarTreeFeature.Builder().log(Blocks.BIRCH_LOG.defaultBlockState()).leaves(Blocks.BIRCH_LEAVES.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> DARK_OAK_POPLAR = register("dark_oak_poplar", new PoplarTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState()).create());

	//Swamp Trees
	public static final Feature<BaseTreeFeatureConfig> CYPRESS_TREE = register("cypress_tree", new CypressTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> CYPRESS_TREE_MEDIUM = register("cypress_tree_medium", new CypressTreeFeature.Builder().minHeight(15).maxHeight(25).trunkWidth(2).create());
	public static final Feature<BaseTreeFeatureConfig> TALL_SWAMP_TREE = register("tall_swamp_tree", new BasicTreeFeature.Builder().vine(Blocks.VINE.defaultBlockState()).minHeight(8).maxHeight(12).maxLeavesRadius(2).leavesOffset(0).create());
	public static final Feature<BaseTreeFeatureConfig> WILLOW_TREE = register("willow_tree", new BasicTreeFeature.Builder().log(BOPBlocks.willow_log.defaultBlockState()).leaves(BOPBlocks.willow_leaves.defaultBlockState()).vine(BOPBlocks.willow_vine.defaultBlockState()).minHeight(6).maxHeight(10).maxLeavesRadius(2).leavesOffset(0).create());

	//Sparse Trees
	public static final Feature<BaseTreeFeatureConfig> SPARSE_ACACIA_TREE = register("sparse_acacia_tree", new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.orange_sand).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).maxHeight(8).foliageHeight(1).create());
	public static final Feature<BaseTreeFeatureConfig> SPARSE_OAK_TREE = register("sparse_oak_tree", new BigTreeFeature.Builder().maxHeight(10).foliageHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> DYING_TREE = register("dying_tree", new BigTreeFeature.Builder().log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).maxHeight(10).foliageHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> DYING_TREE_WASTELAND = register("dying_tree_wasteland", new BigTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_salt).log(BOPBlocks.dead_log.defaultBlockState()).leaves(BOPBlocks.dead_leaves.defaultBlockState()).maxHeight(10).foliageHeight(1).create());

	//Bushes/Twiglets
	public static final Feature<BaseTreeFeatureConfig> BUSH = register("bush", new BushTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> SPRUCE_BUSH = register("spruce_bush", new BushTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> ACACIA_BUSH = register("acacia_bush", new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.orange_sand).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> FLOWERING_BUSH = register("flowering_bush", new BushTreeFeature.Builder().altLeaves(BOPBlocks.flowering_oak_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> COBWEB_BUSH = register("cobweb_bush", new BushTreeFeature.Builder().altLeaves(Blocks.COBWEB.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> FULL_COBWEB_BUSH = register("full_cobweb_bush", new BushTreeFeature.Builder().leaves(Blocks.COBWEB.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> HELLBARK_TREE = register("hellbark_tree", new BushTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK || world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING)).log(BOPBlocks.hellbark_log.defaultBlockState()).leaves(BOPBlocks.hellbark_leaves.defaultBlockState()).create());

	public static final Feature<BaseTreeFeatureConfig> TWIGLET_TREE = register("twiglet_tree", new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> TALL_TWIGLET_TREE = register("tall_twiglet_tree", new TwigletTreeFeature.Builder().minHeight(2).maxHeight(4).create());
	public static final Feature<BaseTreeFeatureConfig> SPRUCE_TWIGLET_TREE = register("spruce_twiglet_tree", new TwigletTreeFeature.Builder().log(Blocks.SPRUCE_LOG.defaultBlockState()).leaves(Blocks.SPRUCE_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> JUNGLE_TWIGLET_TREE = register("jungle_twiglet_tree", new TwigletTreeFeature.Builder().log(Blocks.JUNGLE_LOG.defaultBlockState()).leaves(Blocks.JUNGLE_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).trunkFruit(Blocks.COCOA.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> DARK_OAK_TWIGLET_TREE = register("dark_oak_twiglet_tree", new TwigletTreeFeature.Builder().log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET_TREE = register("acacia_twiglet_tree", new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.RED_SAND).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET_SMALL = register("acacia_twiglet_small", new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.SAND || world.getBlockState(pos).getBlock() == BOPBlocks.orange_sand).log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> ACACIA_TWIGLET = register("acacia_twiglet", new TwigletTreeFeature.Builder().log(Blocks.ACACIA_LOG.defaultBlockState()).leaves(Blocks.ACACIA_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> MAPLE_TWIGLET_TREE = register("maple_twiglet_tree", new TwigletTreeFeature.Builder().minHeight(1).maxHeight(2).leaves(BOPBlocks.maple_leaves.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE_SMALL = register("dead_twiglet_tree_small", new TwigletTreeFeature.Builder().minHeight(1).maxHeight(1).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).create());
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE = register("dead_twiglet_tree", new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.05F, 0.25F).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(4).maxHeight(7).create());
	public static final Feature<BaseTreeFeatureConfig> DEAD_TWIGLET_TREE_TALL = register("dead_twiglet_tree_tall", new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.15F, 0.6F).leaves(BOPBlocks.dead_leaves.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(8).maxHeight(12).create());
	public static final Feature<BaseTreeFeatureConfig> TWIGLET_TREE_VOLCANO = register("twiglet_tree_volcano", new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.black_sand).log(Blocks.DARK_OAK_LOG.defaultBlockState()).leaves(Blocks.DARK_OAK_LEAVES.defaultBlockState()).minHeight(1).maxHeight(2).create());
	public static final Feature<BaseTreeFeatureConfig> BIG_HELLBARK_TREE = register("big_hellbark_tree", new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == Blocks.NETHERRACK || world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING)).log(BOPBlocks.hellbark_log.defaultBlockState()).leaves(BOPBlocks.hellbark_leaves.defaultBlockState()).minHeight(3).maxHeight(7).create());

	//Special Trees
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE = register("redwood_tree", new RedwoodTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE_MEDIUM = register("redwood_tree_medium", new RedwoodTreeFeature.Builder().minHeight(25).maxHeight(40).trunkWidth(2).create());
	public static final Feature<BaseTreeFeatureConfig> REDWOOD_TREE_LARGE = register("redwood_tree_large", new RedwoodTreeFeature.Builder().minHeight(45).maxHeight(60).trunkWidth(3).create());
	public static final Feature<BaseTreeFeatureConfig> MAHOGANY_TREE = register("mahogany_tree", new MahoganyTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> PALM_TREE = register("palm_tree", new PalmTreeFeature.Builder().create());
	public static final Feature<BaseTreeFeatureConfig> DEAD_TREE = register("dead_tree", new TwigletTreeFeature.Builder().trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(6).maxHeight(10).create());
	public static final Feature<BaseTreeFeatureConfig> DEAD_TREE_WASTELAND = register("dead_tree_wasteland", new TwigletTreeFeature.Builder().placeOn((world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.dried_salt).trunkFruit(BOPBlocks.dead_branch.defaultBlockState()).leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.dead_log.defaultBlockState()).minHeight(6).maxHeight(10).create());
	public static final Feature<BaseTreeFeatureConfig> BURNT_TREE = register("burnt_tree", new TwigletTreeFeature.Builder().leafChance(0.0F, 0.0F).leaves(Blocks.AIR.defaultBlockState()).log(BOPBlocks.stripped_hellbark_log.defaultBlockState()).minHeight(4).maxHeight(8).create());

	/////////////////////////////////////////////////////////////////////////////////

	//Features
	public static final Feature<NoFeatureConfig> BIG_PUMPKIN = register("big_pumpkin", new BigPumpkinFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> BLACK_SAND_SPLATTER = register("black_sand_splatter", new BlackSandSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> BONE_SPINE = register("bone_spine", new BoneSpineFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> BRAMBLE = register("bramble", new BrambleFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> CRAG_SPLATTER = register("crag_splatter", new CragSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> DEEP_BAYOU_VINES = register("deep_bayou_vines", new DeepBayouVinesFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> FERN = register("fern", new FernFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> FERN_GRASS = register("fern_grass", new FernGrassFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> FLESH_TENDON = register("flesh_tendon", new FleshTendonFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> GRASS_SPLATTER = register("grass_splatter", new GrassSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> HUGE_GLOWSHROOM = register("huge_glowshroom", new HugeGlowshroomFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> HUGE_TOADSTOOL = register("huge_toadstool", new HugeToadstoolFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> LARGE_CRYSTAL = register("large_crystal", new LargeCrystalFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> MANGROVE = register("mangrove", new MangroveFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> MYCELIUM_SPLATTER = register("mycelium_splatter", new MyceliumSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> NETHER_VINES = register("nether_vines", new NetherVinesFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> OBSIDIAN_SPLATTER = register("obsidian_splatter", new ObsidianSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> RAINFOREST_CLIFFS_VINES = register("rainforest_cliffs_vines", new RainforestCliffsVinesFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> PODZOL_SPLATTER = register("podzol_splatter", new PodzolSplatterFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> PUMPKIN_PATCH = register("pumpkin_patch", new PumpkinPatchFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<BlockClusterFeatureConfig> RANDOM_PATCH_ABOVE_GROUND = register("random_patch_above_ground", new RandomPatchAboveGroundFeature(BlockClusterFeatureConfig.CODEC));
	public static final Feature<NoFeatureConfig> SCATTERED_ROCKS = register("scattered_rocks", new ScatteredRocksFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SCRUB = register("scrub", new ScrubFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SHORT_BAMBOO = register("short_bamboo", new ShortBambooFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SMALL_BROWN_MUSHROOM = register("small_brown_mushroom", new SmallBrownMushroomFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SMALL_CRYSTAL = register("small_crystal", new SmallCrystalFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SMALL_GLOWSHROOM = register("small_glowshroom", new SmallGlowshroomFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SMALL_RED_MUSHROOM = register("small_red_mushroom", new SmallRedMushroomFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> SMALL_TOADSTOOL = register("small_toadstool", new SmallToadstoolFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> STANDARD_GRASS = register("standard_grass", new StandardGrassFeature(NoFeatureConfig.CODEC.stable()));
	public static final Feature<NoFeatureConfig> WASTELAND_GRASS = register("wasteland_grass", new WastelandGrassFeature(NoFeatureConfig.CODEC.stable()));

	//Flowers
	public static final BlockClusterFeatureConfig CHERRY_BLOSSOM_GROVE_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.pink_daffodil.defaultBlockState(), 1).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig CONIFEROUS_FOREST_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.CORNFLOWER.defaultBlockState(), 1).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig EXTENDED_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig FLOWER_MEADOW_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.PINK_TULIP.defaultBlockState(), 1).add(Blocks.RED_TULIP.defaultBlockState(), 1).add(Blocks.WHITE_TULIP.defaultBlockState(), 1).add(Blocks.ORANGE_TULIP.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig MEADOW_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig MOOR_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.ALLIUM.defaultBlockState(), 1).add(BOPBlocks.violet.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig MYSTIC_GROVE_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.glowflower.defaultBlockState(), 1).add(BOPBlocks.pink_daffodil.defaultBlockState(), 1).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.ALLIUM.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig ORIGIN_VALLEY_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.rose.defaultBlockState(), 6).add(Blocks.DANDELION.defaultBlockState(), 4), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig RAINBOW_HILLS_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.orange_cosmos.defaultBlockState(), 1).add(BOPBlocks.pink_daffodil.defaultBlockState(), 1).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1).add(Blocks.AZURE_BLUET.defaultBlockState(), 1).add(Blocks.ALLIUM.defaultBlockState(), 1).add(Blocks.CORNFLOWER.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1).add(Blocks.BLUE_ORCHID.defaultBlockState(), 1).add(Blocks.PINK_TULIP.defaultBlockState(), 1).add(Blocks.RED_TULIP.defaultBlockState(), 1).add(Blocks.WHITE_TULIP.defaultBlockState(), 1).add(Blocks.ORANGE_TULIP.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig RAINFOREST_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.orange_cosmos.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig SNOWY_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.violet.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig TROPICS_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(BOPBlocks.pink_hibiscus.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();
	public static final BlockClusterFeatureConfig WETLAND_FLOWER_CONFIG = (new BlockClusterFeatureConfig.Builder((new WeightedBlockStateProvider()).add(Blocks.BLUE_ORCHID.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1), SimpleBlockPlacer.INSTANCE)).tries(64).build();

	//Other
	public static final LiquidsConfig VOLCANO_SPRING_CONFIG = new LiquidsConfig(Fluids.LAVA.defaultFluidState(), true, 4, 1, ImmutableSet.of(Blocks.BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.black_sand, BOPBlocks.black_sandstone, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE));
	public static final LiquidsConfig WATER_SPRING_EXTRA_CONFIG = new LiquidsConfig(Fluids.WATER.defaultFluidState(), true, 4, 1, ImmutableSet.of(Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.orange_sandstone));

	private static <C extends IFeatureConfig, F extends Feature<C>> F register(String key, F value)
	{
		value.setRegistryName(new ResourceLocation(BiomesOPlenty.MOD_ID, key));
		ForgeRegistries.FEATURES.register(value);
		return value;
	}
}
