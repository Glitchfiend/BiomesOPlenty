package biomesoplenty.common.configuration;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

public class BOPConfigurationMisc
{
	public static Configuration config;
	
	public static boolean skyColors;
	public static boolean fogColors;
	//public static boolean achievements;
	public static boolean dungeonLoot;
	public static boolean titlePanorama;

    public static boolean hotSpringsRegeneration;
	
	public static int spawnSearchRadius;
	public static boolean onlySpawnOnBeaches;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			//achievements = config.get("Miscellanious Settings", "Add Biomes O\' Plenty Achievements", true).getBoolean(false);
			dungeonLoot = config.get("Miscellanious Settings", "Add Custom Dungeon Loot", true).getBoolean(false);
			hotSpringsRegeneration = config.get("Miscellanious Settings", "Enable Spring Water Regeneration Effect", true).getBoolean(true);
            hotSpringsRegeneration = config.get("Miscellanious Settings", "Behave Normally During Special Events", false).getBoolean(false);
            		titlePanorama = config.get("Miscellanious Settings", "Enable Biomes O\' Plenty Main Menu Panorama", true).getBoolean(false);

			//Hard-Coded Colors
			skyColors = config.get("Hard-Coded Colors", "Enable Sky Colors", true).getBoolean(false);
			fogColors = config.get("Hard-Coded Colors", "Enable Fog Colors", true).getBoolean(false);
			
			spawnSearchRadius = config.get("Spawn Settings", "Spawn Location Search Radius", 1024, "Must be 256 or higher").getInt();
			if (spawnSearchRadius < 256) spawnSearchRadius = 256;
			onlySpawnOnBeaches = config.get("Spawn Settings", "Only Spawn On Beaches", true).getBoolean(true);
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