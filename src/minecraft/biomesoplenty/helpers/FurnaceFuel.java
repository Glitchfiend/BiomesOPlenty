package biomesoplenty.helpers;

import net.minecraft.item.ItemStack;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuel implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return addFuel(fuel.itemID, fuel.getItemDamage());
	}

	// Add Fuel rates
	private static int addFuel(int par1, int par2)
	{
		if(par1 == Blocks.colorizedSaplings.get().blockID)
			return 100;
		if(par1 == Blocks.woodenSingleSlab1.get().blockID)
			return 150;
		if(par1 == Blocks.redwoodStairs.get().blockID)
			return 300;
		if(par1 == Blocks.woodenSingleSlab2.get().blockID)
			return 150;
		if(par1 == Blocks.willowStairs.get().blockID)
			return 300;
		if(par1 == Blocks.saplings.get().blockID)
			return 100;
		if(par1 == Blocks.firStairs.get().blockID)
			return 300;
		if(par1 == Blocks.acaciaStairs.get().blockID)
			return 300;
		if(par1 == Blocks.cherryStairs.get().blockID)
			return 300;
		if(par1 == Blocks.darkStairs.get().blockID)
			return 300;
		if(par1 == Blocks.magicStairs.get().blockID)
			return 300;
		if(par1 == Blocks.palmStairs.get().blockID)
			return 300;
		if(par1 == Blocks.mangroveStairs.get().blockID)
			return 300;
		if(par1 == Blocks.holyStairs.get().blockID)
			return 300;
		if(par1 == Blocks.pineStairs.get().blockID)
            return 300;
		if(par1 == Blocks.jacarandaStairs.get().blockID)
            return 300;
		if(par1 == Blocks.hellBarkStairs.get().blockID)
            return 300;
		if(par1 == Items.miscItems.get().itemID && par2 == 1)
			return 400;

		return 0;
	}
}