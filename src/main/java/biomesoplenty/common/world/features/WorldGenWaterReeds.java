package biomesoplenty.common.world.features;

import java.lang.reflect.Field;
import java.util.Random;

import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenerator;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.common.world.decoration.IBOPDecoration;
import biomesoplenty.common.world.generation.WorldGeneratorBOP;

public class WorldGenWaterReeds extends WorldGeneratorBOP
{
	@Override
	public boolean generate(World world, Random random, int x, int y, int z)
	{
		for (int var6 = 0; var6 < 128; ++var6)
		{
			int i1 = x + random.nextInt(8) - random.nextInt(8);
			int j1 = y + random.nextInt(2) - random.nextInt(2);
			int k1 = z + random.nextInt(8) - random.nextInt(8);
			
            //TODO:	  isAirBlock()												canReplace()
			if (world.func_147437_c(i1, j1, k1) && BOPBlockHelper.get("plants").func_149705_a(world, i1, j1, k1, 0, new ItemStack(BOPBlockHelper.get("plants"), 1, 14)))
			{
				for (int i = 4; i > -4; --i)
				{
					//TODO:		getBlock()
					if (world.func_147439_a(i1 - i, j1 - 1, k1 - i) != Blocks.water)
					{
		            	//TODO:	setBlock()
						world.func_147465_d(i1, j1, k1, BOPBlockHelper.get("plants"), 14, 2);
					}
				}
			}
		}

		return true;
	}
	
	@Override
	public void doGeneration(World world, Random random, Field worldGeneratorField, WorldGenerator worldGenerator, BiomeGenBase biome, IBOPDecoration bopDecoration, int x, int z) throws Exception
	{
		for (int i = 0; i < worldGeneratorField.getInt(bopDecoration.getWorldFeatures()); i++)
		{
			int randX = x + random.nextInt(16) + 8;
			int randZ = z + random.nextInt(16) + 8;
			int randY = random.nextInt(world.getHeightValue(randX, randZ) * 2);

			worldGenerator.generate(world, random, randX, randY, randZ);
		}
	}
}