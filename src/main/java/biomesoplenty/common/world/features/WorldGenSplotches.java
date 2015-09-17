package biomesoplenty.common.world.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.MathHelper;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenSplotches extends WorldGeneratorBOP
{
    private Block splotchBlock;
    private int splotchBlockMeta;

    private int numberOfBlocks;

    private List blockList;

    public WorldGenSplotches(Block quicksandBlock, int quicksandBlockMeta, int numberOfBlocks, Block... blockList)
    {
        super(true);

        this.splotchBlock = quicksandBlock;
        this.splotchBlockMeta = quicksandBlockMeta;
        this.numberOfBlocks = numberOfBlocks;

        this.blockList = Arrays.asList(blockList);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        float var6 = random.nextFloat() * (float)Math.PI;
        double var7 = x + 8 + MathHelper.sin(var6) * numberOfBlocks / 8.0F;
        double var9 = x + 8 - MathHelper.sin(var6) * numberOfBlocks / 8.0F;
        double var11 = z + 8 + MathHelper.cos(var6) * numberOfBlocks / 8.0F;
        double var13 = z + 8 - MathHelper.cos(var6) * numberOfBlocks / 8.0F;
        double var15 = y + random.nextInt(3) - 2;
        double var17 = y + random.nextInt(3) - 2;

        for (int var19 = 0; var19 <= numberOfBlocks; ++var19)
        {
            double var20 = var7 + (var9 - var7) * var19 / numberOfBlocks;
            double var22 = var15 + (var17 - var15) * var19 / numberOfBlocks;
            double var24 = var11 + (var13 - var11) * var19 / numberOfBlocks;
            double var26 = random.nextDouble() * numberOfBlocks / 16.0D;
            double var28 = (MathHelper.sin(var19 * (float)Math.PI / numberOfBlocks) + 1.0F) * var26 + 1.0D;
            double var30 = (MathHelper.sin(var19 * (float)Math.PI / numberOfBlocks) + 1.0F) * var26 + 1.0D;
            int var32 = MathHelper.floor_double(var20 - var28 / 2.0D);
            int var33 = MathHelper.floor_double(var22 - var30 / 2.0D);
            int var34 = MathHelper.floor_double(var24 - var28 / 2.0D);
            int var35 = MathHelper.floor_double(var20 + var28 / 2.0D);
            int var36 = MathHelper.floor_double(var22 + var30 / 2.0D);
            int var37 = MathHelper.floor_double(var24 + var28 / 2.0D);

            for (int var38 = var32; var38 <= var35; ++var38)
            {
                double var39 = (var38 + 0.5D - var20) / (var28 / 2.0D);

                if (var39 * var39 < 1.0D)
                {
                    for (int var41 = var33; var41 <= var36; ++var41)
                    {
                        double var42 = (var41 + 0.5D - var22) / (var30 / 2.0D);

                        if (var39 * var39 + var42 * var42 < 1.0D)
                        {
                            for (int var44 = var34; var44 <= var37; ++var44)
                            {
                                double var45 = (var44 + 0.5D - var24) / (var28 / 2.0D);

                                if (var39 * var39 + var42 * var42 + var45 * var45 < 1.0D && world.getBlock(var38, var41, var44) != Blocks.air && (blockList.contains(world.getBlock(var38, var41, var44))))
                                {
                                    this.setBlockAndNotifyAdequately(world, var38, var41, var44, splotchBlock, splotchBlockMeta);
                                }
                            }
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
        if (featureName.equals("generateQuicksand") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateQuicksand"))
        {
            for (int i = 0; i < 5; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateCanyon") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateCanyon"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateStoneInGrass") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateStoneInGrass"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateStoneInGrass2") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateStoneInGrass2"))
        {
            for (int i = 0; i < 20; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateGrass") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateGrass"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateSand") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateSand"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateQuagmire") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateQuagmire"))
        {
            for (int i = 0; i < 15; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64) + 64;
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateAsh") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateAsh"))
        {
            for (int i = 0; i < 10; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateMycelium") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateMycelium"))
        {
            for (int i = 0; i < 10; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(128);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
        else if (featureName.equals("generateSponge") && (Boolean)biome.theBiomeDecorator.bopFeatures.getFeature("generateSponge"))
        {
            for (int i = 0; i < 5; ++i)
            {
                int randX = x + random.nextInt(16);
                int randY = random.nextInt(64);
                int randZ = z + random.nextInt(16);

                this.generate(world, random, randX, randY, randZ);
            }
        }
    }
}
