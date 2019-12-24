/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.IFeatureConfig;
import net.minecraft.world.gen.placement.IPlacementConfig;
import net.minecraft.world.gen.placement.Placement;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class BiomeBOP extends Biome
{
    protected Map<BOPClimates, Integer> weightMap = new HashMap<BOPClimates, Integer>();
	public boolean canSpawnInBiome;
	public int beachBiomeId = Registry.BIOME.getId(Biomes.BEACH);
	public int riverBiomeId = Registry.BIOME.getId(Biomes.RIVER);

    public BiomeBOP(Builder builder)
    {
        super(builder);
        this.canSpawnInBiome = true;
    }

    public static <FC extends IFeatureConfig, F extends Feature<FC>, DC extends IPlacementConfig> ConfiguredFeature<?, ?> createDecoratedFeature(Feature<FC> featureIn, FC config, Placement<DC> placementIn, DC placementConfig)
    {
        return featureIn.configured(config).decorated(placementIn.func_227446_a_(placementConfig));
    }

    public void addWeight(BOPClimates climate, int weight)
    {
        this.weightMap.put(climate, weight);
    }

    public void setBeachBiome(Optional<Biome> biome)
    {
        if (biome.isPresent())
            this.beachBiomeId = Registry.BIOME.getId(biome.get());
        else
            this.beachBiomeId = -1;
    }

    public void setBeachBiome(Biome biome)
    {
        if (biome != null)
            this.beachBiomeId = Registry.BIOME.getId(biome);
        else
            this.beachBiomeId = -1;
    }

    public void setRiverBiome(Optional<Biome> biome)
    {
        if (biome.isPresent())
            this.riverBiomeId = Registry.BIOME.getId(biome.get());
        else
            this.riverBiomeId = -1;
    }

    public void setRiverBiome(Biome biome)
    {
        if (biome != null)
            this.riverBiomeId = Registry.BIOME.getId(biome);
        else
            this.riverBiomeId = -1;
    }

    public Map<BOPClimates, Integer> getWeightMap()
    {
        return this.weightMap;
    }
}
