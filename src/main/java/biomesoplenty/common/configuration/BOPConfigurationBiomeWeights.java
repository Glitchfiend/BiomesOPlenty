package biomesoplenty.common.configuration;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

public class BOPConfigurationBiomeWeights 
{
	public static Configuration config;
	
	public static void init(File configFile)
	{
		config = new Configuration(configFile);
	}
}
