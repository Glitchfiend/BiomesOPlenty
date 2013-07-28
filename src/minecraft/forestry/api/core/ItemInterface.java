package forestry.api.core;

import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class ItemInterface {

	/**
	 * Get items here!
	 * 
	 * Blocks currently not supported.
	 * 
	 * @param ident
	 * @return ItemStack representing the item, null if not found.
	 */
	public static ItemStack getItem(String ident) {
		ItemStack item = null;

		try {
			String pack = ItemInterface.class.getPackage().getName();
			pack = pack.substring(0, pack.lastIndexOf('.'));
			String itemClass = pack.substring(0, pack.lastIndexOf('.')) + ".core.config.ForestryItem";
			Object obj = Class.forName(itemClass).getField(ident).get(null);
			if (obj instanceof Item)
				item = new ItemStack((Item) obj);
			else if (obj instanceof ItemStack)
				item = (ItemStack) obj;
		} catch (Exception ex) {
			FMLLog.warning("Could not retrieve Forestry item identified by: " + ident);
		}

		return item;
	}

}
