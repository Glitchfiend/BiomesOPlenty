/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.configurations.*;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.featuresize.TwoLayersFeatureSize;
import net.minecraft.world.level.levelgen.feature.foliageplacers.CherryFoliagePlacer;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;
import net.minecraft.world.level.levelgen.feature.trunkplacers.CherryTrunkPlacer;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.createKey;

public class BOPTreeFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_OAK_TREE = createKey("flowering_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_OAK_TREE_BEES = createKey("flowering_oak_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE = createKey("jacaranda_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JACARANDA_TREE_BEES = createKey("jacaranda_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TREE = createKey("maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORANGE_AUTUMN_TREE = createKey("orange_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORIGIN_TREE = createKey("origin_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> RAINBOW_BIRCH_TREE = createKey("rainbow_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_DEAD_TREE = createKey("small_dead_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SNOWBLOSSOM_TREE = createKey("snowblossom_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WILLOW_TREE = createKey("willow_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> YELLOW_AUTUMN_TREE = createKey("yellow_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_FLOWERING_OAK_TREE = createKey("big_flowering_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_HELLBARK_TREE = createKey("big_hellbark_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_JACARANDA_TREE = createKey("big_jacaranda_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_MAPLE_TREE = createKey("big_maple_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_OAK_TREE = createKey("big_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_ORANGE_AUTUMN_TREE = createKey("big_orange_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_ORIGIN_TREE = createKey("big_origin_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_RAINBOW_BIRCH_TREE = createKey("big_rainbow_birch_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_YELLOW_AUTUMN_TREE = createKey("big_yellow_autumn_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DYING_TREE = createKey("dying_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DYING_TREE_WASTELAND = createKey("dying_tree_wasteland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_TREE = createKey("giant_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPARSE_ACACIA_TREE = createKey("sparse_acacia_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPARSE_OAK_TREE = createKey("sparse_oak_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE = createKey("fir_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE_LARGE = createKey("fir_tree_large");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FIR_TREE_SMALL = createKey("fir_tree_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SPRUCE_TREE = createKey("tall_spruce_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_SPRUCE_TREE_BEES = createKey("tall_spruce_tree_bees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_UMBRAN_TREE = createKey("tall_umbran_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UMBRAN_TREE = createKey("umbran_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BIG_MAGIC_TREE = createKey("big_magic_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DARK_OAK_POPLAR_TREE = createKey("dark_oak_poplar_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAGIC_TREE = createKey("magic_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_POPLAR_TREE = createKey("spruce_poplar_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE = createKey("cypress_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CYPRESS_TREE_MEDIUM = createKey("cypress_tree_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_BUSH_TREE = createKey("acacia_bush_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWERING_OAK_BUSH = createKey("flowering_oak_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_BUSH = createKey("jungle_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OAK_BUSH = createKey("oak_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_BUSH = createKey("spruce_bush");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_TWIGLET = createKey("acacia_twiglet");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ACACIA_TWIGLET_SMALL = createKey("acacia_twiglet_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TREE_WASTELAND = createKey("dead_tree_wasteland");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TWIGLET_TREE = createKey("dead_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_TWIGLET_TREE_SMALL = createKey("dead_twiglet_tree_small");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HELLBARK_TREE = createKey("hellbark_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> JUNGLE_TWIGLET_TREE = createKey("jungle_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MANGROVE_TWIGLET_TREE = createKey("mangrove_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAPLE_TWIGLET_TREE = createKey("maple_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRUCE_TWIGLET_TREE = createKey("spruce_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_DEAD_TWIGLET_TREE = createKey("tall_dead_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TALL_TWIGLET_TREE = createKey("tall_twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TWIGLET_TREE = createKey("twiglet_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TWIGLET_TREE_VOLCANO = createKey("twiglet_tree_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE = createKey("redwood_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE_MEDIUM = createKey("redwood_tree_medium");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MAHOGANY_TREE = createKey("mahogany_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PALM_TREE = createKey("palm_tree");
    public static final ResourceKey<ConfiguredFeature<?, ?>> REDWOOD_TREE_LARGE = createKey("redwood_tree_large");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, BOPTreeFeatures.FLOWERING_OAK_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES.get())).build());
        register(context, BOPTreeFeatures.FLOWERING_OAK_TREE_BEES, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES.get())).decorator(new BeehiveDecorator(0.05f)).build());
        register(context, BOPTreeFeatures.JACARANDA_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES.get())).build());
        register(context, BOPTreeFeatures.JACARANDA_TREE_BEES, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES.get())).decorator(new BeehiveDecorator(0.02f)).build());
        register(context, BOPTreeFeatures.MAPLE_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES.get())).minHeight(5).maxHeight(10).build());
        register(context, BOPTreeFeatures.ORANGE_AUTUMN_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORANGE_AUTUMN_LEAVES.get())).minHeight(5).maxHeight(8).build());
        register(context, BOPTreeFeatures.ORIGIN_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORIGIN_LEAVES.get())).minHeight(5).maxHeight(8).build());
        register(context, BOPTreeFeatures.RAINBOW_BIRCH_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.RAINBOW_BIRCH_LEAVES.get())).build());
        register(context, BOPTreeFeatures.SMALL_DEAD_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).build());
        register(context, BOPTreeFeatures.WILLOW_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.WILLOW_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.WILLOW_LEAVES.get())).vine(BlockStateProvider.simple(BOPBlocks.WILLOW_VINE.get())).minHeight(6).maxHeight(10).leavesOffset(0).maxLeavesRadius(2).build());
        register(context, BOPTreeFeatures.YELLOW_AUTUMN_TREE, BOPBaseFeatures.BASIC_TREE, new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.YELLOW_AUTUMN_LEAVES.get())).minHeight(5).maxHeight(8).build());
        register(context, BOPTreeFeatures.BIG_FLOWERING_OAK_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_HELLBARK_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.HELLBARK_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.HELLBARK_LEAVES.get())).maxHeight(10).foliageHeight(2).build());
        register(context, BOPTreeFeatures.BIG_JACARANDA_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_MAPLE_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_OAK_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().build());
        register(context, BOPTreeFeatures.BIG_ORANGE_AUTUMN_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORANGE_AUTUMN_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_ORIGIN_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORIGIN_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_RAINBOW_BIRCH_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.RAINBOW_BIRCH_LEAVES.get())).build());
        register(context, BOPTreeFeatures.BIG_YELLOW_AUTUMN_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.YELLOW_AUTUMN_LEAVES.get())).build());
        register(context, BOPTreeFeatures.DYING_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).maxHeight(10).foliageHeight(2).build());
        register(context, BOPTreeFeatures.DYING_TREE_WASTELAND, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).maxHeight(10).foliageHeight(1).build());
        register(context, BOPTreeFeatures.GIANT_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true))).minHeight(15).maxHeight(20).trunkWidth(4).build());
        register(context, BOPTreeFeatures.SPARSE_ACACIA_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).maxHeight(8).foliageHeight(1).build());
        register(context, BOPTreeFeatures.SPARSE_OAK_TREE, BOPBaseFeatures.BIG_TREE, new BigTreeConfiguration.Builder().maxHeight(10).foliageHeight(2).build());
        register(context, BOPTreeFeatures.FIR_TREE, BOPBaseFeatures.TAIGA_TREE, createFir().minHeight(5).maxHeight(28).build());
        register(context, BOPTreeFeatures.FIR_TREE_LARGE, BOPBaseFeatures.TAIGA_TREE, createFir().minHeight(20).maxHeight(40).trunkWidth(2).build());
        register(context, BOPTreeFeatures.FIR_TREE_SMALL, BOPBaseFeatures.TAIGA_TREE, createFir().minHeight(5).maxHeight(11).build());
        register(context, BOPTreeFeatures.TALL_SPRUCE_TREE, BOPBaseFeatures.TAIGA_TREE, new TaigaTreeConfiguration.Builder().maxHeight(13).build());
        register(context, BOPTreeFeatures.TALL_SPRUCE_TREE_BEES, BOPBaseFeatures.TAIGA_TREE, new TaigaTreeConfiguration.Builder().maxHeight(13).decorator(new BeehiveDecorator(0.05f)).build());
        register(context, BOPTreeFeatures.TALL_UMBRAN_TREE, BOPBaseFeatures.TAIGA_TREE, new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.UMBRAN_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.UMBRAN_LEAVES.get())).minHeight(20).maxHeight(30).trunkWidth(2).build());
        register(context, BOPTreeFeatures.UMBRAN_TREE, BOPBaseFeatures.TAIGA_TREE, new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.UMBRAN_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.UMBRAN_LEAVES.get())).maxHeight(20).build());
        register(context, BOPTreeFeatures.BIG_MAGIC_TREE, BOPBaseFeatures.POPLAR_TREE, new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.MAGIC_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.MAGIC_LEAVES.get())).minHeight(16).maxHeight(20).build());
        register(context, BOPTreeFeatures.DARK_OAK_POPLAR_TREE, BOPBaseFeatures.POPLAR_TREE, new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES)).build());
        register(context, BOPTreeFeatures.MAGIC_TREE, BOPBaseFeatures.POPLAR_TREE, new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.MAGIC_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.MAGIC_LEAVES.get())).minHeight(8).maxHeight(12).build());
        register(context, BOPTreeFeatures.SPRUCE_POPLAR_TREE, BOPBaseFeatures.POPLAR_TREE, new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.SPRUCE_LOG)).foliage(BlockStateProvider.simple(Blocks.SPRUCE_LEAVES)).build());
        register(context, BOPTreeFeatures.CYPRESS_TREE, BOPBaseFeatures.CYPRESS_TREE, new CypressTreeConfiguration.Builder().build());
        register(context, BOPTreeFeatures.CYPRESS_TREE_MEDIUM, BOPBaseFeatures.CYPRESS_TREE, new CypressTreeConfiguration.Builder().minHeight(18).maxHeight(25).trunkWidth(2).build());
        register(context, BOPTreeFeatures.ACACIA_BUSH_TREE, BOPBaseFeatures.BUSH_TREE, new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).build());
        register(context, BOPTreeFeatures.FLOWERING_OAK_BUSH, BOPBaseFeatures.BUSH_TREE, new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES.get())).build());
        register(context, BOPTreeFeatures.JUNGLE_BUSH, BOPBaseFeatures.BUSH_TREE, new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.JUNGLE_LOG)).foliage(BlockStateProvider.simple(Blocks.JUNGLE_LEAVES)).build());
        register(context, BOPTreeFeatures.OAK_BUSH, BOPBaseFeatures.BUSH_TREE, new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).build());
        register(context, BOPTreeFeatures.SPRUCE_BUSH, BOPBaseFeatures.BUSH_TREE, new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.SPRUCE_LOG)).foliage(BlockStateProvider.simple(Blocks.SPRUCE_LEAVES)).build());
        register(context, BOPTreeFeatures.ACACIA_TWIGLET, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).build());
        register(context, BOPTreeFeatures.ACACIA_TWIGLET_SMALL, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.DEAD_TREE_WASTELAND, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH.get())).leafChance(0.0F, 0.0F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(Blocks.AIR)).minHeight(6).maxHeight(10).build());
        register(context, BOPTreeFeatures.DEAD_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH.get())).leafChance(0.05F, 0.25F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).minHeight(6).maxHeight(10).build());
        register(context, BOPTreeFeatures.DEAD_TWIGLET_TREE_SMALL, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.HELLBARK_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.HELLBARK_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.HELLBARK_LEAVES.get())).minHeight(3).maxHeight(7).leafChance(0.75F,1.0F).build());
        register(context, BOPTreeFeatures.JUNGLE_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.JUNGLE_LOG)).foliage(BlockStateProvider.simple(Blocks.JUNGLE_LEAVES)).trunkFruit(BlockStateProvider.simple(Blocks.COCOA)).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.MANGROVE_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.MANGROVE_LOG)).foliage(BlockStateProvider.simple(Blocks.MANGROVE_LEAVES)).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.MAPLE_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES.get())).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.SPRUCE_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.SPRUCE_LOG)).foliage(BlockStateProvider.simple(Blocks.SPRUCE_LEAVES)).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.TALL_DEAD_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH.get())).leafChance(0.15F, 0.6F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES.get())).minHeight(12).maxHeight(18).build());
        register(context, BOPTreeFeatures.TALL_TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().minHeight(2).maxHeight(4).build());
        register(context, BOPTreeFeatures.TWIGLET_TREE, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.TWIGLET_TREE_VOLCANO, BOPBaseFeatures.TWIGLET_TREE, new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES)).minHeight(1).maxHeight(2).build());
        register(context, BOPTreeFeatures.REDWOOD_TREE, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_LOG.get()).minHeight(10).maxHeight(30).build());
        register(context, BOPTreeFeatures.REDWOOD_TREE_MEDIUM, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_WOOD.get()).minHeight(25).maxHeight(40).trunkWidth(2).build());
        register(context, BOPTreeFeatures.MAHOGANY_TREE, BOPBaseFeatures.MAHOGANY_TREE, new MahoganyTreeConfiguration.Builder().build());
        register(context, BOPTreeFeatures.PALM_TREE, BOPBaseFeatures.PALM_TREE, new PalmTreeConfiguration.Builder().build());
        register(context, BOPTreeFeatures.SNOWBLOSSOM_TREE, Feature.TREE, snowblossom().build());
        register(context, BOPTreeFeatures.REDWOOD_TREE_LARGE, BOPBaseFeatures.REDWOOD_TREE, createRedwood(BOPBlocks.REDWOOD_WOOD.get()).minHeight(45).maxHeight(60).trunkWidth(3).build());
    }

    private static TreeConfiguration.TreeConfigurationBuilder snowblossom() {
        return (new TreeConfiguration.TreeConfigurationBuilder(BlockStateProvider.simple(Blocks.CHERRY_LOG), new CherryTrunkPlacer(7, 1, 0, new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(ConstantInt.of(1), 1).add(ConstantInt.of(2), 1).add(ConstantInt.of(3), 1).build()), UniformInt.of(2, 4), UniformInt.of(-4, -3), UniformInt.of(-1, 0)), BlockStateProvider.simple(BOPBlocks.SNOWBLOSSOM_LEAVES.get()), new CherryFoliagePlacer(ConstantInt.of(4), ConstantInt.of(0), ConstantInt.of(5), 0.25F, 0.5F, 0.16666667F, 0.33333334F), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    }

    private static TaigaTreeConfiguration.Builder createFir()
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.FIR_LOG.get())).foliage(BlockStateProvider.simple(BOPBlocks.FIR_LEAVES.get()));
    }

    private static TaigaTreeConfiguration.Builder createRedwood(Block log)
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(log)).foliage(BlockStateProvider.simple(BOPBlocks.REDWOOD_LEAVES.get()));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
