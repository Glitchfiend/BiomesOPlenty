package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ItemBOPHoe extends ItemHoe
{
	public int TextureID = 0;

	public ItemBOPHoe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		TextureID = texture;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (TextureID == 1 && par2ItemStack.itemID == Items.miscItems.get().itemID && par2ItemStack.getItemDamage() == 2)
			return true;
		else
			return false;
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudhoe"); }
		else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethysthoe"); }
		else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
