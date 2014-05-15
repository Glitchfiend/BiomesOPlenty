package biomesoplenty.common.configuration.structures;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.BiomeManager;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPBiomeManager;
import biomesoplenty.api.BOPBiomeManager.BiomeEntry;
import biomesoplenty.api.content.BOPCBiomes;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationVillages
{
	public static Configuration config;
	
	public static int villageDistance;
	
	public static ArrayList<Integer> enabledBiomes = new ArrayList();
	
	public static void addDefaultVillageBiomes()
	{
        //addVillageBiome(BOPCBiomes.alps);
        addVillageBiome(BOPCBiomes.arctic);
        //addVillageBiome(BOPCBiomes.autumnHills); 
        //addVillageBiome(BOPCBiomes.badlands); 
        //addVillageBiome(BOPCBiomes.bambooForest); 
        addVillageBiome(BOPCBiomes.bayou);
        addVillageBiome(BOPCBiomes.bog); 
        //addVillageBiome(BOPCBiomes.borealForest);
        addVillageBiome(BOPCBiomes.brushland);
        addVillageBiome(BOPCBiomes.canyon);
        addVillageBiome(BOPCBiomes.chaparral);
        //addVillageBiome(BOPCBiomes.cherryBlossomGrove); 
        addVillageBiome(BOPCBiomes.coniferousForest);
        addVillageBiome(BOPCBiomes.snowyConiferousForest);
        addVillageBiome(BOPCBiomes.deadForest); 
        //addVillageBiome(BOPCBiomes.deadForestSnow); 
        //addVillageBiome(BOPCBiomes.deadSwamp);
        addVillageBiome(BOPCBiomes.deciduousForest);
        //addVillageBiome(BOPCBiomes.dunes); 
        //addVillageBiome(BOPCBiomes.fen);
        //addVillageBiome(BOPCBiomes.flowerField);
        addVillageBiome(BOPCBiomes.frostForest); 
        //addVillageBiome(BOPCBiomes.glacier);
        addVillageBiome(BOPCBiomes.grassland);
        addVillageBiome(BOPCBiomes.grove);
        addVillageBiome(BOPCBiomes.heathland);
        //addVillageBiome(BOPCBiomes.highland);
        //addVillageBiome(BOPCBiomes.hotSprings);
        //addVillageBiome(BOPCBiomes.jadeCliffs);
        //addVillageBiome(BOPCBiomes.lushDesert);
        addVillageBiome(BOPCBiomes.lushSwamp);
        //addVillageBiome(BOPCBiomes.mangrove);
        addVillageBiome(BOPCBiomes.mapleWoods);
        //addVillageBiome(BOPCBiomes.marsh);
        addVillageBiome(BOPCBiomes.meadow);
        //addVillageBiome(BOPCBiomes.meadowForest);
        //addVillageBiome(BOPCBiomes.mesa); 
        //addVillageBiome(BOPCBiomes.moor); 
        //addVillageBiome(BOPCBiomes.mountain); 
        //addVillageBiome(BOPCBiomes.oasis); 

        //addVillageBiome(BOPCBiomes.orchard);
        addVillageBiome(BOPCBiomes.outback);
        //addVillageBiome(BOPCBiomes.overgrownGreens);
        //addVillageBiome(BOPCBiomes.polar);
        addVillageBiome(BOPCBiomes.prairie);
        //addVillageBiome(BOPCBiomes.quagmire); 
        //addVillageBiome(BOPCBiomes.rainforest); 
        //addVillageBiome(BOPCBiomes.redwoodForest);

        addVillageBiome(BOPCBiomes.seasonalForest); 
        //addVillageBiome(BOPCBiomes.shield);
        //addVillageBiome(BOPCBiomes.scrubland);
        addVillageBiome(BOPCBiomes.shrubland);
        //addVillageBiome(BOPCBiomes.sludgepit);
        addVillageBiome(BOPCBiomes.spruceWoods);
        //addVillageBiome(BOPCBiomes.steppe);
        //addVillageBiome(BOPCBiomes.temperateRainforest); 
        //addVillageBiome(BOPCBiomes.thicket);
        //addVillageBiome(BOPCBiomes.tropicalRainforest);
        //addVillageBiome(BOPCBiomes.tropics);
        addVillageBiome(BOPCBiomes.tundra);
        //addVillageBiome(BOPCBiomes.volcano); 

        //addVillageBiome(BOPCBiomes.wetland);
        addVillageBiome(BOPCBiomes.woodland);
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
			
			for (List<BiomeEntry> biomeList : BOPBiomeManager.overworldBiomes)
			{
				for (BiomeEntry entry : biomeList)
				{
					BiomeGenBase biome = entry.biome;
					
					if (config.get("Allow Villages", biome.biomeName, enabledBiomes.contains(biome.biomeID)).getBoolean(enabledBiomes.contains(biome.biomeID)))
					{
						BiomeManager.addVillageBiome(biome, true);
					}
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
	
	public static void addVillageBiome(BiomeGenBase biome)
	{
		enabledBiomes.add(biome.biomeID);
	}
}
