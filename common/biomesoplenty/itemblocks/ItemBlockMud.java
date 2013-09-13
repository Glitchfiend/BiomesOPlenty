package biomesoplenty.itemblocks;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBlockMud extends ItemBlock
{
	private static final String[] types = new String[] {"mud", "quicksand"};

	public ItemBlockMud(int par1)
	{
		super(par1);
		setMaxDamage(0);
		setHasSubtypes(true);
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
		if (meta < 0 || meta >= types.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + types[meta];
	}
}
