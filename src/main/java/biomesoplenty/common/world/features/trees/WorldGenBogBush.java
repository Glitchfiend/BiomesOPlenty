package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

public class WorldGenBogBush extends WorldGenAbstractTree
{
    public WorldGenBogBush()
    {
        super(false);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:     isAirBlock()
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
                    //TODO:    isAirBlock()                                     isAirBlock()
                    if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8))
                        return false;
                }
            }

            //TODO: setBlockAndMetadata()
            this.func_150516_a(world, x, y + 1, z, Blocks.leaves, 4);
            return true;
        }
    }
}
