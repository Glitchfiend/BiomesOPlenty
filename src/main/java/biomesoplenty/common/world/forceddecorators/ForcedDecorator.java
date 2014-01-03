package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.BOPWorldFeatures;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForcedDecorator implements IBOPDecoration
{
	protected BOPWorldFeatures bopWorldFeatures;
	
	public ForcedDecorator()
	{
		bopWorldFeatures = new BOPWorldFeatures();
	}
	
	@Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random) 
	{
		return null;
	}

	@Override
	public BOPWorldFeatures getWorldFeatures() 
	{
		return bopWorldFeatures;
	}
}
