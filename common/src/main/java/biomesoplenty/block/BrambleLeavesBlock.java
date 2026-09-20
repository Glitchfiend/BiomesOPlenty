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
import net.minecraft.world.level.*;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BrambleLeavesBlock extends DirectionalBlock
{
    protected static final VoxelShape HORIZONTAL = Block.box(0.0D, 4.0D, 0.0D, 16.0D, 12.0D, 16.0D);
    protected static final VoxelShape VERTICAL = Block.box(0.0D, 0.0D, 4.0D, 16.0D, 16.0D, 12.0D);

    public BrambleLeavesBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.UP));
    }


    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context)
    {
        Direction facing = state.getValue(FACING);

        if (facing == Direction.UP || facing == Direction.DOWN)
        {
            return VERTICAL;
        }

        return HORIZONTAL;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    protected BlockState updateShape(BlockState state, LevelReader level, ScheduledTickAccess tickAccess, BlockPos pos, Direction facing, BlockPos facingPos, BlockState facingState, RandomSource random)
    {
        return facing.getOpposite() == state.getValue(FACING) && !state.canSurvive(level, pos) ? Blocks.AIR.defaultBlockState() : state;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        return blockstate.getBlock() == BOPBlocks.BRAMBLE || Block.isShapeFullBlock(blockstate.getCollisionShape(worldIn, blockpos));
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockState blockstate = super.getStateForPlacement(context);
        Level LevelReader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction direction = context.getClickedFace();

        blockstate = blockstate.setValue(FACING, direction);
        if (blockstate.canSurvive(LevelReader, blockpos))
        {
            return blockstate;
        }

        return null;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
