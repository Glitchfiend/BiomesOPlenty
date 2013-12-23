package biomesoplenty.handlers;

import net.minecraft.block.Block;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.stats.AchievementList;
import biomesoplenty.api.BOPBlocks;
import biomesoplenty.api.BOPItems;
import biomesoplenty.core.BOPAchievements;
import cpw.mods.fml.common.IPickupNotifier;

public class BOPPickupHandler implements IPickupNotifier

{
	@Override
	public void notifyPickup(EntityItem item, EntityPlayer player)
	{
		//Getting Wood (Vanilla
		if (item.getEntityItem().itemID == BOPBlocks.logs1.get().blockID || item.getEntityItem().itemID == BOPBlocks.logs2.get().blockID || item.getEntityItem().itemID == BOPBlocks.logs3.get().blockID || item.getEntityItem().itemID == BOPBlocks.logs4.get().blockID)
		{
			player.addStat(AchievementList.mineWood, 1);
		}
		
		//Flower Child
		if (item.getEntityItem().itemID == BOPBlocks.flowers.get().blockID || item.getEntityItem().itemID == BOPBlocks.flowers2.get().blockID || item.getEntityItem().itemID == Block.plantRed.blockID || item.getEntityItem().itemID == Block.plantYellow.blockID)
		{
			player.addStat(BOPAchievements.achFlower, 1);
		}
		
		//Berry Picking
		if (item.getEntityItem().itemID == BOPItems.food.get().itemID && (item.getEntityItem().getItemDamage() == 0))
		{
			player.addStat(BOPAchievements.achBerry, 1);
		}
		
		//Rather Thorny
		if (item.getEntityItem().itemID == BOPBlocks.plants.get().blockID && (item.getEntityItem().getItemDamage() == 5))
		{
			player.addStat(BOPAchievements.achThorn, 1);
		}
		
		//A Rolling Stone
		if (item.getEntityItem().itemID == BOPBlocks.moss.get().blockID)
		{
			player.addStat(BOPAchievements.achMoss, 1);
		}
		
		//Nutritious
		if (item.getEntityItem().itemID == BOPBlocks.coral.get().blockID && (item.getEntityItem().getItemDamage() > 3))
		{
			player.addStat(BOPAchievements.achCoral, 1);
		}
		
		//Bittersweet
		if (item.getEntityItem().itemID == BOPItems.food.get().itemID && (item.getEntityItem().getItemDamage() == 9))
		{
			player.addStat(BOPAchievements.achHoney, 1);
		}
		
		//Decay
		if (item.getEntityItem().itemID == BOPBlocks.plants.get().blockID && (item.getEntityItem().getItemDamage() == 13))
		{
			player.addStat(BOPAchievements.achWitherWart, 1);
		}
		
		//Gravedigger
		if (item.getEntityItem().itemID == BOPBlocks.grave.get().blockID)
		{
			player.addStat(BOPAchievements.achGrave, 1);
		}
		
		//Phantom Menace
		if (item.getEntityItem().itemID == BOPItems.miscItems.get().itemID && (item.getEntityItem().getItemDamage() == 10))
		{
			player.addStat(BOPAchievements.achPhantom, 1);
		}
		
		//Blue Sky
		if (item.getEntityItem().itemID == BOPItems.miscItems.get().itemID && (item.getEntityItem().getItemDamage() == 4))
		{
			player.addStat(BOPAchievements.achCelestial, 1);
		}
	}
}
