package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenShrub;
import net.minecraft.world.gen.feature.WorldGenTallGrass;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.worldgen.tree.WorldGenAcacia;

public class BiomeGenSavannaPlateau extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenSavannaPlateau(int par1)
	{
		super(par1);
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = 0;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.purpleFlowersPerChunk = 5;
		customBiomeDecorator.tinyFlowersPerChunk = 1;
		customBiomeDecorator.grassPerChunk = 10;
		customBiomeDecorator.bushesPerChunk = 5;
		customBiomeDecorator.generatePumpkins = false;
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
				par1World.setBlock(var7, var8, var9, Blocks.amethystOre.get().blockID, 2, 2);
			}
		}
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForTrees(Random par1Random)
	{
		return par1Random.nextInt(3) == 0 ? new WorldGenAcacia(false) : new WorldGenShrub(0,0);
	}

	/**
	 * Gets a WorldGen appropriate for this biome.
	 */
	@Override
	public WorldGenerator getRandomWorldGenForGrass(Random par1Random)
	{
		return par1Random.nextInt(4) == 0 ? new WorldGenTallGrass(Blocks.foliage.get().blockID, 2) : new WorldGenTallGrass(Blocks.foliage.get().blockID, 1);
	}
}
