package biomesoplenty.common.world.features.trees;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;

public class WorldGenGiantFlower extends WorldGenAbstractTree
{
	private int metadata;
	
    public WorldGenGiantFlower(int metadata) 
    {
		super(false);
		
		this.metadata = metadata;
	}

	@Override
    public boolean generate(World world, Random random, int x, int y, int z)
    {
		while (world.isAirBlock(x, y, z) && y > 2)
		{
			--y;
		}

		Block block = world.getBlock(x, y, z);

		if (block != BOPCBlocks.longGrass) return false;
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (world.isAirBlock(x + var7, y - 1, z + var8) && world.isAirBlock(x + var7, y - 2, z + var8))
						return false;
				}
			}
			
			world.setBlock(x, y, z, Blocks.dirt);
			world.setBlock(x, y + 1, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 2, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 3, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 4, z, BOPCBlocks.logs3, 3, 2);
			world.setBlock(x, y + 5, z, BOPCBlocks.logs3, 3, 2);

			//Red
			if (metadata == 0)
			{
				world.setBlock(x - 1, y + 5, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 1, y + 5, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 5, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 5, z + 1, BOPCBlocks.petals, 0, 2);

				world.setBlock(x, y + 6, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 1, y + 6, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 1, y + 6, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 6, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 6, z + 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 1, y + 6, z + 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 1, y + 6, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 1, y + 6, z + 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 1, y + 6, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 2, y + 6, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 2, y + 6, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 6, z + 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 6, z - 2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x + 1, y + 7, z + 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 1, y + 7, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 1, y + 7, z + 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 1, y + 7, z - 1, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 2, y + 7, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 2, y + 7, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 7, z + 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 7, z - 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 2, y + 7, z + 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x + 2, y + 7, z - 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 2, y + 7, z + 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 2, y + 7, z - 2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x + 2, y + 8, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 2, y + 8, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 8, z + 2, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 8, z - 2, BOPCBlocks.petals, 0, 2);

				world.setBlock(x + 3, y + 9, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x - 3, y + 9, z, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 9, z + 3, BOPCBlocks.petals, 0, 2);
				world.setBlock(x, y + 9, z - 3, BOPCBlocks.petals, 0, 2);
			}
			else
			{
				//Yellow
				world.setBlock(x - 1, y + 5, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 1, y + 5, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 5, z - 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 5, z + 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 2, y + 5, z + 2, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 2, y + 5, z - 2, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 2, y + 5, z + 2, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 2, y + 5, z - 2, BOPCBlocks.petals, 1, 2);

				world.setBlock(x, y + 6, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 1, y + 6, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 1, y + 6, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 6, z - 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 6, z + 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 1, y + 6, z + 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 1, y + 6, z - 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 1, y + 6, z + 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 1, y + 6, z - 1, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 2, y + 6, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 2, y + 6, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 6, z + 2, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 6, z - 2, BOPCBlocks.petals, 1, 2);

				world.setBlock(x, y + 7, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x + 3, y + 7, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x - 3, y + 7, z, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 7, z + 3, BOPCBlocks.petals, 1, 2);
				world.setBlock(x, y + 7, z - 3, BOPCBlocks.petals, 1, 2);
			}

			return true;
		}
	}
}
