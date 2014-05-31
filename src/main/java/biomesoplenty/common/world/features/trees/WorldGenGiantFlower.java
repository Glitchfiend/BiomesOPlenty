package biomesoplenty.common.world.features.trees;

import java.util.Random;

import biomesoplenty.api.BOPBlockHelper;

import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenAbstractTree;

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

		if (block != BOPBlockHelper.get("longGrass")) return false;
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
			world.setBlock(x, y + 1, z, BOPBlockHelper.get("logs3"), 3, 2);
			world.setBlock(x, y + 2, z, BOPBlockHelper.get("logs3"), 3, 2);
			world.setBlock(x, y + 3, z, BOPBlockHelper.get("logs3"), 3, 2);
			world.setBlock(x, y + 4, z, BOPBlockHelper.get("logs3"), 3, 2);
			world.setBlock(x, y + 5, z, BOPBlockHelper.get("logs3"), 3, 2);

			//Red
			if (metadata == 0)
			{
				world.setBlock(x - 1, y + 5, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 1, y + 5, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 5, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 5, z + 1, BOPBlockHelper.get("petals"), 0, 2);

				world.setBlock(x, y + 6, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 1, y + 6, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 1, y + 6, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 6, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 6, z + 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 1, y + 6, z + 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 1, y + 6, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 1, y + 6, z + 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 1, y + 6, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 2, y + 6, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 2, y + 6, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 6, z + 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 6, z - 2, BOPBlockHelper.get("petals"), 0, 2);

				world.setBlock(x + 1, y + 7, z + 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 1, y + 7, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 1, y + 7, z + 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 1, y + 7, z - 1, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 2, y + 7, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 2, y + 7, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 7, z + 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 7, z - 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 2, y + 7, z + 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x + 2, y + 7, z - 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 2, y + 7, z + 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 2, y + 7, z - 2, BOPBlockHelper.get("petals"), 0, 2);

				world.setBlock(x + 2, y + 8, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 2, y + 8, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 8, z + 2, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 8, z - 2, BOPBlockHelper.get("petals"), 0, 2);

				world.setBlock(x + 3, y + 9, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x - 3, y + 9, z, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 9, z + 3, BOPBlockHelper.get("petals"), 0, 2);
				world.setBlock(x, y + 9, z - 3, BOPBlockHelper.get("petals"), 0, 2);
			}
			else
			{
				//Yellow
				world.setBlock(x - 1, y + 5, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 1, y + 5, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 5, z - 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 5, z + 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 2, y + 5, z + 2, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 2, y + 5, z - 2, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 2, y + 5, z + 2, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 2, y + 5, z - 2, BOPBlockHelper.get("petals"), 1, 2);

				world.setBlock(x, y + 6, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 1, y + 6, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 1, y + 6, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 6, z - 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 6, z + 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 1, y + 6, z + 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 1, y + 6, z - 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 1, y + 6, z + 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 1, y + 6, z - 1, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 2, y + 6, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 2, y + 6, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 6, z + 2, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 6, z - 2, BOPBlockHelper.get("petals"), 1, 2);

				world.setBlock(x, y + 7, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x + 3, y + 7, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x - 3, y + 7, z, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 7, z + 3, BOPBlockHelper.get("petals"), 1, 2);
				world.setBlock(x, y + 7, z - 3, BOPBlockHelper.get("petals"), 1, 2);
			}

			return true;
		}
	}
}
