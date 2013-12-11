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

public class ItemBOP extends Item
{
	private static String[] items = {"mudbrick", "ash", "emptyhoneycomb", "fleshchunk", "crystalshard", "bluedye", "browndye", "greendye", "whitedye", "blackdye", "ghastlysoul", "pixiedust"};
	@SideOnly(Side.CLIENT)
	private Icon[] textures;

	public ItemBOP(int id)
	{
		super(id);
		setMaxDamage(0);
		setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		textures = new Icon[items.length];

		for (int i = 0; i < items.length; ++i) {
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+items[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= items.length) {
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + items[meta];
	}

	@Override
	public Icon getIconFromDamage(int meta)
	{
		if (meta < 0 || meta >= textures.length) {
			meta = 0;
		}

		return textures[meta];
	}
	
	@Override
    public int getItemStackLimit(ItemStack par1ItemStack)
    {
    	if (par1ItemStack.itemID == this.itemID && par1ItemStack.getItemDamage() == 10)
    	{
    		return 1;
    	}
    	
        return 64;
    }

	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Override
	public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
	{
		for(int meta = 0; meta < items.length; ++meta) {
			subTypes.add(new ItemStack(itemId, 1, meta));
		}
	}
}
