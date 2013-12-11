package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class WorldGenCloverPatch extends WorldGenerator
{
	/** The ID of the plant block used in this plant generator. */
	private int plantBlockId;
	private int plantBlockMeta;

	public WorldGenCloverPatch(int par1, int meta)
	{
		plantBlockId = par1;
		plantBlockMeta = meta;
	}

	@Override
	public boolean generate(World par1World, Random par2Random, int par3, int par4, int par5)
	{
		for (int l = 0; l < 128; ++l)
		{
			int i1 = par3 + par2Random.nextInt(6) - par2Random.nextInt(6);
			int j1 = par4;
			int k1 = par5 + par2Random.nextInt(6) - par2Random.nextInt(6);

			if (par1World.isAirBlock(i1, j1, k1) && (!par1World.provider.hasNoSky || j1 < 127) && (par1World.getFullBlockLightValue(i1, j1, k1) >= 8 || par1World.canBlockSeeTheSky(i1, j1, k1))
					&& Block.blocksList[plantBlockId].canPlaceBlockOnSide(par1World, i1, j1, k1, 1, new ItemStack(plantBlockId, 1, plantBlockMeta)))
			{
				par1World.setBlock(i1, j1, k1, plantBlockId, plantBlockMeta, 2);
			}
		}

		return true;
	}
}
