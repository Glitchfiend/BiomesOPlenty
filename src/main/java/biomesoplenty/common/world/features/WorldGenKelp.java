package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BlockBOPCoral;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenKelp extends WorldGeneratorBOP
{
    private final int minHeight;
    private final int randomHeight;
	
    public WorldGenKelp(int minHeight, int randomHeight)
    {
        super(true);

        this.minHeight = minHeight;
        this.randomHeight = randomHeight;
    }
    
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z)
	{
		BlockBOPCoral coral = (BlockBOPCoral)BOPCBlocks.coral1;

		int x2 = x + rand.nextInt(4) - rand.nextInt(4);
		int y2 = y;
		int z2 = z + rand.nextInt(4) - rand.nextInt(4);

		int height = minHeight + rand.nextInt(randomHeight);

		int currentMeta;

		if (world.getBlock(x2, y2, z2) == Blocks.water && world.getBlock(x2, y2 + 1, z2) == Blocks.water && coral.canBlockStay(world, x2, y, z2, 8))
		{
			for (int i = 0; i < height; ++i)
			{
				currentMeta = i == 0 ? 8 : i == height - 1 ? 10 : 9;

				if (world.getBlock(x2, y2 + i, z2) == Blocks.water && world.getBlock(x2, y2 + i + 1, z2) == Blocks.water)
				{
					world.setBlock(x2, y2 + i, z2, BOPCBlocks.coral1, currentMeta, 2);
				}
				else if (i > 0)
				{
					world.setBlock(x2, y2 + i - 1, z2, BOPCBlocks.coral1, 10, 2);
					break;
				}
				else
				{
					break;
				}
			}
		}

		return true;
	}
	
	@Override
	public void setupGeneration(World world, Random random, BOPBiome biome, String featureName, int x, int z)
	{
		if (featureName == "kelpPerChunk")
		{
			for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
			{
				int randX = x + random.nextInt(16);
				int randZ = z + random.nextInt(16);
				int randY = random.nextInt(64);

				this.generate(world, random, randX, randY, randZ);
			}
		}
		else if (featureName == "kelpThickPerChunk")
		{
			for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
			{
				int randX = x + random.nextInt(8);
				int randZ = z + random.nextInt(8);
				int randY = random.nextInt(64);

				this.generate(world, random, randX, randY, randZ);
			}
		}
		else if (featureName == "shortKelpPerChunk")
		{
			for (int i = 0; i < (Integer)biome.theBiomeDecorator.bopFeatures.getFeature(featureName); i++)
			{
				int randX = x + random.nextInt(16);
				int randZ = z + random.nextInt(16);
				int randY = random.nextInt(64);

				this.generate(world, random, randX, randY, randZ);
			}
		}
	}
}
