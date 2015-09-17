package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockMushroom extends ItemBlock
{
	private static final String[] plants = new String[] {"toadstool", "portobello", "bluemilk", "glowshroom", "flatmushroom", "shadowshroom"};

	public ItemBlockMushroom(Block block)
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
		if (meta < 0 || meta >= plants.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + plants[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return field_150939_a.getIcon(0, meta);
	}
}
