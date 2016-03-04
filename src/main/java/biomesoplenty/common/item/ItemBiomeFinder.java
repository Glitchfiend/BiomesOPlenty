/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import java.util.List;

import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.client.resources.I18n;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.world.World;
import net.minecraft.world.biome.BiomeGenBase;

public class ItemBiomeFinder extends Item
{    
    
    public ItemBiomeFinder()
    {
        this.setMaxStackSize(1);
    }
        
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {

        if (!stack.hasTagCompound()) {stack.setTagCompound(new NBTTagCompound());}
        NBTTagCompound nbt = stack.getTagCompound();
        
        if (nbt.getBoolean("found"))
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
        if (nbt.hasKey("searchStarted") && (world.getWorldTime() - nbt.getLong("searchStarted") < 100))
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
        if (!nbt.hasKey("biomeIDToFind"))
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);
        }
        BiomeGenBase biomeToFind = BiomeGenBase.getBiome(nbt.getInteger("biomeIDToFind")); // returns ocean if biomeIDToFind is out of bounds
        
        // both client and server set the 'searching' tag - client to update the rendering, server so it can't be used again too quickly
        writeNBTSearching(nbt, world);
        
        // carry out the search on the server, not the client
        if (world.isRemote)
        {
            // client functionality stops here
            return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
        }
        else
        {            
            // server notifies player that search is starting
            sendChatMessage(player, I18n.format("biome_finder.searching",biomeToFind.getBiomeName()), TextFormatting.DARK_PURPLE);
            
            // search for biomeToFind, maximum distance 5000 blocks
            BlockPos pos = BiomeUtils.spiralOutwardsLookingForBiome(world, biomeToFind, player.posX, player.posZ);
            
            if (pos == null)
            {
                // server notifies player that search was unsuccessful
                sendChatMessage(player, I18n.format("biome_finder.not_found",biomeToFind.getBiomeName()), TextFormatting.RED);
                // write not found tag
                writeNBTNotFound(nbt);
            }
            else
            {
                // server notifies player that search was successful
                sendChatMessage(player, I18n.format("biome_finder.found",biomeToFind.getBiomeName()), TextFormatting.GREEN);
                // write found tag
                writeNBTFound(nbt, pos);
            }
            // It is necessary for the server to return a copy of the stack to make sure that the client successfully replaces it in the inventory
            return new ActionResult<ItemStack>(EnumActionResult.PASS, stack.copy());
            
        }

    }
    
    public static void sendChatMessage(EntityPlayer player, String msg, TextFormatting color)
    {
        TextComponentTranslation chatComponent = new TextComponentTranslation(msg);
        chatComponent.getChatStyle().setColor(color);
        player.addChatMessage(chatComponent);
    }
    
    public static void writeNBTSearching(NBTTagCompound nbt, World world)
    {
        nbt.setBoolean("found",false);
        nbt.setLong("searchStarted", world.getWorldTime());
        nbt.removeTag("posX");
        nbt.removeTag("posZ");
    }
    
    public static void writeNBTFound(NBTTagCompound nbt, BlockPos pos)
    {
        nbt.setBoolean("found",true);
        nbt.removeTag("searchStarted");
        nbt.setInteger("posX", pos.getX());
        nbt.setInteger("posZ", pos.getZ());
    }
    
    public static void writeNBTNotFound(NBTTagCompound nbt)
    {
        nbt.setBoolean("found",false);
        nbt.removeTag("searchStarted");
        nbt.removeTag("posX");
        nbt.removeTag("posZ");
    }
    
    @Override
    public void addInformation(ItemStack itemStack, EntityPlayer player, List infoList, boolean advancedItemTooltips)
    {
        if (!itemStack.hasTagCompound()) {return;}
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (nbt.hasKey("biomeIDToFind"))
        {
            BiomeGenBase biomeToFind = BiomeGenBase.getBiome(nbt.getInteger("biomeIDToFind")); // returns ocean if biomeIDToFind is out of bounds
            infoList.add(biomeToFind.getBiomeName());
        }
    }

    
}