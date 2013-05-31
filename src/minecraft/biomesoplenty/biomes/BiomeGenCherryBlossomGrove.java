package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenCherry1;
import biomesoplenty.worldgen.WorldGenCherry2;

public class BiomeGenCherryBlossomGrove extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCherryBlossomGrove(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.pinkFlowersPerChunk = 15;
		customBiomeDecorator.whiteFlowersPerChunk = 30;
		customBiomeDecorator.tinyFlowersPerChunk = 25;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.lilyflowersPerChunk = 9;
		customBiomeDecorator.generatePumpkins = false;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenCherry2(false) : new WorldGenCherry1(false);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 10747818;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 10747818;
	}
}
