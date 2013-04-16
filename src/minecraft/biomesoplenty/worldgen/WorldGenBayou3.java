package biomesoplenty.worldgen;

import java.util.Random;

import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBayou3 extends WorldGenerator
{
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int var6;

        for (var6 = 7; par1World.getBlockMaterial(par3, par4 - 1, par5) == Material.water; --par4)
        {
            ;
        }

        boolean var7 = true;

        if (par4 >= 1 && par4 + var6 + 1 <= 128)
        {
            int var8;
            int var10;
            int var11;
            int var12;

            for (var8 = par4; var8 <= par4 + 1 + var6; ++var8)
            {
                byte var9 = 1;

                if (var8 == par4)
                {
                    var9 = 0;
                }

                if (var8 >= par4 + 1 + var6 - 2)
                {
                    var9 = 3;
                }

                for (var10 = par3 - var9; var10 <= par3 + var9 && var7; ++var10)
                {
                    for (var11 = par5 - var9; var11 <= par5 + var9 && var7; ++var11)
                    {
                        if (var8 >= 0 && var8 < 128)
                        {
                            var12 = par1World.getBlockId(var10, var8, var11);

                            if (var12 != 0 && var12 != BOPBlocks.willowLeaves.blockID)
                            {
                                if (var12 != Block.waterStill.blockID && var12 != Block.waterMoving.blockID)
                                {
                                    var7 = false;
                                }
                            }
                        }
                        else
                        {
                            var7 = false;
                        }
                    }
                }
            }

            if (!var7)
            {
                return false;
            }
            else
            {
                var8 = par1World.getBlockId(par3, par4 - 1, par5);

                if ((var8 == Block.grass.blockID || var8 == Block.dirt.blockID) && par4 < 128 - var6 - 1)
                {
                    this.setBlock(par1World, par3, par4 - 1, par5, Block.dirt.blockID);
                    this.setBlock(par1World, par3 - 1, par4, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 + 1, par4, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4, par5 - 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4, par5 + 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 - 1, par4 + 1, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 + 1, par4 + 1, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 1, par5 - 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 1, par5 + 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 - 1, par4 + 2, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 + 1, par4 + 2, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 2, par5 - 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 2, par5 + 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 - 1, par4 + 3, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 + 1, par4 + 3, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 3, par5 - 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 3, par5 + 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 - 1, par4 + 4, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3 + 1, par4 + 4, par5, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 4, par5 - 1, BOPBlocks.willowWood.blockID);
                    this.setBlock(par1World, par3, par4 + 4, par5 + 1, BOPBlocks.willowWood.blockID);
                    int var13;
                    int var16;

                    for (var16 = 0; var16 < var6; ++var16)
                    {
                        var10 = par1World.getBlockId(par3, par4 + var16, par5);

                        if (var10 == 0 || var10 == BOPBlocks.willowLeaves.blockID || var10 == Block.waterMoving.blockID || var10 == Block.waterStill.blockID)
                        {
                            this.setBlock(par1World, par3, par4 + var16, par5, BOPBlocks.willowWood.blockID);
                        }
                    }

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }
}
