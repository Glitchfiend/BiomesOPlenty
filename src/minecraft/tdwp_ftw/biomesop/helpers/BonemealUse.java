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
import net.minecraft.entity.player.EntityPlayer;

import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.BonemealEvent;

public class BonemealUse
{
	
	@ForgeSubscribe
	public void onUseBonemeal(BonemealEvent event)
	{
		if (event.ID == mod_BiomesOPlenty.firSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockFirSapling)mod_BiomesOPlenty.firSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.redwoodSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockRedwoodSapling)mod_BiomesOPlenty.redwoodSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.palmSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockPalmSapling)mod_BiomesOPlenty.palmSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.redSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockRedSapling)mod_BiomesOPlenty.redSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.orangeSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockOrangeSapling)mod_BiomesOPlenty.orangeSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.yellowSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockYellowSapling)mod_BiomesOPlenty.yellowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.brownSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockBrownSapling)mod_BiomesOPlenty.brownSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.willowSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockWillowSapling)mod_BiomesOPlenty.willowSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.appleSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockAppleSapling)mod_BiomesOPlenty.appleSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.originSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockOriginSapling)mod_BiomesOPlenty.originSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.pinkSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockPinkSapling)mod_BiomesOPlenty.pinkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.whiteSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockWhiteSapling)mod_BiomesOPlenty.whiteSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.darkSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockDarkSapling)mod_BiomesOPlenty.darkSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.magicSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockMagicSapling)mod_BiomesOPlenty.magicSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.mangroveSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockMangroveSapling)mod_BiomesOPlenty.mangroveSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
		
		if (event.ID == mod_BiomesOPlenty.acaciaSapling.blockID)
		{
			if (!event.world.isRemote)
			{
					((BlockAcaciaSapling)mod_BiomesOPlenty.acaciaSapling).growTree(event.world, event.X, event.Y, event.Z, event.world.rand);
			}
		}
	}
}
