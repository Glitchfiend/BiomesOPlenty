package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.Item;
import net.minecraft.item.ItemAxe;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class ItemBOPAxe extends ItemAxe
{
	public int textureID = 0;

	public ItemBOPAxe(Item.ToolMaterial toolMaterial, int texture)
	{
		super(toolMaterial);
		
		this.textureID = texture;
		
		this.setHarvestLevel("axe", 4);
		
		this.setCreativeTab(BiomesOPlenty.tabBiomesOPlenty);
	}

	@Override
	public boolean getIsRepairable(ItemStack itemToRepair, ItemStack itemToRepairWith)
	{
		if (textureID == 1 && itemToRepairWith.getItem() == BOPItemHelper.get("misc") && itemToRepairWith.getItemDamage() == 2)
			return true;
		else
			return false;
	}

	@Override
	public void registerIcons(IIconRegister iconRegister)
	{
		if (textureID==0){ itemIcon = iconRegister.registerIcon("biomesoplenty:mudaxe"); }
		else if (textureID==1){ itemIcon = iconRegister.registerIcon("biomesoplenty:amethystaxe"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
