package biomesoplenty.common.world.features.nether;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.tileentity.TileEntityMobSpawner;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;
import biomesoplenty.common.configuration.BOPConfigurationTerrainGen;

public class WorldGenWaspHive extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{ 
		if (!BOPConfigurationTerrainGen.genWaspHives) return false;

		int baseWidth = 4 + rand.nextInt(2);
		int baseHeight = 8 + rand.nextInt(2);

		for (int air = 0; air < 26; air++)
		{
			if (world.getBlock(x, y + 3, z) != Blocks.netherrack || !world.isAirBlock(x, (y + 2) - air, z))
			{
				return false;
			}
		}

		for (int cubeno = 0; cubeno < 4; cubeno++)
		{
			float chance = 0.0F;
			int meta = 0;

			switch (cubeno)
			{
			case 0:
				chance = 0.25F;
				meta = 0;
				break;

			case 1:
				chance = 1.0F;
				meta = 0;
				break;

			case 2:
				chance = 1.0F;
				meta = 1;
				break;

			case 3:
				chance = 0.5F;
				meta = 1;
				break;
			}

			int honeychance = rand.nextInt(2);

			//Top
			generateHiveCubeSmall(world, x, y + cubeno, z, (baseHeight - 11) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance, meta);

			//Middle
			generateHiveCube(world, x, (y - 2) + cubeno, z, baseHeight + (cubeno * 2), baseWidth + cubeno, cubeno, chance, honeychance, meta);

			//Bottom
			generateHiveCubeSmall(world, x, (y - (baseHeight + 6)) + cubeno, z, (baseHeight - 10) + (cubeno * 2), (baseWidth - 1) + cubeno, cubeno, chance, meta);

			//Bottom 2
			generateHiveCubeSmall(world, x, (y - (baseHeight + 7)) + cubeno, z, (baseHeight - 9) + (cubeno * 2), (baseWidth - 2) + cubeno, cubeno, chance, meta);

			//Bottom 3
			generateHiveCubeSmall(world, x, (y - (baseHeight + 9)) + cubeno, z, (baseHeight - 9) + (cubeno * 2), (baseWidth - 4) + cubeno, cubeno, chance, meta);

			spawnWasps(world, rand, x, y, z);

			spawnEmptyHoneycombs(world, rand, x, y, z);

			spawnFilledHoneycombs(world, rand, x, y, z);
		}

