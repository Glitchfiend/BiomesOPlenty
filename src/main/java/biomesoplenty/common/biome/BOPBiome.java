/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;

public class BOPBiome extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();

    public BOPBiome(BiomeBuilder builder)
    {
        super(builder);
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }
}
