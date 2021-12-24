/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPMiscOverworldFeatures;
import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.MiscOverworldFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.valueproviders.ConstantInt;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.VeryBiasedToBottomHeight;
import net.minecraft.world.level.levelgen.placement.*;

public class BOPMiscOverworldPlacements
{
    public static final PlacedFeature BLACK_SAND_SPLATTER = register("black_sand_splatter", BOPMiscOverworldFeatures.BLACK_SAND_SPLATTER.placed(VegetationPlacements.worldSurfaceSquaredWithCount(15)));
    public static final PlacedFeature BONE_SPINE = register("bone_spine", BOPMiscOverworldFeatures.BONE_SPINE.placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature CRAG_SPLATTER = register("crag_splatter", BOPMiscOverworldFeatures.CRAG_SPLATTER.placed(CountPlacement.of(255), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_BLACK_SAND = register("disk_black_sand", BOPMiscOverworldFeatures.DISK_BLACK_SAND.placed(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_CALCITE = register("disk_calcite", BOPMiscOverworldFeatures.DISK_CALCITE.placed(CountPlacement.of(14), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_GRAVEL_EXTRA = register("disk_gravel_extra", BOPMiscOverworldFeatures.DISK_GRAVEL_EXTRA.placed(CountPlacement.of(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_MUD = register("disk_mud", BOPMiscOverworldFeatures.DISK_MUD.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_ORANGE_SAND = register("disk_orange_sand", BOPMiscOverworldFeatures.DISK_ORANGE_SAND.placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_WHITE_SAND = register("disk_white_sand", BOPMiscOverworldFeatures.DISK_WHITE_SAND.placed(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature DISK_WHITE_SAND_EXTRA = register("disk_white_sand_extra", BOPMiscOverworldFeatures.DISK_WHITE_SAND_EXTRA.placed(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature GRASS_SPLATTER = register("grass_splatter", BOPMiscOverworldFeatures.GRASS_SPLATTER.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_TOP_SOLID, BiomeFilter.biome()));
    public static final PlacedFeature LAKE_LAVA_SURFACE_EXTRA = register("lake_lava_surface_extra", MiscOverworldFeatures.LAKE_LAVA.placed(CountPlacement.of(30), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP_WORLD_SURFACE, BiomeFilter.biome()));
    public static final PlacedFeature SPRING_LAVA_VOLCANO = register("spring_lava_volcano", BOPMiscOverworldFeatures.SPRING_LAVA_VOLCANO.placed(CountPlacement.of(128), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature SPRING_WATER_EXTRA = register("spring_water_extra", BOPMiscOverworldFeatures.SPRING_WATER_EXTRA.placed(CountPlacement.of(128), InSquarePlacement.spread(), HeightRangePlacement.uniform(VerticalAnchor.bottom(), VerticalAnchor.absolute(192)), BiomeFilter.biome()));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
