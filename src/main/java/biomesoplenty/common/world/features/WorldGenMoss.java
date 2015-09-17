package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.util.Direction;
import net.minecraft.util.Facing;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenMoss extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		int var6 = x;

		for (int var7 = z; y < 80; ++y)
		{
			if (world.isAirBlock(x, y, z))
			{
				for (int var8 = 2; var8 <= 5; ++var8)
				{
					if (BOPCBlocks.moss.canPlaceBlockOnSide(world, x, y, z, var8))
					{
						int var999 = random.nextInt(4);

						if (var999 == 0)
						{
							world.setBlock(x, y, z, BOPCBlocks.moss, 1 << Direction.facingToDirection[Facing.oppositeSide[var8]], 2);
						}
						break;
					}
				}
			}
			else
			{
				x = var6 + random.nextInt(4) - random.nextInt(4);
				z = var7 + random.nextInt(4) - random.nextInt(4);
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
