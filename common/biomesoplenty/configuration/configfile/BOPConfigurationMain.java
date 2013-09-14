package biomesoplenty.configuration.configfile;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import net.minecraftforge.common.Property;
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