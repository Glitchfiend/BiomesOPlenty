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
import net.minecraft.world.gen.area.AreaDimension;

public enum GenLayerRainfallNoise implements IBOPAreaTransformer0
{
    SMALL_ZONES(0.14D),
    MEDIUM_ZONES(0.08D),
    LARGE_ZONES(0.04D);

    private final double scale;

    GenLayerRainfallNoise(double scale)
    {
        this.scale = scale;
    }

    @Override
    public int apply(IBOPContextExtended context, AreaDimension areaDimension, int x, int z)
    {
        double xOffset = (double)(context.getWorldSeed() & 0xFFFFFF) * 0.000003D;
        double zOffset = (double)(context.getWorldSeed() & 0xFFFFFF) * 0.000004D;
        double noiseVal = SimplexNoise.noise((x + xOffset) * this.scale, (z + zOffset) * this.scale);

        // boundaries were determined empirically by analyzing statistically output from the SimplexNoise function, and splitting into 12 equally likely groups
        if (noiseVal < -0.637D) return 0;
        else if (noiseVal < -0.575D) return 1;
        else if (noiseVal < -0.465D) return 2;
        else if (noiseVal < -0.295D) return 3;
        else if (noiseVal < -0.148D) return 4;
        else if (noiseVal < -0.034D) return 5;
        else if (noiseVal < 0.132D) return 6;
        else if (noiseVal < 0.246D) return 7;
        else if (noiseVal < 0.400D) return 8;
        else if (noiseVal < 0.551D) return 9;
        else if (noiseVal < 0.634D) return 10;
        else return 11;
    }
}
