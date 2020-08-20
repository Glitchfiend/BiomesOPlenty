/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.util.biome;

import net.minecraft.util.RegistryKey;
import net.minecraft.util.registry.WorldGenRegistries;
import net.minecraft.world.biome.Biome;

public class BiomeUtil
{
    public static int getBiomeId(Biome biome)
    {
        return WorldGenRegistries.BIOME.getId(biome);
    }

    public static int getBiomeId(RegistryKey<Biome> key)
    {
        return getBiomeId(WorldGenRegistries.BIOME.get(key));
    }
}
