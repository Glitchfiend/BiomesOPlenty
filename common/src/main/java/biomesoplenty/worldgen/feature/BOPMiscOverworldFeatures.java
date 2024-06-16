/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.worldgen.BOPFeatureUtils;
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.DiskConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.levelgen.feature.configurations.SpringConfiguration;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedBlockStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class BOPMiscOverworldFeatures
{
    public static final ResourceKey<ConfiguredFeature<?, ?>> BLACK_SAND_SPLATTER = BOPFeatureUtils.createKey("black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> BONE_SPINE = BOPFeatureUtils.createKey("bone_spine");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_MOSS = BOPFeatureUtils.createKey("crag_moss");
    public static final ResourceKey<ConfiguredFeature<?, ?>> CRAG_SPLATTER = BOPFeatureUtils.createKey("crag_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_BLACK_SAND = BOPFeatureUtils.createKey("disk_black_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_CALCITE = BOPFeatureUtils.createKey("disk_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_GRAVEL_EXTRA = BOPFeatureUtils.createKey("disk_gravel_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_ORANGE_SAND = BOPFeatureUtils.createKey("disk_orange_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND = BOPFeatureUtils.createKey("disk_white_sand");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SAND_EXTRA = BOPFeatureUtils.createKey("disk_white_sand_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_WHITE_SANDSTONE = BOPFeatureUtils.createKey("disk_white_sandstone");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_GRAVEL = BOPFeatureUtils.createKey("disk_hot_spring_gravel");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_CALCITE = BOPFeatureUtils.createKey("disk_hot_spring_calcite");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_BASALT = BOPFeatureUtils.createKey("disk_hot_spring_basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_PACKED_MUD = BOPFeatureUtils.createKey("disk_hot_spring_packed_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_HOT_SPRING_THERMAL_CALCITE = BOPFeatureUtils.createKey("disk_hot_spring_thermal_calcite");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_VOLCANO_SMOOTH_BASALT = BOPFeatureUtils.createKey("disk_volcano_smooth_basalt");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_VOLCANO_BLACK_SANDSTONE = BOPFeatureUtils.createKey("disk_volcano_black_sandstone");
    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_VOLCANO_MAGMA = BOPFeatureUtils.createKey("disk_volcano_magma");

    public static final ResourceKey<ConfiguredFeature<?, ?>> DISK_MUD = BOPFeatureUtils.createKey("disk_mud");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MOSSY_BLACK_SAND_SPLATTER = BOPFeatureUtils.createKey("mossy_black_sand_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> MUD_SPLATTER = BOPFeatureUtils.createKey("mud_splatter");
    public static final ResourceKey<ConfiguredFeature<?, ?>> WATER_LAKE = BOPFeatureUtils.createKey("water_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> HOT_SPRING_LAKE = BOPFeatureUtils.createKey("hot_spring_lake");
    public static final ResourceKey<ConfiguredFeature<?, ?>> LAVA_LAKE_VOLCANO = BOPFeatureUtils.createKey("lava_lake_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_LAVA_VOLCANO = BOPFeatureUtils.createKey("spring_lava_volcano");
    public static final ResourceKey<ConfiguredFeature<?, ?>> SPRING_WATER_EXTRA = BOPFeatureUtils.createKey("spring_water_extra");
    public static final ResourceKey<ConfiguredFeature<?, ?>> ORIGIN_GRAVEL_CLIFFS = BOPFeatureUtils.createKey("origin_gravel_cliffs");

    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        register(context, BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER, BOPBaseFeatures.BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.BONE_SPINE, BOPBaseFeatures.BONE_SPINE, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.CRAG_MOSS, BOPBaseFeatures.CRAG_MOSS, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.CRAG_SPLATTER, BOPBaseFeatures.CRAG_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.DISK_BLACK_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.BLACK_SAND), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.SAND, Blocks.GRAVEL, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(3, 7), 2));
        register(context, BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(1, 4), 2));
        register(context, BOPMiscOverworldFeatures.DISK_ORANGE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.ORANGE_SAND), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SAND), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, BOPBlocks.ORIGIN_GRASS_BLOCK)), UniformInt.of(6, 8), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SANDSTONE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.WHITE_SANDSTONE), BlockPredicate.matchesBlocks(List.of(Blocks.STONE, Blocks.SANDSTONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE)), UniformInt.of(6, 8), 2));

        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_GRAVEL, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.GRAVEL), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE)), UniformInt.of(8, 8), 4));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE)), UniformInt.of(6, 7), 3));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_BASALT, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.SMOOTH_BASALT), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.CALCITE)), UniformInt.of(5, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_PACKED_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.PACKED_MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.SMOOTH_BASALT, Blocks.CALCITE)), UniformInt.of(4, 4), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_THERMAL_CALCITE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.THERMAL_CALCITE), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.SMOOTH_BASALT, Blocks.CALCITE)), UniformInt.of(3, 5), 1));

        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_SMOOTH_BASALT, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.SMOOTH_BASALT), BlockPredicate.matchesBlocks(List.of(Blocks.TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.MOSSY_BLACK_SAND)), UniformInt.of(6, 8), 4));
        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_BLACK_SANDSTONE, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(BOPBlocks.BLACK_SANDSTONE), BlockPredicate.matchesBlocks(List.of(Blocks.TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.BLACK_SANDSTONE, BOPBlocks.MOSSY_BLACK_SAND)), UniformInt.of(4, 6), 3));
        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_MAGMA, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MAGMA_BLOCK), BlockPredicate.matchesBlocks(List.of(Blocks.TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.BLACK_SANDSTONE, BOPBlocks.MOSSY_BLACK_SAND)), UniformInt.of(1, 3), 2));

        register(context, BOPMiscOverworldFeatures.DISK_MUD, Feature.DISK, new DiskConfiguration(RuleBasedBlockStateProvider.simple(Blocks.MUD), BlockPredicate.matchesBlocks(List.of(Blocks.DIRT, Blocks.GRASS_BLOCK)), UniformInt.of(4, 6), 2));
        register(context, BOPMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER, BOPBaseFeatures.MOSSY_BLACK_SAND_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.MUD_SPLATTER, BOPBaseFeatures.MUD_SPLATTER, NoneFeatureConfiguration.INSTANCE);
        register(context, BOPMiscOverworldFeatures.WATER_LAKE, BOPBaseFeatures.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.HOT_SPRING_LAKE, BOPBaseFeatures.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.WATER.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.LAVA_LAKE_VOLCANO, Feature.LAKE, new LakeFeature.Configuration(BlockStateProvider.simple(Blocks.LAVA.defaultBlockState()), BlockStateProvider.simple(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO, Feature.SPRING, new SpringConfiguration(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.SMOOTH_BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.BLACK_SANDSTONE)));
        register(context, BOPMiscOverworldFeatures.SPRING_WATER_EXTRA, Feature.SPRING, new SpringConfiguration(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.LIGHT_GRAY_TERRACOTTA, Blocks.CYAN_TERRACOTTA, Blocks.LIGHT_BLUE_TERRACOTTA, Blocks.BLUE_TERRACOTTA, Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE)));
        register(context, BOPMiscOverworldFeatures.ORIGIN_GRAVEL_CLIFFS, BOPBaseFeatures.ORIGIN_GRAVEL_CLIFFS, NoneFeatureConfiguration.INSTANCE);
    }
    
    private static <FC extends FeatureConfiguration, F extends Feature<FC>> void register(BootstrapContext<ConfiguredFeature<?, ?>> context, ResourceKey<ConfiguredFeature<?, ?>> configuredFeatureKey, F feature, FC configuration)
    {
        context.register(configuredFeatureKey, new ConfiguredFeature<>(feature, configuration));
    }
}
