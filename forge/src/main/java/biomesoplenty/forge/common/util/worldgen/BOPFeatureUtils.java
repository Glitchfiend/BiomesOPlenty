/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.forge.common.util.worldgen;

import biomesoplenty.forge.common.worldgen.feature.*;
import biomesoplenty.forge.core.BiomesOPlentyForge;
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
        return ResourceKey.create(Registries.CONFIGURED_FEATURE, new ResourceLocation(BiomesOPlentyForge.MOD_ID, name));
    }
}
