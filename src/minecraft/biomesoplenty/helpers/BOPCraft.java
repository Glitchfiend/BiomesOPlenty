package biomesoplenty.helpers;

import biomesoplenty.api.Blocks;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPConfiguration;
import cpw.mods.fml.common.ICraftingHandler;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;

public class BOPCraft implements ICraftingHandler 
{
	public void onCrafting(EntityPlayer var1, ItemStack var2, IInventory var3)
	{
		if (var2.itemID == Items.flowerBand.get().itemID && BOPConfiguration.achievements)
			var1.addStat(AchievementHelper.achFlowerP, 1);
	}

	public void onSmelting(EntityPlayer var1, ItemStack var2) {}
}
