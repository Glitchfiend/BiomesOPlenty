/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.layer.traits;

import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.area.AreaFactory;
import net.minecraft.world.level.newbiome.context.BigContext;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.DimensionTransformer;

public interface IAreaTransformer3 extends DimensionTransformer
{
    default <R extends Area> AreaFactory<R> run(BigContext<R> context, AreaFactory<R> areaFactory1, AreaFactory<R> areaFactory2, AreaFactory<R> areaFactory3)
    {
        return () ->
        {
            R area1 = areaFactory1.make();
            R area2 = areaFactory2.make();
            R area3 = areaFactory3.make();

            return context.createResult
                    ((x, z) -> {
                context.initRandom((long)x, (long)z);
                return this.applyPixel(context, area1, area2, area3, x, z);
            });
        };
    }

    int applyPixel(Context context, Area area1, Area area2, Area area3, int x, int z);
}
