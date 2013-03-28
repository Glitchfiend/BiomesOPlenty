package tdwp_ftw.biomesop.worldgen;

import java.util.Random;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenQuicksand2 extends WorldGenerator
{
    public boolean generate(World var1, Random var2, int var3, int var4, int var5)
    {
        while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
        {
            --var4;
        }

        int var6 = var1.getBlockId(var3, var4, var5);
		int var96 = var1.getBlockId(var3 - 1, var4, var5);
		int var97 = var1.getBlockId(var3 + 1, var4, var5);
		int var98 = var1.getBlockId(var3, var4, var5 - 1);
		int var99 = var1.getBlockId(var3, var4, var5 + 1);

        if (var6 != Block.sand.blockID && var96 != Block.sand.blockID && var97 != Block.sand.blockID && var98 != Block.sand.blockID && var99 != Block.sand.blockID)
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

			var1.setBlock(var3, var4 - 2, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4 - 1, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4, var5, mod_BiomesOPlenty.quicksand.blockID);
			
			var1.setBlock(var3 - 1, var4 - 1, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3 + 1, var4 - 1, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4 - 1, var5 - 1, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4 - 1, var5 + 1, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3 - 1, var4, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3 + 1, var4, var5, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4, var5 - 1, mod_BiomesOPlenty.quicksand.blockID);
			var1.setBlock(var3, var4, var5 + 1, mod_BiomesOPlenty.quicksand.blockID);
            return true;
        }
    }
}
