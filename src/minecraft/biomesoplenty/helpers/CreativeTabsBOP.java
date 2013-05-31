package biomesoplenty.helpers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Blocks;

public class CreativeTabsBOP extends CreativeTabs
{
	public CreativeTabsBOP(int position, String tabID)
	{
		super(position, tabID); //The constructor for your tab
	}

	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(Blocks.saplings.get(),1,6);
	}
}
