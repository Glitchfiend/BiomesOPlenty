package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;
import biomesoplenty.worldgen.WorldGenDeadTree;
import biomesoplenty.worldgen.WorldGenDeadTree2;
import biomesoplenty.worldgen.WorldGenTaiga5;

public class BiomeGenOchreAcres extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOchreAcres(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 4;
		customBiomeDecorator.grassPerChunk = 7;
		customBiomeDecorator.thornsPerChunk = 1;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.reedsPerChunk = -999;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(9) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Block.tallGrass.blockID, 2);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenDeadTree(false) : (par1Random.nextInt(4) == 0 ? new WorldGenTaiga5(false): new WorldGenDeadTree2(false));
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 16756070;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 16187283;
	}
}
