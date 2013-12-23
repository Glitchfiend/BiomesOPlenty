package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlocks;

public class WorldGenBoneSpine2 extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5)&& var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != Block.netherrack.blockID)
			return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (!var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8))
						return false;
				}
			}

			int var999 = var2.nextInt(2);

			if (var999 == 0)
			{
				var1.setBlock(var3, var4 - 1, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 1, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 1, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 1, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 1, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 1, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 1, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 1, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 1, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 2, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 3, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 3, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 3, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 3, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 3, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 3, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 3, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 3, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 3, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 4, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 5, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 5, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 5, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 5, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 5, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 5, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 5, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 5, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 5, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 6, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 7, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 7, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 7, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 7, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 7, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 7, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 7, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 7, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 7, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 8, var5, BOPBlocks.bones.get().blockID, 1, 2);
				var1.setBlock(var3, var4 - 9, var5, BOPBlocks.bones.get().blockID, 0, 2);
			}

			if (var999 == 1)
			{
				var1.setBlock(var3, var4 - 1, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 1, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 1, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 1, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 1, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 1, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 1, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 1, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 1, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 2, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 3, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 3, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 3, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 3, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 3, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 3, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 3, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 3, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 3, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 4, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 5, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 5, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 5, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 5, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 5, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 5, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 5, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 5, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 5, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 6, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 7, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 7, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 7, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 7, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 7, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 7, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 7, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 7, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 7, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 8, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 9, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 9, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 9, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 9, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 9, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 9, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 9, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 9, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 9, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 10, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 11, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 11, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 11, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 11, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 11, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 11, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 11, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 11, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 11, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 12, var5, BOPBlocks.bones.get().blockID, 1, 2);

				var1.setBlock(var3, var4 - 13, var5, BOPBlocks.bones.get().blockID, 2, 2);
				var1.setBlock(var3 - 1, var4 - 13, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3 + 1, var4 - 13, var5, BOPBlocks.bones.get().blockID, 6, 2);
				var1.setBlock(var3, var4 - 13, var5 - 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3, var4 - 13, var5 + 1, BOPBlocks.bones.get().blockID, 5, 2);
				var1.setBlock(var3 - 2, var4 - 13, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3 + 2, var4 - 13, var5, BOPBlocks.bones.get().blockID, 4, 2);
				var1.setBlock(var3, var4 - 13, var5 - 2, BOPBlocks.bones.get().blockID, 3, 2);
				var1.setBlock(var3, var4 - 13, var5 + 2, BOPBlocks.bones.get().blockID, 3, 2);

				var1.setBlock(var3, var4 - 14, var5, BOPBlocks.bones.get().blockID, 1, 2);
				var1.setBlock(var3, var4 - 15, var5, BOPBlocks.bones.get().blockID, 0, 2);
			}

			return true;
		}
	}
}
