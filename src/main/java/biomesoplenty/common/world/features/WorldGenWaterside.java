package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
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
    public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
    {
        for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
        {
            int randX = x + random.nextInt(16) + 8;
            int randZ = z + random.nextInt(16) + 8;

            worldGenerator.generate(world, random, randX, world.getTopSolidOrLiquidBlock(randX, randZ), randZ);
        }
    }
}
