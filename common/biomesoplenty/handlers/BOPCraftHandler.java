package biomesoplenty.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPAchievements;
import cpw.mods.fml.common.ICraftingHandler;

public class BOPCraftHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer var1, ItemStack var2, IInventory var3)
	{
		//Plant Killer
        if (var2.itemID == Items.scytheAmethyst.get().itemID || var2.itemID == Items.scytheDiamond.get().itemID || var2.itemID == Items.scytheGold.get().itemID || var2.itemID == Items.scytheIron.get().itemID || var2.itemID == Items.scytheStone.get().itemID || var2.itemID == Items.scytheWood.get().itemID || var2.itemID == Items.scytheMud.get().itemID)
        {
        	var1.addStat(BOPAchievements.achScythe, 1);
        }
		
		//Full Auto
        if (var2.itemID == Items.dartBlower.get().itemID)
        {
        	var1.addStat(BOPAchievements.achDartBlower, 1);
        }
		
		//Flower Power
        if (var2.itemID == Items.flowerBand.get().itemID)
        {
        	var1.addStat(BOPAchievements.achFlowerBand, 1);
        }
		
	}

	@Override
	public void onSmelting(EntityPlayer var1, ItemStack var2) {}
}
