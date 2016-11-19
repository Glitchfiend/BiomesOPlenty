package biomesoplenty.common.world.layer;

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
            int index = (temperatureValues[i] * 12) + rainfallValues[i];

            // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
            // index is clamped to account for potential rounding errors due to use of doubles/floats
            out[i] = this.climateMapping[MathHelper.clamp(index, 0, this.climateMapping.length - 1)];
        }
        return out;
    }

}
