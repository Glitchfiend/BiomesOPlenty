package biomesoplenty.world.layer;

import net.minecraft.world.WorldType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class BiomeLayer
{
	private long worldGenSeed;
	protected BiomeLayer parent;
	private long chunkSeed;
	private long baseSeed;

	//dim: 0 = surface, 1 = hell, 2 = promised
	public static BiomeLayer[] initializeAllBiomeGenerators(long seed, WorldType worldtype, int dim)
	{
		if(dim == 0)
		{
			BiomeLayer obj = new BiomeLayerCreate(1L, true);
			obj = new BiomeLayerFuzzyZoom(2000L, (obj));
			obj = new BiomeLayerIsland(1L, (obj));
			obj = new BiomeLayerZoom(2001L, (obj));
			obj = new BiomeLayerIsland(2L, (obj));
			obj = new BiomeLayerZoom(2002L, (obj));
			obj = new BiomeLayerIsland(3L, (obj));
			obj = new BiomeLayerZoom(2003L, (obj));
			obj = new BiomeLayerIsland(4L, (obj));

			byte size = 4;
			size = getModdedBiomeSize(worldtype, size);

			BiomeLayer obj1 = obj;
			obj1 = BiomeLayerZoom.func_75915_a(1000L, ((obj1)), 0);
			obj1 = new BiomeLayerRiverInit(100L, ((obj1)));
			obj1 = BiomeLayerZoom.func_75915_a(1000L, ((obj1)), size + 2);
			obj1 = new BiomeLayerRiver(1L, ((obj1)));
			obj1 = new BiomeLayerSmooth(1000L, ((obj1)));
			BiomeLayer obj2 = obj;
			obj2 = BiomeLayerZoom.func_75915_a(1000L, ((obj2)), 0);
			obj2 = new BiomeLayerBiomes(200L, ((obj2)), worldtype, 0);
			obj2 = BiomeLayerZoom.func_75915_a(1000L, ((obj2)), 2);
			//obj2 = new BWG4LayerHills(1000L, ((BiomeLayer)(obj2)), generatorSettings);
			obj2 = new BiomeLayerZoom(1000, ((obj2)));
			obj2 = new BiomeLayerShore(1000L, ((BiomeLayer)(obj2)));

			for (int i = 0 + 1; i < size; i++)
			{
				obj2 = new BiomeLayerZoom(1000 + i, ((obj2)));
			}

			obj2 = new BiomeLayerSmooth(1000L, ((obj2)));
			obj2 = new BiomeLayerRiverMix(100L, ((obj2)), ((obj1)));
			BiomeLayerRiverMix bwg4layerrivermix = ((BiomeLayerRiverMix)(obj2));
			BiomeLayerVoronoiZoom genlayervoronoizoom = new BiomeLayerVoronoiZoom(10L, ((obj2)));
			(obj2).initWorldGenSeed(seed);
			genlayervoronoizoom.initWorldGenSeed(seed);
			return (new BiomeLayer[]
					{
					obj2, genlayervoronoizoom, bwg4layerrivermix
					});
		}
		else
		{
			int biomesize = 3;
			if(dim == 1)
			{
				biomesize = 2;
			}

			//Hell and promised biome gen
			BiomeLayer obj = new BiomeLayerCreate(1L, false);
			obj = new BiomeLayerFuzzyZoom(2000L, (obj));
			for(int i = 1; i < 3; i++) { obj = new BiomeLayerZoom(2000L + i, (obj)); }
			obj = BiomeLayerZoom.func_75915_a(1000L, ((obj)), 0);
			obj = new BiomeLayerBiomes(200L, ((obj)), worldtype, dim);
			obj = BiomeLayerZoom.func_75915_a(1000L, ((obj)), 2);
			for(int j = 0; j < biomesize; j++) { obj = new BiomeLayerZoom(1000L + j, (obj)); }
			BiomeLayerVoronoiZoom genlayervoronoizoom = new BiomeLayerVoronoiZoom(10L, ((obj)));
			(obj).initWorldGenSeed(seed);
			genlayervoronoizoom.initWorldGenSeed(seed);
			return (new BiomeLayer[] { obj, genlayervoronoizoom });
		}
	}

	public BiomeLayer(long seed)
	{
		baseSeed = seed;
		baseSeed *= baseSeed * 6364136223846793005L + 1442695040888963407L;
		baseSeed += seed;
		baseSeed *= baseSeed * 6364136223846793005L + 1442695040888963407L;
		baseSeed += seed;
		baseSeed *= baseSeed * 6364136223846793005L + 1442695040888963407L;
		baseSeed += seed;
	}

	public void initWorldGenSeed(long seed)
	{
		worldGenSeed = seed;

		if (parent != null)
		{
			parent.initWorldGenSeed(seed);
		}

		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
		worldGenSeed *= worldGenSeed * 6364136223846793005L + 1442695040888963407L;
		worldGenSeed += baseSeed;
	}

	public void initChunkSeed(long par1, long par3)
	{
		chunkSeed = worldGenSeed;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par3;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par1;
		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += par3;
	}

	protected int nextInt(int par1)
	{
		int j = (int)((chunkSeed >> 24) % par1);

		if (j < 0)
		{
			j += par1;
		}

		chunkSeed *= chunkSeed * 6364136223846793005L + 1442695040888963407L;
		chunkSeed += worldGenSeed;
		return j;
	}

	public abstract int[] getInts(int i, int j, int k, int l);

	public static byte getModdedBiomeSize(WorldType worldType, byte original)
	{
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}
