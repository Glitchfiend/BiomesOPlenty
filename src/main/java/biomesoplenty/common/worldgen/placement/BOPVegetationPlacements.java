/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import com.google.common.collect.ImmutableList;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BOPVegetationPlacements
{
    // Foliage
    public static final RegistryObject<PlacedFeature> CLOVER_NORMAL = register("patch_clover_normal", BOPVegetationFeatures.PATCH_CLOVER, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> CLOVER_EXTRA = register("patch_clover_extra", BOPVegetationFeatures.PATCH_CLOVER, () -> VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final RegistryObject<PlacedFeature> HUGE_TOADSTOOL = register("huge_toadstool", BOPVegetationFeatures.HUGE_TOADSTOOL, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> HUGE_CLOVER = register("huge_clover", BOPVegetationFeatures.HUGE_CLOVER, () -> VegetationPlacements.worldSurfaceSquaredWithCount(6));
    public static final RegistryObject<PlacedFeature> HUGE_RED_MUSHROOM_EXTRA = register("huge_red_mushroom_extra", TreeFeatures.HUGE_RED_MUSHROOM, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_BARLEY = register("patch_barley", BOPVegetationFeatures.PATCH_BARLEY, () -> VegetationPlacements.worldSurfaceSquaredWithCount(10));
    public static final RegistryObject<PlacedFeature> PATCH_BLUE_HYDRANGEA = register("patch_blue_hydrangea", BOPVegetationFeatures.PATCH_BLUE_HYDRANGEA, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_BUSH_1 = register("patch_bush_1", BOPVegetationFeatures.PATCH_BUSH, () -> VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final RegistryObject<PlacedFeature> PATCH_BUSH_4 = register("patch_bush_4", BOPVegetationFeatures.PATCH_BUSH, () -> VegetationPlacements.worldSurfaceSquaredWithCount(3));
    public static final RegistryObject<PlacedFeature> PATCH_BUSH_10 = register("patch_bush_10", BOPVegetationFeatures.PATCH_BUSH, () -> VegetationPlacements.worldSurfaceSquaredWithCount(10));
    public static final RegistryObject<PlacedFeature> PATCH_CATTAIL_NORMAL = register("patch_cattail_normal", BOPVegetationFeatures.PATCH_CATTAIL, () -> List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_CATTAIL_EXTRA = register("patch_cattail_extra", BOPVegetationFeatures.PATCH_CATTAIL, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_DEAD_GRASS = register("patch_dead_grass", BOPVegetationFeatures.PATCH_DEAD_GRASS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final RegistryObject<PlacedFeature> PATCH_DESERT_GRASS = register("patch_desert_grass", BOPVegetationFeatures.PATCH_DESERT_GRASS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final RegistryObject<PlacedFeature> PATCH_DUNE_GRASS = register("patch_dune_grass", BOPVegetationFeatures.PATCH_DUNE_GRASS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(5));
    public static final RegistryObject<PlacedFeature> PATCH_FERN_2 = register("patch_fern_2", BOPVegetationFeatures.PATCH_FERN, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> PATCH_FERN_4 = register("patch_fern_4", BOPVegetationFeatures.PATCH_FERN, () -> VegetationPlacements.worldSurfaceSquaredWithCount(4));
    public static final RegistryObject<PlacedFeature> PATCH_FERN_8 = register("patch_fern_8", BOPVegetationFeatures.PATCH_FERN, () -> VegetationPlacements.worldSurfaceSquaredWithCount(8));
    public static final RegistryObject<PlacedFeature> PATCH_FERN_16 = register("patch_fern_16", BOPVegetationFeatures.PATCH_FERN, () -> VegetationPlacements.worldSurfaceSquaredWithCount(16));
    public static final RegistryObject<PlacedFeature> PATCH_GRASS_1 = register("patch_grass_1", VegetationFeatures.PATCH_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final RegistryObject<PlacedFeature> PATCH_GRASS_3 = register("patch_grass_3", VegetationFeatures.PATCH_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(3));
    public static final RegistryObject<PlacedFeature> PATCH_GRASS_6 = register("patch_grass_6", VegetationFeatures.PATCH_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(6));
    public static final RegistryObject<PlacedFeature> PATCH_GRASS_12 = register("patch_grass_12", VegetationFeatures.PATCH_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(12));
    public static final RegistryObject<PlacedFeature> PATCH_GRASS_24 = register("patch_grass_24", VegetationFeatures.PATCH_GRASS, VegetationPlacements.worldSurfaceSquaredWithCount(24));
    public static final RegistryObject<PlacedFeature> PATCH_LARGE_FERN_NORMAL = register("patch_large_fern_normal", VegetationFeatures.PATCH_LARGE_FERN, RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_LARGE_FERN_EXTRA = register("patch_large_fern_extra", VegetationFeatures.PATCH_LARGE_FERN, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_SEA_OATS = register("patch_sea_oats", BOPVegetationFeatures.PATCH_SEA_OATS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(3));
    public static final RegistryObject<PlacedFeature> PATCH_SPROUTS_5 = register("patch_sprouts_5", BOPVegetationFeatures.PATCH_SPROUTS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(1));
    public static final RegistryObject<PlacedFeature> PATCH_SPROUTS_10 = register("patch_sprouts_10", BOPVegetationFeatures.PATCH_SPROUTS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> PATCH_SPROUTS_15 = register("patch_sprouts_15", BOPVegetationFeatures.PATCH_SPROUTS, () -> VegetationPlacements.worldSurfaceSquaredWithCount(6));
    public static final RegistryObject<PlacedFeature> PATCH_TALL_GRASS_6 = register("patch_tall_grass_6", VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_TALL_GRASS_12 = register("patch_tall_grass_12", VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_TALL_GRASS_24 = register("patch_tall_grass_24", VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_TALL_GRASS_64 = register("patch_tall_grass_64", VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_TALL_GRASS_250 = register("patch_tall_grass_250", VegetationFeatures.PATCH_TALL_GRASS, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> RAINFOREST_CLIFFS_VINES = register("rainforest_cliffs_vines", BOPVegetationFeatures.RAINFOREST_CLIFFS_VINES, () -> VegetationPlacements.worldSurfaceSquaredWithCount(10));
    public static final RegistryObject<PlacedFeature> SMALL_TOADSTOOL = register("small_toadstool", BOPVegetationFeatures.SMALL_TOADSTOOL, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> SMALL_RED_MUSHROOM = register("small_red_mushroom", BOPVegetationFeatures.SMALL_RED_MUSHROOM, () -> List.of(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> WASTELAND_GRASS_1 = register("wasteland_grass_1", BOPVegetationFeatures.WASTELAND_GRASS, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> WASTELAND_GRASS_2 = register("wasteland_grass_2", BOPVegetationFeatures.WASTELAND_GRASS, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DRIPSTONE_SPLATTER = register("dripstone_splatter", BOPVegetationFeatures.DRIPSTONE_SPLATTER, () -> List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> MYCELIUM_SPLATTER = register("mycelium_splatter", BOPVegetationFeatures.MYCELIUM_SPLATTER, () -> List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Plants
    public static final RegistryObject<PlacedFeature> TOADSTOOL_NORMAL = register("toadstool_normal", BOPVegetationFeatures.TOADSTOOL_NORMAL, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> TOADSTOOL_EXTRA = register("toadstool_extra", BOPVegetationFeatures.TOADSTOOL_NORMAL, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));

    // Water Plants
    public static final RegistryObject<PlacedFeature> LILY_PAD_5 = register("patch_lily_pad_5", VegetationFeatures.PATCH_WATERLILY, RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> LILY_PAD_10 = register("patch_lily_pad_10", VegetationFeatures.PATCH_WATERLILY, RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> LILY_PAD_15 = register("patch_lily_pad_15", VegetationFeatures.PATCH_WATERLILY, RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> PATCH_REED_5 = register("patch_reed_5", BOPVegetationFeatures.PATCH_REED, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_REED_10 = register("patch_reed_10", BOPVegetationFeatures.PATCH_REED, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_WATERGRASS_10 = register("patch_watergrass_10", BOPVegetationFeatures.PATCH_WATERGRASS, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PATCH_WATERGRASS_50 = register("patch_watergrass_50", BOPVegetationFeatures.PATCH_WATERGRASS, () -> AquaticPlacements.seagrassPlacement(50));
    public static final RegistryObject<PlacedFeature> PATCH_WATERGRASS_250 = register("patch_watergrass_250", BOPVegetationFeatures.PATCH_WATERGRASS, () -> AquaticPlacements.seagrassPlacement(100));

    // Flowers
    public static final RegistryObject<PlacedFeature> FLOWER_LAVENDER = register("flower_lavender", BOPVegetationFeatures.FLOWER_LAVENDER, () -> VegetationPlacements.worldSurfaceSquaredWithCount(20));
    public static final RegistryObject<PlacedFeature> FLOWER_LUSH_DESERT = register("flower_lush_desert", BOPVegetationFeatures.FLOWER_WILDFLOWER, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_CLOVER_PATCH = register("flower_clover_patch", BOPVegetationFeatures.FLOWER_CLOVER_PATCH, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_CONIFEROUS_FOREST = register("flower_coniferous_forest", BOPVegetationFeatures.FLOWER_CONIFEROUS_FOREST, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_CHERRY_BLOSSOM_GROVE = register("flower_cherry_blossom_grove", BOPVegetationFeatures.FLOWER_CHERRY_BLOSSOM_GROVE, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_DEFAULT_EXTENDED_6 = register("flower_default_extended_6", BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_DEFAULT_EXTENDED_15 = register("flower_default_extended_15", BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_FIELD_1 = register("flower_field_1", BOPVegetationFeatures.FLOWER_FIELD_1, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_FIELD_2 = register("flower_field_2", BOPVegetationFeatures.FLOWER_FIELD_2, () -> List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_MOOR = register("flower_moor", BOPVegetationFeatures.FLOWER_MOOR, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_MYSTIC_GROVE = register("flower_mystic_grove", BOPVegetationFeatures.FLOWER_MYSTIC_GROVE, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_OMINOUS_WOODS = register("flower_ominous_woods", BOPVegetationFeatures.FLOWER_OMINOUS_WOODS, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_ORIGIN_VALLEY = register("flower_origin_valley", BOPVegetationFeatures.FLOWER_ORIGIN_VALLEY, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_POPPY = register("flower_poppy", BOPVegetationFeatures.FLOWER_POPPY, () -> VegetationPlacements.worldSurfaceSquaredWithCount(15));
    public static final RegistryObject<PlacedFeature> FLOWER_RAINFOREST = register("flower_rainforest", BOPVegetationFeatures.FLOWER_RAINFOREST, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_SCRUBLAND = register("flower_scrubland", BOPVegetationFeatures.FLOWER_WILDFLOWER, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_SHRUBLAND = register("flower_shrubland", BOPVegetationFeatures.FLOWER_SHRUBLAND, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_SNOWY = register("flower_snowy", BOPVegetationFeatures.FLOWER_SNOWY, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_TROPICS = register("flower_tropics", BOPVegetationFeatures.FLOWER_TROPICS, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_VIOLET = register("flower_violet", BOPVegetationFeatures.FLOWER_VIOLET, () -> VegetationPlacements.worldSurfaceSquaredWithCount(3));
    public static final RegistryObject<PlacedFeature> FLOWER_WASTELAND = register("flower_wasteland", BOPVegetationFeatures.FLOWER_WASTELAND, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> FLOWER_WETLAND = register("flower_wetland", BOPVegetationFeatures.FLOWER_WETLAND, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Large flowers
    public static final RegistryObject<PlacedFeature> GOLDENROD_NORMAL = register("goldenrod_normal", BOPVegetationFeatures.GOLDENROD, () -> List.of(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> GOLDENROD_EXTRA = register("goldenrod_extra", BOPVegetationFeatures.GOLDENROD, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> LILAC_NORMAL = register("patch_lilac_normal", BOPVegetationFeatures.PATCH_LILAC, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> LILAC_EXTRA = register("patch_lilac_extra", BOPVegetationFeatures.PATCH_LILAC, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PEONY = register("patch_peony", BOPVegetationFeatures.PATCH_PEONY, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> ROSE_BUSH_NORMAL = register("rose_bush_normal", BOPVegetationFeatures.ROSE_BUSH, () -> List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> ROSE_BUSH_EXTRA = register("rose_bush_extra", BOPVegetationFeatures.ROSE_BUSH, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SUNFLOWER = register("sunflower", BOPVegetationFeatures.SUNFLOWER, () -> List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Trees
    public static final RegistryObject<PlacedFeature> TREES_BAMBOO_GROVE = register("trees_bamboo_grove", BOPVegetationFeatures.TREES_BAMBOO_GROVE, () -> treePlacement(PlacementUtils.countExtra(7, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_BAYOU = register("trees_bayou", BOPVegetationFeatures.TREES_BAYOU, () -> waterTreePlacement(PlacementUtils.countExtra(2, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_BOG = register("trees_bog", BOPVegetationFeatures.TREES_BOG, () -> treePlacement(PlacementUtils.countExtra(16, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_BOREAL_FOREST = register("trees_boreal_forest", BOPVegetationFeatures.TREES_BOREAL_FOREST, () -> treePlacement(PlacementUtils.countExtra(3, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_CHERRY_BLOSSOM_GROVE = register("trees_cherry_blossom_grove", BOPVegetationFeatures.TREES_CHERRY_BLOSSOM_GROVE, () -> treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_CONIFEROUS_FOREST = register("trees_coniferous_forest", BOPVegetationFeatures.TREES_CONIFEROUS_FOREST, () -> treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_DEAD_FOREST = register("trees_dead_forest", BOPVegetationFeatures.TREES_DEAD_FOREST, () -> treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_DENSE_WOODLAND = register("trees_dense_woodland", BOPVegetationFeatures.TREES_DENSE_WOODLAND, () -> treePlacement(PlacementUtils.countExtra(12, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_DRYLAND = register("trees_dryland", BOPVegetationFeatures.TREES_DRYLAND, () -> treePlacement(PlacementUtils.countExtra(2, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_FIELD = register("trees_field", BOPVegetationFeatures.TREES_FIELD, () -> treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_FIELD_FOREST = register("trees_field_forest", BOPVegetationFeatures.TREES_FIELD_FOREST, () -> treePlacement(PlacementUtils.countExtra(9, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_FUNGAL_JUNGLE = register("trees_fungal_jungle", BOPVegetationFeatures.TREES_FUNGAL_JUNGLE, () -> treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_FIR_CLEARING = register("trees_fir_clearing", BOPTreeFeatures.FIR_TREE_SMALL, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1), BOPBlocks.FIR_SAPLING.get()));
    public static final RegistryObject<PlacedFeature> TREES_JADE_CLIFFS = register("trees_jade_cliffs", BOPVegetationFeatures.TREES_JADE_CLIFFS, () -> treePlacement(PlacementUtils.countExtra(25, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_LAVENDER_FIELD = register("trees_lavender_field", BOPVegetationFeatures.TREES_LAVENDER_FIELD, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_LAVENDER_FOREST = register("trees_lavender_forest", BOPVegetationFeatures.TREES_LAVENDER_FOREST, () -> treePlacement(PlacementUtils.countExtra(7, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_LUSH_DESERT = register("trees_lush_desert", BOPVegetationFeatures.TREES_LUSH_DESERT, () -> treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_MAPLE_FOREST = register("trees_maple_forest", BOPVegetationFeatures.TREES_MAPLE_FOREST, () -> treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_MEDITERRANEAN_FOREST = register("trees_mediterranean_forest", BOPVegetationFeatures.TREES_MEDITERRANEAN_FOREST, () -> treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_MUSKEG = register("trees_muskeg", BOPVegetationFeatures.TREES_MUSKEG, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_OLD_GROWTH_DEAD_FOREST = register("trees_old_growth_dead_forest", BOPVegetationFeatures.TREES_OLD_GROWTH_DEAD_FOREST, () -> treePlacement(PlacementUtils.countExtra(5, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_MYSTIC_GROVE = register("trees_mystic_grove", BOPVegetationFeatures.TREES_MYSTIC_GROVE, () -> treePlacement(PlacementUtils.countExtra(9, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_OMINOUS_WOODS = register("trees_ominous_woods", BOPVegetationFeatures.TREES_OMINOUS_WOODS, () -> treePlacement(PlacementUtils.countExtra(6, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_ORCHARD = register("trees_orchard", BOPVegetationFeatures.TREES_ORCHARD, () -> treePlacement(PlacementUtils.countExtra(2, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_ORIGIN_VALLEY = register("trees_origin_valley", BOPVegetationFeatures.TREES_ORIGIN_VALLEY, () -> treePlacement(PlacementUtils.countExtra(2, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_PRAIRIE = register("trees_prairie", BOPVegetationFeatures.TREES_PRAIRIE, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_WASTELAND = register("trees_wasteland", BOPVegetationFeatures.TREES_WASTELAND, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_WETLAND = register("trees_wetland", BOPVegetationFeatures.TREES_WETLAND, () -> treePlacement(PlacementUtils.countExtra(7, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_WOODED_SCRUBLAND = register("trees_wooded_scrubland", BOPVegetationFeatures.TREES_WOODED_SCRUBLAND, () -> treePlacement(PlacementUtils.countExtra(6, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_WOODLAND = register("trees_woodland", TreeFeatures.OAK, treePlacement(PlacementUtils.countExtra(6, 0.1F, 1), Blocks.OAK_SAPLING));
    public static final RegistryObject<PlacedFeature> TREES_RAINBOW_HILLS = register("trees_rainbow_hills", BOPVegetationFeatures.TREES_RAINBOW_HILLS, () -> treePlacement(PlacementUtils.countExtra(10, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_RAINFOREST = register("trees_rainforest", BOPVegetationFeatures.TREES_RAINFOREST, () -> treePlacement(PlacementUtils.countExtra(12, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_RAINFOREST_CLIFFS = register("trees_rainforest_cliffs", BOPVegetationFeatures.TREES_RAINFOREST_CLIFFS, () -> treePlacement(PlacementUtils.countExtra(20, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_RAINFOREST_FLOODPLAIN = register("trees_rainforest_floodplain", BOPVegetationFeatures.TREES_RAINFOREST_FLOODPLAIN, () -> treePlacement(PlacementUtils.countExtra(3, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_REDWOOD_FOREST = register("trees_redwood_forest", BOPVegetationFeatures.TREES_REDWOOD_FOREST, () -> treePlacement(PlacementUtils.countExtra(12, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_ROCKY_SHRUBLAND = register("trees_rocky_shrubland", BOPTreeFeatures.OAK_BUSH, () -> treePlacement(PlacementUtils.countExtra(4, 0.05F, 1), Blocks.OAK_SAPLING));
    public static final RegistryObject<PlacedFeature> TREES_SHRUBLAND = register("trees_shrubland", BOPTreeFeatures.OAK_BUSH, () -> treePlacement(PlacementUtils.countExtra(8, 0.1F, 1), Blocks.OAK_SAPLING));
    public static final RegistryObject<PlacedFeature> TREES_SEASONAL_FOREST = register("trees_seasonal_forest", BOPVegetationFeatures.TREES_SEASONAL_FOREST, () -> treePlacement(PlacementUtils.countExtra(10, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_SEASONAL_PUMPKIN_PATCH = register("trees_seasonal_pumpkin_patch", BOPVegetationFeatures.TREES_SEASONAL_PUMPKIN_PATCH, () -> treePlacement(PlacementUtils.countExtra(6, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_SNOWY_CONIFEROUS_FOREST = register("trees_snowy_coniferous_forest", BOPVegetationFeatures.TREES_SNOWY_CONIFEROUS_FOREST, () -> treePlacement(PlacementUtils.countExtra(6, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_SNOWY_MAPLE_FOREST = register("trees_snowy_maple_forest", BOPVegetationFeatures.TREES_SNOWY_MAPLE_FOREST, () -> treePlacement(PlacementUtils.countExtra(7, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_TROPICS = register("trees_tropics", BOPVegetationFeatures.TREES_TROPICS, () -> treePlacement(PlacementUtils.countExtra(11, 0.2F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_TUNDRA = register("trees_tundra", BOPVegetationFeatures.TREES_TUNDRA, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));
    public static final RegistryObject<PlacedFeature> TREES_VOLCANIC_PLAINS = register("trees_volcanic_plains", BOPTreeFeatures.TWIGLET_TREE_VOLCANO, () -> treePlacement(PlacementUtils.countExtra(1, 0.1F, 1)));

    // Misc
    public static final RegistryObject<PlacedFeature> BIG_DRIPLEAF = register("big_dripleaf", BOPVegetationFeatures.BIG_DRIPLEAF, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> BIG_PUMPKIN = register("big_pumpkin", BOPVegetationFeatures.BIG_PUMPKIN, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> BRAMBLE = register("bramble", BOPVegetationFeatures.BRAMBLE, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> MOSS_SPLATTER = register("moss_splatter", BOPVegetationFeatures.MOSS_SPLATTER, () -> List.of(RarityFilter.onAverageOnceEvery(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> PUMPKIN_PATCH = register("pumpkin_patch", BOPVegetationFeatures.PUMPKIN_PATCH, () -> List.of(RarityFilter.onAverageOnceEvery(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SCATTERED_ROCKS = register("scattered_rocks", BOPVegetationFeatures.SCATTERED_ROCKS, () -> List.of(RarityFilter.onAverageOnceEvery(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SCRUB_EXTRA = register("scrub_extra", BOPVegetationFeatures.SCRUB, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SCRUB_NORMAL = register("scrub_normal", BOPVegetationFeatures.SCRUB, () -> List.of(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SHORT_BAMBOO = register("short_bamboo", BOPVegetationFeatures.SHORT_BAMBOO, () -> VegetationPlacements.worldSurfaceSquaredWithCount(2));
    public static final RegistryObject<PlacedFeature> SMALL_DRIPLEAF = register("small_dripleaf", BOPVegetationFeatures.SMALL_DRIPLEAF, () -> List.of(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    public static List<PlacementModifier> waterTreePlacement(PlacementModifier modifier)
    {
        return ImmutableList.<PlacementModifier>builder().add(modifier).add(InSquarePlacement.spread()).add(PlacementUtils.HEIGHTMAP_TOP_SOLID).add(BiomeFilter.biome()).build();
    }

    public static void setup() {}
}
