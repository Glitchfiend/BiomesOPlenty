package biomesoplenty.common.helpers;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCacheBlockPromised
{
	/** An array of chunk temperatures saved by this cache. */
	public float[] temperatureValues;

	/** An array of chunk rainfall values saved by this cache. */
	public float[] rainfallValues;

	/** The array of biome types stored in this BiomeCacheBlock. */
	public BiomeGenBase[] biomes;

	/** The x coordinate of the BiomeCacheBlock. */
	public int xPosition;

	/** The z coordinate of the BiomeCacheBlock. */
	public int zPosition;

	/** The last time this BiomeCacheBlock was accessed, in milliseconds. */
	public long lastAccessTime;

	/** The BiomeCache object that contains this BiomeCacheBlock */
	final BiomeCachePromised theBiomeCache;

	public BiomeCacheBlockPromised(BiomeCachePromised par1BiomeCache, int par2, int par3)
	{
		theBiomeCache = par1BiomeCache;
		temperatureValues = new float[256];
		rainfallValues = new float[256];
		biomes = new BiomeGenBase[256];
		xPosition = par2;
		zPosition = par3;
		BiomeCachePromised.getChunkManager(par1BiomeCache).getTemperatures(temperatureValues, par2 << 4, par3 << 4, 16, 16);
		BiomeCachePromised.getChunkManager(par1BiomeCache).getRainfall(rainfallValues, par2 << 4, par3 << 4, 16, 16);
		BiomeCachePromised.getChunkManager(par1BiomeCache).getBiomeGenAt(biomes, par2 << 4, par3 << 4, 16, 16, false);
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position from the cache block.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return biomes[par1 & 15 | (par2 & 15) << 4];
	}
}
