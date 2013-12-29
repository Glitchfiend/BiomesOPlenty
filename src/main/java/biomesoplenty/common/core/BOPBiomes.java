package biomesoplenty.common.core;

import biomesoplenty.common.world.ForcedDecorators;
import biomesoplenty.common.world.WorldGenFieldAssociation;
import biomesoplenty.common.world.WorldTypeBOP;

public class BOPBiomes 
{
	public static WorldTypeBOP worldTypeBOP;
	
	public static void init()
	{
		initializeBiomes();
		WorldGenFieldAssociation.init();
		ForcedDecorators.init();
	}
	
	private static void initializeBiomes()
	{
	}
}
