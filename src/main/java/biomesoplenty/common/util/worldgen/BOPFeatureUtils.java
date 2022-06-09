/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.worldgen;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class BOPFeatureUtils
{
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> RegistryObject<ConfiguredFeature<FC, ?>> register(String key, F feature, Supplier<FC> configurationSupplier)
    {
        return BiomesOPlenty.CONFIGURED_FEATURE_REGISTER.register(key, () -> new ConfiguredFeature<>(feature, configurationSupplier.get()));
    }
}
