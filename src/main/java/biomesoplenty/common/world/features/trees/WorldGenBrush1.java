package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenBrush1 extends WorldGenAbstractTree
{
	public WorldGenBrush1() 
	{
		super(false);
	}

	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		//TODO:		isAirBlock()
		while (world.func_147437_c(x, y, z) && y > 2)
		{
			--y;
		}

		//TODO:			  getBlock()
		Block block = world.func_147439_a(x, y, z);

		if (block != Blocks.grass)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					//TODO:	 isAirBlock()												isAirBlock()
					if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8))
						return false;
				}
			}

			//TODO:	setBlock()
			world.func_147449_b(x, y, z, Blocks.dirt);
			world.func_147465_d(x, y + 1, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 2, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 3, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 4, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 5, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 6, z, Blocks.log, 3, 2);
			world.func_147465_d(x, y + 7, z, Blocks.log, 3, 2);
			world.func_147449_b(x + 1, y + 7, z, Blocks.leaves);
			world.func_147449_b(x - 1, y + 7, z, Blocks.leaves);
			world.func_147449_b(x, y + 7, z + 1, Blocks.leaves);
			world.func_147449_b(x, y + 7, z - 1, Blocks.leaves);

			world.func_147449_b(x + 1, y + 8, z, Blocks.leaves);
			world.func_147449_b(x - 1, y + 8, z, Blocks.leaves);
			world.func_147449_b(x, y + 8, z + 1, Blocks.leaves);
			world.func_147449_b(x, y + 8, z - 1, Blocks.leaves);
                                                         
			world.func_147449_b(x + 1, y + 8, z + 1, Blocks.leaves);
			world.func_147449_b(x + 1, y + 8, z - 1, Blocks.leaves);
			world.func_147449_b(x - 1, y + 8, z + 1, Blocks.leaves);
			world.func_147449_b(x - 1, y + 8, z - 1, Blocks.leaves);
			world.func_147449_b(x, y + 8, z, Blocks.leaves);

			world.func_147449_b(x, y + 9, z, Blocks.leaves);
			return true;
		}
	}
}