/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.common.worldgen.placement.BOPTreePlacements;
import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.init.ModTags;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;

public class BOPNetherFeatures
{
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> BLACKSTONE_BULB = register("blackstone_bulb", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_BULB))))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> BLACKSTONE_SPINES = register("blackstone_spines", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_SPINES))))));
    public static final ConfiguredFeature<LakeFeature.Configuration, ?> BLOOD_LAKE = register("blood_lake", Feature.LAKE.configured(new LakeFeature.Configuration(BlockStateProvider.simple(BOPBlocks.BLOOD), BlockStateProvider.simple(BOPBlocks.FLESH))));
    public static final ConfiguredFeature<SpringConfiguration, ?> BLOOD_SPRING = register("blood_spring", Feature.SPRING.configured(new SpringConfiguration(BOPFluids.BLOOD.defaultFluidState(), false, 4, 1, ImmutableSet.of(Blocks.NETHERRACK, BOPBlocks.FLESH, BOPBlocks.POROUS_FLESH))));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> DEAD_GRASS = register("dead_grass", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DEAD_GRASS))))));
    public static final ConfiguredFeature<?, ?> FLESH_TENDON = register("flesh_tendon", BOPBaseFeatures.FLESH_TENDON.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> HAIR = register("hair", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.HAIR))))));
    public static final ConfiguredFeature<?, ?> HANGING_FLESH_TENDON = register("hanging_flesh_tendon", BOPBaseFeatures.HANGING_FLESH_TENDON.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> LARGE_ROSE_QUARTZ = register("large_rose_quartz", BOPBaseFeatures.LARGE_ROSE_QUARTZ.configured(new LargeDripstoneConfiguration(30, UniformInt.of(3, 7), UniformFloat.of(0.3F, 1.8F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F)));
    public static final ConfiguredFeature<?, ?> NETHER_BONE_SPINE = register("nether_bone_spine", BOPBaseFeatures.BONE_SPINE.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> NETHER_BRAMBLE = register("nether_bramble", BOPBaseFeatures.BRAMBLE.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> NETHER_VINES = register("nether_vines", BOPBaseFeatures.NETHER_VINES.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPBaseFeatures.OBSIDIAN_SPLATTER.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<?, ?> POROUS_FLESH = register("porous_flesh", Feature.ORE.configured(new OreConfiguration(new TagMatchTest(ModTags.Blocks.FLESH), BOPBlocks.POROUS_FLESH.defaultBlockState(), 16)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> PUS_BUBBLES = register("pus_bubbles", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.PUS_BUBBLE))))));
    public static final ConfiguredFeature<?, ?> SMALL_CRYSTAL = register("small_crystal", BOPBaseFeatures.SMALL_CRYSTAL.configured(NoneFeatureConfiguration.INSTANCE));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> SPROUTS_UNDERGROWTH = register("sprouts_undergrowth", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPROUT))))));
    public static final ConfiguredFeature<?, ?> TREES_UNDERGROWTH = register("trees_undergrowth", Feature.RANDOM_SELECTOR.configured(new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.BIG_HELLBARK_TREE_CHECKED, 0.1F)), BOPTreePlacements.HELLBARK_TREE_CHECKED)));
    public static final ConfiguredFeature<RandomPatchConfiguration, ?> UNDERGROWTH_FLOWERS = register("undergrowth_flowers", Feature.RANDOM_PATCH.configured(FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK.configured(new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BURNING_BLOSSOM))))));

    private static <FC extends FeatureConfiguration> ConfiguredFeature<FC, ?> register(String key, ConfiguredFeature<FC, ?> feature)
    {
        return Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
