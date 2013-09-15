package biomesoplenty.configuration.configfile;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationTerrainGen
{
	public static Configuration config;
	
	public static int biomeSize;
	public static boolean addToDefault;
	public static boolean vanillaEnhanced;
	public static boolean netherOverride;
	public static boolean pumpkinGen;
	public static boolean poisonIvyGen;
	public static boolean thornGen;
	public static boolean springWaterGen;
	public static boolean poisonWaterGen;
	public static boolean quicksandGen;
	public static boolean exposedStone;
	
	public static int villageDistance;
	
	// Village biomes
	public static boolean arcticVillage;
	public static boolean brushlandVillage;
	public static boolean chaparralVillage;
	public static boolean coniferousForestVillage;
	public static boolean coniferousForestSnowVillage;
	public static boolean deciduousForestVillage;
	public static boolean frostForestVillage;
	public static boolean fieldVillage;
	public static boolean grasslandVillage;
	public static boolean groveVillage;
	public static boolean heathlandVillage;
	public static boolean lushDesertVillage;
	public static boolean lushSwampVillage;
	public static boolean mapleWoodsVillage;
	public static boolean meadowVillage;
	public static boolean outbackVillage;
	public static boolean overgrownGreensVillage;
	public static boolean prairieVillage;
	public static boolean savannaVillage;
	public static boolean scrublandVillage;
	public static boolean shrublandVillage;
	public static boolean spruceWoodsVillage;
	public static boolean steppeVillage;
	public static boolean timberVillage;
	public static boolean tropicalRainforestVillage;
	public static boolean tundraVillage;
	public static boolean wetlandVillage;
	public static boolean woodlandVillage;
	public static boolean plainsVillage;
	public static boolean desertVillage;
	public static boolean forestVillage;

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
			poisonIvyGen = config.get("Decoration Settings", "Generate Poison Ivy", true).getBoolean(true);
			thornGen = config.get("Decoration Settings", "Generate Thorns", true).getBoolean(true);
			springWaterGen = config.get("Decoration Settings", "Generate Hot Springs", true).getBoolean(true);
			poisonWaterGen = config.get("Decoration Settings", "Generate Poison Pools", true).getBoolean(true);
			quicksandGen = config.get("Decoration Settings", "Generate Quicksand", true).getBoolean(true);
			exposedStone = config.get("Decoration Settings", "Generate Sections Of Exposed Stone", true).getBoolean(true);
			
			villageDistance = config.get("Biomes O\' Plenty World Type Settings", "Distance between villages", 32, "In Vanilla it is set to 32").getInt();
			if (villageDistance < 8) 
			{
				villageDistance = 8;
			}

			// Biomes with villages
			arcticVillage = config.get("Allow Villages", "Arctic", true).getBoolean(false);
			brushlandVillage = config.get("Allow Villages", "Brushland", true).getBoolean(false);
			chaparralVillage = config.get("Allow Villages", "Chaparral", true).getBoolean(false);
			coniferousForestVillage = config.get("Allow Villages", "ConiferousForest", true).getBoolean(false);
			coniferousForestSnowVillage = config.get("Allow Villages", "SnowyConiferousForest", true).getBoolean(false);
			deciduousForestVillage = config.get("Allow Villages", "DeciduousForest", true).getBoolean(false);
			frostForestVillage = config.get("Allow Villages", "FrostForest", true).getBoolean(false);
			fieldVillage = config.get("Allow Villages", "Field", true).getBoolean(false);
			grasslandVillage = config.get("Allow Villages", "Grassland", true).getBoolean(false);
			groveVillage = config.get("Allow Villages", "Grove", true).getBoolean(false);
			heathlandVillage = config.get("Allow Villages", "Heathland", true).getBoolean(false);
			lushSwampVillage = config.get("Allow Villages", "LushSwamp", true).getBoolean(false);
			mapleWoodsVillage = config.get("Allow Villages", "MapleWoods", true).getBoolean(false);
			meadowVillage = config.get("Allow Villages", "Meadow", true).getBoolean(false);
			outbackVillage = config.get("Allow Villages", "Outback", true).getBoolean(false);
			overgrownGreensVillage = config.get("Allow Villages", "OvergrownGreens", true).getBoolean(false);
			prairieVillage = config.get("Allow Villages", "Prairie", true).getBoolean(false);
			savannaVillage = config.get("Allow Villages", "Savanna", true).getBoolean(false);
			scrublandVillage = config.get("Allow Villages", "Scrubland", true).getBoolean(false);
			shrublandVillage = config.get("Allow Villages", "Shrubland", true).getBoolean(false);
			spruceWoodsVillage = config.get("Allow Villages", "SpruceWoods", true).getBoolean(false);
			steppeVillage = config.get("Allow Villages", "Steppe", true).getBoolean(false);
			timberVillage = config.get("Allow Villages", "Timber", true).getBoolean(false);
			tropicalRainforestVillage = config.get("Allow Villages", "TropicalRainforest", true).getBoolean(false);
			tundraVillage = config.get("Allow Villages", "Tundra", true).getBoolean(false);
			wetlandVillage = config.get("Allow Villages", "Wetland", true).getBoolean(false);
			woodlandVillage = config.get("Allow Villages", "Woodland", true).getBoolean(false);

			// Vanilla biomes
			desertVillage = config.get("Allow Villages", "Desert", true).getBoolean(true);
			forestVillage = config.get("Allow Villages", "Forest", true).getBoolean(true);
			plainsVillage = config.get("Allow Villages", "Plains", true).getBoolean(true);
			
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Terrain Gen Config!");
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
