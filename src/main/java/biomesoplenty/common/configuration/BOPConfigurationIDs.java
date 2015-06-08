package biomesoplenty.common.configuration;

import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;

public class BOPConfigurationIDs
{
	public static Configuration config;

	public static int entityMudballID;
	public static int entityDartID;
	public static int entityPoisonDartID;

	public static int jungleSpiderID;
	public static int rosesterID;
	public static int globID;
	public static int phantomID;
	public static int waspID;
	public static int birdID;
	public static int pixieID;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			//Mob IDs
			jungleSpiderID = config.get("Mob IDs", "Jungle Spider ID", 101, null).getInt();
			rosesterID = config.get("Mob IDs", "Rosester ID", 102, null).getInt();
			globID = config.get("Mob IDs", "Glob ID", 106, null).getInt();
			phantomID = config.get("Mob IDs", "Phantom ID", 107, null).getInt();
	        waspID = config.get("Mob IDs", "Wasp ID", 108, null).getInt();
	        birdID = config.get("Mob IDs", "Bird ID", 109, null).getInt();
	        pixieID = config.get("Mob IDs", "Pixie ID", 110, null).getInt();

			//Projectile IDs
			entityMudballID = config.get("Entity IDs", "Mudball ID", 103, null).getInt();
			entityDartID = config.get("Entity IDs", "Dart ID", 104, null).getInt();
			entityPoisonDartID = config.get("Entity IDs", "Poison Dart ID", 105, null).getInt();
		}
		catch (Exception e)
		{
			FMLLog.log(Level.ERROR, e, "Biomes O Plenty has had a problem loading its configuration");
		}
		finally
		{
			if (config.hasChanged()) 
			{
				config.save();
			}
		}
	}
}
