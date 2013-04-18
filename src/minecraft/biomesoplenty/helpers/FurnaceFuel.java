package biomesoplenty.helpers;

import net.minecraft.item.ItemStack;
import biomesoplenty.configuration.BOPBlocks;
import biomesoplenty.configuration.BOPItems;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuel implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return addFuel(fuel.itemID, fuel.getItemDamage());
	}
	
	// Add Fuel rates
	private static int addFuel(int par1, int par2)
    {
        if(par1 == BOPBlocks.redwoodSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.redwoodSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.redwoodStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.willowSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.willowSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.willowStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.firSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.firSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.firStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.acaciaSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.acaciaSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.acaciaStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.pinkSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.whiteSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.orangeSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.yellowSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.redSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.brownSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.appleSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.originSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.cherrySingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.cherryStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.darkSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.darkSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.darkStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.magicSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.magicSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.magicStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.palmSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.palmSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.palmStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.mangroveSapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.mangroveSingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.mangroveStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPBlocks.holySapling.blockID)
        {
            return 100;
        }
        if(par1 == BOPBlocks.holySingleSlab.blockID)
        {
            return 150;
        }
        if(par1 == BOPBlocks.holyStairs.blockID)
        {
            return 300;
        }
        if(par1 == BOPItems.ashes.itemID)
        {
            return 400;
        }

        return 0;
    }
}