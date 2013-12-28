package biomesoplenty.common.eventhandler.potions;

import java.util.Random;

import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import biomesoplenty.api.BOPPotionHelper;
import cpw.mods.fml.common.eventhandler.SubscribeEvent;

public class PotionPossessionEventHandler 
{
	@SubscribeEvent
	public void onEntityUpdate(LivingUpdateEvent event)
	{
		if (event.entityLiving.isPotionActive(BOPPotionHelper.get("possession")))
		{
			EntityLivingBase entity = event.entityLiving;
			
			Random rand = entity.worldObj.rand;

			double posX = entity.posX;
			double posY = entity.posY;
			double posZ = entity.posZ;

			double randX = 1.0 - (rand.nextDouble() * 2.0);
			double randY = (double) rand.nextInt(3);
			double randZ = 1.0 - (rand.nextDouble() * 2.0);

			entity.motionX = 0.0;        
			entity.motionY = 0.0;
			entity.motionZ = 0.0;

			if (rand.nextInt(5) == 0)
			{
				if (!entity.worldObj.checkBlockCollision(entity.boundingBox.offset(randX, randY, randZ)))
					entity.setPosition(posX + randX, posY + randY, posZ + randZ);
			}

			if (entity.getActivePotionEffect(BOPPotionHelper.get("possession")).getDuration() == 0)
			{
				entity.removePotionEffect(BOPPotionHelper.get("possession").id);
				return;
			}
		}
	}
}
