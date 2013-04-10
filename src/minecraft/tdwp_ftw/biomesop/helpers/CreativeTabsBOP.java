package tdwp_ftw.biomesop.helpers;

import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import tdwp_ftw.biomesop.declarations.BOPBlocks;

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
