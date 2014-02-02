package biomesoplenty.common.core;

import static biomesoplenty.common.configuration.BOPConfigurationIDs.promisedLandDimID;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.WorldChunkManager;
import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeDictionary.Type;
import net.minecraftforge.common.BiomeManager;
import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeEntry;
import biomesoplenty.api.BOPBiomeHelper.TemperatureType;
import biomesoplenty.common.biomes.BiomeGenAlps;
import biomesoplenty.common.biomes.BiomeGenArctic;
import biomesoplenty.common.biomes.BiomeGenBambooForest;
import biomesoplenty.common.biomes.BiomeGenBayou;
import biomesoplenty.common.biomes.BiomeGenBog;
import biomesoplenty.common.biomes.BiomeGenBorealForest;
import biomesoplenty.common.biomes.BiomeGenBrushland;
import biomesoplenty.common.biomes.BiomeGenCanyon;
import biomesoplenty.common.biomes.BiomeGenChaparral;
import biomesoplenty.common.biomes.BiomeGenCherryBlossomGrove;
import biomesoplenty.common.biomes.BiomeGenConiferousForest;
import biomesoplenty.common.biomes.BiomeGenConiferousForestSnow;
import biomesoplenty.common.biomes.BiomeGenCrag;
import biomesoplenty.common.biomes.BiomeGenDeadForest;
import biomesoplenty.common.biomes.BiomeGenDeadSwamp;
import biomesoplenty.common.biomes.BiomeGenDeciduousForest;
import biomesoplenty.common.biomes.BiomeGenDunes;
import biomesoplenty.common.biomes.BiomeGenFen;
import biomesoplenty.common.biomes.BiomeGenFlowerField;
import biomesoplenty.common.biomes.BiomeGenFrostForest;
import biomesoplenty.common.biomes.BiomeGenGrassland;
import biomesoplenty.common.biomes.BiomeGenGrove;
import biomesoplenty.common.biomes.BiomeGenHeathland;
import biomesoplenty.common.biomes.BiomeGenHighland;
import biomesoplenty.common.biomes.BiomeGenJadeCliffs;
import biomesoplenty.common.biomes.BiomeGenLavenderFields;
import biomesoplenty.common.biomes.BiomeGenLushDesert;
import biomesoplenty.common.biomes.BiomeGenLushSwamp;
import biomesoplenty.common.biomes.BiomeGenMapleWoods;
import biomesoplenty.common.biomes.BiomeGenMarsh;
import biomesoplenty.common.biomes.BiomeGenMeadow;
import biomesoplenty.common.biomes.BiomeGenMoor;
import biomesoplenty.common.biomes.BiomeGenMountain;
import biomesoplenty.common.biomes.BiomeGenMysticGrove;
import biomesoplenty.common.biomes.BiomeGenOminousWoods;
import biomesoplenty.common.biomes.BiomeGenOriginValley;
import biomesoplenty.common.biomes.BiomeGenOutback;
import biomesoplenty.common.biomes.BiomeGenPasture;
import biomesoplenty.common.biomes.BiomeGenPrairie;
import biomesoplenty.common.biomes.BiomeGenQuagmire;
import biomesoplenty.common.biomes.BiomeGenRainforest;
import biomesoplenty.common.biomes.BiomeGenRedwoodForest;
import biomesoplenty.common.biomes.BiomeGenSacredSprings;
import biomesoplenty.common.biomes.BiomeGenSeasonalForest;
import biomesoplenty.common.biomes.BiomeGenShield;
import biomesoplenty.common.biomes.BiomeGenShrubland;
import biomesoplenty.common.biomes.BiomeGenSilkglades;
import biomesoplenty.common.biomes.BiomeGenSludgepit;
import biomesoplenty.common.biomes.BiomeGenSpruceWoods;
import biomesoplenty.common.biomes.BiomeGenTemperateRainforest;
import biomesoplenty.common.biomes.BiomeGenThicket;
import biomesoplenty.common.biomes.BiomeGenTimber;
import biomesoplenty.common.biomes.BiomeGenTropicalRainforest;
import biomesoplenty.common.biomes.BiomeGenTropics;
import biomesoplenty.common.biomes.BiomeGenTundra;
import biomesoplenty.common.biomes.BiomeGenVolcano;
import biomesoplenty.common.biomes.BiomeGenWasteland;
import biomesoplenty.common.biomes.BiomeGenWetland;
import biomesoplenty.common.biomes.BiomeGenWoodland;
import biomesoplenty.common.biomes.promisedland.BiomeGenPromisedLandForest;
import biomesoplenty.common.biomes.promisedland.BiomeGenPromisedLandPlains;
import biomesoplenty.common.biomes.promisedland.BiomeGenPromisedLandSwamp;
import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.configuration.BOPConfigurationMisc;
import biomesoplenty.common.world.WorldTypeBOP;

