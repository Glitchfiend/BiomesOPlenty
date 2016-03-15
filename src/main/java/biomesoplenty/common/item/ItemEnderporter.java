/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.MobEffects;
import net.minecraft.init.SoundEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;

public class ItemEnderporter extends Item {

    public ItemEnderporter()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(9);
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        if (player.getRidingEntity() != null) {return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        // can only use the enderporter on the surface world
        if (world.provider.isSurfaceWorld())
        {
            stack.damageItem(1, player);
            player.addPotionEffect(new PotionEffect(MobEffects.blindness, 30, 999));
            player.addPotionEffect(new PotionEffect(MobEffects.resistance, 200, 999));
            player.addPotionEffect(new PotionEffect(MobEffects.nightVision, 100, 999));
            player.addPotionEffect(new PotionEffect(MobEffects.regeneration, 200, 3));
            player.setPosition((double)world.getSpawnPoint().getX(), 250, (double)world.getSpawnPoint().getZ());
            world.playSound(player, player.getPosition(), SoundEvents.entity_player_levelup, SoundCategory.NEUTRAL, 1.0F, 5.0F);
            //TODO: FEATURE par3EntityPlayer.addStat(BOPAchievements.achEnderporter, 1);
        }
        else
        {
            // have the server inform the client that they can't use the enderporter
            if (!world.isRemote)
            {
                TextComponentTranslation msg = new TextComponentTranslation("enderporter.prevent");
                msg.getChatStyle().setColor(TextFormatting.DARK_PURPLE);
                player.addChatMessage(msg);
            }
        }
        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    
}