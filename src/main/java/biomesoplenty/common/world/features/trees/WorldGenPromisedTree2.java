package biomesoplenty.common.world.features.trees;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPromisedTree2 extends WorldGenAbstractTree
{
	public WorldGenPromisedTree2(boolean par1)
	{
		super(par1);
	}

	@Override
	public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
	{
		int var6 = par2Random.nextInt(3) + 5;
		boolean var7 = true;
		
		Block block;

		if (par4 >= 1 && par4 + var6 + 1 <= 256)
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
					var9 = 2;
				}

				for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
				{
					for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
					{
						if (var8 >= 0 && var8 < 256)
						{
							//var12 = world.getBlockId(var10, var8, var11);
							block = world.func_147439_a(var10, var8, var11);

							//if (var12 != 0 && var12 != Blocks.leaves1.get().blockID)
							//{
							if (block.isAir(world, var10, var8, var11) || block.isLeaves(world, var10, var8, var11))
	                        {
								var7 = false;
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

				if ((block == BOPBlockHelper.get("holyGrass") || block == BOPBlockHelper.get("holyDirt")) && par4 < 256 - var6 - 1)
				{
					//this.setBlockAndMetadata(world, par3, par4 - 1, par5, Blocks.holyDirt.get().blockID, 0);
					this.func_150516_a(world, par3, par4 - 1, par5, BOPBlockHelper.get("holyDirt"), 0);
					int var16;

					for (var16 = par4 - 3 + var6; var16 <= par4 + var6; ++var16)
					{
						var10 = var16 - (par4 + var6);
						var11 = 1 - var10 / 2;

						for (var12 = par3 - var11; var12 <= par3 + var11; ++var12)
						{
							int var13 = var12 - par3;

							for (int var14 = par5 - var11; var14 <= par5 + var11; ++var14)
							{
								int var15 = var14 - par5;

								if ((Math.abs(var13) != var11 || Math.abs(var15) != var11 || par2Random.nextInt(2) != 0 && var10 != 0) && !world.func_147439_a(var12, var16, var14).func_149662_c())
								{
									//this.setBlockAndMetadata(world, var12, var16, var14, Blocks.leaves1.get().blockID, 2);
									this.func_150516_a(world, var12, var16, var14, BOPBlockHelper.get("leaves1"), 2);}
							}
						}
					}

					for (var16 = 0; var16 < var6; ++var16)
					{
						//var10 = world.getBlockId(par3, par4 + var16, par5);
						block = world.func_147439_a(par3, par4 + var16, par5);

						if (!(block.isAir(world, par3, par4 + var16, par5) || block.isLeaves(world, par3, par4 + var16, par5)))
						{
							//this.setBlockAndMetadata(world, par3, par4 + var16, par5, Blocks.logs2.get().blockID,1);
							this.func_150516_a(world, par3, par4 + var16, par5, BOPBlockHelper.get("logs2"), 1);
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
