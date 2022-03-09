/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.core.BiomesOPlenty;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableSet;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderSet;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPFeatureUtils.register;

public class BOPMiscOverworldFeatures
{
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPBaseFeatures.BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> BONE_SPINE = register("bone_spine", BOPBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> CRAG_SPLATTER = register("crag_splatter", BOPBaseFeatures.CRAG_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_BLACK_SAND = register("disk_black_sand", Feature.DISK, new DiskConfiguration(BOPBlocks.BLACK_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_CALCITE = register("disk_calcite", Feature.DISK, new DiskConfiguration(Blocks.CALCITE.defaultBlockState(), UniformInt.of(3, 7), 2, ImmutableList.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState(), Blocks.PODZOL.defaultBlockState(), Blocks.SAND.defaultBlockState(), Blocks.GRAVEL.defaultBlockState(), Blocks.GRANITE.defaultBlockState(), Blocks.ANDESITE.defaultBlockState(), Blocks.DIORITE.defaultBlockState(), Blocks.COAL_ORE.defaultBlockState(), Blocks.IRON_ORE.defaultBlockState(), Blocks.STONE.defaultBlockState(), Blocks.CALCITE.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_GRAVEL_EXTRA = register("disk_gravel_extra", Feature.DISK, new DiskConfiguration(Blocks.GRAVEL.defaultBlockState(), UniformInt.of(1, 4), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_ORANGE_SAND = register("disk_orange_sand", Feature.DISK, new DiskConfiguration(BOPBlocks.ORANGE_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_WHITE_SAND = register("disk_white_sand", Feature.DISK, new DiskConfiguration(BOPBlocks.WHITE_SAND.defaultBlockState(), UniformInt.of(2, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_WHITE_SAND_EXTRA = register("disk_white_sand_extra", Feature.DISK, new DiskConfiguration(BOPBlocks.WHITE_SAND.defaultBlockState(), UniformInt.of(2, 6), 1, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<DiskConfiguration, ?>> DISK_MUD = register("disk_mud", Feature.DISK, new DiskConfiguration(BOPBlocks.MUD.defaultBlockState(), UniformInt.of(4, 6), 2, List.of(Blocks.DIRT.defaultBlockState(), Blocks.GRASS_BLOCK.defaultBlockState())));
    public static final Holder<ConfiguredFeature<NoneFeatureConfiguration, ?>> GRASS_SPLATTER = register("grass_splatter", BOPBaseFeatures.GRASS_SPLATTER, NoneFeatureConfiguration.INSTANCE);
    public static final Holder<ConfiguredFeature<SpringConfiguration, ?>> SPRING_LAVA_VOLCANO = register("spring_lava_volcano", Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.BLACK_SAND, BOPBlocks.BLACK_SANDSTONE, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE)));
    public static final Holder<ConfiguredFeature<SpringConfiguration, ?>> SPRING_WATER_EXTRA = register("spring_water_extra", Feature.SPRING, new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE)));
}
