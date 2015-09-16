package forestry.api.core;

import java.util.HashMap;
import java.util.Map;

import net.minecraft.world.biome.BiomeGenBase;

import net.minecraftforge.common.BiomeDictionary;

public class BiomeHelper {

	private static final Map<BiomeGenBase, Boolean> isBiomeHellishCache = new HashMap<BiomeGenBase, Boolean>();

	/**
	 * Determines if it can rain or snow in the given biome.
	 */
	public static boolean canRainOrSnow(BiomeGenBase biomeGenBase) {
		return biomeGenBase.getEnableSnow() || biomeGenBase.canSpawnLightningBolt();
	}

	/**
	 * Determines if a given BiomeGenBase is of HELLISH temperature, since it is treated separately from actual temperature values.
	 * Uses the BiomeDictionary.
	 * @param biomeGen BiomeGenBase of the biome in question
	 * @return true, if the BiomeGenBase is a Nether-type biome; false otherwise.
	 */
	public static boolean isBiomeHellish(BiomeGenBase biomeGen) {
		if (isBiomeHellishCache.containsKey(biomeGen)) {
			return isBiomeHellishCache.get(biomeGen);
		}

		boolean isBiomeHellish = BiomeDictionary.isBiomeOfType(biomeGen, BiomeDictionary.Type.NETHER);
		isBiomeHellishCache.put(biomeGen, isBiomeHellish);
		return isBiomeHellish;
	}
}
