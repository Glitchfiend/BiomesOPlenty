/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;

public enum TemperatureRandomLayer implements IBOPAreaTransformer0
{
    INSTANCE;

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        return context.nextRandom(9);
    }
}
