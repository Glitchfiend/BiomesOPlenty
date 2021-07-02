/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.data.BuiltinRegistries;
import net.minecraft.world.level.levelgen.carver.ConfiguredWorldCarver;
import net.minecraft.world.level.levelgen.carver.CarverConfiguration;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.feature.configurations.ProbabilityFeatureConfiguration;

public class BOPConfiguredCarvers
{
    public static final ConfiguredWorldCarver<ProbabilityFeatureConfiguration> ORIGIN_CAVE = register("origin_cave", BOPWorldCarvers.ORIGIN_CAVE.configured(new ProbabilityFeatureConfiguration(0.14285715F)));

    private static <WC extends CarverConfiguration> ConfiguredWorldCarver<WC> register(String key, ConfiguredWorldCarver<WC> carver)
    {
        return BuiltinRegistries.register(BuiltinRegistries.CONFIGURED_CARVER, new ResourceLocation(BiomesOPlenty.MOD_ID, key), carver);
    }
}
