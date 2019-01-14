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
import net.minecraft.world.gen.area.AreaDimension;

public enum GenLayerRainfallRandom implements IBOPAreaTransformer0
{
    INSTANCE;

    @Override
    public int apply(IBOPContextExtended context, AreaDimension areaDimension, int x, int z)
    {
        // Choose a random heat value
        return context.random(12);
    }
}
