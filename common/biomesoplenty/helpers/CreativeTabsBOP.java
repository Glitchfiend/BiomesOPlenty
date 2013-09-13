package biomesoplenty.helpers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Items;

public class CreativeTabsBOP extends CreativeTabs
{
	public CreativeTabsBOP(int position, String tabID)
	{
		super(position, tabID); //The constructor for your tab
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(Items.food.get(),1,7);
	}
}
