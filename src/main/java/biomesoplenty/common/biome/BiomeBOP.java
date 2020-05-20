/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.common.util.biome.BiomeUtil;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.ConfiguredPlacement;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BiomeBOP extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
	public boolean canSpawnInBiome;
	public int beachBiomeId = BiomeUtil.GetBiomeIDFromRegistry(Biomes.BEACH.getRegistryName());
	public int riverBiomeId = BiomeUtil.GetBiomeIDFromRegistry(Biomes.RIVER.getRegistryName());

    public BiomeBOP(Builder builder)
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
            this.beachBiomeId = BiomeUtil.GetBiomeIDFromRegistry(biome.get().getRegistryName());
        else
            this.beachBiomeId = -1;
    }

    public void setBeachBiome(Biome biome)
    {
        if (biome != null)
            this.beachBiomeId = BiomeUtil.GetBiomeIDFromRegistry(biome.getRegistryName());
        else
            this.beachBiomeId = -1;
    }

    public void setRiverBiome(Optional<Biome> biome)
    {
        if (biome.isPresent())
            this.riverBiomeId = BiomeUtil.GetBiomeIDFromRegistry(biome.get().getRegistryName());
        else
            this.riverBiomeId = -1;
    }

    public void setRiverBiome(Biome biome)
    {
        if (biome != null)
            this.riverBiomeId = BiomeUtil.GetBiomeIDFromRegistry(biome.getRegistryName());
        else
            this.riverBiomeId = -1;
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
