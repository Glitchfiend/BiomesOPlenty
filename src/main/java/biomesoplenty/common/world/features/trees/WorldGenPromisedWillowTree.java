package biomesoplenty.common.world.features.trees;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPromisedWillowTree extends WorldGenAbstractTree
{
	public WorldGenPromisedWillowTree(boolean p_i45448_1_) {
		super(p_i45448_1_);
		// TODO Auto-generated constructor stub
	}

	@Override
	public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
	{
		int var6;
		Block block;

		for (var6 = par2Random.nextInt(8) + 6; world.func_147439_a(par3, par4 - 1, par5) == Blocks.water; --par4)
		{
			;
		}

		boolean var7 = true;

		if (par4 >= 1 && par4 + var6 + 1 <= 128)
		{
			int var8;
			int var10;
			int var11;
			int var12;

			for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
			{
				byte var9 = 1;

				if (var8 == par4)
				{
					var9 = 0;
				}

				if (var8 >= par4 + 1 + var6 - 2)
				{
					var9 = 3;
				}

				for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
				{
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
					{
						if (var8 >= 0 && var8 < 128)
						{
							block = world.func_147439_a(var10, var8, var11);

							if (block.isAir(world, var10, var8, var11) || block.isLeaves(world, var10, var8, var11))
							{
								if (block != Blocks.water)
								{
									var7 = false;
								}
								else if (var8 > par4)
								{
									var7 = false;
								}
							}
						}
						else
						{
							var7 = false;
						}
					}
				}
			}

			if (!var7)
				return false;
			else
			{
				block = world.func_147439_a(par3, par4 - 1, par5);

				if ((block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("holyDirt")) && par4 < 128 - var6 - 1)
				{
					this.func_150516_a(world, par3, par4 - 1, par5, BOPBlockHelper.get("holyDirt"), 0);
					int var13;
					int var16;

					for (var16 = par4 - 3 + var6; var16 <= par4 + var6; ++var16)
					{
						var10 = var16 - (par4 + var6);
						var11 = 2 - var10 / 2;

						for (var12 = par3 - var11; var12 <= par3 + var11; ++var12)
						{
							var13 = var12 - par3;

							for (int var14 = par5 - var11; var14 <= par5 + var11; ++var14)
							{
								int var15 = var14 - par5;

								if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || par2Random.nextInt(2) != 0 && var10 != 0) && !world.func_147439_a(var12, var16, var14).func_149662_c())
								{
									this.func_150516_a(world, var12, var16, var14, Blocks.leaves, 4);
								}
							}
						}
					}

					for (var16 = 0; var16 < var6; ++var16)
					{
						Block block2 = world.func_147439_a(par3, par4 + var16, par5);

						//if (var10 == 0 || var10 == Block.leaves.blockID || block2 == Blocks.water)
						if (block.isAir(world, par3, par4 + var16, par5) || block.isLeaves(world, par3, par4 + var16, par5) || block2 == Blocks.water)
						{
							this.func_150516_a(world, par3, par4 + var16, par5, Blocks.log, 0);
						}
					}

					var16 = par4 - 3 + var6;
					for (var16 = par4 - 3 + var6; var16 <= par4 + var6; ++var16)
					{
						var10 = var16 - (par4 + var6);
						var11 = 2 - var10 / 2;

						for (var12 = par3 - var11; var12 <= par3 + var11; ++var12)
						{
							for (var13 = par5 - var11; var13 <= par5 + var11; ++var13)
							{
								//if (world.getBlockId(var12, var16, var13) == Block.leaves.blockID)
								Block block2 = world.func_147439_a(var12, var16, var13);
								if (!(block.isAir(world, var12, var16, var13) || block.isLeaves(world, var12, var16, var13)))
								{
									if (par2Random.nextInt(4) == 0 && world.func_147439_a(var12 - 1, var16, var13).isAir(world, var12 - 1, var16, var13))
									{
										this.generateVines(world, var12 - 1, var16, var13, 8);
									}

									if (par2Random.nextInt(4) == 0 && world.func_147439_a(var12 + 1, var16, var13).isAir(world, var12 + 1, var16, var13))
									{									{
										this.generateVines(world, var12 + 1, var16, var13, 2);
									}

									if (par2Random.nextInt(4) == 0 && world.func_147439_a(var12, var16, var13 - 1).isAir(world, var12, var16, var13 - 1))
									{
										this.generateVines(world, var12, var16, var13 - 1, 1);
									}

									if (par2Random.nextInt(4) == 0 && world.func_147439_a(var12, var16, var13 + 1).isAir(world, var12, var16, var13 + 1))
									{
										this.generateVines(world, var12, var16, var13 + 1, 4);
									}
								}
							}
						}
					}

					return true;
				}
				}
				else
					return false;
			}
		} else
			return false;
		return false;
	}

	/**
	 * Generates vines at the given position until it hits a block.
	 */
	 private void generateVines(World par1World, int par2, int par3, int par4, int par5)
	{
		this.func_150516_a(par1World, par2, par3, par4, Blocks.leaves, 4);
		int var6 = 24;

		while (true)
		{
			--par3;

			if (!par1World.func_147439_a(par2, par3, par4).isAir(par1World, par2, par3, par4) || var6 <= 0)
				return;

			this.func_150516_a(par1World, par2, par3, par4, Blocks.leaves, 4);
			--var6;
		}
	}
}
