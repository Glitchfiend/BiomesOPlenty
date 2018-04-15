package biomesoplenty.common.handler;

import java.util.Random;

import biomesoplenty.api.potion.BOPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionCurseHandler
{
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        if (event.getEntityLiving().isPotionActive(BOPPotions.curse))
        {
            EntityLivingBase entity = event.getEntityLiving();
            
            Random rand = new Random();

            if (rand.nextInt(5) == 0)
            {
	            entity.motionX = (rand.nextDouble() - rand.nextDouble()) * (rand.nextDouble() + 0.5D);
	            entity.motionZ = (rand.nextDouble() - rand.nextDouble()) * (rand.nextDouble() + 0.5D);
            }

            if (entity.getActivePotionEffect(BOPPotions.curse).getDuration() == 0)
            {
                entity.removePotionEffect(BOPPotions.curse);
                return;
            }
        }
    }
}