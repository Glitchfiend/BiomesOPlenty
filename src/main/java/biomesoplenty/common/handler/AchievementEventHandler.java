package biomesoplenty.common.handler;

import biomesoplenty.common.block.BlockBOPLog;
import net.minecraft.block.Block;
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
		
		if (block != null && block instanceof BlockBOPLog)
		{
			event.player.addStat(AchievementList.mineWood, 1);
		}
	}
}
