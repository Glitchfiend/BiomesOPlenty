/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPMiscOverworldFeatures;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.blockpredicates.BlockPredicate;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.material.Fluids;
import net.minecraftforge.registries.RegistryObject;

import java.util.List;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;

public class BOPMiscOverworldPlacements
{
    public static final RegistryObject<PlacedFeature> BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER, () -> VegetationPlacements.worldSurfaceSquaredWithCount(15));
    public static final RegistryObject<PlacedFeature> BONE_SPINE = register("bone_spine", BOPMiscOverworldFeatures.BONE_SPINE, () -> List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> CRAG_SPLATTER = register("crag_splatter", BOPMiscOverworldFeatures.CRAG_SPLATTER, () -> List.of(CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_BLACK_SAND = register("disk_black_sand", BOPMiscOverworldFeatures.DISK_BLACK_SAND, () -> List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_CALCITE = register("disk_calcite", BOPMiscOverworldFeatures.DISK_CALCITE, () -> List.of(CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_GRAVEL_EXTRA = register("disk_gravel_extra", BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA, () -> List.of(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_MUD = register("disk_mud", BOPMiscOverworldFeatures.DISK_MUD, () -> List.of(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_ORANGE_SAND = register("disk_orange_sand", BOPMiscOverworldFeatures.DISK_ORANGE_SAND, () -> List.of(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_WHITE_SAND = register("disk_white_sand", BOPMiscOverworldFeatures.DISK_WHITE_SAND, () -> List.of(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> DISK_WHITE_SAND_EXTRA = register("disk_white_sand_extra", BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, () -> List.of(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BlockPredicateFilter.forPredicate(BlockPredicate.matchesFluids(Fluids.WATER)), BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> GRASS_SPLATTER = register("grass_splatter", BOPMiscOverworldFeatures.GRASS_SPLATTER, () -> List.of(CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> LAKE_LAVA_SURFACE_EXTRA = register("lake_lava_surface_extra", MiscOverworldFeatures.LAKE_LAVA, CountPlacement.of(15), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final RegistryObject<PlacedFeature> SPRING_LAVA_VOLCANO = register("spring_lava_volcano", BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO, () -> List.of(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final RegistryObject<PlacedFeature> SPRING_WATER_EXTRA = register("spring_water_extra", BOPMiscOverworldFeatures.SPRING_WATER_EXTRA, () -> List.of(CountPlacement.of(128), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome()));

    public static void setup() {}
}
