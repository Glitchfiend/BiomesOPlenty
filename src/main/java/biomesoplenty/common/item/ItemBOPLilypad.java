/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.item;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraft.util.EnumHand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;

public class ItemBOPLilypad extends ItemBOPBlock {

    public ItemBOPLilypad(Block block) {
        super(block);
    }

    // The code for right clicking needs to be overridden to handle the unique way lilies are placed - on top of the water
    // (usually when you point the cursor at water the picked block is whatever is underneath the water - when placing lilies the water itself has to be picked)
    // The below is copied from vanille BlockLilyPad
    @Override
    public ActionResult<ItemStack> onItemRightClick(World worldIn, EntityPlayer playerIn, EnumHand hand)
    {
        ItemStack itemStackIn = playerIn.getHeldItem(hand);
        RayTraceResult movingobjectposition = this.rayTrace(worldIn, playerIn, true);
        
        if (movingobjectposition == null)
        {
            return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
        }
        else
        {
            if (movingobjectposition.typeOfHit == RayTraceResult.Type.BLOCK)
            {
                BlockPos blockpos = movingobjectposition.getBlockPos();

                if (!worldIn.isBlockModifiable(playerIn, blockpos))
                {
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
                }

                if (!playerIn.canPlayerEdit(blockpos.offset(movingobjectposition.sideHit), movingobjectposition.sideHit, itemStackIn))
                {
                    return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
                }

                BlockPos blockpos1 = blockpos.up();
                IBlockState iblockstate = worldIn.getBlockState(blockpos);

                if (iblockstate.getMaterial() == Material.WATER && ((Integer)iblockstate.getValue(BlockLiquid.LEVEL)).intValue() == 0 && worldIn.isAirBlock(blockpos1))
                {
                    // special case for handling block placement with water lilies
                    net.minecraftforge.common.util.BlockSnapshot blocksnapshot = net.minecraftforge.common.util.BlockSnapshot.getBlockSnapshot(worldIn, blockpos1);
                                        
                    worldIn.setBlockState(blockpos1, BOPBlocks.waterlily.getStateFromMeta(itemStackIn.getMetadata()));
                    if (net.minecraftforge.event.ForgeEventFactory.onPlayerBlockPlace(playerIn, blocksnapshot, net.minecraft.util.EnumFacing.UP).isCanceled())
                    {
                        blocksnapshot.restore(true, false);
                        return new ActionResult<ItemStack>(EnumActionResult.FAIL, itemStackIn);
                    }

                    if (!playerIn.capabilities.isCreativeMode)
                    {
                        itemStackIn.setCount(itemStackIn.getCount() - 1);
                    }

                    //TODO: playerIn.addStat(StatList.objectUseStats[Item.getIdFromItem(this)]);
                }
            }

            return new ActionResult<ItemStack>(EnumActionResult.SUCCESS, itemStackIn);
        }
    }
    
    
}