package biomesoplenty.common.world.features.nether;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.init.Blocks;
import net.minecraft.world.EnumSkyBlock;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLakesNether extends WorldGeneratorBOP
{
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		x -= 8;

		for (z -= 8; y > 5 && world.isAirBlock(x, y, z); --y)
		{
			;
		}

		if (y <= 4)
		{
			return false;
		}
		else
		{
			y -= 4;
			boolean[] aboolean = new boolean[2048];
			int l = rand.nextInt(4) + 4;
			int i1;

			for (i1 = 0; i1 < l; ++i1)
			{
				double d0 = rand.nextDouble() * 6.0D + 3.0D;
				double d1 = rand.nextDouble() * 4.0D + 2.0D;
				double d2 = rand.nextDouble() * 6.0D + 3.0D;
				double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
				double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
				double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

				for (int k1 = 1; k1 < 15; ++k1)
				{
					for (int l1 = 1; l1 < 15; ++l1)
					{
						for (int i2 = 1; i2 < 7; ++i2)
						{
							double d6 = ((double)k1 - d3) / (d0 / 2.0D);
							double d7 = ((double)i2 - d4) / (d1 / 2.0D);
							double d8 = ((double)l1 - d5) / (d2 / 2.0D);
							double d9 = d6 * d6 + d7 * d7 + d8 * d8;

							if (d9 < 1.0D)
							{
								aboolean[(k1 * 16 + l1) * 8 + i2] = true;
							}
						}
					}
				}
			}

			int j1;
			int j2;
			boolean flag;

			for (i1 = 0; i1 < 16; ++i1)
			{
				for (j2 = 0; j2 < 16; ++j2)
				{
					for (j1 = 0; j1 < 8; ++j1)
					{
						flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

						if (flag)
						{
							Material material = world.getBlock(x + i1, y + j1, z + j2).getMaterial();

							if (j1 >= 4 && material.isLiquid())
							{
								return false;
							}

							if (j1 < 4 && !material.isSolid() && world.getBlock(x + i1, y + j1, z + j2) != Blocks.lava)
							{
								return false;
							}
						}
					}
				}
			}

			for (i1 = 0; i1 < 16; ++i1)
			{
				for (j2 = 0; j2 < 16; ++j2)
				{
					for (j1 = 0; j1 < 8; ++j1)
					{
						if (aboolean[(i1 * 16 + j2) * 8 + j1])
						{
							world.setBlock(x + i1, y + j1, z + j2, j1 >= 4 ? Blocks.air : Blocks.lava, 0, 2);
						}
					}
				}
			}

			for (i1 = 0; i1 < 16; ++i1)
			{
				for (j2 = 0; j2 < 16; ++j2)
				{
					for (j1 = 0; j1 < 8; ++j1)
					{
						flag = !aboolean[(i1 * 16 + j2) * 8 + j1] && (i1 < 15 && aboolean[((i1 + 1) * 16 + j2) * 8 + j1] || i1 > 0 && aboolean[((i1 - 1) * 16 + j2) * 8 + j1] || j2 < 15 && aboolean[(i1 * 16 + j2 + 1) * 8 + j1] || j2 > 0 && aboolean[(i1 * 16 + (j2 - 1)) * 8 + j1] || j1 < 7 && aboolean[(i1 * 16 + j2) * 8 + j1 + 1] || j1 > 0 && aboolean[(i1 * 16 + j2) * 8 + (j1 - 1)]);

						if (flag && (j1 < 4 || rand.nextInt(2) != 0) && world.getBlock(x + i1, y + j1, z + j2).getMaterial().isSolid())
						{
							world.setBlock(x + i1, y + j1, z + j2, Blocks.netherrack, 0, 2);
						}
					}
				}
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
    		int randY = random.nextInt(random.nextInt(random.nextInt(112) + 8) + 8);
    		int randZ = z + random.nextInt(16) + 8;

    		this.generate(world, random, randX, randY, randZ);
    	}
    }
}
