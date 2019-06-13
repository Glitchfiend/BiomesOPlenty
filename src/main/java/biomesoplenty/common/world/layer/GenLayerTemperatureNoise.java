/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.SimplexNoise;
import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;

public enum GenLayerTemperatureNoise implements IBOPAreaTransformer0
{
    SMALL_ZONES(0.20D),
    MEDIUM_ZONES(0.10D),
    LARGE_ZONES(0.05D);

    private final double scale;

    GenLayerTemperatureNoise(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int apply(IBOPContextExtended context, int x, int z)
    {
        double xOffset = (double)(context.getWorldSeed() & 0xFFFFFF) * 0.000001D;
        double zOffset = (double)(context.getWorldSeed() & 0xFFFFFF) * 0.000002D;
        double noiseVal = SimplexNoise.noise((x + xOffset) * this.scale, (z + zOffset) * this.scale);

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
