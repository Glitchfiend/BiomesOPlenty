package biomesoplenty.armor;

import biomesoplenty.CommonProxy;
import biomesoplenty.configuration.BOPItems;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;

public class ArmorMuddy extends ItemArmor implements IArmorTextureProvider
{
	public int textureID = 0;
	
	public ArmorMuddy(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
	}
	
	public String getArmorTextureFile(ItemStack par1) {
		if(par1.itemID == BOPItems.helmetMud.itemID||par1.itemID == BOPItems.chestplateMud.itemID||par1.itemID == BOPItems.bootsMud.itemID){
			return CommonProxy.ARMOR_MUD1_PNG;
		}
		if(par1.itemID == BOPItems.leggingsMud.itemID){
			return CommonProxy.ARMOR_MUD2_PNG;
		}
		return null;
	}

	public void updateIcons(IconRegister iconRegister)
	{
    	if(textureID==0){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudhelmet"); }
    	else if(textureID==1){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudchestplate"); }
    	else if(textureID==2){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudleggings"); }
    	else if(textureID==3){ iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudboots"); }
    	else { iconIndex = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
