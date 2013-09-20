package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.biome.SpawnListEntry;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.tree.WorldGenApple;

public class BiomeGenOrchard extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenOrchard(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 2;
		customBiomeDecorator.flowersPerChunk = 20;
		customBiomeDecorator.whiteFlowersPerChunk = 20;
		customBiomeDecorator.tinyFlowersPerChunk = 20;
		customBiomeDecorator.grassPerChunk = 15;
		customBiomeDecorator.portobellosPerChunk = 2;
		customBiomeDecorator.sunflowersPerChunk = 1;
		customBiomeDecorator.lilyflowersPerChunk = 2;
		customBiomeDecorator.berryBushesPerChunk = 3;
		customBiomeDecorator.carrotsPerChunk = 1;
		spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
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

			if (Block.blocksList[var10].isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
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
		//return (WorldGenerator)(par1Random.nextInt(3) == 0 ? new WorldGenAppleTree1() : new WorldGenAppleTree2());
		return new WorldGenApple(false);
	}

	/**
	 * Provides the basic grass color based on the biome temperature and rainfall
	 */
	@Override
	public int getBiomeGrassColor()
	{
		return 14024557;
	}

	@Override
	public int getBiomeFoliageColor()
	{
		return 14024557;
	}
}
