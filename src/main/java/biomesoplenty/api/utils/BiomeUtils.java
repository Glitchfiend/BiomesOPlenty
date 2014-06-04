package biomesoplenty.api.utils;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeUtils 
{
	public static boolean areBiomesEqual(BiomeGenBase biome1, BiomeGenBase biome2)
	{
		if (biome1 != null && biome2 != null)
		{
			return biome1.biomeID == biome2.biomeID;
		}
		
		return false;
	}
}
