package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenBrush1;
import biomesoplenty.worldgen.WorldGenBrush2;
import biomesoplenty.worldgen.WorldGenChaparral2;

public class BiomeGenBrushland extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenBrushland(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 10;
		customBiomeDecorator.grassPerChunk = 6;
		customBiomeDecorator.thornsPerChunk = 4;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.wheatGrassPerChunk = 3;
		customBiomeDecorator.shrubsPerChunk = 30;
		customBiomeDecorator.generateQuicksand = true;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenBrush2() : (par1Random.nextInt(5) == 0 ?  new WorldGenBrush1() : new WorldGenChaparral2());
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 13222271;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 11716223;
	}
}
