package biomesoplenty.common.world.layer;

import java.util.Arrays;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.BOPGenLayer;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.gen.layer.GenLayer;
import net.minecraft.world.gen.layer.IntCache;

public class GenLayerClimate extends BOPGenLayer {

    private final GenLayer temperature;
    private final GenLayer rainfall;
    private final int[] climateMapping;
    
    public GenLayerClimate(long seed, GenLayer temperature, GenLayer rainfall) {
        super(seed);
        this.temperature = temperature;
        this.rainfall = rainfall;
        this.climateMapping = BOPClimates.getClimateMappingInts();
    }

    @Override
    public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight)
    {
        int[] temperatureValues = this.temperature.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] rainfallValues = this.rainfall.getInts(areaX, areaY, areaWidth, areaHeight);
        int[] out = IntCache.getIntCache(areaWidth * areaHeight);

        for (int i = 0; i < areaWidth * areaHeight; ++i)
        {
            // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
            // clamp as a precaution against potential rounding errors due to use of doubles/floats in noise calculations
            // this guarantees index is between 0 and 108 (= 9 * 12), the range of indexes in BOPClimates.getClimateMappingInts()
            int index = ( MathHelper.clamp(temperatureValues[i], 0, 8) * 12 ) + MathHelper.clamp(rainfallValues[i], 0, 11);
            
            out[i] = this.climateMapping[index];
        }
        return out;
    }
    
    // debug method added to assist in troubleshooting a specific bug (https://github.com/Glitchfiend/BiomesOPlenty/issues/983)
    public String debugClimateMappingInts()
    {
        return Arrays.toString(this.climateMapping);
    }

}
