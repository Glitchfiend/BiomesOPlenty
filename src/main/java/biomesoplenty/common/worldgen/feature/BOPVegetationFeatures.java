/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.placement.BOPTreePlacements;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;

import java.util.List;

public class BOPVegetationFeatures
{
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> GOLDENROD = register("goldenrod", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.GOLDENROD))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_FERN_GRASS = register("patch_fern_grass", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.FERN))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_LILAC = register("patch_lilac", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.LILAC))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_PEONY = register("patch_peony", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.PEONY))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PATCH_CLOVER = register("patch_clover", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.CLOVER))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> ROSE_BUSH = register("rose_bush", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(Blocks.ROSE_BUSH))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> TOADSTOOL_NORMAL = register("toadstool_normal", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.TOADSTOOL))))));
    public static final ConfiguredFeature<?, ?> SHORT_BAMBOO = register("short_bamboo", BOPBaseFeatures.SHORT_BAMBOO.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<NoneFeatureConfiguration, ?> MOSS_SPLATTER = register("moss_splatter", BOPBaseFeatures.MOSS_SPLATTER.configured(NoneFeatureConfiguration.INSTANCE));

    // Flowers
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_CONIFEROUS_FOREST = register("flower_coniferous_forest", Feature.FLOWER.configured(grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.CORNFLOWER.defaultBlockState(), 1).add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_CHERRY_BLOSSOM_GROVE = register("flower_cherry_blossom_grove", Feature.FLOWER.configured(grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.PINK_DAFFODIL.defaultBlockState(), 1).add(Blocks.LILY_OF_THE_VALLEY.defaultBlockState(), 1)), 64)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> FLOWER_DEFAULT_EXTENDED = register("flower_default_extended", Feature.FLOWER.configured(grassPatch(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(Blocks.OXEYE_DAISY.defaultBlockState(), 1).add(Blocks.POPPY.defaultBlockState(), 1).add(Blocks.DANDELION.defaultBlockState(), 1)), 64)));

    // Trees
    public static final ConfiguredFeature<?, ?> TREES_BAMBOO_BLOSSOM_GROVE = register("bamboo_blossom_grove_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.WHITE_CHERRY_TREE_BEES_CHECKED, 0.05F), new WeightedPlacedFeature(BOPTreePlacements.PINK_CHERRY_TREE_BEES_CHECKED, 0.05F), new WeightedPlacedFeature(BOPTreePlacements.BIG_WHITE_CHERRY_TREE_CHECKED, 0.05F), new WeightedPlacedFeature(BOPTreePlacements.BIG_PINK_CHERRY_TREE_CHECKED, 0.05F), new WeightedPlacedFeature(BOPTreePlacements.BIG_FLOWERING_TREE_CHECKED, 0.05F), new WeightedPlacedFeature(BOPTreePlacements.FLOWERING_OAK_BUSH_CHECKED, 0.3F)), BOPTreePlacements.FLOWERING_OAK_TREE_BEES_CHECKED)));
    public static final ConfiguredFeature<?, ?> TREES_CHERRY_BLOSSOM_GROVE = register("cherry_blossom_grove_trees", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.WHITE_CHERRY_TREE_BEES_CHECKED, 0.1F), new WeightedPlacedFeature(BOPTreePlacements.PINK_CHERRY_TREE_BEES_CHECKED, 0.2F), new WeightedPlacedFeature(BOPTreePlacements.BIG_WHITE_CHERRY_TREE_CHECKED, 0.3F), new WeightedPlacedFeature(BOPTreePlacements.FLOWERING_OAK_TREE_BEES_CHECKED, 0.15F), new WeightedPlacedFeature(BOPTreePlacements.FLOWERING_OAK_BUSH_CHECKED, 0.3F)), BOPTreePlacements.BIG_PINK_CHERRY_TREE_CHECKED)));
    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> TREES_CONIFEROUS_FOREST = register("trees_coniferous_forest", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.FIR_CHECKED, 0.33333334F)), BOPTreePlacements.FIR_LARGE_CHECKED)));
    public static final ConfiguredFeature<RandomFeatureConfiguration, ?> TREES_REDWOOD_FOREST = register("trees_redwood_forest", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.REDWOOD_TREE_CHECKED, 0.3f), new WeightedPlacedFeature(BOPTreePlacements.REDWOOD_TREE_LARGE_CHECKED, 0.5f)), BOPTreePlacements.REDWOOD_TREE_MEDIUM_CHECKED)));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }

    private static RandomPatchConfiguration grassPatch(BlockStateProvider p_195203_, int tries)
    {
        return FeatureUtils.simpleRandomPatchConfiguration(tries, Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(p_195203_)).onlyWhenEmpty());
    }
}
