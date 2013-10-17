package biomesoplenty.configuration.configfile;

import java.io.File;
import java.util.logging.Level;

import net.minecraftforge.common.Configuration;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationMisc
{
	public static Configuration config;
	
	public static boolean skyColors;
	public static boolean achievements;
	public static boolean dungeonLoot;
	public static boolean rainCreatesPuddles;
	
	public static boolean amethystTools;
	public static boolean mudTools;
	public static boolean altarCrafting;
	public static boolean scytheCrafting;
	public static boolean staffCrafting;
	public static boolean enderporterCrafting;
	public static boolean dartCrafting;
	public static boolean flowerbandCrafting;
	public static boolean hotSpringsRegeneration;

	public static int promisedLandSkyColor;
	
	public static int grassColourSmoothingArea;
	public static int leavesColourSmoothingArea;
	public static int waterColourSmoothingArea;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();

			achievements = config.get("Miscellanious Settings", "Add Biomes O\' Plenty Achievements", true).getBoolean(false);
			dungeonLoot = config.get("Miscellanious Settings", "Add Custom Dungeon Loot", true).getBoolean(false);
			rainCreatesPuddles = config.get("Miscellanious Settings", "Enable Puddles During Rain", true).getBoolean(true);
			hotSpringsRegeneration = config.get("Miscellanious Settings", "Enable Spring Water Regeneration Effect", true).getBoolean(true);

			amethystTools = config.get("Crafting Settings", "Enable Amethyst Tool/Armor Crafting", true).getBoolean(true);
			mudTools = config.get("Crafting Settings", "Enable Mud Tool/Armor Crafting", true).getBoolean(true);
			altarCrafting = config.get("Crafting Settings", "Enable Altar Items Crafting", true).getBoolean(true);
			scytheCrafting = config.get("Crafting Settings", "Enable Scythe Crafting", true).getBoolean(true);
			staffCrafting = config.get("Crafting Settings", "Enable Ancient Staff Crafting", true).getBoolean(true);
			enderporterCrafting = config.get("Crafting Settings", "Enable Enderporter Crafting", true).getBoolean(true);
			dartCrafting = config.get("Crafting Settings", "Enable Dartblower/Dart Crafting", true).getBoolean(true);
			flowerbandCrafting = config.get("Crafting Settings", "Enable Flower Band Crafting", true).getBoolean(true);

			//Hard-Coded Colors
			skyColors = config.get("Hard-Coded Colors", "Enable Sky Colors", true).getBoolean(false);

			promisedLandSkyColor = config.get("Hard-Coded Colors", "Promised Land Sky Color", 50175, null).getInt();
			
			grassColourSmoothingArea = config.get("Biome Transition Colour Smoothing", "Grass Colour Smoothing Area", 7, "In Vanilla this is 1.").getInt();
	        leavesColourSmoothingArea = config.get("Biome Transition Colour Smoothing", "Leaves Colour Smoothing Area", 7, "In Vanilla this is 1.").getInt();
	        waterColourSmoothingArea = config.get("Biome Transition Colour Smoothing", "Water Colour Smoothing Area", 5, "In Vanilla this is 1.").getInt();

			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Misc Config!");
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