package biomesoplenty.common.world.layer.hell;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.WorldTypeEvent;

public abstract class BiomeLayerHell extends GenLayer
{
	public static GenLayer[] initializeAllBiomeGenerators(long seed, WorldType worldtype, int dim)
	{
		int biomesize = 3;
		if(dim == 1)
		{
			biomesize = 2;
		}

		//Hell and promised biome gen
		BiomeLayerHell obj = new BiomeLayerHellCreate(1L, false);
		obj = new BiomeLayerHellFuzzyZoom(2000L, (obj));
		for(int i = 1; i < 3; i++) { obj = new BiomeLayerHellZoom(2000L + i, (obj)); }
		obj = BiomeLayerHellZoom.func_75915_a(1000L, ((obj)), 0);
		obj = new BiomeLayerHellBiomes(200L, ((obj)));
		obj = BiomeLayerHellZoom.func_75915_a(1000L, ((obj)), 2);
		for(int j = 0; j < biomesize; j++) { obj = new BiomeLayerHellZoom(1000L + j, (obj)); }
		BiomeLayerHellVoronoiZoom genlayervoronoizoom = new BiomeLayerHellVoronoiZoom(10L, ((obj)));
		(obj).initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return (new GenLayer[] { obj, genlayervoronoizoom });
	}

	public BiomeLayerHell(long seed)
	{
	    super(seed);
	}

	public static byte getModdedBiomeSize(WorldType worldType, byte original)
	{
		WorldTypeEvent.BiomeSize event = new WorldTypeEvent.BiomeSize(worldType, original);
		MinecraftForge.TERRAIN_GEN_BUS.post(event);
		return event.newSize;
	}
}