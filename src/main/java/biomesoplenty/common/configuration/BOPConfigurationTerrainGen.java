package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationTerrainGen
{
	public static Configuration config;
	
	public static int biomeSize;
	public static boolean addToDefault;
	public static boolean vanillaEnhanced;
	public static boolean netherOverride;
	public static boolean stoneFormationGen;
	public static boolean pumpkinGen;
	public static boolean poisonIvyGen;
	public static boolean thornGen;
	public static boolean springWaterGen;
	public static boolean poisonWaterGen;
	public static boolean quicksandGen;
	public static boolean exposedStone;
	public static boolean witherWartGen;
	public static boolean burningBlossomGen;
	
	public static int villageDistance;
	public static int landmassPercentage;
	
	// Village biomes
	public static boolean alpsVillage;
	public static boolean arcticVillage;
	public static boolean autumnHillsVillage;
	public static boolean badlandsVillage;
	public static boolean bambooForestVillage;
	public static boolean bayouVillage;
	public static boolean birchForestVillage;
	public static boolean bogVillage;
	public static boolean borealForestVillage;
	public static boolean brushlandVillage;
	public static boolean canyonVillage;
	public static boolean chaparralVillage;
	public static boolean cherryBlossomGroveVillage;
	public static boolean coniferousForestVillage;
	public static boolean coniferousForestSnowVillage;
	public static boolean cragVillage;
	public static boolean deadForestVillage;
	public static boolean deadForestSnowVillage;
	public static boolean deadSwampVillage;
	public static boolean deadlandsVillage;
	public static boolean deciduousForestVillage;
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
	public static boolean hotSpringsVillage;
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
	public static boolean mysticGroveVillage;
	public static boolean oasisVillage;
	public static boolean ominousWoodsVillage;
	public static boolean orchardVillage;
	public static boolean originValleyVillage;
	public static boolean outbackVillage;
	public static boolean overgrownGreensVillage;
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
	public static boolean silkgladesVillage;
	public static boolean sludgepitVillage;
	public static boolean spruceWoodsVillage;
	public static boolean steppeVillage;
	public static boolean temperateRainforestVillage;
	public static boolean thicketVillage;
	public static boolean timberVillage;
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
			
			biomeSize = config.get("Biomes O\' Plenty World Type Settings", "Biome Size", 4, "Default World Type has 4. Large Biomes World Type has 6.").getInt(4);
			addToDefault = config.get("Biome Settings", "Add Biomes To Default World", false).getBoolean(true);
			vanillaEnhanced = config.get("Biome Settings", "Enhanced Vanilla Biomes", true).getBoolean(false);
			netherOverride = config.get("Dimension Settings", "Enable Nether Override", true).getBoolean(true);
			pumpkinGen = config.get("Decoration Settings", "Generate Pumpkins Without Faces", true).getBoolean(true);
			stoneFormationGen = config.get("Decoration Settings", "Generate Stone Formations", true, "Stalagmites, stalactites, etc.").getBoolean(true);
			poisonIvyGen = config.get("Decoration Settings", "Generate Poison Ivy", true).getBoolean(true);
			thornGen = config.get("Decoration Settings", "Generate Thorns", true).getBoolean(true);
			springWaterGen = config.get("Decoration Settings", "Generate Hot Springs", true).getBoolean(true);
			poisonWaterGen = config.get("Decoration Settings", "Generate Poison Pools", true).getBoolean(true);
			quicksandGen = config.get("Decoration Settings", "Generate Quicksand", true).getBoolean(true);
			witherWartGen = config.get("Decoration Settings", "Generate Wither Wart", true).getBoolean(true);
			burningBlossomGen = config.get("Decoration Settings", "Generate Burning Blossoms", true).getBoolean(true);
			exposedStone = config.get("Decoration Settings", "Generate Sections Of Exposed Stone", true).getBoolean(true);
			
			villageDistance = config.get("Biomes O\' Plenty World Type Settings", "Distance between villages", 32, "In Vanilla it is set to 32").getInt();
			if (villageDistance < 8) 
			{
				villageDistance = 8;
			}
			
			landmassPercentage = config.get("Biomes O\' Plenty World Type Settings", "Landmass Percentage", 10, "In Vanilla it is set to 10. Takes values from 0 to 100.").getInt();

			// Biomes with villages
			alpsVillage = config.get("Allow Villages", "Alps", false).getBoolean(false);
			arcticVillage = config.get("Allow Villages", "Arctic", true).getBoolean(false);
			autumnHillsVillage = config.get("Allow Villages", "AutumnHills", false).getBoolean(false);
			badlandsVillage = config.get("Allow Villages", "Badlands", false).getBoolean(false);
			bambooForestVillage = config.get("Allow Villages", "BambooForest", false).getBoolean(false);
			bayouVillage = config.get("Allow Villages", "Bayou", false).getBoolean(false);
			birchForestVillage = config.get("Allow Villages", "BirchForest", true).getBoolean(false);
			bogVillage = config.get("Allow Villages", "Bog", false).getBoolean(false);
			borealForestVillage = config.get("Allow Villages", "BorealForest", false).getBoolean(false);
			brushlandVillage = config.get("Allow Villages", "Brushland", true).getBoolean(false);
			canyonVillage = config.get("Allow Villages", "Canyon", false).getBoolean(false);
			chaparralVillage = config.get("Allow Villages", "Chaparral", true).getBoolean(false);
			cherryBlossomGroveVillage = config.get("Allow Villages", "CherryBlossomGrove", false).getBoolean(false);
			coniferousForestVillage = config.get("Allow Villages", "ConiferousForest", true).getBoolean(false);
			coniferousForestSnowVillage = config.get("Allow Villages", "ConiferousForestSnow", true).getBoolean(false);
			//cragVillage = config.get("Allow Villages", "Crag", false).getBoolean(false);
			deadForestVillage = config.get("Allow Villages", "DeadForest", false).getBoolean(false);
			deadForestSnowVillage = config.get("Allow Villages", "DeadForestSnow", false).getBoolean(false);
			deadSwampVillage = config.get("Allow Villages", "DeadSwamp", false).getBoolean(false);
			//deadlandsVillage = config.get("Allow Villages", "Deadlands", false).getBoolean(false);
			deciduousForestVillage = config.get("Allow Villages", "DeciduousForest", true).getBoolean(false);
			dunesVillage = config.get("Allow Villages", "Dunes", false).getBoolean(false);
			fenVillage = config.get("Allow Villages", "Fen", false).getBoolean(false);
			fieldVillage = config.get("Allow Villages", "Field", true).getBoolean(false);
			frostForestVillage = config.get("Allow Villages", "FrostForest", true).getBoolean(false);
			//fungiForestVillage = config.get("Allow Villages", "FungiForest", false).getBoolean(false);
			//gardenVillage = config.get("Allow Villages", "Garden", false).getBoolean(false);
			glacierVillage = config.get("Allow Villages", "Glacier", false).getBoolean(false);
			grasslandVillage = config.get("Allow Villages", "Grassland", true).getBoolean(false);
			groveVillage = config.get("Allow Villages", "Grove", true).getBoolean(false);
			heathlandVillage = config.get("Allow Villages", "Heathland", true).getBoolean(false);
			highlandVillage = config.get("Allow Villages", "Highland", false).getBoolean(false);
			hotSpringsVillage = config.get("Allow Villages", "HotSprings", false).getBoolean(false);
			//icyHillsVillage = config.get("Allow Villages", "IcyHills", false).getBoolean(false);
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
			//mysticGroveVillage = config.get("Allow Villages", "MysticGrove", false).getBoolean(false);
			oasisVillage = config.get("Allow Villages", "Oasis", false).getBoolean(false);
			//ominousWoodsVillage = config.get("Allow Villages", "OminousWoods", false).getBoolean(false);
			orchardVillage = config.get("Allow Villages", "Orchard", false).getBoolean(false);
			//originValleyVillage = config.get("Allow Villages", "OriginValley", false).getBoolean(false);
			outbackVillage = config.get("Allow Villages", "Outback", true).getBoolean(false);
			overgrownGreensVillage = config.get("Allow Villages", "OvergrownGreens", true).getBoolean(false);
			pastureVillage = config.get("Allow Villages", "Pasture", false).getBoolean(false);
			polarVillage = config.get("Allow Villages", "Polar", false).getBoolean(false);
			prairieVillage = config.get("Allow Villages", "Prairie", true).getBoolean(false);
			quagmireVillage = config.get("Allow Villages", "Quagmire", false).getBoolean(false);
			rainforestVillage = config.get("Allow Villages", "Rainforest", false).getBoolean(false);
			redwoodForestVillage = config.get("Allow Villages", "RedwoodForest", false).getBoolean(false);
			//sacredSpringsVillage = config.get("Allow Villages", "SacredSprings", false).getBoolean(false);
			savannaVillage = config.get("Allow Villages", "Savanna", true).getBoolean(false);
			scrublandVillage = config.get("Allow Villages", "Scrubland", true).getBoolean(false);
			seasonalForestVillage = config.get("Allow Villages", "SeasonalForest", false).getBoolean(false);
			shieldVillage = config.get("Allow Villages", "Shield", false).getBoolean(false);
			shrublandVillage = config.get("Allow Villages", "Shrubland", true).getBoolean(false);
			//silkgladesVillage = config.get("Allow Villages", "Silkgladess", false).getBoolean(false);
			sludgepitVillage = config.get("Allow Villages", "Sludgepit", false).getBoolean(false);
			spruceWoodsVillage = config.get("Allow Villages", "SpruceWoods", true).getBoolean(false);
			steppeVillage = config.get("Allow Villages", "Steppe", true).getBoolean(false);
			temperateRainforestVillage = config.get("Allow Villages", "TemperateRainforest", false).getBoolean(false);
			thicketVillage = config.get("Allow Villages", "Thicket", false).getBoolean(false);
			timberVillage = config.get("Allow Villages", "Timber", true).getBoolean(false);
			tropicalRainforestVillage = config.get("Allow Villages", "TropicalRainforest", true).getBoolean(false);
			tropicsVillage = config.get("Allow Villages", "Tropics", false).getBoolean(false);
			tundraVillage = config.get("Allow Villages", "Tundra", true).getBoolean(false);
			volcanoVillage = config.get("Allow Villages", "Volcano", false).getBoolean(false);
			//wastelandVillage = config.get("Allow Villages", "Wasteland", false).getBoolean(false);
			wetlandVillage = config.get("Allow Villages", "Wetland", true).getBoolean(false);
			woodlandVillage = config.get("Allow Villages", "Woodland", true).getBoolean(false);

			// Vanilla biomes
			desertVillage = config.get("Allow Villages", "Desert", true).getBoolean(true);
			extremeHillsVillage = config.get("Allow Villages", "ExtremeHills", false).getBoolean(false);
			forestVillage = config.get("Allow Villages", "Forest", false).getBoolean(false);
			jungleVillage = config.get("Allow Villages", "Jungle", false).getBoolean(false);
			plainsVillage = config.get("Allow Villages", "Plains", true).getBoolean(true);
			swamplandVillage = config.get("Allow Villages", "Swampland", false).getBoolean(false);
			taigaVillage = config.get("Allow Villages", "Taiga", false).getBoolean(false);
			
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Terrain Gen Config!");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Biomes O Plenty has had a problem loading its configuration");
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}
