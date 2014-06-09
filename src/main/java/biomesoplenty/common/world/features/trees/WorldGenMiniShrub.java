package biomesoplenty.common.world.features.trees;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenMiniShrub extends WorldGenAbstractTree
{
    private Block wood;
    private Block leaves;

    private int woodMeta;
    private int leavesMeta;

    private List soilBlocks;

    public WorldGenMiniShrub(Block wood, Block leaves, int woodMeta, int leavesMeta, Block... soilBlocks) 
    {
        super(false);

        this.wood = wood;
        this.leaves = leaves;

        this.leavesMeta = leavesMeta;
        this.woodMeta = woodMeta;

        this.soilBlocks = Arrays.asList(soilBlocks);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block block;

        do
        {
            block = world.getBlock(x, y, z);
            if (!(block == Blocks.netherrack || block == Blocks.bedrock || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        if (!soilBlocks.contains(block))
        {
            return false;
        }
        else
        {
            /*for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (world.isAirBlock(x + var7, y + 1, z + var8) && world.isAirBlock(x + var7, y + 2, z + var8))
                        return false;
                }
            }*/

            world.getBlock(x, y, z).onPlantGrow(world, x, y, z, x, y, z);
            
            world.setBlock(x, y + 1, z, wood, woodMeta, 2);
            world.setBlock(x, y + 2, z, wood, woodMeta, 2);
            world.setBlock(x + 1, y + 2, z, leaves, leavesMeta, 2);
            world.setBlock(x - 1, y + 2, z, leaves, leavesMeta, 2);
            world.setBlock(x, y + 2, z + 1, leaves, leavesMeta, 2);
            world.setBlock(x, y + 2, z - 1, leaves, leavesMeta, 2);
            world.setBlock(x, y + 3, z, leaves, leavesMeta, 2);
            return true;
        }
    }
}
