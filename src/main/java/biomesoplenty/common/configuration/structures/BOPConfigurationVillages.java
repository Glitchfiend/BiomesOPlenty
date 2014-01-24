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
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeEntry;

public class BOPConfigurationVillages
{
	public static Configuration config;
	
	public static int villageDistance;
	
	public static ArrayList<String> enabledBiomes = new ArrayList();
	
	public static void addDefaultVillageBiomes()
	{
        addVillageBiome("alps");
        addVillageBiome("arctic");
        //addVillageBiome("autumnHills"); 
        //addVillageBiome("badlands"); 
        addVillageBiome("bambooForest"); 
        addVillageBiome("bayou");
        addVillageBiome("bog"); 
        addVillageBiome("borealForest");
        addVillageBiome("brushland");
        addVillageBiome("canyon");
        addVillageBiome("chaparral");
        addVillageBiome("cherryBlossomGrove"); 
        addVillageBiome("coniferousForest");
        addVillageBiome("snowyConiferousForest");
        addVillageBiome("deadForest"); 
        //addVillageBiome("deadForestSnow"); 
        addVillageBiome("deadSwamp");
        addVillageBiome("deciduousForest");
        addVillageBiome("dunes"); 
        addVillageBiome("fen");
        addVillageBiome("flowerField");
        addVillageBiome("frostForest"); 
        //addVillageBiome("glacier");
        addVillageBiome("grassland");
        addVillageBiome("grove");
        addVillageBiome("heathland");
        addVillageBiome("highland");
        //addVillageBiome("hotSprings");
        addVillageBiome("jadeCliffs");
        addVillageBiome("lushDesert");
        addVillageBiome("lushSwamp");
        //addVillageBiome("mangrove");
        addVillageBiome("mapleWoods");
        addVillageBiome("marsh");
        addVillageBiome("meadow");
        //addVillageBiome("meadowForest");
        //addVillageBiome("mesa"); 
        addVillageBiome("moor"); 
        addVillageBiome("mountain"); 
        //addVillageBiome("oasis"); 

        //addVillageBiome("orchard");
        addVillageBiome("outback");
        //addVillageBiome("overgrownGreens");
        addVillageBiome("pasture");
        //addVillageBiome("polar");
        addVillageBiome("prairie");
        addVillageBiome("quagmire"); 
        addVillageBiome("rainforest"); 
        addVillageBiome("redwoodForest");

        addVillageBiome("seasonalForest"); 
        addVillageBiome("shield");
        //addVillageBiome("scrubland");
        addVillageBiome("shrubland");
        addVillageBiome("sludgepit");
        addVillageBiome("spruceWoods");
        //addVillageBiome("steppe");
        addVillageBiome("temperateRainforest"); 
        addVillageBiome("thicket");
        addVillageBiome("timber");
        addVillageBiome("tropicalRainforest");
        addVillageBiome("tropics");
        addVillageBiome("tundra");
        addVillageBiome("volcano"); 

        addVillageBiome("wetland");
        addVillageBiome("woodland");
	}
	
	public static void init(File configFile)
	{
		addDefaultVillageBiomes();

		config = new Configuration(configFile);

		try
		{
			config.load();
			
			villageDistance = config.get("Biomes O\' Plenty World Type Settings", "Distance between villages", 32, "In Vanilla it is set to 32").getInt();
			if (villageDistance < 8) villageDistance = 8;
			
			for (BOPBiomeEntry entry : BOPBiomeHelper.biomeList.values())
			{
				BiomeGenBase biome = entry.biome;
				
				String name = biome.biomeName;
				String convertedName = BOPBiomeHelper.convertBiomeName(name);

				if (config.get("Allow Villages", name, enabledBiomes.contains(convertedName)).getBoolean(enabledBiomes.contains(convertedName)))
				{
					BiomeManager.addVillageBiome(biome, true);
				}
			}
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
	
	public static void addVillageBiome(String biomeName)
	{
		enabledBiomes.add(biomeName);
	}
}
