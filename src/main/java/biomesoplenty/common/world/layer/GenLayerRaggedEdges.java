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

public class GenLayerRaggedEdges extends BOPGenLayer
{

    public GenLayerRaggedEdges(long seed, GenLayer parent)
    {
        super(seed);
        this.parent = parent;
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int outerWidth = areaWidth + 2;
        int outerHeight = areaHeight + 2;
        int[] parentVals = this.parent.getInts(areaX - 1, areaY - 1, outerWidth, outerHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {
                int northWestVal = parentVals[x + 0 + (y + 0) * outerWidth];
                int northEastVal = parentVals[x + 2 + (y + 0) * outerWidth];
                int southWestVal = parentVals[x + 0 + (y + 2) * outerWidth];
                int southEastVal = parentVals[x + 2 + (y + 2) * outerWidth];
                int centerVal = parentVals[x + 1 + (y + 1) * outerWidth];

                this.initChunkSeed((long)(x + areaX), (long)(y + areaY));

                if (centerVal == 0 && (northWestVal != 0 || northEastVal != 0 || southWestVal != 0 || southEastVal != 0))
                {
                    // center is an ocean touching land on at least one corner

                    // with a one in 3 chance, turn the ocean into a random one of the corners
                    if (this.nextInt(3) == 0)
                    {
                        int counter = 1;
                        int replacement = 1;
                        if (northWestVal != 0 && this.nextInt(counter++) == 0) {replacement = northWestVal;}
                        if (northEastVal != 0 && this.nextInt(counter++) == 0) {replacement = northEastVal;}
                        if (southWestVal != 0 && this.nextInt(counter++) == 0) {replacement = southWestVal;}
                        if (southEastVal != 0 && this.nextInt(counter++) == 0) {replacement = southEastVal;}
                        out[x + y * areaWidth] = replacement;
                    }
                    else
                    {
                        out[x + y * areaWidth] = 0;
                    }  
                }
                else if (centerVal > 0 && (northWestVal == 0 || northEastVal == 0 || southWestVal == 0 || southEastVal == 0))
                {
                    // center is a land square, with ocean on at least one corner
                    
                    // with a one in 5 chance, turn the center into ocean
                    out[x + y * areaWidth] = (this.nextInt(5) == 0) ? 0 : centerVal;
                }
                else
                {
                    // otherwise, leave it alone
                    out[x + y * areaWidth] = centerVal;
                }
            }
        }

        return out;
    }
}