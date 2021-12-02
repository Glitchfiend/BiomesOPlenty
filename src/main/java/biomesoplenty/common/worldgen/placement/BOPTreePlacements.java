/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.placement;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.worldgen.feature.BOPTreeFeatures;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.Registry;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.data.worldgen.features.TreeFeatures;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPTreePlacements
{
    public static final PlacedFeature FIR_CHECKED = PlacementUtils.register("fir", BOPTreeFeatures.FIR.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));
    public static final PlacedFeature FIR_LARGE_CHECKED = PlacementUtils.register("fir_large", BOPTreeFeatures.LARGE_FIR.filteredByBlockSurvival(BOPBlocks.FIR_SAPLING));

    public static PlacedFeature register(String key, PlacedFeature feature)
    {
        return Registry.register(BuiltinRegistries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, key), feature);
    }
}
