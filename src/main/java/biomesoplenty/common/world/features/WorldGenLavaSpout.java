package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLavaSpout extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }

        Block block = world.getBlock(x, y, z);

        if (block != BOPCBlocks.ashStone)
        {
            return false;
        }
        else
        {
            for (int var7 = -2; var7 <= 2; ++var7)
            {
                for (int var8 = -2; var8 <= 2; ++var8)
                {
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8))
                    {
                        return false;
                    }
                }
            }

            world.setBlock(x, y - 1, z, Blocks.flowing_lava);
            world.setBlock(x, y, z, Blocks.flowing_lava);
            world.setBlock(x, y + 1, z, Blocks.flowing_lava);
            world.setBlock(x - 1, y + 1, z, Blocks.flowing_lava);
            world.setBlock(x + 1, y + 1, z, Blocks.flowing_lava);
            world.setBlock(x, y + 1, z - 1, Blocks.flowing_lava);
            world.setBlock(x, y + 1, z + 1, Blocks.flowing_lava);
            return true;
        }
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
