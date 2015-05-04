/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;


import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entities.projectiles.EntityDart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;


public class ItemDartBlower extends Item
{
    
    public ItemDartBlower()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(63);
    }
   
    @Override
    public ItemStack onItemRightClick(ItemStack itemStackIn, World worldIn, EntityPlayer playerIn)
    {
        if (worldIn.isRemote) {return itemStackIn;}
        boolean isCreative = playerIn.capabilities.isCreativeMode;
        
        // look for the best dart in inventory - find out which slot it's in
        int bestDartSlot = -1;
        ItemDart.DartType bestAvailableDartType = ItemDart.DartType.DART;
        for (int k = 0; k < playerIn.inventory.mainInventory.length; ++k)
        {
            ItemStack current = playerIn.inventory.mainInventory[k];
            if (current != null && current.getItem()==BOPItems.dart)
            {
                ItemDart.DartType currentDartType = ItemDart.DartType.fromMeta(current.getMetadata());
                if (currentDartType.ordinal() >= bestAvailableDartType.ordinal())
                {
                    bestAvailableDartType = currentDartType;
                    bestDartSlot = k;
                }
            }
        }
        
        if (isCreative || (bestDartSlot > -1))
        {
            // there is a dart available to blow - blow it.
            EntityDart entityDart = new EntityDart(worldIn, playerIn, 1.0F);
            entityDart.setDartType(bestAvailableDartType);
            if (!isCreative)
            {
                itemStackIn.damageItem(1, playerIn);
                playerIn.inventory.decrStackSize(bestDartSlot, 1);
            }
            worldIn.spawnEntityInWorld(entityDart);
            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.75F);
        }
              
        return itemStackIn;
    }
    

}
