package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMoor extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par3;
        int var89;
		int var90;
		int var91;
		int var92;
		int var93;

        for (int var7 = par5; par4 < 128; ++par4)
        {
            var89 = par1World.getBlockId(par3, par4, par5);
			var90 = par1World.getBlockId(par3 - 1, par4, par5);
			var91 = par1World.getBlockId(par3 + 1, par4, par5);
			var92 = par1World.getBlockId(par3, par4, par5 - 1);
			var93 = par1World.getBlockId(par3, par4, par5 + 1);

            if ((var89 == Block.grass.blockID) && par4 < 256 - var6 - 1)
            {
				if (var90 == Block.grass.blockID)
				{
					if (var91 == Block.grass.blockID)
					{
						if (var92 == Block.grass.blockID)
						{
							if (var93 == Block.grass.blockID)
							{
								for (int var8 = 2; var8 <= 5; ++var8)
								{
									par1World.setBlock(par3, par4, par5, Block.waterStill.blockID);
									par1World.setBlock(par3, par4 + 1, par5, 0);
									break;
								}
							}
						}
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
