/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;


import biomesoplenty.api.item.BOPItems;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.projectile.EntitySnowball;
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
        
        if (isCreative || playerIn.inventory.hasItem(BOPItems.dart))
        {
            // TODO: implement EntityDart EntityDart entityDart = new EntityDart(worldIn, playerIn, 1.0F);
            EntitySnowball entityDart = new EntitySnowball(worldIn, playerIn);
            itemStackIn.damageItem(1, playerIn);
            worldIn.playSoundAtEntity(playerIn, "random.bow", 1.0F, 1.75F);
            
            // look for the best dart in inventory - find out which slot it's in
            int bestDartSlot = -1;
            ItemDart.DartType bestDart = ItemDart.DartType.DART;
            for (int k = 0; k < playerIn.inventory.mainInventory.length; ++k)
            {
                ItemStack current = playerIn.inventory.mainInventory[k];
                if (current != null && current.getItem()==BOPItems.dart)
                {
                    ItemDart.DartType currentDart = ItemDart.DartType.fromMeta(current.getMetadata());
                    if (currentDart.ordinal() >= bestDart.ordinal())
                    {
                        bestDart = currentDart;
                        bestDartSlot = k;
                    }
                }
            }
            
            // TODO: entityDart.setDartType(bestDart);
            
            if (isCreative)
            {
                worldIn.spawnEntityInWorld(entityDart);
            }
            else if (bestDartSlot >= 0)
            {
                worldIn.spawnEntityInWorld(entityDart);
                playerIn.inventory.decrStackSize(bestDartSlot, 1);
            }
        }
              
        return itemStackIn;
    }
    

}
