package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.common.config.Property;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationMain
{
	public static Configuration config;

	public static boolean debugMode;
	public static boolean mobSpawns;
	public static boolean spawnJungleSpider;
	public static boolean spawnRosester;
	public static boolean spawnGlob;
	public static boolean spawnPhantom;
	public static boolean spawnPixie;
        public static boolean trailsEnabled;
	public static Property trailsVersion;

	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			debugMode = config.get("Debug Settings", "Debug Mode", false, "Turn debug mode on/off").getBoolean(false);
                        trailsEnabled = config.get("FlowerTrails", "Flower Trails Enabled", true, "Turn contributor flower trails on/off").getBoolean(true);
			trailsVersion = config.get("FlowerTrails", "Trail Version", "");
			mobSpawns = config.get("Mob settings", "Spawn all mobs", true, null).getBoolean();
			spawnJungleSpider = config.get("Mob settings", "Spawn Jungle Spider", true, null).getBoolean();
			spawnRosester = config.get("Mob settings", "Spawn Rosester", true, null).getBoolean();
			spawnGlob = config.get("Mob settings", "Spawn Glob", true, null).getBoolean();
			spawnPhantom = config.get("Mob settings", "Spawn Phantom", true, null).getBoolean();
			spawnPixie = config.get("Mob settings", "Spawn Pixie", true, null).getBoolean();
			
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