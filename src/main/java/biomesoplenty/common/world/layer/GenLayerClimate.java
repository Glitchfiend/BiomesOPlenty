package biomesoplenty.common.world.layer;

import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.core.BiomesOPlenty;
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
            //TODO: Debug code - remove once out of bounds bug is solved.
            if (i >= temperatureValues.length)
            {
                BiomesOPlenty.logger.error(i + " is out of bounds for temperature values");
            }
            
            if (i >= rainfallValues.length)
            {
                BiomesOPlenty.logger.error(i + " is out of bounds for rainfall values");
            }
            
            int index = (temperatureValues[i] * 12) + rainfallValues[i];
            
            if (index >= climateMapping.length)
            {
                BiomesOPlenty.logger.error("Index " + i + " is out of bounds for climate mappings");
                BiomesOPlenty.logger.error("Temperature: " + temperatureValues[i]);
                BiomesOPlenty.logger.error("Rainfall: " + rainfallValues[i]);
            }
            
            // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
            out[i] = this.climateMapping[index];
        }
        return out;
    }

}
