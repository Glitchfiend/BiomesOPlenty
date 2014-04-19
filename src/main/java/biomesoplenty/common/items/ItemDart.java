package biomesoplenty.common.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDart extends Item
{
	private static final String[] dartTypes = new String[] {"dart", "poisondart"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemDart()
	{
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int i = 0; i < dartTypes.length; ++i)
		{
			list.add(new ItemStack(item, 1, i));
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= dartTypes.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + dartTypes[meta];
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[dartTypes.length];

		for (int i = 0; i < dartTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:" + dartTypes[i]);
		}
	}

	@Override
	public IIcon getIconFromDamage(int meta)
	{
		return textures[meta];
	}
}
