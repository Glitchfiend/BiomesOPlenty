package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.api.Blocks;
import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCrystal1 extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        if (!par1World.isAirBlock(par3, par4, par5))
        {
            return false;
        }
        else if (par1World.getBlockId(par3, par4 + 1, par5) != Blocks.holyStone.get().blockID)
        {
            return false;
        }
        else
        {
            par1World.setBlock(par3, par4, par5, Blocks.crystal.get().blockID, 0, 2);

            for (int l = 0; l < 1500; ++l)
            {
                int i1 = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
                int j1 = par4 - par2Random.nextInt(12);
                int k1 = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

                if (par1World.getBlockId(i1, j1, k1) == 0)
                {
                    int l1 = 0;

                    for (int i2 = 0; i2 < 6; ++i2)
                    {
                        int j2 = 0;

                        if (i2 == 0)
                        {
                            j2 = par1World.getBlockId(i1 - 1, j1, k1);
                        }

                        if (i2 == 1)
                        {
                            j2 = par1World.getBlockId(i1 + 1, j1, k1);
                        }

                        if (i2 == 2)
                        {
                            j2 = par1World.getBlockId(i1, j1 - 1, k1);
                        }

                        if (i2 == 3)
                        {
                            j2 = par1World.getBlockId(i1, j1 + 1, k1);
                        }

                        if (i2 == 4)
                        {
                            j2 = par1World.getBlockId(i1, j1, k1 - 1);
                        }

                        if (i2 == 5)
                        {
                            j2 = par1World.getBlockId(i1, j1, k1 + 1);
                        }

                        if (j2 == Blocks.crystal.get().blockID)
                        {
                            ++l1;
                        }
                    }

                    if (l1 == 1)
                    {
                        par1World.setBlock(i1, j1, k1, Blocks.crystal.get().blockID, 0, 2);
                    }
                }
            }

            return true;
        }
    }
}
