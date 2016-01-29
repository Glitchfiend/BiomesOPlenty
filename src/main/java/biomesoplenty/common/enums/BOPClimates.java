package biomesoplenty.common.enums;

import java.util.ArrayList;
import java.util.Iterator;

import biomesoplenty.common.world.layer.BOPGenLayer;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager.BiomeType;

public enum BOPClimates {

    ICE_CAP (BiomeType.ICY),
    FROZEN_DESERT (BiomeType.ICY),
    TUNDRA (BiomeType.ICY),
    COLD_DESERT (BiomeType.COOL),
    BOREAL (BiomeType.ICY),
    COLD_SWAMP (BiomeType.COOL),
    WET_TEMPERATE (BiomeType.COOL),
    DRY_TEMPERATE (BiomeType.WARM),
    COOL_TEMPERATE (BiomeType.COOL),
    WARM_TEMPERATE (BiomeType.WARM),
    HOT_SWAMP (BiomeType.WARM),
    TROPICAL (BiomeType.DESERT),
    MEDITERANEAN (BiomeType.WARM),
    SAVANNA (BiomeType.DESERT),
    HOT_DESERT (BiomeType.DESERT);
    
    public final BiomeType biomeType;
    private int totalLandBiomesWeight;
    private ArrayList<WeightedBiomeEntry> landBiomes = new ArrayList<WeightedBiomeEntry>();
    
    private BOPClimates(BiomeType biomeType)
    {
        this.biomeType = biomeType;
    }
    
    public BOPClimates addLandBiome(int weight, BiomeGenBase biome)
    {
        return this.addLandBiome(new WeightedBiomeEntry(weight, biome));
    }
    
    public BOPClimates addLandBiome(WeightedBiomeEntry biomeEntry)
    {
        this.totalLandBiomesWeight += biomeEntry.weight;
        this.landBiomes.add(biomeEntry);
        return this;
    }
    
    public BiomeGenBase getRandomLandBiome(BOPGenLayer layer)
    {
        int weight = layer.nextInt(this.totalLandBiomesWeight);
        Iterator<WeightedBiomeEntry> iterator = this.landBiomes.iterator();
        WeightedBiomeEntry item;
        do
        {
            item = iterator.next();
            weight -= item.weight;
        }
        while (weight >= 0);
        return item.biome;
    }
    
    public BiomeGenBase getRandomOceanBiome(BOPGenLayer layer, boolean deep)
    {
        switch (this)
        {
            case ICE_CAP:
                return (layer.nextInt(2)==0) ? this.getRandomLandBiome(layer) : BiomeGenBase.frozenOcean;
            case FROZEN_DESERT: case TUNDRA: case COLD_DESERT: case BOREAL:
                return (layer.nextInt(3)!=0) ? (deep ? BiomeGenBase.deepOcean : BiomeGenBase.ocean) : BiomeGenBase.frozenOcean;
            default:
                return (deep ? BiomeGenBase.deepOcean : BiomeGenBase.ocean);
        }
    }
    
    static
    {
        // set up vanilla biomes
        
        BOPClimates.ICE_CAP.addLandBiome(5,BiomeGenBase.icePlains);
        BOPClimates.FROZEN_DESERT.addLandBiome(5, BiomeGenBase.icePlains);
        BOPClimates.TUNDRA.addLandBiome(5, BiomeGenBase.icePlains).addLandBiome(10, BiomeGenBase.coldTaiga);
        BOPClimates.COLD_DESERT.addLandBiome(5, BiomeGenBase.extremeHills);
        BOPClimates.BOREAL.addLandBiome(10, BiomeGenBase.coldTaiga).addLandBiome(10, BiomeGenBase.extremeHills).addLandBiome(10, BiomeGenBase.taiga);
        BOPClimates.COLD_SWAMP.addLandBiome(3, BiomeGenBase.swampland);
        BOPClimates.WET_TEMPERATE.addLandBiome(10, BiomeGenBase.forest).addLandBiome(20, BiomeGenBase.roofedForest);
        BOPClimates.DRY_TEMPERATE.addLandBiome(5, BiomeGenBase.plains);
        BOPClimates.COOL_TEMPERATE.addLandBiome(5, BiomeGenBase.taiga).addLandBiome(10, BiomeGenBase.forest).addLandBiome(20, BiomeGenBase.birchForest);
        BOPClimates.WARM_TEMPERATE.addLandBiome(20, BiomeGenBase.plains).addLandBiome(5, BiomeGenBase.forest).addLandBiome(5, BiomeGenBase.birchForest);
        BOPClimates.HOT_SWAMP.addLandBiome(7, BiomeGenBase.swampland);
        BOPClimates.TROPICAL.addLandBiome(10, BiomeGenBase.jungle);
        BOPClimates.MEDITERANEAN.addLandBiome(5, BiomeGenBase.plains);
        BOPClimates.SAVANNA.addLandBiome(20, BiomeGenBase.savanna).addLandBiome(5, BiomeGenBase.mesaPlateau);
        BOPClimates.HOT_DESERT.addLandBiome(30, BiomeGenBase.desert);
    }
    
    
    
    
    
    private static BOPClimates[] values = BOPClimates.values();
    
    public static BOPClimates lookup(int i) {return values[i];}
    
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
    
    public static class WeightedBiomeEntry
    {
        public final int weight;
        public final BiomeGenBase biome;
        
        public WeightedBiomeEntry(int weight, BiomeGenBase biome)
        {
            this.weight = weight;
            this.biome = biome;
        }
    }
    
    // for debugging purposes
    public static void printWeights()
    {
        for (BOPClimates climate : BOPClimates.values())
        {
            for (WeightedBiomeEntry entry : climate.landBiomes)
            {
                System.out.println(climate.name()+" "+entry.biome.biomeName+" "+entry.weight);
            }
        }
    }

}
