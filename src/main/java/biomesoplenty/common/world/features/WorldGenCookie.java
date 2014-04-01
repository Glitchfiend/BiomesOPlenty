package biomesoplenty.common.world.features;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

public class WorldGenCookie extends WorldGeneratorBOP
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
                if (world.getBlock(x + ix, y, z + iz) != BOPBlockHelper.get("frostedCake"))
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
   
            //TODO: setBlockAndMetadata()
            this.func_150515_a(world, x, y + 1, z, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x - 1, y + 1, z, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x - 1, y + 1, z - 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x - 1, y + 1, z + 1, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x + 1, y + 1, z, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 1, y + 1, z - 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 1, y + 1, z + 1, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x, y + 1, z - 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x, y + 1, z + 1, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x - 2, y + 1, z, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 2, y + 1, z, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x, y + 1, z - 2, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x, y + 1, z + 2, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x - 2, y + 1, z - 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 2, y + 1, z + 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x - 1, y + 1, z - 2, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 1, y + 1, z + 2, BOPBlockHelper.get("cookieBlock"));
            
            this.func_150515_a(world, x - 1, y + 1, z + 2, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 1, y + 1, z - 2, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x - 2, y + 1, z + 1, BOPBlockHelper.get("cookieBlock"));
            this.func_150515_a(world, x + 2, y + 1, z - 1, BOPBlockHelper.get("cookieBlock"));

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
