package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.tree.WorldGenDeadTree;
import biomesoplenty.worldgen.tree.WorldGenPersimmon;
import biomesoplenty.worldgen.tree.WorldGenTaiga4;

public class BiomeGenAutumnHills extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenAutumnHills(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 6;
		customBiomeDecorator.grassPerChunk = 13;
		customBiomeDecorator.thornsPerChunk = 1;
		customBiomeDecorator.purpleFlowersPerChunk = 6;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.reedsPerChunk = -999;
		customBiomeDecorator.pumpkinsPerChunk = 2;
		customBiomeDecorator.bushesPerChunk = 45;
		customBiomeDecorator.berryBushesPerChunk = 5;
		customBiomeDecorator.sproutsPerChunk = 2;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(5) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 2));
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(9) == 0 ? new WorldGenDeadTree(false) : (par1Random.nextInt(6) == 0 ? new WorldGenTaiga4(false) : (par1Random.nextInt(5) == 0 ? new WorldGenPersimmon(false) : this.worldGeneratorTrees));
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 12233056;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 12897365;
	}
}
