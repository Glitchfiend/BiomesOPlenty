package biomesoplenty.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;

public class WorldGenBambooTree2 extends WorldGenerator
{
	/** The minimum height of a generated tree. */
	private final int minTreeHeight;

	/** True if this tree should grow Vines. */
	private final boolean vinesGrow;

	/** The metadata value of the wood to use in tree generation. */
	private final int metaWood;

	/** The metadata value of the leaves to use in tree generation. */
	private final int metaLeaves;

	public WorldGenBambooTree2(boolean par1)
	{
		this(par1, 18, 0, 0, false);
	}

	public WorldGenBambooTree2(boolean par1, int par2, int par3, int par4, boolean par5)
	{
		super(par1);
		minTreeHeight = par2;
		metaWood = par3;
		metaLeaves = par4;
		vinesGrow = par5;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int X, int Y, int Z)
	{
		int var6 = par2Random.nextInt(3) + minTreeHeight - 7;
		boolean var7 = true;

		if (Y >= 1 && Y + var6 + 1 <= 256)
		{
			int var8;
			byte var9;
			int var11;
			int var12;

			for (var8 = Y; var8 <= Y + 1 + var6; ++var8)
			{
				var9 = 1;

				if (var8 == Y)
				{
					var9 = 0;
				}

				if (var8 >= Y + 1 + var6 - 2)
				{
					var9 = 2;
				}

				for (int var10 = X - var9; var10 <= X + var9 && var7; ++var10)
				{
					for (var11 = Z - var9; var11 <= Z + var9 && var7; ++var11)
					{
						if (var8 >= 0 && var8 < 256)
						{
							var12 = par1World.getBlockId(var10, var8, var11);

							if (var12 != 0 && var12 != BOPBlocks.leaves1.get().blockID && var12 != Block.grass.blockID && var12 != Block.dirt.blockID && var12 != BOPBlocks.bamboo.get().blockID)
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
				var8 = par1World.getBlockId(X, Y - 1, Z);

				if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && Y < 256 - var6 - 1)
				{
					var9 = 3;
					byte var18 = 0;
					int var13;
					int var14;
					int var15;

					for (var11 = Y - var9 + var6; var11 <= Y + var6; ++var11)
					{
						var12 = var11 - (Y + var6);
						var13 = var18 + 1 - var12 / 3;

						for (var14 = X - var13; var14 <= X + var13; ++var14)
						{
							var15 = var14 - X;

							for (int var16 = Z - var13; var16 <= Z + var13; ++var16)
							{
								int var17 = var16 - Z;

								if ((Math.abs(var15) != var13 || Math.abs(var17) != var13 || par2Random.nextInt(2) != 0 && var12 != 0) && !Block.opaqueCubeLookup[par1World.getBlockId(var14, var11, var16)])
								{
									this.setBlockAndMetadata(par1World, var14, var11, var16, BOPBlocks.leaves1.get().blockID, 1);
								}
							}
						}
					}

					for (var11 = 0; var11 < var6; ++var11)
					{
						var12 = par1World.getBlockId(X, Y + var11, Z);

						if (var12 == 0 || var12 == BOPBlocks.bamboo.get().blockID)
						{
							this.setBlockAndMetadata(par1World, X, Y + var11, Z, BOPBlocks.bamboo.get().blockID, 0);

							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 4), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 4), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 4), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 4), Z + 1, BOPBlocks.leaves1.get().blockID, 1);

							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 5), Z + 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 5), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 5), Z + 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 5), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 5), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 5), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 5), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 5), Z + 1, BOPBlocks.leaves1.get().blockID, 1);

							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 6), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 6), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 6), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 6), Z + 1, BOPBlocks.leaves1.get().blockID, 1);

							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 7), Z + 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 7), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 7), Z + 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 7), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 7), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 7), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 7), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 7), Z + 1, BOPBlocks.leaves1.get().blockID, 1);

							this.setBlockAndMetadata(par1World, X - 1, Y + (var6 - 8), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X + 1, Y + (var6 - 8), Z, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 8), Z - 1, BOPBlocks.leaves1.get().blockID, 1);
							this.setBlockAndMetadata(par1World, X, Y + (var6 - 8), Z + 1, BOPBlocks.leaves1.get().blockID, 1);

							if (vinesGrow && var11 > 0)
							{
								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(X - 1, Y + var11, Z))
								{
									this.setBlockAndMetadata(par1World, X - 1, Y + var11, Z, Block.vine.blockID, 8);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(X + 1, Y + var11, Z))
								{
									this.setBlockAndMetadata(par1World, X + 1, Y + var11, Z, Block.vine.blockID, 2);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(X, Y + var11, Z - 1))
								{
									this.setBlockAndMetadata(par1World, X, Y + var11, Z - 1, Block.vine.blockID, 1);
								}

								if (par2Random.nextInt(3) > 0 && par1World.isAirBlock(X, Y + var11, Z + 1))
								{
									this.setBlockAndMetadata(par1World, X, Y + var11, Z + 1, Block.vine.blockID, 4);
								}
							}
						}
					}

					if (vinesGrow)
					{
						for (var11 = Y - 3 + var6; var11 <= Y + var6; ++var11)
						{
							var12 = var11 - (Y + var6);
							var13 = 2 - var12 / 2;

							for (var14 = X - var13; var14 <= X + var13; ++var14)
							{
								for (var15 = Z - var13; var15 <= Z + var13; ++var15)
								{
									if (par1World.getBlockId(var14, var11, var15) == BOPBlocks.bamboo.get().blockID)
									{
										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 - 1, var11, var15) == 0)
										{
											this.growVines(par1World, var14 - 1, var11, var15, 8);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14 + 1, var11, var15) == 0)
										{
											this.growVines(par1World, var14 + 1, var11, var15, 2);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 - 1) == 0)
										{
											this.growVines(par1World, var14, var11, var15 - 1, 1);
										}

										if (par2Random.nextInt(4) == 0 && par1World.getBlockId(var14, var11, var15 + 1) == 0)
										{
											this.growVines(par1World, var14, var11, var15 + 1, 4);
										}
									}
								}
							}
						}

						if (par2Random.nextInt(5) == 0 && var6 > 5)
						{
							for (var11 = 0; var11 < 2; ++var11)
							{
								for (var12 = 0; var12 < 4; ++var12)
								{
									if (par2Random.nextInt(4 - var11) == 0)
									{
										var13 = par2Random.nextInt(3);
										this.setBlockAndMetadata(par1World, X + Direction.offsetX[Direction.rotateOpposite[var12]], Y + var6 - 5 + var11, Z + Direction.offsetZ[Direction.rotateOpposite[var12]], Block.cocoaPlant.blockID, var13 << 2 | var12);
									}
								}
							}
						}
					}

					return true;
				} else
					return false;
			}
		} else
			return false;
	}

	/**
	 * Grows vines downward from the given block for a given length. Args: World, x, starty, z, vine-length
	 */
	 private void growVines(World par1World, int par2, int par3, int par4, int par5)
	{
		this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
		int var6 = 4;

		while (true)
		{
			--par3;

			if (par1World.getBlockId(par2, par3, par4) != 0 || var6 <= 0)
				return;

			this.setBlockAndMetadata(par1World, par2, par3, par4, Block.vine.blockID, par5);
			--var6;
		}
	}
}
