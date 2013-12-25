package biomesoplenty.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemSlab;
import net.minecraft.item.ItemStack;
import biomesoplenty.common.blocks.BlockBOPSlab;

import com.google.common.base.Optional;

public class ItemBlockSlab extends ItemSlab
{
	private static Optional<BlockHalfSlab>	singleSlab	= Optional.absent();
	private static Optional<BlockHalfSlab>	doubleSlab	= Optional.absent();

	static public void setSlabs(BlockHalfSlab singleSlab, BlockHalfSlab doubleSlab)
	{
		ItemBlockSlab.singleSlab = Optional.of(singleSlab);
		ItemBlockSlab.doubleSlab = Optional.of(doubleSlab);
	}

	public ItemBlockSlab(int id) {
		super(id, singleSlab.get(), doubleSlab.get(), id == doubleSlab.get().blockID);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 7;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack) {
		BlockBOPSlab slab = (BlockBOPSlab)Block.blocksList[itemStack.itemID];

		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(slab.getFullSlabName(itemStack.getItemDamage())).toString();
	}
}
