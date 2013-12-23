package biomesoplenty.helpers;

import net.minecraft.item.ItemStack;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.api.BOPItems;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuel implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return addFuel(fuel.itemID, fuel.getItemDamage());
	}

	// Add Fuel rates
	private static int addFuel(int par1, int par2)
	{
		if(par1 == BOPBlocks.colorizedSaplings.get().blockID)
			return 100;
		if(par1 == BOPBlocks.woodenSingleSlab1.get().blockID)
			return 150;
		if(par1 == BOPBlocks.redwoodStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.woodenSingleSlab2.get().blockID)
			return 150;
		if(par1 == BOPBlocks.willowStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.saplings.get().blockID)
			return 100;
		if(par1 == BOPBlocks.firStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.acaciaStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.cherryStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.darkStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.magicStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.palmStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.mangroveStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.holyStairs.get().blockID)
			return 300;
		if(par1 == BOPBlocks.pineStairs.get().blockID)
            return 300;
		if(par1 == BOPBlocks.jacarandaStairs.get().blockID)
            return 300;
		if(par1 == BOPBlocks.hellBarkStairs.get().blockID)
            return 300;
		if(par1 == BOPItems.miscItems.get().itemID && par2 == 1)
			return 400;

		return 0;
	}
}