public class BOPBiomes 
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static BOPBiomeEntry onlyBiome;
	
	public static void init()
	{
		BOPBiomeHelper.init();
		registerBiomes();
		addBiomesToDictionary();
		addSpawnBiomes();
	}
	
	private static void registerBiomes()
	{
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.desert, TemperatureType.HOT, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.desert, TemperatureType.HOT, 50)); 
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.desert, TemperatureType.HOT, 50)); 
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.field_150588_X, TemperatureType.HOT, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.field_150588_X, TemperatureType.HOT, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.plains, TemperatureType.HOT, 50));
		       
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.forest, TemperatureType.WARM, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.field_150585_R, TemperatureType.WARM, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.extremeHills, TemperatureType.WARM, 50));
		registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.plains, TemperatureType.WARM, 50));
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.field_150583_P, TemperatureType.WARM, 50)); 
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.swampland, TemperatureType.WARM, 50));
               
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.forest, TemperatureType.COOL, 50)); 
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.extremeHills, TemperatureType.COOL, 50));  
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.taiga, TemperatureType.COOL, 50)); 
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.plains, TemperatureType.COOL, 50));
              
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.icePlains, TemperatureType.ICY, 50));  
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.icePlains, TemperatureType.ICY, 50)); 
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.icePlains, TemperatureType.ICY, 50));  
        registerVanillaBiome(new BOPBiomeEntry(BiomeGenBase.field_150584_S, TemperatureType.ICY, 50));
        
        registerBiome(new BOPBiomeEntry(new BiomeGenAlps(BOPConfigurationIDs.alpsID).setBiomeName("Alps"), TemperatureType.ICY, 25)); 
        registerBiome(new BOPBiomeEntry(new BiomeGenArctic(BOPConfigurationIDs.arcticID).setBiomeName("Arctic"), TemperatureType.ICY, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenBambooForest(BOPConfigurationIDs.bambooForestID).setBiomeName("Bamboo Forest"), TemperatureType.HOT, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenBayou(BOPConfigurationIDs.bayouID).setBiomeName("Bayou"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenBog(BOPConfigurationIDs.bogID).setBiomeName("Bog"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenBorealForest(BOPConfigurationIDs.borealForestID).setBiomeName("Boreal Forest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenBrushland(BOPConfigurationIDs.brushlandID).setBiomeName("Brushland"), TemperatureType.HOT, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenCanyon(BOPConfigurationIDs.canyonID).setBiomeName("Canyon"), TemperatureType.HOT, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenChaparral(BOPConfigurationIDs.chaparralID).setBiomeName("Chaparral"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenCherryBlossomGrove(BOPConfigurationIDs.cherryBlossomGroveID).setBiomeName("Cherry Blossom Grove"), TemperatureType.COOL, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenConiferousForest(BOPConfigurationIDs.coniferousForestID).setBiomeName("Coniferous Forest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenConiferousForestSnow(BOPConfigurationIDs.coniferousForestSnowID).setBiomeName("Snowy Coniferous Forest"), TemperatureType.ICY, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenCrag(BOPConfigurationIDs.cragID).setBiomeName("Crag"), TemperatureType.COOL, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenDeadForest(BOPConfigurationIDs.deadForestID).setBiomeName("Dead Forest"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenDeadSwamp(BOPConfigurationIDs.deadSwampID).setBiomeName("Dead Swamp"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenDeciduousForest(BOPConfigurationIDs.deciduousForestID).setBiomeName("Deciduous Forest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenDunes(BOPConfigurationIDs.dunesID).setBiomeName("Dunes"), TemperatureType.HOT, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenFen(BOPConfigurationIDs.fenID).setBiomeName("Fen"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenFlowerField(BOPConfigurationIDs.flowerFieldID).setBiomeName("Flower Field"), TemperatureType.WARM, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenFrostForest(BOPConfigurationIDs.frostForestID).setBiomeName("Frost Forest"), TemperatureType.ICY, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenGrassland(BOPConfigurationIDs.grasslandID).setBiomeName("Grassland"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenGrove(BOPConfigurationIDs.groveID).setBiomeName("Grove"), TemperatureType.COOL, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenHeathland(BOPConfigurationIDs.heathlandID).setBiomeName("Heathland"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenHighland(BOPConfigurationIDs.highlandID).setBiomeName("Highland"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenJadeCliffs(BOPConfigurationIDs.jadeCliffsID).setBiomeName("Jade Cliffs"), TemperatureType.WARM, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenLavenderFields(BOPConfigurationIDs.lavenderFieldsID).setBiomeName("Lavender Fields"), TemperatureType.WARM, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenLushDesert(BOPConfigurationIDs.lushDesertID).setBiomeName("Lush Desert"), TemperatureType.HOT, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenLushSwamp(BOPConfigurationIDs.lushSwampID).setBiomeName("Lush Swamp"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMapleWoods(BOPConfigurationIDs.mapleWoodsID).setBiomeName("Maple Woods"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMarsh(BOPConfigurationIDs.marshID).setBiomeName("Marsh"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMeadow(BOPConfigurationIDs.meadowID).setBiomeName("Meadow"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMoor(BOPConfigurationIDs.moorID).setBiomeName("Moor"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMountain(BOPConfigurationIDs.mountainID).setBiomeName("Mountain"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenMysticGrove(BOPConfigurationIDs.mysticGroveID).setBiomeName("Mystic Grove"), TemperatureType.WARM, 15));
        registerBiome(new BOPBiomeEntry(new BiomeGenOminousWoods(BOPConfigurationIDs.ominousWoodsID).setBiomeName("Ominous Woods"), TemperatureType.COOL, 15));
        registerBiome(new BOPBiomeEntry(new BiomeGenOriginValley(BOPConfigurationIDs.originValleyID).setBiomeName("Origin Valley"), TemperatureType.WARM, 5));
        registerBiome(new BOPBiomeEntry(new BiomeGenOutback(BOPConfigurationIDs.outbackID).setBiomeName("Outback"), TemperatureType.HOT, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenPasture(BOPConfigurationIDs.pastureID).setBiomeName("Pasture"), TemperatureType.WARM, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenPrairie(BOPConfigurationIDs.prairieID).setBiomeName("Prairie"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenQuagmire(BOPConfigurationIDs.quagmireID).setBiomeName("Quagmire"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenRainforest(BOPConfigurationIDs.rainforestID).setBiomeName("Rainforest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenRedwoodForest(BOPConfigurationIDs.redwoodForestID).setBiomeName("Redwood Forest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenSacredSprings(BOPConfigurationIDs.sacredSpringsID).setBiomeName("Sacred Springs"), TemperatureType.WARM, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenSeasonalForest(BOPConfigurationIDs.seasonalForestID).setBiomeName("Seasonal Forest"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenShield(BOPConfigurationIDs.shieldID).setBiomeName("Shield"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenShrubland(BOPConfigurationIDs.shrublandID).setBiomeName("Shrubland"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenSilkglades(BOPConfigurationIDs.silkgladesID).setBiomeName("Silkglades"), TemperatureType.COOL, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenSludgepit(BOPConfigurationIDs.sludgepitID).setBiomeName("Sludgepit"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenSpruceWoods(BOPConfigurationIDs.spruceWoodsID).setBiomeName("Spruce Woods"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenTemperateRainforest(BOPConfigurationIDs.temperateRainforestID).setBiomeName("Temperate Rainforest"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenThicket(BOPConfigurationIDs.thicketID).setBiomeName("Thicket"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenTimber(BOPConfigurationIDs.timberID).setBiomeName("Timber"), TemperatureType.COOL, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenTropicalRainforest(BOPConfigurationIDs.tropicalRainforestID).setBiomeName("Tropical Rainforest"), TemperatureType.HOT, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenTropics(BOPConfigurationIDs.tropicsID).setBiomeName("Tropics"), TemperatureType.HOT, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenTundra(BOPConfigurationIDs.tundraID).setBiomeName("Tundra"), TemperatureType.ICY, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenVolcano(BOPConfigurationIDs.volcanoID).setBiomeName("Volcano"), TemperatureType.HOT, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenWasteland(BOPConfigurationIDs.wastelandID).setBiomeName("Wasteland"), TemperatureType.HOT, 25));
        registerBiome(new BOPBiomeEntry(new BiomeGenWetland(BOPConfigurationIDs.wetlandID).setBiomeName("Wetland"), TemperatureType.WARM, 50));
        registerBiome(new BOPBiomeEntry(new BiomeGenWoodland(BOPConfigurationIDs.woodlandID).setBiomeName("Woodland"), TemperatureType.WARM, 50));

        registerBiome(new BOPBiomeEntry(new BiomeGenPromisedLandForest(BOPConfigurationIDs.wonderousWoodsID).setBiomeName("Wonderous Woods"), TemperatureType.WARM, 50), promisedLandDimID);
        registerBiome(new BOPBiomeEntry(new BiomeGenPromisedLandPlains(BOPConfigurationIDs.majesticMeadowID).setBiomeName("Majestic Meadow"), TemperatureType.WARM, 50), promisedLandDimID);
        registerBiome(new BOPBiomeEntry(new BiomeGenPromisedLandSwamp(BOPConfigurationIDs.blessedBogID).setBiomeName("Blessed Bog"), TemperatureType.WARM, 50), promisedLandDimID);
	}

	private static void addSpawnBiomes()
	{
		if (BOPConfigurationMisc.onlySpawnOnBeaches)
		{
			clearAllSpawnBiomes();

			addSpawnBiome(BiomeGenBase.beach);
			//TODO:					   stoneBeach
			addSpawnBiome(BiomeGenBase.field_150576_N);
			//TODO:						coldBeach
			addSpawnBiome(BiomeGenBase.field_150577_O);
		}
		else
		{
			for (BOPBiomeEntry entry : BOPBiomeHelper.biomeLists[0 + 1].values())
			{
				addSpawnBiome(entry.biome);
			}
		}
	}
	
	private static void addBiomesToDictionary()
	{
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("alps"), Type.FROZEN, Type.MOUNTAIN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("alpsBase"), Type.FROZEN, Type.MOUNTAIN, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("alpsForest"), Type.FROZEN, Type.MOUNTAIN);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("arctic"), Type.FROZEN, Type.WASTELAND);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("autumnHills"), Type.FOREST, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("badlands"), Type.DESERT, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("bambooForest"), Type.JUNGLE, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("bayou"), Type.SWAMP, Type.WATER);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("beachGravel"), Type.BEACH);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("beachOvergrown"), Type.BEACH, Type.FOREST);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("birchForest"), Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("bog"), Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("borealForest"), Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("brushland"), Type.DESERT, Type.FOREST, Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("canyon"), Type.DESERT, Type.MOUNTAIN, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("canyonRavine"), Type.DESERT, Type.HILLS);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("chaparral"), Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("cherryBlossomGrove"), Type.MAGICAL, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("coniferousForest"), Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("snowyConiferousForest"), Type.FROZEN, Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("crag"), Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("deadForest"), Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("deadForestSnow"), Type.FOREST, Type.FROZEN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("deadlands"), Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("deadSwamp"), Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("deciduousForest"), Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("dunes"), Type.BEACH, Type.DESERT, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("fen"), Type.FOREST, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("flowerField"), Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("fieldForest"), Type.PLAINS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("frostForest"), Type.FROZEN, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("fungiForest"), Type.MAGICAL, Type.MUSHROOM, Type.FOREST, Type.SWAMP);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("garden"), Type.MAGICAL, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("glacier"), Type.FROZEN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("grassland"), Type.PLAINS, Type.SWAMP, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("grove"), Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("heathland"), Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("highland"), Type.HILLS, Type.MOUNTAIN);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("hotSprings"), Type.HILLS, Type.FOREST, Type.WATER);
       // BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("icyHills"), Type.FROZEN, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("jadeCliffs"), Type.FOREST, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("lavenderFields"), Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("lushDesert"), Type.DESERT, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("lushSwamp"), Type.SWAMP, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mangrove"), Type.WATER, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("mapleWoods"), Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("marsh"), Type.SWAMP, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("meadow"), Type.FOREST, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("meadowForest"), Type.FOREST, Type.PLAINS);
        
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mesa"), Type.DESERT, Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("moor"), Type.HILLS, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("mountain"), Type.MOUNTAIN);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("mysticGrove"), Type.MAGICAL, Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("mysticGroveThin"), Type.MAGICAL, Type.FOREST);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBase"), Type.NETHER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherGarden"), Type.NETHER, Type.JUNGLE);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherDesert"), Type.NETHER, Type.DESERT);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherLava"), Type.NETHER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBone"), Type.NETHER, Type.WASTELAND);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("netherBlood"), Type.NETHER);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oasis"), Type.DESERT, Type.JUNGLE);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanAbyss"), Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanCoral"), Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("oceanKelp"), Type.WATER, Type.FOREST);

        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("ominousWoods"), Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("ominousWoodsThick"), Type.MAGICAL);

        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("orchard"), Type.FOREST, Type.PLAINS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("outback"), Type.DESERT, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("overgrownGreens"), Type.JUNGLE, Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("pasture"), Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("pastureThin"), Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("pastureMeadow"), Type.PLAINS, Type.FOREST);
        
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("polar"), Type.FROZEN, Type.WATER);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("prairie"), Type.PLAINS);

        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get(promisedLandDimID, "wonderousWoods"), Type.FOREST, Type.MAGICAL);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get(promisedLandDimID, "majesticMeadow"), Type.PLAINS, Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.get(promisedLandDimID, "promisedLandShrub"), Type.PLAINS, Type.FOREST, Type.MAGICAL);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get(promisedLandDimID, "blessedBog"), Type.SWAMP, Type.MAGICAL);

        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("quagmire"), Type.WASTELAND, Type.SWAMP);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("rainforest"), Type.JUNGLE, Type.HILLS, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("redwoodForest"), Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("sacredSprings"), Type.MOUNTAIN, Type.FOREST, Type.MAGICAL);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("savanna"), Type.DESERT, Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("savannaPlateau"), Type.DESERT, Type.PLAINS, Type.HILLS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("scrubland"), Type.DESERT, Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("seasonalForest"), Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("seasonalSpruceForest"), Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("shield"), Type.FOREST, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("shrubland"), Type.PLAINS);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("shrublandForest"), Type.PLAINS);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("silkglades"), Type.SWAMP, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("sludgepit"), Type.SWAMP, Type.FOREST, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("spruceWoods"), Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("steppe"), Type.PLAINS, Type.DESERT);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("temperateRainforest"), Type.FOREST, Type.HILLS);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("thicket"), Type.PLAINS, Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("timber"), Type.FOREST);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("timberThin"), Type.FOREST);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("tropicalRainforest"), Type.JUNGLE);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("tropics"), Type.JUNGLE, Type.WATER);
        //BiomeDictionary.registerBiomeType(BOPBiomeHelper.getBOPBiome("tropicsMountain"), Type.JUNGLE, Type.WATER);
        
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("tundra"), Type.FROZEN, Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("volcano"), Type.WASTELAND, Type.MOUNTAIN);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("wasteland"), Type.WASTELAND);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("wetland"), Type.SWAMP, Type.FOREST);
        BiomeDictionary.registerBiomeType(BOPBiomeHelper.get("woodland"), Type.FOREST);
	}
	
	public static void registerOnlyBiome(BOPBiomeEntry biome)
	{
		onlyBiome = biome;
		registerBiome(biome);
	}
	
	public static void registerVanillaBiome(BOPBiomeEntry biome)
	{
	    BOPBiomeHelper.registerBiome(biome, "minecraft:" + BOPBiomeHelper.convertBiomeName(biome.biome.biomeName));
	}
	
	public static void registerBiome(BOPBiomeEntry biome, int dimID)
	{
	    BOPBiomeHelper.registerBiome(biome, dimID, "biomesoplenty:" + BOPBiomeHelper.convertBiomeName(biome.biome.biomeName));
	}
	
	public static void registerBiome(BOPBiomeEntry biome)
	{
		registerBiome(biome, 0);
	}
	
	public static void addSpawnBiome(BiomeGenBase biome)
	{
	    BiomeManager.addSpawnBiome(biome);
	}
	
	public static void clearAllSpawnBiomes()
	{
	    WorldChunkManager.allowedBiomes.clear();
	}
}
