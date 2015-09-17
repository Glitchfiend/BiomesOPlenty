package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockFlower2 extends ItemBlock
{
	private static final String[] plants = new String[] {"hibiscus", "lilyofthevalley", "burningblossom", "lavender", "goldenrod", "bluebells", "minersdelight", "icyiris", "rose"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBlockFlower2(Block block)
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

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
}
