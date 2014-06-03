package biomesoplenty.common.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCItems;

public class ItemMuddyArmor extends ItemArmor
{
	public int textureID = 0;

	public ItemMuddyArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) 
	{
		super(armorMaterial, renderIndex, armorType);
		
		textureID = armorType;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItem() == BOPCItems.helmetMud || stack.getItem() == BOPCItems.chestplateMud || stack.getItem() == BOPCItems.bootsMud)
			return "biomesoplenty:textures/armor/mud_1.png";
		
		if (stack.getItem() == BOPCItems.leggingsMud)
			return "biomesoplenty:textures/armor/mud_2.png";
		
		return null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 0) { itemIcon = iconRegister.registerIcon("biomesoplenty:mudhelmet"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("biomesoplenty:mudchestplate"); }
		else if (textureID == 2) { itemIcon = iconRegister.registerIcon("biomesoplenty:mudleggings"); }
		else if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:mudboots"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
