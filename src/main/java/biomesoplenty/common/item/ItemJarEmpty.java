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
    public ActionResult<ItemStack> onItemRightClick(ItemStack stack, World world, EntityPlayer player, EnumHand hand)
    {
        
        RayTraceResult hit = this.getMovingObjectPositionFromPlayer(world, player, true);
        if (hit == null) {new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        if (hit.typeOfHit != RayTraceResult.Type.BLOCK) {new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        BlockPos pos = hit.getBlockPos();
        if (!world.isBlockModifiable(player, pos)) {new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        if (!player.canPlayerEdit(pos, hit.sideHit, stack)) {new ActionResult<ItemStack>(EnumActionResult.FAIL, stack);}
        
        // determine if the block is one of our BOP fluids
        // note - no need to check level - you don't get a hit unless it's full
        IBlockState state = world.getBlockState(pos);
        ItemJarFilled.JarContents jarContents = null;
        if (state.getBlock() == BOPBlocks.honey)
        {
            jarContents = ItemJarFilled.JarContents.HONEY;                
        }
        else if (state.getBlock() == BOPBlocks.poison)
        {
            jarContents = ItemJarFilled.JarContents.POISON;
        }
        
        // if it was honey or poison, return the corresponding filled jar
        if (jarContents != null)
        {
            world.setBlockToAir(pos);
            --stack.stackSize;
            //TODO: 1.9 player.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(this)]);
            
            ItemStack honeyJar = new ItemStack(BOPItems.jar_filled, 1, jarContents.ordinal());
            // if there was only one empty jar in the stack, replace it, otherwise add the filledJar elsewhere in the inventory
            if (stack.stackSize <= 0)
            {
                return new ActionResult<ItemStack>(EnumActionResult.FAIL, honeyJar);
            }
            else if (!player.inventory.addItemStackToInventory(honeyJar))
            {
                // no room in inventory, so just drop it on the floor
                player.dropPlayerItemWithRandomChoice(honeyJar, false);
            }
        }

        return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, stack);
    }
    
    @Override
    public boolean itemInteractionForEntity(ItemStack stack, EntityPlayer player, EntityLivingBase target, EnumHand hand)
    {
        // right clicking a pixie with an empty jar catches it in the jar
        if (target instanceof EntityPixie)
        {
            EntityPixie pixie = (EntityPixie)target;
            pixie.setDead();
            --stack.stackSize;
            ItemStack pixieJar = new ItemStack(BOPItems.jar_filled, 1, ItemJarFilled.JarContents.PIXIE.ordinal());
            EntityItem pixieJarEntity = new EntityItem(player.worldObj, player.posX, player.posY, player.posZ, pixieJar);
            if (!player.worldObj.isRemote)
            {
                player.worldObj.spawnEntityInWorld(pixieJarEntity);
                if (!(player instanceof FakePlayer)) {pixieJarEntity.onCollideWithPlayer(player);}
            }
            return true;
        }
        return false;
    }
    
}
  