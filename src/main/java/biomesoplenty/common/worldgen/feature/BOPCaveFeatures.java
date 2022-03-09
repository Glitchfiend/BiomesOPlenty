/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.Direction;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.random.SimpleWeightedRandomList;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.util.valueproviders.WeightedListInt;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.BlockColumnConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SimpleBlockConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.VegetationPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.WeightedStateProvider;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.register;

public class BOPCaveFeatures
{
    // Glowing Grotto
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> GLOWING_GROTTO_FLOOR_PLANTS = register("glowing_grotto_floor_plants", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(new WeightedStateProvider(SimpleWeightedRandomList.<BlockState>builder().add(BOPBlocks.GLOWSHROOM.defaultBlockState(), 6).add(BOPBlocks.GLOWING_MOSS_CARPET.defaultBlockState(), 25))));

    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>>  GLOWING_MOSS_PATCH = register("glowing_moss_patch", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_BLOCK), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.5F, UniformInt.of(4, 7), 0.3F));

    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>>  GLOWING_MOSS_PATCH_BONEMEAL = register("glowing_moss_patch_bonemeal", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_BLOCK), PlacementUtils.inlinePlaced(GLOWING_GROTTO_FLOOR_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.4F, UniformInt.of(1, 2), 0.75F));

    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> MUD_PLANTS = register("mud_plants", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.GLOWING_MOSS_CARPET)));
    public static final Holder<ConfiguredFeature<VegetationPatchConfiguration, ?>>  MUD_PATCH = register("mud_patch", Feature.VEGETATION_PATCH, new VegetationPatchConfiguration(BlockTags.MOSS_REPLACEABLE, BlockStateProvider.simple(BOPBlocks.MUD), PlacementUtils.inlinePlaced(MUD_PLANTS), CaveSurface.FLOOR, ConstantInt.of(1), 0.0F, 5, 0.05F, UniformInt.of(4, 7), 0.3F));

    public static final Holder<ConfiguredFeature<BlockColumnConfiguration, ?>> GLOWWORM_SILK = register("glowworm_silk", Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 11), 2).add(UniformInt.of(0, 5), 3).add(UniformInt.of(0, 2), 10).build()), BlockStateProvider.simple(BOPBlocks.GLOWWORM_SILK_STRAND)), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(BOPBlocks.GLOWWORM_SILK))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));

    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> GIANT_GLOWSHROOM_CAVE = register("giant_glowshroom_cave", BOPBaseFeatures.GIANT_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> HUGE_GLOWSHROOM_CAVE = register("huge_glowshroom_cave", BOPBaseFeatures.HUGE_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> MEDIUM_GLOWSHROOM_CAVE = register("medium_glowshroom_cave", BOPBaseFeatures.MEDIUM_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> SMALL_GLOWSHROOM_CAVE = register("small_glowshroom_cave", BOPBaseFeatures.SMALL_GLOWSHROOM, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> EXTRA_GLOW_LICHEN = register("extra_glow_lichen", BOPBaseFeatures.EXTRA_GLOW_LICHEN, NoneFeatureConfiguration.INSTANCE);

    // Spider Nest
    public static final Holder<ConfiguredFeature<BlockColumnConfiguration, ?>> HANGING_COBWEB = register("hanging_cobweb", Feature.BLOCK_COLUMN, new BlockColumnConfiguration(List.of(BlockColumnConfiguration.layer(new WeightedListInt(SimpleWeightedRandomList.<IntProvider>builder().add(UniformInt.of(0, 15), 2).add(UniformInt.of(0, 3), 3).add(UniformInt.of(0, 7), 3).build()), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB_STRAND)), BlockColumnConfiguration.layer(ConstantInt.of(1), BlockStateProvider.simple(BOPBlocks.HANGING_COBWEB))), Direction.DOWN, BlockPredicate.ONLY_IN_AIR_PREDICATE, true));
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CORNER_COBWEBS = register("corner_cobwebs", BOPBaseFeatures.CORNER_COBWEBS, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<SimpleBlockConfiguration, ?>> SPIDER_EGG = register("spider_egg", Feature.SIMPLE_BLOCK, new SimpleBlockConfiguration(BlockStateProvider.simple(BOPBlocks.SPIDER_EGG)));
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> STRINGY_COBWEB = register("stringy_cobweb", BOPBaseFeatures.STRINGY_COBWEB, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> WEBBING = register("webbing", BOPBaseFeatures.WEBBING, NoneFeatureConfiguration.INSTANCE);
}
