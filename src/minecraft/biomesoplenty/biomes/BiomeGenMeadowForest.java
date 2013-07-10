package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenTaiga2;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class BiomeGenMeadowForest extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMeadowForest(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 7;
		customBiomeDecorator.grassPerChunk = 10;
		customBiomeDecorator.tinyFlowersPerChunk = 7;
		customBiomeDecorator.flowersPerChunk = 10;
		customBiomeDecorator.carrotsPerChunk = 1;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
		customBiomeDecorator.hydrangeasPerChunk = 3;
		customBiomeDecorator.generatePumpkins = true;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			if (var10 == Block.stone.blockID)
			{
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 4, 2);
			}
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return new WorldGenTaiga2(false);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	 @Override
	 public int getBiomeGrassColor()
	 {
		 return 6533741;
	 }

	 @Override
	 public int getBiomeFoliageColor()
	 {
		 return 6533741;
	 }
}
