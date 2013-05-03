package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;

public class ItemBOPHoe extends ItemHoe
{
	public int TextureID = 0;
	
	public ItemBOPHoe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudhoe"); }
    	else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethysthoe"); }
    	else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
