package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;

public class WorldGenNetherGrass extends WorldGenerator
{
	/** Stores ID for WorldGenTallGrass */
	private int tallGrassID;
	private int tallGrassMetadata;

	public WorldGenNetherGrass(int par1, int par2)
	{
		tallGrassID = par1;
		tallGrassMetadata = par2;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		int var11;

		for (boolean var6 = false; ((var11 = par1World.getBlockId(par3, par4, par5)) == 0 || var11 == Block.leaves.blockID) && par4 > 0; --par4)
		{
			;
		}

		for (int var7 = 0; var7 < 128; ++var7)
		{
			int var8 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var9 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var10 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var99 = par2Random.nextInt(9);

			if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlockId(var8, var9 - 1, var10) == Block.netherrack.blockID)
			{
				par1World.setBlock(var8, var9 - 1, var10, BOPBlocks.overgrownNetherrack.get().blockID, 0, 2);

				if (var99 == 0)
				{
					par1World.setBlock(var8, var9, var10, Block.mushroomRed.blockID, 0, 2);
				}

				if (var99 == 1)
				{
					par1World.setBlock(var8, var9, var10, Block.mushroomBrown.blockID, 0, 2);
				}

				if (var99 == 2)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.foliage.get().blockID, 1, 2);
				}
				
				if (var99 == 5)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.foliage.get().blockID, 2, 2);
				}
				
				if (var99 == 6)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.foliage.get().blockID, 10, 2);
				}
				
				if (var99 == 7)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.foliage.get().blockID, 11, 2);
				}
				
				if (var99 == 8)
				{
					par1World.setBlock(var8, var9, var10, Block.tallGrass.blockID, 1, 2);
				}

				if (var99 == 3)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.logs4.get().blockID, 1, 2);
					if (par1World.isAirBlock(var8, var9 + 1, var10))
					{
						par1World.setBlock(var8, var9 + 1, var10, BOPBlocks.logs4.get().blockID, 1, 2);
					}
					if (par1World.isAirBlock(var8 + 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 + 1, var9 + 1, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8 - 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 - 1, var9 + 1, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 + 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 + 1, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 - 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 - 1, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 2, var10))
					{
						par1World.setBlock(var8, var9 + 2, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
				}

				if (var99 == 4)
				{
					par1World.setBlock(var8, var9, var10, BOPBlocks.logs4.get().blockID, 1, 2);
					if (par1World.isAirBlock(var8, var9 + 1, var10))
					{
						par1World.setBlock(var8, var9 + 1, var10, BOPBlocks.logs4.get().blockID, 1, 2);
					}
					if (par1World.isAirBlock(var8 + 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 + 1, var9 + 1, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8 - 1, var9 + 1, var10))
					{
						par1World.setBlock(var8 - 1, var9 + 1, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 + 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 + 1, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 1, var10 - 1))
					{
						par1World.setBlock(var8, var9 + 1, var10 - 1, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
					if (par1World.isAirBlock(var8, var9 + 2, var10))
					{
						par1World.setBlock(var8, var9 + 2, var10, BOPBlocks.leaves2.get().blockID, 4, 2);
					}
				}
			}
		}

		return true;
	}
}
