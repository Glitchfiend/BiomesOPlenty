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

public class ItemGems extends Item
{
	private static String[] gems = {"amethyst", "ruby", "peridot", "topaz", "tanzanite", "malachite", "sapphire", "amber"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemGems()
	{
		setMaxDamage(0);
		
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[gems.length];

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
	public IIcon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}

	@Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for (int meta = 0; meta < gems.length; ++meta) 
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
