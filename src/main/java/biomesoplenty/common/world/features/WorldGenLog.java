package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLog extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:         isAirBlock()
        while (!world.func_147437_c(x, y, z) && y > 2)
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
            
            //TODO:   isAirBlock()                               getBlock()
            if (!world.func_147437_c(x + ix, y, z + iz) || world.func_147439_a(x + ix, y - 1, z + iz) != Blocks.grass)
            {
                isAllowed = false;
                break;
            }
        }

        if (isAllowed)
        {
            for (int i = 0; i < length; i++)
            {
                if (direction == 0) world.func_147465_d(x + i, y, z, Blocks.log, 4, 2);
                else world.func_147465_d(x, y, z + i, Blocks.log, 8, 2);
            }
            
            return true;
        }
        
        return false;
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
