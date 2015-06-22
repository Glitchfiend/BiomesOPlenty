/*******************************************************************************
 * Copyright 2014, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.stats.StatList;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MovingObjectPosition;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.item.BOPItems;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;
import biomesoplenty.common.fluids.blocks.BlockHoneyFluid;
import biomesoplenty.common.fluids.blocks.BlockPoisonFluid;

public class BucketEventHandler
{
    
    @SubscribeEvent
    public void arse(FillBucketEvent event)
    {
        // check we're using a bucket, on a block we can modify
        if (event.current.getItem() != Items.bucket) {return;}
        if (event.target.typeOfHit != MovingObjectPosition.MovingObjectType.BLOCK) {return;}
        BlockPos blockpos = event.target.getBlockPos();
        if (!event.world.isBlockModifiable(event.entityPlayer, blockpos)) {return;}
        if (!event.entityPlayer.canPlayerEdit(blockpos.offset(event.target.sideHit), event.target.sideHit, event.current)) {return;}
        
        // determine if the block is one of our BOP fluids
        IBlockState iblockstate = event.world.getBlockState(blockpos);
        Item filled_bucket = null;
        if (iblockstate.getBlock() == BOPBlocks.honey && ((Integer)iblockstate.getValue(BlockHoneyFluid.LEVEL)).intValue() == 0)
        {
            filled_bucket = BOPItems.honey_bucket;
        }
        else if (iblockstate.getBlock() == BOPBlocks.blood && ((Integer)iblockstate.getValue(BlockBloodFluid.LEVEL)).intValue() == 0)
        {
            filled_bucket = BOPItems.blood_bucket;
        }
        else if (iblockstate.getBlock() == BOPBlocks.poison && ((Integer)iblockstate.getValue(BlockPoisonFluid.LEVEL)).intValue() == 0)
        {
            filled_bucket = BOPItems.poison_bucket;
        }
        else
        {
            return;
        }
        
        // remove the fluid and return the appropriate filled bucket
        event.setResult(Result.ALLOW);
        event.result = new ItemStack(filled_bucket);
        event.world.setBlockToAir(blockpos);
        event.entityPlayer.triggerAchievement(StatList.objectUseStats[Item.getIdFromItem(event.current.getItem())]);
    }
        
}