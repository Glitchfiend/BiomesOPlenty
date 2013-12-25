package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;

public class ItemBlockBamboo extends ItemBlock
{
	public ItemBlockBamboo(Block block)
	{
		super(block);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		itemIcon = iconRegister.registerIcon("biomesoplenty:item_bamboo");
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return itemIcon;
	}
}
