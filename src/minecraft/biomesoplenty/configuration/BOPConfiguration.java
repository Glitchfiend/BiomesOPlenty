package biomesoplenty.configuration;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLLog;

public class BOPConfiguration {

	public static Configuration config;
	
	// Configuration variables
	public static boolean skyColors;
	public static int biomeSize;
	public static boolean addToDefault;
	public static boolean achievements;
	public static boolean vanillaEnhanced;
	
	public static int villageDistance;
	
	public static int promisedLandDimID;
	
	public static int promisedLandSkyColor;

	public static boolean alpsGen;
	public static boolean arcticGen;
	public static boolean badlandsGen;
	public static boolean bambooForestGen;
	public static boolean bayouGen;
	public static boolean birchForestGen;
	public static boolean bogGen;
	public static boolean borealForestGen;
	public static boolean canyonGen;
	public static boolean chaparralGen;
	public static boolean cherryBlossomGroveGen;
	public static boolean coniferousForestGen;
	public static boolean cragGen;
	public static boolean deadForestGen;
	public static boolean deadSwampGen;
	public static boolean deadlandsGen;
	public static boolean deciduousForestGen;
	public static boolean drylandsGen;
	public static boolean dunesGen;
	public static boolean fenGen;
	public static boolean fieldGen;
	public static boolean frostForestGen;
	public static boolean fungiForestGen;
	public static boolean gardenGen;
	public static boolean glacierGen;
	public static boolean grasslandGen;
	public static boolean groveGen;
	public static boolean heathlandGen;
	public static boolean highlandGen;
	public static boolean icyHillsGen;
	public static boolean jadeCliffsGen;
	public static boolean lushDesertGen;
	public static boolean lushSwampGen;
	public static boolean mangroveGen;
	public static boolean mapleWoodsGen;
	public static boolean marshGen;
	public static boolean meadowGen;
	public static boolean mesaGen;
	public static boolean moorGen;
	public static boolean mountainGen;
//	public static boolean mushroomIslandGen;
	public static boolean mysticGroveGen;
	public static boolean oasisGen;
	public static boolean ominousWoodsGen;
	public static boolean orchardGen;
	public static boolean originValleyGen;
	public static boolean outbackGen;
	public static boolean pastureGen;
	public static boolean polarGen;
	public static boolean prairieGen;
	public static boolean quagmireGen;
	public static boolean rainforestGen;
	public static boolean redwoodForestGen;
	public static boolean sacredSpringsGen;
	public static boolean savannaGen;
	public static boolean scrublandGen;
	public static boolean seasonalForestGen;
	public static boolean shieldGen;
	public static boolean shrublandGen;
	public static boolean snowyWoodsGen;
	public static boolean spruceWoodsGen;
	public static boolean steppeGen;
	public static boolean swampwoodsGen;
	public static boolean temperateRainforestGen;
	public static boolean thicketGen;
	public static boolean tropicalRainforestGen;
	public static boolean tropicsGen;
	public static boolean tundraGen;
	public static boolean volcanoGen;
	public static boolean wastelandGen;
	public static boolean wetlandGen;
	public static boolean woodlandGen;
	
	// Vanilla biomes
	public static boolean plainsGen;
	public static boolean desertGen;
	public static boolean extremeHillsGen;
	public static boolean forestGen;
	public static boolean taigaGen;
	public static boolean swamplandGen;
	public static boolean jungleGen;

	//Block IDS
	public static int mudID;
	public static int driedDirtID;
	public static int redRockID;
	public static int ashID;
	public static int ashStoneID;
	public static int hardIceID;
	public static int originGrassID;
	public static int hardSandID;
	public static int hardDirtID;
	public static int holyGrassID;
	public static int holyDirtID;
	public static int holyStoneID;
	public static int cragRockID;

	public static int plantsID;
	public static int flowersID;
	public static int mushroomsID;
	public static int willowID;
	
	public static int leaves1ID;
	public static int leaves2ID;
	public static int foliageID;
	
	public static int leavesFruitID;
	public static int bambooID;
	public static int mudBrickBlockID;
	public static int mudBrickStairsID;
	public static int stoneDoubleSlabID;
	public static int stoneSingleSlabID;
	
	public static int treeMossID;
	
	public static int logs1ID;
	public static int logs2ID;
	public static int logs3ID;
	public static int petalsID;
	public static int saplingsID;
	public static int colourizedSaplingsID;
	public static int redCobbleStairsID;
	public static int redBrickStairsID;
	public static int holyCobbleStairsID;
	public static int holyBrickStairsID;
	
	public static int promisedLandPortalID;
	public static int amethystOreID;
//	public static int amethystBlockID;
//	public static int bambooThatchingID;
	
	public static int mossID;
//	public static int smolderingGrassID;
	
//	public static int quicksandID;
	
	public static int planksID;
	
	public static int woodenDoubleSlab1ID;
	public static int woodenSingleSlab1ID;
	public static int woodenDoubleSlab2ID;
	public static int woodenSingleSlab2ID;
	
	public static int acaciaStairsID;
	public static int cherryStairsID;
	public static int darkStairsID;
	public static int firStairsID;
	public static int holyStairsID;
	public static int magicStairsID;
	public static int mangroveStairsID;
	public static int palmStairsID;
	public static int redwoodStairsID;
	public static int willowStairsID;
	
	public static int colourizedLeavesID;
	
	public static int crystalID;
	public static int cloudID;

	//Item IDs
	public static int shroomPowderID;
	public static int sunflowerSeedsID;
	public static int berriesID;

	public static int ancientStaffID;
	public static int enderporterID;

	public static int bopDiscID;
	public static int bopDiscMudID;
	
	public static int miscItemsID;
	public static int mudballID;
	public static int dartBlowerID;
	public static int dartID;
	
