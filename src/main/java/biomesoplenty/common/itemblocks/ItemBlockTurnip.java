package biomesoplenty.itemblocks;

import javax.swing.Icon;

import net.minecraft.item.ItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockTurnip extends ItemBlock
{
	@SideOnly(Side.CLIENT)
	private Icon texture;

	public ItemBlockTurnip(int par1)
	{
		super(par1);
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return texture;
	}
}
