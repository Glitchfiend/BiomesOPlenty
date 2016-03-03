package biomesoplenty.common.init;

import java.util.Map;
import java.util.Map.Entry;

import biomesoplenty.common.util.biome.BiomeUtils;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.world.biome.BiomeGenBase;

public class ModChecks 
{
	public static void postInit()
	{
		verifyBiomeIds();
	}
	
	private static void verifyBiomeIds()
	{
		BiomesOPlenty.logger.info("Checking for biome id conflicts...");
		
		Map<String, Integer> biomeIdMap = ModBiomes.biomeIdMap;
		
		for (Entry<String, Integer> entry : biomeIdMap.entrySet())
		{
			String biomeIdentifier = entry.getKey();
			int id = entry.getValue();
			
			//Ensure the id is valid (some biomes may be set to -1 as they are disabled)
			if (id >= 0 && id <= BiomeGenBase.getBiomeGenArray().length)
			{
			    BiomeGenBase biome = BiomeGenBase.getBiome(id);

			    //Check if the biome is unexpectedly null, or if the identifier doesn't match that which is in our map
			    if (biome == null || !BiomeUtils.getBiomeIdentifier(biome).equals(biomeIdentifier))
			    {
			        throw new RuntimeException("Unexpected biome " + biome.biomeName + " for id " + id + ". This is not a bug, please ensure your biome ids are configured to be unique.");
			    }
			}
		}
		
		BiomesOPlenty.logger.info("No conflicts found");
	}
}
