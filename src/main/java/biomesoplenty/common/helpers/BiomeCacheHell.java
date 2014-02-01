package biomesoplenty.common.helpers;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.util.LongHashMap;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.WorldChunkManagerBOPHell;
import biomesoplenty.common.world.WorldChunkManagerPromised;

public class BiomeCacheHell
{
	/** Reference to the WorldChunkManager */
	private final WorldChunkManagerBOPHell chunkManager;

	/** The last time this BiomeCache was cleaned, in milliseconds. */
	private long lastCleanupTime = 0L;

	/**
	 * The map of keys to BiomeCacheBlocks. Keys are based on the chunk x, z coordinates as (x | z << 32).
	 */
	private LongHashMap cacheMap = new LongHashMap();

	/** The list of cached BiomeCacheBlocks */
	@SuppressWarnings("rawtypes")
	private List cache = new ArrayList();

	public BiomeCacheHell(WorldChunkManagerBOPHell par1WorldChunkManager)
	{
		chunkManager = par1WorldChunkManager;
	}

	/**
	 * Returns a biome cache block at location specified.
	 */
	@SuppressWarnings("unchecked")
	public BiomeCacheBlockHell getBiomeCacheBlock(int par1, int par2)
	{
		par1 >>= 4;
		par2 >>= 4;
		long var3 = par1 & 4294967295L | (par2 & 4294967295L) << 32;
		BiomeCacheBlockHell var5 = (BiomeCacheBlockHell)cacheMap.getValueByKey(var3);

		if (var5 == null)
		{
			var5 = new BiomeCacheBlockHell(this, par1, par2);
			cacheMap.add(var3, var5);
			cache.add(var5);
		}

		var5.lastAccessTime = System.currentTimeMillis();
		return var5;
	}

	/**
	 * Returns the BiomeGenBase related to the x, z position from the cache.
	 */
	public BiomeGenBase getBiomeGenAt(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).getBiomeGenAt(par1, par2);
	}

	/**
	 * Removes BiomeCacheBlocks from this cache that haven't been accessed in at least 30 seconds.
	 */
	public void cleanupCache()
	{
		long var1 = System.currentTimeMillis();
		long var3 = var1 - lastCleanupTime;

		if (var3 > 7500L || var3 < 0L)
		{
			lastCleanupTime = var1;

			for (int var5 = 0; var5 < cache.size(); ++var5)
			{
				BiomeCacheBlockHell var6 = (BiomeCacheBlockHell)cache.get(var5);
				long var7 = var1 - var6.lastAccessTime;

				if (var7 > 30000L || var7 < 0L)
				{
					cache.remove(var5--);
					long var9 = var6.xPosition & 4294967295L | (var6.zPosition & 4294967295L) << 32;
					cacheMap.remove(var9);
				}
			}
		}
	}

	/**
	 * Returns the array of cached biome types in the BiomeCacheBlock at the given location.
	 */
	public BiomeGenBase[] getCachedBiomes(int par1, int par2)
	{
		return this.getBiomeCacheBlock(par1, par2).biomes;
	}

	/**
	 * Get the world chunk manager object for a biome list.
	 */
	static WorldChunkManagerBOPHell getChunkManager(BiomeCacheHell par0BiomeCache)
	{
		return par0BiomeCache.chunkManager;
	}
}
