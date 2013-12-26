package biomesoplenty.common.itemblocks;

import javax.swing.Icon;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockPersimmonLeaves extends ItemBlock
{
	@SideOnly(Side.CLIENT)
	private Icon texture;

	public ItemBlockPersimmonLeaves(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta;
	}
}