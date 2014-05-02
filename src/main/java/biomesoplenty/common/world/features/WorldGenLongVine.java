package biomesoplenty.common.world.features;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.BOPDecorationManager;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenLongVine extends WorldGeneratorBOP
{
	private Block vine;
	private int baseHeight;
	private int randHeight;
	
	public WorldGenLongVine(Block vine, int baseHeight, int randHeight)
	{
		this.vine = vine;
		this.baseHeight = baseHeight;
		this.randHeight = randHeight;
	}
	
	@Override
	public boolean generate(World world, Random rand, int x, int y, int z) 
	{
		while (world.isAirBlock(x, y, z) && y < 254) y++;
		
		Block block = world.getBlock(x, y, z);
		
		if (block != Blocks.netherrack) return false;
		else
		{
			for (int iy = 1; iy <= rand.nextInt(randHeight) + baseHeight; iy++)
			{
				if (world.isAirBlock(x - 1, y - iy, z)) world.setBlock(x - 1, y - iy, z, vine, 8, 2);
				else break;
			}
			
			for (int iy = 1; iy <= rand.nextInt(randHeight) + baseHeight; iy++)
			{
				if (world.isAirBlock(x + 1, y - iy, z)) world.setBlock(x + 1, y - iy, z, vine, 2, 2);
				else break;
			}
			
			for (int iy = 1; iy <= rand.nextInt(randHeight) + baseHeight; iy++)
			{
				if (world.isAirBlock(x, y - iy, z - 1)) world.setBlock(x, y - iy, z - 1, vine, 1, 2);
				else break;
			}
			
			for (int iy = 1; iy <= rand.nextInt(randHeight) + baseHeight; iy++)
			{
				if (world.isAirBlock(x, y - iy, z + 1)) world.setBlock(x, y - iy, z + 1, vine, 4, 2);
				else break;
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
