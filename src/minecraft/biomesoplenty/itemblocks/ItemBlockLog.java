package biomesoplenty.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import biomesoplenty.blocks.BlockBOPLog;

public class ItemBlockLog extends ItemBlock
{
	public ItemBlockLog(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 3;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		BlockBOPLog block = (BlockBOPLog)Block.blocksList[itemStack.itemID];
		return super.getUnlocalizedName() + "." + block.getWoodType(itemStack.getItemDamage()) + "Wood";
	}
}
