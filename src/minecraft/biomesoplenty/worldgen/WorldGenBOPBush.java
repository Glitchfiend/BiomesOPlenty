package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenBOPBush extends WorldGenerator
{
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;
	private int plantBlockMeta;

	public WorldGenBOPBush(int par1, int meta)
	{
		plantBlockId = par1;
		plantBlockMeta = meta;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int var6 = 0; var6 < 64; ++var6)
		{
			int x = par3 + par2Random.nextInt(8) - par2Random.nextInt(8);
			int y = par4 + par2Random.nextInt(4) - par2Random.nextInt(4);
			int z = par5 + par2Random.nextInt(8) - par2Random.nextInt(8);

			if (par1World.isAirBlock(x, y, z) && !par1World.provider.hasNoSky && Block.blocksList[plantBlockId].canPlaceBlockOnSide(par1World, x, y, z, 1, new ItemStack(plantBlockId, 1, plantBlockMeta)))
			{
				par1World.setBlock(x, y, z, plantBlockId, plantBlockMeta, 2);
			}
		}

		return true;
	}
}

