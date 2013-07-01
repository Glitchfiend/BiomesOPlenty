package biomesoplenty.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ArmorMuddy extends ItemArmor
{
	public int textureID = 0;

	public ArmorMuddy(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) 
	{
		if (stack.itemID == Items.helmetMud.get().itemID || stack.itemID == Items.chestplateMud.get().itemID || stack.itemID == Items.bootsMud.get().itemID)
			return "/mods/BiomesOPlenty/textures/armor/mud_1.png";
		if (stack.itemID == Items.leggingsMud.get().itemID)
			return "/mods/BiomesOPlenty/textures/armor/mud_2.png";
		
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (textureID == 0) { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudhelmet"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudchestplate"); }
		else if (textureID == 2) { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudleggings"); }
		else if (textureID == 3) { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudboots"); }
		else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
