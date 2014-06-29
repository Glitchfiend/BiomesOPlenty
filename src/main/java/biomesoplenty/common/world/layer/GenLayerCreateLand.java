package biomesoplenty.common.world.layer;

import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerCreateLand extends GenLayer
{
    public GenLayerCreateLand(long seed)
    {
        super(seed);
    }

    @Override
	public int[] getInts(int x, int z, int length, int width)
    {
        int[] outputBiomeIds = IntCache.getIntCache(length * width);

        for (int xItr = 0; xItr < length; ++xItr)
        {
        	for (int zItr = 0; zItr < width; ++zItr)
        	{
        		this.initChunkSeed((long)(x + xItr), (long)(z + zItr));
        		
        		if (this.nextInt(100) < BOPConfigurationTerrainGen.landmassPercentage)
        		{
        			outputBiomeIds[xItr + zItr * length] = 1;
        		}
        		else
        		{
        			outputBiomeIds[xItr + zItr * length] = 0;
        		}
        	}
        }

        if (x > -length && x <= 0 && z > -width && z <= 0)
        {
            outputBiomeIds[-x + -z * length] = 1;
        }

        return outputBiomeIds;
    }
}