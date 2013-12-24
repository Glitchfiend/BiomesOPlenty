package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationMain
{
	public static Configuration config;

	// Configuration variables
	public static boolean realisticTrees;

	public static Property seenVersion;
	public static Property seenWorldTypeMsg;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			//realisticTrees = config.get("Biome Settings", "Realistic Trees", true).getBoolean(true);
			realisticTrees = false;

			seenVersion = config.get("Vars", "Seen Version", "null");
			seenWorldTypeMsg = config.get("Vars", "Seen WorldType Msg", false);

			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Main Config!");
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, "Biomes O Plenty has had a problem loading its configuration", e);
		}
		finally
		{
			if (config.hasChanged()) {
				config.save();
			}
		}
	}
}