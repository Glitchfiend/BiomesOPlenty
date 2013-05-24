package biomesoplenty.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.IArmorTextureProvider;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.CommonProxy;
import biomesoplenty.api.Items;

public class ArmorAmethyst extends ItemArmor implements IArmorTextureProvider
{
	public int textureID = 0;
	
	public ArmorAmethyst(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) {
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}
	
	@Override
    public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
    {
		if (par2ItemStack.itemID == Items.miscItems.get().itemID && par2ItemStack.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
    }
	
	public String getArmorTextureFile(ItemStack par1) {
		if(par1.itemID == Items.helmetAmethyst.get().itemID||par1.itemID == Items.chestplateAmethyst.get().itemID||par1.itemID == Items.bootsAmethyst.get().itemID){
			return CommonProxy.ARMOR_AMETHYST1_PNG;
		}
		if(par1.itemID == Items.leggingsAmethyst.get().itemID){
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
