package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenApple;

public class BiomeGenOrchard extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOrchard(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 2;
		customBiomeDecorator.flowersPerChunk = 20;
		customBiomeDecorator.whiteFlowersPerChunk = 20;
		customBiomeDecorator.tinyFlowersPerChunk = 20;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.portobellosPerChunk = 2;
		customBiomeDecorator.sunflowersPerChunk = 1;
		customBiomeDecorator.lilyflowersPerChunk = 2;
		customBiomeDecorator.berryBushesPerChunk = 3;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenAppleTree1() : new WorldGenAppleTree2());
		return new WorldGenApple(false);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 14024557;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 14024557;
	}
}
