/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.configurations.BasicTreeConfiguration;
import biomesoplenty.common.worldgen.feature.configurations.BigTreeConfiguration;
import biomesoplenty.common.worldgen.feature.configurations.TaigaTreeConfiguration;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.treedecorators.BeehiveDecorator;

public class BOPTreeFeatures
{
    public static final ConfiguredFeature<TreeConfiguration, ?> FIR = register("fir", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(5).maxHeight(28).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> LARGE_FIR = register("large_fir", BOPBaseFeatures.TAIGA_TREE.configured(createFir().minHeight(20).maxHeight(40).trunkWidth(2).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> JACARANDA_TREE_BEES = register("jacaranda_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES)).decorator(new BeehiveDecorator(0.02f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> WHITE_CHERRY_TREE_BEES = register("white_cherry_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.WHITE_CHERRY_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> PINK_CHERRY_TREE_BEES = register("pink_cherry_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.PINK_CHERRY_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_TREE_BEES = register("flowering_oak_tree_bees", BOPBaseFeatures.BASIC_TREE.configured(new BasicTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).decorator(new BeehiveDecorator(0.05f)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> FLOWERING_OAK_BUSH = register("flowering_oak_bush", BOPBaseFeatures.BUSH_TREE.configured(new BasicTreeConfiguration.Builder().maxHeight(2).minHeight(2).trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).build()));

    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_JACARANDA_TREE = register("big_jacaranda_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.JACARANDA_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.JACARANDA_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_WHITE_CHERRY_TREE = register("big_white_cherry_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.WHITE_CHERRY_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_PINK_CHERRY_TREE = register("big_pink_cherry_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.CHERRY_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.PINK_CHERRY_LEAVES)).build()));
    public static final ConfiguredFeature<TreeConfiguration, ?> BIG_FLOWERING_TREE = register("big_flowering_tree", BOPBaseFeatures.BIG_TREE.configured(new BigTreeConfiguration.Builder().trunk(BlockStateProvider.simple(Blocks.OAK_LOG)).foliage(BlockStateProvider.simple(Blocks.OAK_LEAVES)).altFoliage(BlockStateProvider.simple(BOPBlocks.FLOWERING_OAK_LEAVES)).build()));

    private static TaigaTreeConfiguration.Builder createFir()
    {
        return new TaigaTreeConfiguration.Builder().trunk(BlockStateProvider.simple(BOPBlocks.FIR_LOG)).foliage(BlockStateProvider.simple(BOPBlocks.FIR_LEAVES));
    }

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
