/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import biomesoplenty.worldgen.placement.BOPTreePlacements;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.RandomFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANOMALY = BOPFeatureUtils.createKey("anomaly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDERPHYTE_BONEMEAL = BOPFeatureUtils.createKey("enderphyte_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_END_WILDS = BOPFeatureUtils.createKey("flower_end_wilds");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NULL_LAKE = BOPFeatureUtils.createKey("null_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NULL_TREES = BOPFeatureUtils.createKey("null_trees");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ENDERPHYTES = BOPFeatureUtils.createKey("patch_enderphytes");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        final Holder<PlacedFeature> NULL_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.NULL_TREE_CHECKED);

        register(context, BOPEndFeatures.ANOMALY, BOPBaseFeatures.ANOMALY, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPEndFeatures.ENDERPHYTE_BONEMEAL, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ENDERPHYTE.defaultBlockState())));
        register(context, BOPEndFeatures.FLOWER_END_WILDS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ALGAE_BLOOM))));
        register(context, BOPEndFeatures.NULL_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(BOPBlocks.NULL_BLOCK), BlockStateProvider.simple(BOPBlocks.NULL_END_STONE)));
        register(context, BOPEndFeatures.NULL_TREES, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(NULL_TREE_CHECKED, 0.075F)), NULL_TREE_CHECKED));
        register(context, BOPEndFeatures.PATCH_ENDERPHYTES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ENDERPHYTE))));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
