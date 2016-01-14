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

public class GenLayerIslandBOP extends BOPGenLayer
{

    private final int islandChance;

    public GenLayerIslandBOP(long seed, int islandChance)
    {
        this(seed, islandChance, null);
    }
    
    public GenLayerIslandBOP(long seed, int islandChance, GenLayer parent)
    {
        super(seed);
        this.islandChance = islandChance;
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);
        if (this.parent == null)
        {
            // randomly set land areas
            for (int y = 0; y < areaHeight; ++y)
            {
                for (int x = 0; x < areaWidth; ++x)
                {
                    this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
                    out[x + y * areaWidth] = this.nextInt(this.islandChance) == 0 ? 1 : 0;
                }
            }

            // set the origin as land, always
            if (areaX > -areaWidth && areaX <= 0 && areaY > -areaHeight && areaY <= 0)
            {
                out[-areaX + -areaY * areaWidth] = 1;
            }            
            
        }
        else
        {
            int[] parentVals = this.parent.getInts(areaX, areaY, areaWidth, areaHeight);
            
            // randomly set land areas
            for (int y = 0; y < areaHeight; ++y)
            {
                for (int x = 0; x < areaWidth; ++x)
                {
                    this.initChunkSeed((long)(areaX + x), (long)(areaY + y));
                    out[x + y * areaWidth] = ((parentVals[x + y * areaWidth] == 0) ? ((this.nextInt(this.islandChance) == 0) ? 1 : 0) : parentVals[x + y * areaWidth]);
                }
            }
        }

        return out;
    }
}