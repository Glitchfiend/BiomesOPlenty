/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.inventory;

import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.item.ItemFlowerBasket;
import biomesoplenty.common.util.NBTUtil;
import biomesoplenty.common.util.entity.PlayerUtil;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.InventoryBasic;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraftforge.common.util.Constants;

public class InventoryFlowerBasket extends InventoryBasic
{
    public static final int INVENTORY_ROWS = 2;
    public static final int INVENTORY_COLUMNS = 9;
    
    private EntityPlayer player;
    
    private ItemStack ownerStack;
    
    public InventoryFlowerBasket(ItemStack ownerStack, EntityPlayer player) 
    {
        super("container.flower_basket", false, INVENTORY_ROWS * INVENTORY_COLUMNS);
        
        this.player = player;
        
        //Load only on the server
        ItemStack basketStack = ownerStack;

        if (basketStack == null) basketStack = player.getHeldItem(PlayerUtil.getHandForItemAndMeta(player, BOPItems.flower_basket, 0));
        else this.ownerStack = basketStack;

        NBTTagCompound invData = NBTUtil.getOrCreateStackNBT(basketStack);
        this.readFromNBT(invData);
    }
    
    public InventoryFlowerBasket(EntityPlayer player)
    {
        this(null, player);
    }
    
    @Override
    public void markDirty()
    {
        //Resolve the basket stack (it may have moved since it was opened)
        ItemStack basketStack = getBasketStack();

        //There's no point continuing if there's nothing to save to
        if (basketStack != null)
        {
            NBTTagCompound currentData = new NBTTagCompound();
            //Overwrite relevant data in the compound with updated data
            this.writeToNBT(currentData);  
            //Replace the stack's compound
            basketStack.setTagCompound(currentData);
        }
    }

    public void readFromNBT(NBTTagCompound compound)
    {
        //Obtain a list of the existing items
        NBTTagList itemsList = compound.getTagList("Items", Constants.NBT.TAG_COMPOUND);
        //Remove any existing items
        this.clear();

        for (int index = 0; index < itemsList.tagCount(); ++index)
        {
            NBTTagCompound itemTag = itemsList.getCompoundTagAt(index);
            int slotIndex = itemTag.getByte("Slot");

            //Ensure the slot index is valid
            if (slotIndex >= 0 && slotIndex < this.getSizeInventory())
            {
                this.setInventorySlotContents(slotIndex, ItemStack.loadItemStackFromNBT(itemTag));
            }
        }

        if (compound.hasKey("CustomName", Constants.NBT.TAG_STRING))
        {
            this.setCustomName(compound.getString("CustomName"));
        }
    }

    public void writeToNBT(NBTTagCompound compound)
    {
        //Create a new items list to write to
        NBTTagList itemsList = new NBTTagList();

        //Iterate over all valid slot indexes
        for (int slotIndex = 0; slotIndex < this.getSizeInventory(); ++slotIndex)
        {
            if (this.getStackInSlot(slotIndex) != null)
            {
                //Create a new item tag and populate it with data
                NBTTagCompound itemTag = new NBTTagCompound();
                itemTag.setByte("Slot", (byte)slotIndex);
                this.getStackInSlot(slotIndex).writeToNBT(itemTag);
                itemsList.appendTag(itemTag);
            }
        }
        
        //Update the Items compound with our new data
        compound.setTag("Items", itemsList);

        if (this.hasCustomName())
        {
            compound.setString("CustomName", this.getName());
        }
        
        //We can't assume the basket is always open because the inventory may be accessed
        //outside of the gui
        compound.setBoolean("BasketOpen", ItemFlowerBasket.isBasketOpen(getBasketStack()));
    }
    
    private ItemStack getBasketStack()
    {
        return this.ownerStack != null ? this.ownerStack : ItemFlowerBasket.findOpenBasketStack(this.player);
    }
}
