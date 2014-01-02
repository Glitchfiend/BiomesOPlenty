package biomesoplenty.common.biomes;

import net.minecraft.world.biome.BiomeGenBase;

public class BiomeGenThicket extends BiomeGenBase
{

	public BiomeGenThicket(int par1)
	{
		super(par1);
		/*
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 17;
		customBiomeDecorator.grassPerChunk = 1;
		customBiomeDecorator.wheatGrassPerChunk = 1;
		customBiomeDecorator.thornsPerChunk = 25;
		customBiomeDecorator.shrubsPerChunk = 15;
		*/
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	/*
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		 return par1Random.nextInt(5) == 0 ? worldGeneratorTrees : new WorldGenShrub(0, 0);
	}
	*/
}
