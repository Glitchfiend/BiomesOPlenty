/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.util.registry.IRegistry;
import net.minecraft.world.biome.Biome;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BiomeBOP extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
	public boolean canSpawnInBiome;
	public int beachBiomeId = -1;

    public BiomeBOP(BiomeBuilder builder)
    {
        super(builder);
        this.canSpawnInBiome = true;
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    public void setBeachBiome(Optional<Biome> biome)
    {
        if (biome.isPresent())
            this.beachBiomeId = IRegistry.BIOME.getId(biome.get());
        else
            this.beachBiomeId = -1;
    }

    public void setBeachBiome(Biome biome)
    {
        this.beachBiomeId = IRegistry.BIOME.getId(biome);
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }
}
