package biomesoplenty.armor;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ArmorWadingBoots extends ItemArmor
{
	public int textureID = 0;

	public ArmorWadingBoots(int par1, EnumArmorMaterial par2EnumArmorMaterial, int par3, int par4) 
	{
		super(par1, par2EnumArmorMaterial, par3, par4);
		textureID = par4;
		setMaxDamage(0);
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.itemID == Items.wadingBoots.get().itemID)
			return "biomesoplenty:textures/armor/wadingboots.png";
		
		return null;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:wadingboots"); }
	}
}
