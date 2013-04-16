package biomesoplenty.helpers;

import biomesoplenty.configuration.BOPBlocks;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;

public class CreativeTabsBOP extends CreativeTabs
{
	public CreativeTabsBOP(int position, String tabID)
	{
		super(position, tabID); //The constructor for your tab
	}
	
	@Override
	public ItemStack getIconItemStack()
	{
		return new ItemStack(BOPBlocks.firSapling);
	}
}
