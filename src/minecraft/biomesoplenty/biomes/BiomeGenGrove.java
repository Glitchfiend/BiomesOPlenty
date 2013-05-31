package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.WorldGenChaparral2;
import biomesoplenty.worldgen.WorldGenPoplar;
import biomesoplenty.worldgen.WorldGenPoplar2;

public class BiomeGenGrove extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenGrove(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.flowersPerChunk = 5;
		customBiomeDecorator.tinyFlowersPerChunk = 80;
		customBiomeDecorator.whiteFlowersPerChunk = 15;
		customBiomeDecorator.grassPerChunk = 8;
		customBiomeDecorator.sproutsPerChunk = 1;
		customBiomeDecorator.lilyflowersPerChunk = 3;
		customBiomeDecorator.berryBushesPerChunk = 2;
		customBiomeDecorator.generatePumpkins = false;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenChaparral2() : par1Random.nextInt(3) == 0 ? new WorldGenPoplar2() : new WorldGenPoplar();
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenNorwaySpruce1() : new WorldGenNorwaySpruce2());
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(2) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 5341009;
	}

	/**
	 * Provides the basic foliage color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeFoliageColor()
	{
		return 6396257;
	}
}
