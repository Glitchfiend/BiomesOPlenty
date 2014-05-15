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

public class BOPConfigurationStrongholds
{
	public static Configuration config;
	
	public static ArrayList<Integer> enabledBiomes = new ArrayList();
	
	public static void addDefaultStrongholdBiomes()
	{
        addStrongholdBiome(BOPCBiomes.alps);
        addStrongholdBiome(BOPCBiomes.arctic);
        addStrongholdBiome(BOPCBiomes.bambooForest);
        addStrongholdBiome(BOPCBiomes.bayou);
        addStrongholdBiome(BOPCBiomes.bog);
        addStrongholdBiome(BOPCBiomes.borealForest);
        addStrongholdBiome(BOPCBiomes.brushland);
        addStrongholdBiome(BOPCBiomes.canyon);
        addStrongholdBiome(BOPCBiomes.chaparral);
        addStrongholdBiome(BOPCBiomes.cherryBlossomGrove);
        addStrongholdBiome(BOPCBiomes.coniferousForest);
        addStrongholdBiome(BOPCBiomes.snowyConiferousForest);
        addStrongholdBiome(BOPCBiomes.crag);
        addStrongholdBiome(BOPCBiomes.deadForest);
        addStrongholdBiome(BOPCBiomes.deadSwamp);
        addStrongholdBiome(BOPCBiomes.deciduousForest);
        addStrongholdBiome(BOPCBiomes.dunes);
        addStrongholdBiome(BOPCBiomes.fen);
        addStrongholdBiome(BOPCBiomes.flowerField);
        addStrongholdBiome(BOPCBiomes.frostForest);
        addStrongholdBiome(BOPCBiomes.fungiForest);
        //addStrongholdBiome(BOPCBiomes.garden);
        addStrongholdBiome(BOPCBiomes.grassland);
        addStrongholdBiome(BOPCBiomes.grove);
        addStrongholdBiome(BOPCBiomes.heathland);
        addStrongholdBiome(BOPCBiomes.highland);
        addStrongholdBiome(BOPCBiomes.jadeCliffs);
        addStrongholdBiome(BOPCBiomes.lavenderFields);
        addStrongholdBiome(BOPCBiomes.lushDesert);
        addStrongholdBiome(BOPCBiomes.lushSwamp);
        addStrongholdBiome(BOPCBiomes.mapleWoods);
        addStrongholdBiome(BOPCBiomes.marsh);
        addStrongholdBiome(BOPCBiomes.meadow);
        addStrongholdBiome(BOPCBiomes.moor);
        addStrongholdBiome(BOPCBiomes.mountain);
        addStrongholdBiome(BOPCBiomes.mysticGrove);
        //addStrongholdBiomBOPCBiomes.("oasis);
        addStrongholdBiome(BOPCBiomes.ominousWoods);
        //addStrongholdBiomBOPCBiomes.("orchard);
        addStrongholdBiome(BOPCBiomes.outback);
        addStrongholdBiome(BOPCBiomes.prairie);
        addStrongholdBiome(BOPCBiomes.quagmire);
        addStrongholdBiome(BOPCBiomes.rainforest);
        addStrongholdBiome(BOPCBiomes.redwoodForest);
        addStrongholdBiome(BOPCBiomes.sacredSprings);
        addStrongholdBiome(BOPCBiomes.scrubland);
        addStrongholdBiome(BOPCBiomes.seasonalForest);
        addStrongholdBiome(BOPCBiomes.shield);
        addStrongholdBiome(BOPCBiomes.shrubland);
        addStrongholdBiome(BOPCBiomes.silkglades);
        addStrongholdBiome(BOPCBiomes.sludgepit);
        addStrongholdBiome(BOPCBiomes.spruceWoods);
        addStrongholdBiome(BOPCBiomes.steppe);
        addStrongholdBiome(BOPCBiomes.temperateRainforest);
        addStrongholdBiome(BOPCBiomes.thicket);
        addStrongholdBiome(BOPCBiomes.tropicalRainforest);
        addStrongholdBiome(BOPCBiomes.tropics);
        addStrongholdBiome(BOPCBiomes.tundra);
        addStrongholdBiome(BOPCBiomes.volcano);
        addStrongholdBiome(BOPCBiomes.wasteland);
        addStrongholdBiome(BOPCBiomes.wetland);
        addStrongholdBiome(BOPCBiomes.woodland);
	}
	
	public static void init(File configFile)
	{
		addDefaultStrongholdBiomes();

		config = new Configuration(configFile);

		try
		{
			config.load();

			for (List<BiomeEntry> biomeList : BOPBiomeManager.overworldBiomes)
			{
				for (BiomeEntry entry : biomeList)
				{
					BiomeGenBase biome = entry.biome;

					if (config.get("Allow Strongholds", biome.biomeName, enabledBiomes.contains(biome.biomeID)).getBoolean(enabledBiomes.contains(biome.biomeID)))
					{
						BiomeManager.addStrongholdBiome(biome);
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
	
	public static void addStrongholdBiome(BiomeGenBase biome)
	{
		enabledBiomes.add(biome.biomeID);
	}
}
