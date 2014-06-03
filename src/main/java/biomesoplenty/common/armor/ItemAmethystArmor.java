package biomesoplenty.common.armor;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;
import biomesoplenty.api.content.BOPCItems;

public class ItemAmethystArmor extends ItemArmor
{
	public int textureID = 0;

	public ItemAmethystArmor(ArmorMaterial armorMaterial, int renderIndex, int armorType) 
	{
		super(armorMaterial, renderIndex, armorType);
		
		textureID = armorType;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		if (itemToRepairWith.getItem() == BOPCItems.misc && itemToRepairWith.getItemDamage() == 2)
			return true;
		
		return false;
	}

	@Override
	public String getArmorTexture(ItemStack stack, Entity entity, int slot, String type) 
	{
		if (stack.getItem() == BOPCItems.helmetAmethyst || stack.getItem() == BOPCItems.chestplateAmethyst || stack.getItem() == BOPCItems.bootsAmethyst)
			return "biomesoplenty:textures/armor/amethyst_1.png";
		
		if (stack.getItem() == BOPCItems.leggingsAmethyst)
			return "biomesoplenty:textures/armor/amethyst_2.png";
		
		return null;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 0) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethysthelmet"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystchestplate"); }
		else if (textureID == 2) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystleggings"); }
		else if (textureID == 3) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystboots"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
