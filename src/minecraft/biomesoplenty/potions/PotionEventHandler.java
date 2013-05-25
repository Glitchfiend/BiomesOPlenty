package biomesoplenty.potions;

import biomesoplenty.configuration.BOPPotions;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraftforge.event.ForgeSubscribe;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;

public class PotionEventHandler 
{
	@ForgeSubscribe
	public void onEntityUpdate(LivingUpdateEvent event) 
	{
		if (event.entityLiving.isPotionActive(BOPPotions.nourishment)) 
		{
			if (event.entityLiving.worldObj.rand.nextInt(350) == 0) 
			{
				if (!event.entityLiving.worldObj.isRemote)
					if (event.entityLiving instanceof EntityPlayer) 
						((EntityPlayer)event.entityLiving).getFoodStats().addStats(1, 0);
			}
			
			if (event.entityLiving.getActivePotionEffect(BOPPotions.nourishment).getDuration() == 0) 
			{
				event.entityLiving.removePotionEffect(BOPPotions.nourishment.id);
				return;
			}
		}
	}
}
