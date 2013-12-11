package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenBOPShrub extends WorldGenerator
{
    private int field_76527_a;
    private int field_76526_b;

    public WorldGenBOPShrub(int par1, int par2)
    {
        this.field_76526_b = par1;
        this.field_76527_a = par2;
    }

    @Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l;

        Block block = null;
        do 
        {
            block = Block.blocksList[par1World.getBlockId(par3,  par4, par5)];
            if (block != null && !block.isLeaves(par1World, par3, par4, par5))
            {
                break;
            }
            par4--;
        } while (par4 > 0);

        int i1 = par1World.getBlockId(par3, par4, par5);

        if (i1 == Block.dirt.blockID || i1 == Blocks.longGrass.get().blockID)
        {
            ++par4;
            this.setBlockAndMetadata(par1World, par3, par4, par5, Block.wood.blockID, this.field_76526_b);

            for (int j1 = par4; j1 <= par4 + 2; ++j1)
            {
                int k1 = j1 - par4;
                int l1 = 2 - k1;

                for (int i2 = par3 - l1; i2 <= par3 + l1; ++i2)
                {
                    int j2 = i2 - par3;

                    for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2)
                    {
                        int l2 = k2 - par5;

                        block = Block.blocksList[par1World.getBlockId(i2, j1, k2)];

                        if ((Math.abs(j2) != l1 || Math.abs(l2) != l1 || par2Random.nextInt(2) != 0) && 
                            (block == null || block.canBeReplacedByLeaves(par1World, i2, j1, k2)))
                        {
                            this.setBlockAndMetadata(par1World, i2, j1, k2, Block.leaves.blockID, this.field_76527_a);
                        }
                    }
                }
            }
        }

        return true;
    }
}
