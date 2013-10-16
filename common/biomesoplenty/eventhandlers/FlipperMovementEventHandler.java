package biomesoplenty.eventhandlers;

import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.Items;

public class FlipperMovementEventHandler 
{
	@ForgeSubscribe
	public void onLivingUpdate(LivingUpdateEvent event)
	{
		if (event.entity instanceof EntityLiving)
		{
			EntityLiving entity = (EntityLiving)event.entity;
			
			ItemStack itemstack = entity.getCurrentItemOrArmor(1);

			if (entity.isInWater())
			{
				if (itemstack != null && itemstack.itemID == Items.flippers.get().itemID)
				{
					entity.motionX *= 1.125D;
					entity.motionY *= 1.1D;
					entity.motionZ *= 1.125D;
				}
			}
		}
		
		if (event.entity instanceof EntityPlayer)
		{
			EntityPlayer player = (EntityPlayer)event.entity;
			
			InventoryPlayer inventory = player.inventory;

			if (player.isInWater() && !player.capabilities.isFlying)
			{
				if (inventory.armorInventory[0] != null && inventory.armorInventory[0].itemID == Items.flippers.get().itemID)
				{
					player.motionX *= 1.125D;
					player.motionY *= 1.1D;
					player.motionZ *= 1.125D;
				}
			}
		}
	}
}
