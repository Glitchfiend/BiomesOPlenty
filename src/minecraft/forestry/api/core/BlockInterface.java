package forestry.api.core;

import net.minecraft.block.Block;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

public class BlockInterface {
	/**
	 * Get yer blocks here!
	 * 
	 * @param ident
	 * @return
	 */
	public static ItemStack getBlock(String ident) {
		ItemStack item = null;

		try {
			String pack = ItemInterface.class.getPackage().getName();
			pack = pack.substring(0, pack.lastIndexOf('.'));
			String itemClass = pack.substring(0, pack.lastIndexOf('.')) + ".core.config.ForestryBlock";
			Object obj = Class.forName(itemClass).getField(ident).get(null);
			if (obj instanceof Block)
				item = new ItemStack((Block) obj);
			else if (obj instanceof ItemStack)
				item = (ItemStack) obj;
		} catch (Exception ex) {
			FMLLog.warning("Could not retrieve Forestry block identified by: " + ident);
		}

		return item;
	}

}
