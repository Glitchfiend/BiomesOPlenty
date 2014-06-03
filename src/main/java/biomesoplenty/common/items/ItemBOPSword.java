package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemSword;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCItems;

public class ItemBOPSword extends ItemSword
{
	public int textureID = 0;

	public ItemBOPSword(ToolMaterial toolMaterial, int texture)
	{
		super(toolMaterial);
		
		this.textureID = texture;
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		if (textureID == 1 && itemToRepairWith.getItem() == BOPCItems.misc && itemToRepairWith.getItemDamage() == 2)
			return true;
		else
			return false;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID == 0) { itemIcon = iconRegister.registerIcon("biomesoplenty:mudsword"); }
		else if (textureID == 1) { itemIcon = iconRegister.registerIcon("biomesoplenty:amethystsword"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
