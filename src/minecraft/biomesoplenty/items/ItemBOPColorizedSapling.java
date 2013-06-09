package biomesoplenty.items;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;

public class ItemBOPColorizedSapling extends ItemBlock
{
	private static final String[] saplings = new String[] {"acacia", "mangrove", "palm", "redwood", "willow", "pine"};

	public ItemBOPColorizedSapling(int par1)
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
		if (meta < 0 || meta >= saplings.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(saplings[meta]).append("Sapling").toString();
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return Block.blocksList[itemID].getIcon(0, meta);
	}
}
