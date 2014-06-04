package biomesoplenty.common.world.features.nether;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenGrave extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		while (world.isAirBlock(x, y, z) && y > 2)
		{
			--y;
		}

		Block block = world.getBlock(x, y, z);

		if (block != Blocks.netherrack && block != Blocks.soul_sand)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8) && !world.isAirBlock(x + var7, y, z + var8))
						return false;
				}
			}

			int orientation = random.nextInt(4);

			if (orientation == 0 || orientation == 1)
			{
				world.setBlock(x, y + 1, z, BOPCBlocks.grave, 0, 2);
				world.setBlock(x, y + 2, z, BOPCBlocks.grave, 1, 2);
			}
			else
			{
				world.setBlock(x, y + 1, z, BOPCBlocks.grave, 2, 2);
				world.setBlock(x, y + 2, z, BOPCBlocks.grave, 3, 2);
			}

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
			int randY = random.nextInt(256);

            this.generate(world, random, randX, randY, randZ);
		}
	}
}
