/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.enums;

import biomesoplenty.common.world.layer.traits.IBOPContextExtended;
import net.minecraft.init.Biomes;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.IContext;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.ArrayList;
import java.util.Iterator;

public enum BOPClimates
{
    COLD_DESERT (BiomeType.ICY),
    TUNDRA (BiomeType.ICY),
    BOREAL (BiomeType.COOL),
    COLD_SWAMP (BiomeType.COOL),
    WET_TEMPERATE (BiomeType.WARM),
    DRY_TEMPERATE (BiomeType.WARM),
    COOL_TEMPERATE (BiomeType.COOL),
    WARM_TEMPERATE (BiomeType.WARM),
    SUBTROPICAL (BiomeType.WARM),
    TROPICAL (BiomeType.DESERT),
    MEDITERRANEAN (BiomeType.WARM),
    SAVANNA (BiomeType.DESERT),
    HOT_DESERT (BiomeType.DESERT),
    WASTELAND (BiomeType.DESERT),
    HELL (null);

    public final BiomeType biomeType;
    private int totalBiomesWeight;

    private ArrayList<WeightedBiomeEntry> landBiomes = new ArrayList<WeightedBiomeEntry>();

    BOPClimates(BiomeType biomeType)
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

    public Biome getRandomBiome(IContext context)
    {
        int weight = context.random(this.totalBiomesWeight);
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

    public Biome getRandomOceanBiome(IContext context, boolean deep)
    {
        return (deep ? Biomes.DEEP_OCEAN : Biomes.OCEAN);
    }

    static
    {
        // set up vanilla biomes

        BOPClimates.COLD_DESERT.addBiome(10, Biomes.SNOWY_TUNDRA);
        BOPClimates.TUNDRA.addBiome(10, Biomes.SNOWY_TAIGA).addBiome(5, Biomes.MOUNTAINS);
        BOPClimates.BOREAL.addBiome(7, Biomes.GIANT_TREE_TAIGA).addBiome(5, Biomes.MOUNTAINS).addBiome(15, Biomes.TAIGA);
        BOPClimates.COLD_SWAMP.addBiome(10, Biomes.SWAMP);
        BOPClimates.WET_TEMPERATE.addBiome(3, Biomes.DARK_FOREST).addBiome(7, Biomes.FOREST);
        BOPClimates.DRY_TEMPERATE.addBiome(5, Biomes.PLAINS);
        BOPClimates.COOL_TEMPERATE.addBiome(7, Biomes.DARK_FOREST).addBiome(7, Biomes.FOREST).addBiome(10, Biomes.BIRCH_FOREST);
        BOPClimates.WARM_TEMPERATE.addBiome(7, Biomes.PLAINS);
        BOPClimates.SUBTROPICAL.addBiome(5, Biomes.LUKEWARM_OCEAN);
        BOPClimates.TROPICAL.addBiome(15, Biomes.JUNGLE);
        BOPClimates.MEDITERRANEAN.addBiome(5, Biomes.PLAINS);
        BOPClimates.SAVANNA.addBiome(20, Biomes.SAVANNA);
        BOPClimates.HOT_DESERT.addBiome(30, Biomes.DESERT).addBiome(15, Biomes.BADLANDS_PLATEAU);
        BOPClimates.WASTELAND.addBiome(1, Biomes.DESERT);
        BOPClimates.HELL.addBiome(30, Biomes.NETHER);
    }

    private static BOPClimates[] values = BOPClimates.values();

    public static BOPClimates lookup(int i) {return values[i];}

    // map temperature and rainfall to climates
    // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
    // we will contrive to make any combination equally likely, so the overall rarity of each climate is in proportion to the number of times it appears in the array
    private static final BOPClimates[] climateMapping = new BOPClimates[] {
            //  0               1               2               3               4               5               6               7               8               9               10              11
            TUNDRA,        	TUNDRA,        	TUNDRA,        	COLD_DESERT,    COLD_DESERT,    COLD_DESERT,    COLD_DESERT,    COLD_DESERT,    COLD_DESERT,    COLD_DESERT,    COLD_DESERT,  	COLD_DESERT,    // 0
            BOREAL,			BOREAL,			BOREAL,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			// 1
            COLD_SWAMP,		COLD_SWAMP,		COLD_SWAMP,		BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			BOREAL,			// 2
            COLD_SWAMP,		COLD_SWAMP,		COLD_SWAMP,		COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	// 3
            WET_TEMPERATE,	WET_TEMPERATE,	WET_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	// 4
            SUBTROPICAL,	SUBTROPICAL,	WET_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	SAVANNA,		SAVANNA,		// 5
            TROPICAL,		SUBTROPICAL,	SUBTROPICAL,	WET_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	MEDITERRANEAN,	SAVANNA,		SAVANNA,		HOT_DESERT,		// 6
            TROPICAL,		TROPICAL,		SUBTROPICAL,	SUBTROPICAL,	WET_TEMPERATE,	MEDITERRANEAN,	MEDITERRANEAN,	MEDITERRANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		HOT_DESERT,		// 7
            TROPICAL,		TROPICAL,		TROPICAL,		SUBTROPICAL,	WET_TEMPERATE,	MEDITERRANEAN,	MEDITERRANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		WASTELAND,		WASTELAND		// 8
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
                System.out.println(climate.name()+" "+entry.biome.getDisplayName()+" "+entry.weight);
            }
        }
    }

}
