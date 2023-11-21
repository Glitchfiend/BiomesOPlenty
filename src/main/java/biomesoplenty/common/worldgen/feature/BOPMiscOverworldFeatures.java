/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.data.worldgen.features.FeatureUtils;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.*;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.createKey;

public class BOPMiscOverworldFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_SAND_SPLATTER = createKey("black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_SPINE = createKey("bone_spine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_MOSS = createKey("crag_moss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_SPLATTER = createKey("crag_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_BLACK_SAND = createKey("disk_black_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_CALCITE = createKey("disk_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_GRAVEL_EXTRA = createKey("disk_gravel_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_ORANGE_SAND = createKey("disk_orange_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND = createKey("disk_white_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND_EXTRA = createKey("disk_white_sand_extra");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_CALCITE = createKey("disk_hot_spring_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_PACKED_MUD = createKey("disk_hot_spring_packed_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_MUD = createKey("disk_hot_spring_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_ORANGE = createKey("disk_hot_spring_orange");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_YELLOW = createKey("disk_hot_spring_yellow");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_MUD = createKey("disk_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BLACK_SAND_SPLATTER = createKey("mossy_black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_SPLATTER = createKey("mud_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LAKE = createKey("water_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_LAKE_VOLCANO = createKey("lava_lake_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANO = createKey("spring_lava_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_WATER_EXTRA = createKey("spring_water_extra");

    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER, BOPBaseFeatures.BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.BONE_SPINE, BOPBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.CRAG_MOSS, BOPBaseFeatures.CRAG_MOSS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.CRAG_SPLATTER, BOPBaseFeatures.CRAG_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.DISK_BLACK_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.BLACK_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.SAND, Blocks.GRAVEL, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(3, 7), 2));
        register(context, BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(1, 4), 2));
        register(context, BOPMiscOverworldFeatures.DISK_ORANGE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.ORANGE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 1));

        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE)), UniformInt.of(8, 8), 3));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_PACKED_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.PACKED_MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(6, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.CALCITE)), UniformInt.of(5, 5), 2));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_ORANGE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.ORANGE_TERRACOTTA), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.MUD, Blocks.PACKED_MUD, Blocks.CALCITE)), UniformInt.of(3, 4), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_YELLOW, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.YELLOW_TERRACOTTA), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.ORANGE_TERRACOTTA, Blocks.MUD, Blocks.PACKED_MUD, Blocks.CALCITE)), UniformInt.of(1, 2), 0));

        register(context, BOPMiscOverworldFeatures.DISK_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(4, 6), 2));
        register(context, BOPMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER, BOPBaseFeatures.MOSSY_BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.MUD_SPLATTER, BOPBaseFeatures.MUD_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.WATER_LAKE, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.LAVA_LAKE_VOLCANO, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.BLACK_SAND.get(), BOPBlocks.BLACK_SANDSTONE.get(), Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE)));
        register(context, BOPMiscOverworldFeatures.SPRING_WATER_EXTRA, Feature.SPRING, new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE.get())));
    }
    
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
