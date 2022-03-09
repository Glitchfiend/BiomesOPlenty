/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPMiscOverworldFeatures;
import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;

import static biomesoplenty.common.util.worldgen.BOPPlacementUtils.register;

public class BOPMiscOverworldPlacements
{
    public static final Holder<PlacedFeature> BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER, VegetationPlacements.worldSurfaceSquaredWithCount(15));
    public static final Holder<PlacedFeature> BONE_SPINE = register("bone_spine", BOPMiscOverworldFeatures.BONE_SPINE, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome());
    public static final Holder<PlacedFeature> CRAG_SPLATTER = register("crag_splatter", BOPMiscOverworldFeatures.CRAG_SPLATTER, CountPlacement.of(64), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_BLACK_SAND = register("disk_black_sand", BOPMiscOverworldFeatures.DISK_BLACK_SAND, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_CALCITE = register("disk_calcite", BOPMiscOverworldFeatures.DISK_CALCITE, CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_GRAVEL_EXTRA = register("disk_gravel_extra", BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA, CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_MUD = register("disk_mud", BOPMiscOverworldFeatures.DISK_MUD, CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_ORANGE_SAND = register("disk_orange_sand", BOPMiscOverworldFeatures.DISK_ORANGE_SAND, CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_WHITE_SAND = register("disk_white_sand", BOPMiscOverworldFeatures.DISK_WHITE_SAND, CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> DISK_WHITE_SAND_EXTRA = register("disk_white_sand_extra", BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA, CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> GRASS_SPLATTER = register("grass_splatter", BOPMiscOverworldFeatures.GRASS_SPLATTER, CountPlacement.of(1), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome());
    public static final Holder<PlacedFeature> LAKE_LAVA_SURFACE_EXTRA = register("lake_lava_surface_extra", MiscOverworldFeatures.LAKE_LAVA, CountPlacement.of(30), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome());
    public static final Holder<PlacedFeature> SPRING_LAVA_VOLCANO = register("spring_lava_volcano", BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO, CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome());
    public static final Holder<PlacedFeature> SPRING_WATER_EXTRA = register("spring_water_extra", BOPMiscOverworldFeatures.SPRING_WATER_EXTRA, CountPlacement.of(128), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome());
}
