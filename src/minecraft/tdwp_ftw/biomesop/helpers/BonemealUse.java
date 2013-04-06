package tdwp_ftw.biomesop.helpers;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.blocks.BlockFirSapling;
import tdwp_ftw.biomesop.blocks.BlockRedwoodSapling;
import tdwp_ftw.biomesop.blocks.BlockPalmSapling;
import tdwp_ftw.biomesop.blocks.BlockRedSapling;
import tdwp_ftw.biomesop.blocks.BlockOrangeSapling;
import tdwp_ftw.biomesop.blocks.BlockYellowSapling;
import tdwp_ftw.biomesop.blocks.BlockBrownSapling;
import tdwp_ftw.biomesop.blocks.BlockWillowSapling;
import tdwp_ftw.biomesop.blocks.BlockAppleSapling;
import tdwp_ftw.biomesop.blocks.BlockOriginSapling;
import tdwp_ftw.biomesop.blocks.BlockPinkSapling;
import tdwp_ftw.biomesop.blocks.BlockWhiteSapling;
import tdwp_ftw.biomesop.blocks.BlockDarkSapling;
import tdwp_ftw.biomesop.blocks.BlockMagicSapling;
import tdwp_ftw.biomesop.blocks.BlockMangroveSapling;
import tdwp_ftw.biomesop.blocks.BlockAcaciaSapling;
import tdwp_ftw.biomesop.declarations.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.entity.player.EntityPlayer;

import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.Event.Result;
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
		
		if (event.ID == BOPBlocks.magicSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
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
