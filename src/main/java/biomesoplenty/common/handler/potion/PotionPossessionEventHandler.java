/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler.potion;

import java.util.Random;

import biomesoplenty.api.potion.BOPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionPossessionEventHandler
{
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        if (event.getEntityLiving().isPotionActive(BOPPotions.possession))
        {
            EntityLivingBase entity = event.getEntityLiving();
            
            Random rand = entity.world.rand;

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
                if (!entity.world.checkBlockCollision(entity.getEntityBoundingBox().offset(randX, randY, randZ)))
                    entity.setPosition(posX + randX, posY + randY, posZ + randZ);
            }

            if (entity.getActivePotionEffect(BOPPotions.possession).getDuration() == 0)
            {
                entity.removePotionEffect(BOPPotions.possession);
                return;
            }
        }
    }
}