/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;

import java.util.HashMap;
import java.util.Map;

public class NetherBiomeBOP extends BiomeBOP
{
    public NetherBiomeBOP(Builder builder)
    {
        super(builder);
        this.canSpawnInBiome = false;
    }
}
