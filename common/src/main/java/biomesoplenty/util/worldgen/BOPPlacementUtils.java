/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.util.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.placement.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class BOPPlacementUtils
{
    public static void bootstrap(BootstapContext<PlacedFeature> context)
    {
        BOPCavePlacements.bootstrap(context);
        BOPMiscOverworldPlacements.bootstrap(context);
        BOPNetherPlacements.bootstrap(context);
        BOPTreePlacements.bootstrap(context);
        BOPVegetationPlacements.bootstrap(context);
    }

    public static ResourceKey<PlacedFeature> createKey(String name)
    {
        return ResourceKey.create(Registries.PLACED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
