package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenOutback extends WorldGenerator
{
	/** Stores ID for WorldGenTallGrass */
	private int tallGrassID;
	private int tallGrassMetadata;

	public WorldGenOutback(int par1, int par2)
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
			
			int var999 = par2Random.nextInt(5);

			if (par1World.isAirBlock(var8, var9, var10) && (par1World.getBlockId(var8, var9 - 1, var10) == Blocks.hardSand.get().blockID || par1World.getBlockId(var8, var9 - 1, var10) == Block.sand.blockID || par1World.getBlockId(var8, var9 - 1, var10) == Block.stone.blockID))
			{
				par1World.setBlock(var8, var9 - 1, var10, Block.grass.blockID, 0, 2);
				
				if (var999 == 0)
				{
					par1World.setBlock(var8, var9, var10, tallGrassID, tallGrassMetadata, 2);
				}
				else if (var999 == 1)
				{
					par1World.setBlock(var8, var9, var10, Blocks.foliage.get().blockID, 10, 2);
				}
				else if (var999 == 2)
				{
					par1World.setBlock(var8, var9, var10, Blocks.foliage.get().blockID, 11, 2);
				}
				else
				{
					par1World.setBlock(var8, var9, var10, tallGrassID, tallGrassMetadata, 2);
				}
			}
		}

		return true;
	}
}
