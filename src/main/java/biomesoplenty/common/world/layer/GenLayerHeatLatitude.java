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

public class GenLayerHeatLatitude extends GenLayer
{
    
    private double period;
    private double halfPeriod;
    private int offset;
    private double offsetVariation;
    private double amplitude;
    
    public GenLayerHeatLatitude(long seed, GenLayer parent, double halfPeriod, long worldSeed)
    {
        super(seed);
        this.parent = parent;
        
        this.period = halfPeriod * 2.0D;
        this.halfPeriod = halfPeriod;
        this.offset = (int)(worldSeed % ((int)(this.period * 2)));
        this.offsetVariation = halfPeriod / 7.0F;
        this.amplitude = 5.999D / halfPeriod;
        
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
                
                // x and y are local coordinates, but we need global coordinates, which we'll call X and Y
                int X = x + areaX;
                int Y = y + areaY;
                
                this.initChunkSeed((long)X, (long)Y);

                if (parentVal == 0)
                {
                    // ocean stays as ocean
                    out[x + y * areaWidth] = 0;
                }
                else
                {
                    // set value between 1 and 4 which is periodic in Y (with some random variation)
                    double Yoffset = Y + this.offset + ((this.nextInt(1001) - 500) * this.offsetVariation / 500.0D);
                    int scaledVal = (int)Math.floor(this.amplitude * Math.abs((Math.abs(Yoffset % period) - halfPeriod)));
                    switch (scaledVal)
                    {
                        case 0:
                            out[x + y * areaWidth] = 1;
                            break;
                        case 1: case 2:
                            out[x + y * areaWidth] = 2;
                            break;
                        case 3: case 4:
                            out[x + y * areaWidth] = 3;
                            break;
                        default:
                            out[x + y * areaWidth] = 4;
                            break;
                    }
                }
            }
        }

        return out;
    }
}