package biomesoplenty.common.world.features;

import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

import java.util.Random;

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

			//TODO:	  isAirBlock()
			if (world.isAirBlock(i1, j1, k1))
			{
				int l1 = 1 + par2Random.nextInt(par2Random.nextInt(3) + 1);

				for (int i2 = 0; i2 < l1; ++i2)
				{
					//TODO:							canReplace()
					if (BOPBlockHelper.get("plants").canReplace(world, i1, j1, k1, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 8)))
					{
						//TODO:	setBlock()
						world.setBlock(i1, j1 + i2, k1, BOPBlockHelper.get("plants"), 8, 2);
					}
				}
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
