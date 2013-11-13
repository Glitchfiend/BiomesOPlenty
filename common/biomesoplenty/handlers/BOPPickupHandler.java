package biomesoplenty.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import biomesoplenty.api.Blocks;
import biomesoplenty.api.Fluids;
import biomesoplenty.api.Items;
import biomesoplenty.configuration.BOPAchievements;
import cpw.mods.fml.common.IPickupNotifier;

public class BOPPickupHandler implements IPickupNotifier

{
	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
	{
		//Flower Child
		if (item.getEntityItem().itemID == Blocks.flowers.get().blockID || item.getEntityItem().itemID == Blocks.flowers2.get().blockID || item.getEntityItem().itemID == Block.plantRed.blockID || item.getEntityItem().itemID == Block.plantYellow.blockID)
		{
			player.addStat(BOPAchievements.achFlower, 1);
		}
		
		//Berry Picking
		if (item.getEntityItem().itemID == Items.food.get().itemID && (item.getEntityItem().getItemDamage() == 0))
		{
			player.addStat(BOPAchievements.achBerry, 1);
		}
		
		//Rather Thorny
		if (item.getEntityItem().itemID == Blocks.plants.get().blockID && (item.getEntityItem().getItemDamage() == 5))
		{
			player.addStat(BOPAchievements.achThorn, 1);
		}
		
		//A Rolling Stone
		if (item.getEntityItem().itemID == Blocks.moss.get().blockID)
		{
			player.addStat(BOPAchievements.achMoss, 1);
		}
		
		//Nutritious
		if (item.getEntityItem().itemID == Blocks.coral.get().blockID && (item.getEntityItem().getItemDamage() > 3))
		{
			player.addStat(BOPAchievements.achCoral, 1);
		}
		
		//Decay
		if (item.getEntityItem().itemID == Blocks.plants.get().blockID && (item.getEntityItem().getItemDamage() == 13))
		{
			player.addStat(BOPAchievements.achWitherWart, 1);
		}
		
		//Gravedigger
		if (item.getEntityItem().itemID == Blocks.grave.get().blockID)
		{
			player.addStat(BOPAchievements.achGrave, 1);
		}
		
		//Phantom Menace
		if (item.getEntityItem().itemID == Items.miscItems.get().itemID && (item.getEntityItem().getItemDamage() == 16))
		{
			player.addStat(BOPAchievements.achPhantom, 1);
		}
		
		//Blue Sky
		if (item.getEntityItem().itemID == Items.miscItems.get().itemID && (item.getEntityItem().getItemDamage() == 4))
		{
			player.addStat(BOPAchievements.achCelestial, 1);
		}
	}
}
