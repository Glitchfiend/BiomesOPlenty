package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenWaterReeds extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int var6 = 0; var6 < 128; ++var6)
		{
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(2) - random.nextInt(2);
			int k1 = z + random.nextInt(8) - random.nextInt(8);
			
			if (world.isAirBlock(i1, j1, k1) && BOPCBlocks.plants.canReplace(world, i1, j1, k1, 0, new ItemStack(BOPCBlocks.plants, 1, 14)))
			{
				for (int i = 4; i > -4; --i)
				{
					if (world.getBlock(i1 - i, j1 - 1, k1 - i) != Blocks.water)
					{
						world.setBlock(i1, j1, k1, BOPCBlocks.plants, 14, 2);
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