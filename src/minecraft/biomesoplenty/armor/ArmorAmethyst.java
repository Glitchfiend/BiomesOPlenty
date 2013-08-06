package biomesoplenty.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ArmorAmethyst extends ItemArmor
{
	public int textureID = 0;

	public ArmorAmethyst(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (par2ItemStack.itemID == Items.miscItems.get().itemID && par2ItemStack.getItemDamage() == 2)
			return true;
		
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, int layer) 
	{
		if (stack.itemID == Items.helmetAmethyst.get().itemID || stack.itemID == Items.chestplateAmethyst.get().itemID || stack.itemID == Items.bootsAmethyst.get().itemID)
			return "/mods/biomesoplenty/textures/armor/amethyst_1.png";
		if (stack.itemID == Items.leggingsAmethyst.get().itemID)
			return "/mods/biomesoplenty/textures/armor/amethyst_2.png";
		
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (textureID == 0) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethysthelmet"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystchestplate"); }
		else if (textureID == 2) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystleggings"); }
		else if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystboots"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
