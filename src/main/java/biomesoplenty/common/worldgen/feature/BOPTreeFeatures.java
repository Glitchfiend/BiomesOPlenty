/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.configurations.*;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;

public class BOPTreeFeatures
{
    // Standard trees
    public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_TREE = register("flowering_oak_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_TREE_BEES = register("flowering_oak_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> JACARANDA_TREE_BEES = register("jacaranda_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES)).decorator(new BeehiveDecorator(0.02f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MAPLE_TREE = register("maple_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES)).minHeight(5).maxHeight(10).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> ORANGE_AUTUMN_TREE = register("orange_autumn_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORANGE_AUTUMN_LEAVES)).minHeight(5).maxHeight(8).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> PINK_CHERRY_TREE_BEES = register("pink_cherry_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.PINK_CHERRY_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SMALL_DEAD_TREE = register("small_dead_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> WHITE_CHERRY_TREE_BEES = register("white_cherry_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.WHITE_CHERRY_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> WILLOW_TREE = register("willow_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.WILLOW_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.WILLOW_LEAVES)).vine(BlockStateProvider.simple(BOPBlocks.WILLOW_VINE)).minHeight(6).maxHeight(10).leavesOffset(0).maxLeavesRadius(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> YELLOW_AUTUMN_TREE = register("yellow_autumn_tree", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.YELLOW_AUTUMN_LEAVES)).minHeight(5).maxHeight(8).build()));

    // Big trees
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_FLOWERING_OAK_TREE = register("big_flowering_oak_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_JACARANDA_TREE = register("big_jacaranda_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_MAPLE_TREE = register("big_maple_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_OAK_TREE = register("big_oak_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_ORANGE_AUTUMN_TREE = register("big_orange_autumn_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.ORANGE_AUTUMN_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_PINK_CHERRY_TREE = register("big_pink_cherry_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.PINK_CHERRY_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_WHITE_CHERRY_TREE = register("big_white_cherry_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.WHITE_CHERRY_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_YELLOW_AUTUMN_TREE = register("big_yellow_autumn_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.BIRCH_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.YELLOW_AUTUMN_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> DYING_TREE = register("dying_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES)).maxHeight(10).foliageHeight(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> DYING_TREE_WASTELAND = register("dying_tree_wasteland", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES)).maxHeight(10).foliageHeight(1).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SPARSE_ACACIA_TREE = register("sparse_acacia_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).maxHeight(8).foliageHeight(1).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SPARSE_OAK_TREE = register("sparse_oak_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().maxHeight(10).foliageHeight(2).build()));

    // Conifer trees
    public static final ConfiguredFeature<TreeConfiguration, ?> FIR_TREE = register("fir_tree", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(5).maxHeight(28).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FIR_TREE_LARGE = register("fir_tree_large", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(20).maxHeight(40).trunkWidth(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FIR_TREE_SMALL = register("fir_tree_small", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(5).maxHeight(11).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_SPRUCE_TREE = register("tall_spruce_tree", BOPBaseFeatures.TAIGA_TREE.configured(new TaigaTreeConfiguration.Builder().maxHeight(13).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_SPRUCE_TREE_BEES = register("tall_spruce_tree_bees", BOPBaseFeatures.TAIGA_TREE.configured(new TaigaTreeConfiguration.Builder().maxHeight(13).decorator(new BeehiveDecorator(0.05f)).build()));

    // Poplar trees
    public static final ConfiguredFeature<TreeConfiguration, ?> DARK_OAK_POPLAR_TREE = register("dark_oak_poplar_tree", BOPBaseFeatures.POPLAR_TREE.configured(new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.DARK_OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.DARK_OAK_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SPRUCE_POPLAR_TREE = register("spruce_poplar_tree", BOPBaseFeatures.POPLAR_TREE.configured(new PoplarTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.SPRUCE_LOG)).foliage(BlockStateProvider.simple(Blocks.SPRUCE_LEAVES)).build()));

    // Swamp trees
    public static final ConfiguredFeature<TreeConfiguration, ?> CYPRESS_TREE = register("cypress_tree", BOPBaseFeatures.CYPRESS_TREE.configured(new CypressTreeConfiguration.Builder().build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> CYPRESS_TREE_MEDIUM = register("cypress_tree_medium", BOPBaseFeatures.CYPRESS_TREE.configured(new CypressTreeConfiguration.Builder().minHeight(18).maxHeight(25).trunkWidth(2).build()));

    // Bush trees
    public static final ConfiguredFeature<TreeConfiguration, ?> ACACIA_BUSH_TREE = register("acacia_bush_tree", BOPBaseFeatures.BUSH_TREE.configured(new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_BUSH = register("flowering_oak_bush", BOPBaseFeatures.BUSH_TREE.configured(new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> OAK_BUSH = register("bush", BOPBaseFeatures.BUSH_TREE.configured(new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).build()));

    // Twiglets
    public static final ConfiguredFeature<TreeConfiguration, ?> ACACIA_TWIGLET = register("acacia_twiglet", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> ACACIA_TWIGLET_SMALL = register("acacia_twiglet_small", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.ACACIA_LOG)).foliage(BlockStateProvider.simple(Blocks.ACACIA_LEAVES)).minHeight(1).maxHeight(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> DEAD_TREE_WASTELAND = register("dead_tree_wasteland", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH)).leafChance(0.0F, 0.0F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(Blocks.AIR)).minHeight(6).maxHeight(10).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> DEAD_TWIGLET_TREE = register("dead_twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH)).leafChance(0.05F, 0.25F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES)).minHeight(6).maxHeight(10).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MAPLE_TWIGLET_TREE = register("maple_twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().foliage(BlockStateProvider.simple(BOPBlocks.MAPLE_LEAVES)).minHeight(1).maxHeight(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> SPRUCE_TWIGLET_TREE = register("spruce_twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.SPRUCE_LOG)).foliage(BlockStateProvider.simple(Blocks.SPRUCE_LEAVES)).minHeight(1).maxHeight(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_DEAD_TWIGLET_TREE = register("tall_dead_twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().trunkFruit(BlockStateProvider.simple(BOPBlocks.DEAD_BRANCH)).leafChance(0.15F, 0.6F).trunk(BlockStateProvider.simple(BOPBlocks.DEAD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.DEAD_LEAVES)).minHeight(12).maxHeight(18).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TALL_TWIGLET_TREE = register("tall_twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().minHeight(2).maxHeight(4).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> TWIGLET_TREE = register("twiglet_tree", BOPBaseFeatures.TWIGLET_TREE.configured(new TwigletTreeConfiguration.Builder().minHeight(1).maxHeight(2).build()));

    // Special trees
    public static final ConfiguredFeature<TreeConfiguration, ?> REDWOOD_TREE_MEDIUM = register("redwood_tree_medium", BOPBaseFeatures.REDWOOD_TREE.configured(createRedwood().minHeight(25).maxHeight(40).trunkWidth(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> REDWOOD_TREE = register("redwood_tree", BOPBaseFeatures.REDWOOD_TREE.configured(createRedwood().minHeight(10).maxHeight(30).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> REDWOOD_TREE_LARGE = register("redwood_tree_large", BOPBaseFeatures.REDWOOD_TREE.configured(createRedwood().minHeight(45).maxHeight(60).trunkWidth(3).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> MAHOGANY_TREE = register("mahogany_tree", BOPBaseFeatures.MAHOGANY_TREE.configured(new MahoganyTreeConfiguration.Builder().build()));

    private static TaigaTreeConfiguration.Builder createFir()
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.FIR_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.FIR_LEAVES));
    }

    private static TaigaTreeConfiguration.Builder createRedwood()
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.REDWOOD_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.REDWOOD_LEAVES));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
