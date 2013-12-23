package biomesoplenty.biomes;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.BOPBlocks;

public class BiomeGenAlps extends BiomeGenBase
{
	private BiomeDecoratorBOP customBiomeDecorator;

	public BiomeGenAlps(int par1)
	{
		super(par1);
		topBlock = Blocks.stone;
		fillerBlock = Blocks.stone;
		theBiomeDecorator = new BiomeDecoratorBOP(this);
		customBiomeDecorator = (BiomeDecoratorBOP)theBiomeDecorator;
		customBiomeDecorator.treesPerChunk = -999;
		customBiomeDecorator.flowersPerChunk = -999;
		customBiomeDecorator.grassPerChunk = -999;
		customBiomeDecorator.sandPerChunk = -999;
		customBiomeDecorator.sandPerChunk2 = -999;
	}
	
	@Override
	public void decorate(World world, Random par2Random, int chunkX, int chunkZ)
	{
		super.decorate(world, par2Random, chunkX, chunkZ);
		int var5 = 12 + par2Random.nextInt(6);

		for (int var6 = 0; var6 < var5; ++var6)
		{
			int var7 = chunkX + par2Random.nextInt(16);
			int var8 = par2Random.nextInt(28) + 4;
			int var9 = chunkZ + par2Random.nextInt(16);
			
			//TODO:		  world.getBlock(x, y, z)
			Block block = world.func_147439_a(var7, var8, var9);

			if (block != null && block.isGenMineableReplaceable(world, var7, var8, var9, Blocks.stone))
			{
				world.setBlock(var7, var8, var9, BOPBlocks.amethystOre.get().blockID, 8, 2);
			}
		}
	}
}
