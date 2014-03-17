package biomesoplenty.common.world.features;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenWasteland2 extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:      isAirBlock()
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        //TODO:             getBlock()
        Block block = world.getBlock(x, y, z);

        if (block != BOPBlockHelper.get("driedDirt"))
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    //TODO:  isAirBlock()                                               isAirBlock()                                                isAirBlock()
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) && !world.isAirBlock(x + var7, y, z + var8))
                    {
                        return false;
                    }
                }
            }

            world.setBlock(x, y, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y, z + 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 2, y, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y, z + 2, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 1, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 1, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 1, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 1, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 1, z + 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 1, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 1, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 1, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 1, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 2, y + 1, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y + 1, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y + 1, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 1, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 1, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 1, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 1, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 1, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 1, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 1, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 1, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 1, z + 2, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 2, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 2, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 2, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 2, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 2, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 2, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 2, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 2, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 2, y + 2, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y + 2, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 2, y + 2, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 2, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 2, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 2, y + 2, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 2, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 2, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 2, z - 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 2, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 2, z + 2, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 2, z + 2, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 2, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 3, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 3, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 3, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 3, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 3, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 3, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 3, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 3, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 3, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 4, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 4, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 4, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 4, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 4, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 4, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 4, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 4, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 4, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 5, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 5, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 5, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 5, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 5, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 5, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x - 1, y + 5, z + 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 5, z - 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 5, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 6, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 6, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 6, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 6, z + 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 6, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 7, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 7, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 7, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 7, z + 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 7, z, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x - 1, y + 8, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x + 1, y + 8, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 8, z - 1, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 8, z + 1, BOPBlockHelper.get("driedDirt"));

            world.setBlock(x, y + 8, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 9, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 10, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 11, z, BOPBlockHelper.get("driedDirt"));
            world.setBlock(x, y + 12, z, BOPBlockHelper.get("driedDirt"));

            return true;
        }
    }

    @Override
    public void setupGeneration(World world, Random random, BiomeGenBase biome, String featureName, int x, int z)
    {
        for (int i = 0; i < (Integer)BOPDecorationManager.getBiomeFeatures(biome.biomeID).getFeature(featureName); i++)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
