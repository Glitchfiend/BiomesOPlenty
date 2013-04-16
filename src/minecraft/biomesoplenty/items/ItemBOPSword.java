package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemSword;

public class ItemBOPSword extends ItemSword
{
	public int TextureID = 0;
	
	public ItemBOPSword(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudsword"); }
    	else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystsword"); }
    	else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
