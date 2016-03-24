package biomesoplenty.common.handler;

import biomesoplenty.common.inventory.ContainerFlowerBasket;
import biomesoplenty.common.inventory.InventoryFlowerBasket;
import biomesoplenty.common.item.ItemFlowerBasket;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.item.ItemTossEvent;
import net.minecraftforge.event.entity.player.EntityItemPickupEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ItemEventHandler 
{
    /**
     * Closes the gui if the open flower basket is tossed
     */
    @SubscribeEvent
    public void onItemToss(ItemTossEvent event)
    {
        EntityPlayer player = event.getPlayer();
        ItemStack stack = event.getEntityItem().getEntityItem();
        
        if (player.openContainer instanceof ContainerFlowerBasket)
        {
            //Only close the gui if this stack was the one that was open
            if (ItemFlowerBasket.isBasketOpen(stack))
            {
                //Remove the itemstack from the inventory now to prevent a loop
                player.inventory.setItemStack(null);
                player.closeScreen();
            }
        }
        
        //Ensure the tossed stack is properly closed
        ItemFlowerBasket.closeIfBasket(stack);
    }
    
    @SubscribeEvent
    public void onItemPickup(EntityItemPickupEvent event)
    {
        EntityPlayer player = event.getEntityPlayer();
        EntityItem entityItem = event.getItem();
        
        ItemStack stack = event.getItem().getEntityItem();
        ItemStack basketStack = ItemFlowerBasket.findBasketStack(player);
        
        if (!player.worldObj.isRemote)
        {
            //Check if the player has a basket in their inventory, and if the stack is suitable for adding
            //to the basket
            if (basketStack != null && ItemFlowerBasket.isStackSuitableForBasket(stack))
            {
                InventoryFlowerBasket inventory = new InventoryFlowerBasket(basketStack, player);

                //Add the stack to the basket's inventory, if successful, don't add it to the player's regular inventory
                if (inventory.func_174894_a(stack) == null)
                {
                    //Set stack size to 0 to cause it to be removed
                    stack.stackSize = 0;
                    //Prevent the stack from being added to the player's inventory
                    event.setResult(Result.ALLOW);
                }
            }
        }
    }
}
