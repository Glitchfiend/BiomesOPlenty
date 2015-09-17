package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenRockpile extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (world.isAirBlock(x, y, z) && y > 2)
        {
            --y;
        }
        
        boolean isGrass = true;
        
        for (int ix = -1; ix <= 1; ix++)
        {
            for (int iz = -1; iz <= 1; iz++)
            {
                if (world.getBlock(x + ix, y, z + iz) != Blocks.grass)
                {
                    isGrass = false;
                    break;
                }
            }
        }

        if (!isGrass)
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
                        return false;
                }
            }

            int var999 = random.nextInt(2);

            if (var999 == 0)
            {
                this.func_150515_a(world, x, y, z, Blocks.dirt);
                this.func_150515_a(world, x - 1, y, z, Blocks.dirt);
                this.func_150515_a(world, x + 1, y, z, Blocks.dirt);
                this.func_150515_a(world, x, y, z - 1, Blocks.dirt);
                this.func_150515_a(world, x, y, z + 1, Blocks.dirt);

                this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.stone, 0);
                this.setBlockAndNotifyAdequately(world, x + 1, y + 1, z, Blocks.stone, 0);
                this.setBlockAndNotifyAdequately(world, x - 1, y + 1, z, Blocks.stone, 0);
                this.setBlockAndNotifyAdequately(world, x, y + 1, z + 1, Blocks.stone, 0);
                this.setBlockAndNotifyAdequately(world, x, y + 1, z - 1, Blocks.stone, 0);
                this.setBlockAndNotifyAdequately(world, x, y + 2, z, Blocks.stone, 0);
                return true;
            }
            else if (var999 == 1)
            {
                this.func_150515_a(world, x, y, z, Blocks.dirt);
                this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.stone, 0);
                return true;
            }

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
