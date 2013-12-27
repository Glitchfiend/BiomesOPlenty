package biomesoplenty.common.itemblocks;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemBlockStoneFormations extends ItemBlock
{
	private static final String[] forms = new String[] {"stalagmite", "stalactite"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBlockStoneFormations(Block block)
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
		if (meta < 0 || meta >= forms.length) 
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + forms[meta];
	}
	
	@Override
	public IIcon getIconFromDamage(int meta)
	{
		//TODO: block		  getIcon()
		return field_150939_a.func_149691_a(0, meta);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public boolean isFull3D()
	{
		return true;
	}
}
