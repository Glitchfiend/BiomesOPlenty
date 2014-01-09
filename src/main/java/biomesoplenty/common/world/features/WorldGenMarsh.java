package biomesoplenty.common.world.features;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenMarsh extends WorldGenerator
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        int var6 = x;

        for (int var7 = z; y < 63; ++y)
        {
            //TODO:             getBlock()
            Block block = world.func_147439_a(x, y - 1, z);

            if ((block == Blocks.water) && y < 256 - var6 - 1)
            {
                for (int var8 = 2; var8 <= 5; ++var8)
                {
                    //TODO: setBlock()
                    world.func_147449_b(x, y, z, Blocks.grass);
                    world.func_147449_b(x - 1, y, z, Blocks.grass);
                    world.func_147449_b(x + 1, y, z, Blocks.grass);
                    world.func_147449_b(x, y, z - 1, Blocks.grass);
                    world.func_147449_b(x, y, z + 1, Blocks.grass);
                    world.func_147449_b(x, y - 1, z, Blocks.dirt);
                    world.func_147449_b(x, y - 2, z, Blocks.dirt);
                    world.func_147449_b(x, y - 3, z, Blocks.dirt);
                    world.func_147449_b(x, y - 4, z, Blocks.dirt);
                    world.func_147449_b(x, y - 5, z, Blocks.dirt);
                    world.func_147449_b(x, y - 6, z, Blocks.dirt);
                    world.func_147449_b(x, y - 7, z, Blocks.dirt);
                    world.func_147449_b(x, y - 8, z, Blocks.dirt);
                    world.func_147449_b(x, y - 9, z, Blocks.dirt);
                    world.func_147449_b(x, y - 10, z, Blocks.dirt);
                    world.func_147449_b(x, y - 11, z, Blocks.dirt);
                    world.func_147449_b(x, y - 12, z, Blocks.dirt);
                    world.func_147449_b(x, y - 13, z, Blocks.dirt);
                    world.func_147449_b(x, y - 14, z, Blocks.dirt);
                    world.func_147449_b(x, y - 15, z, Blocks.dirt);
                    world.func_147449_b(x, y - 16, z, Blocks.dirt);
                    world.func_147449_b(x, y - 17, z, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 1, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 1, z, Blocks.dirt);
                    world.func_147449_b(x, y - 1, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 1, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 2, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 2, z, Blocks.dirt);
                    world.func_147449_b(x, y - 2, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 2, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 3, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 3, z, Blocks.dirt);
                    world.func_147449_b(x, y - 3, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 3, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 4, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 4, z, Blocks.dirt);
                    world.func_147449_b(x, y - 4, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 4, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 5, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 5, z, Blocks.dirt);
                    world.func_147449_b(x, y - 5, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 5, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 6, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 6, z, Blocks.dirt);
                    world.func_147449_b(x, y - 6, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 6, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 7, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 7, z, Blocks.dirt);
                    world.func_147449_b(x, y - 7, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 7, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 8, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 8, z, Blocks.dirt);
                    world.func_147449_b(x, y - 8, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 8, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 9, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 9, z, Blocks.dirt);
                    world.func_147449_b(x, y - 9, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 9, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 10, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 10, z, Blocks.dirt);
                    world.func_147449_b(x, y - 10, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 10, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 11, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 11, z, Blocks.dirt);
                    world.func_147449_b(x, y - 11, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 11, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 12, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 12, z, Blocks.dirt);
                    world.func_147449_b(x, y - 12, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 12, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 13, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 13, z, Blocks.dirt);
                    world.func_147449_b(x, y - 13, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 13, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 14, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 14, z, Blocks.dirt);
                    world.func_147449_b(x, y - 14, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 14, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 15, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 15, z, Blocks.dirt);
                    world.func_147449_b(x, y - 15, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 15, z + 1, Blocks.dirt);
                    world.func_147449_b(x - 1, y - 16, z, Blocks.dirt);
                    world.func_147449_b(x + 1, y - 16, z, Blocks.dirt);
                    world.func_147449_b(x, y - 16, z - 1, Blocks.dirt);
                    world.func_147449_b(x, y - 16, z + 1, Blocks.dirt);

                    if (random.nextInt(3) == 0)
                    {
                        Blocks.double_plant.func_149889_c(world, x, y + 1, z, 2, 2);
                        Blocks.double_plant.func_149889_c(world, x - 1 , y + 1, z, 2, 2);
                        Blocks.double_plant.func_149889_c(world, x + 1, y + 1, z, 2, 2);
                        Blocks.double_plant.func_149889_c(world, x, y + 1, z - 1, 2, 2);
                        Blocks.double_plant.func_149889_c(world, x, y + 1, z + 1, 2, 2);
                    }
                    else
                    {
                        world.func_147465_d(x, y + 1, z, Blocks.tallgrass, 1, 2);
                        world.func_147465_d(x - 1, y + 1, z, Blocks.tallgrass, 1, 2);
                        world.func_147465_d(x + 1, y + 1, z, Blocks.tallgrass, 1, 2);
                        world.func_147465_d(x, y + 1, z - 1, Blocks.tallgrass, 1, 2);
                        world.func_147465_d(x, y + 1, z + 1, Blocks.tallgrass, 1, 2);
                    }
                    break;
                }
            }
            else
            {
                x = var6 + random.nextInt(4) - random.nextInt(4);
                z = var7 + random.nextInt(4) - random.nextInt(4);
            }
        }

        return true;
    }
}