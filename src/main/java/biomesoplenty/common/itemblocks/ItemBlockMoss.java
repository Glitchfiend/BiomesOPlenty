package biomesoplenty.itemblocks;

import javax.swing.Icon;

import net.minecraft.item.ItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockMoss extends ItemBlock
{
	@SideOnly(Side.CLIENT)
	private Icon texture;

	public ItemBlockMoss(int par1)
	{
		super(par1);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		texture = iconRegister.registerIcon("biomesoplenty:item_moss");
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return texture;
	}
}
