/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerHeatRandom extends GenLayer
{
    
    public GenLayerHeatRandom(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;        
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        
        int x0 = areaX - 1;
        int y0 = areaY - 1;
        int x1 = areaWidth + 2;
        int y1 = areaHeight + 2;
        int[] parentVals = this.parent.getInts(x0, y0, x1, y1);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                int parentVal = parentVals[x + 1 + (y + 1) * x1];                
                this.initChunkSeed((long)(x + areaX), (long)(y + areaY));

                if (parentVal == 0)
                {
                    // ocean stays as ocean
                    out[x + y * areaWidth] = 0;
                }
                else
                {
                    // otherwise choose a random heat value
                    out[x + y * areaWidth] = this.nextInt(4) + 1;
                }
            }
        }

        return out;
    }
}