/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
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

public enum TemperatureNoiseLayer implements IBOPAreaTransformer0
{
    SMALL_ZONES(0.16D),
    MEDIUM_ZONES(0.06D),
    LARGE_ZONES(0.01D);

    private final double scale;

    private long seed;
    private double xOffset;
    private double zOffset;

    TemperatureNoiseLayer(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        long seed = context.getWorldSeed();

        // If the seed has changed, re-initialize offsets
        if (this.seed != seed) {
            Random random = new Random(seed + 123);
            this.xOffset = random.nextDouble() * 128;
            this.zOffset = random.nextDouble() * 128;
            this.seed = seed;
        }

        double noiseVal = SimplexNoise.noise((x + this.xOffset) * this.scale, (z + this.zOffset) * this.scale);

        // boundaries were determined empirically by analyzing statistically output from the SimplexNoise function, and splitting into 9 equally likely groups
        if (noiseVal < -0.619D) return 0;
        else if (noiseVal < -0.503D) return 1;
        else if (noiseVal < -0.293D) return 2;
        else if (noiseVal < -0.120D) return 3;
        else if (noiseVal < 0.085D) return 4;
        else if (noiseVal < 0.252D) return 5;
        else if (noiseVal < 0.467D) return 6;
        else if (noiseVal < 0.619D) return 7;
        else return 8;
    }
}
