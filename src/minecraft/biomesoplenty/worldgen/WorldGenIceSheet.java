package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenIceSheet extends WorldGenerator
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
                    par1World.setBlock(par3, par4, par5, Block.ice.blockID);
                    par1World.setBlock(par3 - 1, par4, par5, Block.ice.blockID);
                    par1World.setBlock(par3 + 1, par4, par5, Block.ice.blockID);
                    par1World.setBlock(par3, par4, par5 - 1, Block.ice.blockID);
                    par1World.setBlock(par3, par4, par5 + 1, Block.ice.blockID);
					
					if (par2Random.nextInt(30) == 0)
						{
						par1World.setBlock(par3 - 1, par4, par5 - 1, Block.ice.blockID);
						par1World.setBlock(par3 + 1, par4, par5 + 1, Block.ice.blockID);
						par1World.setBlock(par3 + 1, par4, par5 - 1, Block.ice.blockID);
						par1World.setBlock(par3 - 1, par4, par5 + 1, Block.ice.blockID);
						
						par1World.setBlock(par3, par4 + 1, par5, Block.ice.blockID);
						par1World.setBlock(par3 - 1, par4 + 1, par5, Block.ice.blockID);
						par1World.setBlock(par3 + 1, par4 + 1, par5, Block.ice.blockID);
						par1World.setBlock(par3, par4 + 1, par5 - 1, Block.ice.blockID);
						par1World.setBlock(par3, par4 + 1, par5 + 1, Block.ice.blockID);
						
						par1World.setBlock(par3, par4 + 2, par5, Block.ice.blockID);
						
						par1World.setBlock(par3, par4 - 1, par5, Block.ice.blockID);
						par1World.setBlock(par3 - 1, par4 - 1, par5, Block.ice.blockID);
						par1World.setBlock(par3 + 1, par4 - 1, par5, Block.ice.blockID);
						par1World.setBlock(par3, par4 - 1, par5 - 1, Block.ice.blockID);
						par1World.setBlock(par3, par4 - 1, par5 + 1, Block.ice.blockID);
						
						par1World.setBlock(par3, par4 - 2, par5, Block.ice.blockID);
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
