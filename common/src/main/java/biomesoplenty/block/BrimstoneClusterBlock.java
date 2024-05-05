/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.init.ModTags;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.DoubleBlockHalf;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class BrimstoneClusterBlock extends DoublePlantBlockBOP
{
    protected static final VoxelShape SHAPE = box(3.0D, 0.0D, 3.0D, 13.0D, 16.0D, 13.0D);
    protected static final VoxelShape SHAPE_TOP = box(6.0D, 0.0D, 6.0D, 10.0D, 8.0D, 10.0D);

    public BrimstoneClusterBlock(Properties properties)
    {
        super(properties);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext selectionContext)
    {
        if (state.getValue(HALF) == DoubleBlockHalf.UPPER)
        {
            return SHAPE_TOP;
        }

        return SHAPE;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldReader, BlockPos pos)
    {
        if (state.getBlock() != this) return super.canSurvive(state, worldReader, pos);
        if (state.getValue(HALF) != DoubleBlockHalf.UPPER)
        {
            BlockState soil = worldReader.getBlockState(pos.below());
            if (soil.is(ModTags.Blocks.BRIMSTONE_DECORATION_PLACEABLE) && soil.isFaceSturdy(worldReader, pos.below(), Direction.UP))
            {
                return true;
            }
        }
        else
        {
            BlockState below = worldReader.getBlockState(pos.below());
            return below.getBlock() == this && below.getValue(HALF) == DoubleBlockHalf.LOWER;
        }

        return false;
    }

    @Override
    protected boolean isPathfindable(BlockState state, PathComputationType computationType)
    {
        return false;
    }
}
