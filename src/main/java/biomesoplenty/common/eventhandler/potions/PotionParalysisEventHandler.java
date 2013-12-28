package biomesoplenty.common.eventhandler.potions;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.BOPPotionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionParalysisEventHandler 
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(BOPPotionHelper.get("paralysis")))
		{
			EntityLivingBase entity = event.entityLiving;
			
			entity.motionX = 0.0;
			
			if (!entity.isAirBorne) 
			{
				entity.motionY = 0.0;
			}
			
			entity.motionZ = 0.0;

			if (entity instanceof EntityCreeper) 
			{
				((EntityCreeper)entity).setCreeperState(-1);
			}

			if (entity.getActivePotionEffect(BOPPotionHelper.get("paralysis")).getDuration() == 0)
			{
				entity.removePotionEffect(BOPPotionHelper.get("paralysis").id);
				return;
			}
		}
	}

	@SubscribeEvent
	public void onEndermanTP(EnderTeleportEvent event)
	{
		if (event.entityLiving.isPotionActive(BOPPotionHelper.get("paralysis"))) 
		{
			event.setCanceled(true);
		}
	}
}
