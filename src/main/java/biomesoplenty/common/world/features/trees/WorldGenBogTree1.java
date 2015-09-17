/*package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBogTree1 extends WorldGenAbstractTree
{
    private final int minTreeHeight;
    private final int randomTreeHeight;

    private final Block wood;
    private final Block leaves;

    private final int woodMeta;
    private final int leavesMeta;

    public WorldGenBogTree1(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
    {
        super(doBlockNotify);

        this.wood = wood;
        this.leaves = leaves;
        this.woodMeta = woodMeta;
        this.leavesMeta = leavesMeta;
        this.minTreeHeight = minTreeHeight;
        this.randomTreeHeight = randomTreeHeight;
    }

    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        int l = par2Random.nextInt(this.randomTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (par4 >= 1 && par4 + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = par4; i1 <= par4 + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == par4)
                {
                    b0 = 0;
                }

                if (i1 >= par4 + 1 + l - 2)
                {
                    b0 = 3;
                }

                for (int j1 = par3 - b0; j1 <= par3 + b0 && flag; ++j1)
                {
                    for (k1 = par5 - b0; k1 <= par5 + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = par1World.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(par1World, j1, i1, k1))
                            {
                                flag = false;
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block2 = par1World.getBlock(par3, par4 - 1, par5);

                boolean isSoil = block2.canSustainPlant(par1World, par3, par4 - 1, par5, ForgeDirection.UP, (BlockSapling)Blocks.sapling);

                if (isSoil && par4 < 256 - l - 1)
                {
                    block2.onPlantGrow(par1World, par3, par4 - 1, par5, par3, par4, par5);
                    b0 = 3;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = par4 - b0 + l; k1 <= par4 + l; ++k1)
                    {
                        i3 = k1 - (par4 + l);
                        l1 = 3 - i3;

                        for (i2 = par3 - l1; i2 <= par3 + l1; ++i2)
                        {
                            j2 = i2 - par3;

                            for (int k2 = par5 - l1; k2 <= par5 + l1; ++k2)
                            {
                                int l2 = k2 - par5;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || par2Random.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = par1World.getBlock(i2, k1, k2);

                                    if (block1.isAir(par1World, i2, k1, k2) || block1.isLeaves(par1World, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(par1World, i2, k1, k2, this.leaves, this.leavesMeta);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = par1World.getBlock(par3, par4 + k1, par5);

                        if (block.isAir(par1World, par3, par4 + k1, par5) || block.isLeaves(par1World, par3, par4 + k1, par5))
                        {
                            //TODO: setBlockAndMetadata()
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + k1, par5, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4 + k1, par5, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4 + k1, par5, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + k1, par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + k1, par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4, par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4, par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4, par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4, par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4 + 1, par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4 + 1, par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4 + 1, par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4 + 1, par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 2, par4, par5, this.wood, 4);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 2, par4, par5, this.wood, 4);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4, par5 - 2, this.wood, 8);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4, par5 + 2, this.wood, 8);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4 + (l - 4), par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4 + (l - 4), par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 1, par4 + (l - 4), par5 + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 1, par4 + (l - 4), par5 - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 2, par4 + (l - 4), par5, this.wood, 4);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 2, par4 + (l - 4), par5, this.wood, 4);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + (l - 4), par5 - 2, this.wood, 8);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + (l - 4), par5 + 2, this.wood, 8);
                            this.setBlockAndNotifyAdequately(par1World, par3 - 3, par4 + (l - 3), par5, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3 + 3, par4 + (l - 3), par5, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + (l - 3), par5 - 3, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(par1World, par3, par4 + (l - 3), par5 + 3, this.wood, this.woodMeta);
                        }
                    }

                    for (k1 = par4 - 3 + l; k1 <= par4 + l; ++k1)
                    {
                        i3 = k1 - (par4 + l);
                        l1 = 3 - i3;

                        for (i2 = par3 - l1; i2 <= par3 + l1; ++i2)
                        {
                            for (j2 = par5 - l1; j2 <= par5 + l1; ++j2)
                            {
                                if (par1World.getBlock(i2, k1, j2).isLeaves(par1World, i2, k1, j2))
                                {
                                    if (par2Random.nextInt(4) == 0 && par1World.getBlock(i2 - 1, k1, j2).isAir(par1World, i2 - 1, k1, j2))
                                    {
                                        this.growVines(par1World, i2 - 1, k1, j2, 8);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlock(i2 + 1, k1, j2).isAir(par1World, i2 + 1, k1, j2))
                                    {
                                        this.growVines(par1World, i2 + 1, k1, j2, 2);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlock(i2, k1, j2 - 1).isAir(par1World, i2, k1, j2 - 1))
                                    {
                                        this.growVines(par1World, i2, k1, j2 - 1, 1);
                                    }

                                    if (par2Random.nextInt(4) == 0 && par1World.getBlock(i2, k1, j2 + 1).isAir(par1World, i2, k1, j2 + 1))
                                    {
                                        this.growVines(par1World, i2, k1, j2 + 1, 4);
                                    }
                                }
                            }
                        }
                    }
                }

                return true;
            }
        }

        return false;
    }

    private void growVines(World world, int x, int y, int z, int flag)
    {
        //TODO: setBlockAndMetadata()
        this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, flag);
        int i1 = 4;

        while (true)
        {
            --y;

            //TODO:     getBlock()
            if (world.getBlock(x, y, z).isAir(world, x, y, z) || i1 <= 0)
            {
                return;
            }

            //TODO: setBlockAndMetadata()
            this.setBlockAndNotifyAdequately(world, x, y, z, Blocks.vine, flag);
            --i1;
        }
    }
    
    
}*/

