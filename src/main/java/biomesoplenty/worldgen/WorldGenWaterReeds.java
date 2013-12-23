package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;

public class WorldGenWaterReeds extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int var6 = 0; var6 < 64; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var8 = par4 + par2Random.nextInt(2) - par2Random.nextInt(2);
			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9) && par1World.getBlockId(var7, var8 - 1, var9) == Block.waterStill.blockID)
			{
				for (int var900 = 2; var900 > -2; --var900)
				{
					
					if (par1World.getBlockId(var7 - var900, var8 - 1, var9 - var900) != Block.waterStill.blockID && par1World.getBlockId(var7 - var900, var8 - 1, var9 - var900) != Block.waterMoving.blockID)
					{
							par1World.setBlock(var7, var8, var9, BOPBlocks.plants.get().blockID, 14, 2);
					}
				}
			}
		}

		return true;
	}
}
