/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPFluids;
import biomesoplenty.common.worldgen.placement.BOPTreePlacements;
import biomesoplenty.init.ModTags;
import com.google.common.collect.ImmutableList;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
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
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.world.level.material.Fluids;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.createKey;

public class BOPNetherFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_BULB = createKey("blackstone_bulb");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACKSTONE_SPINES = createKey("blackstone_spines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_LAKE = createKey("blood_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLOOD_SPRING = createKey("blood_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMSTONE_BUD = createKey("brimstone_bud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BRIMSTONE_CLUSTER = createKey("brimstone_cluster");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DEAD_GRASS = createKey("dead_grass");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EYEBULB = createKey("eyebulb");
    public static final ResourceKey<ConfiguredFeature<?, ?>> FLESH_TENDON = createKey("flesh_tendon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HAIR = createKey("hair");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_FLESH_TENDON = createKey("hanging_flesh_tendon");
    public static final ResourceKey<ConfiguredFeature<?, ?>> INFERNO_DELTA = createKey("inferno_delta");
    public static final ResourceKey<ConfiguredFeature<?, ?>> INFERNO_LAVA_SPRING = createKey("inferno_lava_spring");
    public static final ResourceKey<ConfiguredFeature<?, ?>> INFERNO_SPLATTER = createKey("inferno_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_FUMAROLE = createKey("large_fumarole");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LARGE_ROSE_QUARTZ = createKey("large_rose_quartz");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BONE_SPINE = createKey("nether_bone_spine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_BRAMBLE = createKey("nether_bramble");
    public static final ResourceKey<ConfiguredFeature<?, ?>> NETHER_VINES = createKey("nether_vines");
    public static final ResourceKey<ConfiguredFeature<?, ?>> OBSIDIAN_SPLATTER = createKey("obsidian_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> POROUS_FLESH = createKey("porous_flesh");
    public static final ResourceKey<ConfiguredFeature<?, ?>> PUS_BUBBLES = createKey("pus_bubbles");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_CRYSTAL = createKey("small_crystal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_FUMAROLE = createKey("small_fumarole");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPROUTS_UNDERGROWTH = createKey("sprouts_undergrowth");
    public static final ResourceKey<ConfiguredFeature<?, ?>> TREES_UNDERGROWTH = createKey("trees_undergrowth");
    public static final ResourceKey<ConfiguredFeature<?, ?>> UNDERGROWTH_FLOWERS = createKey("undergrowth_flowers");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<PlacedFeature> placedFeatureGetter = context.lookup(Registries.PLACED_FEATURE);

        final Holder<PlacedFeature> BIG_HELLBARK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.BIG_HELLBARK_TREE_CHECKED);
        final Holder<PlacedFeature> HELLBARK_TREE_CHECKED = placedFeatureGetter.getOrThrow(BOPTreePlacements.HELLBARK_TREE_CHECKED);

        register(context, BOPNetherFeatures.BLACKSTONE_BULB, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_BULB.get()))));
        register(context, BOPNetherFeatures.BLACKSTONE_SPINES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BLACKSTONE_SPINES.get()))));
        register(context, BOPNetherFeatures.BLOOD_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(BOPBlocks.BLOOD.get()), BlockStateProvider.simple(BOPBlocks.FLESH.get())));
        register(context, BOPNetherFeatures.BLOOD_SPRING, Feature.SPRING, new SpringConfiguration(BOPFluids.BLOOD.get().defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK, BOPBlocks.FLESH.get(), BOPBlocks.POROUS_FLESH.get())));
        register(context, BOPNetherFeatures.BRIMSTONE_BUD, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BRIMSTONE_BUD.get()))));
        register(context, BOPNetherFeatures.BRIMSTONE_CLUSTER, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BRIMSTONE_CLUSTER.get()))));
        register(context, BOPNetherFeatures.DEAD_GRASS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.DEAD_GRASS.get()))));
        register(context, BOPNetherFeatures.EYEBULB, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.EYEBULB.get()))));
        register(context, BOPNetherFeatures.FLESH_TENDON, BOPBaseFeatures.FLESH_TENDON, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.HAIR, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.HAIR.get()))));
        register(context, BOPNetherFeatures.HANGING_FLESH_TENDON, BOPBaseFeatures.HANGING_FLESH_TENDON, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.INFERNO_DELTA, Feature.DELTA_FEATURE, new DeltaFeatureConfiguration(Blocks.LAVA.defaultBlockState(), BOPBlocks.BRIMSTONE.get().defaultBlockState(), UniformInt.of(5, 8), UniformInt.of(2, 3)));
        register(context, BOPNetherFeatures.INFERNO_LAVA_SPRING, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), false, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.NETHERRACK)));
        register(context, BOPNetherFeatures.INFERNO_SPLATTER, BOPBaseFeatures.INFERNO_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.LARGE_FUMAROLE, BOPBaseFeatures.LARGE_FUMAROLE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.LARGE_ROSE_QUARTZ, BOPBaseFeatures.LARGE_ROSE_QUARTZ, new LargeDripstoneConfiguration(30, UniformInt.of(3, 7), UniformFloat.of(0.3F, 1.8F), 0.33F, UniformFloat.of(0.3F, 0.9F), UniformFloat.of(0.4F, 1.0F), UniformFloat.of(0.0F, 0.3F), 4, 0.6F));
        register(context, BOPNetherFeatures.NETHER_BONE_SPINE, BOPBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.NETHER_BRAMBLE, BOPBaseFeatures.BRAMBLE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.NETHER_VINES, BOPBaseFeatures.NETHER_VINES, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.OBSIDIAN_SPLATTER, BOPBaseFeatures.OBSIDIAN_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.POROUS_FLESH, Feature.ORE, new OreConfiguration(new TagMatchTest(ModTags.Blocks.FLESH), BOPBlocks.POROUS_FLESH.get().defaultBlockState(), 16));
        register(context, BOPNetherFeatures.PUS_BUBBLES, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.PUS_BUBBLE.get()))));
        register(context, BOPNetherFeatures.SMALL_CRYSTAL, BOPBaseFeatures.SMALL_CRYSTAL, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.SMALL_FUMAROLE, BOPBaseFeatures.SMALL_FUMAROLE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPNetherFeatures.SPROUTS_UNDERGROWTH, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPROUT.get()))));
        register(context, BOPNetherFeatures.TREES_UNDERGROWTH, Feature.RANDOM_SELECTOR, new RandomFeatureConfiguration(ImmutableList.of(new WeightedPlacedFeature(BIG_HELLBARK_TREE_CHECKED, 0.4F)), HELLBARK_TREE_CHECKED));
        register(context, BOPNetherFeatures.UNDERGROWTH_FLOWERS, Feature.RANDOM_PATCH, FeatureUtils.simplePatchConfiguration(Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.BURNING_BLOSSOM.get()))));
    }

    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
