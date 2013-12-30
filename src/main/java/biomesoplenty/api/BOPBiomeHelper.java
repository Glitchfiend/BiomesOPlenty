package biomesoplenty.api;

import java.util.ArrayList;
import java.util.HashMap;

import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.GenLayerBiomeBOP;

public class BOPBiomeHelper 
{
	public static HashMap<String, BiomeGenBase> biomeList = new HashMap();
	
	public static void registerBiome(BiomeGenBase biome, String name)
	{
		biomeList.put(name, biome);
	}
	
	public static BiomeGenBase get(String name)
	{
		return biomeList.get(name);
	}
	
	public static ArrayList<BiomeGenBase> getCorrespondingTemperatureTypeList(BOPBiomeTemperatureType type)
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
	
	public enum BOPBiomeTemperatureType
	{
		HOT, WARM, COOL, ICY;
	}
	
	public static class BOPBiomeListEntry
	{
		public BiomeGenBase biome;
		public BOPBiomeTemperatureType temperatureType;
		
		public BOPBiomeListEntry(BiomeGenBase biome, BOPBiomeTemperatureType temperatureType)
		{
			this.biome = biome;
			this.temperatureType = temperatureType;
		}
	}
}
