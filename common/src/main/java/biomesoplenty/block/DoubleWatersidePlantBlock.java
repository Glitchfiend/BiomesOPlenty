/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.material.FluidState;

import java.util.Iterator;

public class DoubleWatersidePlantBlock extends DoublePlantBlockBOP
{
    public DoubleWatersidePlantBlock(Block.Properties properties)
    {
        super(properties);
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader level, BlockPos pos)
    {
        if (state.getBlock() != this) return super.canSurvive(state, level, pos);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
        {
            BlockState soil = level.getBlockState(pos.below());
            if (this.mayPlaceOn(soil, level, pos.below()))
            {
                BlockPos blockpos = pos.below();
                Iterator var7 = Direction.Plane.HORIZONTAL.iterator();

                BlockState BlockState;
                FluidState ifluidstate;
                do {
                    if (!var7.hasNext()) {
                        return false;
                    }

                    Direction dir = (Direction)var7.next();
                    BlockState = level.getBlockState(blockpos.relative(dir));
                    ifluidstate = level.getFluidState(blockpos.relative(dir));
                } while(!ifluidstate.is(FluidTags.WATER) && BlockState.getBlock() != Blocks.FROSTED_ICE);

                return true;
            }
        }
        else
        {
           BlockState below = level.getBlockState(pos.below());
           return below.getBlock() == this && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }

        return false;
    }
}
