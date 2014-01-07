package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBOPTallGrass extends WorldGeneratorBOP
{
    private Block tallGrass;
    private int tallGrassMetadata;

    public WorldGenBOPTallGrass(Block p_i45466_1_, int p_i45466_2_)
    {
        this.tallGrass = p_i45466_1_;
        this.tallGrassMetadata = p_i45466_2_;
    }

    @Override
	public boolean generate(World world, Random random, int x, int y, int z)
    {
        Block block;

        do
        {
            block = world.func_147439_a(x, y, z);
            if (!(block.isLeaves(world, x, y, z) || block.isAir(world, x, y, z)))
            {
                break;
            }
            --y;
        } while (y > 0);

        for (int l = 0; l < 128; ++l)
        {
            int i1 = x + random.nextInt(8) - random.nextInt(8);
            int j1 = y + random.nextInt(4) - random.nextInt(4);
            int k1 = z + random.nextInt(8) - random.nextInt(8);

            //TODO:	  isAirBlock()									canReplace()
            if (world.func_147437_c(i1, j1, k1) && this.tallGrass.func_149705_a(world, i1, j1, k1, 0, new ItemStack(this.tallGrass, 1, this.tallGrassMetadata)))
            {
                world.func_147465_d(i1, j1, k1, this.tallGrass, this.tallGrassMetadata, 2);
            }
        }

        return true;
    }
    
	@Override
	public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
	{
		for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
		{
			int randX = x + random.nextInt(16);
			int randZ = z + random.nextInt(16);
			int randY = random.nextInt(world.getHeightValue(randX, randZ));

			worldGenerator.generate(world, random, randX, randY, randZ);
		}
	}
}