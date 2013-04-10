package tdwp_ftw.biomesop.helpers;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import tdwp_ftw.biomesop.mod_BiomesOPlenty;

public class AchievementPickup
{
	@SuppressWarnings("unused")
	private ItemStack pickupItemStack;

	@ForgeSubscribe
	public void EntityItemPickupEvent(EntityItemPickupEvent event)
	{
		mod_BiomesOPlenty.onItemPickup(event.entityPlayer, event.item.getEntityItem());
	}
}