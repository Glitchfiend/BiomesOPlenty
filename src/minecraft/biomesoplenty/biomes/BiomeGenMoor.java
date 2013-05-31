package biomesoplenty.biomes;

import java.awt.Color;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPConfiguration;

public class BiomeGenMoor extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMoor(int par1)
	{
		super(par1);
		spawnableCreatureList.clear();
		spawnableWaterCreatureList.clear();
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.mudPerChunk = 1;
		customBiomeDecorator.mudPerChunk2 = 1;
		customBiomeDecorator.waterLakesPerChunk = 10;
		waterColorMultiplier = 5800566;
		customBiomeDecorator.generatePumpkins = false;
	}

	//public void decorate(World par1World, Random par2Random, int par3, int par4)
	//{
	//    super.decorate(par1World, par2Random, par3, par4);
	//    WorldGenMoor var5 = new WorldGenMoor();
	//
	//    for (int var6 = 0; var6 < 16; ++var6)
	//    {
	//        int var7 = par3 + par2Random.nextInt(16) + 8;
	//        byte var8 = 64;
	//        int var9 = par4 + par2Random.nextInt(16) + 8;
	//        var5.generate(par1World, par2Random, var7, var8, var9);
	//    }
	//}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return (par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Block.tallGrass.blockID, 1) : (par1Random.nextInt(3) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1)));
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 6394725;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 6394725;
	}

	/**
	 * takes temperature, returns color
	 */
	@Override
	public int getSkyColorByTemp(float par1)
	{
		if (BOPConfiguration.skyColors = true)
			return 10536403;
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
