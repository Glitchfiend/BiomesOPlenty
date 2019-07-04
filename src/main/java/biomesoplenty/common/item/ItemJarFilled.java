/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.*;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

import javax.annotation.Nonnull;
import java.util.Locale;
import java.util.Objects;

public class ItemJarFilled extends Item
{
    
    public enum JarContents implements IStringSerializable
    {
        HONEY, BLUE_FIRE;
        
        @Override
        public String getName()
        {
            return this.name().toLowerCase(Locale.ENGLISH);
        }
        @Override
        public String toString()
        {
            return this.getName();
        }
    }

    public ItemJarFilled()
    {
        this.setHasSubtypes(true);
        this.setMaxDamage(0);
        this.setMaxStackSize(1);
    }
    
    // add all the contents types as separate items in the creative tab
    @Override
    public void getSubItems(@Nonnull CreativeTabs tab, @Nonnull NonNullList<ItemStack> subItems)
    {
        if (this.isInCreativeTab(tab))
        {
            for (JarContents contents : JarContents.values())
            {
                subItems.add(new ItemStack(this, 1, contents.ordinal()));
            }
        }
    }

    // default behavior in Item is to return 0, but the meta value is important here because it determines the jar contents
    @Override
    public int getMetadata(int metadata)
    {
        return metadata;
    }
    
    public JarContents getContentsType(ItemStack stack)
    {
        int meta = stack.getMetadata();
        try {
            return JarContents.values()[meta];
        } catch (Exception e) {
            // if metadata is out of bounds return honey as a default (should never happen)
            return JarContents.HONEY;
        }
    }
    
    // get the correct name for this item by looking up the meta value in the JarContents enum
    @Override
    @Nonnull
    public String getUnlocalizedName(ItemStack stack)
    {
        return super.getUnlocalizedName() + "_" + this.getContentsType(stack).getName();
    }
    
    @Override
    @Nonnull
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, @Nonnull EnumHand hand)
    {
        ItemStack heldStack = player.getHeldItem(hand);
        if (getContentsType(heldStack) == JarContents.BLUE_FIRE)
        {
            RayTraceResult raytraceresult = this.rayTrace(world, player, false);
            if (raytraceresult == null)
            {
                return new ActionResult<>(EnumActionResult.PASS, heldStack);
            }
            else if (raytraceresult.typeOfHit != RayTraceResult.Type.BLOCK)
            {
                return new ActionResult<>(EnumActionResult.PASS, heldStack);
            }
            else
            {
                BlockPos rayPos = raytraceresult.getBlockPos();

                if (!world.isBlockModifiable(player, rayPos))
                {
                    return new ActionResult<>(EnumActionResult.FAIL, heldStack);
                }
                else
                {
                    boolean isReplaceable = world.getBlockState(rayPos).getBlock().isReplaceable(world, rayPos);
                    BlockPos pos = isReplaceable && raytraceresult.sideHit == EnumFacing.UP ? rayPos : rayPos.offset(raytraceresult.sideHit);

                    if (!player.canPlayerEdit(pos, raytraceresult.sideHit, heldStack))
                    {
                        return new ActionResult<>(EnumActionResult.FAIL, heldStack);
                    }
                    else if (world.getBlockState(pos).getBlock().isReplaceable(world, pos) && world.getBlockState(pos.down()).isTopSolid())
                    {
                        world.setBlockState(pos, BOPBlocks.blue_fire.getDefaultState());
                        this.emptyJar(player, hand);
                        return new ActionResult<>(EnumActionResult.SUCCESS, heldStack);
                    }
                    else
                    {
                        return new ActionResult<>(EnumActionResult.FAIL, heldStack);
                    }
                }
            }
        }
        return new ActionResult<>(EnumActionResult.PASS, heldStack);
    }

    private void emptyJar(EntityPlayer player, EnumHand hand)
    {
        ItemStack heldStack = player.getHeldItem(hand);
        ItemStack emptyJar = new ItemStack(BOPItems.jar_empty);
        if (!player.capabilities.isCreativeMode)
        {
            if (heldStack.isEmpty()) 
            {
                player.setHeldItem(hand, emptyJar);
            } 
            else if (!player.inventory.addItemStackToInventory(emptyJar)) 
            {
                player.dropItem(emptyJar, false);
            } 
            else if (player instanceof EntityPlayerMP) 
            {
                ((EntityPlayerMP) player).sendContainerToPlayer(player.inventoryContainer);
            }
            heldStack.shrink(1);
            player.addStat(Objects.requireNonNull(StatList.getObjectUseStats(this)));
        }
    }
}
   