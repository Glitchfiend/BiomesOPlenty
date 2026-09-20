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
import net.minecraft.core.HolderSet;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.feature.*;
import net.minecraft.world.level.levelgen.feature.LakeFeature;
import net.minecraft.world.level.levelgen.feature.stateproviders.BlockStateProvider;
import net.minecraft.world.level.levelgen.feature.stateproviders.RuleBasedStateProvider;
import net.minecraft.world.level.material.Fluids;

import java.util.List;

public class BOPMiscOverworldFeatures
{
    public static final ResourceKey<Feature> BLACK_SAND_SPLATTER = BOPFeatureUtils.createKey("black_sand_splatter");
    public static final ResourceKey<Feature> BONE_SPINE = BOPFeatureUtils.createKey("bone_spine");
    public static final ResourceKey<Feature> CRAG_MOSS = BOPFeatureUtils.createKey("crag_moss");
    public static final ResourceKey<Feature> CRAG_SPLATTER = BOPFeatureUtils.createKey("crag_splatter");
    public static final ResourceKey<Feature> DISK_BLACK_SAND = BOPFeatureUtils.createKey("disk_black_sand");
    public static final ResourceKey<Feature> DISK_CALCITE = BOPFeatureUtils.createKey("disk_calcite");
    public static final ResourceKey<Feature> DISK_GRAVEL_EXTRA = BOPFeatureUtils.createKey("disk_gravel_extra");
    public static final ResourceKey<Feature> DISK_ORANGE_SAND = BOPFeatureUtils.createKey("disk_orange_sand");
    public static final ResourceKey<Feature> DISK_WHITE_SAND = BOPFeatureUtils.createKey("disk_white_sand");
    public static final ResourceKey<Feature> DISK_WHITE_SAND_EXTRA = BOPFeatureUtils.createKey("disk_white_sand_extra");
    public static final ResourceKey<Feature> DISK_WHITE_SANDSTONE = BOPFeatureUtils.createKey("disk_white_sandstone");

    public static final ResourceKey<Feature> DISK_HOT_SPRING_GRAVEL = BOPFeatureUtils.createKey("disk_hot_spring_gravel");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_CALCITE = BOPFeatureUtils.createKey("disk_hot_spring_calcite");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_BASALT = BOPFeatureUtils.createKey("disk_hot_spring_basalt");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_PACKED_MUD = BOPFeatureUtils.createKey("disk_hot_spring_packed_mud");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_CINNABAR = BOPFeatureUtils.createKey("disk_hot_spring_cinnabar");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_ORPIMENT = BOPFeatureUtils.createKey("disk_hot_spring_orpiment");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_SULFUR = BOPFeatureUtils.createKey("disk_hot_spring_sulfur");
    public static final ResourceKey<Feature> DISK_HOT_SPRING_SPHALERITE = BOPFeatureUtils.createKey("disk_hot_spring_sphalerite");

    public static final ResourceKey<Feature> DISK_VOLCANO_SMOOTH_BASALT = BOPFeatureUtils.createKey("disk_volcano_smooth_basalt");
    public static final ResourceKey<Feature> DISK_VOLCANO_BLACK_SANDSTONE = BOPFeatureUtils.createKey("disk_volcano_black_sandstone");
    public static final ResourceKey<Feature> DISK_VOLCANO_MAGMA = BOPFeatureUtils.createKey("disk_volcano_magma");

    public static final ResourceKey<Feature> DISK_SURFACE_CLAY = BOPFeatureUtils.createKey("disk_surface_clay");
    public static final ResourceKey<Feature> DISK_MUD = BOPFeatureUtils.createKey("disk_mud");
    public static final ResourceKey<Feature> MOSSY_BLACK_SAND_SPLATTER = BOPFeatureUtils.createKey("mossy_black_sand_splatter");
    public static final ResourceKey<Feature> MUD_SPLATTER = BOPFeatureUtils.createKey("mud_splatter");
    public static final ResourceKey<Feature> WATER_LAKE = BOPFeatureUtils.createKey("water_lake");
    public static final ResourceKey<Feature> HOT_SPRING_LAKE = BOPFeatureUtils.createKey("hot_spring_lake");
    public static final ResourceKey<Feature> OBSIDIAN_LAKE = BOPFeatureUtils.createKey("obsidian_lake");
    public static final ResourceKey<Feature> LAVA_LAKE_VOLCANO = BOPFeatureUtils.createKey("lava_lake_volcano");
    public static final ResourceKey<Feature> SPRING_LAVA_VOLCANO = BOPFeatureUtils.createKey("spring_lava_volcano");
    public static final ResourceKey<Feature> SPRING_WATER_EXTRA = BOPFeatureUtils.createKey("spring_water_extra");
    public static final ResourceKey<Feature> ORIGIN_GRAVEL_CLIFFS = BOPFeatureUtils.createKey("origin_gravel_cliffs");

