/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.layer;

import biomesoplenty.api.enums.BOPClimates;
import net.minecraft.util.Mth;
import net.minecraft.world.level.newbiome.area.Area;
import net.minecraft.world.level.newbiome.context.Context;
import net.minecraft.world.level.newbiome.layer.traits.AreaTransformer2;
import net.minecraft.world.level.newbiome.layer.traits.DimensionOffset0Transformer;

public enum ClimateLayer implements AreaTransformer2, DimensionOffset0Transformer
{
    INSTANCE;

    private final int[] climateMapping;

    ClimateLayer()
    {
        this.climateMapping = BOPClimates.getClimateMappingInts();
    }

    @Override
    public int applyPixel(Context context, Area area1, Area area2, int x, int z)
    {
        int temperature = area1.get(x, z);
        int rainfall = area2.get(x, z);

        // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
        // clamp as a precaution against potential rounding errors due to use of doubles/floats in noise calculations
        // this guarantees index is between 0 and 108 (= 9 * 12), the range of indexes in BOPClimates.getClimateMappingInts()
        int index = ( Mth.clamp(temperature, 0, 8) * 12 ) + Mth.clamp(rainfall, 0, 11);
        return this.climateMapping[index];
    }
}
