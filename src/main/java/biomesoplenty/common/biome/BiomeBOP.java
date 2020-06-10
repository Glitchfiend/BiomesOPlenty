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
import net.minecraft.world.biome.Biomes;
import net.minecraftforge.fml.RegistryObject;

import java.util.HashMap;
import java.util.Map;

public class BiomeBOP extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();

    public boolean canSpawnInBiome;
    public int beachBiomeId = BiomeRegistry.getId(Biomes.BEACH);
    public int riverBiomeId = BiomeRegistry.getId(Biomes.RIVER);

    public BiomeBOP(Builder builder)
    {
        super(builder);
        this.canSpawnInBiome = true;
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    public void setBeachBiome(Biome biome) {
        this.beachBiomeId = BiomeRegistry.getId(biome);
    }
    
    public void setRiverBiome(Biome biome) {
        this.riverBiomeId = BiomeRegistry.getId(biome);
    }

    public void setBeachBiome(RegistryObject<Biome> regObj)
    {
        Biome biome = BiomeRegistry.getBiome(regObj);
        this.beachBiomeId = BiomeRegistry.getId(biome);
    }
    
    public void setRiverBiome(RegistryObject<Biome> regObj)
    {
        Biome biome = BiomeRegistry.getBiome(regObj);
        this.riverBiomeId = BiomeRegistry.getId(biome);
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }

    public boolean hasWeights()
    {
        return !this.weightMap.isEmpty() && !this.weightMap.entrySet().stream().allMatch((entry) -> entry.getValue().equals(0));
    }
}
