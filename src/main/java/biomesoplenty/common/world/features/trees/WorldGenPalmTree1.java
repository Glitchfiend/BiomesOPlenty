package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;

public class WorldGenPalmTree1 extends WorldGenAbstractTree
{
    public WorldGenPalmTree1()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:        isAirBlock()
        while (world.func_147437_c(x, y, z) && y > 2)
        {
            --y;
        }

        //TODO:           getBlock()
        Block block = world.func_147439_a(x, y, z);

        if (block != Blocks.grass)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    //TODO: isAirBlock()                                                isAirBlock()                                               isAirBlock()
                    if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8) && !world.func_147437_c(x + var7, y, z + var8))
                    {
                        return false;
                    }
                }
            }

            //settings========
                    double strength = random.nextInt(35) / 100D;
            //================

                    double xoff = 0;
            double yoff = 0;
            int r = random.nextInt(4);
            if(r == 0) { xoff = strength; }
            else if(r == 1) { xoff = -strength; }
            else if(r == 2) { yoff = strength; }
            else { yoff = -strength; }

            int h = 1;
            buildBlock(world, x, y, z, Blocks.dirt, 0);
            for(int b = 0; b < 10; b++)
            {
                buildBlock(world, x + ((int) Math.floor(xoff)), y + h, z + ((int) Math.floor(yoff)), BOPBlockHelper.get("logs2"), 3);
                if(b == 9)
                {
                    generateTop(world, x + ((int) Math.floor(xoff)), y + h, z + ((int) Math.floor(yoff)));
                }
                else
                {
                    h++;
                    xoff *= 1.3D;
                    yoff *= 1.3D;
                }
            }

            return true;
        }
    }

    public void generateTop(World world, int x, int y, int z)
    {
        buildBlock(world, x + 2, y - 1, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 2, y - 1, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y - 1, z + 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y - 1, z - 2, BOPBlockHelper.get("colorizedLeaves1"), 2);

        buildBlock(world, x + 1, y, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 1, y, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y, z + 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y, z - 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x + 2, y, z + 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 2, y, z - 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x + 2, y, z - 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 2, y, z + 2, BOPBlockHelper.get("colorizedLeaves1"), 2);

        buildBlock(world, x + 1, y + 1, z - 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 1, y + 1, z + 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x + 1, y + 1, z + 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 1, y + 1, z - 1, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y + 1, z, BOPBlockHelper.get("colorizedLeaves1"), 2);

        buildBlock(world, x + 2, y + 2, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x - 2, y + 2, z, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y + 2, z + 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
        buildBlock(world, x, y + 2, z - 2, BOPBlockHelper.get("colorizedLeaves1"), 2);
    }

    public void buildBlock(World world, int x, int y, int z, Block block, int meta)
    {
        world.func_147465_d(x, y, z, block, meta, 2);
    }
}
