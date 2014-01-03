package biomesoplenty.common.world.decoration;

import java.util.Random;

import biomesoplenty.common.world.features.WorldGenBOPFlora;

public interface IBOPDecoration
{
	public BOPWorldFeatures bopWorldFeatures = new BOPWorldFeatures();
	
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random);
}