		return true;
	}
	
	@Override
    public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
    {
		 for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); ++i)
		 {
			 int j = random.nextInt(4);

			 if (j != 0)
			 {
				 int randX = x + random.nextInt(16) + 8;
				 int randY = random.nextInt(64) + 50;
				 int randZ = z + random.nextInt(16) + 8;
				 
				 this.generate(world, random, randX, randY, randZ);
			 }
		 }
    }

	public void generateHiveCube(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance, int honeychance, int meta)
	{
		for (int hLayer = 0; hLayer < height; hLayer++)
		{     
			for (int i = -width; i < width; i++)
			{
				for (int j = -width; j < width; j++)
				{
					if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.hive, meta, 2); 
					else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.hive, meta, 2);

					if (hLayer > (height / 2))
					{
						if (honeychance == 0)
						{
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) != BOPCBlocks.hive) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.honey, 7, 2);
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) == BOPCBlocks.hive && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.honey, 7, 2);
						}
						else
						{
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) != BOPCBlocks.hive) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) == BOPCBlocks.hive && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
						}
					}
					else
					{
						if (honeychance == 0)
						{
							if (hLayer == (height / 2))
							{
								if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) != BOPCBlocks.hive) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.honeyBlock, 0, 2);
								if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) == BOPCBlocks.hive && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.honeyBlock, 0, 2);
							}
							else
							{
								if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) != BOPCBlocks.hive) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
								if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) == BOPCBlocks.hive && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
							}
						}
						else
						{
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) != BOPCBlocks.hive) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
							if (cubeno < 2 && world.getBlock(origx + i, origy - hLayer, origz + j) == BOPCBlocks.hive && world.getBlockMetadata(origx + i, origy - hLayer, origz + j) != 0) world.setBlockToAir(origx + i, origy - hLayer, origz + j);
						}
					}
				}
			}
		}
	}

	public void generateHiveCubeSmall(World world, int origx, int origy, int origz, int height, int width, int cubeno, float chance, int meta)
	{
		for (int hLayer = 0; hLayer < height; hLayer++)
		{     
			for (int i = -width; i < width; i++)
			{
				for (int j = -width; j < width; j++)
				{
					if ((hLayer == 0 || hLayer == (height - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.hive, 1, 2); 
					else if ((i == -width || i == (width - 1) || j == -width || j == (width - 1)) && (world.rand.nextFloat() <= chance)) world.setBlock(origx + i, origy - hLayer, origz + j, BOPCBlocks.hive, 1, 2);
				}
			}
		}
	}

	public void spawnWasps(World world, Random rand, int x, int y, int z)
	{
		for (int spawn = 0; spawn < 50; spawn++)
		{
			int spawnx = (x - 12) + rand.nextInt(24);
			int spawny = y - rand.nextInt(24);
			int spawnz = (z - 12) + rand.nextInt(24);

			if (world.getBlock(spawnx, spawny, spawnz) == BOPCBlocks.hive)
			{
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 1)
				{
					if (world.getBlock(spawnx - 1, spawny, spawnz) == BOPCBlocks.hive && world.getBlock(spawnx + 1, spawny, spawnz) == BOPCBlocks.hive && world.getBlock(spawnx, spawny, spawnz - 1) == BOPCBlocks.hive && world.getBlock(spawnx, spawny, spawnz + 1) == BOPCBlocks.hive && world.getBlock(spawnx, spawny - 1, spawnz) == BOPCBlocks.hive && world.getBlock(spawnx, spawny + 1, spawnz) == BOPCBlocks.hive)
					{
						if (world.getBlockMetadata(spawnx - 1, spawny, spawnz) == 1 && world.getBlockMetadata(spawnx + 1, spawny, spawnz) == 1 && world.getBlockMetadata(spawnx, spawny, spawnz - 1) == 1 && world.getBlockMetadata(spawnx, spawny, spawnz + 1) == 1 && world.getBlockMetadata(spawnx, spawny - 1, spawnz) == 1 && world.getBlockMetadata(spawnx, spawny + 1, spawnz) == 1)
						{
							world.setBlock(spawnx, spawny, spawnz, Blocks.mob_spawner);
							TileEntityMobSpawner tileentitymobspawner = (TileEntityMobSpawner)world.getTileEntity(spawnx, spawny, spawnz);

							if (tileentitymobspawner != null)
							{
								//					 getSpawnerLogic()
								tileentitymobspawner.func_145881_a().setEntityName("BiomesOPlenty.Wasp");
							}
						}
					}
				}
			}
		}
	}

	public void spawnEmptyHoneycombs(World world, Random rand, int x, int y, int z)
	{
		for (int spawn = 0; spawn < 50; spawn++)
		{
			int spawnx = (x - 8) + rand.nextInt(16);
			int spawny = y - rand.nextInt(12);
			int spawnz = (z - 8) + rand.nextInt(16);

			if (world.getBlock(spawnx, spawny, spawnz) == BOPCBlocks.hive)
			{
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 0)
				{
					world.setBlock(spawnx, spawny, spawnz, BOPCBlocks.hive, 2, 2);
				}
			}
		}
	}

	public void spawnFilledHoneycombs(World world, Random rand, int x, int y, int z)
	{
		for (int spawn = 0; spawn < 20; spawn++)
		{
			int spawnx = (x - 8) + rand.nextInt(16);
			int spawny = y - rand.nextInt(12);
			int spawnz = (z - 8) + rand.nextInt(16);

			if (world.getBlock(spawnx, spawny, spawnz) == BOPCBlocks.hive)
			{
				if (world.getBlockMetadata(spawnx, spawny, spawnz) == 0)
				{
					world.setBlock(spawnx, spawny, spawnz, BOPCBlocks.hive, 3, 2);
				}
			}
		}
	}
}
