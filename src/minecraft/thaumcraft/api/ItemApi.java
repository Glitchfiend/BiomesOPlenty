package thaumcraft.api;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import cpw.mods.fml.common.FMLLog;

/**
 * @author Azanor	
 * 
 * This is used to gain access to the items in my mod. 
 * I only give some examples and it will probably still 
 * require a bit of work for you to get hold of everything you need.
 *
 */
public class ItemApi {
	
	public static ItemStack getItem(String itemString, int meta) {
		ItemStack item = null;

		try {
			String itemClass = "thaumcraft.common.Config";
			Object obj = Class.forName(itemClass).getField(itemString).get(null);
			if (obj instanceof Item) {
				item = new ItemStack((Item) obj,1,meta);
			} else if (obj instanceof Block) {
				item = new ItemStack((Block) obj,1,meta);
			} else if (obj instanceof ItemStack) {
				item = (ItemStack) obj;
			}
		} catch (Exception ex) {
			FMLLog.warning("[Thaumcraft] Could not retrieve item or block identified by: " + itemString);
		}

		return item;
	}

	/** 
	 * 
	 * Some examples
	 * 
	 * Casting Wands:
	 * itemWandCastingApprentice, itemWandCastingAdept, itemWandCastingMage
	 * 
	 * Elemental Wands:
	 * itemWandFire, itemWandLightning, itemWandFrost, itemWandTrade, itemWandExcavation, itemHellrod
	 * 
	 * Resources:
	 * itemEssence, itemWispEssence, itemResource, itemShard, itemNugget, 
	 * itemNuggetChicken, itemNuggetBeef, itemNuggetPork, itemTripleMeatTreat,
	 * blockWooden, blockMarker
	 * 
	 * Research:
	 * itemResearchNotes, itemInkwell, itemThaumonomicon
	 * 
	 */

}
