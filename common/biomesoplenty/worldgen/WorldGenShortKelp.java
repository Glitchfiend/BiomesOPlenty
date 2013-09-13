package biomesoplenty.worldgen;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.Blocks;
import net.minecraft.block.material.Material;

public class WorldGenShortKelp extends WorldGenerator
{
	public WorldGenShortKelp(boolean var1)
	{
		super(var1);
	}

	@Override
	public boolean generate(World var1, Random var2, int var3, int var4, int var5)
	{
		int var6 = var2.nextInt(4) + 3;
		int var7 = var2.nextInt(3) + 2;
		int var8 = var6 - var7;
		int var9 = 1;
		boolean var10 = true;

		if (var4 >= 1 && var4 + var6 + 1 <= 256)
		{
			int var11;
			int var13;
			int var15;
			int var21;
			
			var11 = var1.getBlockId(var3, var4 - 1, var5);

			if ((var11 == Block.sand.blockID || var11 == Block.dirt.blockID) && var4 < 256 - var6 - 1)
			{
				
				if (var1.getBlockMaterial(var3, var4, var5) != Material.water)
				{
					return false;
				}
				
				var21 = var2.nextInt(2);
				var13 = 1;
				boolean var22 = false;
				int var17;
				int var16;
				int var999 = 0;
				int var998;
				int var996;

				for (var15 = 0; var15 <= var8; ++var15)
				{
					var16 = var4 + var6 - var15;

					if (var21 >= var13)
					{
						var21 = var22 ? 1 : 0;
						var22 = true;
						++var13;

						if (var13 > var9)
						{
							var13 = var9;
						}
					}
					else
					{
						++var21;
					}
				}

				var15 = var2.nextInt(3);

				for (var16 = 0; var16 < var6 - var15; ++var16)
				{
					var17 = var1.getBlockId(var3, (var4 + (var16 + 2)), var5);

					if (var17 == Block.waterStill.blockID || var17 == Block.waterMoving.blockID)
					{
						this.setBlockAndMetadata(var1, var3, var4, var5, Blocks.coral.get().blockID, 0);
						this.setBlockAndMetadata(var1, var3, var4 + var16, var5, Blocks.coral.get().blockID, 1);
						++var999;
					}
				}
				
				var998 = var1.getBlockId(var3, (var4 + (var999 + 1)), var5);
				
				if (var998 == Block.waterStill.blockID || var998 == Block.waterMoving.blockID)
					{
						if (var999 == 0)
						{
							this.setBlockAndMetadata(var1, var3, var4, var5, Blocks.coral.get().blockID, 3);
						}
						else
						{
							this.setBlockAndMetadata(var1, var3, var4, var5, Blocks.coral.get().blockID, 0);
							this.setBlockAndMetadata(var1, var3, var4 + var999, var5, Blocks.coral.get().blockID, 2);
						}
					}
				
				return true;
			} else
				return false;
		} else
			return false;
	}
}
