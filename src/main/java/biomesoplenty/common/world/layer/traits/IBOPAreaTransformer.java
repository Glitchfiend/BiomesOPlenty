/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.gen.IContextExtended;
import net.minecraft.world.gen.area.AreaDimension;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;

public interface IBOPAreaTransformer
{
    default <R extends IArea> IAreaFactory<R> apply(IContextExtended<R> context)
    {
        if (!(context instanceof IBOPContextExtended))
            throw new IllegalArgumentException("Context must be an IBOPContextExtended");

        IBOPContextExtended<R> bopContext = (IBOPContextExtended<R>)context;

        // Create a new IAreaFactory
        return (areaDimension) ->
            // Return a new IArea, with the below IPixelTransformer
            context.makeArea(areaDimension, (x, z) ->
            {
                context.setPosition((long)(x + areaDimension.getStartX()), (long)(z + areaDimension.getStartZ()));
                return this.apply(bopContext, areaDimension, x, z);
            });
    }

    int apply(IBOPContextExtended context, AreaDimension areaDimension, int x, int z);
}
