/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import biomesoplenty.api.item.BOPItems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.passive.EntitySheep;
import net.minecraft.entity.passive.EntityWolf;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraftforge.event.entity.player.EntityInteractEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class DyeEventHandler
{
    @SubscribeEvent
    public void entityInteract(EntityInteractEvent event)
    {
        ItemStack stack = event.getEntityPlayer().getHeldItem(event.getHand());
        if (stack == null) {return;}
        
        Item item = stack.getItem();
        EnumDyeColor dyeColor;
        if (item == BOPItems.black_dye) {dyeColor = EnumDyeColor.BLACK;}
        else if (item == BOPItems.blue_dye) {dyeColor = EnumDyeColor.BLUE;}
        else if (item == BOPItems.brown_dye) {dyeColor = EnumDyeColor.BROWN;}
        else if (item == BOPItems.green_dye) {dyeColor = EnumDyeColor.GREEN;}
        else if (item == BOPItems.white_dye) {dyeColor = EnumDyeColor.WHITE;}
        else {return;}

        Entity target = event.getTarget();
        if (target instanceof EntityWolf)
        {
            EntityWolf wolf = (EntityWolf)target;
            if (dyeColor != wolf.getCollarColor())
            {
                wolf.setCollarColor(dyeColor);
                if (!event.getEntityPlayer().capabilities.isCreativeMode) {--stack.stackSize;}
                event.setResult(Result.ALLOW); 
            }
        }
        else if (target instanceof EntitySheep)
        {
            EntitySheep sheep = (EntitySheep)target;
            if (!sheep.getSheared() && dyeColor != sheep.getFleeceColor())
            {
                sheep.setFleeceColor(dyeColor);
                if (!event.getEntityPlayer().capabilities.isCreativeMode) {--stack.stackSize;}
                event.setResult(Result.ALLOW);
            }
        }
    }
}