    public static final ResourceKey<Feature> ERODED_PILLAR = BOPFeatureUtils.createKey("eroded_pillar");

    public static void bootstrap(BootstrapContext<Feature> context)
    {
        register(context, BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER, new BlackSandSplatterFeature());
        register(context, BOPMiscOverworldFeatures.BONE_SPINE, new BoneSpineFeature());
        register(context, BOPMiscOverworldFeatures.CRAG_MOSS, new CragMossFeature());
        register(context, BOPMiscOverworldFeatures.CRAG_SPLATTER, new CragSplatterFeature());
        register(context, BOPMiscOverworldFeatures.DISK_BLACK_SAND, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.BLACK_SAND), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_CALCITE, new DiskFeature(BlockStateProvider.holderOf(Blocks.CALCITE), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.PODZOL, Blocks.SAND, Blocks.GRAVEL, Blocks.GRANITE, Blocks.ANDESITE, Blocks.DIORITE, Blocks.COAL_ORE, Blocks.IRON_ORE, Blocks.STONE, Blocks.CALCITE), UniformInt.of(3, 7), 2));
        register(context, BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA, new DiskFeature(BlockStateProvider.holderOf(Blocks.GRAVEL), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(1, 4), 2));
        register(context, BOPMiscOverworldFeatures.DISK_ORANGE_SAND, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.ORANGE_SAND), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.WHITE_SAND), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(2, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.WHITE_SAND), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK, Blocks.MYCELIUM, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, BOPBlocks.ORIGIN_GRASS_BLOCK), UniformInt.of(6, 8), 2));
        register(context, BOPMiscOverworldFeatures.DISK_WHITE_SANDSTONE, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.WHITE_SANDSTONE), BlockPredicate.matchesBlocks(Blocks.STONE, Blocks.SANDSTONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE), UniformInt.of(6, 8), 2));

        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_GRAVEL, new DiskFeature(BlockStateProvider.holderOf(Blocks.GRAVEL), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE), UniformInt.of(8, 8), 4));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_CALCITE, new DiskFeature(BlockStateProvider.holderOf(Blocks.CALCITE), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE), UniformInt.of(6, 7), 3));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_BASALT, new DiskFeature(BlockStateProvider.holderOf(Blocks.SMOOTH_BASALT), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.CALCITE), UniformInt.of(5, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_PACKED_MUD, new DiskFeature(BlockStateProvider.holderOf(Blocks.PACKED_MUD), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.SMOOTH_BASALT, Blocks.CALCITE), UniformInt.of(4, 4), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_CINNABAR, new DiskFeature(BlockStateProvider.holderOf(Blocks.CINNABAR), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.SMOOTH_BASALT, Blocks.CALCITE), UniformInt.of(4, 5), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_ORPIMENT, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.ORPIMENT), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.SMOOTH_BASALT, Blocks.CALCITE, Blocks.CINNABAR), UniformInt.of(3, 4), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_SULFUR, new DiskFeature(BlockStateProvider.holderOf(Blocks.SULFUR), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.SMOOTH_BASALT, Blocks.CALCITE, Blocks.CINNABAR, BOPBlocks.ORPIMENT), UniformInt.of(1, 3), 1));
        register(context, BOPMiscOverworldFeatures.DISK_HOT_SPRING_SPHALERITE, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.SPHALERITE), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.PACKED_MUD, Blocks.SMOOTH_BASALT, Blocks.CALCITE, Blocks.CINNABAR, BOPBlocks.ORPIMENT, Blocks.SULFUR), UniformInt.of(1, 2), 1));

        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_SMOOTH_BASALT, new DiskFeature(BlockStateProvider.holderOf(Blocks.SMOOTH_BASALT), BlockPredicate.matchesBlocks(Blocks.TERRACOTTA, Blocks.DYED_TERRACOTTA.lightGray(), Blocks.DYED_TERRACOTTA.cyan(), Blocks.DYED_TERRACOTTA.lightBlue(), Blocks.DYED_TERRACOTTA.blue(), Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.MOSSY_BLACK_SAND), UniformInt.of(6, 8), 4));
        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_BLACK_SANDSTONE, new DiskFeature(BlockStateProvider.holderOf(BOPBlocks.BLACK_SANDSTONE), BlockPredicate.matchesBlocks(Blocks.TERRACOTTA, Blocks.DYED_TERRACOTTA.lightGray(), Blocks.DYED_TERRACOTTA.cyan(), Blocks.DYED_TERRACOTTA.lightBlue(), Blocks.DYED_TERRACOTTA.blue(), Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.BLACK_SANDSTONE, BOPBlocks.MOSSY_BLACK_SAND), UniformInt.of(4, 6), 3));
        register(context, BOPMiscOverworldFeatures.DISK_VOLCANO_MAGMA, new DiskFeature(BlockStateProvider.holderOf(Blocks.MAGMA_BLOCK), BlockPredicate.matchesBlocks(Blocks.TERRACOTTA, Blocks.DYED_TERRACOTTA.lightGray(), Blocks.DYED_TERRACOTTA.cyan(), Blocks.DYED_TERRACOTTA.lightBlue(), Blocks.DYED_TERRACOTTA.blue(), Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.GRASS_BLOCK, Blocks.SAND, Blocks.GRAVEL, Blocks.CLAY, Blocks.ANDESITE, Blocks.GRANITE, Blocks.DIORITE, Blocks.STONE, Blocks.BASALT, Blocks.SMOOTH_BASALT, BOPBlocks.BLACK_SAND, BOPBlocks.BLACK_SANDSTONE, BOPBlocks.MOSSY_BLACK_SAND), UniformInt.of(1, 3), 2));

        register(context, BOPMiscOverworldFeatures.DISK_SURFACE_CLAY, new DiskFeature(BlockStateProvider.holderOf(Blocks.CLAY), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(4, 6), 2));
        register(context, BOPMiscOverworldFeatures.DISK_MUD, new DiskFeature(BlockStateProvider.holderOf(Blocks.MUD), BlockPredicate.matchesBlocks(Blocks.DIRT, Blocks.GRASS_BLOCK), UniformInt.of(4, 6), 2));
        register(context, BOPMiscOverworldFeatures.MOSSY_BLACK_SAND_SPLATTER, new GrassSplatterFeature());
        register(context, BOPMiscOverworldFeatures.MUD_SPLATTER, new MudSplatterFeature());
        register(context, BOPMiscOverworldFeatures.WATER_LAKE, new BOPLakeFeature(BlockStateProvider.of(Blocks.WATER.defaultBlockState()), BlockStateProvider.of(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.HOT_SPRING_LAKE, new BOPLakeFeature(BlockStateProvider.of(Blocks.WATER.defaultBlockState()), BlockStateProvider.of(Blocks.AIR.defaultBlockState())));
        register(context, BOPMiscOverworldFeatures.OBSIDIAN_LAKE, new LakeFeature(BlockStateProvider.holderOf(Blocks.OBSIDIAN), BlockStateProvider.holderOf(BOPBlocks.BLACK_SANDSTONE), BlockPredicate.alwaysTrue(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE)), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE))));
        register(context, BOPMiscOverworldFeatures.LAVA_LAKE_VOLCANO, new LakeFeature(BlockStateProvider.holderOf(Blocks.LAVA), BlockStateProvider.holderOf(Blocks.AIR), BlockPredicate.alwaysTrue(), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.FEATURES_CANNOT_REPLACE)), BlockPredicate.not(BlockPredicate.matchesTag(BlockTags.LAVA_POOL_STONE_CANNOT_REPLACE))));
        register(context, BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO, new SpringFeature(Fluids.LAVA.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.BASALT, Blocks.SMOOTH_BASALT, Blocks.MAGMA_BLOCK, BOPBlocks.BLACK_SANDSTONE)));
        register(context, BOPMiscOverworldFeatures.SPRING_WATER_EXTRA, new SpringFeature(Fluids.WATER.defaultFluidState(), true, 4, 1, HolderSet.direct(Block::builtInRegistryHolder, Blocks.DIRT, Blocks.TERRACOTTA, Blocks.DYED_TERRACOTTA.lightGray(), Blocks.DYED_TERRACOTTA.cyan(), Blocks.DYED_TERRACOTTA.lightBlue(), Blocks.DYED_TERRACOTTA.blue(), Blocks.STONE, Blocks.GRANITE, Blocks.DIORITE, Blocks.ANDESITE, BOPBlocks.ORANGE_SANDSTONE)));
        register(context, BOPMiscOverworldFeatures.ORIGIN_GRAVEL_CLIFFS, new OriginGravelCliffFeature());

        register(context, BOPMiscOverworldFeatures.ERODED_PILLAR, new ErodedPillarFeature());
    }
    
    private static void register(BootstrapContext<Feature> context, ResourceKey<Feature> key, Feature feature)
    {
        context.register(key, feature);
    }
}
