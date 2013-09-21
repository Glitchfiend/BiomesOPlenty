package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.configfile.BOPConfigurationTerrainGen;

public class WorldGenNetherWart extends WorldGenerator
{
	/** Stores ID for WorldGenTallGrass */
	private int tallGrassID;
	private int tallGrassMetadata;

	public WorldGenNetherWart(int par1, int par2)
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
			int var999 = par2Random.nextInt(12);

			if (par1World.isAirBlock(var8, var9, var10) && par1World.getBlockId(var8, var9 - 1, var10) == Block.netherrack.blockID)
			{
				par1World.setBlock(var8, var9 - 1, var10, Block.slowSand.blockID, 0, 2);
				
				if (var999 == 0 || var999 == 1)
				{
					par1World.setBlock(var8, var9, var10, Block.netherStalk.blockID, 0, 2);
				}
				else if (var999 == 2)
				{
					par1World.setBlock(var8, var9, var10, Block.netherStalk.blockID, 1, 2);
				}
				else if (var999 == 3)
				{
					par1World.setBlock(var8, var9, var10, Block.netherStalk.blockID, 2, 2);
				}
				else if (var999 == 4 || var999 == 5)
				{
					par1World.setBlock(var8, var9, var10, Block.netherStalk.blockID, 3, 2);
				}
				else
				{
					if (BOPConfigurationTerrainGen.witherWartGen)
					{
						par1World.setBlock(var8, var9, var10, Blocks.plants.get().blockID, 13, 2);
					}
				}
			}
		}

		return true;
	}
}
