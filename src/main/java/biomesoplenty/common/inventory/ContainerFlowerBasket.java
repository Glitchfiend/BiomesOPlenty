/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.inventory;

import static biomesoplenty.common.inventory.InventoryFlowerBasket.INVENTORY_COLUMNS;
import static biomesoplenty.common.inventory.InventoryFlowerBasket.INVENTORY_ROWS;

import biomesoplenty.common.item.ItemFlowerBasket;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;

public class ContainerFlowerBasket extends Container
{
    private static final int PLAYER_ROWS = 3;
    private static final int PLAYER_COLUMNS = 9;
    
    public ContainerFlowerBasket(EntityPlayer player, InventoryFlowerBasket inventoryFlowerBasket)
    {
        //Slots are inset on the x axis by 8 pixels as a result of the inventory border. 
        //Each slot is 18 pixels wide. The total slot height may vary as extra rows are 
        //added, however there is always a constant based on the texture layout.
        
        int totalInvSlotHeight = INVENTORY_ROWS * 18;
        
        for (int row = 0; row < INVENTORY_ROWS; ++row)
        {
            for (int col = 0; col < INVENTORY_COLUMNS; ++col)
            {
                this.addSlotToContainer(new BasketSlot(inventoryFlowerBasket, col + row * 9, 8 + col * 18, 18 + row * 18));
            }
        }

        //Adds slots for the player's inventory
        for (int row = 0; row < PLAYER_ROWS; ++row)
        {
            for (int col = 0; col < PLAYER_COLUMNS; ++col)
            {
                //Start at index 9, after the hotbar
                this.addSlotToContainer(new Slot(player.inventory, col + row * 9 + 9, 8 + col * 18, 31 + row * 18 + totalInvSlotHeight));
            }
        }

        //Adds slots for the player's hotbar
        for (int col = 0; col < PLAYER_COLUMNS; ++col)
        {
            //Hotbar uses the indexes 0-8 for its slots. 
            this.addSlotToContainer(new Slot(player.inventory, col, 8 + col * 18, 89 + totalInvSlotHeight));
        }
    }
    
    @Override
    public boolean canInteractWith(EntityPlayer player) 
    {
        return true;
    }
    
    @Override
    public void onContainerClosed(EntityPlayer player) 
    {
        super.onContainerClosed(player);
        
        if (!player.worldObj.isRemote)
        {
            //Ensure all baskets are closed once the inventory is
            ItemFlowerBasket.clearOpenBaskets(player);
        }
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer player, int index)
    {
        ItemStack oldStack = null;
        Slot slot = (Slot)this.inventorySlots.get(index);

        //Ensure there is a slot at this index and it has an item in it
        if (slot != null && slot.getHasStack())
        {
            ItemStack mergedStack = slot.getStack();
            oldStack = mergedStack.copy();

            if (index < INVENTORY_ROWS * 9)
            {
                if (!this.mergeItemStack(mergedStack, INVENTORY_ROWS * 9, this.inventorySlots.size(), true))
                {
                    return null;
                }
            }
            else if (!this.mergeItemStack(mergedStack, 0, INVENTORY_ROWS * 9, false))
            {
                return null;
            }

            if (mergedStack.stackSize == 0)
            {
                slot.putStack((ItemStack)null);
            }
            else
            {
                slot.onSlotChanged();
            }
        }

        return oldStack;
    }
    
    public static class BasketSlot extends Slot
    {
        public BasketSlot(IInventory inventoryIn, int index, int xPosition, int yPosition) 
        {
            super(inventoryIn, index, xPosition, yPosition);
        }
        
        @Override
        public boolean isItemValid(ItemStack stack)
        {
            return ItemFlowerBasket.isStackSuitableForBasket(stack);
        }
    }
}
