package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.configuration.configfile.BOPConfigurationMain;
import biomesoplenty.worldgen.realtree.WorldGenRealPineTree;
import biomesoplenty.worldgen.realtree.WorldGenRealPineTree2;
import biomesoplenty.worldgen.tree.WorldGenPineTree;

public class BiomeGenMountain extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenMountain(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 2;
		customBiomeDecorator.grassPerChunk = 3;
		customBiomeDecorator.berryBushesPerChunk = 3;
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		if (BOPConfigurationMain.realisticTrees)
		{
			return par1Random.nextInt(4) == 0 ? new WorldGenRealPineTree2() : (par1Random.nextInt(6) == 0 ? new WorldGenRealPineTree() : worldGeneratorTrees);
		}
		
		return par1Random.nextInt(4) == 0 ? new WorldGenPineTree() : worldGeneratorTrees;
	}
	
	@Override
	public void decorate(World par1World, Random par2Random, int par3, int par4)
	{
		super.decorate(par1World, par2Random, par3, par4);
		int var5 = 3 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = par4 + par2Random.nextInt(16);
			int var10 = par1World.getBlockId(var7, var8, var9);

			if (Block.blocksList[var10].isGenMineableReplaceable(par1World, var7, var8, var9, Block.stone.blockID))
			{
				par1World.setBlock(var7, var8, var9, Block.oreEmerald.blockID, 0, 2);
			}
		}
	}
}
