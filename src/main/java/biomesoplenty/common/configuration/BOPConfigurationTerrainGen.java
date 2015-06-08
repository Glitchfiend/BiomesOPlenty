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
	
	public static boolean genOreGeneral;
	public static boolean genAmethystOre;
	public static boolean genRubyOre;
	public static boolean genPeridotOre;
	public static boolean genTopazOre;
	public static boolean genTanzaniteOre;
	public static boolean genMalachiteOre;
	public static boolean genSapphireOre;
	public static boolean genAmberOre;

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
			
			
			//Ores
			genOreGeneral = config.get("Biomes O\' Plenty World Type Settings", "OreGeneration", true, "Disable or Enable all BoP ore generation.").getBoolean();
			
			
			genAmethystOre = config.get("Biomes O\' Plenty World Type Settings", "genAmethystOre", true).getBoolean();
			genRubyOre = config.get("Biomes O\' Plenty World Type Settings", "genRubyOre", true).getBoolean();
			genPeridotOre = config.get("Biomes O\' Plenty World Type Settings", "genPeridotOre", true).getBoolean();
			genTopazOre = config.get("Biomes O\' Plenty World Type Settings", "genTopazOre", true).getBoolean();
			genTanzaniteOre = config.get("Biomes O\' Plenty World Type Settings", "genTanzaniteOre", true).getBoolean();
			genMalachiteOre = config.get("Biomes O\' Plenty World Type Settings", "genMalachiteOre", true).getBoolean();
			genSapphireOre = config.get("Biomes O\' Plenty World Type Settings", "genSapphireOre", true).getBoolean();
			genAmberOre = config.get("Biomes O\' Plenty World Type Settings", "genAmberOre", true, "One by one.").getBoolean();
			
			
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
