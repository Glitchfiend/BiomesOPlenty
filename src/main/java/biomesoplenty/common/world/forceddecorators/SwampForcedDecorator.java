package biomesoplenty.common.world.forceddecorators;

import java.util.Random;

import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generators.WorldGenBOPFlowers;

public class SwampForcedDecorator implements IBOPDecoration
{
	@Override
	public int getWorldGenPerChunk(String fieldName) 
	{
		if (fieldName.equals("mudPerChunk")) return 3;
		
		return 0;
	}

	@Override
	public WorldGenBOPFlowers getRandomWorldGenForBOPFlowers(Random random) 
	{
		return null;
	}
}
