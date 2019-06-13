/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.gen.IExtendedNoiseRandom;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraft.world.gen.area.IArea;
import net.minecraft.world.gen.area.IAreaFactory;
import net.minecraft.world.gen.layer.traits.IDimTransformer;

public interface IAreaTransformer3 extends IDimTransformer
{
    default <R extends IArea> IAreaFactory<R> apply(IExtendedNoiseRandom<R> context, IAreaFactory<R> areaFactory1, IAreaFactory<R> areaFactory2, IAreaFactory<R> areaFactory3)
    {
        return () ->
        {
            R area1 = areaFactory1.make();
            R area2 = areaFactory2.make();
            R area3 = areaFactory3.make();

            return context.func_212861_a_((x, z) -> {
                context.setPosition((long)x, (long)z);
                return this.apply(context, area1, area2, area3, x, z);
            });
        };
    }

    int apply(INoiseRandom context, IArea area1, IArea area2, IArea area3, int x, int z);
}
