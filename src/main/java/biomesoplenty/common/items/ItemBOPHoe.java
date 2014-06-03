package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemHoe;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.content.BOPCItems;

public class ItemBOPHoe extends ItemHoe
{
	public int textureID = 0;

	public ItemBOPHoe(ToolMaterial par2, int texture)
	{
		super(par2);
		
		this.textureID = texture;
		
		setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
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
		if (textureID==0){ itemIcon = iconRegister.registerIcon("biomesoplenty:mudhoe"); }
		else if (textureID==1){ itemIcon = iconRegister.registerIcon("biomesoplenty:amethysthoe"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
