package tdwp_ftw.biomesop.helpers;

import net.minecraft.item.ItemStack;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import cpw.mods.fml.common.IFuelHandler;

public class FurnaceFuel implements IFuelHandler {

	@Override
	public int getBurnTime(ItemStack fuel) {
		return mod_BiomesOPlenty.addFuel(fuel.itemID, fuel.getItemDamage());
	}
}