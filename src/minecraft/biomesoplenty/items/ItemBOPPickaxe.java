package biomesoplenty.items;

import net.minecraft.client.renderer.texture.IconRegister;
import net.minecraft.item.EnumToolMaterial;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.Items;

public class ItemBOPPickaxe extends ItemPickaxe
{
	public int textureID = 0;

	public ItemBOPPickaxe(int par1, EnumToolMaterial par2, int texture)
	{
		super(par1, par2);
		textureID = texture;
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack par1ItemStack, ItemStack par2ItemStack)
	{
		if (textureID == 1 && par2ItemStack.itemID == Items.miscItems.get().itemID && par2ItemStack.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void registerIcons(IconRegister iconRegister)
	{
		if (textureID == 0)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudpickaxe"); 
		}
		else if (textureID == 1)
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:amethystpickaxe"); 
		}
		else 
		{ 
			itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); 
		}
	}
}
