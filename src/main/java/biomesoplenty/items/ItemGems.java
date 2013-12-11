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

public class ItemGems extends Item
{
	private static String[] gems = {"amethyst", "ruby", "peridot", "topaz", "tanzanite", "malachite", "sapphire"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemGems(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[gems.length];

		for (int i = 0; i < gems.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+gems[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= gems.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + gems[meta];
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
		for(int meta = 0; meta < gems.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}
