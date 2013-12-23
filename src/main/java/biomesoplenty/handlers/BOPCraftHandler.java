package biomesoplenty.handlers;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;
import net.minecraft.item.ItemStack;
import biomesoplenty.api.BOPItems;
import biomesoplenty.core.BOPAchievements;
import cpw.mods.fml.common.ICraftingHandler;

public class BOPCraftHandler implements ICraftingHandler
{
	@Override
	public void onCrafting(EntityPlayer var1, ItemStack var2, IInventory var3)
	{
		//Plant Killer
        if (var2.itemID == BOPItems.scytheAmethyst.get().itemID || var2.itemID == BOPItems.scytheDiamond.get().itemID || var2.itemID == BOPItems.scytheGold.get().itemID || var2.itemID == BOPItems.scytheIron.get().itemID || var2.itemID == BOPItems.scytheStone.get().itemID || var2.itemID == BOPItems.scytheWood.get().itemID || var2.itemID == BOPItems.scytheMud.get().itemID)
        {
        	var1.addStat(BOPAchievements.achScythe, 1);
        }
		
		//Full Auto
        if (var2.itemID == BOPItems.dartBlower.get().itemID)
        {
        	var1.addStat(BOPAchievements.achDartBlower, 1);
        }
		
		//Flower Power
        if (var2.itemID == BOPItems.flowerBand.get().itemID)
        {
        	var1.addStat(BOPAchievements.achFlowerBand, 1);
        }
        
        //Drink of the Gods
        if (var2.itemID == BOPItems.food.get().itemID && var2.getItemDamage() == 10)
        {
        	var1.addStat(BOPAchievements.achAmbrosia, 1);
        }
		
	}

	@Override
	public void onSmelting(EntityPlayer var1, ItemStack var2) {}
}
