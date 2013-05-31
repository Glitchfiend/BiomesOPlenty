package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenMangrove;
import biomesoplenty.worldgen.WorldGenMangrove2;

public class BiomeGenMangrove extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMangrove(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 6;
		customBiomeDecorator.deadBushPerChunk = 1;
		customBiomeDecorator.deadGrassPerChunk = 9;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.cactiPerChunk = -999;
		customBiomeDecorator.desertSproutsPerChunk = 1;
		customBiomeDecorator.waterLakesPerChunk = 10;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return par1Random.nextInt(3) == 0 ? new WorldGenMangrove2(0,0) : new WorldGenMangrove(false);
	 }
}
