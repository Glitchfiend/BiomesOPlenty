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
		//Scythe
		if (var2 == new ItemStack(Items.scytheAmethyst.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		if (var2 == new ItemStack(Items.scytheDiamond.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		if (var2 == new ItemStack(Items.scytheGold.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		if (var2 == new ItemStack(Items.scytheIron.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		if (var2 == new ItemStack(Items.scytheWood.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		if (var2 == new ItemStack(Items.scytheMud.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achScythe, 1);
		}
		
		//Dart Blower
		if (var2 == new ItemStack(Items.dartBlower.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achDartBlower, 1);
		}
		
		//Flower Band
		if (var2 == new ItemStack(Items.flowerBand.get(), 1, 0))
		{
			var1.addStat(BOPAchievements.achFlowerBand, 1);
		}
		if (var2 == new ItemStack(Items.flowerBand.get(), 1, 1))
		{
			var1.addStat(BOPAchievements.achFlowerBand, 1);
		}
		if (var2 == new ItemStack(Items.flowerBand.get(), 1, 2))
		{
			var1.addStat(BOPAchievements.achFlowerBand, 1);
		}
		if (var2 == new ItemStack(Items.flowerBand.get(), 1, 3))
		{
			var1.addStat(BOPAchievements.achFlowerBand, 1);
		}
		
	}

	@Override
	public void onSmelting(EntityPlayer var1, ItemStack var2) {}
}
