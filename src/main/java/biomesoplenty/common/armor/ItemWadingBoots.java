package biomesoplenty.common.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCItems;

public class ItemWadingBoots extends ItemArmor
{
	public int textureID = 0;

	public ItemWadingBoots(ArmorMaterial armorMaterial, int renderIndex, int armorType) 
	{
		super(armorMaterial, renderIndex, armorType);
		
		textureID = armorType;
		
		this.setMaxDamage(0);
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItem() == BOPCItems.wadingBoots)
			return "biomesoplenty:textures/armor/wadingboots.png";
		
		return null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:wadingboots"); }
	}
}
