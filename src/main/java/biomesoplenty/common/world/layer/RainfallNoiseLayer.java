/*******************************************************************************
 * Copyright 2014-2021, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import java.util.Random;

import biomesoplenty.common.world.SimplexNoise;
import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;

public enum RainfallNoiseLayer implements IBOPAreaTransformer0
{
    SMALL_ZONES(0.16D),
    MEDIUM_ZONES(0.06D),
    LARGE_ZONES(0.01D);

    private final double scale;

    RainfallNoiseLayer(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        long seed = context.getWorldSeed();
        double noiseVal = SimplexNoise.noise(seed ^ 0xE157A1DC3B2A298CL, x * this.scale + SimplexNoise.TRIANGLE_START_Y, z * this.scale + SimplexNoise.TRIANGLE_START_X);

        // boundaries were determined empirically by analyzing statistically the output from the SimplexNoise function, and splitting into 12 equally likely groups
        if (noiseVal < -0.7804209166984755) return 0;
        else if (noiseVal < -0.6263615214979332) return 1;
        else if (noiseVal < -0.47131115810932966) return 2;
        else if (noiseVal < -0.31471113670415907) return 3;
        else if (noiseVal < -0.15717936237854807) return 4;
        else if (noiseVal < 0.0) return 5;
        else if (noiseVal < 0.15717936237854807) return 6;
        else if (noiseVal < 0.31471113670415907) return 7;
        else if (noiseVal < 0.47131115810932966) return 8;
        else if (noiseVal < 0.6263615214979332) return 9;
        else if (noiseVal < 0.7804209166984755) return 10;
        else return 11;
    }
}