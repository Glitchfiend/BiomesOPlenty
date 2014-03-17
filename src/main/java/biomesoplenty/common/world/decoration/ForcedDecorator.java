package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.features.WorldGenBOPFlora;

import java.util.Random;

public class ForcedDecorator implements IBOPBiome
{
	protected BOPWorldFeatures bopWorldFeatures = new BOPWorldFeatures();
	
	@Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random) 
	{
		return null;
	}

	@Override
	public BOPWorldFeatures getBiomeFeatures()
	{
		return bopWorldFeatures;
	}
}
