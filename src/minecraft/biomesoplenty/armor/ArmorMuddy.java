package biomesoplenty.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.CommonProxy;
import biomesoplenty.api.Items;

public class ArmorMuddy extends ItemArmor implements IArmorTextureProvider
{
	public int textureID = 0;

	public ArmorMuddy(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTextureFile(ItemStack par1) {
		if(par1.itemID == Items.helmetMud.get().itemID||par1.itemID == Items.chestplateMud.get().itemID||par1.itemID == Items.bootsMud.get().itemID)
			return CommonProxy.ARMOR_MUD1_PNG;
		if(par1.itemID == Items.leggingsMud.get().itemID)
			return CommonProxy.ARMOR_MUD2_PNG;
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(textureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudhelmet"); }
		else if(textureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudchestplate"); }
		else if(textureID==2){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudleggings"); }
		else if(textureID==3){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudboots"); }
		else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
