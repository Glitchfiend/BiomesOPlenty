/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.handler;

import biomesoplenty.api.achievement.BOPAchievements;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.stats.AchievementList;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.gameevent.PlayerEvent;

public class AchievementEventHandler 
{
	@SubscribeEvent
	public void onItemPickup(PlayerEvent.ItemPickupEvent event)
	{
		Item item = event.pickedUp.getEntityItem().getItem();
		Block block = Block.getBlockFromItem(item);
		EntityPlayer player = event.player;
		
		if (block != null && block instanceof BlockBOPLog)
		{
			event.player.addStat(AchievementList.mineWood, 1);
		}
		
		//Flower Child Achievement
		if (block != null && block instanceof BlockBOPFlower)
		{
		    player.addStat(BOPAchievements.obtain_flowers, 1);
		}
	}
}
