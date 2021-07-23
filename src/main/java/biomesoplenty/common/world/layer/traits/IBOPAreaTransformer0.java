/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.context.BigContext;

/***
 * An area transformer that takes 0 existing AreaDimensions. Classes implementing
 * this interface are not required to implement any further Transformer interfaces.
 */
public interface IBOPAreaTransformer0
{
    default <R extends Area> AreaFactory<R> run(BigContext<R> context)
    {
        if (!(context instanceof IBOPContextExtended))
            throw new IllegalArgumentException("Context must be an IBOPContextExtended");

        IBOPContextExtended<R> bopContext = (IBOPContextExtended<R>)context;

        // Create a new IAreaFactory
        return () ->
            // Return a new IArea, with the below IPixelTransformer
            context.createResult((x, z) ->
            {
                context.initRandom((long)(x), (long)(z));
                return this.applyPixel(bopContext, x, z);
            });
    }

    int applyPixel(IBOPContextExtended context, int x, int z);
}
