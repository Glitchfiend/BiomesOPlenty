package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenDesertCactus extends WorldGenerator
{
	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int var6 = 0; var6 < 10; ++var6)
		{
			int var7 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int var8 = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int var9 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(var7, var8, var9))
			{
				if (!par1World.isAirBlock(var7 - 1, var8 - 1, var9))
				{
					if (!par1World.isAirBlock(var7 + 1, var8 - 1, var9))
					{
						if (!par1World.isAirBlock(var7, var8 - 1, var9 - 1))
						{
							if (!par1World.isAirBlock(var7, var8 - 1, var9 + 1))
							{
								int var10 = 2 + par2Random.nextInt(par2Random.nextInt(2) + 2);

								for (int var11 = 0; var11 < var10; ++var11)
								{
									if (Blocks.plants.get().canBlockStay(par1World, var7, var8 + var11, var9))
									{
										par1World.setBlock(var7, var8 - 1, var9, Block.sand.blockID);
										par1World.setBlock(var7, var8 + var11, var9, Block.cactus.blockID);
									}
								}
							}
						}
					}
				}
			}
		}

		return true;
	}
}
