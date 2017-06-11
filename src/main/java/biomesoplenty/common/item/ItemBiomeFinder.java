/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.util.biome.BiomeUtils;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.IItemPropertyGetter;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.text.TextComponentTranslation;
import net.minecraft.util.text.TextFormatting;
import net.minecraft.util.text.translation.I18n;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nullable;
import java.util.List;

public class ItemBiomeFinder extends Item
{    
    
    public ItemBiomeFinder()
    {
        this.addPropertyOverride(new ResourceLocation("frame"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(ItemStack stack, World world, EntityLivingBase entity) 
            {
                if (entity == null) {return 0.00F;}
                
                NBTTagCompound nbt = stack.getTagCompound();
                if (nbt != null && nbt.hasKey("biomeIDToFind"))
                {                
                    if (nbt.hasKey("searchStarted"))
                    {
                        // searching for biome, but not yet found indicate searching by flashing
                        return this.getFlashingFrame(entity);
                    }
                    else if (nbt.getBoolean("found"))
                    {
                        // if the biome has been found, point at it
                        int posX = nbt.getInteger("posX");
                        int posZ = nbt.getInteger("posZ");
                        return getFrameForPositionRelativeToPlayer(entity, posX, posZ);
                    }
                    else
                    {
                        // the search has not yet been started, show all sectors lit
                        return 0.09F;
                    }
                }
                else
                {
                    // if we've got here, the biome finder has not been bound to a biome yet - show no sectors lit
                    return 0.08F;
                }
            }  

            public float getFlashingFrame(EntityLivingBase entity)
            {
                return (entity.ticksExisted % 2 == 0 ? 0.10F : 0.11F);
            }
            
            public float getFrameForPositionRelativeToPlayer(EntityLivingBase entity, int biomePosX, int biomePosZ)
            {
                double xDiff = (double)biomePosX - entity.posX;
                double zDiff = (double)biomePosZ - entity.posZ;
                // angle (in degrees) of direction from player to biome (relative to player rotation)
                double angleDiff = (Math.atan2(zDiff, xDiff) * 180.0D / Math.PI) + 270.0D - entity.rotationYaw;
                // there are 8 sectors on the biome finder, so 45 degrees each (offset by 22.5 to center the angle in the middle of the sector)
                int sector = (int)Math.floor((angleDiff + 22.5D) / 45.0D);
                return (((sector % 8) + 8) % 8) / 100.0F;        
            }
        });
        
        this.setMaxStackSize(1);
    }
        
    
    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
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
        Biome biomeToFind = Biome.getBiome(nbt.getInteger("biomeIDToFind")); // returns ocean if biomeIDToFind is out of bounds
        
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
            sendChatMessage(player, I18n.translateToLocalFormatted("biome_finder.searching",biomeToFind.getBiomeName()), TextFormatting.DARK_PURPLE);
            
            // search for biomeToFind, maximum distance 5000 blocks
            BlockPos pos = BiomeUtils.spiralOutwardsLookingForBiome(world, biomeToFind, player.posX, player.posZ);
            
            if (pos == null)
            {
                // server notifies player that search was unsuccessful
                sendChatMessage(player, I18n.translateToLocalFormatted("biome_finder.not_found",biomeToFind.getBiomeName()), TextFormatting.RED);
                // write not found tag
                writeNBTNotFound(nbt);
            }
            else
            {
                // server notifies player that search was successful
                sendChatMessage(player, I18n.translateToLocalFormatted("biome_finder.found",biomeToFind.getBiomeName()), TextFormatting.GREEN);
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
        chatComponent.getStyle().setColor(color);
        player.sendMessage(chatComponent);
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
    @SideOnly(Side.CLIENT)
    public void addInformation(ItemStack itemStack, @Nullable World world, List<String> tooltip, ITooltipFlag advanced)
    {
        if (!itemStack.hasTagCompound()) {return;}
        NBTTagCompound nbt = itemStack.getTagCompound();
        if (nbt.hasKey("biomeIDToFind"))
        {
            Biome biomeToFind = Biome.getBiome(nbt.getInteger("biomeIDToFind")); // returns ocean if biomeIDToFind is out of bounds
            tooltip.add(biomeToFind.getBiomeName());
        }
    }

    
}