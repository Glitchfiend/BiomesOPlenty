package biomesoplenty.common.helpers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.BOPItemHelper;

public class CreativeTabsBOP extends CreativeTabs
{
	public CreativeTabsBOP(int position, String tabID)
	{
		super(position, tabID);
	}

	@Override
	public ItemStack getIconItemStack() 
	{
		return new ItemStack(BOPItemHelper.get("food"), 1, 7);
	}

	@Override
	public Item getTabIconItem() 
	{
		return null;
	}
}
