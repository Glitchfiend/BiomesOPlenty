package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import biomesoplenty.common.blocks.BlockBOPColorizedLeaves;

public class ItemBlockColorizedLeaves extends ItemBlock
{
	public ItemBlockColorizedLeaves(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta | 4;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockBOPColorizedLeaves block = (BlockBOPColorizedLeaves)field_150939_a;		
		return super.getUnlocalizedName() + "." + block.getLeafType(itemStack.getItemDamage());
	}
}
