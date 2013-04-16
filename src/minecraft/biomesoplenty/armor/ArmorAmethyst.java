package biomesoplenty.armor;

import biomesoplenty.CommonProxy;
import biomesoplenty.configuration.BOPItems;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ArmorAmethyst extends ItemArmor implements IArmorTextureProvider
{
	public int textureID = 0;
	
	public ArmorAmethyst(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
	}
	
	public String getArmorTextureFile(ItemStack par1) {
		if(par1.itemID == BOPItems.helmetAmethyst.itemID||par1.itemID == BOPItems.chestplateAmethyst.itemID||par1.itemID == BOPItems.bootsAmethyst.itemID){
			return CommonProxy.ARMOR_AMETHYST1_PNG;
		}
		if(par1.itemID == BOPItems.leggingsAmethyst.itemID){
			return CommonProxy.ARMOR_AMETHYST2_PNG;
		}
		return null;
	}
	
	public void registerIcons(IconRegister iconRegister)
	{
    	if(textureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethysthelmet"); }
    	else if(textureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystchestplate"); }
    	else if(textureID==2){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystleggings"); }
    	else if(textureID==3){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystboots"); }
    	else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
