/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.util.worldgen;

import biomesoplenty.common.util.BOPRegistryUtils;
import net.minecraft.core.Holder;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.FeatureConfiguration;

public class BOPFeatureUtils
{
    public static <FC extends FeatureConfiguration, F extends Feature<FC>> Holder<ConfiguredFeature<FC, ?>> register(String key, F feature, FC configuration)
    {
        return BOPRegistryUtils.registerExact(BuiltinRegistries.CONFIGURED_FEATURE, key, new ConfiguredFeature<>(feature, configuration));
    }
}
