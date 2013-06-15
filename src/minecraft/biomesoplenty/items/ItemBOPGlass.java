package biomesoplenty.items;

import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;

public class ItemBOPGlass extends ItemBlock
{
	private static final String[] glassTypes = new String[] {"celestialLens", "sacrificialFocus_empty", "sacrificialFocus_active"};

	public ItemBOPGlass(int par1)
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
		if (meta < 0 || meta >= glassTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + glassTypes[meta];
	}
}
