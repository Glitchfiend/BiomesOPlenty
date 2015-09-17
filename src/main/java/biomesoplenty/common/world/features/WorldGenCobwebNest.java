package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.entity.monster.EntitySpider;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenCobwebNest extends WorldGeneratorBOP
{
    @Override
    public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
    {
        Block var15;

        for (boolean var6 = false; ((var15 = par1World.getBlock(par3, par4, par5)) == Blocks.air || var15 == Blocks.web) && par4 > 0; --par4)
        {
            ;
        }

        Block block = par1World.getBlock(par3, par4, par5);

        if (block == Blocks.grass)
        {
            ++par4;
            this.setBlockAndNotifyAdequately(par1World, par3, par4, par5, Blocks.web, 0);

            int var999 = par2Random.nextInt(3);

            if (var999 == 0)
            {
                EntitySpider spider = new EntitySpider(par1World);
                spider.setLocationAndAngles((double)par3, (double)par4 + 1, (double)par5, 0.0F, 0.0F);
                par1World.spawnEntityInWorld(spider);
            }

            for (int var8 = par4; var8 <= par4 + 1; ++var8)
            {
                int var9 = var8 - par4;
                int var10 = 2 - var9;

                for (int var11 = par3 - var10; var11 <= par3 + var10; ++var11)
                {
                    int var12 = var11 - par3;

                    for (int var13 = par5 - var10; var13 <= par5 + var10; ++var13)
                    {
                        int var14 = var13 - par5;

                        if (Math.abs(var12) != var10 || Math.abs(var14) != var10 || par2Random.nextInt(2) != 0)
                        {
                            this.setBlockAndNotifyAdequately(par1World, var11, var8, var13, Blocks.web, 0);
                        }
                    }
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
