package biomesoplenty.common.itemblocks;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.api.Blocks;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockWillow extends ItemColored
{
	@SideOnly(Side.CLIENT)
	private Icon texture;

	public ItemBlockWillow(int par1)
	{
		super(par1, false);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IconRegister iconRegister)
	{
		texture = iconRegister.registerIcon("biomesoplenty:willow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int par2)
	{
		return Blocks.willow.get().getRenderColor(itemStack.getItemDamage());
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return texture;
	}
}