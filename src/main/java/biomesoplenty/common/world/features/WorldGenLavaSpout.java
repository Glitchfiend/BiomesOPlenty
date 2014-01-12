package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLavaSpout extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:      isAirBlock()
        while (world.func_147437_c(x, y, z) && y > 2)
        {
            --y;
        }

        //TODO:             getBlock()
        Block block = world.func_147439_a(x, y, z);

        if (block != BOPBlockHelper.get("ashStone"))
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    //TODO:   isAirBlock()                                      isAirBlock()
                    if (world.func_147437_c(x + var7, y - 1, z + var8) && world.func_147437_c(x + var7, y - 2, z + var8))
                    {
                        return false;
                    }
                }
            }

            //TODO: setBlock()
            world.func_147449_b(x, y - 1, z, Blocks.flowing_lava);
            world.func_147449_b(x, y, z, Blocks.flowing_lava);
            world.func_147449_b(x, y + 1, z, Blocks.flowing_lava);
            world.func_147449_b(x - 1, y + 1, z, Blocks.flowing_lava);
            world.func_147449_b(x + 1, y + 1, z, Blocks.flowing_lava);
            world.func_147449_b(x, y + 1, z - 1, Blocks.flowing_lava);
            world.func_147449_b(x, y + 1, z + 1, Blocks.flowing_lava);
            return true;
        }
    }
    
    @Override
    public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
    {
        for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            worldGenerator.generate(world, random, randX, randY, randZ);
        }
    }
}
