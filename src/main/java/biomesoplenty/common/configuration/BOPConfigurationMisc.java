package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationMisc
{
	public static Configuration config;
	
	public static boolean skyColors;
	public static boolean achievements;
	public static boolean dungeonLoot;
	
	public static boolean amethystTools;
	public static boolean mudTools;
	public static boolean scytheCrafting;
	public static boolean staffCrafting;
	public static boolean enderporterCrafting;
	public static boolean dartCrafting;
	public static boolean flowerbandCrafting;
	public static boolean hotSpringsRegeneration;

	public static int promisedLandSkyColor;
	
	public static int spawnSearchRadius;
	public static boolean onlySpawnOnBeaches;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			achievements = config.get("Miscellanious Settings", "Add Biomes O\' Plenty Achievements", true).getBoolean(false);
			dungeonLoot = config.get("Miscellanious Settings", "Add Custom Dungeon Loot", true).getBoolean(false);
			hotSpringsRegeneration = config.get("Miscellanious Settings", "Enable Spring Water Regeneration Effect", true).getBoolean(true);

			amethystTools = config.get("Crafting Settings", "Enable Amethyst Tool/Armor Crafting", true).getBoolean(true);
			mudTools = config.get("Crafting Settings", "Enable Mud Tool/Armor Crafting", true).getBoolean(true);
			scytheCrafting = config.get("Crafting Settings", "Enable Scythe Crafting", true).getBoolean(true);
			staffCrafting = config.get("Crafting Settings", "Enable Ancient Staff Crafting", true).getBoolean(true);
			enderporterCrafting = config.get("Crafting Settings", "Enable Enderporter Crafting", true).getBoolean(true);
			dartCrafting = config.get("Crafting Settings", "Enable Dartblower/Dart Crafting", true).getBoolean(true);
			flowerbandCrafting = config.get("Crafting Settings", "Enable Flower Band Crafting", true).getBoolean(true);

			//Hard-Coded Colors
			skyColors = config.get("Hard-Coded Colors", "Enable Sky Colors", true).getBoolean(false);
			
			spawnSearchRadius = config.get("Spawn Settings", "Spawn Location Search Radius", 1024, "Must be 256 or higher").getInt();
			onlySpawnOnBeaches = config.get("Spawn Settings", "Only Spawn On Beaches", true).getBoolean(true);

			promisedLandSkyColor = config.get("Hard-Coded Colors", "Promised Land Sky Color", 5883101, null).getInt();

			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Misc Config!");
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