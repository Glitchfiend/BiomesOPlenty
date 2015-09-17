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

public class ItemBOPMisc extends Item
{
	private static String[] items = {"mudbrick", "ash", "emptyhoneycomb", "fleshchunk", "crystalshard", "bluedye", "browndye", "greendye", "whitedye", "blackdye", "ghastlysoul", "pixiedust", "ichor", "pinecone"};
	@SideOnly(Side.CLIENT)
	private IIcon[] textures;

	public ItemBOPMisc()
	{
		this.setMaxDamage(0);
		
		this.setHasSubtypes(true);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		textures = new IIcon[items.length];

		for (int i = 0; i < items.length; ++i) 
		{
			textures[i] = iconRegister.registerIcon("biomesoplenty:"+items[i]);
		}
	}

	@Override
	public String getUnlocalizedName(ItemStack itemStack)
	{
		int meta = itemStack.getItemDamage();
		if (meta < 0 || meta >= items.length) 
		{
			meta = 0;
		}

		return super.getUnlocalizedName() + "." + items[meta];
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
    public int getItemStackLimit(ItemStack itemStack)
    {
    	if (itemStack.getItemDamage() == 10)
    	{
    		return 1;
    	}
    	
        return 64;
    }

	@Override
    public void getSubItems(Item item, CreativeTabs creativeTabs, List list)
	{
		for(int meta = 0; meta < items.length; ++meta) 
		{
			list.add(new ItemStack(item, 1, meta));
		}
	}
}
