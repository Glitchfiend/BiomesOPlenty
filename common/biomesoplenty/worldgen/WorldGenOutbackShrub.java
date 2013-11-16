package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenOutbackShrub extends WorldGenerator
{
	private int field_76527_a;
	private int field_76526_b;

	public WorldGenOutbackShrub(int par1, int par2)
	{
		field_76526_b = par1;
		field_76527_a = par2;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var15;

		for (boolean var6 = false; ((var15 = par1World.getBlockId(par3, par4, par5)) == 0 || var15 == Blocks.leavesColorized1.get().blockID) && par4 > 0; --par4)
		{
			;
		}

		int var7 = par1World.getBlockId(par3, par4, par5);

		if (var7 == Blocks.hardSand.get().blockID)
		{
			++par4;
			this.setBlockAndMetadata(par1World, par3, par4, par5, Blocks.logs1.get().blockID,0);

			for (int var8 = par4; var8 <= par4 + 1; ++var8)
			{
				int var9 = var8 - par4;
				int var10 = 2 - var9;

				for (int var11 = par3 - var10; var11 <= par3 + var10; ++var11)
				{
					int var12 = var11 - par3;

					for (int var13 = par5 - var10; var13 <= par5 + var10; ++var13)
					{
						int var14 = var13 - par5;

						if ((Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var11, var8, var13)])
						{
							this.setBlockAndMetadata(par1World, var11, var8, var13, Blocks.leavesColorized1.get().blockID, 0);
						}
					}
				}
			}
		}

		return true;
	}
}
