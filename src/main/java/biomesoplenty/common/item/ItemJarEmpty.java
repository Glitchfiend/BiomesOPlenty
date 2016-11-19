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
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.entities.EntityPixie;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraftforge.common.util.FakePlayer;


public class ItemJarEmpty extends Item
{
    
    public ItemJarEmpty()
    {
        this.setMaxDamage(0);
    }


    @Override
    public ActionResult<ItemStack> onItemRightClick(World world, EntityPlayer player, EnumHand hand)
    {
        ItemStack stack = player.getHeldItem(hand);
        RayTraceResult hit = this.rayTrace(world, player, true);

        if (hit == null) {
            return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
        } else {
            if (hit.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos pos = hit.getBlockPos();

                if (!world.isBlockModifiable(player, pos) || !player.canPlayerEdit(pos.offset(hit.sideHit), hit.sideHit, stack))
                {
                    return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
                }

                // determine if the block is one of our BOP fluids
                // note - no need to check level - you don't get a hit unless it's full
                IBlockState state = world.getBlockState(pos);
                ItemJarFilled.JarContents jarContents = null;
                if (state.getBlock() == BOPBlocks.honey)
                {
                    jarContents = ItemJarFilled.JarContents.HONEY;
                }

                // if it was honey, return the corresponding filled jar
                if (jarContents != null)
                {
                    world.setBlockToAir(pos);

                    return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, this.fillJar(stack, player, new ItemStack(BOPItems.jar_filled, 1, jarContents.ordinal())));
                }
            }
        }

        player.setActiveHand(hand);
        return new ActionResult<ItemStack>(EnumActionResult.PASS, stack);
    }

    private ItemStack fillJar(ItemStack stack, EntityPlayer player, ItemStack jarStack)
    {
        stack.setCount(stack.getCount() - 1);
        player.addStat(StatList.getObjectUseStats(this));

        // if there was only one empty jar in the stack, replace it, otherwise add the filledJar elsewhere in the inventory
        if (stack.getCount() <= 0)
        {
            return jarStack;
        } else {
            if (!player.inventory.addItemStackToInventory(jarStack))
            {
                // no room in inventory, so just drop it on the floor
                player.dropItem(jarStack, false);
            }

            return stack;
        }
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
    {
        // right clicking a pixie with an empty jar catches it in the jar
        if (target instanceof EntityPixie)
        {
            EntityPixie pixie = (EntityPixie)target;
            pixie.setDead();
            stack.setCount(stack.getCount() - 1);
            ItemStack pixieJar = new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.PIXIE.ordinal());
            EntityItem pixieJarEntity = new EntityItem(player.world, player.posX, player.posY, player.posZ, pixieJar);
            if (!player.world.isRemote)
            {
                player.world.spawnEntity(pixieJarEntity);
                if (!(player instanceof FakePlayer)) {pixieJarEntity.onCollideWithPlayer(player);}
            }
            return true;
        }
        if (target instanceof EntityButterfly)
        {
            EntityButterfly butterfly = (EntityButterfly)target;
            butterfly.setDead();
            stack.setCount(stack.getCount() - 1);
            ItemStack butterflyJar = new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.BUTTERFLY.ordinal());
            EntityItem butterflyJarEntity = new EntityItem(player.world, player.posX, player.posY, player.posZ, butterflyJar);
            if (!player.world.isRemote)
            {
                player.world.spawnEntity(butterflyJarEntity);
                if (!(player instanceof FakePlayer)) {butterflyJarEntity.onCollideWithPlayer(player);}
            }
            return true;
        }
        return false;
    }
    
}
  