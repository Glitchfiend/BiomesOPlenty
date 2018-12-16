/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.handler;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.fluids.BloodFluid;
import biomesoplenty.common.fluids.HotSpringWaterFluid;
import biomesoplenty.common.fluids.blocks.BlockBloodFluid;
import biomesoplenty.common.fluids.blocks.BlockHotSpringWaterFluid;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Items;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.RayTraceResult;
import net.minecraftforge.common.ForgeModContainer;
import net.minecraftforge.event.entity.player.FillBucketEvent;
import net.minecraftforge.fluids.Fluid;
import net.minecraftforge.fluids.UniversalBucket;
import net.minecraftforge.fml.common.eventhandler.Event.Result;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class BucketEventHandler
{
    
    @SubscribeEvent
    public void onRightClickHoldingBucket(FillBucketEvent event)
    {
        // check we're using a bucket, on a block we can modify
        if (event.getEmptyBucket().getItem() != Items.BUCKET) {return;}
        if (event.getTarget() == null || event.getTarget().typeOfHit != RayTraceResult.Type.BLOCK) {return;}
        BlockPos blockpos = event.getTarget().getBlockPos();
        if (!event.getWorld().isBlockModifiable(event.getEntityPlayer(), blockpos)) {return;}
        if (!event.getEntityPlayer().canPlayerEdit(blockpos.offset(event.getTarget().sideHit), event.getTarget().sideHit, event.getEmptyBucket())) {return;}
        
        // determine if the block is one of our BOP fluids
        IBlockState iblockstate = event.getWorld().getBlockState(blockpos);
        Fluid filled_fluid = null;
        if (iblockstate.getBlock() == BOPBlocks.blood && iblockstate.getValue(BlockBloodFluid.LEVEL).intValue() == 0)
        {
            filled_fluid = BloodFluid.instance;
        }
        else if (iblockstate.getBlock() == BOPBlocks.hot_spring_water && iblockstate.getValue(BlockHotSpringWaterFluid.LEVEL).intValue() == 0)
        {
            filled_fluid = HotSpringWaterFluid.instance;
        }
        else
        {
            return;
        }
        
        // remove the fluid and return the appropriate filled bucket
        event.setResult(Result.ALLOW);
        event.setFilledBucket(UniversalBucket.getFilledBucket(ForgeModContainer.getInstance().universalBucket, filled_fluid));
        event.getWorld().setBlockToAir(blockpos);
    }
        
}