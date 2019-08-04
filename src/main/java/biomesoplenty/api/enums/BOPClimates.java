/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.enums;

import com.google.common.collect.Lists;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biomes;
import net.minecraft.world.gen.INoiseRandom;
import net.minecraftforge.common.BiomeManager.BiomeType;

import java.util.ArrayList;
import java.util.Iterator;

public enum BOPClimates
{
    ICE_CAP (BiomeType.ICY),
    TUNDRA (BiomeType.ICY),
    WET_BOREAL (BiomeType.COOL),
    DRY_BOREAL (BiomeType.COOL),
    WET_TEMPERATE (BiomeType.WARM),
    DRY_TEMPERATE (BiomeType.WARM),
    COOL_TEMPERATE (BiomeType.COOL),
    WARM_TEMPERATE (BiomeType.WARM),
    SUBTROPICAL (BiomeType.WARM),
    TROPICAL (BiomeType.DESERT),
    MEDITERRANEAN (BiomeType.WARM),
    SAVANNA (BiomeType.DESERT),
    HOT_DESERT (BiomeType.DESERT),
    NETHER (null);

    public final BiomeType biomeType;
    private int totalBiomesWeight;
    private int totalIslandBiomesWeight;

    private ArrayList<WeightedBiomeEntry> landBiomes = Lists.newArrayList();
    private ArrayList<WeightedBiomeEntry> islandBiomes = Lists.newArrayList();

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

    public BOPClimates addIslandBiome(int weight, Biome biome)
    {
        return this.addIslandBiome(new WeightedBiomeEntry(weight, biome));
    }

    public BOPClimates addIslandBiome(WeightedBiomeEntry biomeEntry)
    {
        this.totalIslandBiomesWeight += biomeEntry.weight;
        this.islandBiomes.add(biomeEntry);
        return this;
    }

    public Biome getRandomBiome(INoiseRandom context, Biome fallback)
    {
        if (this.totalBiomesWeight == 0)
            return fallback;

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

    public Biome getRandomIslandBiome(INoiseRandom context, Biome fallback)
    {
        if (this.totalIslandBiomesWeight == 0)
            return fallback;

        int weight = context.random(this.totalIslandBiomesWeight);
        Iterator<WeightedBiomeEntry> iterator = this.islandBiomes.iterator();
        WeightedBiomeEntry item;
        do
        {
            item = iterator.next();
            weight -= item.weight;
        }
        while (weight >= 0);
        return item.biome;
    }

    public Biome getRandomOceanBiome(INoiseRandom context, boolean deep)
    {
        return (deep ? Biomes.DEEP_OCEAN : Biomes.OCEAN);
    }

    static
    {
        // Set up vanilla biomes
        BOPClimates.ICE_CAP.addBiome(10, Biomes.SNOWY_TUNDRA);
        BOPClimates.TUNDRA.addBiome(10, Biomes.SNOWY_TAIGA).addBiome(7, Biomes.MOUNTAINS);
        BOPClimates.WET_BOREAL.addBiome(10, Biomes.TAIGA);
        BOPClimates.DRY_BOREAL.addBiome(5, Biomes.GIANT_TREE_TAIGA);
        BOPClimates.WET_TEMPERATE.addBiome(5, Biomes.DARK_FOREST).addBiome(7, Biomes.SWAMP);
        BOPClimates.DRY_TEMPERATE.addBiome(7, Biomes.BIRCH_FOREST);
        BOPClimates.COOL_TEMPERATE.addBiome(10, Biomes.FOREST);
        BOPClimates.WARM_TEMPERATE.addBiome(10, Biomes.PLAINS);
        BOPClimates.SUBTROPICAL.addBiome(1, Biomes.LUKEWARM_OCEAN);
        BOPClimates.TROPICAL.addBiome(15, Biomes.JUNGLE);
        BOPClimates.MEDITERRANEAN.addBiome(1, Biomes.PLAINS);
        BOPClimates.SAVANNA.addBiome(10, Biomes.SAVANNA);
        BOPClimates.HOT_DESERT.addBiome(15, Biomes.DESERT).addBiome(10, Biomes.BADLANDS_PLATEAU);

        BOPClimates.NETHER.addBiome(10, Biomes.NETHER);
    }

    private static BOPClimates[] values = BOPClimates.values();

    public static BOPClimates lookup(int i) {return values[i];}

    // map temperature and rainfall to climates
    // temperature values from 0 (cold) to 8 (hot) and rainfall values from 0 (wet) to 11 (dry), index is (temperatureValue * 12) + rainfallValue
    // we will contrive to make any combination equally likely, so the overall rarity of each climate is in proportion to the number of times it appears in the array
    private static final BOPClimates[] climateMapping = new BOPClimates[]
    {
    	WET_BOREAL,		TUNDRA,			ICE_CAP,		ICE_CAP,		ICE_CAP,		ICE_CAP,		ICE_CAP,		ICE_CAP,		ICE_CAP,		ICE_CAP,		TUNDRA,			DRY_BOREAL,
    	WET_BOREAL,		WET_BOREAL,		TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			TUNDRA,			DRY_BOREAL,		DRY_BOREAL,
    	WET_TEMPERATE,	WET_BOREAL,		WET_BOREAL,		WET_BOREAL,		WET_BOREAL,		WET_BOREAL,		DRY_BOREAL,		DRY_BOREAL,		DRY_BOREAL,		DRY_BOREAL,		DRY_BOREAL,		DRY_TEMPERATE,
    	WET_TEMPERATE,	WET_TEMPERATE,	WET_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,
    	WET_TEMPERATE,	WET_TEMPERATE,	WET_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	COOL_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,	DRY_TEMPERATE,
    	SUBTROPICAL,	SUBTROPICAL,	SUBTROPICAL,	WARM_TEMPERATE, WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	SAVANNA,		SAVANNA,		SAVANNA,
    	TROPICAL,		TROPICAL,		SUBTROPICAL,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	WARM_TEMPERATE,	SAVANNA,		HOT_DESERT,		HOT_DESERT,
    	TROPICAL,		TROPICAL,		SUBTROPICAL,	SUBTROPICAL,	MEDITERRANEAN,	MEDITERRANEAN,	MEDITERRANEAN,	MEDITERRANEAN,	SAVANNA,		SAVANNA,		HOT_DESERT,		HOT_DESERT,
    	TROPICAL,		TROPICAL,		TROPICAL,		SUBTROPICAL,	MEDITERRANEAN,	MEDITERRANEAN,	MEDITERRANEAN,	MEDITERRANEAN,	SAVANNA,		HOT_DESERT,		HOT_DESERT,		HOT_DESERT
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
