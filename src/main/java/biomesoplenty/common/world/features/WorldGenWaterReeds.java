package biomesoplenty.common.world.features;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenWaterReeds extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int var6 = 0; var6 < 64; ++var6)
		{
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(2) - random.nextInt(2);
			int k1 = z + random.nextInt(8) - random.nextInt(8);
			
            //TODO:	  isAirBlock()												canReplace()
			if (world.func_147437_c(i1, j1, j1) && BOPBlockHelper.get("plants").func_149705_a(world, i1, j1, k1, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 14)))
			{
				for (int i = 2; i > -2; --i)
				{
					//TODO:		getBlock()														getBlock()
					if (world.func_147439_a(i1 - i, j1 - 1, j1 - i) != Blocks.water && world.func_147439_a(i1 - i, j1 - 1, j1 - i) != Blocks.flowing_water)
					{
		            	//TODO:	setBlock()
						world.func_147465_d(i1, j1, j1, BOPBlockHelper.get("plants"), 14, 2);
					}
				}
			}
		}

		return true;
	}
}