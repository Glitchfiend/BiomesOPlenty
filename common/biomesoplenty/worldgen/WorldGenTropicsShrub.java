package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenTropicsShrub extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != Block.grass.blockID && var6 != Block.sand.blockID)
			return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8))
						return false;
				}
			}

			var1.setBlock(var3, var4, var5, Block.dirt.blockID);
			var1.setBlock(var3, var4 + 1, var5, Block.wood.blockID, 3, 2);
			var1.setBlock(var3, var4 + 2, var5, Block.wood.blockID, 3, 2);
			var1.setBlock(var3 + 1, var4 + 2, var5, Block.leaves.blockID, 3, 2);
			var1.setBlock(var3 - 1, var4 + 2, var5, Block.leaves.blockID, 3, 2);
			var1.setBlock(var3, var4 + 2, var5 + 1, Block.leaves.blockID, 3, 2);
			var1.setBlock(var3, var4 + 2, var5 - 1, Block.leaves.blockID, 3, 2);
			var1.setBlock(var3, var4 + 3, var5, Block.leaves.blockID, 3, 2);
			
			int var999 = var2.nextInt(12);
			int var998 = var2.nextInt(4);
			
			if (var998 == 0)
			{
				if (var999 == 0)
				{
					var1.setBlock(var3 - 1, var4 + 1, var5, Block.cocoaPlant.blockID, 11, 2);
				}
				if (var999 == 1)
				{
					var1.setBlock(var3 + 1, var4 + 1, var5, Block.cocoaPlant.blockID, 9, 2);
				}
				if (var999 == 2)
				{
					var1.setBlock(var3, var4 + 1, var5 - 1, Block.cocoaPlant.blockID, 8, 2);
				}
				if (var999 == 3)
				{
					var1.setBlock(var3, var4 + 1, var5 + 1, Block.cocoaPlant.blockID, 10, 2);
				}
			}
			else if (var998 == 1)
			{
				if (var999 == 0)
				{
					var1.setBlock(var3 - 1, var4 + 1, var5, Block.cocoaPlant.blockID, 7, 2);
				}
				if (var999 == 1)
				{
					var1.setBlock(var3 + 1, var4 + 1, var5, Block.cocoaPlant.blockID, 5, 2);
				}
				if (var999 == 2)
				{
					var1.setBlock(var3, var4 + 1, var5 - 1, Block.cocoaPlant.blockID, 4, 2);
				}
				if (var999 == 3)
				{
					var1.setBlock(var3, var4 + 1, var5 + 1, Block.cocoaPlant.blockID, 6, 2);
				}
			}
			else if (var998 == 2)
			{
				if (var999 == 0)
				{
					var1.setBlock(var3 - 1, var4 + 1, var5, Block.cocoaPlant.blockID, 3, 2);
				}
				if (var999 == 1)
				{
					var1.setBlock(var3 + 1, var4 + 1, var5, Block.cocoaPlant.blockID, 1, 2);
				}
				if (var999 == 2)
				{
					var1.setBlock(var3, var4 + 1, var5 - 1, Block.cocoaPlant.blockID, 0, 2);
				}
				if (var999 == 3)
				{
					var1.setBlock(var3, var4 + 1, var5 + 1, Block.cocoaPlant.blockID, 2, 2);
				}
			}
			return true;
		}
	}
}
