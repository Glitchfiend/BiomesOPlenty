/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.FaceAttachedHorizontalDirectionalBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.AttachFace;
import net.minecraft.world.level.pathfinder.PathComputationType;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class NetherCrystalBlock extends FaceAttachedHorizontalDirectionalBlock
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

    protected static boolean mayPlaceOn(BlockState state, BlockGetter worldIn, BlockPos pos) {
        Block block = state.getBlock();
        return block == Blocks.NETHERRACK || block == Blocks.NETHER_QUARTZ_ORE || block == Blocks.BLACKSTONE || block == BOPBlocks.NETHER_CRYSTAL_BLOCK;
    }

    @Override
    public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos) {
        return canAttach(worldIn, pos, getConnectedDirection(state).getOpposite());
    }

    public static boolean canAttach(LevelReader p_220185_0_, BlockPos p_220185_1_, Direction p_220185_2_) {
        BlockPos blockpos = p_220185_1_.relative(p_220185_2_);
        return mayPlaceOn(p_220185_0_.getBlockState(blockpos), p_220185_0_, blockpos);
    }

    public VoxelShape getShape(BlockState state, BlockGetter worldIn, BlockPos pos, CollisionContext context) {
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
    public boolean propagatesSkylightDown(BlockState state, BlockGetter reader, BlockPos pos) {
        return state.getFluidState().isEmpty();
    }

    @Override
    public boolean isPathfindable(BlockState state, BlockGetter worldIn, BlockPos pos, PathComputationType type) {
        return type == PathComputationType.AIR && !this.hasCollision ? true : super.isPathfindable(state, worldIn, pos, type);
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builderIn) {
        builderIn.add(FACING, FACE);
    }
}
