package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationBiomeGen
{
	public static Configuration config;
	
	//Special
	public static boolean oceanGen = false;
	public static boolean frozenOceanGen = false;
	public static boolean deepOceanGen = false;
	public static boolean mushroomIslandGen = false;
	public static boolean mesaPlateauFGen = false;
	public static boolean mesaPlateauGen = false;
	public static boolean jungleGen = false;
	public static boolean megaTaigaGen = false;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			oceanGen = config.get("Special Biomes To Generate", "Ocean", true).getBoolean(true);
			frozenOceanGen = config.get("Special Biomes To Generate", "Frozen Ocean", true).getBoolean(true);
			deepOceanGen = config.get("Special Biomes To Generate", "Deep Ocean", true).getBoolean(true);
			mushroomIslandGen = config.get("Special Biomes To Generate", "Mushroom Island", true).getBoolean(true);
			mesaPlateauFGen = config.get("Special Biomes To Generate", "Mesa Plateau F", true).getBoolean(true);
			mesaPlateauGen = config.get("Special Biomes To Generate", "Mesa Plateau", true).getBoolean(true);
			jungleGen = config.get("Special Biomes To Generate", "Jungle", true).getBoolean(true);
			megaTaigaGen = config.get("Special Biomes To Generate", "Mega Taiga", true).getBoolean(true);
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