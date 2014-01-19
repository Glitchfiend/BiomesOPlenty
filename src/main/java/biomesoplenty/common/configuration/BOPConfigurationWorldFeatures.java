package biomesoplenty.common.configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.Map.Entry;

import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.config.Configuration;

import org.apache.logging.log4j.Level;

import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import cpw.mods.fml.common.FMLCommonHandler;
import cpw.mods.fml.common.FMLLog;

public class BOPConfigurationWorldFeatures
{
	public static Configuration config;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			for (Entry<String, WorldGenerator> entry : WorldGenFieldAssociation.worldGeneratorMap.entrySet())
			{
				String key = entry.getKey();
				
				boolean featureEnabled = true;
				
				featureEnabled = config.get("World Features", key.replace("PerChunk", "Generation"), true).getBoolean(true);
				
				if (!featureEnabled)
				{
					WorldGenFieldAssociation.worldGeneratorMap.remove(key);
				}
			}

			FMLCommonHandler.instance().getFMLLogger().log(Level.INFO, "[BiomesOPlenty] Generated World Features Config!");
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
}
