package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemAxe;

public class ItemBOPAxe extends ItemAxe
{
	public int TextureID = 0;
	
	public ItemBOPAxe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudaxe"); }
    	else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystaxe"); }
    	else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
