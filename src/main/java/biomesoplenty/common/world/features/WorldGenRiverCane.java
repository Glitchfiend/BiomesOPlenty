package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenRiverCane extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random par2Random, int x, int y, int z)
	{
		for (int l = 0; l < 5; ++l)
		{
			int i1 = x + par2Random.nextInt(8) - par2Random.nextInt(8);
			int j1 = y;
			int k1 = z + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (world.isAirBlock(i1, j1, k1))
			{
				int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

				for (int i2 = 0; i2 < l1; ++i2)
				{
					if (BOPCBlocks.plants.canReplace(world, i1, j1, k1, 0, new ItemStack(BOPCBlocks.plants, 1, 8)))
					{
						world.setBlock(i1, j1 + i2, k1, BOPCBlocks.plants, 8, 2);
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
