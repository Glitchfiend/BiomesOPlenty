package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenCloud extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var998 = par2Random.nextInt(10);
		
		if (var998 != 0)
		{
			return false;
		}
		
		for (int var6 = 0; var6 < 80; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(12) - par2Random.nextInt(12);
			int var8 = par4 + par2Random.nextInt(6) - par2Random.nextInt(6);
			int var9 = par5 + par2Random.nextInt(12) - par2Random.nextInt(12);

			int var999 = par2Random.nextInt(4);
			
			if (var999 == 0)
			{
				if (par1World.isAirBlock(var7, var8, var9) && par1World.isAirBlock(var7 - 1, var8, var9) && par1World.isAirBlock(var7 + 1, var8, var9) && par1World.isAirBlock(var7, var8, var9 - 1) && par1World.isAirBlock(var7, var8, var9 + 1) && par1World.isAirBlock(var7, var8 - 1, var9) && par1World.isAirBlock(var7, var8 + 1, var9))
				{
					par1World.setBlock(var7, var8, var9, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7 - 1, var8, var9, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7 + 1, var8, var9, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7, var8, var9 - 1, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7, var8, var9 + 1, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7, var8 - 1, var9, Blocks.cloud.get().blockID, 0, 2);
					par1World.setBlock(var7, var8 + 1, var9, Blocks.cloud.get().blockID, 0, 2);
				}
			}
			else
			{
				if (par1World.isAirBlock(var7, var8, var9))
				{
					par1World.setBlock(var7, var8, var9, Blocks.cloud.get().blockID, 0, 2);
				}
			}
		}

		return true;
	}
}