package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBogTree1 extends WorldGenAbstractTree
{
    private final int minTreeHeight;
    private final int randomTreeHeight;

    private final Block wood;
    private final Block leaves;

    private final int woodMeta;
    private final int leavesMeta;

    public WorldGenBogTree1(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
    {
        super(doBlockNotify);

        this.wood = wood;
        this.leaves = leaves;
        this.woodMeta = woodMeta;
        this.leavesMeta = leavesMeta;
        this.minTreeHeight = minTreeHeight;
        this.randomTreeHeight = randomTreeHeight;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int l;

        for (l = random.nextInt(randomTreeHeight) + minTreeHeight; world.getBlock(x, y - 1, z).getMaterial() == Material.water; --y)
        {
            ;
        }

        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            int j1;
            int k1;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                byte b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 3;
                }

                for (j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            Block block = world.getBlock(j1, i1, k1);

                            if (!(block.isAir(world, j1, i1, k1) || block.isLeaves(world, j1, i1, k1)))
                            {
                                if (block != Blocks.water && block != Blocks.flowing_water)
                                {
                                    flag = false;
                                }
                                else if (i1 > y)
                                {
                                    flag = false;
                                }
                            }
                        }
                        else
                        {
                            flag = false;
                        }
                    }
                }
            }

            if (!flag)
            {
                return false;
            }
            else
            {
                Block block1 = world.getBlock(x, y - 1, z);

                boolean isSoil = block1.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block1.onPlantGrow(world, x, y - 1, z, x, y, z);
                    int l1;
                    int l2;
                    int k2;

                    for (k2 = y - 3 + l; k2 <= y + l; ++k2)
                    {
                        j1 = k2 - (y + l);
                        k1 = 3 - j1;

                        for (l2 = x - k1; l2 <= x + k1; ++l2)
                        {
                            l1 = l2 - x;

                            for (int i2 = z - k1; i2 <= z + k1; ++i2)
                            {
                                int j2 = i2 - z;

                                if ((Math.abs(l1) != k1 || Math.abs(j2) != k1 || random.nextInt(2) != 0 && j1 != 0) && world.getBlock(l2, k2, i2).canBeReplacedByLeaves(world, l2, k2, i2))
                                {
                                    this.setBlockAndNotifyAdequately(world, l2, k2, i2, this.leaves, this.leavesMeta);
                                }
                            }
                        }
                    }

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        Block block2 = world.getBlock(x, y + k2, z);

                        if (block2.isAir(world, x, y + k2, z) || block2.isLeaves(world, x, y + k2, z) || block2 == Blocks.flowing_water || block2 == Blocks.water)
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + k2, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + k2, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y, z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y, z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y, z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y, z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + 1, z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + 1, z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 2, y, z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x + 2, y, z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x, y, z - 2, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y, z + 2, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + (l - 4), z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + (l - 4), z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + (l - 4), z + 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + (l - 4), z - 1, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 2, y + (l - 4), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x + 2, y + (l - 4), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 4), z - 2, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 4), z + 2, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x - 3, y + (l - 3), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 3, y + (l - 3), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z - 3, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z + 3, this.wood, this.woodMeta);
                        }
                    }

                    for (k2 = y - 3 + l; k2 <= y + l; ++k2)
                    {
                        j1 = k2 - (y + l);
                        k1 = 3 - j1;

                        for (l2 = x - k1; l2 <= x + k1; ++l2)
                        {
                            for (l1 = z - k1; l1 <= z + k1; ++l1)
                            {
                                if (world.getBlock(l2, k2, l1).isLeaves(world, l2, k2, l1))
                                {
                                    if (random.nextInt(4) == 0 && world.getBlock(l2 - 1, k2, l1).isAir(world, l2 - 1, k2, l1))
                                    {
                                        this.generateVines(world, l2 - 1, k2, l1, 8);
                                    }

                                    if (random.nextInt(4) == 0 && world.getBlock(l2 + 1, k2, l1).isAir(world, l2 + 1, k2, l1))
                                    {
                                        this.generateVines(world, l2 + 1, k2, l1, 2);
                                    }

                                    if (random.nextInt(4) == 0 && world.getBlock(l2, k2, l1 - 1).isAir(world, l2, k2, l1 - 1))
                                    {
                                        this.generateVines(world, l2, k2, l1 - 1, 1);
                                    }

                                    if (random.nextInt(4) == 0 && world.getBlock(l2, k2, l1 + 1).isAir(world, l2, k2, l1 + 1))
                                    {
                                        this.generateVines(world, l2, k2, l1 + 1, 4);
                                    }
                                }
                            }
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

    // JAVADOC METHOD $$ generateVines
    private void generateVines(World par1World, int par2, int par3, int par4, int par5)
    {
        this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
        int i1 = 4;

        while (true)
        {
            --par3;

            if (!(par1World.getBlock(par2, par3, par4).isAir(par1World, par2, par3, par4)) || i1 <= 0)
            {
                return;
            }

            this.setBlockAndNotifyAdequately(par1World, par2, par3, par4, Blocks.vine, par5);
            --i1;
        }
    }
}
