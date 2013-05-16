package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.ItemFood;
import net.minecraft.potion.Potion;

public class ItemBerries extends ItemFood
{
	public ItemBerries(int par1, int par2, float par3, boolean par4)
	{
		super(par1, par2, par3, par4);
		setAlwaysEdible().setUnlocalizedName("berries");
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	itemIcon = iconRegister.registerIcon("BiomesOPlenty:berries");
	}
}
