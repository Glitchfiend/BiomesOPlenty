package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

public class ItemBlockSapling extends ItemBlock
{
	private static final String[] saplings = new String[] {"apple", "yellowautumn", "bamboo", "magic", "dark", "dead", "fir", "ethereal", "orangeautumn", "origin", "pinkcherry", "maple", "whitecherry", "hellbark", "jacaranda", "persimmon"};
	private static final int MAX = 15;

	public ItemBlockSapling(Block block)
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
		int meta = itemStack.getItemDamageForDisplay() > MAX ? 0 : itemStack.getItemDamageForDisplay();
		return super.getUnlocalizedName() + "." + (new StringBuilder()).append(saplings[meta]).append("Sapling").toString();
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return field_150939_a.getIcon(0, meta);
	}
}
