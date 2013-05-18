package biomesoplenty.world.layer;

import net.minecraft.world.WorldType;
import net.minecraft.world.gen.layer.GenLayer;
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
    	//Hell and promised biome gen
    	BiomeLayer obj = new BiomeLayerCreate(1L);
    	obj = new BiomeLayerFuzzyZoom(2000L, (BiomeLayer)(obj));
    	for(int i = 1; i < 3; i++) { obj = new BiomeLayerZoom(2000L + i, (BiomeLayer)(obj)); }
    	obj = BiomeLayerZoom.func_75915_a(1000L, ((BiomeLayer)(obj)), 0);
    	obj = new BiomeLayerBiomes(200L, ((BiomeLayer)(obj)), worldtype, dim);
    	obj = BiomeLayerZoom.func_75915_a(1000L, ((BiomeLayer)(obj)), 2);
    	for(int j = 0; j < 3; j++) { obj = new BiomeLayerZoom(1000L + j, (BiomeLayer)(obj)); }	
    	BiomeLayerVoronoiZoom genlayervoronoizoom = new BiomeLayerVoronoiZoom(10L, ((BiomeLayer)(obj)));
		((BiomeLayer)(obj)).initWorldGenSeed(seed);
		genlayervoronoizoom.initWorldGenSeed(seed);
		return (new BiomeLayer[] { obj, genlayervoronoizoom }); 	
    }
    
    public BiomeLayer(long seed)
    {
        this.baseSeed = seed;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += seed;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += seed;
        this.baseSeed *= this.baseSeed * 6364136223846793005L + 1442695040888963407L;
        this.baseSeed += seed;
    }
    
    public void initWorldGenSeed(long seed)
    {
        this.worldGenSeed = seed;

        if (this.parent != null)
        {
            this.parent.initWorldGenSeed(seed);
        }

        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
        this.worldGenSeed *= this.worldGenSeed * 6364136223846793005L + 1442695040888963407L;
        this.worldGenSeed += this.baseSeed;
    }
    
    public void initChunkSeed(long par1, long par3)
    {
        this.chunkSeed = this.worldGenSeed;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par1;
        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += par3;
    }
    
    protected int nextInt(int par1)
    {
        int j = (int)((this.chunkSeed >> 24) % (long)par1);

        if (j < 0)
        {
            j += par1;
        }

        this.chunkSeed *= this.chunkSeed * 6364136223846793005L + 1442695040888963407L;
        this.chunkSeed += this.worldGenSeed;
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
