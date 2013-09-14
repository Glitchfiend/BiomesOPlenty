package biomesoplenty.configuration.configfile;

import java.io.File;

public class BOPConfiguration 
{
	public static File mainConfigFile;
	public static File biomeGenConfigFile;
	public static File terrainGenConfigFile;
	public static File idConfigFile;
	public static File miscConfigFile;

	public static void init(String configpath)
	{
		mainConfigFile = new File(configpath + "main.cfg");
		biomeGenConfigFile = new File(configpath + "biomegen.cfg");
		terrainGenConfigFile = new File(configpath + "terraingen.cfg");
		idConfigFile = new File(configpath + "ids.cfg");
		miscConfigFile = new File(configpath + "misc.cfg");

		BOPConfigurationMain.init(mainConfigFile);
		BOPConfigurationBiomeGen.init(biomeGenConfigFile);
		BOPConfigurationTerrainGen.init(terrainGenConfigFile);
		BOPConfigurationIDs.init(idConfigFile);
		BOPConfigurationMisc.init(miscConfigFile);
	}
}
