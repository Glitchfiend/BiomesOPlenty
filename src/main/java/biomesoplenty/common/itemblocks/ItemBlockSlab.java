package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSlab;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import biomesoplenty.common.blocks.BlockBOPSlab;

import com.google.common.base.Optional;

public class ItemBlockSlab extends ItemSlab
{
	public ItemBlockSlab(Block block, BlockBOPSlab singleSlab, BlockBOPSlab doubleSlab) 
	{
		super(block, singleSlab, doubleSlab, block == doubleSlab);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 7;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) 
	{
		//TODO:									getBlockFromItem
		BlockBOPSlab slab = (BlockBOPSlab)Block.getBlockFromItem(itemStack.getItem());

		//TODO:																		getFullSlabName()
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(slab.func_150002_b(itemStack.getItemDamage())).toString();
	}
}
