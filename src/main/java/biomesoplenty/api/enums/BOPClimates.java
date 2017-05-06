package biomesoplenty.api.enums;

import java.util.ArrayList;
import java.util.Iterator;

import biomesoplenty.api.generation.BOPGenLayer;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.common.BiomeManager.BiomeType;

public enum BOPClimates {

    ICE_CAP (BiomeType.ICY),
    TUNDRA (BiomeType.ICY),
    BOREAL (BiomeType.COOL),
    COLD_SWAMP (BiomeType.COOL),
    WET_TEMPERATE (BiomeType.WARM),
    DRY_TEMPERATE (BiomeType.WARM),
    COOL_TEMPERATE (BiomeType.COOL),
    WARM_TEMPERATE (BiomeType.WARM),
    HOT_SWAMP (BiomeType.WARM),
    TROPICAL (BiomeType.DESERT),
    MEDITERANEAN (BiomeType.WARM),
    SAVANNA (BiomeType.DESERT),
    HOT_DESERT (BiomeType.DESERT),
    WASTELAND (BiomeType.DESERT),
    HELL (null);
    
    public final BiomeType biomeType;
    private int totalBiomesWeight;

    private ArrayList<WeightedBiomeEntry> landBiomes = new ArrayList<WeightedBiomeEntry>();
    
    private BOPClimates(BiomeType biomeType)
    {
        this.biomeType = biomeType;
    }
    
    public BOPClimates addBiome(int weight, Biome biome)
    {
        return this.addBiome(new WeightedBiomeEntry(weight, biome));
    }
    
    public BOPClimates addBiome(WeightedBiomeEntry biomeEntry)
    {
        this.totalBiomesWeight += biomeEntry.weight;
        this.landBiomes.add(biomeEntry);
        return this;
    }
    
    public Biome getRandomBiome(BOPGenLayer layer)
    {
        int weight = layer.nextInt(this.totalBiomesWeight);
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
    
    public Biome getRandomOceanBiome(BOPGenLayer layer, boolean deep)
    {
        return (deep ? Biomes.DEEP_OCEAN : Biomes.OCEAN);
    }

    static
    {
        // set up vanilla biomes
        
        BOPClimates.ICE_CAP.addBiome(10,Biomes.ICE_PLAINS);
        BOPClimates.TUNDRA.addBiome(10, Biomes.COLD_TAIGA).addBiome(10, Biomes.EXTREME_HILLS);
        BOPClimates.BOREAL.addBiome(5, Biomes.REDWOOD_TAIGA).addBiome(5, Biomes.EXTREME_HILLS).addBiome(20, Biomes.TAIGA);
        BOPClimates.COLD_SWAMP.addBiome(5, Biomes.SWAMPLAND);
        BOPClimates.WET_TEMPERATE.addBiome(20, Biomes.ROOFED_FOREST).addBiome(5, Biomes.FOREST);
        BOPClimates.DRY_TEMPERATE.addBiome(5, Biomes.PLAINS);
        BOPClimates.COOL_TEMPERATE.addBiome(15, Biomes.FOREST).addBiome(10, Biomes.BIRCH_FOREST);
        BOPClimates.WARM_TEMPERATE.addBiome(20, Biomes.PLAINS).addBiome(5, Biomes.BIRCH_FOREST);
        BOPClimates.HOT_SWAMP.addBiome(5, Biomes.SWAMPLAND);
        BOPClimates.TROPICAL.addBiome(15, Biomes.JUNGLE);
        BOPClimates.MEDITERANEAN.addBiome(5, Biomes.PLAINS);
        BOPClimates.SAVANNA.addBiome(20, Biomes.SAVANNA);
        BOPClimates.HOT_DESERT.addBiome(30, Biomes.DESERT).addBiome(10, Biomes.MESA_CLEAR_ROCK);
        BOPClimates.WASTELAND.addBiome(1, Biomes.DESERT);
        BOPClimates.HELL.addBiome(40, Biomes.HELL);
    }
    
    private static BOPClimates[] values = BOPClimates.values();
    
    public static BOPClimates lookup(int i) {return values[i];}
    
    // map temperature and rainfall to climates
    // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
    // we will contrive to make any combination equally likely, so the overall rarity of each climate is in proportion to the number of times it appears in the array
    private static final BOPClimates[] climateMapping = new BOPClimates[] {
    //  0               1               2               3               4               5               6               7               8               9               10              11
        TUNDRA,        	TUNDRA,        	TUNDRA,        	ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,        ICE_CAP,  		ICE_CAP,  		// 0
        BOREAL,			BOREAL,			BOREAL,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			// 1
        COLD_SWAMP,		COLD_SWAMP,		COLD_SWAMP,		BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			// 2
        COLD_SWAMP,		COLD_SWAMP,		COLD_SWAMP,		WET_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	// 3
        COLD_SWAMP,		WET_TEMPERATE,	WET_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	// 4
        WET_TEMPERATE,	WET_TEMPERATE,	WET_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	DRY_TEMPERATE,	SAVANNA,		SAVANNA,		// 5
        HOT_SWAMP,		HOT_SWAMP,		HOT_SWAMP,		WET_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	MEDITERANEAN,	MEDITERANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		// 6
        TROPICAL,		TROPICAL,		HOT_SWAMP,		HOT_SWAMP,		WET_TEMPERATE,	WARM_TEMPERATE,	MEDITERANEAN,	MEDITERANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		WASTELAND,		// 7
        TROPICAL,		TROPICAL,		TROPICAL,		HOT_SWAMP,		HOT_SWAMP,		MEDITERANEAN,	MEDITERANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		WASTELAND,		WASTELAND		// 8
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
        public final Biome biome;
        
        public WeightedBiomeEntry(int weight, Biome biome)
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
                System.out.println(climate.name()+" "+entry.biome.getBiomeName()+" "+entry.weight);
            }
        }
    }

}