	public static int swordMudID;
	public static int shovelMudID;
	public static int pickaxeMudID;
	public static int axeMudID;
	public static int hoeMudID;
	public static int helmetMudID;
	public static int chestplateMudID;
	public static int leggingsMudID;
	public static int bootsMudID;
	

	public static int swordAmethystID;
	public static int shovelAmethystID;
	public static int pickaxeAmethystID;
	public static int axeAmethystID;
	public static int hoeAmethystID;
	public static int helmetAmethystID;
	public static int chestplateAmethystID;
	public static int leggingsAmethystID;
	public static int bootsAmethystID;
	
	public static int flowerBandID;

	//Biome IDS
	public static int alpsID;
	public static int arcticID;
	public static int arcticForestID;
	public static int badlandsID;
	public static int bambooForestID;
	public static int bayouID;
	public static int birchForestID;
	public static int bogID;
	public static int borealForestID;
	public static int canyonID;
	public static int chaparralID;
	public static int cherryBlossomGroveID;
	public static int coniferousForestID;
	public static int coniferousForestThinID;
	public static int cragID;
	public static int deadForestID;
	public static int deadSwampID;
	public static int deadlandsID;
	public static int deciduousForestID;
	public static int drylandsID;
	public static int dunesID;
	public static int fenID;
	public static int fieldID;
	public static int frostForestID;
	public static int fungiForestID;
	public static int gardenID;
	public static int glacierID;
	public static int grasslandID;
	public static int groveID;
	public static int groveThinID;
	public static int heathlandID;
	public static int highlandID;
	public static int icyHillsID;
	public static int jadeCliffsID;
	public static int lushDesertID;
	public static int lushSwampID;
	public static int mangroveID;
	public static int mapleWoodsID;
	public static int marshID;
	public static int meadowID;
	public static int meadowForestID;
	public static int mesaID;
	public static int moorID;
	public static int mountainID;
	public static int mysticGroveID;
	public static int oasisID;
	public static int ominousWoodsID;
	public static int orchardID;
	public static int originValleyID;
	public static int outbackID;
	public static int pastureID;
	public static int polarID;
	public static int prairieID;
	public static int promisedLandForestID;
	public static int promisedLandPlainsID;
	public static int promisedLandSwampID;
	public static int quagmireID;
	public static int rainforestID;
	public static int redwoodForestID;
	public static int reefID;
	public static int sacredSpringsID;
	public static int savannaID;
	public static int savannaThickID;
	public static int scrublandID;
	public static int seasonalForestID;
	public static int shieldID;
	public static int shoreID;
	public static int shrublandID;
	public static int snowyWoodsID;
	public static int spruceWoodsID;
	public static int steppeID;
	public static int swampwoodsID;
	public static int temperateRainforestID;
	public static int thicketID;
	public static int tropicalRainforestID;
	public static int tropicsID;
	public static int tundraID;
	public static int tundraDryID;
	public static int volcanoID;
	public static int wastelandID;
	public static int wastelandTreesID;
	public static int wetlandID;
	public static int woodlandID;
	public static int plainsNewID;
	public static int desertNewID;
	public static int desertHillsNewID;
	public static int extremeHillsNewID;
	public static int extremeHillsEdgeNewID;
	public static int forestNewID;
	public static int forestHillsNewID;
	public static int taigaNewID;
	public static int taigaHillsNewID;
	public static int swamplandNewID;
	public static int jungleNewID;
	public static int jungleHillsNewID;

	public static int entityMudballID;
	public static int entityDartID;
	public static int entityPoisonDartID;
	
	public static int jungleSpiderID;
	public static int rosesterID;
	public static int globID;
	
	// Village biomes
	public static boolean alpsVillage;
    public static boolean arcticVillage;
    public static boolean badlandsVillage;
    public static boolean bambooForestVillage;
    public static boolean bayouVillage;
    public static boolean birchForestVillage;
    public static boolean bogVillage;
    public static boolean borealForestVillage;
    public static boolean canyonVillage;
    public static boolean chaparralVillage;
    public static boolean cherryBlossomGroveVillage;
    public static boolean coniferousForestVillage;
    public static boolean cragVillage;
    public static boolean deadForestVillage;
    public static boolean deadSwampVillage;
    public static boolean deadlandsVillage;
    public static boolean deciduousForestVillage;
    public static boolean drylandsVillage;
    public static boolean dunesVillage;
    public static boolean fenVillage;
    public static boolean fieldVillage;
    public static boolean frostForestVillage;
    public static boolean fungiForestVillage;
    public static boolean gardenVillage;
    public static boolean glacierVillage;
    public static boolean grasslandVillage;
    public static boolean groveVillage;
    public static boolean heathlandVillage;
    public static boolean highlandVillage;
    public static boolean icyHillsVillage;
    public static boolean jadeCliffsVillage;
    public static boolean lushDesertVillage;
    public static boolean lushSwampVillage;
    public static boolean mangroveVillage;
    public static boolean mapleWoodsVillage;
    public static boolean marshVillage;
    public static boolean meadowVillage;
    public static boolean mesaVillage;
    public static boolean moorVillage;
    public static boolean mountainVillage;
//  public static boolean mushroomIslandVillage;
    public static boolean mysticGroveVillage;
    public static boolean oasisVillage;
    public static boolean ominousWoodsVillage;
    public static boolean orchardVillage;
    public static boolean originValleyVillage;
    public static boolean outbackVillage;
    public static boolean pastureVillage;
	public static boolean polarVillage;
    public static boolean prairieVillage;
    public static boolean quagmireVillage;
    public static boolean rainforestVillage;
    public static boolean redwoodForestVillage;
    public static boolean sacredSpringsVillage;
    public static boolean savannaVillage;
    public static boolean scrublandVillage;
    public static boolean seasonalForestVillage;
    public static boolean shieldVillage;
    public static boolean shrublandVillage;
    public static boolean snowyWoodsVillage;
    public static boolean spruceWoodsVillage;
    public static boolean steppeVillage;
    public static boolean swampwoodsVillage;
    public static boolean temperateRainforestVillage;
    public static boolean thicketVillage;
    public static boolean tropicalRainforestVillage;
    public static boolean tropicsVillage;
    public static boolean tundraVillage;
    public static boolean volcanoVillage;
    public static boolean wastelandVillage;
    public static boolean wetlandVillage;
    public static boolean woodlandVillage;
    public static boolean plainsVillage;
    public static boolean desertVillage;
    public static boolean extremeHillsVillage;
    public static boolean forestVillage;
    public static boolean taigaVillage;
    public static boolean swamplandVillage;
    public static boolean jungleVillage;

