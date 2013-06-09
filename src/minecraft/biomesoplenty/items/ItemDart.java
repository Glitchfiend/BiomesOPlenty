package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemDart extends Item
{
	private static final String[] dartTypes = new String[] {"dart", "poisondart"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemDart(int par1)
	{
		super(par1);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for (int i = 0; i < dartTypes.length; ++i)
		{
			par3List.add(new ItemStack(par1, 1, i));
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
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[dartTypes.length];

		for (int i = 0; i < dartTypes.length; ++i) {
			textures[i] = iconRegister.registerIcon("BiomesOPlenty:" + dartTypes[i]);
		}
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		return textures[meta];
	}
}
