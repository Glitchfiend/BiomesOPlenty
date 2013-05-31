package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ItemBOPSword extends ItemSword
{
	public int TextureID = 0;

	public ItemBOPSword(int par1, EnumToolMaterial par2, int texture)
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
		if(TextureID==0){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudsword"); }
		else if(TextureID==1){ itemIcon = iconRegister.registerIcon("BiomesOPlenty:amethystsword"); }
		else { itemIcon = iconRegister.registerIcon("BiomesOPlenty:mudball"); }
	}
}
