package biomesoplenty.common.configuration.structures;

import java.io.File;
import java.util.ArrayList;

import org.apache.logging.log4j.Level;

import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;
import biomesoplenty.api.BOPBiomeHelper;

public class BOPConfigurationStrongholds
{
	public static Configuration config;
	
	public static ArrayList<String> enabledBiomes = new ArrayList();
	
	public static void addDefaultStrongholdBiomes()
	{
        addStrongholdBiome("alps");
        addStrongholdBiome("arctic");
        //addStrongholdBiome("autumnHills");
        //addStrongholdBiome("badlands");
        addStrongholdBiome("bambooForest");
        addStrongholdBiome("bayou");
        //addStrongholdBiome("birchForest");
        addStrongholdBiome("bog");
        addStrongholdBiome("borealForest");
        addStrongholdBiome("brushland");
        addStrongholdBiome("canyon");
        addStrongholdBiome("chaparral");
        addStrongholdBiome("cherryBlossomGrove");
        addStrongholdBiome("coniferousForest");
        addStrongholdBiome("snowyConiferousForest");
        addStrongholdBiome("crag");
        addStrongholdBiome("deadForest");
        //addStrongholdBiome("deadForestSnow");
        addStrongholdBiome("deadSwamp");
        //addStrongholdBiome("deadlands");
        addStrongholdBiome("deciduousForest");
        addStrongholdBiome("dunes");
        addStrongholdBiome("fen");
        addStrongholdBiome("flowerField");
        addStrongholdBiome("frostForest");
        //addStrongholdBiome("fungiForest");
        //addStrongholdBiome("garden");
        //addStrongholdBiome("glacier");
        addStrongholdBiome("grassland");
        addStrongholdBiome("grove");
        addStrongholdBiome("heathland");
        addStrongholdBiome("highland");
        //addStrongholdBiome("hotSprings");
        //addStrongholdBiome("icyHills");
        addStrongholdBiome("jadeCliffs");
        addStrongholdBiome("lavenderFields");
        addStrongholdBiome("lushDesert");
        addStrongholdBiome("lushSwamp");
        //addStrongholdBiome("mangrove");
        addStrongholdBiome("mapleWoods");
        addStrongholdBiome("marsh");
        addStrongholdBiome("meadow");
        //addStrongholdBiome("mesa");
        addStrongholdBiome("moor");
        addStrongholdBiome("mountain");
        addStrongholdBiome("mysticGrove");
        //addStrongholdBiome("oasis");
        addStrongholdBiome("ominousWoods");
        //addStrongholdBiome("orchard");
        addStrongholdBiome("outback");
        //addStrongholdBiome("overgrownGreens");
        addStrongholdBiome("pasture");
        //addStrongholdBiome("polar");
        addStrongholdBiome("prairie");
        addStrongholdBiome("quagmire");
        addStrongholdBiome("rainforest");
        addStrongholdBiome("redwoodForest");
        addStrongholdBiome("sacredSprings");
        //addStrongholdBiome("savanna");
        //addStrongholdBiome("scrubland");
        addStrongholdBiome("seasonalForest");
        addStrongholdBiome("shield");
        addStrongholdBiome("shrubland");
        addStrongholdBiome("silkglades");
        addStrongholdBiome("sludgepit");
        addStrongholdBiome("spruceWoods");
        //addStrongholdBiome("steppe");
        addStrongholdBiome("temperateRainforest");
        addStrongholdBiome("thicket");
        addStrongholdBiome("timber");
        addStrongholdBiome("tropicalRainforest");
        addStrongholdBiome("tropics");
        addStrongholdBiome("tundra");
        addStrongholdBiome("volcano");
        addStrongholdBiome("wasteland");
        addStrongholdBiome("wetland");
        addStrongholdBiome("woodland");
	}
	
	public static void init(File configFile)
	{
		addDefaultStrongholdBiomes();

		config = new Configuration(configFile);

		try
		{
			config.load();
			
			for (BiomeGenBase biome : BOPBiomeHelper.biomeList.values())
			{
				String name = biome.biomeName;
				String convertedName = BOPBiomeHelper.convertBiomeName(name);

				if (config.get("Allow Strongholds", name.replace(" ", ""), enabledBiomes.contains(convertedName)).getBoolean(enabledBiomes.contains(convertedName)))
				{
					BiomeManager.addStrongholdBiome(biome);
				}
			}
			
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Villages Config!");
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
	
	public static void addStrongholdBiome(String biomeName)
	{
		enabledBiomes.add(biomeName);
	}
}
