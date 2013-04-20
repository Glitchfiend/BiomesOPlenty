package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPaperBirch2 extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (var1.isAirBlock(var3 + 2, var4, var5 + 2) && var4 > 2)
        {
            --var4;
        }

        int var6 = var1.getBlockId(var3 + 2, var4, var5 + 2);

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
                    if (var1.isAirBlock(var3 + var7 + 2, var4 - 1, var5 + var8 + 2) && var1.isAirBlock(var3 + var7 + 2, var4 - 2, var5 + var8 + 2))
                    {
                        return false;
                    }
                }
            }

			var1.setBlock(var3 + 0, var4 + 2, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 0, var4 + 3, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 0, var4 + 4, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 0, var4 + 4, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 0, var4 + 4, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 0, var4 + 5, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 1, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 2, var5 + 4, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 3, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 3, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 3, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 4, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 4, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 4, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 5, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 6, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 6, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 1, var4 + 7, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 1, var4 + 7, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 2, var4 + 0, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 1, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 2, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 2, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 2, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 2, var4 + 3, var5 + 4, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 2, var4 + 4, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 4, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 4, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 4, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 4, var5 + 4, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 5, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 5, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 5, var5 + 2, Block.wood.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 5, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 6, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 7, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 7, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 2, var4 + 7, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 2, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 2, var5 + 4, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 0, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 3, var5 + 4, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 4, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 4, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 5, var5 + 0, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 5, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 5, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 3, var4 + 5, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 6, var5 + 3, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 3, var4 + 7, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 4, var4 + 2, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 4, var4 + 2, var5 + 2, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 4, var4 + 3, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 3, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 4, var5 + 1, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 4, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 4, var5 + 3, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 5, var5 + 2, Block.leaves.blockID, 6, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 1, Block.leaves.blockID, 14, 2);
			var1.setBlock(var3 + 4, var4 + 6, var5 + 2, Block.leaves.blockID, 14, 2);
            return true;
        }
    }
}
