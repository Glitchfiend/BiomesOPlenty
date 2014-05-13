package biomesoplenty.common.world.features;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenGrassSplatter extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block block;

        do
        {
            block = world.getBlock(x, y, z);
            if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        for (int var7 = 0; var7 < 128; ++var7)
        {
            int randX = x + random.nextInt(8) - random.nextInt(8);
            int randY = y + random.nextInt(4) - random.nextInt(4);
            int randZ = z + random.nextInt(8) - random.nextInt(8);

            int var999 = random.nextInt(5);

            //TODO:    isAirBlock()
            if (world.isAirBlock(randX, randY, randZ) && (world.getBlock(randX, randY - 1, randZ) == BOPBlockHelper.get("hardSand") || world.getBlock(randX, randY - 1, randZ) == BOPBlockHelper.get("mud") || world.getBlock(randX, randY - 1, randZ) == BOPBlockHelper.get("hardDirt") || world.getBlock(randX, randY - 1, randZ) == Blocks.sand || world.getBlock(randX, randY - 1, randZ) == Blocks.stone))
            {
                world.setBlock(randX, randY - 1, randZ, Blocks.grass, 0, 2);

                if (var999 == 0)
                {
                    world.setBlock(randX, randY, randZ, BOPBlockHelper.get("foliage"), 2, 2);
                }
                else if (var999 == 1)
                {
                    world.setBlock(randX, randY, randZ, BOPBlockHelper.get("foliage"), 10, 2);
                }
                else if (var999 == 2)
                {
                    world.setBlock(randX, randY, randZ, BOPBlockHelper.get("foliage"), 11, 2);
                }
                else
                {
                    world.setBlock(randX, randY, randZ, BOPBlockHelper.get("foliage"), 2, 2);
                }
            }
        }

        return true;
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
