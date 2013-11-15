package biomesoplenty.items;

import java.util.List;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Icon;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ItemJarFilled extends Item
{
	private static String[] jars = {"jarhoney", "jarpoison"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemJarFilled(int id)
	{
		super(id);
		setMaxDamage(0);
		setMaxStackSize(1);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[jars.length];

		for (int i = 0; i < jars.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+jars[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= jars.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + jars[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
	{
		for(int meta = 0; meta < jars.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}
