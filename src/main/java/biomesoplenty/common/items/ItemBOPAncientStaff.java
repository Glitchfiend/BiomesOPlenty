package biomesoplenty.common.items;

import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;

import java.util.List;

public class ItemBOPAncientStaff extends Item
{
	private static String[] parts = {"ancientstaff", "staffhandle", "staffpole", "stafftopper", "ancientstaffbroken"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBOPAncientStaff()
	{
		this.maxStackSize = 1;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public boolean isFull3D()
    {
		return true;
    }

	@Override
	public boolean hasEffect(ItemStack itemStack, int pass)
	{
		if (itemStack.getItemDamage() == 0)
			return true;
		else
			return false;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[parts.length];

		for (int i = 0; i < parts.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + parts[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= parts.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + parts[meta];
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) 
		{
			meta = 0;
		}

		return textures[meta];
	}

	@Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int meta = 0; meta < parts.length; ++meta) 
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
