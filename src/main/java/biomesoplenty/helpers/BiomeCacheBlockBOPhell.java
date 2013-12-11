package biomesoplenty.helpers;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeCacheBlockBOPhell
{
	public float[] temperatureValues;
	public float[] rainfallValues;
	public BiomeGenBase[] biomes;
	public int xPosition;
	public int zPosition;
	public long lastAccessTime;
	final BiomeCacheBOPhell theBiomeCache;

	public BiomeCacheBlockBOPhell(BiomeCacheBOPhell par1BiomeCache, int par2, int par3)
	{
		theBiomeCache = par1BiomeCache;
		temperatureValues = new float[256];
		rainfallValues = new float[256];
		biomes = new BiomeGenBase[256];
		xPosition = par2;
		zPosition = par3;
		BiomeCacheBOPhell.getChunkManager(par1BiomeCache).getTemperatures(temperatureValues, par2 << 4, par3 << 4, 16, 16);
		BiomeCacheBOPhell.getChunkManager(par1BiomeCache).getRainfall(rainfallValues, par2 << 4, par3 << 4, 16, 16);
		BiomeCacheBOPhell.getChunkManager(par1BiomeCache).getBiomeGenAt(biomes, par2 << 4, par3 << 4, 16, 16, false);
	}

	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return biomes[par1 & 15 | (par2 & 15) << 4];
	}
}
