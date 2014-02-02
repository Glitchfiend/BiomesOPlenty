package biomesoplenty.common.world.layer;

import static biomesoplenty.common.configuration.BOPConfigurationIDs.promisedLandDimID;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPBiomeHelper;
import net.minecraft.world.WorldType;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerBiomesCustom extends GenLayer 
{
	protected BiomeGenBase[] allowedBiomes;

	public GenLayerBiomesCustom(long seed, GenLayer genlayer) 
	{
		super(seed);
		this.parent = genlayer;
	}

	//called from GenLayerCustom.makeTheWorld
	public GenLayerBiomesCustom(long seed, WorldType worldType) 
	{
		super(seed);

		this.allowedBiomes = new BiomeGenBase[]{BOPBiomeHelper.get(promisedLandDimID, "wonderousWoods"), BOPBiomeHelper.get(promisedLandDimID, "majesticMeadow"), BOPBiomeHelper.get(promisedLandDimID, "blessedBog")};
	}

	@Override
	public int[] getInts(int x, int z, int width, int depth)
	{
		int[] dest = IntCache.getIntCache(width*depth);

		for (int dz=0; dz<depth; dz++)
		{
			for (int dx=0; dx<width; dx++)
			{
				this.initChunkSeed(dx+x, dz+z);
				dest[(dx+dz*width)] = this.allowedBiomes[nextInt(this.allowedBiomes.length)].biomeID;
			}
		}
		return dest;
	}
}