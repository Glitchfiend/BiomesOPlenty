/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.createKey;

public class BOPCaveFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_GROTTO_FLOOR_PLANTS = createKey("glowing_grotto_floor_plants");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH = createKey("glowing_moss_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWING_MOSS_PATCH_BONEMEAL = createKey("glowing_moss_patch_bonemeal");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_PLANTS = createKey("mud_plants");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_PATCH = createKey("mud_patch");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GLOWWORM_SILK = createKey("glowworm_silk");
    public static final ResourceKey<ConfiguredFeature<?, ?>> GIANT_GLOWSHROOM_CAVE = createKey("giant_glowshroom_cave");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HUGE_GLOWSHROOM_CAVE = createKey("huge_glowshroom_cave");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MEDIUM_GLOWSHROOM_CAVE = createKey("medium_glowshroom_cave");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SMALL_GLOWSHROOM_CAVE = createKey("small_glowshroom_cave");
    public static final ResourceKey<ConfiguredFeature<?, ?>> EXTRA_GLOW_LICHEN = createKey("extra_glow_lichen");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HANGING_COBWEB = createKey("hanging_cobweb");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CORNER_COBWEBS = createKey("corner_cobwebs");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPIDER_EGG = createKey("spider_egg");
    public static final ResourceKey<ConfiguredFeature<?, ?>> STRINGY_COBWEB = createKey("stringy_cobweb");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WEBBING = createKey("webbing");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        HolderGetter<ConfiguredFeature<?, ?>> configuredFeatureGetter = context.lookup(Registries.CONFIGURED_FEATURE);

        register(context, BOPCaveFeatures.GLOWING_GROTTO_FLOOR_PLANTS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.GLOWSHROOM.get().defaultBlockState(), 6).add(BOPBlocks.GLOWING_MOSS_CARPET.get().defaultBlockState(), 25))));
        final Holder<ConfiguredFeature<?, ?>> GLOWING_GROTTO_FLOOR_PLANTS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GLOWING_GROTTO_FLOOR_PLANTS);

        register(context, BOPCaveFeatures.GLOWING_MOSS_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.5F, UniformInt.of(4, 7), 0.3F));
        register(context, BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_BLOCK.get()), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.4F, UniformInt.of(1, 2), 0.75F));

        register(context, BOPCaveFeatures.MUD_PLANTS, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_CARPET.get())));
        final Holder<ConfiguredFeature<?, ?>> MUD_PLANTS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.MUD_PLANTS);

        register(context, BOPCaveFeatures.MUD_PATCH, Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(Blocks.MUD), PlacementUtils.inlinePlaced(MUD_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.05F, UniformInt.of(4, 7), 0.3F));
        register(context, BOPCaveFeatures.GLOWWORM_SILK, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 11), 2).add(UniformInt.of(0, 5), 3).add(UniformInt.of(0, 2), 10).build()), BlockStateProvider.simple(BOPBlocks.GLOWWORM_SILK_STRAND.get())), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(BOPBlocks.GLOWWORM_SILK.get()))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
        register(context, BOPCaveFeatures.GIANT_GLOWSHROOM_CAVE, BOPBaseFeatures.GIANT_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.HUGE_GLOWSHROOM_CAVE, BOPBaseFeatures.HUGE_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.MEDIUM_GLOWSHROOM_CAVE, BOPBaseFeatures.MEDIUM_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.SMALL_GLOWSHROOM_CAVE, BOPBaseFeatures.SMALL_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.EXTRA_GLOW_LICHEN, BOPBaseFeatures.EXTRA_GLOW_LICHEN, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.HANGING_COBWEB, Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 15), 2).add(UniformInt.of(0, 3), 3).add(UniformInt.of(0, 7), 3).build()), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB_STRAND.get())), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB.get()))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
        register(context, BOPCaveFeatures.CORNER_COBWEBS, BOPBaseFeatures.CORNER_COBWEBS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.SPIDER_EGG, Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPIDER_EGG.get())));
        register(context, BOPCaveFeatures.STRINGY_COBWEB, BOPBaseFeatures.STRINGY_COBWEB, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPCaveFeatures.WEBBING, BOPBaseFeatures.WEBBING, NoneFeatureConfiguration.INSTANCE);
    }
    
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}