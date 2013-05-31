package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.worldgen.WorldGenPalmTree3;

public class BiomeGenOasis extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOasis(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		topBlock = (byte)Block.sand.blockID;
		fillerBlock = (byte)Block.sand.blockID;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.reedsPerChunk = 100;
		customBiomeDecorator.oasesPerChunk = 999;
		customBiomeDecorator.oasesPerChunk2 = 999;
		customBiomeDecorator.cactiPerChunk = 7;
		customBiomeDecorator.desertSproutsPerChunk = 3;
		customBiomeDecorator.tinyCactiPerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
		customBiomeDecorator.generateMelons = true;
		customBiomeDecorator.generateQuicksand = true;
		customBiomeDecorator.waterLakesPerChunk = 10;
		customBiomeDecorator.aloePerChunk = 4;
		customBiomeDecorator.hotSpringsPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	 @Override
	 public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	 {
		 return new WorldGenPalmTree3();
	 }
}
