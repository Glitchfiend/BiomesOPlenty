/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Items;
import net.minecraft.init.MobEffects;
import net.minecraft.init.PotionTypes;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemFood;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemAmbrosia extends ItemFood
{
    
    public ItemAmbrosia()
    {
        super(6, 0.8F, false);
        // can drink even when full
        this.setAlwaysEdible();
        // dont' stack
        this.setMaxStackSize(1);
    }
    
    // Ambrosia takes twice as long as usual to finish
    @Override
    public int getMaxItemUseDuration(ItemStack stack)
    {
        return 64;
    }
    
    // Use drink action/sound instead of eat
    @Override
    public EnumAction getItemUseAction(ItemStack stack)
    {
        return EnumAction.DRINK;
    }
    
    // Loads of potion effects
    @Override
    protected void onFoodEaten(ItemStack stack, World worldIn, EntityPlayer player)
    {
        if (worldIn.isRemote) {return;}
        player.addPotionEffect(new PotionEffect(MobEffects.absorption, 5000, 4));
        player.addPotionEffect(new PotionEffect(MobEffects.saturation, 100, 1));
        player.addPotionEffect(new PotionEffect(MobEffects.regeneration, 500, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.digSpeed, 500, 2));
        player.addPotionEffect(new PotionEffect(MobEffects.resistance, 600, 1));
    }
    
    // keep the empty bottle after finishing
    @Override
    public ItemStack onItemUseFinish(ItemStack stack, World worldIn, EntityLivingBase playerIn)
    {
        super.onItemUseFinish(stack, worldIn, playerIn);
        return new ItemStack(Items.glass_bottle);
    }
    
}