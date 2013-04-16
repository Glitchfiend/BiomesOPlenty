package com.bopteam.biomesop.helpers;

import com.bopteam.biomesop.mod_BiomesOPlenty;

import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;

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