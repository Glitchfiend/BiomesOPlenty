package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenXericSplatter extends WorldGeneratorBOP
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

            int var999 = random.nextInt(8);

            if (world.isAirBlock(randX, randY, randZ) && (world.getBlock(randX, randY - 1, randZ) == BOPCBlocks.hardSand || world.getBlock(randX, randY - 1, randZ) == BOPCBlocks.mud || world.getBlock(randX, randY - 1, randZ) == BOPCBlocks.hardDirt || world.getBlock(randX, randY - 1, randZ) == Blocks.sand || world.getBlock(randX, randY - 1, randZ) == Blocks.stone))
            {
                world.setBlock(randX, randY - 1, randZ, Blocks.grass, 0, 2);

                if (var999 == 0)
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.flowers, 7, 2);
                }
                else if (var999 == 2)
                {
                    world.setBlock(randX, randY, randZ, Blocks.red_flower, 0, 2);
                }
                else if (var999 == 3)
                {
                    world.setBlock(randX, randY, randZ, Blocks.yellow_flower, 0, 2);
                }
                else if (var999 == 5)
                {
                    world.setBlock(randX, randY, randZ, Blocks.red_flower, 3, 2);
                }
                else if (var999 == 7)
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 9, 2);
                }
                else
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 4, 2);
                }
            }
        }

        return true;
    }

    @Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
        for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;
            int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

            this.generate(world, random, randX, randY, randZ);
        }
    }
}
