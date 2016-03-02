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
        /* TODO: surely just holding flippers shouldn't give you the swimming bonus, surely you have to put them on your feet? - commented this out for now
        if (event.entity instanceof EntityLiving)
        {
            EntityLiving entity = (EntityLiving)event.entity;

            ItemStack itemstack = entity.getEquipmentInSlot(1);

            if (entity.isInWater())
            {
                if (itemstack != null && itemstack.getItem() == BOPItems.flippers)
                {
                    entity.motionX *= 1.125D;
                    entity.motionY *= 1.1D;
                    entity.motionZ *= 1.125D;
                }
            }
        }
        */

        if (event.entity instanceof EntityPlayer)
        {
            EntityPlayer player = (EntityPlayer)event.entity;

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