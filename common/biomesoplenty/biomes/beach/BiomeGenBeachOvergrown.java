package biomesoplenty.biomes.beach;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.biomes.BiomeDecoratorBOP;
import biomesoplenty.worldgen.WorldGenChaparral2;
import biomesoplenty.worldgen.WorldGenChaparral3;

public class BiomeGenBeachOvergrown extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenBeachOvergrown(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		customBiomeDecorator.treesPerChunk = 16;
		customBiomeDecorator.deadBushPerChunk = 3;
		customBiomeDecorator.duneGrassPerChunk = 25;
		customBiomeDecorator.cactiPerChunk = 5;
		customBiomeDecorator.outbackPerChunk = 7;
		customBiomeDecorator.waterReedsPerChunk = 6;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenChaparral2() : new WorldGenChaparral3();
	}
}
