package biomesoplenty.api;

import java.util.HashMap;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.text.WordUtils;

import biomesoplenty.common.world.BOPBiomeManager;
import biomesoplenty.common.world.BOPBiomeManager.BiomeEntry;

public class BOPBiomeHelper 
{
	public static HashMap<String, BiomeEntry>[] biomeLists = new HashMap[256];
	
	public static void init()
	{
		biomeLists[-1 + 1] = new HashMap();
		biomeLists[0 + 1] = new HashMap();
	}
	
	public static void registerBiome(BiomeEntry biome, int dimID, String name)
	{
		biomeLists[dimID + 1].put(name, biome);
	}
	
	public static void registerBiome(BiomeEntry biome, String name)
	{
		registerBiome(biome, 0, name);
	}
	
	public static BiomeGenBase get(int dimID, String name)
	{
		return biomeLists[dimID + 1].get("biomesoplenty:" + name).biome;
	}
	
	public static BiomeGenBase get(String name)
	{
		return get(0, name);
	}
	
	public static String convertBiomeName(String originalName)
	{
		return StringUtils.remove(StringUtils.uncapitalize(WordUtils.capitalize(originalName)), " ");
	}
	
	public static List<BiomeEntry> getCorrespondingTemperatureTypeList(TemperatureType type)
	{
		switch (type)
		{
		case HOT:
			return BOPBiomeManager.desertBiomes;
			
		case WARM:
			return BOPBiomeManager.warmBiomes;
			
		case COOL:
			return BOPBiomeManager.coolBiomes;
			
		case ICY:
			return BOPBiomeManager.icyBiomes;

		default:
			return BOPBiomeManager.warmBiomes;
		}
	}
	
	public enum TemperatureType
	{
		HOT, WARM, COOL, ICY;
	}
}
