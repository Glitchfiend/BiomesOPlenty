package tdwp_ftw.biomesop.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemSword;

public class ItemBOPSpade extends ItemSpade
{
	public int TextureID = 0;
	
	public ItemBOPSpade(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
	}
	
	public void updateIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudshovel"); }
    	else if(TextureID==1){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:amethystshovel"); }
    	else { iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
