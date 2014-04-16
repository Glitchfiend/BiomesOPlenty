package biomesoplenty.common.world;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.TemperatureType;
import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;

public class BOPBiomeManager 
{
	public static List<BiomeEntry> desertBiomes = new ArrayList<BiomeEntry>();
	public static List<BiomeEntry> warmBiomes = new ArrayList<BiomeEntry>();
	public static List<BiomeEntry> coolBiomes = new ArrayList<BiomeEntry>();
	public static List<BiomeEntry> icyBiomes = new ArrayList<BiomeEntry>();
    
    public static BiomeEntry getWeightedRandomBiome(Collection<BiomeEntry> weightedBiomes, int seedRandom)
    {
        return getWeightedRandomBiome(weightedBiomes, seedRandom, WeightedRandom.getTotalWeight(weightedBiomes));
    }
    
    private static BiomeEntry getWeightedRandomBiome(Collection<BiomeEntry> weightedBiomes, int seedRandom, int totalWeight)
    {
        if (totalWeight <= 0)
        {
            throw new IllegalArgumentException();
        }
        else
        {
            Iterator iterator = weightedBiomes.iterator();
            BiomeEntry biomeEntry;
            
            do
            {
                if (!iterator.hasNext())
                {
                    return null;
                }
                
                biomeEntry = (BiomeEntry)iterator.next();
                seedRandom -= biomeEntry.itemWeight;
            }
            while (seedRandom >= 0);
            
            return biomeEntry;
        }
    }
    
	public static class BiomeEntry extends WeightedRandom.Item
	{
		public BiomeGenBase biome;
		public TemperatureType temperatureType;
		
		public BiomeEntry(BiomeGenBase biome, TemperatureType temperatureType, int weight)
		{
			super(weight);
			this.biome = biome;
			this.temperatureType = temperatureType;
		}
		
		public BiomeEntry(BiomeGenBase biome, int weight)
		{
			this(biome, TemperatureType.WARM, weight);
		}
		
		public void addToCorrespondingTemperatureTypeList()
		{
			BOPBiomeHelper.getCorrespondingTemperatureTypeList(temperatureType).add(this);
		}
	}
}
