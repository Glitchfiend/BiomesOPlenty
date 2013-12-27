package biomesoplenty.common.itemblocks;

import biomesoplenty.api.BOPBlockHelper;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.init.Blocks;
import net.minecraft.item.ItemColored;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockWillow extends ItemColored
{
	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public ItemBlockWillow(Block block)
	{
		super(block, false);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		texture = iconRegister.registerIcon("biomesoplenty:willow");
	}

	@Override
	@SideOnly(Side.CLIENT)
	public int getColorFromItemStack(ItemStack itemStack, int par2)
	{
		//TODO:							 getRenderColor()
		return BOPBlockHelper.get("willow").func_149741_i(itemStack.getItemDamage());
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return texture;
	}
}