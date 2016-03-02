/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.world.World;

public class ItemEnderporter extends Item {

    public ItemEnderporter()
    {
        this.maxStackSize = 1;
        this.setMaxDamage(9);
    }
    
    @Override
    public ItemStack onItemRightClick(ItemStack stack, World world, EntityPlayer player)
    {
        if (player.ridingEntity != null) {return stack;}
        // can only use the enderporter on the surface world
        if (world.provider.isSurfaceWorld())
        {
            stack.damageItem(1, player);
            player.addPotionEffect(new PotionEffect(Potion.blindness.id, 30, 999));
            player.addPotionEffect(new PotionEffect(Potion.resistance.id, 200, 999));
            player.addPotionEffect(new PotionEffect(Potion.nightVision.id, 100, 999));
            player.addPotionEffect(new PotionEffect(Potion.regeneration.id, 200, 3));
            player.setPosition((double)world.getSpawnPoint().getX(), 250, (double)world.getSpawnPoint().getZ());
            world.playSoundAtEntity(player, "random.levelup", 1.0F, 5.0F);
            //TODO: FEATURE par3EntityPlayer.addStat(BOPAchievements.achEnderporter, 1);            
        }
        else
        {
            // have the server inform the client that they can't use the enderporter
            if (!world.isRemote)
            {
                ChatComponentText msg = new ChatComponentText(StatCollector.translateToLocal("enderporter.prevent"));
                msg.getChatStyle().setColor(EnumChatFormatting.DARK_PURPLE);
                player.addChatMessage(msg);
            }
        }
        return stack;
    }
    
    
}