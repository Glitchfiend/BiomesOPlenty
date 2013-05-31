package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenPineTree;
import biomesoplenty.worldgen.WorldGenTaiga6;

public class BiomeGenHotSprings extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenHotSprings(int par1)
	{
		super(par1);
		topBlock = (byte)Block.stone.blockID;
		fillerBlock = (byte)Block.stone.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.outbackPerChunk = 5;
		customBiomeDecorator.hotSpringsPerChunk = 25;
		customBiomeDecorator.lavaLakesPerChunk = 5;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenPineTree() : new WorldGenTaiga6(false);
	}
}
