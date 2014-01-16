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
	//public static boolean addToDefault;
	public static boolean vanillaEnhanced;
	public static boolean netherOverride;
	
	public static int landmassPercentage;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			biomeSize = config.get("Biomes O\' Plenty World Type Settings", "Biome Size", 4, "Default World Type has 4. Large Biomes World Type has 6.").getInt(4);
			//addToDefault = config.get("Biome Settings", "Add Biomes To Default World", false).getBoolean(true);
			vanillaEnhanced = config.get("Biome Settings", "Enhanced Vanilla Biomes", true).getBoolean(false);
			netherOverride = config.get("Dimension Settings", "Enable Nether Override", true).getBoolean(true);
			
			landmassPercentage = config.get("Biomes O\' Plenty World Type Settings", "Landmass Percentage", 10, "In Vanilla it is set to 10. Takes values from 0 to 100.").getInt();
			
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
