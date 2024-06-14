/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.util.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import biomesoplenty.worldgen.feature.*;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BOPFeatureUtils
{
    public static void bootstrap(BootstrapContext<ConfiguredFeature<?, ?>> context)
    {
        BOPCaveFeatures.bootstrap(context);
        BOPMiscOverworldFeatures.bootstrap(context);
        BOPNetherFeatures.bootstrap(context);
        BOPEndFeatures.bootstrap(context);
        BOPTreeFeatures.bootstrap(context);
        BOPVegetationFeatures.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(BiomesOPlenty.MOD_ID, name));
    }
}
