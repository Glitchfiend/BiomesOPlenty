package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenOvergrownNetherrack extends WorldGeneratorBOP
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

            if (world.isAirBlock(randX, randY, randZ) && (world.getBlock(randX, randY - 1, randZ) == Blocks.netherrack))
            {
                world.setBlock(randX, randY - 1, randZ, BOPCBlocks.overgrownNetherrack, 0, 2);

                if (var999 == 0)
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 2, 2);
                }
                else if (var999 == 1)
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 10, 2);
                }
                else if (var999 == 2)
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 11, 2);
                }
                else
                {
                    world.setBlock(randX, randY, randZ, BOPCBlocks.foliage, 2, 2);
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
