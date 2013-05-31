package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenAlgae extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int var6 = 0; var6 < 80; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(4) - par2Random.nextInt(4);

			if (par1World.isAirBlock(var7, var8, var9) && par1World.getBlockId(var7, var8 - 1, var9) == Block.waterStill.blockID)
			{
				par1World.setBlock(var7, var8, var9, Blocks.foliage.get().blockID, 0, 2);
			}
		}

		return true;
	}
}
