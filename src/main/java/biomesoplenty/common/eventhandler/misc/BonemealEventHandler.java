package biomesoplenty.common.eventhandler.misc;

import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;
import net.minecraftforge.event.entity.player.BonemealEvent;
import biomesoplenty.api.BOPBlockHelper;
import biomesoplenty.api.content.BOPCBlocks;
import biomesoplenty.common.blocks.BlockBOPColorizedSapling;
import biomesoplenty.common.blocks.BlockBOPSapling;
import biomesoplenty.common.world.features.trees.WorldGenGiantFlower;
import cpw.mods.fml.common.eventhandler.Event.Result;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class BonemealEventHandler
{
	@SubscribeEvent
	public void onUseBonemeal(BonemealEvent event)
	{
		World world = event.world;

		int x = event.x;
		int y = event.y;
		int z = event.z;

		Block block = event.block;
		int meta = event.world.getBlockMetadata(x, y, z);
		
		if (block == BOPCBlocks.saplings)
		{
			event.setResult(Result.ALLOW);

			if (!world.isRemote)
			{
				double chance = 0D;

				switch (meta)
				{
				case 3: // Magic Sapling
					chance = 0.1D;
					break;

				case 7: // Ethereal Sapling
					chance = 0.15D;
					break;

				case 9: // Origin Sapling
					chance = 1D;
					break;

				default:
					chance = 0.45D;
					break;
				}

				if (world.rand.nextFloat() < chance)
				{
					((BlockBOPSapling)BOPCBlocks.saplings).func_149878_d(event.world, x, y, z, event.world.rand);
				}
			}
		}
		else if (block == BOPCBlocks.colorizedSaplings)
		{
			event.setResult(Result.ALLOW);

			if (!world.isRemote)
			{
				if (world.rand.nextFloat() < 0.45D)
				{
					((BlockBOPColorizedSapling)BOPCBlocks.colorizedSaplings).func_149878_d(event.world, x, y, z, event.world.rand);
				}
			}
		}
		else if (block == BOPCBlocks.plants)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				switch (meta)
				{
				case 7:
					if (event.world.rand.nextFloat() < 0.45D)
					{
						world.setBlock(x, y, z, BOPCBlocks.plants, 10, 2);
						world.setBlock(x, y + 1, z, BOPCBlocks.plants, 9, 2);
					}
					break;
				
				case 12:
					if (world.rand.nextFloat() < 0.45D)
					{
						world.setBlock(x, y, z, Blocks.cactus);
					}
					break;
				}
			}
		}
		else if (block == Blocks.red_flower)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					WorldGenGiantFlower worldgengiantflower = new WorldGenGiantFlower(0);
					worldgengiantflower.generate(event.world, event.world.rand, event.x, event.y - 1, event.z);
				}
			}
		}
		else if (block == Blocks.yellow_flower)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if (event.world.rand.nextFloat() < 0.45D)
				{
					WorldGenGiantFlower worldgengiantflower = new WorldGenGiantFlower(1);
					worldgengiantflower.generate(event.world, event.world.rand, event.x, event.y - 1, event.z);
				}
			}
		}
		else if (block == BOPCBlocks.turnip)
		{
			if (event.world.getBlockMetadata(x, y, z) != 7)
			{
				if (!event.world.isRemote)
				{
					((BlockCrops)BOPCBlocks.turnip).func_149863_m(event.world, x, y, z);
				}
			}
		}
		else if (block == BOPCBlocks.bopGrass && event.world.getBlockMetadata(x, y, z) == 0)
		{
			int var14 = y + 1;

			for (int i1 = 0; i1 < 128; ++i1)
			{

				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					x += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					z += event.world.rand.nextInt(3) - 1;
				}

				if (event.world.getBlock(x, var14, z).isAir(world, x, var14, z))
				{
					if (BOPCBlocks.plants.canReplace(world, x, var14, z, 0, new ItemStack(BOPCBlocks.plants, 1, 4)))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlock(x, var14, z, BOPCBlocks.plants, 4, 2);
						}
					}
				}
			}
		}
	}
}