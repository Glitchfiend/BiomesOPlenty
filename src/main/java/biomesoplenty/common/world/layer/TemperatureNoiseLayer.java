/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.SimplexNoise;
import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;

public enum TemperatureNoiseLayer implements IBOPAreaTransformer0
{
    SMALL_ZONES(0.16D),
    MEDIUM_ZONES(0.06D),
    LARGE_ZONES(0.01D);

    private final double scale;

    TemperatureNoiseLayer(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        long seed = context.getWorldSeed();
        double noiseVal = SimplexNoise.noise(seed ^ 0xAB1C154F2C586F42L, x * this.scale + SimplexNoise.TRIANGLE_START_X, z * this.scale + SimplexNoise.TRIANGLE_START_Y);

        // boundaries were determined empirically by analyzing statistically the output from the SimplexNoise function, and splitting into 9 equally likely groups
        if (noiseVal < -0.7290668901192167) return 0;
        else if (noiseVal < -0.5226116882660503) return 1;
        else if (noiseVal < -0.31460282189018446) return 2;
        else if (noiseVal < -0.10524117177898246) return 3;
        else if (noiseVal < 0.10524117177898246) return 4;
        else if (noiseVal < 0.31460282189018446) return 5;
        else if (noiseVal < 0.5226116882660503) return 6;
        else if (noiseVal < 0.7290668901192167) return 7;
        else return 8;
    }
}