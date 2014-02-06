package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenRockpile extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        //TODO:      isAirBlock()
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
                    //TODO:   isAirBlock()                                      isAirBlock()
                    if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8))
                        return false;
                }
            }

            int var999 = random.nextInt(2);

            if (var999 == 0)
            {
                //TODO: setBlock()
                this.func_150515_a(world, x, y, z, Blocks.dirt);
                this.func_150515_a(world, x - 1, y, z, Blocks.dirt);
                this.func_150515_a(world, x + 1, y, z, Blocks.dirt);
                this.func_150515_a(world, x, y, z - 1, Blocks.dirt);
                this.func_150515_a(world, x, y, z + 1, Blocks.dirt);
                //TODO: setBlockAndMetadata()
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
                //TODO: setBlock()
                this.func_150515_a(world, x, y, z, Blocks.dirt);
                //TODO: setBlockAndMetadata()
                this.setBlockAndNotifyAdequately(world, x, y + 1, z, Blocks.stone, 0);
                return true;
            }

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
