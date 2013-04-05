package tdwp_ftw.biomesop.helpers;

import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.declarations.BOPBlocks;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import java.util.List;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentData;
import net.minecraft.enchantment.EnumEnchantmentType;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.StringTranslate;

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
