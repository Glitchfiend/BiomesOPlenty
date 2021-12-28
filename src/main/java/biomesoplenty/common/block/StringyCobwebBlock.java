/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class StringyCobwebBlock extends Block
{
    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;
    protected static final VoxelShape SHAPE_X = Block.box(0.0D, 0.0D, 7.0D, 16.0D, 16.0D, 9.0D);
    protected static final VoxelShape SHAPE_Z = Block.box(7.0D, 0.0D, 0.0D, 9.0D, 16.0D, 16.0D);

    public StringyCobwebBlock(Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACING, Direction.NORTH));
    }

    @Override
    public BlockState rotate(BlockState state, Rotation rot) {
        return state.setValue(FACING, rot.rotate(state.getValue(FACING)));
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirrorIn) {
        return state.rotate(mirrorIn.getRotation(state.getValue(FACING)));
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
    }

    @Override
    public VoxelShape getShape(BlockState p_220053_1_, BlockGetter p_220053_2_, BlockPos p_220053_3_, CollisionContext p_220053_4_)
    {
        switch((Direction)p_220053_1_.getValue(FACING))
        {
            case EAST:
            case WEST:
                return SHAPE_X;
            case NORTH:
            case SOUTH:
            default:
                return SHAPE_Z;
        }
    }

    @Override
    public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
    {
        return facing.getOpposite() == stateIn.getValue(FACING) && !stateIn.canSurvive(worldIn, currentPos) ? Blocks.AIR.defaultBlockState() : stateIn;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
    {
        Direction direction = state.getValue(FACING);
        BlockPos blockpos = pos.relative(direction.getOpposite());
        BlockState blockstate = worldIn.getBlockState(blockpos);
        BlockState blockstate1 = worldIn.getBlockState(pos.below());
        BlockPos blockpos1 = pos.relative(direction);
        BlockState blockstate2 = worldIn.getBlockState(blockpos1);
        BlockState blockstate3 = worldIn.getBlockState(pos.above());

        BlockPos webpos = pos.relative(direction);
        BlockPos webpos1 = pos.relative(direction.getOpposite());
        BlockState webstate = worldIn.getBlockState(webpos.above());
        BlockState webstate1 = worldIn.getBlockState(webpos1.below());

        if (blockstate.getMaterial().isSolid() || blockstate1.getMaterial().isSolid())
        {
            if (webstate.getBlock() == BOPBlocks.STRINGY_COBWEB)
            {
                return true;
            }
            else
            {
                return false;
            }
        }
        else
        {
            if (blockstate2.getMaterial().isSolid() || blockstate3.getMaterial().isSolid())
            {
                if (webstate1.getBlock() == BOPBlocks.STRINGY_COBWEB)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
            else
            {
                if (webstate.getBlock() == BOPBlocks.STRINGY_COBWEB && webstate1.getBlock() == BOPBlocks.STRINGY_COBWEB)
                {
                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
    }

    @Override
    @Nullable
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        BlockState blockstate = super.getStateForPlacement(context);
        Level LevelReader = context.getLevel();
        BlockPos blockpos = context.getClickedPos();
        Direction[] adirection = context.getNearestLookingDirections();

        for (Direction direction : adirection) {
            if (direction.getAxis().isHorizontal()) {
                blockstate = blockstate.setValue(FACING, direction.getOpposite());
                if (blockstate.canSurvive(LevelReader, blockpos)) {
                    return blockstate;
                }
            }
        }

        return null;
    }
}
