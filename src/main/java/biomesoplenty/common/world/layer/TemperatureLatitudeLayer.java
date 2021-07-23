/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;
import net.minecraft.util.Mth;

public enum TemperatureLatitudeLayer implements IBOPAreaTransformer0
{
    INSTANCE;

    private static final double HALF_PERIOD = 16.0D;
    private static final double PERIOD = HALF_PERIOD * 2.0D;
    private static final double OFFSET_VARIATION = HALF_PERIOD / 10.0D;
    private static final double AMPLITUDE = 8.9999D / HALF_PERIOD;

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        int offset = (int) (context.getWorldSeed() % ((int) (PERIOD * 2)));

        double yOffset = z + offset + ((context.nextRandom(1001) - 500) * OFFSET_VARIATION / 500.0D);
        return Mth.floor(AMPLITUDE * Math.abs((Math.abs(yOffset % PERIOD) - HALF_PERIOD)));
    }
}
