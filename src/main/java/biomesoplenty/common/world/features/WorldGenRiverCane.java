package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;

public class WorldGenRiverCane extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random par2Random, int x, int y, int z)
	{
		for (int l = 0; l < 5; ++l)
		{
			int i1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = y;
			int k1 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

			//TODO:	  isAirBlock()
			if (world.func_147437_c(i1, j1, k1))
			{
				int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

				for (int i2 = 0; i2 < l1; ++i2)
				{
					//TODO:							canReplace()
					if (BOPBlockHelper.get("plants").func_149705_a(world, i1, j1, k1, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 8)))
					{
						//TODO:	setBlock()
						world.func_147465_d(i1, j1 + i2, k1, BOPBlockHelper.get("plants"), 8, 2);
					}
				}
			}
		}

		return true;
	}
}
