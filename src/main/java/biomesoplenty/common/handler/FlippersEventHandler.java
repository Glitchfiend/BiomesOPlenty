package biomesoplenty.common.handler;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraftforge.event.entity.living.LivingEvent.LivingUpdateEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class FlippersEventHandler 
{
    @SubscribeEvent
    public void onLivingUpdate(LivingUpdateEvent event)
    {
        if (event.getEntity() instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.getEntity();

            InventoryPlayer inventory = player.inventory;

            if (player.isInWater() && !player.capabilities.isFlying)
            {
                if (inventory.armorInventory[0] != null && inventory.armorInventory[0].getItem() == BOPItems.flippers)
                {
                    player.motionX *= 1.125D;
                    player.motionY *= 1.1D;
                    player.motionZ *= 1.125D;
                }
            }
        }
    }
}