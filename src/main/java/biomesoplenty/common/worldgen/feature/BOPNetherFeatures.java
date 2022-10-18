/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.common.worldgen.placement.BOPTreePlacements;
import biomesoplenty.init.ModTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.util.valueproviders.UniformFloat;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.WeightedPlacedFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.RegistryObject;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.register;

public class BOPNetherFeatures
{
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> BLACKSTONE_BULB = register("blackstone_bulb", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_BULB.get()))));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> BLACKSTONE_SPINES = register("blackstone_spines", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_SPINES.get()))));
    public static final RegistryObject<ConfiguredFeature<LakeFeature.Configuration, ?>> BLOOD_LAKE = register("blood_lake", Feature.LAKE, () -> new LakeFeature.Configuration(BlockStateProvider.simple(BOPBlocks.BLOOD.get()), BlockStateProvider.simple(BOPBlocks.FLESH.get())));
    public static final RegistryObject<ConfiguredFeature<SpringConfiguration, ?>> BLOOD_SPRING = register("blood_spring", Feature.SPRING, () -> new SpringConfiguration(BOPFluids.BLOOD.get().defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK, BOPBlocks.FLESH.get(), BOPBlocks.POROUS_FLESH.get())));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> BRIMSTONE_BUD = register("brimstone_bud", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BRIMSTONE_BUD.get()))));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> BRIMSTONE_CLUSTER = register("brimstone_cluster", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BRIMSTONE_CLUSTER.get()))));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> DEAD_GRASS = register("dead_grass", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DEAD_GRASS.get()))));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> EYEBULB = register("eyebulb", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.EYEBULB.get()))));
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> FLESH_TENDON = register("flesh_tendon", BOPBaseFeatures.FLESH_TENDON, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> HAIR = register("hair", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.HAIR.get()))));
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> HANGING_FLESH_TENDON = register("hanging_flesh_tendon", BOPBaseFeatures.HANGING_FLESH_TENDON, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<DeltaFeatureConfiguration, ?>> INFERNO_DELTA = register("inferno_delta", Feature.DELTA_FEATURE, () -> new DeltaFeatureConfiguration(Blocks.LAVA.defaultBlockState(), BOPBlocks.BRIMSTONE.get().defaultBlockState(), UniformInt.of(5, 8), UniformInt.of(2, 3)));
    public static final RegistryObject<ConfiguredFeature<SpringConfiguration, ?>> INFERNO_LAVA_SPRING = register("inferno_lava_spring", Feature.SPRING, () -> new SpringConfiguration(Fluids.LAVA.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK)));
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> INFERNO_SPLATTER = register("inferno_splatter", BOPBaseFeatures.INFERNO_SPLATTER, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> LARGE_FUMAROLE = register("large_fumarole", BOPBaseFeatures.LARGE_FUMAROLE, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<LargeDripstoneConfiguration, ?>> LARGE_ROSE_QUARTZ = register("large_rose_quartz", BOPBaseFeatures.LARGE_ROSE_QUARTZ, () -> new LargeDripstoneConfiguration(30, UniformInt.of(3, 7), UniformFloat.of(0.3F, 1.8F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> NETHER_BONE_SPINE = register("nether_bone_spine", BOPBaseFeatures.BONE_SPINE, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> NETHER_BRAMBLE = register("nether_bramble", BOPBaseFeatures.BRAMBLE, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> NETHER_VINES = register("nether_vines", BOPBaseFeatures.NETHER_VINES, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPBaseFeatures.OBSIDIAN_SPLATTER, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<OreConfiguration, ?>> POROUS_FLESH = register("porous_flesh", Feature.ORE, () -> new OreConfiguration(new TagMatchTest(ModTags.Blocks.FLESH), BOPBlocks.POROUS_FLESH.get().defaultBlockState(), 16));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> PUS_BUBBLES = register("pus_bubbles", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.PUS_BUBBLE.get()))));
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> SMALL_CRYSTAL = register("small_crystal", BOPBaseFeatures.SMALL_CRYSTAL, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<NoneFeatureConfiguration, ?>> SMALL_FUMAROLE = register("small_fumarole", BOPBaseFeatures.SMALL_FUMAROLE, () -> NoneFeatureConfiguration.INSTANCE);
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> SPROUTS_UNDERGROWTH = register("sprouts_undergrowth", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPROUT.get()))));
    public static final RegistryObject<ConfiguredFeature<RandomFeatureConfiguration, ?>> TREES_UNDERGROWTH = register("trees_undergrowth", Feature.RANDOM_SELECTOR, () -> new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BOPTreePlacements.BIG_HELLBARK_TREE_CHECKED.getHolder().orElseThrow(), 0.1F)), BOPTreePlacements.HELLBARK_TREE_CHECKED.getHolder().orElseThrow()));
    public static final RegistryObject<ConfiguredFeature<RandomPatchConfiguration, ?>> UNDERGROWTH_FLOWERS = register("undergrowth_flowers", Feature.RANDOM_PATCH, () -> FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BURNING_BLOSSOM.get()))));

    public static void setup() {}
}
