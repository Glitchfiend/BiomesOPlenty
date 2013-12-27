package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockMoss extends ItemBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public ItemBlockMoss(Block block)
	{
		super(block);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		texture = iconRegister.registerIcon("biomesoplenty:item_moss");
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return texture;
	}
}
