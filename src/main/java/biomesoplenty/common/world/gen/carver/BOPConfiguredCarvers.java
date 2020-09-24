/*******************************************************************************
 * Copyright 2014-2020, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.carver;

import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.gen.carver.ConfiguredCarver;
import net.minecraft.world.gen.carver.ICarverConfig;
import net.minecraft.world.gen.carver.WorldCarver;
import net.minecraft.world.gen.feature.ProbabilityConfig;

public class BOPConfiguredCarvers
{
    public static final ConfiguredCarver<ProbabilityConfig> ORIGIN_CAVE = register("origin_cave", BOPWorldCarvers.ORIGIN_CAVE.configured(new ProbabilityConfig(0.14285715F)));

    private static <WC extends ICarverConfig> ConfiguredCarver<WC> register(String key, ConfiguredCarver<WC> carver)
    {
        return WorldGenRegistries.register(WorldGenRegistries.CONFIGURED_CARVER, new ResourceLocation(BiomesOPlenty.MOD_ID, key), carver);
    }
}
