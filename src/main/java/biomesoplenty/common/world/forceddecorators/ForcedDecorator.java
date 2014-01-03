package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.features.WorldGenBOPFlora;

public class ForcedDecorator implements IBOPDecoration
{
	@Override
	public int getWorldGenPerChunk(String fieldName) 
	{
		return 0;
	}

	@Override
	public WorldGenBOPFlora getRandomWorldGenForBOPFlowers(Random random) 
	{
		return null;
	}

	@Override
	public WorldGenBOPFlora getRandomWorldGenForBOPPlants(Random random)
	{
		return null;
	}
}
