package biomesoplenty.common.core;

import biomesoplenty.common.configuration.BOPConfigurationIDs;
import biomesoplenty.common.world.WorldProviderBOPHell;
import biomesoplenty.common.world.WorldProviderPromised;
import net.minecraftforge.common.DimensionManager;

public class BOPDimensions 
{
	public static void init()
	{
		unregisterProviders();
		registerProviders();
		registerDimensions();
	}
	
	private static void unregisterProviders()
	{
		DimensionManager.unregisterProviderType(-1);
	}
	
	private static void registerProviders()
	{
		DimensionManager.registerProviderType(-1, WorldProviderBOPHell.class, true);
        DimensionManager.registerProviderType(BOPConfigurationIDs.promisedLandDimID, WorldProviderPromised.class, false);
	}
	
	private static void registerDimensions()
	{
        DimensionManager.registerDimension(BOPConfigurationIDs.promisedLandDimID, BOPConfigurationIDs.promisedLandDimID);
	}
}
