package tdwp_ftw.biomesop.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemSword;

public class ItemBOPPickaxe extends ItemPickaxe
{
	public int TextureID = 0;
	
	public ItemBOPPickaxe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
	}
	
	public void updateIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudpickaxe"); }
    	else if(TextureID==1){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:amethystpickaxe"); }
    	else { iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
