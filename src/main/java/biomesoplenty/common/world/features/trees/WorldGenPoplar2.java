package biomesoplenty.common.world.features.trees;

import java.util.Random;

import biomesoplenty.api.content.BOPCBlocks;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenPoplar2 extends WorldGenAbstractTree
{
    public WorldGenPoplar2()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        Block block = world.getBlock(x, y, z);

        if (block != Blocks.grass && block != BOPCBlocks.newBopGrass)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) && !world.isAirBlock(x + var7, y, z + var8))
                        return false;
                }
            }

            world.setBlock(x, y, z, Blocks.dirt);
            world.setBlock(x, y + 1, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 2, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 3, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 4, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 5, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 6, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 7, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 8, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 9, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 10, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 11, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 12, z, Blocks.log, 2, 2);
            world.setBlock(x, y + 13, z, Blocks.log, 2, 2);

            world.setBlock(x + 1, y + 7, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 7, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 7, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 7, z - 1, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 8, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 8, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 8, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 8, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 8, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 8, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 8, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 8, z - 1, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 9, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 9, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 9, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 9, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 9, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 9, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 9, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 9, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 2, y + 9, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 2, y + 9, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 9, z + 2, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 9, z - 2, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 10, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 10, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 10, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 10, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 10, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 10, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 10, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 10, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 2, y + 10, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 2, y + 10, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 10, z + 2, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 10, z - 2, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 11, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 11, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 11, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 11, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 11, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 11, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 11, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 11, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 2, y + 11, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 2, y + 11, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 11, z + 2, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 11, z - 2, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 12, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 12, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 12, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 12, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 12, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x + 1, y + 12, z - 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 12, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 12, z - 1, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 13, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 13, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 13, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 13, z - 1, Blocks.leaves, 2, 2);

            world.setBlock(x + 1, y + 14, z, Blocks.leaves, 2, 2);
            world.setBlock(x - 1, y + 14, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 14, z + 1, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 14, z - 1, Blocks.leaves, 2, 2);

            world.setBlock(x, y + 14, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 15, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 16, z, Blocks.leaves, 2, 2);
            world.setBlock(x, y + 17, z, Blocks.leaves, 2, 2);

            return true;
        }
    }
}
