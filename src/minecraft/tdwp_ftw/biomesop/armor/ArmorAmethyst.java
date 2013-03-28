package tdwp_ftw.biomesop.armor;

import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;
import tdwp_ftw.biomesop.CommonProxy;

public class ArmorAmethyst extends ItemArmor implements IArmorTextureProvider
{
	public ArmorAmethyst(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
	}
	
	public String getArmorTextureFile(ItemStack par1) {
		if(par1.itemID == mod_BiomesOPlenty.helmetAmethyst.itemID||par1.itemID == mod_BiomesOPlenty.chestplateAmethyst.itemID||par1.itemID == mod_BiomesOPlenty.bootsAmethyst.itemID){
			return CommonProxy.ARMOR_AMETHYST1_PNG;
		}
		if(par1.itemID == mod_BiomesOPlenty.leggingsAmethyst.itemID){
			return CommonProxy.ARMOR_AMETHYST2_PNG;
		}
		return null;
	}
}
