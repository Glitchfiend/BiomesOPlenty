/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.worldgen;

import biomesoplenty.common.worldgen.feature.*;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;

public class BOPFeatureUtils
{
    public static void bootstrap(BootstapContext<ConfiguredFeature<?, ?>> context)
    {
        BOPCaveFeatures.bootstrap(context);
        BOPMiscOverworldFeatures.bootstrap(context);
        BOPNetherFeatures.bootstrap(context);
        BOPTreeFeatures.bootstrap(context);
        BOPVegetationFeatures.bootstrap(context);
    }

    public static ResourceKey<ConfiguredFeature<?, ?>> createKey(String name)
    {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlenty.MOD_ID, name));
    }
}
