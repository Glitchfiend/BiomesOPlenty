/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerAllSame extends BOPGenLayer
{
    private final int val;
    
    public GenLayerAllSame(long seed, int val)
    {
        super(seed);
        this.val = val;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
                out[x + y * areaWidth] = this.val;
            }
        }
        return out;
    }
}