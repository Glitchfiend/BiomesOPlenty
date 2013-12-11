package biomesoplenty.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenCanyonTree extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var6 = par2Random.nextInt(5) + 7;
		int var7 = var6 - par2Random.nextInt(2) - 3;
		int var8 = var6 - var7;
		int var9 = 1 + par2Random.nextInt(var8 + 1);
		boolean var10 = true;

		if (par4 >= 95 && par4 + var6 + 1 <= 128)
		{
			int var11;
			int var13;
			int var14;
			int var15;
			int var18;

			for (var11 = par4; var11 <= par4 + 1 + var6 && var10; ++var11)
			{
				boolean var12 = true;

				if (var11 - par4 < var7)
				{
					var18 = 0;
				}
				else
				{
					var18 = var9;
				}

				for (var13 = par3 - var18; var13 <= par3 + var18 && var10; ++var13)
				{
					for (var14 = par5 - var18; var14 <= par5 + var18 && var10; ++var14)
					{
						if (var11 >= 95 && var11 < 128)
						{
							var15 = par1World.getBlockId(var13, var11, var14);

							Block block = Block.blocksList[var15];

							if (var15 != 0 && (block == null || var15 != Blocks.leavesColorized1.get().blockID))
							{
								var10 = false;
							}
						}
						else
						{
							var10 = false;
						}
					}
				}
			}

			if (!var10)
				return false;
			else
			{
				var11 = par1World.getBlockId(par3, par4 - 1, par5);

				if ((var11 == Blocks.hardDirt.get().blockID) && par4 < 128 - var6 - 1)
				{
					this.setBlock(par1World, par3, par4 - 1, par5, Blocks.hardDirt.get().blockID);
					var18 = 0;

					for (var13 = par4 + var6; var13 >= par4 + var7; --var13)
					{
						for (var14 = par3 - var18; var14 <= par3 + var18; ++var14)
						{
							var15 = var14 - par3;

							for (int var16 = par5 - var18; var16 <= par5 + var18; ++var16)
							{
								int var17 = var16 - par5;

								Block block = Block.blocksList[par1World.getBlockId(var14, var13, var16)];

								if ((Math.abs(var15) != var18 || Math.abs(var17) != var18 || var18 <= 0) &&
										(block == null || block.canBeReplacedByLeaves(par1World, var14, var13, var16)))
								{
									this.setBlockAndMetadata(par1World, var14, var13, var16, Blocks.leavesColorized1.get().blockID, 0);
								}
							}
						}

						if (var18 >= 1 && var13 == par4 + var7 + 1)
						{
							--var18;
						}
						else if (var18 < var9)
						{
							++var18;
						}
					}

					for (var13 = 0; var13 < var6 - 1; ++var13)
					{
						var14 = par1World.getBlockId(par3, par4 + var13, par5);

						Block block = Block.blocksList[var14];

						if (var14 == 0 || block == null || var14 == Blocks.leavesColorized1.get().blockID)
						{
							this.setBlockAndMetadata(par1World, par3, par4 + var13, par5, Blocks.logs1.get().blockID,0);
						}
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}
}
