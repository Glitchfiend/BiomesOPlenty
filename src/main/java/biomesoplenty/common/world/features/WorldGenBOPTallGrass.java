package biomesoplenty.common.world.features;

import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

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
            block = world.getBlock(x, y, z);
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

            if (world.isAirBlock(i1, j1, k1) && this.tallGrass.canReplace(world, i1, j1, k1, 0, new ItemStack(this.tallGrass, 1, this.tallGrassMetadata)))
            {
                world.setBlock(i1, j1, k1, this.tallGrass, this.tallGrassMetadata, 2);
            }
        }

        return true;
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