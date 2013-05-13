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

public class ItemBOP extends Item
{
    private static String[] items = {"mudbrick", "ash", "amethyst", "poison", "crystalshard", "bluedye", "browndye", "greendye", "whitedye", "blackdye"};
    @SideOnly(Side.CLIENT)
    private Icon[] textures;

	public ItemBOP(int id)
	{
		super(id);
		setMaxDamage(0);
        setHasSubtypes(true);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	public void registerIcons(IconRegister iconRegister)
	{
	    textures = new Icon[items.length];
        
        for (int i = 0; i < items.length; ++i)
            textures[i] = iconRegister.registerIcon("BiomesOPlenty:"+items[i]);
	}
	
	@Override
    public String getUnlocalizedName(ItemStack itemStack)
    {
        return items[itemStack.getItemDamage()];
    }
    
    @Override
    public Icon getIconFromDamage(int meta)
    {
        return textures[meta];
    }
    
    @SuppressWarnings({ "rawtypes", "unchecked" })
    @Override
    public void getSubItems(int itemId, CreativeTabs creativeTab, List subTypes)
    {
        for(int meta = 0; meta < items.length; ++meta)
            subTypes.add(new ItemStack(itemId, 1, meta));
    }
}
