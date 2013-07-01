package biomesoplenty.items;

import net.minecraft.item.ItemBlock;

public class ItemBOPGrave extends ItemBlock
{
	public ItemBOPGrave(int par1)
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
}
