/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.MultifaceBlock;
import net.minecraft.world.level.block.SimpleWaterloggedBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class BarnaclesBlock extends MultifaceBlock implements SimpleWaterloggedBlock
{
    public static final MapCodec<BarnaclesBlock> CODEC = simpleCodec(BarnaclesBlock::new);

    public BarnaclesBlock(Properties p_153282_)
    {
        super(p_153282_);
        this.registerDefaultState(this.defaultBlockState().setValue(WATERLOGGED, Boolean.valueOf(false)));
    }

    @Override
    public MapCodec<BarnaclesBlock> codec()
    {
        return CODEC;
    }


    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        if (state.getValue(WATERLOGGED))
        {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        return super.updateShape(state, level, tickAccess, pos, facing, facingPos, facingState, random);
    }

    @Override
    public boolean canBeReplaced(BlockState p_153299_, BlockPlaceContext p_153300_)
    {
        return !p_153300_.getItemInHand().is(BOPBlocks.BARNACLES.asItem()) || super.canBeReplaced(p_153299_, p_153300_);
    }

    @Override
    public FluidState getFluidState(BlockState p_153311_)
    {
        return p_153311_.getValue(WATERLOGGED) ? Fluids.WATER.getSource(false) : super.getFluidState(p_153311_);
    }

    @Override
    public boolean propagatesSkylightDown(BlockState p_181225_)
    {
        return p_181225_.getFluidState().isEmpty();
    }
}