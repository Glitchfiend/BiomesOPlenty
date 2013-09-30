package biomesoplenty.worldgen.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;

public class WorldGenPalmTree1 extends WorldGenerator
{
	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		while (var1.isAirBlock(var3, var4, var5) && var4 > 2)
		{
			--var4;
		}

		int var6 = var1.getBlockId(var3, var4, var5);

		if (var6 != Block.grass.blockID)
		{
			return false;
		}
		else
		{
			for (int var7 = -2; var7 <= 2; ++var7)
			{
				for (int var8 = -2; var8 <= 2; ++var8)
				{
					if (var1.isAirBlock(var3 + var7, var4 - 1, var5 + var8) && var1.isAirBlock(var3 + var7, var4 - 2, var5 + var8) && !var1.isAirBlock(var3 + var7, var4, var5 + var8))
					{
						return false;
					}
				}
			}
			
			//settings========
			double strength = var2.nextInt(35) / 100D;
			//================
			
			double xoff = 0;
			double yoff = 0;
			int r = var2.nextInt(4);
			if(r == 0) { xoff = strength; }
			else if(r == 1) { xoff = -strength; }
			else if(r == 2) { yoff = strength; }
			else { yoff = -strength; }
			
			int h = 1;
			buildBlock(var1, var3, var4, var5, Block.dirt.blockID, 0);
			for(int b = 0; b < 10; b++)
			{
				buildBlock(var1, var3 + ((int) Math.floor(xoff)), var4 + h, var5 + ((int) Math.floor(yoff)), Blocks.logs2.get().blockID, 3);
				if(b == 9)
				{
					generateTop(var1, var3 + ((int) Math.floor(xoff)), var4 + h, var5 + ((int) Math.floor(yoff)));
				}
				else
				{
					h++;
					xoff *= 1.3D;
					yoff *= 1.3D;
				}
			}

			return true;
		}
	}
	
	public void generateTop(World world, int x, int y, int z)
	{
		buildBlock(world, x + 2, y - 1, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 2, y - 1, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y - 1, z + 2, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y - 1, z - 2, Blocks.leavesColorized.get().blockID, 2);
	
		buildBlock(world, x + 1, y, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 1, y, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y, z + 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y, z - 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x + 2, y, z + 2, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 2, y, z - 2, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x + 2, y, z - 2, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 2, y, z + 2, Blocks.leavesColorized.get().blockID, 2);

		buildBlock(world, x + 1, y + 1, z - 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 1, y + 1, z + 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x + 1, y + 1, z + 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 1, y + 1, z - 1, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y + 1, z, Blocks.leavesColorized.get().blockID, 2);

		buildBlock(world, x + 2, y + 2, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x - 2, y + 2, z, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y + 2, z + 2, Blocks.leavesColorized.get().blockID, 2);
		buildBlock(world, x, y + 2, z - 2, Blocks.leavesColorized.get().blockID, 2);
	}
	
	public void buildBlock(World world, int x, int y, int z, int id, int meta)
	{
		Material m = world.getBlockMaterial(x, y, z);
		if(m == Material.air || m == Material.leaves || m == Material.vine || m == Material.plants)
		{
			world.setBlock(x, y, z, id, meta, 2);
		}
	}
}
