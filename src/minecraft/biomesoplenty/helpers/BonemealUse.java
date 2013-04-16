package biomesoplenty.helpers;

import biomesoplenty.blocks.BlockAcaciaSapling;
import biomesoplenty.blocks.BlockAppleSapling;
import biomesoplenty.blocks.BlockBambooSapling;
import biomesoplenty.blocks.BlockBrownSapling;
import biomesoplenty.blocks.BlockDarkSapling;
import biomesoplenty.blocks.BlockFirSapling;
import biomesoplenty.blocks.BlockHolySapling;
import biomesoplenty.blocks.BlockMagicSapling;
import biomesoplenty.blocks.BlockMangroveSapling;
import biomesoplenty.blocks.BlockOrangeSapling;
import biomesoplenty.blocks.BlockOriginSapling;
import biomesoplenty.blocks.BlockPalmSapling;
import biomesoplenty.blocks.BlockPinkSapling;
import biomesoplenty.blocks.BlockRedSapling;
import biomesoplenty.blocks.BlockRedwoodSapling;
import biomesoplenty.blocks.BlockWhiteSapling;
import biomesoplenty.blocks.BlockWillowSapling;
import biomesoplenty.blocks.BlockYellowSapling;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.worldgen.WorldGenGiantFlowerRed;
import biomesoplenty.worldgen.WorldGenGiantFlowerYellow;

import net.minecraft.block.Block;
import net.minecraftforge.event.Event.Result;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BonemealUse
{
	
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		
		if (event.ID == BOPBlocks.firSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockFirSapling)BOPBlocks.firSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.redwoodSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockRedwoodSapling)BOPBlocks.redwoodSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.palmSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockPalmSapling)BOPBlocks.palmSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.redSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockRedSapling)BOPBlocks.redSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.orangeSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockOrangeSapling)BOPBlocks.orangeSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.yellowSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockYellowSapling)BOPBlocks.yellowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.brownSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockBrownSapling)BOPBlocks.brownSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.willowSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockWillowSapling)BOPBlocks.willowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.appleSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockAppleSapling)BOPBlocks.appleSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.originSapling.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				((BlockOriginSapling)BOPBlocks.originSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == BOPBlocks.pinkSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockPinkSapling)BOPBlocks.pinkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.whiteSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockWhiteSapling)BOPBlocks.whiteSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.darkSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockDarkSapling)BOPBlocks.darkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.holySapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.15D)
				{
					((BlockHolySapling)BOPBlocks.holySapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.magicSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.10D)
				{
					((BlockMagicSapling)BOPBlocks.magicSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.mangroveSapling.blockID)
		{
			if (event.world.getBlockId(event.X, event.Y - 1, event.Z) == Block.sand.blockID)
			{
				event.setResult(Result.ALLOW);

				if (!event.world.isRemote)
				{
					if ((double)event.world.rand.nextFloat() < 0.45D)
					{
						((BlockMangroveSapling)BOPBlocks.mangroveSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
					}
				}
			}
		}
		
		if (event.ID == BOPBlocks.acaciaSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockAcaciaSapling)BOPBlocks.acaciaSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == BOPBlocks.bambooSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockBambooSapling)BOPBlocks.bambooSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == Block.plantRed.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
		           WorldGenGiantFlowerRed worldgengiantflowerred = new WorldGenGiantFlowerRed();
		           worldgengiantflowerred.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}
		
		if (event.ID == Block.plantYellow.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
		           WorldGenGiantFlowerYellow worldgengiantfloweryellow = new WorldGenGiantFlowerYellow();
		           worldgengiantfloweryellow.generate(event.world, event.world.rand, event.X, event.Y - 1, event.Z);
				}
			}
		}
		
		if (event.ID == BOPBlocks.holyGrass.blockID)
		{
			int var13 = event.X;
			int var14 = event.Y + 1;
			int var15 = event.Z;

			for (int i1 = 0; i1 < 128; ++i1)
			{

				for (int i2 = 0; i2 < i1 / 16; ++i2)
				{
					var13 += event.world.rand.nextInt(3) - 1;
					var14 += (event.world.rand.nextInt(3) - 1) * event.world.rand.nextInt(3) / 2;
					var15 += event.world.rand.nextInt(3) - 1;
				}

				if (event.world.getBlockId(var13, var14, var15) == 0)
				{
					if (BOPBlocks.holyTallGrass.canBlockStay(event.world, var13, var14, var15))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlock(var13, var14, var15, BOPBlocks.holyTallGrass.blockID, 0, 0x02);
						}      
					}
				}
			}
		}
	}
}
