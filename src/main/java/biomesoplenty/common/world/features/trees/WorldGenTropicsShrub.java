package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenTropicsShrub extends WorldGenAbstractTree
{
    public WorldGenTropicsShrub()
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

        if (block != Blocks.grass && block != Blocks.sand)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    //TODO:     isAirBlock()                                    isAirBlock()
                    if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8))
                    {
                        return false;
                    }
                }
            }

            //TODO: setBlock()
            world.func_147449_b(x, y, z, Blocks.dirt);
            world.func_147465_d(x, y + 1, z, Blocks.log, 3, 2);
            world.func_147465_d(x, y + 2, z, Blocks.log, 3, 2);
            world.func_147465_d(x + 1, y + 2, z, Blocks.leaves, 3, 2);
            world.func_147465_d(x - 1, y + 2, z, Blocks.leaves, 3, 2);
            world.func_147465_d(x, y + 2, z + 1, Blocks.leaves, 3, 2);
            world.func_147465_d(x, y + 2, z - 1, Blocks.leaves, 3, 2);
            world.func_147465_d(x, y + 3, z, Blocks.leaves, 3, 2);

            int var999 = random.nextInt(12);
            int var998 = random.nextInt(4);

            if (var998 == 0)
            {
                if (var999 == 0)
                {
                    world.func_147465_d(x - 1, y + 1, z, Blocks.cocoa, 11, 2);
                }
                if (var999 == 1)
                {
                    world.func_147465_d(x + 1, y + 1, z, Blocks.cocoa, 9, 2);
                }
                if (var999 == 2)
                {
                    world.func_147465_d(x, y + 1, z - 1, Blocks.cocoa, 8, 2);
                }
                if (var999 == 3)
                {
                    world.func_147465_d(x, y + 1, z + 1, Blocks.cocoa, 10, 2);
                }
            }
            else if (var998 == 1)
            {
                if (var999 == 0)
                {
                    world.func_147465_d(x - 1, y + 1, z, Blocks.cocoa, 7, 2);
                }
                if (var999 == 1)
                {
                    world.func_147465_d(x + 1, y + 1, z, Blocks.cocoa, 5, 2);
                }
                if (var999 == 2)
                {
                    world.func_147465_d(x, y + 1, z - 1, Blocks.cocoa, 4, 2);
                }
                if (var999 == 3)
                {
                    world.func_147465_d(x, y + 1, z + 1, Blocks.cocoa, 6, 2);
                }
            }
            else if (var998 == 2)
            {
                if (var999 == 0)
                {
                    world.func_147465_d(x - 1, y + 1, z, Blocks.cocoa, 3, 2);
                }
                if (var999 == 1)
                {
                    world.func_147465_d(x + 1, y + 1, z, Blocks.cocoa, 1, 2);
                }
                if (var999 == 2)
                {
                    world.func_147465_d(x, y + 1, z - 1, Blocks.cocoa, 0, 2);
                }
                if (var999 == 3)
                {
                    world.func_147465_d(x, y + 1, z + 1, Blocks.cocoa, 2, 2);
                }
            }
            return true;
        }
    }
}
