/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.ScheduledTickAccess;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BooleanProperty;
import net.minecraft.world.level.material.FluidState;
import net.minecraft.world.level.material.Fluids;

public class WillowLeavesBlock extends LeavesBlockBOP
{
    public static final BooleanProperty MOSSY = BooleanProperty.create("mossy");

    public WillowLeavesBlock(Properties p_273704_)
    {
        super(p_273704_);
        this.registerDefaultState(this.stateDefinition.any().setValue(DISTANCE, Integer.valueOf(7)).setValue(PERSISTENT, Boolean.valueOf(false)).setValue(WATERLOGGED, Boolean.valueOf(false)).setValue(MOSSY, Boolean.valueOf(false)));
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        if (state.getValue(WATERLOGGED))
        {
            tickAccess.scheduleTick(pos, Fluids.WATER, Fluids.WATER.getTickDelay(level));
        }

        int i = getDistanceAt(facingState) + 1;
        if (i != 1 || state.getValue(DISTANCE) != i)
        {
            tickAccess.scheduleTick(pos, this, 1);
        }

        BlockState below = level.getBlockState(pos.below());
        return state.setValue(MOSSY, Boolean.valueOf(isMoss(below)));
    }

    @Override
    public BlockState getStateForPlacement(BlockPlaceContext p_54424_) {
        FluidState fluidstate = p_54424_.getLevel().getFluidState(p_54424_.getClickedPos());
        BlockState blockstate = this.defaultBlockState().setValue(PERSISTENT, Boolean.valueOf(true)).setValue(WATERLOGGED, Boolean.valueOf(fluidstate.getType() == Fluids.WATER));
        BlockState blockstateBelow = p_54424_.getLevel().getBlockState(p_54424_.getClickedPos().below());
        return updateDistance(blockstate.setValue(MOSSY, Boolean.valueOf(isMoss(blockstateBelow))), p_54424_.getLevel(), p_54424_.getClickedPos());
    }

    private static BlockState updateDistance(BlockState p_54436_, LevelAccessor p_54437_, BlockPos p_54438_) {
        int i = 7;
        BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

        for(Direction direction : Direction.values()) {
            blockpos$mutableblockpos.setWithOffset(p_54438_, direction);
            i = Math.min(i, getDistanceAt(p_54437_.getBlockState(blockpos$mutableblockpos)) + 1);
            if (i == 1) {
                break;
            }
        }

        return p_54436_.setValue(DISTANCE, Integer.valueOf(i));
    }

    private static int getDistanceAt(BlockState p_54464_) {
        return getOptionalDistanceAt(p_54464_).orElse(7);
    }

    private static boolean isMoss(BlockState p_154649_)
    {
        return p_154649_.getBlock() == BOPBlocks.SPANISH_MOSS || p_154649_.getBlock() == BOPBlocks.SPANISH_MOSS_PLANT;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> p_56651_) {
        p_56651_.add(DISTANCE, PERSISTENT, WATERLOGGED, MOSSY);
    }
}