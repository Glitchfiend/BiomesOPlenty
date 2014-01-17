package biomesoplenty.common.configuration;

import java.io.File;
import java.util.ArrayList;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraftforge.common.config.Configuration;

import org.apache.commons.lang3.text.WordUtils;
import org.apache.logging.log4j.Level;

import biomesoplenty.api.BOPBiomeHelper;
import biomesoplenty.api.BOPBiomeHelper.BOPBiomeEntry;
import biomesoplenty.common.world.GenLayerBiomeBOP;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationBiomeGen
{
	public static Configuration config;
	
	public static ArrayList<String> disabledBiomes = new ArrayList();
	
	public static void addDefaultDisabledBiomes()
	{
	}

	public static void init(File configFile)
	{
		addDefaultDisabledBiomes();
		
		config = new Configuration(configFile);

		try
		{
			config.load();

			for (BOPBiomeEntry entry : BOPBiomeHelper.biomeList.values())
			{
				BiomeGenBase biome = entry.biome;
				
				String name = biome.biomeName;
				String convertedName = BOPBiomeHelper.convertBiomeName(name);

				if (config.get("Biomes To Generate (There must be at least one from each category)", name + " (" + WordUtils.capitalize(entry.temperatureType.toString().toLowerCase()) + ")", !disabledBiomes.contains(convertedName)).getBoolean(!disabledBiomes.contains(convertedName)))
				{
					BOPBiomeHelper.getCorrespondingTemperatureTypeList(entry.temperatureType).add(entry);
				}
			}
			
			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated Biome Gen Config!");
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