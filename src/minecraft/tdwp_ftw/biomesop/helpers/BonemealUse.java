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
		event.setResult(Result.ALLOW);
		
		if (event.ID == mod_BiomesOPlenty.firSapling.blockID)
		{
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockFirSapling)mod_BiomesOPlenty.firSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.redwoodSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockRedwoodSapling)mod_BiomesOPlenty.redwoodSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.palmSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockPalmSapling)mod_BiomesOPlenty.palmSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.redSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockRedSapling)mod_BiomesOPlenty.redSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.orangeSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockOrangeSapling)mod_BiomesOPlenty.orangeSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.yellowSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockYellowSapling)mod_BiomesOPlenty.yellowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.brownSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockBrownSapling)mod_BiomesOPlenty.brownSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.willowSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockWillowSapling)mod_BiomesOPlenty.willowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.appleSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockAppleSapling)mod_BiomesOPlenty.appleSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.originSapling.blockID)
		{
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockOriginSapling)mod_BiomesOPlenty.originSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.pinkSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockPinkSapling)mod_BiomesOPlenty.pinkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.whiteSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockWhiteSapling)mod_BiomesOPlenty.whiteSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.darkSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockDarkSapling)mod_BiomesOPlenty.darkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.magicSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockMagicSapling)mod_BiomesOPlenty.magicSapling).func_96477_c(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.mangroveSapling.blockID)
		{
			event.setResult(Result.ALLOW);

			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockMangroveSapling)mod_BiomesOPlenty.mangroveSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.acaciaSapling.blockID)
		{
			event.setResult(Result.ALLOW);
			
			if (!event.world.isRemote)
			{
				if ((double)event.world.rand.nextFloat() < 0.45D)
				{
					((BlockAcaciaSapling)mod_BiomesOPlenty.acaciaSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
				}
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.holyGrass.blockID)
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
					if (mod_BiomesOPlenty.holyTallGrass.canBlockStay(event.world, var13, var14, var15))
					{
						event.setResult(Result.ALLOW);

						if (!event.world.isRemote)
						{
							event.world.setBlock(var13, var14, var15, mod_BiomesOPlenty.holyTallGrass.blockID, 0, 0x02);
						}      
					}
				}
			}
		}
	}
}
