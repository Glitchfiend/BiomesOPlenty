package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationMisc;
import biomesoplenty.worldgen.tree.WorldGenDeadTree;
import biomesoplenty.worldgen.tree.WorldGenDeadTree2;
import biomesoplenty.worldgen.tree.WorldGenTaiga5;

public class BiomeGenDeadForest extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenDeadForest(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 3;
		customBiomeDecorator.grassPerChunk = 1;
		customBiomeDecorator.thornsPerChunk = 2;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.reedsPerChunk = -999;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(9) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 0) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
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
		return 12362085;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 12362085;
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfigurationMisc.skyColors)
			return 9873591;
		else
		{
			par1 /= 3.0F;

			if (par1 < -1.0F)
			{
				par1 = -1.0F;
			}

			if (par1 > 1.0F)
			{
				par1 = 1.0F;
			}

			return Color.getHSBColor(0.62222224F - par1 * 0.05F, 0.5F + par1 * 0.1F, 1.0F).getRGB();
		}
	}
}
