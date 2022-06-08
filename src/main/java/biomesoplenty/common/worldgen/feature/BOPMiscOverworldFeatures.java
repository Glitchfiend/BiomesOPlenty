/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.register;

public class BOPMiscOverworldFeatures
{
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPBaseFeatures.BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BONE_SPINE = register("bone_spine", BOPBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CRAG_SPLATTER = register("crag_splatter", BOPBaseFeatures.CRAG_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_BLACK_SAND = register("disk_black_sand", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.BLACK_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_CALCITE = register("disk_calcite", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.SAND, Blocks.GRAVEL, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(3, 7), 2));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_GRAVEL_EXTRA = register("disk_gravel_extra", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(1, 4), 2));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_ORANGE_SAND = register("disk_orange_sand", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.ORANGE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_WHITE_SAND = register("disk_white_sand", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_WHITE_SAND_EXTRA = register("disk_white_sand_extra", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND.get()), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 1));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(4, 6), 2));
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> GRASS_SPLATTER = register("grass_splatter", BOPBaseFeatures.GRASS_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<SpringConfiguration, ?>> SPRING_LAVA_VOLCANO = register("spring_lava_volcano", Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.BLACK_SAND.get(), BOPBlocks.BLACK_SANDSTONE.get(), Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE)));
    public static final Holder<ConfiguredFeature<SpringConfiguration, ?>> SPRING_WATER_EXTRA = register("spring_water_extra", Feature.SPRING, new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE.get())));
}
