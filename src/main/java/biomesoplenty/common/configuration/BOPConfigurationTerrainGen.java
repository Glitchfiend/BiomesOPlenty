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
	//public static boolean vanillaEnhanced;
	//public static boolean netherOverride;
	
	public static boolean oceanFiller;
	public static int landmassPercentage;

	public static boolean generateGems;	

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			biomeSize = config.get("Biomes O\' Plenty World Type Settings", "Biome Size", 4, "Default World Type has 4. Large Biomes World Type has 6.").getInt(4);
			//addToDefault = config.get("Biome Settings", "Add Biomes To Default World", false).getBoolean(true);
			//vanillaEnhanced = config.get("Biome Settings", "Enhanced Vanilla Biomes", true).getBoolean(false);
			//netherOverride = config.get("Dimension Settings", "Enable Nether Override", true).getBoolean(true);
			
			oceanFiller = config.get("Biomes O\' Plenty World Type Settings", "OceanFiller", true, "Fills the ocean with land biomes if there is an excessive amount. This must be disabled to use the landmass percentage").getBoolean();
			landmassPercentage = config.get("Biomes O\' Plenty World Type Settings", "Landmass Percentage", 10, "Requires ocean filler to be disabled. In Vanilla it is set to 10. Takes values from 0 to 100.").getInt();

			generateGems = config.get("Biomes O\' Plenty World Type Settings", "GemGen", true, "Controls whether or not Biomes O' Plenty gem ores generate in the world (such as Tanzanite, Amythest, Sapphire, etc...)").getBoolean();
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
