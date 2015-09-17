package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLog extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        while (!world.isAirBlock(x, y, z) && y > 2)
        {
            ++y;
        }

        int length = 3 + random.nextInt(3);
        int direction = random.nextInt(2);
        boolean isAllowed = true;

        for (int i = 0; i < length; i++)
        {
            int ix = 0;
            int iz = 0;
            
            if (direction == 0) ix = i;
            else iz = i;
            
            if (!world.isAirBlock(x + ix, y, z + iz) || world.getBlock(x + ix, y - 1, z + iz) != Blocks.grass)
            {
                isAllowed = false;
                break;
            }
        }

        if (isAllowed)
        {
            for (int i = 0; i < length; i++)
            {
                if (direction == 0) world.setBlock(x + i, y, z, Blocks.log, 4, 2);
                else world.setBlock(x, y, z + i, Blocks.log, 8, 2);
            }
            
            return true;
        }
        
        return false;
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
