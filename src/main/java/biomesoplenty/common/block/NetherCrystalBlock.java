/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import net.minecraft.block.*;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.pathfinding.PathType;
import net.minecraft.state.DirectionProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.AttachFace;
import net.minecraft.util.Direction;
import net.minecraft.util.Mirror;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;

import javax.annotation.Nullable;
import java.util.Map;

public class NetherCrystalBlock extends HorizontalFaceBlock
{
    protected static final VoxelShape FLOOR_AABB = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 13.0D, 14.0D);
    protected static final VoxelShape CEILING_AABB = Block.box(2.0D, 3.0D, 2.0D, 14.0D, 16.0D, 14.0D);
    protected static final VoxelShape NORTH_AABB = Block.box(2.0D, 2.0D, 3.0D, 14.0D, 14.0D, 16.0D);
    protected static final VoxelShape SOUTH_AABB = Block.box(2.0D, 2.0D, 0.0D, 14.0D, 14.0D, 13.0D);
    protected static final VoxelShape EAST_AABB = Block.box(0.0D, 2.0D, 2.0D, 13.0D, 14.0D, 14.0D);
    protected static final VoxelShape WEST_AABB = Block.box(3.0D, 2.0D, 2.0D, 16.0D, 14.0D, 14.0D);

    public NetherCrystalBlock(Block.Properties properties)
    {
        super(properties);
        this.registerDefaultState(this.stateDefinition.any().setValue(FACE, AttachFace.FLOOR).setValue(FACING, Direction.NORTH));
    }

    protected static boolean mayPlaceOn(BlockState state, IBlockReader worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.NETHERRACK || block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.BLACKSTONE || block == BOPBlocks.nether_crystal_block;
    }

    @Override
    public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos) {
        return canAttach(worldIn, pos, getConnectedDirection(state).getOpposite());
    }

    public static boolean canAttach(IWorldReader p_220185_0_, BlockPos p_220185_1_, Direction p_220185_2_) {
        BlockPos blockpos = p_220185_1_.relative(p_220185_2_);
        return mayPlaceOn(p_220185_0_.getBlockState(blockpos), p_220185_0_, blockpos);
    }

    public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext context) {
        Direction direction = state.getValue(FACING);
        switch(state.getValue(FACE))
        {
            case FLOOR:
                return FLOOR_AABB;
            case WALL:
                switch(direction)
                {
                    case EAST:
                        return EAST_AABB;
                    case WEST:
                        return WEST_AABB;
                    case SOUTH:
                        return SOUTH_AABB;
                    case NORTH:
                    default:
                        return NORTH_AABB;
                }
            case CEILING:
            default:
                return CEILING_AABB;
        }
    }

    @Override
    public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public boolean isPathfindable(BlockState state, IBlockReader worldIn, BlockPos pos, PathType type) {
        return type == PathType.AIR && !this.hasCollision ? true : super.isPathfindable(state, worldIn, pos, type);
    }

    @Override
    protected void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builderIn) {
        builderIn.add(FACING, FACE);
    }
}
