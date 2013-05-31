package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenOutbackShrub;
import biomesoplenty.worldgen.WorldGenOutbackTree;

public class BiomeGenOutback extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOutback(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Blocks.hardSand.get().blockID;
		fillerBlock = (byte)Blocks.hardSand.get().blockID;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.outbackPerChunk = 10;
		customBiomeDecorator.deadBushPerChunk = 7;
		customBiomeDecorator.tinyCactiPerChunk = 2;
		customBiomeDecorator.bushesPerChunk = 5;
		customBiomeDecorator.generatePumpkins = false;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenOutbackShrub(0,0) : new WorldGenOutbackTree();
	}
}
