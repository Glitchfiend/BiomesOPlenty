/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerArchipelago extends BOPGenLayer
{

    private final int seaChance;
    
    public GenLayerArchipelago(long seed, int islandChance, GenLayer parent)
    {
        super(seed);
        this.seaChance = islandChance;
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        int[] parentVals = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
        
        // randomly set land areas
        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
                out[x + y * areaWidth] = this.nextInt(this.seaChance) == 0 ? 0 : parentVals[x + y * areaWidth];
            }
        }

        return out;
    }
}