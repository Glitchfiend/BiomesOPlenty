package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;

public class WorldGenGrave extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != Block.netherrack.blockID && var6 != Block.slowSand.blockID)
			return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8) && !var1.isAirBlock(var3 + var7, var4, var5 + var8))
						return false;
				}
			}
			
			int var999 = var2.nextInt(4);
			
			int var998 = var2.nextInt(10);
			
			if (var998 == 0)
			{
				if (var999 == 0 || var999 == 1)
				{
					var1.setBlock(var3, var4 + 1, var5, BOPBlocks.grave.get().blockID, 0, 2);
					var1.setBlock(var3, var4 + 2, var5, BOPBlocks.grave.get().blockID, 1, 2);
				}
				else
				{
					var1.setBlock(var3, var4 + 1, var5, BOPBlocks.grave.get().blockID, 2, 2);
					var1.setBlock(var3, var4 + 2, var5, BOPBlocks.grave.get().blockID, 3, 2);
				}
			}

			return true;
		}
	}
}
