package tdwp_ftw.biomesop.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import tdwp_ftw.biomesop.configuration.BOPBlocks;

public class WorldGenMarsh extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6 = par3;
        int var89;

        for (int var7 = par5; par4 < 63; ++par4)
        {
            var89 = par1World.getBlockId(par3, par4 - 1, par5);

            if ((var89 == Block.waterStill.blockID) && par4 < 256 - var6 - 1)
            {
                for (int var8 = 2; var8 <= 5; ++var8)
                {
                    par1World.setBlock(par3, par4, par5, Block.grass.blockID);
                    par1World.setBlock(par3 - 1, par4, par5, Block.grass.blockID);
                    par1World.setBlock(par3 + 1, par4, par5, Block.grass.blockID);
                    par1World.setBlock(par3, par4, par5 - 1, Block.grass.blockID);
                    par1World.setBlock(par3, par4, par5 + 1, Block.grass.blockID);
                    par1World.setBlock(par3, par4 - 1, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 2, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 3, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 4, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 5, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 6, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 7, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 8, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 9, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 10, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 11, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 12, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 13, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 14, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 15, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 16, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 17, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 1, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 1, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 1, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 1, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 2, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 2, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 2, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 2, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 3, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 3, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 3, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 3, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 4, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 4, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 4, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 4, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 5, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 5, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 5, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 5, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 6, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 6, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 6, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 6, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 7, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 7, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 7, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 7, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 8, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 8, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 8, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 8, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 9, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 9, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 9, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 9, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 10, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 10, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 10, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 10, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 11, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 11, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 11, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 11, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 12, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 12, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 12, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 12, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 13, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 13, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 13, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 13, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 14, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 14, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 14, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 14, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 15, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 15, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 15, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 15, par5 + 1, Block.dirt.blockID);
                    par1World.setBlock(par3 - 1, par4 - 16, par5, Block.dirt.blockID);
                    par1World.setBlock(par3 + 1, par4 - 16, par5, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 16, par5 - 1, Block.dirt.blockID);
                    par1World.setBlock(par3, par4 - 16, par5 + 1, Block.dirt.blockID);
					
					if (par2Random.nextInt(3) == 0)
						{
						par1World.setBlock(par3, par4 + 1, par5, BOPBlocks.highGrassBottom.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 2, par5, BOPBlocks.highGrassTop.blockID, 1, 2);
						par1World.setBlock(par3 - 1, par4 + 1, par5, BOPBlocks.highGrassBottom.blockID, 1, 2);
						par1World.setBlock(par3 - 1, par4 + 2, par5, BOPBlocks.highGrassTop.blockID, 1, 2);
						par1World.setBlock(par3 + 1, par4 + 1, par5, BOPBlocks.highGrassBottom.blockID, 1, 2);
						par1World.setBlock(par3 + 1, par4 + 2, par5, BOPBlocks.highGrassTop.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 1, par5 - 1, BOPBlocks.highGrassBottom.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 2, par5 - 1, BOPBlocks.highGrassTop.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 1, par5 + 1, BOPBlocks.highGrassBottom.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 2, par5 + 1, BOPBlocks.highGrassTop.blockID, 1, 2);
						}
					else
						{
						par1World.setBlock(par3, par4 + 1, par5, Block.tallGrass.blockID, 1, 2);
						par1World.setBlock(par3 - 1, par4 + 1, par5, Block.tallGrass.blockID, 1, 2);
						par1World.setBlock(par3 + 1, par4 + 1, par5, Block.tallGrass.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 1, par5 - 1, Block.tallGrass.blockID, 1, 2);
						par1World.setBlock(par3, par4 + 1, par5 + 1, Block.tallGrass.blockID, 1, 2);
						}
                    break;
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
