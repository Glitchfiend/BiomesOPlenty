package biomesoplenty.common.configuration;

import biomesoplenty.common.world.generation.WorldGenFieldAssociation;
import cpw.mods.fml.common.FMLLog;
import net.minecraftforge.common.config.Configuration;
import org.apache.logging.log4j.Level;

import java.io.File;
import java.util.Map.Entry;

public class BOPConfigurationWorldFeatures
{
	public static Configuration config;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);

		try
		{
			config.load();
			
			for (Entry<String, WorldGenFieldAssociation.WorldFeature> entry : WorldGenFieldAssociation.featureMap.entrySet())
			{
				String key = entry.getKey();
				
				boolean featureEnabled = true;
				
				featureEnabled = config.get("World Features", key.replace("PerChunk", "Generation"), true).getBoolean(true);
				
				if (!featureEnabled)
				{
					WorldGenFieldAssociation.featureMap.remove(key);
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
}
