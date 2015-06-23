package biomesoplenty.common.world;

import biomesoplenty.common.enums.BOPClimates;
import static biomesoplenty.common.enums.BOPClimates.*;

public class BOPBiomeManager {
    
    // map temperature and rainfall to climates
    // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
    // we will contrive to make any combination equally likely, so the overall rarity of each climate is in proportion to the number of times it appears in the array
    private static final BOPClimates[] climateMapping = new BOPClimates[] {
    //  0               1               2               3               4               5               6               7               8               9               10              11
        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        FROZEN_DESERT,  FROZEN_DESERT,  // 0
        TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         TUNDRA,         COLD_DESERT,    COLD_DESERT,    // 1
        BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         BOREAL,         COLD_DESERT,    // 2
        COLD_SWAMP,     WET_TEMPERATE,  WET_TEMPERATE,  COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, DRY_TEMPERATE,  COLD_DESERT,    // 3
        COLD_SWAMP,     WET_TEMPERATE,  WET_TEMPERATE,  COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, COOL_TEMPERATE, DRY_TEMPERATE,  DRY_TEMPERATE,  COLD_DESERT,    // 4
        COLD_SWAMP,     COLD_SWAMP,     WET_TEMPERATE,  WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, DRY_TEMPERATE,  DRY_TEMPERATE,  DRY_TEMPERATE,  // 5
        COLD_SWAMP,     COLD_SWAMP,     WET_TEMPERATE,  WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, WARM_TEMPERATE, DRY_TEMPERATE,  DRY_TEMPERATE,  // 6
        HOT_SWAMP,      HOT_SWAMP,      TROPICAL,       TROPICAL,       TROPICAL,       MEDITERANEAN,   MEDITERANEAN,   MEDITERANEAN,   MEDITERANEAN,   HOT_DESERT,     HOT_DESERT,     HOT_DESERT,     // 7
        HOT_SWAMP,      HOT_SWAMP,      TROPICAL,       TROPICAL,       TROPICAL,       SAVANNA,        SAVANNA,        SAVANNA,        SAVANNA,        HOT_DESERT,     HOT_DESERT,     HOT_DESERT      // 8
    };
    
    public static int[] getClimateMappingInts()
    {
        // 9 temperature values, 12 rainfall values, 12 * 9 = 108
        int[] out = new int[108];
        for (int i = 0; i < 108; i++)
        {
            out[i] = climateMapping[i].ordinal();
        }
        return out;
    }
    
    
    
    public BOPBiomeManager() {
        // TODO Auto-generated constructor stub
    }

}
