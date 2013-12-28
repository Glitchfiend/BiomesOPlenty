package biomesoplenty.common.items;

import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.item.ItemSpade;
import net.minecraft.item.ItemStack;
import biomesoplenty.BiomesOPlenty;
import biomesoplenty.api.BOPItemHelper;

public class ItemBOPSpade extends ItemSpade
{
	public int textureID = 0;

	public ItemBOPSpade(ToolMaterial toolMaterial, int texture)
	{
		super(toolMaterial);
		
		this.textureID = texture;
		
		this.setHarvestLevel("shovel", 4);
		
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
		if (textureID == 0){ itemIcon = iconRegister.registerIcon("biomesoplenty:mudshovel"); }
		else if (textureID == 1){ itemIcon = iconRegister.registerIcon("biomesoplenty:amethystshovel"); }
		else { itemIcon = iconRegister.registerIcon("biomesoplenty:mudball"); }
	}
}
