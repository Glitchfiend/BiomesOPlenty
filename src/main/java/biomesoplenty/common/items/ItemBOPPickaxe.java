package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemPickaxe;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCItems;

public class ItemBOPPickaxe extends ItemPickaxe
{
	public int textureID = 0;

	public ItemBOPPickaxe(ToolMaterial toolMaterial, int texture)
	{
		super(toolMaterial);
		
		this.textureID = texture;
		
		this.setHarvestLevel("pickaxe", 4);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		if (textureID == 1 && itemToRepairWith.getItem() == BOPCItems.misc && itemToRepairWith.getItemDamage() == 2)
		{
			return true;
		}
		else
		{
			return false;
		}
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
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
