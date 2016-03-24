/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;


import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.entities.projectiles.EntityDart;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.world.World;


public class ItemDartBlower extends Item
{
    
    public ItemDartBlower()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(63);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (world.isRemote) {return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);}
        boolean isCreative = player.capabilities.isCreativeMode;
        
        // look for the best dart in inventory - find out which slot it's in
        int bestDartSlot = -1;
        ItemDart.DartType bestAvailableDartType = ItemDart.DartType.DART;
        for (int k = 0; k < player.inventory.mainInventory.length; ++k)
        {
            ItemStack current = player.inventory.mainInventory[k];
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
            EntityDart entityDart = new EntityDart(world, player);
            entityDart.setDartType(bestAvailableDartType);
            entityDart.func_184547_a(player, player.rotationPitch, player.rotationYaw, 0.0F, 3.0F, 1.0F);
            if (!isCreative)
            {
                stack.damageItem(1, player);
                player.inventory.decrStackSize(bestDartSlot, 1);
            }
            world.spawnEntityInWorld(entityDart);
            world.playSound(player, player.getPosition(), SoundEvents.entity_arrow_shoot, SoundCategory.NEUTRAL, 1.0F, 1.75F);
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    

}
