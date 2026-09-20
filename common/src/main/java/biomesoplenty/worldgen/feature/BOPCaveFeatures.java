/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.worldgen.feature.misc.*;
import biomesoplenty.worldgen.feature.tree.*;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.WeightedList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

public class BOPCaveFeatures
{
    public static final ResourceKey<Feature> GLOWING_GROTTO_FLOOR_PLANTS = BOPFeatureUtils.createKey("glowing_grotto_floor_plants");
    public static final ResourceKey<Feature> GLOWING_GROTTO_CEILING_PLANTS = BOPFeatureUtils.createKey("glowing_grotto_ceiling_plants");
    public static final ResourceKey<Feature> GLOWING_MOSS_PATCH = BOPFeatureUtils.createKey("glowing_moss_patch");
    public static final ResourceKey<Feature> GLOWING_MOSS_PATCH_BONEMEAL = BOPFeatureUtils.createKey("glowing_moss_patch_bonemeal");
    public static final ResourceKey<Feature> GLOWING_MOSS_PATCH_BONEMEAL_BOTTOM = BOPFeatureUtils.createKey("glowing_moss_patch_bonemeal_bottom");
    public static final ResourceKey<Feature> MUD_PLANTS = BOPFeatureUtils.createKey("mud_plants");
    public static final ResourceKey<Feature> MUD_PATCH = BOPFeatureUtils.createKey("mud_patch");
    public static final ResourceKey<Feature> GLOWWORM_SILK = BOPFeatureUtils.createKey("glowworm_silk");
    public static final ResourceKey<Feature> GIANT_GLOWSHROOM_CAVE = BOPFeatureUtils.createKey("giant_glowshroom_cave");
    public static final ResourceKey<Feature> HUGE_GLOWSHROOM_CAVE = BOPFeatureUtils.createKey("huge_glowshroom_cave");
    public static final ResourceKey<Feature> MEDIUM_GLOWSHROOM_CAVE = BOPFeatureUtils.createKey("medium_glowshroom_cave");
    public static final ResourceKey<Feature> SMALL_GLOWSHROOM_CAVE = BOPFeatureUtils.createKey("small_glowshroom_cave");
    public static final ResourceKey<Feature> EXTRA_GLOW_LICHEN = BOPFeatureUtils.createKey("extra_glow_lichen");
    public static final ResourceKey<Feature> HANGING_COBWEB = BOPFeatureUtils.createKey("hanging_cobweb");
    public static final ResourceKey<Feature> CORNER_COBWEBS = BOPFeatureUtils.createKey("corner_cobwebs");
    public static final ResourceKey<Feature> SPIDER_EGG = BOPFeatureUtils.createKey("spider_egg");
    public static final ResourceKey<Feature> STRINGY_COBWEB = BOPFeatureUtils.createKey("stringy_cobweb");
    public static final ResourceKey<Feature> WEBBING = BOPFeatureUtils.createKey("webbing");

    public static void bootstrap(BootstrapContext<Feature> context)
    {
        HolderGetter<Block> blocks = context.lookup(Registries.BLOCK);
        HolderGetter<Feature> configuredFeatureGetter = context.lookup(Registries.FEATURE);

        register(context, BOPCaveFeatures.GLOWING_GROTTO_FLOOR_PLANTS, new SimpleBlockFeature(new WeightedStateProvider(WeightedList.<BlockState>builder().add(BOPBlocks.GLOWSHROOM.defaultBlockState(), 6).add(BOPBlocks.GLOWING_MOSS_CARPET.defaultBlockState(), 25))));
        final Holder<Feature> GLOWING_GROTTO_FLOOR_PLANTS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GLOWING_GROTTO_FLOOR_PLANTS);

