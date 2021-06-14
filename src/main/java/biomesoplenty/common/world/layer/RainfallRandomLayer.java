/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.common.world.layer.traits.IBOPAreaTransformer0;
import biomesoplenty.common.world.layer.traits.IBOPContextExtended;

public enum RainfallRandomLayer implements IBOPAreaTransformer0
{
    INSTANCE;

    @Override
    public int applyPixel(IBOPContextExtended context, int x, int z)
    {
        // Choose a random heat value
        return context.nextRandom(12);
    }
}
