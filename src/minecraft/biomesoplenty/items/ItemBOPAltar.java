package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPAltar extends ItemBlock
{
	private static final String[] altarTypes = new String[] {"altarframe"};

	public ItemBOPAltar(int par1)
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
		if (meta < 0 || meta >= altarTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + altarTypes[meta];
	}
}
