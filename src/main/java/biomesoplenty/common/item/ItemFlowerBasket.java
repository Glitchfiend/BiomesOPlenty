/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.common.handler.GuiHandler;
import biomesoplenty.common.inventory.InventoryFlowerBasket;
import biomesoplenty.common.util.NBTUtil;
import biomesoplenty.core.BiomesOPlenty;
import net.minecraft.block.Block;
import net.minecraft.block.IGrowable;
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
import net.minecraft.world.World;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.IShearable;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import javax.annotation.Nonnull;

public class ItemFlowerBasket extends Item
{
    public ItemFlowerBasket()
    {
        this.addPropertyOverride(new ResourceLocation("open"), new IItemPropertyGetter()
        {
            @Override
            @SideOnly(Side.CLIENT)
            public float apply(@Nonnull ItemStack stack, World world, EntityLivingBase entity)
            {
                InventoryFlowerBasket inventory = new InventoryFlowerBasket(stack, null);
                boolean filled = false;
                
                for (int index = 0; index < inventory.getSizeInventory(); ++index)
                {
                    if (!inventory.getStackInSlot(index).isEmpty())
                    {
                        filled = true;
                        break;
                    }
                }
                
                return filled ? 1 : 0;
            }  
        });
        
        this.maxStackSize = 1;
    }

    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        if (!world.isRemote)
        {
            NBTTagCompound compound = NBTUtil.getOrCreateStackNBT(stack);
            
            //Ensure there are no other open baskets in the player's inventory
            //This oculd potentially happen if the inventory is never properly closed
            clearOpenBaskets(player);
            //Tag this basket as open
            compound.setBoolean("BasketOpen", true);
            //Set the stack's data in case it didn't previously have any
            player.openGui(BiomesOPlenty.instance, GuiHandler.FLOWER_BASKET_ID, world, 0, 0, 0);
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }

    @Nonnull
    public static ItemStack findBasketStack(EntityPlayer player)
    {
        //Search every item in the player's main inventory for a basket
        for (ItemStack stack : player.inventory.mainInventory)
        {
            if (!stack.isEmpty() && stack.getItem() instanceof ItemFlowerBasket)
            {
                return stack;
            }
        }
        
        return ItemStack.EMPTY;
    }

    @Nonnull
    public static ItemStack findOpenBasketStack(EntityPlayer player)
    {
        //Search every item in the player's main inventory for a basket
        for (ItemStack stack : player.inventory.mainInventory)
        {
            if (isBasketOpen(stack))
            {
                return stack;
            }
        }
        
        return ItemStack.EMPTY;
    }
    
    public static boolean isBasketOpen(@Nonnull ItemStack stack)
    {
        if (!stack.isEmpty() && stack.getItem() instanceof ItemFlowerBasket && stack.hasTagCompound())
        {
            NBTTagCompound compound = stack.getTagCompound();
            
            if (compound.hasKey("BasketOpen"))
            {
                return compound.getBoolean("BasketOpen");
            }
        }
        
        return false;
    }
    
    public static void clearOpenBaskets(EntityPlayer player)
    {
        //Search every item in the player's main inventory for a basket
        for (ItemStack stack : player.inventory.mainInventory)
        {
            closeIfBasket(stack);
        }
    }
    
    public static void closeIfBasket(@Nonnull ItemStack stack)
    {
        //Validate to ensure the stack is a basket and it is open
        if (isBasketOpen(stack))
        {
            NBTTagCompound compound = stack.getTagCompound();
            //Ensure the basket is closed
            compound.setBoolean("BasketOpen", false);
        }
    }
    
    public static boolean isStackSuitableForBasket(@Nonnull ItemStack stack)
    {
        Item item = stack.getItem();
        Block block = Block.getBlockFromItem(item);
        
        return !(item instanceof ItemFlowerBasket) && (block instanceof IPlantable || block instanceof IGrowable || block instanceof IShearable);
    }
}
