package biomesoplenty.common.world.decoration;

import java.util.Random;

import biomesoplenty.common.world.generators.WorldGenBOPFlowers;

public interface IBOPDecoration
{
	public WorldGenBOPFlowers getRandomWorldGenForBOPFlowers(Random random);
	
	public int getWorldGenPerChunk(String fieldName);
}
