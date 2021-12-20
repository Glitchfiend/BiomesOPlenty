/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
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
    // Foliage
    public static final PlacedFeature CLOVER_3 = register("patch_clover_3", BOPVegetationFeatures.PATCH_CLOVER.placed(VegetationPlacements.worldSurfaceSquaredWithCount(3)));
    public static final PlacedFeature CLOVER_6 = register("patch_clover_6", BOPVegetationFeatures.PATCH_CLOVER.placed(VegetationPlacements.worldSurfaceSquaredWithCount(6)));
    public static final PlacedFeature HUGE_TOADSTOOL = register("huge_toadstool", BOPVegetationFeatures.HUGE_TOADSTOOL.placed(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_BUSH_10 = register("patch_bush_10", BOPVegetationFeatures.PATCH_BUSH.placed(VegetationPlacements.worldSurfaceSquaredWithCount(2)));
    public static final PlacedFeature PATCH_BUSH_15 = register("patch_bush_15", BOPVegetationFeatures.PATCH_BUSH.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4)));
    public static final PlacedFeature PATCH_DEAD_GRASS_2 = register("patch_dead_grass_2", BOPVegetationFeatures.PATCH_DEAD_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(1)));
    public static final PlacedFeature PATCH_DESERT_GRASS_10 = register("patch_desert_grass_10", BOPVegetationFeatures.PATCH_DESERT_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(5)));
    public static final PlacedFeature PATCH_DUNE_GRASS_128 = register("patch_dune_grass_128", BOPVegetationFeatures.PATCH_DUNE_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(10)));
    public static final PlacedFeature PATCH_FERN_GRASS_4 = register("patch_fern_grass_4", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4)));
    public static final PlacedFeature PATCH_FERN_GRASS_5 = register("patch_fern_grass_5", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(5)));
    public static final PlacedFeature PATCH_FERN_GRASS_8 = register("patch_fern_grass_8", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(8)));
    public static final PlacedFeature PATCH_FERN_GRASS_16 = register("patch_fern_grass_16", BOPVegetationFeatures.PATCH_FERN_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(16)));
    public static final PlacedFeature PATCH_GRASS_1 = register("patch_grass_1", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(1)));
    public static final PlacedFeature PATCH_GRASS_3 = register("patch_grass_3", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(3)));
    public static final PlacedFeature PATCH_GRASS_6 = register("patch_grass_6", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(6)));
    public static final PlacedFeature PATCH_GRASS_12 = register("patch_grass_12", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(12)));
    public static final PlacedFeature PATCH_GRASS_22 = register("patch_grass_22", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(22)));
    public static final PlacedFeature PATCH_GRASS_24 = register("patch_grass_24", VegetationFeatures.PATCH_GRASS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(24)));
    public static final PlacedFeature PATCH_LARGE_FERN_25 = register("patch_large_fern_25", VegetationFeatures.PATCH_LARGE_FERN.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_SPROUTS_5 = register("patch_sprouts_5", BOPVegetationFeatures.PATCH_SPROUTS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(2)));
    public static final PlacedFeature PATCH_SPROUTS_10 = register("patch_sprouts_10", BOPVegetationFeatures.PATCH_SPROUTS.placed(VegetationPlacements.worldSurfaceSquaredWithCount(4)));
    public static final PlacedFeature PATCH_TALL_GRASS_6 = register("patch_tall_grass_6", VegetationFeatures.PATCH_TALL_GRASS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_TALL_GRASS_12 = register("patch_tall_grass_12", VegetationFeatures.PATCH_TALL_GRASS.placed(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_TALL_GRASS_24 = register("patch_tall_grass_24", VegetationFeatures.PATCH_TALL_GRASS.placed(RarityFilter.onAverageOnceEvery(12), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_TOADSTOOL = register("small_toadstool", BOPVegetationFeatures.SMALL_TOADSTOOL.placed(RarityFilter.onAverageOnceEvery(6), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature WASTELAND_GRASS_1 = register("wasteland_grass_1", BOPVegetationFeatures.WASTELAND_GRASS.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature WASTELAND_GRASS_2 = register("wasteland_grass_2", BOPVegetationFeatures.WASTELAND_GRASS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Plants
    public static final PlacedFeature TOADSTOOL_NORMAL = register("toadstool_normal", BOPVegetationFeatures.TOADSTOOL_NORMAL.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature TOADSTOOL_EXTRA = register("toadstool_extra", BOPVegetationFeatures.TOADSTOOL_NORMAL.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Water Plants
    public static final PlacedFeature LILY_PAD_5 = register("patch_lily_pad_5", VegetationFeatures.PATCH_WATERLILY.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILY_PAD_10 = register("patch_lily_pad_10", VegetationFeatures.PATCH_WATERLILY.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_REED_5 = register("patch_reed_5", BOPVegetationFeatures.PATCH_REED.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_REED_10 = register("patch_reed_10", BOPVegetationFeatures.PATCH_REED.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PATCH_WATERGRASS_10 = register("patch_watergrass_10", BOPVegetationFeatures.PATCH_WATERGRASS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Flowers
    public static final PlacedFeature FLOWER_LAVENDER = register("flower_lavender", BOPVegetationFeatures.FLOWER_LAVENDER.placed(VegetationPlacements.worldSurfaceSquaredWithCount(20)));
    public static final PlacedFeature FLOWER_LUSH_DESERT = register("flower_lush_desert", BOPVegetationFeatures.FLOWER_WILDFLOWER.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_CONIFEROUS_FOREST = register("flower_coniferous_forest", BOPVegetationFeatures.FLOWER_CONIFEROUS_FOREST.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_CHERRY_BLOSSOM_GROVE = register("flower_cherry_blossom_grove", BOPVegetationFeatures.FLOWER_CHERRY_BLOSSOM_GROVE.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_DEFAULT_EXTENDED_6 = register("flower_default_extended_6", BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_DEFAULT_EXTENDED_15 = register("flower_default_extended_15", BOPVegetationFeatures.FLOWER_DEFAULT_EXTENDED.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_FIELD_1 = register("flower_field_1", BOPVegetationFeatures.FLOWER_FIELD_1.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_FIELD_2 = register("flower_field_2", BOPVegetationFeatures.FLOWER_FIELD_2.placed(RarityFilter.onAverageOnceEvery(8), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_RAINFOREST = register("flower_rainforest", BOPVegetationFeatures.FLOWER_RAINFOREST.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_SCRUBLAND = register("flower_scrubland", BOPVegetationFeatures.FLOWER_WILDFLOWER.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_SHRUBLAND = register("flower_shrubland", BOPVegetationFeatures.FLOWER_SHRUBLAND.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_SNOWY = register("flower_snowy", BOPVegetationFeatures.FLOWER_SNOWY.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature FLOWER_WASTELAND = register("flower_wasteland", BOPVegetationFeatures.FLOWER_WASTELAND.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Large flowers
    public static final PlacedFeature GOLDENROD_2 = register("goldenrod_2", BOPVegetationFeatures.GOLDENROD.placed(RarityFilter.onAverageOnceEvery(2), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature GOLDENROD_10 = register("goldenrod_10", BOPVegetationFeatures.GOLDENROD.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILAC_1 = register("patch_lilac_1", BOPVegetationFeatures.PATCH_LILAC.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILAC_2 = register("patch_lilac_2", BOPVegetationFeatures.PATCH_LILAC.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature LILAC_4 = register("patch_lilac_4", BOPVegetationFeatures.PATCH_LILAC.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PEONY_1 = register("patch_peony_1", BOPVegetationFeatures.PATCH_PEONY.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature ROSE_BUSH_1 = register("rose_bush_1", BOPVegetationFeatures.ROSE_BUSH.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature ROSE_BUSH_2 = register("rose_bush_2", BOPVegetationFeatures.ROSE_BUSH.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SUNFLOWER_1 = register("sunflower_1", BOPVegetationFeatures.SUNFLOWER.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    // Trees
    public static final PlacedFeature TREES_BAMBOO_BLOSSOM_GROVE = register("trees_bamboo_blossom_grove", BOPVegetationFeatures.TREES_BAMBOO_BLOSSOM_GROVE.placed(treePlacement(PlacementUtils.countExtra(7, 0.1F, 1))));
    public static final PlacedFeature TREES_CHERRY_BLOSSOM_GROVE = register("trees_cherry_blossom_grove", BOPVegetationFeatures.TREES_CHERRY_BLOSSOM_GROVE.placed(treePlacement(PlacementUtils.countExtra(5, 0.1F, 1))));
    public static final PlacedFeature TREES_CONIFEROUS_FOREST = register("trees_coniferous_forest", BOPVegetationFeatures.TREES_CONIFEROUS_FOREST.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
    public static final PlacedFeature TREES_DEAD_FOREST = register("trees_dead_forest", BOPVegetationFeatures.TREES_DEAD_FOREST.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_DENSE_WOODLAND = register("trees_dense_woodland", BOPVegetationFeatures.TREES_DENSE_WOODLAND.placed(treePlacement(PlacementUtils.countExtra(12, 0.1F, 1))));
    public static final PlacedFeature TREES_DRYLAND = register("trees_dryland", BOPVegetationFeatures.TREES_DRYLAND.placed(treePlacement(PlacementUtils.countExtra(2, 0.1F, 1))));
    public static final PlacedFeature TREES_FIELD = register("trees_field", BOPVegetationFeatures.TREES_FIELD.placed(treePlacement(PlacementUtils.countExtra(4, 0.1F, 1))));
    public static final PlacedFeature TREES_FIR_CLEARING = register("trees_fir_clearing", BOPTreeFeatures.FIR_TREE_SMALL.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1), BOPBlocks.FIR_SAPLING)));
    public static final PlacedFeature TREES_LAVENDER_FIELD = register("trees_lavender_field", BOPVegetationFeatures.TREES_LAVENDER_FIELD.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_LAVENDER_FOREST = register("trees_lavender_forest", BOPVegetationFeatures.TREES_LAVENDER_FOREST.placed(treePlacement(PlacementUtils.countExtra(7, 0.1F, 1))));
    public static final PlacedFeature TREES_LUSH_DESERT = register("trees_lush_desert", BOPVegetationFeatures.TREES_LUSH_DESERT.placed(treePlacement(PlacementUtils.countExtra(5, 0.1F, 1))));
    public static final PlacedFeature TREES_MEDITERRANEAN_FOREST = register("trees_mediterranean_forest", BOPVegetationFeatures.TREES_MEDITERRANEAN_FOREST.placed(treePlacement(PlacementUtils.countExtra(3, 0.1F, 1))));
    public static final PlacedFeature TREES_ORCHARD = register("trees_orchard", BOPVegetationFeatures.TREES_ORCHARD.placed(treePlacement(PlacementUtils.countExtra(2, 0.2F, 1))));
    public static final PlacedFeature TREES_PRAIRIE = register("trees_prairie", BOPVegetationFeatures.TREES_PRAIRIE.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_WASTELAND = register("trees_wasteland", BOPVegetationFeatures.TREES_WASTELAND.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));
    public static final PlacedFeature TREES_WOODED_SCRUBLAND = register("trees_wooded_scrubland", BOPVegetationFeatures.TREES_WOODED_SCRUBLAND.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))));
    public static final PlacedFeature TREES_WOODLAND = register("trees_woodland", TreeFeatures.OAK.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1), Blocks.OAK_SAPLING)));
    public static final PlacedFeature TREES_RAINFOREST = register("trees_rainforest", BOPVegetationFeatures.TREES_RAINFOREST.placed(treePlacement(PlacementUtils.countExtra(12, 0.2F, 1))));
    public static final PlacedFeature TREES_REDWOOD_FOREST = register("trees_redwood_forest", BOPVegetationFeatures.TREES_REDWOOD_FOREST.placed(treePlacement(PlacementUtils.countExtra(16, 0.2F, 1))));
    public static final PlacedFeature TREES_SHRUBLAND = register("trees_shrubland", BOPTreeFeatures.OAK_BUSH.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1), Blocks.OAK_SAPLING)));
    public static final PlacedFeature TREES_SEASONAL_FOREST = register("trees_seasonal_forest", BOPVegetationFeatures.TREES_SEASONAL_FOREST.placed(treePlacement(PlacementUtils.countExtra(10, 0.1F, 1))));
    public static final PlacedFeature TREES_SEASONAL_PUMPKIN_PATCH = register("trees_seasonal_pumpkin_patch", BOPVegetationFeatures.TREES_SEASONAL_PUMPKIN_PATCH.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))));
    public static final PlacedFeature TREES_SNOWY_CONIFEROUS_FOREST = register("trees_snowy_coniferous_forest", BOPVegetationFeatures.TREES_SNOWY_CONIFEROUS_FOREST.placed(treePlacement(PlacementUtils.countExtra(6, 0.1F, 1))));
    public static final PlacedFeature TREES_TUNDRA = register("trees_tundra", BOPVegetationFeatures.TREES_TUNDRA.placed(treePlacement(PlacementUtils.countExtra(1, 0.1F, 1))));

    // Misc
    public static final PlacedFeature BIG_DRIPLEAF = register("big_dripleaf", BOPVegetationFeatures.BIG_DRIPLEAF.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature BIG_PUMPKIN = register("big_pumpkin", BOPVegetationFeatures.BIG_PUMPKIN.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature MOSS_SPLATTER = register("moss_splatter", BOPVegetationFeatures.MOSS_SPLATTER.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature PUMPKIN_PATCH = register("pumpkin_patch", BOPVegetationFeatures.PUMPKIN_PATCH.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SCATTERED_ROCKS = register("scattered_rocks", BOPVegetationFeatures.SCATTERED_ROCKS.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SCRUB_EXTRA = register("scrub_extra", BOPVegetationFeatures.SCRUB.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SCRUB_NORMAL = register("scrub_normal", BOPVegetationFeatures.SCRUB.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SHORT_BAMBOO_5 = register("short_bamboo_5", BOPVegetationFeatures.SHORT_BAMBOO.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_DRIPLEAF = register("small_dripleaf", BOPVegetationFeatures.SMALL_DRIPLEAF.placed(RarityFilter.onAverageOnceEvery(4), InSquarePlacement.spread(), PlacementUtils.HEIGHTMAP, BiomeFilter.biome()));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
