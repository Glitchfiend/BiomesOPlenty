package biomesoplenty.items;

import biomesoplenty.BiomesOPlenty;
import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;

public class ItemBOPPickaxe extends ItemPickaxe
{
	public int TextureID = 0;
	
	public ItemBOPPickaxe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudpickaxe"); }
    	else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystpickaxe"); }
    	else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
