package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenRealPineTree2 extends WorldGenerator
{
    @Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (var1.isAirBlock(var3 + 5, var4, var5 + 9) && var4 > 2)
        {
            --var4;
        }

        int var6 = var1.getBlockId(var3 + 5, var4, var5 + 9);

        if (var6 != Block.grass.blockID)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (var1.isAirBlock(var3 + var7 + 5, var4 - 1, var5 + var8 + 9) && var1.isAirBlock(var3 + var7 + 5, var4 - 2, var5 + var8 + 9))
                    {
                        return false;
                    }
                }
            }

			var1.setBlock(var3 + 1, var4 + 5, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 1, var4 + 6, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 1, var4 + 6, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 2, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 5, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 13, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 2, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 7, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 4, var5 + 7, Block.leaves.blockID, 4, 2);
			var1.setBlock(var3 + 3, var4 + 5, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 11, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 13, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 8, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 3, var4 + 8, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 2, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 2, var5 + 8, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 3, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 3, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 5, var5 + 10, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 5, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 8, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 8, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 8, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 9, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 9, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 4, var4 + 10, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 0, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 1, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 2, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 2, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 2, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 3, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 3, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 4, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 4, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 5, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 6, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 6, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 6, var5 + 13, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 7, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 7, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 7, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 8, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 8, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 8, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 8, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 8, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 9, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 9, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 9, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 9, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 9, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 10, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 10, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 5, var4 + 10, var5 + 10, Block.leaves.blockID, 4, 2);
			var1.setBlock(var3 + 5, var4 + 11, var5 + 9, Block.leaves.blockID, 4, 2);
			var1.setBlock(var3 + 6, var4 + 2, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 3, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 3, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 3, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 3, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 4, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 4, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 5, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 7, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 7, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 8, var5 + 6, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 8, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 8, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 8, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 9, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 9, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 10, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 10, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 10, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 6, var4 + 10, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 3, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 3, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 4, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 4, var5 + 9, Block.wood.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 4, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 4, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 5, var5 + 9, Block.leaves.blockID, 4, 2);
			var1.setBlock(var3 + 7, var4 + 5, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 8, var5 + 7, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 8, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 8, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 9, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 9, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 7, var4 + 10, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 3, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 3, var5 + 11, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 4, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 4, var5 + 12, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 8, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 8, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 8, var5 + 10, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 9, var5 + 9, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 10, var5 + 8, Block.leaves.blockID, 12, 2);
			var1.setBlock(var3 + 8, var4 + 10, var5 + 10, Block.leaves.blockID, 12, 2);
            return true;
        }
    }
}
