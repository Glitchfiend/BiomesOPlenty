package biomesoplenty.common.core;

import biomesoplenty.common.world.WorldProviderBOPEnd;
import biomesoplenty.common.world.WorldProviderBOPHell;
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
		DimensionManager.unregisterProviderType(1);
		DimensionManager.unregisterProviderType(-1);
	}
	
	private static void registerProviders()
	{
		DimensionManager.registerProviderType(1, WorldProviderBOPEnd.class, true);
		DimensionManager.registerProviderType(-1, WorldProviderBOPHell.class, true);
	}
	
	private static void registerDimensions()
	{
	}
}
