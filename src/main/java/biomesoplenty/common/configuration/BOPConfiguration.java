package biomesoplenty.common.configuration;

import java.io.File;

public class BOPConfiguration 
{
	public static File mainConfigFile;
	public static File worldFeaturesConfigFile;
	public static File biomeGenConfigFile;
	public static File biomeWeightsConfigFile;
	public static File terrainGenConfigFile;
	public static File villagesConfigFile;
	public static File strongholdsConfigFile;
	public static File idConfigFile;
	public static File miscConfigFile;

	public static void init(String configpath)
	{
		mainConfigFile = new File(configpath + "main.cfg");
		worldFeaturesConfigFile = new File(configpath + "worldfeatures.cfg");
		biomeGenConfigFile = new File(configpath + "biomegen.cfg");
		biomeWeightsConfigFile = new File(configpath + "biomeweights.cfg");
		terrainGenConfigFile = new File(configpath + "terraingen.cfg");
		villagesConfigFile = new File(configpath + "structures/villages.cfg");
		strongholdsConfigFile = new File(configpath + "structures/strongholds.cfg");
		idConfigFile = new File(configpath + "ids.cfg");
		miscConfigFile = new File(configpath + "misc.cfg");

		BOPConfigurationMain.init(mainConfigFile);
		BOPConfigurationBiomeGen.init(biomeGenConfigFile);
		BOPConfigurationBiomeWeights.init(biomeWeightsConfigFile);
		BOPConfigurationTerrainGen.init(terrainGenConfigFile);
		BOPConfigurationIDs.init(idConfigFile);
		BOPConfigurationMisc.init(miscConfigFile);
	}
}
