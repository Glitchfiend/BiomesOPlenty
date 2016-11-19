/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.layer;

import biomesoplenty.api.generation.BOPGenLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerTemperatureLatitude extends BOPGenLayer
{
    
    private double period;
    private double halfPeriod;
    private int offset;
    private double offsetVariation;
    private double amplitude;
    
    public GenLayerTemperatureLatitude(long seed, double halfPeriod, long worldSeed)
    {
        super(seed);
        
        this.period = halfPeriod * 2.0D;
        this.halfPeriod = halfPeriod;
        this.offset = (int)(worldSeed % ((int)(this.period * 2)));
        this.offsetVariation = halfPeriod / 10.0F;
        this.amplitude = 8.9999D / halfPeriod;
        
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int y = 0; y < areaHeight; ++y)
        {
            for (int x = 0; x < areaWidth; ++x)
            {                
                // x and y are local coordinates, but we need global coordinates, which we'll call X and Y
                int X = x + areaX;
                int Y = y + areaY;
                
                this.initChunkSeed((long)X, (long)Y);

                // set value between 1 and 4 which is periodic in Y (with some random variation)
                // ocean generally stays as ocean, unless it's ICY when it becomes frozen ocean
                double Yoffset = Y + this.offset + ((this.nextInt(1001) - 500) * this.offsetVariation / 500.0D);
                out[x + y * areaWidth] = MathHelper.floor(this.amplitude * Math.abs((Math.abs(Yoffset % period) - halfPeriod)));
            }
        }

        return out;
    }
}