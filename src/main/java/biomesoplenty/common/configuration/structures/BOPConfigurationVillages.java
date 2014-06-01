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
        addVillageBiome(BOPCBiomes.arctic);
        addVillageBiome(BOPCBiomes.brushland);
        addVillageBiome(BOPCBiomes.chaparral);
        addVillageBiome(BOPCBiomes.deadForest); 
        addVillageBiome(BOPCBiomes.frostForest); 
        addVillageBiome(BOPCBiomes.grassland);
        addVillageBiome(BOPCBiomes.grove);
        addVillageBiome(BOPCBiomes.heathland);
        addVillageBiome(BOPCBiomes.meadow);
        addVillageBiome(BOPCBiomes.outback);
        addVillageBiome(BOPCBiomes.prairie);
        addVillageBiome(BOPCBiomes.shrubland);
        addVillageBiome(BOPCBiomes.steppe);
        addVillageBiome(BOPCBiomes.tundra);
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
		if (biome != null) enabledBiomes.add(biome.biomeID);
	}
}
