package biomesoplenty.common.utils;

import java.util.HashMap;
import java.util.Map.Entry;

import net.minecraft.item.ItemStack;

public class ListUtils 
{
	public static <T> T getItemStackMapValue(HashMap<ItemStack, T> list, ItemStack stack)
	{
		for (Entry<ItemStack, T> entry : list.entrySet())
		{
			ItemStack stackToCompareTo = entry.getKey();
			
			if (stackToCompareTo.getItem() == stack.getItem() && stackToCompareTo.getItemDamage() == stack.getItemDamage()) return entry.getValue();
		}
		
		return null;
	}
}