	public static void init(File configFile) 
	{
		config = new Configuration(configFile);

		try 
		{
			config.load();

			biomeSize = config.get("Biomes O\' Plenty World Type Settings", "Biome Size", 4, "Default World Type has 4. Large Biomes World Type has 6.").getInt();
			achievements = config.get("Achievement Settings", "Add Biomes O\' Plenty Achievements", true).getBoolean(false);
			vanillaEnhanced = config.get("Biome Settings", "Enhanced Vanilla Biomes", true).getBoolean(false);
			promisedLandDimID = config.get("Dimension Settings", "Promised Land Dimension ID", 20, null).getInt();
			
			addToDefault = config.get("Biome Settings", "Add Biomes To Default World", true).getBoolean(true);
			
			villageDistance = config.get("Biomes O\' Plenty World Type Settings", "Distance between villages", 32, "In Vanilla it is set to 32").getInt();
			if (villageDistance < 8)
			    villageDistance = 8;
			
			//Hard-Coded Colors
			skyColors = config.get("Hard-Coded Colors", "Enable Sky Colors", true).getBoolean(false);
			
			promisedLandSkyColor = config.get("Hard-Coded Colors", "Promised Land Sky Color", 50175, null).getInt();

			//Biome generation
			alpsGen = config.get("Biomes To Generate", "Alps", true).getBoolean(false);
			arcticGen = config.get("Biomes To Generate", "Arctic", true).getBoolean(false);
			badlandsGen = config.get("Biomes To Generate", "Badlands", true).getBoolean(false);
			bambooForestGen = config.get("Biomes To Generate", "BambooForest", true).getBoolean(false);
			bayouGen = config.get("Biomes To Generate", "Bayou", true).getBoolean(false);
			birchForestGen = config.get("Biomes To Generate", "BirchForest", true).getBoolean(false);
			bogGen = config.get("Biomes To Generate", "Bog", true).getBoolean(false);
			borealForestGen = config.get("Biomes To Generate", "BorealForest", true).getBoolean(false);
			canyonGen = config.get("Biomes To Generate", "Canyon", true).getBoolean(false);
			chaparralGen = config.get("Biomes To Generate", "Chaparral", true).getBoolean(false);
			cherryBlossomGroveGen = config.get("Biomes To Generate", "CherryBlossomGrove", true).getBoolean(false);
			coniferousForestGen = config.get("Biomes To Generate", "ConiferousForest", true).getBoolean(false);
			cragGen = config.get("Biomes To Generate", "Crag", true).getBoolean(false);
			deadForestGen = config.get("Biomes To Generate", "DeadForest", true).getBoolean(false);
			deadSwampGen = config.get("Biomes To Generate", "DeadSwamp", true).getBoolean(false);
			deadlandsGen = config.get("Biomes To Generate", "Deadlands", true).getBoolean(false);
			deciduousForestGen = config.get("Biomes To Generate", "DeciduousForest", true).getBoolean(false);
			drylandsGen = config.get("Biomes To Generate", "Drylands", true).getBoolean(false);
			dunesGen = config.get("Biomes To Generate", "Dunes", true).getBoolean(false);
			fenGen = config.get("Biomes To Generate", "Fen", true).getBoolean(false);
			fieldGen = config.get("Biomes To Generate", "Field", true).getBoolean(false);
			frostForestGen = config.get("Biomes To Generate", "FrostForest", true).getBoolean(false);
			fungiForestGen = config.get("Biomes To Generate", "FungiForest", true).getBoolean(false);
			gardenGen = config.get("Biomes To Generate", "Garden", true).getBoolean(false);
			glacierGen = config.get("Biomes To Generate", "Glacier", true).getBoolean(false);
			grasslandGen = config.get("Biomes To Generate", "Grassland", true).getBoolean(false);
			groveGen = config.get("Biomes To Generate", "Grove", true).getBoolean(false);
			heathlandGen = config.get("Biomes To Generate", "Heathland", true).getBoolean(false);
			highlandGen = config.get("Biomes To Generate", "Highland", true).getBoolean(false);
			icyHillsGen = config.get("Biomes To Generate", "IcyHills", true).getBoolean(false);
			jadeCliffsGen = config.get("Biomes To Generate", "JadeCliffs", true).getBoolean(false);
			lushDesertGen = config.get("Biomes To Generate", "LushDesert", true).getBoolean(false);
			lushSwampGen = config.get("Biomes To Generate", "LushSwamp", true).getBoolean(false);
			mangroveGen = config.get("Biomes To Generate", "Mangrove", true).getBoolean(false);
			mapleWoodsGen = config.get("Biomes To Generate", "MapleWoods", true).getBoolean(false);
			marshGen = config.get("Biomes To Generate", "Marsh", true).getBoolean(false);
			meadowGen = config.get("Biomes To Generate", "Meadow", true).getBoolean(false);
			mesaGen = config.get("Biomes To Generate", "Mesa", true).getBoolean(false);
			moorGen = config.get("Biomes To Generate", "Moor", true).getBoolean(false);
			mountainGen = config.get("Biomes To Generate", "Mountain", true).getBoolean(false);
//			mushroomIslandGen = config.get("Biomes To Generate", "MushroomIsland", true).getBoolean(false);
			mysticGroveGen = config.get("Biomes To Generate", "MysticGrove", true).getBoolean(false);
			oasisGen = config.get("Biomes To Generate", "Oasis", true).getBoolean(false);
			ominousWoodsGen = config.get("Biomes To Generate", "OminousWoods", true).getBoolean(false);
			orchardGen = config.get("Biomes To Generate", "Orchard", true).getBoolean(false);
			originValleyGen = config.get("Biomes To Generate", "OriginValley", true).getBoolean(false);
			outbackGen = config.get("Biomes To Generate", "Outback", true).getBoolean(false);
			pastureGen = config.get("Biomes To Generate", "Pasture", true).getBoolean(false);
			polarGen = config.get("Biomes To Generate", "Polar", true).getBoolean(false);
			prairieGen = config.get("Biomes To Generate", "Prairie", true).getBoolean(false);
			quagmireGen = config.get("Biomes To Generate", "Quagmire", true).getBoolean(false);
			rainforestGen = config.get("Biomes To Generate", "Rainforest", true).getBoolean(false);
			redwoodForestGen = config.get("Biomes To Generate", "RedwoodForest", true).getBoolean(false);
			sacredSpringsGen = config.get("Biomes To Generate", "SacredSprings", true).getBoolean(false);
			savannaGen = config.get("Biomes To Generate", "Savanna", true).getBoolean(false);
			scrublandGen = config.get("Biomes To Generate", "Scrubland", true).getBoolean(false);
			seasonalForestGen = config.get("Biomes To Generate", "SeasonalForest", true).getBoolean(false);
			shieldGen = config.get("Biomes To Generate", "Shield", true).getBoolean(false);
			shrublandGen = config.get("Biomes To Generate", "Shrubland", true).getBoolean(false);
			snowyWoodsGen = config.get("Biomes To Generate", "SnowyWoods", true).getBoolean(false);
			spruceWoodsGen = config.get("Biomes To Generate", "SpruceWoods", true).getBoolean(false);
			steppeGen = config.get("Biomes To Generate", "Steppe", true).getBoolean(false);
			swampwoodsGen = config.get("Biomes To Generate", "Swampwoods", true).getBoolean(false);
			temperateRainforestGen = config.get("Biomes To Generate", "TemperateRainforest", true).getBoolean(false);
			thicketGen = config.get("Biomes To Generate", "Thicket", true).getBoolean(false);
			tropicalRainforestGen = config.get("Biomes To Generate", "TropicalRainforest", true).getBoolean(false);
			tropicsGen = config.get("Biomes To Generate", "Tropics", true).getBoolean(false);
			tundraGen = config.get("Biomes To Generate", "Tundra", true).getBoolean(false);
			volcanoGen = config.get("Biomes To Generate", "Volcano", true).getBoolean(false);
			wastelandGen = config.get("Biomes To Generate", "Wasteland", true).getBoolean(false);
			wetlandGen = config.get("Biomes To Generate", "Wetland", true).getBoolean(false);
			woodlandGen = config.get("Biomes To Generate", "Woodland", true).getBoolean(false);
			
			// Vanilla biomes
            desertGen = config.get("Vanilla Biomes To Generate", "Desert", true).getBoolean(true);
            extremeHillsGen = config.get("Vanilla Biomes To Generate", "ExtremeHills", true).getBoolean(true);
            forestGen = config.get("Vanilla Biomes To Generate", "Forest", true).getBoolean(true);
            jungleGen = config.get("Vanilla Biomes To Generate", "Jungle", true).getBoolean(true);
            plainsGen = config.get("Vanilla Biomes To Generate", "Plains", true).getBoolean(true);
            swamplandGen = config.get("Vanilla Biomes To Generate", "Swampland", true).getBoolean(true);
            taigaGen = config.get("Vanilla Biomes To Generate", "Taiga", true).getBoolean(true);
            
			// Biomes with villages
			alpsVillage = config.get("Allow Villages", "Alps", false).getBoolean(false);
            arcticVillage = config.get("Allow Villages", "Arctic", true).getBoolean(false);
            badlandsVillage = config.get("Allow Villages", "Badlands", false).getBoolean(false);
            bambooForestVillage = config.get("Allow Villages", "BambooForest", true).getBoolean(false);
            bayouVillage = config.get("Allow Villages", "Bayou", true).getBoolean(false);
            birchForestVillage = config.get("Allow Villages", "BirchForest", true).getBoolean(false);
            bogVillage = config.get("Allow Villages", "Bog", false).getBoolean(false);
            borealForestVillage = config.get("Allow Villages", "BorealForest", true).getBoolean(false);
            canyonVillage = config.get("Allow Villages", "Canyon", false).getBoolean(false);
            chaparralVillage = config.get("Allow Villages", "Chaparral", true).getBoolean(false);
            cherryBlossomGroveVillage = config.get("Allow Villages", "CherryBlossomGrove", true).getBoolean(false);
            coniferousForestVillage = config.get("Allow Villages", "ConiferousForest", true).getBoolean(false);
            cragVillage = config.get("Allow Villages", "Crag", false).getBoolean(false);
            deadForestVillage = config.get("Allow Villages", "DeadForest", true).getBoolean(false);
            deadSwampVillage = config.get("Allow Villages", "DeadSwamp", false).getBoolean(false);
            deadlandsVillage = config.get("Allow Villages", "Deadlands", false).getBoolean(false);
            deciduousForestVillage = config.get("Allow Villages", "DeciduousForest", true).getBoolean(false);
            drylandsVillage = config.get("Allow Villages", "Drylands", true).getBoolean(false);
            dunesVillage = config.get("Allow Villages", "Dunes", false).getBoolean(false);
            fenVillage = config.get("Allow Villages", "Fen", false).getBoolean(false);
            fieldVillage = config.get("Allow Villages", "Field", true).getBoolean(false);
            frostForestVillage = config.get("Allow Villages", "FrostForest", true).getBoolean(false);
            fungiForestVillage = config.get("Allow Villages", "FungiForest", false).getBoolean(false);
            gardenVillage = config.get("Allow Villages", "Garden", false).getBoolean(false);
            glacierVillage = config.get("Allow Villages", "Glacier", false).getBoolean(false);
            grasslandVillage = config.get("Allow Villages", "Grassland", true).getBoolean(false);
            groveVillage = config.get("Allow Villages", "Grove", true).getBoolean(false);
            heathlandVillage = config.get("Allow Villages", "Heathland", true).getBoolean(false);
            highlandVillage = config.get("Allow Villages", "Highland", false).getBoolean(false);
            icyHillsVillage = config.get("Allow Villages", "IcyHills", false).getBoolean(false);
            jadeCliffsVillage = config.get("Allow Villages", "JadeCliffs", false).getBoolean(false);
            lushDesertVillage = config.get("Allow Villages", "LushDesert", true).getBoolean(false);
            lushSwampVillage = config.get("Allow Villages", "LushSwamp", true).getBoolean(false);
            mangroveVillage = config.get("Allow Villages", "Mangrove", false).getBoolean(false);
            mapleWoodsVillage = config.get("Allow Villages", "MapleWoods", true).getBoolean(false);
            marshVillage = config.get("Allow Villages", "Marsh", false).getBoolean(false);
            meadowVillage = config.get("Allow Villages", "Meadow", true).getBoolean(false);
            mesaVillage = config.get("Allow Villages", "Mesa", false).getBoolean(false);
            moorVillage = config.get("Allow Villages", "Moor", false).getBoolean(false);
            mountainVillage = config.get("Allow Villages", "Mountain", false).getBoolean(false);
//          mushroomIslandVillage = config.get("Allow Villages", "MushroomIsland", false).getBoolean(false);
            mysticGroveVillage = config.get("Allow Villages", "MysticGrove", false).getBoolean(false);
            oasisVillage = config.get("Allow Villages", "Oasis", true).getBoolean(false);
            ominousWoodsVillage = config.get("Allow Villages", "OminousWoods", false).getBoolean(false);
            orchardVillage = config.get("Allow Villages", "Orchard", true).getBoolean(false);
            originValleyVillage = config.get("Allow Villages", "OriginValley", false).getBoolean(false);
            outbackVillage = config.get("Allow Villages", "Outback", true).getBoolean(false);
            pastureVillage = config.get("Allow Villages", "Pasture", false).getBoolean(false);
			polarVillage = config.get("Allow Villages", "Polar", false).getBoolean(false);
            prairieVillage = config.get("Allow Villages", "Prairie", true).getBoolean(false);
            quagmireVillage = config.get("Allow Villages", "Quagmire", false).getBoolean(false);
            rainforestVillage = config.get("Allow Villages", "Rainforest", false).getBoolean(false);
            redwoodForestVillage = config.get("Allow Villages", "RedwoodForest", true).getBoolean(false);
            sacredSpringsVillage = config.get("Allow Villages", "SacredSprings", false).getBoolean(false);
            savannaVillage = config.get("Allow Villages", "Savanna", true).getBoolean(false);
            scrublandVillage = config.get("Allow Villages", "Scrubland", true).getBoolean(false);
            seasonalForestVillage = config.get("Allow Villages", "SeasonalForest", true).getBoolean(false);
            shieldVillage = config.get("Allow Villages", "Shield", true).getBoolean(false);
            shrublandVillage = config.get("Allow Villages", "Shrubland", true).getBoolean(false);
            snowyWoodsVillage = config.get("Allow Villages", "SnowyWoods", true).getBoolean(false);
            spruceWoodsVillage = config.get("Allow Villages", "SpruceWoods", true).getBoolean(false);
            steppeVillage = config.get("Allow Villages", "Steppe", true).getBoolean(false);
            swampwoodsVillage = config.get("Allow Villages", "Swampwoods", false).getBoolean(false);
            temperateRainforestVillage = config.get("Allow Villages", "TemperateRainforest", true).getBoolean(false);
            thicketVillage = config.get("Allow Villages", "Thicket", true).getBoolean(false);
            tropicalRainforestVillage = config.get("Allow Villages", "TropicalRainforest", true).getBoolean(false);
            tropicsVillage = config.get("Allow Villages", "Tropics", false).getBoolean(false);
            tundraVillage = config.get("Allow Villages", "Tundra", true).getBoolean(false);
            volcanoVillage = config.get("Allow Villages", "Volcano", false).getBoolean(false);
            wastelandVillage = config.get("Allow Villages", "Wasteland", false).getBoolean(false);
            wetlandVillage = config.get("Allow Villages", "Wetland", true).getBoolean(false);
            woodlandVillage = config.get("Allow Villages", "Woodland", true).getBoolean(false);
			
            // Vanilla biomes
            desertVillage = config.get("Allow Villages", "Desert", true).getBoolean(true);
            extremeHillsVillage = config.get("Allow Villages", "ExtremeHills", false).getBoolean(false);
            forestVillage = config.get("Allow Villages", "Forest", true).getBoolean(false);
            jungleVillage = config.get("Allow Villages", "Jungle", true).getBoolean(false);
            plainsVillage = config.get("Allow Villages", "Plains", true).getBoolean(true);
            swamplandVillage = config.get("Allow Villages", "Swampland", true).getBoolean(false);
            taigaVillage = config.get("Allow Villages", "Taiga", true).getBoolean(false);

			// Get Terrain Block ID's
			mudID = config.getTerrainBlock("Terrain Block IDs", "Mud ID", 160, null).getInt();
			driedDirtID = config.getTerrainBlock("Terrain Block IDs", "Dried Dirt ID", 161, null).getInt();
			redRockID = config.getTerrainBlock("Terrain Block IDs", "Red Rock ID", 162, null).getInt();
			ashID = config.getTerrainBlock("Terrain Block IDs", "Ash Block ID", 163, null).getInt();
			ashStoneID = config.getTerrainBlock("Terrain Block IDs", "Ash Stone ID", 164, null).getInt();
			hardIceID = config.getTerrainBlock("Terrain Block IDs", "Hard Ice ID", 165, null).getInt();
			originGrassID = config.getTerrainBlock("Terrain Block IDs", "Origin Grass ID", 166, null).getInt();
			hardSandID = config.getTerrainBlock("Terrain Block IDs", "Hard Sand ID", 167, null).getInt();
			hardDirtID = config.getTerrainBlock("Terrain Block IDs", "Hard Dirt ID", 168, null).getInt();
			holyGrassID = config.getTerrainBlock("Terrain Block IDs", "Holy Grass ID", 169, null).getInt();
			holyDirtID = config.getTerrainBlock("Terrain Block IDs", "Holy Dirt ID", 170, null).getInt();
			holyStoneID = config.getTerrainBlock("Terrain Block IDs", "Skystone ID", 171, null).getInt();
			cragRockID = config.getTerrainBlock("Terrain Block IDs", "Crag Rock ID", 172, null).getInt();

			// Get Crafted Block ID's
			plantsID = config.getBlock("Plant ID", 1920, null).getInt();
			flowersID = config.getBlock("Flower ID", 1921, null).getInt();
			willowID = config.getBlock("Willow ID", 1922, null).getInt();
			
			leaves1ID = config.getBlock("Leaf Block ID 1", 1923, null).getInt();
			leaves2ID = config.getBlock("Leaf Block ID 2", 1924, null).getInt();
			foliageID = config.getBlock("Foliage ID", 1925, null).getInt();
			
			leavesFruitID = config.getBlock("Fruit Leaf Block ID", 1926, null).getInt();
			bambooID = config.getBlock("Bamboo ID", 1927, null).getInt();
			mudBrickBlockID = config.getBlock("Mud Bricks ID", 1928, null).getInt();
			mudBrickStairsID = config.getBlock("Mud Brick Stairs ID", 1929, null).getInt();
			stoneDoubleSlabID = config.getBlock("Stone Double Slab ID", 1930, null).getInt();
			stoneSingleSlabID = config.getBlock("Stone Single Slab ID", 1931, null).getInt();
			
			treeMossID = config.getBlock("Tree Moss ID", 1932, null).getInt();
			
			logs1ID = config.getBlock("Log Block ID 1", 1933, null).getInt();
			logs2ID = config.getBlock("Log Block ID 2", 1934, null).getInt();
			logs3ID = config.getBlock("Log Block ID 3", 1935, null).getInt();
			petalsID = config.getBlock("Petal ID", 1936, null).getInt();
			saplingsID = config.getBlock("Sapling ID", 1937, null).getInt();
			colourizedSaplingsID = config.getBlock("Colourized Sapling ID", 1938, null).getInt();
			redCobbleStairsID = config.getBlock("Red Cobble Stairs ID", 1939, null).getInt();
			redBrickStairsID = config.getBlock("Red Brick Stairs ID", 1940, null).getInt();
			
			promisedLandPortalID = config.getBlock("Promised Land Portal ID", 1941, null).getInt();
			amethystOreID = config.getBlock("Amethyst Ore ID", 1942, null).getInt();
			
			mossID = config.getBlock("Moss ID", 391, null).getInt();

			planksID = config.getBlock("Planks ID", 1947, null).getInt();
			
			woodenDoubleSlab1ID = config.getBlock("Wooden Double Slab 1 ID", 1948, null).getInt();
			woodenSingleSlab1ID = config.getBlock("Wooden Single Slab 1 ID", 1949, null).getInt();
			woodenDoubleSlab2ID = config.getBlock("Wooden Double Slab 2 ID", 1950, null).getInt();
			woodenSingleSlab2ID = config.getBlock("Wooden Single Slab 2 ID", 1951, null).getInt();
			
			acaciaStairsID = config.getBlock("Acacia Stairs ID", 1952, null).getInt();
			cherryStairsID = config.getBlock("Cherry Stairs ID", 1953, null).getInt();
			darkStairsID = config.getBlock("Dark Stairs ID", 1954, null).getInt();
			firStairsID = config.getBlock("Fir Stairs ID", 1955, null).getInt();
			holyStairsID = config.getBlock("Holy Stairs ID", 1956, null).getInt();
			magicStairsID = config.getBlock("Magic Stairs ID", 1957, null).getInt();
			mangroveStairsID = config.getBlock("Mangrove Stairs ID", 1958, null).getInt();
			palmStairsID = config.getBlock("Palm Stairs ID", 1959, null).getInt();
			redwoodStairsID = config.getBlock("Redwood Stairs ID", 1960, null).getInt();
			willowStairsID = config.getBlock("Willow Stairs ID", 1961, null).getInt();
			
			colourizedLeavesID = config.getBlock("Colourized Leaves ID", 1962, null).getInt();
			
			crystalID = config.getBlock("Crystal ID", 1963, null).getInt();
			cloudID = config.getBlock("Cloud ID", 1964, null).getInt();
			
			holyCobbleStairsID = config.getBlock("Skystone Cobble Stairs ID", 1965, null).getInt();
			holyBrickStairsID = config.getBlock("Skystone Brick Stairs ID", 1966, null).getInt();
			
			mushroomsID = config.getBlock("Mushroom ID", 1967, null).getInt();

			// Get Item ID's
			shroomPowderID = config.getItem("Shroom Powder ID", 21001, null).getInt();
			sunflowerSeedsID = config.getItem("Sunflower Seeds ID", 21002, null).getInt();
			berriesID = config.getItem("Berries ID", 21003, null).getInt();

			ancientStaffID = config.getItem("Ancient Staff ID", 21006).getInt();
			enderporterID = config.getItem("Enderporter ID", 21007).getInt();

			miscItemsID = config.getItem("Misc Items ID", 21010).getInt();
			mudballID = config.getItem("Mud Ball ID", 21011).getInt();
			dartBlowerID = config.getItem("Dart Blower ID", 21012).getInt();
			dartID = config.getItem("Dart ID", 21013).getInt();
			
			bopDiscID = config.getItem("Traversia Music Disc ID", 21019, null).getInt();
			bopDiscMudID = config.getItem("Muddy Music Disc ID", 21020, null).getInt();
			
			swordMudID = config.getItem("Muddy Sword ID", 21060, null).getInt();
			shovelMudID = config.getItem("Muddy Shovel ID", 21061, null).getInt();
			pickaxeMudID = config.getItem("Muddy Pickaxe ID", 21062, null).getInt();
			axeMudID = config.getItem("Muddy Axe ID", 21063, null).getInt();
			hoeMudID = config.getItem("Muddy Hoe ID", 21064, null).getInt();
			helmetMudID = config.getItem("Muddy Helmet ID", 21065, null).getInt();
			chestplateMudID = config.getItem("Muddy Chestplate ID", 21066, null).getInt();
			leggingsMudID = config.getItem("Muddy Leggings ID", 21067, null).getInt();
			bootsMudID = config.getItem("Muddy Boots ID", 21068, null).getInt();
			
			swordAmethystID = config.getItem("Amethyst Sword ID", 21069, null).getInt();
			shovelAmethystID = config.getItem("Amethyst Shovel ID", 21070, null).getInt();
			pickaxeAmethystID = config.getItem("Amethyst Pickaxe ID", 21071, null).getInt();
			axeAmethystID = config.getItem("Amethyst Axe ID", 21072, null).getInt();
			hoeAmethystID = config.getItem("Amethyst Hoe ID", 21073, null).getInt();
			helmetAmethystID = config.getItem("Amethyst Helmet ID", 21074, null).getInt();
			chestplateAmethystID = config.getItem("Amethyst Chestplate ID", 21075, null).getInt();
			leggingsAmethystID = config.getItem("Amethyst Leggings ID", 21076, null).getInt();
			bootsAmethystID = config.getItem("Amethyst Boots ID", 21077, null).getInt();
			
			flowerBandID = config.getItem("Flower Band ID", 21078, null).getInt();

			//Mob IDs
			jungleSpiderID = config.get("Mob IDs", "Jungle Spider ID", 101, null).getInt();
			rosesterID = config.get("Mob IDs", "Rosester ID", 102, null).getInt();
			globID = config.get("Mob IDs", "Glob ID", 106, null).getInt();
			
			//Projectile IDs
			entityMudballID = config.get("Entity IDs", "Mudball ID", 103, null).getInt();;
			entityDartID = config.get("Entity IDs", "Dart ID", 104, null).getInt();;
			entityPoisonDartID = config.get("Entity IDs", "Poison Dart ID", 105, null).getInt();;

			System.out.println("Generating Biome ID's");
			
			//23-79 ExtraBiomesXL
			
			//80-169 Better World Generation 4
			
			promisedLandForestID = config.get("Biome IDs", "Wonderous Woods (Promised Land) ID", 170).getInt();
			promisedLandPlainsID = config.get("Biome IDs", "Majestic Meadow (Promised Land) ID", 171).getInt();
			promisedLandSwampID = config.get("Biome IDs", "Blessed Bog (Promised Land) ID", 172).getInt();
			
			alpsID = config.get("Biome IDs", "Alps ID", 173).getInt();
			arcticID = config.get("Biome IDs", "Arctic ID", 174).getInt();
			badlandsID = config.get("Biome IDs", "Badlands ID", 175).getInt();
			bambooForestID = config.get("Biome IDs", "Bamboo Forest ID", 176).getInt();
			bayouID = config.get("Biome IDs", "Bayou ID", 177).getInt();
			birchForestID = config.get("Biome IDs", "Birch Forest ID", 178).getInt();
			bogID = config.get("Biome IDs", "Bog ID", 179).getInt();
			borealForestID = config.get("Biome IDs", "Boreal Forest ID", 180).getInt();
			canyonID = config.get("Biome IDs", "Canyon ID", 181).getInt();
			chaparralID = config.get("Biome IDs", "Chaparral ID", 182).getInt();
			cherryBlossomGroveID = config.get("Biome IDs", "Cherry Blossom Grove ID", 183).getInt();
			coniferousForestID = config.get("Biome IDs", "Coniferous Forest ID", 184).getInt();
			cragID = config.get("Biome IDs", "Crag ID", 185).getInt();
			deadForestID = config.get("Biome IDs", "Dead Forest ID", 186).getInt();
			deadSwampID = config.get("Biome IDs", "Dead Swamp ID", 187).getInt();
			deadlandsID = config.get("Biome IDs", "Deadlands ID", 188).getInt();
			deciduousForestID = config.get("Biome IDs", "Deciduous Forest ID", 189).getInt();
			drylandsID = config.get("Biome IDs", "Drylands ID", 190).getInt();
			dunesID = config.get("Biome IDs", "Dunes ID", 191).getInt();
			fenID = config.get("Biome IDs", "Fen ID", 192).getInt();
			fieldID = config.get("Biome IDs", "Field ID", 193).getInt();
			frostForestID = config.get("Biome IDs", "Frost Forest ID", 194).getInt();
			fungiForestID = config.get("Biome IDs", "Fungi Forest ID", 195).getInt();
			gardenID = config.get("Biome IDs", "Garden ID", 196).getInt();
			glacierID = config.get("Biome IDs", "Glacier ID", 197).getInt();
			grasslandID = config.get("Biome IDs", "Grassland ID", 198).getInt();
			groveID = config.get("Biome IDs", "Grove ID", 199).getInt();
			heathlandID = config.get("Biome IDs", "Heathland ID", 200).getInt();
			highlandID = config.get("Biome IDs", "Highland ID", 201).getInt();
			icyHillsID = config.get("Biome IDs", "Icy Hills ID", 202).getInt();
			jadeCliffsID = config.get("Biome IDs", "Jade Cliffs ID", 203).getInt();
			lushDesertID = config.get("Biome IDs", "Lush Desert ID", 204).getInt();
			lushSwampID = config.get("Biome IDs", "Lush Swamp ID", 205).getInt();
			mangroveID = config.get("Biome IDs", "Mangrove ID", 206).getInt();
			
			//207-209 left for Mo Creatures
			
			mapleWoodsID = config.get("Biome IDs", "Maple Woods ID", 210).getInt();
			marshID = config.get("Biome IDs", "Marsh ID", 211).getInt();
			meadowID = config.get("Biome IDs", "Meadow ID", 212).getInt();
			mesaID = config.get("Biome IDs", "Mesa ID", 213).getInt();
			moorID = config.get("Biome IDs", "Moor ID", 214).getInt();
			mountainID = config.get("Biome IDs", "Mountain ID", 215).getInt();
			mysticGroveID = config.get("Biome IDs", "Mystic Grove ID", 216).getInt();
			oasisID = config.get("Biome IDs", "Oasis ID", 217).getInt();
			ominousWoodsID = config.get("Biome IDs", "Ominous Woods ID", 218).getInt();
			orchardID = config.get("Biome IDs", "Orchard ID", 219).getInt();
			originValleyID = config.get("Biome IDs", "Origin Valley ID", 220).getInt();
			outbackID = config.get("Biome IDs", "Outback ID", 221).getInt();
			pastureID = config.get("Biome IDs", "Pasture ID", 222).getInt();
			polarID = config.get("Biome IDs", "Polar ID", 223).getInt();
			prairieID = config.get("Biome IDs", "Prairie ID", 224).getInt();
			quagmireID = config.get("Biome IDs", "Quagmire ID", 225).getInt();
			rainforestID = config.get("Biome IDs", "Rainforest ID", 226).getInt();
			redwoodForestID = config.get("Biome IDs", "Redwood Forest ID", 227).getInt();
			sacredSpringsID = config.get("Biome IDs", "Sacred Springs ID", 228).getInt();
			savannaID = config.get("Biome IDs", "Savanna ID", 229).getInt();
			scrublandID = config.get("Biome IDs", "Scrubland ID", 230).getInt();
			seasonalForestID = config.get("Biome IDs", "Seasonal Forest ID", 231).getInt();
			shieldID = config.get("Biome IDs", "Shield ID", 232).getInt();
			shoreID = config.get("Biome IDs", "Shore ID", 233).getInt();
			shrublandID = config.get("Biome IDs", "Shrubland ID", 234).getInt();
			snowyWoodsID = config.get("Biome IDs", "Snowy Woods ID", 235).getInt();
			spruceWoodsID = config.get("Biome IDs", "Spruce Woods ID", 236).getInt();
			steppeID = config.get("Biome IDs", "Steppe ID", 237).getInt();
			swampwoodsID = config.get("Biome IDs", "Swampwoods ID", 238).getInt();
			temperateRainforestID = config.get("Biome IDs", "Temperate Rainforest ID", 239).getInt();
			thicketID = config.get("Biome IDs", "Thicket ID", 240).getInt();
			tropicalRainforestID = config.get("Biome IDs", "Tropical Rainforest ID", 241).getInt();
			tropicsID = config.get("Biome IDs", "Tropics ID", 242).getInt();
			tundraID = config.get("Biome IDs", "Tundra ID", 243).getInt();
			volcanoID = config.get("Biome IDs", "Volcano ID", 244).getInt();
			wastelandID = config.get("Biome IDs", "Wasteland ID", 245).getInt();
			wetlandID = config.get("Biome IDs", "Wetland ID", 246).getInt();
			woodlandID = config.get("Biome IDs", "Woodland ID", 247).getInt();

			plainsNewID = config.get("Biome IDs", "Plains (New) ID", 248).getInt();
			desertNewID = config.get("Biome IDs", "Desert (New) ID", 249).getInt();
			forestNewID = config.get("Biome IDs", "Forest (New) ID", 250).getInt();
			taigaNewID = config.get("Biome IDs", "Taiga (New) ID", 251).getInt();
			swamplandNewID = config.get("Biome IDs", "Swampland (New) ID", 252).getInt();
			extremeHillsNewID = config.get("Biome IDs", "Extreme Hills (New) ID", 253).getInt();
			jungleNewID = config.get("Biome IDs", "Jungle (New) ID", 254).getInt();

			System.out.println("[BiomesOPlenty] Generated Config!");
		}
		catch (Exception e) 
		{
			FMLLog.log(Level.SEVERE, e, "Biomes O Plenty has had a problem loading its configuration");
		}
		finally 
		{
			if (config.hasChanged()) config.save();
		}
	}
}
