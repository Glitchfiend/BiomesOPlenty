package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockPetals extends ItemBlock
{
	private static final String[] petals = new String[] {"bigflowerred", "bigfloweryellow"};

	public ItemBlockPetals(Block block)
	{
		super(block);
		
		this.setMaxDamage(0);
		this.setHasSubtypes(true);
	}

	@Override
	public int getMetadata(int meta)
	{
		return meta & 15;
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= petals.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + petals[meta];
	}
}