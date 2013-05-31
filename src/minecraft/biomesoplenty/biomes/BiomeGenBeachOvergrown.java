package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenBogBush;

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
		theBiomeDecorator.treesPerChunk = 15;
		theBiomeDecorator.deadBushPerChunk = 1;
		customBiomeDecorator.duneGrassPerChunk = 25;
		theBiomeDecorator.reedsPerChunk = -999;
		theBiomeDecorator.cactiPerChunk = 1;
		customBiomeDecorator.outbackPerChunk = 5;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return new WorldGenBogBush();
	 }
}
