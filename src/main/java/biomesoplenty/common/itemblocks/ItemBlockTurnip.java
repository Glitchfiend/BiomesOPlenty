package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockTurnip extends ItemBlock
{
	@SideOnly(Side.CLIENT)
	private IIcon texture;

	public ItemBlockTurnip(Block block)
	{
		super(block);
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return texture;
	}
}