        register(context, BOPCaveFeatures.GLOWING_GROTTO_CEILING_PLANTS, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.GLOWWORM_SILK)));
        final Holder<Feature> GLOWING_GROTTO_CEILING_PLANTS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.GLOWING_GROTTO_CEILING_PLANTS);

        register(context, BOPCaveFeatures.GLOWING_MOSS_PATCH, new VegetationPatchFeature(blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE), BlockStateProvider.holderOf(BOPBlocks.GLOWING_MOSS_BLOCK), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.5F, UniformInt.of(4, 7), 0.3F));
        register(context, BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL, new VegetationPatchFeature(blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE), BlockStateProvider.holderOf(BOPBlocks.GLOWING_MOSS_BLOCK), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.4F, UniformInt.of(1, 2), 0.75F));
        register(context, BOPCaveFeatures.GLOWING_MOSS_PATCH_BONEMEAL_BOTTOM, new VegetationPatchFeature(blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE), BlockStateProvider.holderOf(BOPBlocks.GLOWING_MOSS_BLOCK), PlacementUtils.inlinePlaced(GLOWING_GROTTO_CEILING_PLANTS), CaveSurface.CEILING, ConstantInt.of(1), 0.0F, 5, 0.4F, UniformInt.of(1, 2), 0.75F));

        register(context, BOPCaveFeatures.MUD_PLANTS, new SimpleBlockFeature(new WeightedStateProvider(WeightedList.<BlockState>builder().add(Blocks.FIREFLY_BUSH.defaultBlockState(), 6).add(BOPBlocks.GLOWING_MOSS_CARPET.defaultBlockState(), 25))));
        final Holder<Feature> MUD_PLANTS = configuredFeatureGetter.getOrThrow(BOPCaveFeatures.MUD_PLANTS);

        register(context, BOPCaveFeatures.MUD_PATCH, new VegetationPatchFeature(blocks.getOrThrow(BlockTags.MOSS_REPLACEABLE), BlockStateProvider.holderOf(Blocks.MUD), PlacementUtils.inlinePlaced(MUD_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.05F, UniformInt.of(4, 7), 0.3F));
        register(context, BOPCaveFeatures.GLOWWORM_SILK, new BlockColumnFeature(List.of(BlockColumnFeature.layer(new WeightedListInt(WeightedList.<IntProvider>builder().add(UniformInt.of(0, 11), 2).add(UniformInt.of(0, 5), 3).add(UniformInt.of(0, 2), 10).build()), BlockStateProvider.of(BOPBlocks.GLOWWORM_SILK_STRAND)), BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(BOPBlocks.GLOWWORM_SILK))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
        register(context, BOPCaveFeatures.GIANT_GLOWSHROOM_CAVE, new GiantGlowshroomFeature());
        register(context, BOPCaveFeatures.HUGE_GLOWSHROOM_CAVE, new HugeGlowshroomFeature());
        register(context, BOPCaveFeatures.MEDIUM_GLOWSHROOM_CAVE, new MediumGlowshroomFeature());
        register(context, BOPCaveFeatures.SMALL_GLOWSHROOM_CAVE, new SmallGlowshroomFeature());
        register(context, BOPCaveFeatures.EXTRA_GLOW_LICHEN, new ExtraGlowLichenFeature());
        register(context, BOPCaveFeatures.HANGING_COBWEB, new BlockColumnFeature(List.of(BlockColumnFeature.layer(new WeightedListInt(WeightedList.<IntProvider>builder().add(UniformInt.of(0, 15), 2).add(UniformInt.of(0, 3), 3).add(UniformInt.of(0, 7), 3).build()), BlockStateProvider.of(BOPBlocks.HANGING_COBWEB_STRAND)), BlockColumnFeature.layer(ConstantInt.of(1), BlockStateProvider.of(BOPBlocks.HANGING_COBWEB))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
        register(context, BOPCaveFeatures.CORNER_COBWEBS, new CornerCobwebFeature());
        register(context, BOPCaveFeatures.SPIDER_EGG, new SimpleBlockFeature(BlockStateProvider.of(BOPBlocks.SPIDER_EGG)));
        register(context, BOPCaveFeatures.STRINGY_COBWEB, new StringyCobwebFeature());
        register(context, BOPCaveFeatures.WEBBING, new WebbingFeature());
    }
    
    private static void register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature)
    {
        context.register(key, feature);
    }
}