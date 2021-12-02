/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.placement.RarityFilter;

import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BOPVegetationPlacements
{
    public static final PlacedFeature PATCH_SPARSE_FERN_GRASS = register("patch_sparse_fern_grass", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature TOADSTOOL_NORMAL = register("toadstool_normal", BOPVegetationFeatures.TOADSTOOL_NORMAL.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Flowers
    public static final PlacedFeature FLOWER_CONIFEROUS_FOREST = register("flower_coniferous_forest", BOPVegetationFeatures.FLOWER_CONIFEROUS_FOREST.placed(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Trees
    public static final PlacedFeature TREES_CONIFEROUS_FOREST = register("trees_coniferous_forest", BOPVegetationFeatures.TREES_CONIFEROUS_FOREST.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
