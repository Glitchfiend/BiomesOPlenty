/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.common.worldgen.feature.BOPNetherFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.NetherFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPNetherPlacements
{
    public static final PlacedFeature BLACKSTONE_BULBS = register("blackstone_bulbs", BOPNetherFeatures.BLACKSTONE_BULB.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature BLACKSTONE_SPINES = register("blackstone_spines", BOPNetherFeatures.BLACKSTONE_SPINES.placed(CountPlacement.of(160), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature LARGE_ROSE_QUARTZ = register("large_rose_quartz", BOPNetherFeatures.LARGE_ROSE_QUARTZ.placed(CountPlacement.of(8), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature OBSIDIAN_SPLATTER = register("obsidian_splatter", BOPNetherFeatures.OBSIDIAN_SPLATTER.placed(CountPlacement.of(16), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));
    public static final PlacedFeature SMALL_CRYSTAL = register("small_crystal", BOPNetherFeatures.SMALL_CRYSTAL.placed(CountPlacement.of(120), InSquarePlacement.spread(), PlacementUtils.FULL_RANGE, BiomeFilter.biome()));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
