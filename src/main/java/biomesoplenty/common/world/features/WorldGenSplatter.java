package biomesoplenty.common.world.features;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenSplatter extends WorldGeneratorBOP
{
	private Block splatterBlock;
	private int splatterBlockMeta;
	
	private List<Block> blocksToSplatter;
	
	public WorldGenSplatter(Block splatterBlock, int splatterBlockMeta, Block... blocksToSplatter)
	{
		this.splatterBlock = splatterBlock;
		this.splatterBlockMeta = splatterBlockMeta;
		this.blocksToSplatter = Arrays.asList(blocksToSplatter);
	}
	
	public WorldGenSplatter(Block splatterBlock, Block... blocksToSplatter)
	{
		this(splatterBlock, 0 , blocksToSplatter);
	}

	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
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
			int i1 = x + rand.nextInt(8) - rand.nextInt(8);
			int j1 = y + rand.nextInt(4) - rand.nextInt(4);
			int k1 = z + rand.nextInt(8) - rand.nextInt(8);

			if (world.isAirBlock(i1, j1, k1) && blocksToSplatter.contains(world.getBlock(i1, j1 - 1, k1)))
			{
				world.setBlock(i1, j1 - 1, k1, this.splatterBlock, this.splatterBlockMeta, 2);
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
			int randY = random.nextInt(256);
			int randZ = z + random.nextInt(16) + 8;

            this.generate(world, random, randX, randY, randZ);
		}
	}
}
