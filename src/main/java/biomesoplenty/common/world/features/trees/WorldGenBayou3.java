package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import net.minecraftforge.common.util.ForgeDirection;

public class WorldGenBayou3 extends WorldGenAbstractTree
{
    private final Block wood;

    private final int metaWood;

    public WorldGenBayou3(Block wood, int metaWood)
    {
        this(wood, metaWood, false);
    }

    public WorldGenBayou3(Block wood, int metaWood, boolean doBlockNotify)
    {
        super(doBlockNotify);

        this.wood = wood;
        this.metaWood = metaWood;
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int l;

        for (l = 7; world.getBlock(x, y - 1, z).getMaterial() == Material.water; --y)
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

                    this.setBlockAndNotifyAdequately(world, x - 1, y, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x + 1, y, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y, z - 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y, z + 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x - 1, y + 1, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 1, z - 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 1, z + 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x - 1, y + 2, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x + 1, y + 2, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 2, z - 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 2, z + 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x - 1, y + 3, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x + 1, y + 3, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 3, z - 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 3, z + 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x - 1, y + 4, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x + 1, y + 4, z, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 4, z - 1, this.wood, this.metaWood);
                    this.setBlockAndNotifyAdequately(world, x, y + 4, z + 1, this.wood, this.metaWood);

                    int l1;
                    int l2;
                    int k2;

                    for (k2 = 0; k2 < l; ++k2)
                    {
                        Block block2 = world.getBlock(x, y + k2, z);

                        if (block2.isAir(world, x, y + k2, z) || block2.isLeaves(world, x, y + k2, z) || block2 == Blocks.flowing_water || block2 == Blocks.water)
                        {
                            this.setBlockAndNotifyAdequately(world, x, y + k2, z, this.wood, this.metaWood);
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
