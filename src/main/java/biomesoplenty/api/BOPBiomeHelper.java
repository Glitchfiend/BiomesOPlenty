package biomesoplenty.api;

import java.util.HashMap;
import java.util.List;

import net.minecraft.util.WeightedRandom;
import net.minecraft.world.biome.BiomeGenBase;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import biomesoplenty.common.world.GenLayerBiomeBOP;

public class BOPBiomeHelper 
{
	public static HashMap<String, BOPBiomeEntry> biomeList = new HashMap();
	
	public static void registerBiome(BOPBiomeEntry biome, String name)
	{
		biomeList.put(name, biome);
	}
	
	public static BiomeGenBase get(String name)
	{
		return biomeList.get(name).biome;
	}
	
	public static BiomeGenBase getBOPBiome(String name)
	{
		return get("biomesoplenty:" + name);
	}
	
	public static String convertBiomeName(String originalName)
	{
		return StringUtils.remove(StringUtils.uncapitalize(WordUtils.capitalize(originalName)), " ");
	}
	
	public static List<BOPBiomeEntry> getCorrespondingTemperatureTypeList(TemperatureType type)
	{
		switch (type)
		{
		case HOT:
			return GenLayerBiomeBOP.desertBiomes;
			
		case WARM:
			return GenLayerBiomeBOP.warmBiomes;
			
		case COOL:
			return GenLayerBiomeBOP.coldBiomes;
			
		case ICY:
			return GenLayerBiomeBOP.icyBiomes;

		default:
			return GenLayerBiomeBOP.warmBiomes;
		}
	}
	
	public enum TemperatureType
	{
		HOT, WARM, COOL, ICY;
	}
	
	public static class BOPBiomeEntry extends WeightedRandom.Item
	{
		public BiomeGenBase biome;
		public TemperatureType temperatureType;
		
		public BOPBiomeEntry(BiomeGenBase biome, TemperatureType temperatureType, int weight)
		{
			super(weight);
			this.biome = biome;
			this.temperatureType = temperatureType;
		}
		
		public void addToCorrespondingTemperatureTypeList()
		{
			BOPBiomeHelper.getCorrespondingTemperatureTypeList(temperatureType).add(this);
		}
	}
}
