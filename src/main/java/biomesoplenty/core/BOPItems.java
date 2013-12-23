package biomesoplenty.core;

import net.minecraft.item.Item;
import biomesoplenty.items.ItemBOPFood;
import biomesoplenty.items.ItemBOPRecord;
import biomesoplenty.items.ItemDart;
import cpw.mods.fml.common.registry.GameRegistry;

public class BOPItems 
{
	public static void init()
	{
		// Material declaration
		initializeItems();
	}

	private static void initializeItems()
	{
		// Item declaration
		registerItem(new ItemBOPFood(0).setUnlocalizedName("food"));
		registerItem(new ItemDart().setUnlocalizedName("dart"));
		registerItem(new ItemBOPRecord("bopdisc").setUnlocalizedName("bopDisc"));
	}

	public static void registerItem(Item item)
	{
	    GameRegistry.registerItem(item, item.getUnlocalizedName().replace("item.", ""));
	}
}
