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
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPEndFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> ANOMALY = BOPFeatureUtils.createKey("anomaly");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BARNACLES = BOPFeatureUtils.createKey("barnacles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_CORAL = BOPFeatureUtils.createKey("dead_coral");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ENDERPHYTE_BONEMEAL = BOPFeatureUtils.createKey("enderphyte_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLOWER_END_WILDS = BOPFeatureUtils.createKey("flower_end_wilds");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NULL_LAKE = BOPFeatureUtils.createKey("null_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PATCH_ENDERPHYTES = BOPFeatureUtils.createKey("patch_enderphytes");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_END_CORRUPTION = BOPFeatureUtils.createKey("trees_end_corruption");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_END_WILDS = BOPFeatureUtils.createKey("trees_end_wilds");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);
        final Holder<PlacedFeature> EMPYREAL_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.EMPYREAL_TREE_CHECKED);
        final Holder<PlacedFeature> NULL_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.NULL_TREE_CHECKED);

        register(context, BOPEndFeatures.ANOMALY, BOPBaseFeatures.ANOMALY, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPEndFeatures.BARNACLES, BOPBaseFeatures.BARNACLES, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPEndFeatures.DEAD_CORAL, Feature.SIMPLE_RANDOM_SELECTOR, new SimpleRandomFeatureConfiguration(HolderSet.direct(PlacementUtils.inlinePlaced(BOPBaseFeatures.DEAD_CORAL_TREE, FeatureConfiguration.NONE), PlacementUtils.inlinePlaced(BOPBaseFeatures.DEAD_CORAL_CLAW, FeatureConfiguration.NONE), PlacementUtils.inlinePlaced(BOPBaseFeatures.DEAD_CORAL_MUSHROOM, FeatureConfiguration.NONE))));
        register(context, BOPEndFeatures.ENDERPHYTE_BONEMEAL, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ENDERPHYTE.defaultBlockState())));
        register(context, BOPEndFeatures.TREES_END_CORRUPTION, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(NULL_TREE_CHECKED, 0.075F)), NULL_TREE_CHECKED));
        register(context, BOPEndFeatures.TREES_END_WILDS, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(EMPYREAL_TREE_CHECKED, 0.075F)), EMPYREAL_TREE_CHECKED));
        register(context, BOPEndFeatures.FLOWER_END_WILDS, Feature.FLOWER, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ENDBLOOM))));
        register(context, BOPEndFeatures.NULL_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(BOPBlocks.NULL_BLOCK), BlockStateProvider.simple(BOPBlocks.NULL_END_STONE)));
        register(context, BOPEndFeatures.PATCH_ENDERPHYTES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.ENDERPHYTE))));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
