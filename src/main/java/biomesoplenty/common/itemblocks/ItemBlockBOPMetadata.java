package biomesoplenty.common.itemblocks;

import biomesoplenty.common.utils.ISubLocalization;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlockWithMetadata;
import net.minecraft.item.ItemStack;

public class ItemBlockBOPMetadata extends ItemBlockWithMetadata
{
	private Block block;
	
	public ItemBlockBOPMetadata(Block block) 
	{
		super(block, block);
		this.block = block;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		if (block instanceof ISubLocalization)
		{
			try{
				return ((ISubLocalization)block).getUnlocalizedName(super.getUnlocalizedName(itemStack), itemStack);
			}
			catch(Exception e) {
				return null;
			}
		}
		else
		{
			return super.getUnlocalizedName(itemStack);
		}
	}
}
