package tdwp_ftw.biomesop.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemSword;

public class ItemBOPHoe extends ItemHoe
{
	public int TextureID = 0;
	
	public ItemBOPHoe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
	}
	
	public void updateIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudhoe"); }
    	else if(TextureID==1){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:amethysthoe"); }
    	else { iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
