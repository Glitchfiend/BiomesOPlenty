package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;

public class WorldGenMoss extends WorldGenerator
{
	@Override
	public boolean generate(World world, Random par2Random, int par3, int par4, int par5)
	{
		int var6 = par3;

		for (int var7 = par5; par4 < 80; ++par4)
		{
			//TODO:	  isAirBlock()
			if (world.func_147437_c(par3, par4, par5))
			{
				for (int var8 = 2; var8 <= 5; ++var8)
				{
					//TODO:							canPlaceBlockOnSide()
					if (BOPBlockHelper.get("moss").func_149707_d(world, par3, par4, par5, var8))
					{
						int var999 = par2Random.nextInt(4);

						if (var999 == 0)
						{
							//TODO:	setBlock()
							world.func_147465_d(par3, par4, par5, BOPBlockHelper.get("moss"), 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
						}
						break;
					}
				}
			}
			else
			{
				par3 = var6 + par2Random.nextInt(4) - par2Random.nextInt(4);
				par5 = var7 + par2Random.nextInt(4) - par2Random.nextInt(4);
			}
		}

		return true;
	}
}
