package biomesoplenty.potions;

import java.util.Random;

import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.Potions;

public class PotionEventHandler
{
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(Potions.paralysis.get()))
		{
			event.entityLiving.motionX = 0.0;
			if (!event.entityLiving.isAirBorne) {
				event.entityLiving.motionY = 0.0;
			}
			event.entityLiving.motionZ = 0.0;

			if (event.entityLiving instanceof EntityCreeper) {
				((EntityCreeper)event.entityLiving).setCreeperState(-1);
			}

			if (event.entityLiving.getActivePotionEffect(Potions.paralysis.get()).getDuration() == 0)
			{
				event.entityLiving.removePotionEffect(Potions.paralysis.get().id);
				return;
			}
		}
		
		if (event.entityLiving.isPotionActive(Potions.possession.get()))
		{
			Random rand = event.entityLiving.worldObj.rand;

			double posX = event.entityLiving.posX;
			double posY = event.entityLiving.posY;
			double posZ = event.entityLiving.posZ;

			double randX = 1.0 - (rand.nextDouble() * 2.0);
			double randY = (double) rand.nextInt(3);
			double randZ = 1.0 - (rand.nextDouble() * 2.0);
			
			event.entityLiving.motionX = 0.0;	
			event.entityLiving.motionY = 0.0;
			event.entityLiving.motionZ = 0.0;

			if (rand.nextInt(5) == 0)
			{
				if (!event.entityLiving.worldObj.checkBlockCollision(event.entityLiving.boundingBox.offset(randX, randY, randZ)))
					event.entityLiving.setPosition(posX + randX, posY + randY, posZ + randZ);
			}

			if (event.entityLiving.getActivePotionEffect(Potions.possession.get()).getDuration() == 0)
			{
				event.entityLiving.removePotionEffect(Potions.possession.get().id);
				return;
			}
		}
	}

	@ForgeSubscribe
	public void onEndermanTP(EnderTeleportEvent event)
	{
		if (event.entityLiving.isPotionActive(Potions.paralysis.get())) {
			event.setCanceled(true);
		}
	}
}
