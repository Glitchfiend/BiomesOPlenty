package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenRainforestTree1 extends WorldGenAbstractTree
{
    private final int minTreeHeight;
    private final int randomTreeHeight;

    private final Block wood;
    private final Block leaves;
    
    private final int woodMeta;
    private final int leavesMeta;

    public WorldGenRainforestTree1(Block wood, Block leaves, int woodMeta, int leavesMeta, boolean doBlockNotify, int minTreeHeight, int randomTreeHeight)
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
        int l = random.nextInt(randomTreeHeight) + this.minTreeHeight;
        boolean flag = true;

        if (y >= 1 && y + l + 1 <= 256)
        {
            byte b0;
            int k1;
            Block block;

            for (int i1 = y; i1 <= y + 1 + l; ++i1)
            {
                b0 = 1;

                if (i1 == y)
                {
                    b0 = 0;
                }

                if (i1 >= y + 1 + l - 2)
                {
                    b0 = 2;
                }

                for (int j1 = x - b0; j1 <= x + b0 && flag; ++j1)
                {
                    for (k1 = z - b0; k1 <= z + b0 && flag; ++k1)
                    {
                        if (i1 >= 0 && i1 < 256)
                        {
                            block = world.getBlock(j1, i1, k1);

                            if (!this.isReplaceable(world, j1, i1, k1))
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
                Block block2 = world.getBlock(x, y - 1, z);

                boolean isSoil = block2.canSustainPlant(world, x, y - 1, z, ForgeDirection.UP, (BlockSapling)Blocks.sapling);
                if (isSoil && y < 256 - l - 1)
                {
                    block2.onPlantGrow(world, x, y - 1, z, x, y, z);
                    b0 = 3;
                    byte b1 = 0;
                    int l1;
                    int i2;
                    int j2;
                    int i3;

                    for (k1 = y - b0 + l; k1 <= y + l; ++k1)
                    {
                        i3 = k1 - (y + l);
                        l1 = b1 + 1 - i3;

                        for (i2 = x - l1; i2 <= x + l1; ++i2)
                        {
                            j2 = i2 - x;

                            for (int k2 = z - l1; k2 <= z + l1; ++k2)
                            {
                                int l2 = k2 - z;

                                if (Math.abs(j2) != l1 || Math.abs(l2) != l1 || random.nextInt(2) != 0 && i3 != 0)
                                {
                                    Block block1 = world.getBlock(i2, k1, k2);

                                    if (block1.isAir(world, i2, k1, k2) || block1.isLeaves(world, i2, k1, k2))
                                    {
                                        this.setBlockAndNotifyAdequately(world, i2, k1, k2, this.leaves, this.leavesMeta);
                                    }
                                }
                            }
                        }
                    }

                    for (k1 = 0; k1 < l; ++k1)
                    {
                        block = world.getBlock(x, y + k1, z);

                        if (block.isAir(world, x, y + k1, z) || block.isLeaves(world, x, y + k1, z))
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k1, z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 3, y + (l - 3), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x + 3, y + (l - 3), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z - 3, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z + 3, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x - 2, y + (l - 4), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 2, y + (l - 4), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 4), z - 2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 4), z + 2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 2, y + (l - 5), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x + 2, y + (l - 5), z, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 5), z - 2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 5), z + 2, this.wood, this.woodMeta);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + (l - 6), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + (l - 6), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 6), z - 1, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 6), z + 1, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 2), z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 1), z, this.leaves, this.leavesMeta);
                            this.setBlockAndNotifyAdequately(world, x, y + (l), z, this.leaves, this.leavesMeta);
                            this.func_150515_a(world, x, y + (l - 4), z, Blocks.air);
                            this.func_150515_a(world, x, y + (l - 5), z, Blocks.air);
                            this.setBlockAndNotifyAdequately(world, x - 1, y + (l - 3), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x + 1, y + (l - 3), z, this.wood, this.woodMeta + 4);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z - 1, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 3), z + 1, this.wood, this.woodMeta + 8);
                            this.setBlockAndNotifyAdequately(world, x, y + (l - 2), z, this.wood, this.woodMeta);
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