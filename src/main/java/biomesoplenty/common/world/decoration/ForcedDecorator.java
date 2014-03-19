package biomesoplenty.common.world.decoration;

import biomesoplenty.common.world.features.WorldGenBOPFlora;

import java.util.Random;

public class ForcedDecorator implements IBOPBiome
{
	protected BOPWorldFeatures bopWorldFeatures;

    public ForcedDecorator(int id)
    {
        bopWorldFeatures = BOPDecorationManager.getBiomeFeatures(id);
    }

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
