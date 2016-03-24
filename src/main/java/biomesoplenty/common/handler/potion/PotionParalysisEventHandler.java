/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler.potion;

import biomesoplenty.api.potion.BOPPotions;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.monster.EntityCreeper;
import net.minecraftforge.event.entity.living.EnderTeleportEvent;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class PotionParalysisEventHandler
{
    
    @SubscribeEvent
    public void onEntityUpdate(LivingUpdateEvent event)
    {
        
        if (event.getEntityLiving().isPotionActive(BOPPotions.paralysis))
        {
            EntityLivingBase entity = event.getEntityLiving();
            
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

            if (entity.getActivePotionEffect(BOPPotions.paralysis).getDuration() == 0)
            {
                entity.removePotionEffect(BOPPotions.paralysis);
                return;
            }
        }
    }

    @SubscribeEvent
    public void onEndermanTP(EnderTeleportEvent event)
    {
        if (event.getEntityLiving().isPotionActive(BOPPotions.paralysis)) 
        {
            event.setCanceled(true);
        }
    }
}