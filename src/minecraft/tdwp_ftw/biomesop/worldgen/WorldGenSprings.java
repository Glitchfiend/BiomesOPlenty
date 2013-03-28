package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenSprings extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (!var1.isAirBlock(var3, var4, var5) && var4 > 69)
        {
            --var4;
        }

        int var6 = var1.getBlockId(var3, var4, var5);

        if (var6 != Block.dirt.blockID)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (var1.isAirBlock(var3 - 1, var4, var5) || var1.isAirBlock(var3 + 1, var4, var5) || var1.isAirBlock(var3, var4, var5 - 1) || var1.isAirBlock(var3, var4, var5 + 1))
						{
						return true;
						}
					else
						{
						return false;
						}
                }
            }
			
            var1.setBlock(var3, var4, var5, Block.waterMoving.blockID);
            return true;
        }
    }
}
