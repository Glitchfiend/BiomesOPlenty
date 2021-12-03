/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPVegetationFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.features.VegetationFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraft.world.level.block.Blocks;
import static net.minecraft.data.worldgen.placement.VegetationPlacements.treePlacement;

public class BOPVegetationPlacements
{
    public static final PlacedFeature GOLDENROD_SPARSE = register("goldenrod_sparse", BOPVegetationFeatures.GOLDENROD.placed(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature GOLDENROD_DENSE = register("goldenrod_dense", BOPVegetationFeatures.GOLDENROD.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature ROSE_BUSH_LOW = register("rose_bush_low", BOPVegetationFeatures.ROSE_BUSH.placed(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_GRASS_LOW = register("patch_grass_low", VegetationFeatures.PATCH_GRASS.placed(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_TALL_GRASS_LOW = register("patch_tall_grass_low", VegetationFeatures.PATCH_TALL_GRASS.placed(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_SPARSE_FERN_GRASS = register("patch_sparse_fern_grass", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILY_PAD_5 = register("patch_lily_pad_5", VegetationFeatures.PATCH_WATERLILY.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature CLOVER_3 = register("patch_clover_3", BOPVegetationFeatures.PATCH_CLOVER.placed(CountPlacement.of(3), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILAC_2 = register("patch_lilac_2", BOPVegetationFeatures.PATCH_LILAC.placed(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SHORT_BAMBOO_5 = register("short_bamboo_5", BOPVegetationFeatures.SHORT_BAMBOO.placed(CountPlacement.of(5), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_GRASS_12 = register("patch_grass_12", VegetationFeatures.PATCH_GRASS.placed(CountPlacement.of(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature TOADSTOOL_NORMAL = register("toadstool_normal", BOPVegetationFeatures.TOADSTOOL_NORMAL.placed(RarityFilter.onAverageOnceEvery(32), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Flowers
    public static final PlacedFeature FLOWER_CONIFEROUS_FOREST = register("flower_coniferous_forest", BOPVegetationFeatures.FLOWER_CONIFEROUS_FOREST.placed(RarityFilter.onAverageOnceEvery(16), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_CHERRY_BLOSSOM_GROVE = register("flower_cherry_blossom_grove", BOPVegetationFeatures.FLOWER_CHERRY_BLOSSOM_GROVE.placed(CountPlacement.of(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_DEFAULT_EXTENDED = register("flower_default_extended", BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED.placed(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Trees
    public static final PlacedFeature TREES_CONIFEROUS_FOREST = register("trees_coniferous_forest", BOPVegetationFeatures.TREES_CONIFEROUS_FOREST.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
    public static final PlacedFeature TREES_BAMBOO_BLOSSOM_GROVE = register("trees_bamboo_blossom_grove", BOPVegetationFeatures.TREES_BAMBOO_BLOSSOM_GROVE.placed(treePlacement(PlacementUtils.countExtra(7, 0.1F, 1))));
    public static final PlacedFeature TREES_WOODLAND = PlacementUtils.register("trees_woodland", TreeFeatures.OAK.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1), Blocks.OAK_SAPLING)));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
