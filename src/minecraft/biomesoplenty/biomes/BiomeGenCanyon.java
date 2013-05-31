package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenCanyonShrub;
import biomesoplenty.worldgen.WorldGenPineTree;

public class BiomeGenCanyon extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenCanyon(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		topBlock = (byte)Blocks.hardDirt.get().blockID;
		fillerBlock = (byte)Blocks.hardDirt.get().blockID;
		customBiomeDecorator.treesPerChunk = 7;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.canyonGrassPerChunk = 5;
		customBiomeDecorator.aloePerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
		customBiomeDecorator.generateCanyon = true;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(9) == 0 ? new WorldGenPineTree() : new WorldGenCanyonShrub(0,0);
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 11123300;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 11123300;
	}
}
