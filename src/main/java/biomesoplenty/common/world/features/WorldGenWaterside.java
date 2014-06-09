package biomesoplenty.common.world.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenWaterside extends WorldGeneratorBOP
{
    private List sideBlocks;
    
    private Block watersideBlock;
    private int radius;

    public WorldGenWaterside(Block watersideBlock, int radius, Block... sideBlocks)
    {
        super(true);
        
        this.watersideBlock = watersideBlock;
        this.radius = radius;
        
        this.sideBlocks = Arrays.asList(sideBlocks);
    }

    @Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
        if (world.getBlock(x, y, z).getMaterial() != Material.water)
        {
            return false;
        }
        else
        {
            int l = random.nextInt(this.radius - 2) + 2;
            byte b0 = 2;

            for (int i1 = x - l; i1 <= x + l; ++i1)
            {
                for (int j1 = z - l; j1 <= z + l; ++j1)
                {
                    int k1 = i1 - x;
                    int l1 = j1 - z;

                    if (k1 * k1 + l1 * l1 <= l * l)
                    {
                        for (int i2 = y - b0; i2 <= y + b0; ++i2)
                        {
                            Block block = world.getBlock(i1, i2, j1);

                            if (sideBlocks.contains(block))
                            {
                                world.setBlock(i1, i2, j1, this.watersideBlock, 0, 2);
                            }
                        }
                    }
                }
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

            this.generate(world, random, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }
}
