package biomesoplenty.configuration.configfile;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationBiomeGen
{
	public static Configuration config;

	public static boolean alpsGen;
	public static boolean arcticGen;
	public static boolean autumnHillsGen;
	public static boolean badlandsGen;
	public static boolean bambooForestGen;
	public static boolean bayouGen;
	public static boolean birchForestGen;
	public static boolean bogGen;
	public static boolean borealForestGen;
	public static boolean brushlandGen;
	public static boolean canyonGen;
	public static boolean chaparralGen;
	public static boolean cherryBlossomGroveGen;
	public static boolean coniferousForestGen;
	public static boolean coniferousForestSnowGen;
	public static boolean cragGen;
	public static boolean deadForestGen;
	public static boolean deadForestSnowGen;
	public static boolean deadSwampGen;
	public static boolean deadlandsGen;
	public static boolean deciduousForestGen;
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
	public static boolean hotSpringsGen;
	public static boolean icyHillsGen;
	public static boolean jadeCliffsGen;
	public static boolean lavenderFieldsGen;
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
	public static boolean oceanGen;
	public static boolean ominousWoodsGen;
	public static boolean orchardGen;
	public static boolean originValleyGen;
	public static boolean outbackGen;
	public static boolean overgrownGreensGen;
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
	public static boolean silkgladesGen;
	public static boolean sludgepitGen;
	public static boolean spruceWoodsGen;
	public static boolean steppeGen;
	public static boolean temperateRainforestGen;
	public static boolean thicketGen;
	public static boolean timberGen;
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

	// Nether biomes
	public static boolean undergardenGen;
	public static boolean corruptedSandsGen;
	public static boolean phantasmagoricInfernoGen;
	public static boolean boneyardGen;
	public static boolean bloodyHeapGen;
	public static boolean theHiveGen;

	// Beach variations
	public static boolean gravelBeachGen;
	public static boolean overgrownBeachGen;
	
	// Ocean biomes
	public static boolean coralReefGen;
	public static boolean kelpForestGen;
	public static boolean oceanicAbyssGen;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			//Biome generation
			alpsGen = config.get("Biomes To Generate", "Alps", true).getBoolean(false);
			arcticGen = config.get("Biomes To Generate", "Arctic", true).getBoolean(false);
			autumnHillsGen = config.get("Biomes To Generate", "AutumnHills", false).getBoolean(false);
			badlandsGen = config.get("Biomes To Generate", "Badlands", true).getBoolean(false);
			bambooForestGen = config.get("Biomes To Generate", "BambooForest", true).getBoolean(false);
			bayouGen = config.get("Biomes To Generate", "Bayou", true).getBoolean(false);
			birchForestGen = config.get("Biomes To Generate", "BirchForest", true).getBoolean(false);
			bogGen = config.get("Biomes To Generate", "Bog", true).getBoolean(false);
			borealForestGen = config.get("Biomes To Generate", "BorealForest", true).getBoolean(false);
			brushlandGen = config.get("Biomes To Generate", "Brushland", true).getBoolean(false);
			canyonGen = config.get("Biomes To Generate", "Canyon", true).getBoolean(false);
			chaparralGen = config.get("Biomes To Generate", "Chaparral", true).getBoolean(false);
			cherryBlossomGroveGen = config.get("Biomes To Generate", "CherryBlossomGrove", true).getBoolean(false);
			coniferousForestGen = config.get("Biomes To Generate", "ConiferousForest", true).getBoolean(false);
			coniferousForestSnowGen = config.get("Biomes To Generate", "ConiferousForestSnow", true).getBoolean(false);
			cragGen = config.get("Biomes To Generate", "Crag", true).getBoolean(false);
			deadForestGen = config.get("Biomes To Generate", "DeadForest", true).getBoolean(false);
			deadForestSnowGen = config.get("Biomes To Generate", "DeadForestSnow", true).getBoolean(false);
			deadSwampGen = config.get("Biomes To Generate", "DeadSwamp", true).getBoolean(false);
			deadlandsGen = config.get("Biomes To Generate", "Deadlands", true).getBoolean(false);
			deciduousForestGen = config.get("Biomes To Generate", "DeciduousForest", true).getBoolean(false);
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
			hotSpringsGen = config.get("Biomes To Generate", "HotSprings", true).getBoolean(false);
			icyHillsGen = config.get("Biomes To Generate", "IcyHills", true).getBoolean(false);
			jadeCliffsGen = config.get("Biomes To Generate", "JadeCliffs", true).getBoolean(false);
			lavenderFieldsGen = config.get("Biomes To Generate", "LavenderFields", false).getBoolean(false);
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
			overgrownGreensGen = config.get("Biomes To Generate", "OvergrownGreens", false).getBoolean(false);
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
			silkgladesGen = config.get("Biomes To Generate", "Silkglades", false).getBoolean(false);
			sludgepitGen = config.get("Biomes To Generate", "Sludgepit", true).getBoolean(false);
			spruceWoodsGen = config.get("Biomes To Generate", "SpruceWoods", true).getBoolean(false);
			steppeGen = config.get("Biomes To Generate", "Steppe", true).getBoolean(false);
			temperateRainforestGen = config.get("Biomes To Generate", "TemperateRainforest", true).getBoolean(false);
			thicketGen = config.get("Biomes To Generate", "Thicket", true).getBoolean(false);
			timberGen = config.get("Biomes To Generate", "Timber", true).getBoolean(false);
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
			oceanGen = config.get("Vanilla Biomes To Generate", "Ocean", true).getBoolean(false);
			plainsGen = config.get("Vanilla Biomes To Generate", "Plains", true).getBoolean(true);
			swamplandGen = config.get("Vanilla Biomes To Generate", "Swampland", true).getBoolean(true);
			taigaGen = config.get("Vanilla Biomes To Generate", "Taiga", true).getBoolean(true);

			// Nether biomes
			undergardenGen = config.get("Nether Biomes To Generate", "Undergarden", true).getBoolean(true);
			corruptedSandsGen = config.get("Nether Biomes To Generate", "CorruptedSands", true).getBoolean(true);
			phantasmagoricInfernoGen = config.get("Nether Biomes To Generate", "PhantasmagoricInferno", true).getBoolean(true);
			boneyardGen = config.get("Nether Biomes To Generate", "Boneyard", true).getBoolean(true);
			bloodyHeapGen = config.get("Nether Biomes To Generate", "BloodyHeap", true).getBoolean(true);
			theHiveGen = config.get("Nether Biomes To Generate", "TheHive", true).getBoolean(true);

			// Beach variations
			gravelBeachGen = config.get("Beach Variations To Generate", "Gravel Beach", true).getBoolean(true);
			overgrownBeachGen = config.get("Beach Variations To Generate", "Overgrown Beach", false).getBoolean(true);
			
			// Ocean biomes
			coralReefGen = config.get("Ocean Biomes To Generate", "Coral Reef", true).getBoolean(false);
			kelpForestGen = config.get("Ocean Biomes To Generate", "Kelp Forest", true).getBoolean(false);
			oceanicAbyssGen = config.get("Ocean Biomes To Generate", "Oceanic Abyss", true).getBoolean(false);

			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Biome Gen Config!");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.SEVERE, e, "Biomes O Plenty has had a problem loading its configuration");
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}