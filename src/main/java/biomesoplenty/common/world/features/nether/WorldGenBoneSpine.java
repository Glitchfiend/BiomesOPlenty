package biomesoplenty.common.world.features.nether;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenBoneSpine extends WorldGeneratorBOP
{
	private boolean downwards;
	
	public WorldGenBoneSpine(boolean downwards)
	{
		this.downwards = downwards;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		if (!world.isRemote)
		{
			int signum = downwards ? -1 : 1;

			while (y > 2 && y < 254)
			{
				if (!world.isAirBlock(x, y + -signum, z)) break;

				y += -signum;
			}
			
			if (world.getBlock(x, y + -signum, z) == Blocks.netherrack)
			{
				int height = rand.nextInt(6) + 6;
				int layerY = 0;

				if (height % 2 == 0) height++;
				
				boolean hasSpace = true;
				
				for (int ix = -2; ix <= 2; ix++)
				{
					for (int iy = 0; iy < height; iy++)
					{
						for (int iz = -2; iz <= 2; iz++)
						{
							if (!world.isAirBlock(x + ix, y + (iy * signum), z + iz))
							{
								hasSpace = false;
								break;
							}
						}
					}
				}

				if (hasSpace)
				{
					for (int layer = 0; layer < height; layer++)
					{
						layerY = y + (layer * signum);

						if (layer < height - 2)
						{
							if (layer % 2 == 0) createJunction(world, x, layerY, z);
							else world.setBlock(x, layerY, z, BOPCBlocks.bones, 1, 2);
						}
						else
						{
							world.setBlock(x, layerY, z, BOPCBlocks.bones, layer % 2, 2);
						}
					}
				}
			}
		}

		return false;
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
	
	private void createJunction(World world, int x, int y, int z)
	{
		world.setBlock(x, y, z, BOPCBlocks.bones, 2, 2);
		
		world.setBlock(x - 1, y, z, BOPCBlocks.bones, 6, 2);
		world.setBlock(x + 1, y, z, BOPCBlocks.bones, 6, 2);
		world.setBlock(x, y, z - 1, BOPCBlocks.bones, 5, 2);
		world.setBlock(x, y, z + 1, BOPCBlocks.bones, 5, 2);
		
		world.setBlock(x - 2, y, z, BOPCBlocks.bones, 4, 2);
		world.setBlock(x + 2, y, z, BOPCBlocks.bones, 4, 2);
		world.setBlock(x, y, z - 2, BOPCBlocks.bones, 3, 2);
		world.setBlock(x, y, z + 2, BOPCBlocks.bones, 3, 2);
	}
}
