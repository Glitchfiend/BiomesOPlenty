package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenPromisedWillow extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
        {
            --var4;
        }

        int var6 = var1.getBlockId(var3, var4, var5);

        if (var6 != Blocks.holyGrass.get().blockID && var6 != Blocks.holyStone.get().blockID && var6 != Block.stone.blockID)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8))
                    {
                        return false;
                    }
                }
            }

			if (var1.isAirBlock(var3 - 1, var4, var5))
				{
				var1.setBlock(var3 - 1, var4, var5, Blocks.willow.get().blockID, 8, 2);
				}
			if (var1.isAirBlock(var3 - 1, var4 - 1, var5))
				{
				var1.setBlock(var3 - 1, var4 - 1, var5, Blocks.willow.get().blockID, 8, 2);
				}
			if (var1.isAirBlock(var3 - 1, var4 - 2, var5))
				{
				var1.setBlock(var3 - 1, var4 - 2, var5, Blocks.willow.get().blockID, 8, 2);
				}
			if (var1.isAirBlock(var3 - 1, var4 - 3, var5))
				{
				var1.setBlock(var3 - 1, var4 - 3, var5, Blocks.willow.get().blockID, 8, 2);
				}
			if (var1.isAirBlock(var3 - 1, var4 - 4, var5))
				{
				var1.setBlock(var3 - 1, var4 - 4, var5, Blocks.willow.get().blockID, 8, 2);
				}
			if (var1.isAirBlock(var3 - 1, var4 - 5, var5))
				{
				var1.setBlock(var3 - 1, var4 - 5, var5, Blocks.willow.get().blockID, 8, 2);
				}
				
			if (var1.isAirBlock(var3 + 1, var4, var5))
				{
				var1.setBlock(var3 + 1, var4, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 1, var5))
				{
				var1.setBlock(var3 + 1, var4 - 1, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 2, var5))
				{
				var1.setBlock(var3 + 1, var4 - 2, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 3, var5))
				{
				var1.setBlock(var3 + 1, var4 - 3, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 4, var5))
				{
				var1.setBlock(var3 + 1, var4 - 4, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 5, var5))
				{
				var1.setBlock(var3 + 1, var4 - 5, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 6, var5))
				{
				var1.setBlock(var3 + 1, var4 - 6, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 7, var5))
				{
				var1.setBlock(var3 + 1, var4 - 7, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 8, var5))
				{
				var1.setBlock(var3 + 1, var4 - 8, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 9, var5))
				{
				var1.setBlock(var3 + 1, var4 - 9, var5, Blocks.willow.get().blockID, 2, 2);
				}
			if (var1.isAirBlock(var3 + 1, var4 - 10, var5))
				{
				var1.setBlock(var3 + 1, var4 - 10, var5, Blocks.willow.get().blockID, 2, 2);
				}
				
			if (var1.isAirBlock(var3, var4, var5 - 1))
				{
				var1.setBlock(var3, var4, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 1, var5 - 1))
				{
				var1.setBlock(var3, var4 - 1, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 2, var5 - 1))
				{
				var1.setBlock(var3, var4 - 2, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 3, var5 - 1))
				{
				var1.setBlock(var3, var4 - 3, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 4, var5 - 1))
				{
				var1.setBlock(var3, var4 - 4, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 5, var5 - 1))
				{
				var1.setBlock(var3, var4 - 5, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 6, var5 - 1))
				{
				var1.setBlock(var3, var4 - 6, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 7, var5 - 1))
				{
				var1.setBlock(var3, var4 - 7, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 8, var5 - 1))
				{
				var1.setBlock(var3, var4 - 8, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 9, var5 - 1))
				{
				var1.setBlock(var3, var4 - 9, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 10, var5 - 1))
				{
				var1.setBlock(var3, var4 - 10, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 11, var5 - 1))
				{
				var1.setBlock(var3, var4 - 11, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 12, var5 - 1))
				{
				var1.setBlock(var3, var4 - 12, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 13, var5 - 1))
				{
				var1.setBlock(var3, var4 - 13, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 14, var5 - 1))
				{
				var1.setBlock(var3, var4 - 14, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
			if (var1.isAirBlock(var3, var4 - 15, var5 - 1))
				{
				var1.setBlock(var3, var4 - 15, var5 - 1, Blocks.willow.get().blockID, 1, 2);
				}
				
			if (var1.isAirBlock(var3, var4, var5 + 1))
				{
				var1.setBlock(var3, var4, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 1, var5 + 1))
				{
				var1.setBlock(var3, var4 - 1, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 2, var5 + 1))
				{
				var1.setBlock(var3, var4 - 2, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 3, var5 + 1))
				{
				var1.setBlock(var3, var4 - 3, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 4, var5 + 1))
				{
				var1.setBlock(var3, var4 - 4, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 5, var5 + 1))
				{
				var1.setBlock(var3, var4 - 5, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 6, var5 + 1))
				{
				var1.setBlock(var3, var4 - 6, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 7, var5 + 1))
				{
				var1.setBlock(var3, var4 - 7, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 8, var5 + 1))
				{
				var1.setBlock(var3, var4 - 8, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 9, var5 + 1))
				{
				var1.setBlock(var3, var4 - 9, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 10, var5 + 1))
				{
				var1.setBlock(var3, var4 - 10, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 11, var5 + 1))
				{
				var1.setBlock(var3, var4 - 11, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 12, var5 + 1))
				{
				var1.setBlock(var3, var4 - 12, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 13, var5 + 1))
				{
				var1.setBlock(var3, var4 - 13, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 14, var5 + 1))
				{
				var1.setBlock(var3, var4 - 14, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 15, var5 + 1))
				{
				var1.setBlock(var3, var4 - 15, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 16, var5 + 1))
				{
				var1.setBlock(var3, var4 - 16, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 17, var5 + 1))
				{
				var1.setBlock(var3, var4 - 17, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 18, var5 + 1))
				{
				var1.setBlock(var3, var4 - 18, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 19, var5 + 1))
				{
				var1.setBlock(var3, var4 - 19, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
			if (var1.isAirBlock(var3, var4 - 20, var5 + 1))
				{
				var1.setBlock(var3, var4 - 20, var5 + 1, Blocks.willow.get().blockID, 4, 2);
				}
				
            return true;
        }
    }
}
