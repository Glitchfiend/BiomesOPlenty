/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.PipeBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.pathfinder.PathComputationType;

public class BrambleBlock extends PipeBlock
{
    public BrambleBlock(Block.Properties builder)
    {
        super(0.25F, builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }
    
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext context)
    {
        return this.makeConnections(context.getLevel(), context.getClickedPos());
    }

    public BlockState makeConnections(BlockGetter reader, BlockPos pos)
    {
        BlockState block = reader.getBlockState(pos.below());
        BlockState block1 = reader.getBlockState(pos.above());
        BlockState block2 = reader.getBlockState(pos.north());
        BlockState block3 = reader.getBlockState(pos.east());
        BlockState block4 = reader.getBlockState(pos.south());
        BlockState block5 = reader.getBlockState(pos.west());
        return this.defaultBlockState()
        		.setValue(DOWN, Boolean.valueOf(block.getBlock() == this || Block.isShapeFullBlock(block.getCollisionShape(reader, pos.below()))))
        		.setValue(UP, Boolean.valueOf(block1.getBlock() == this || Block.isShapeFullBlock(block1.getCollisionShape(reader, pos.above()))))
        		.setValue(NORTH, Boolean.valueOf(block2.getBlock() == this || Block.isShapeFullBlock(block2.getCollisionShape(reader, pos.north()))))
        		.setValue(EAST, Boolean.valueOf(block3.getBlock() == this || Block.isShapeFullBlock(block3.getCollisionShape(reader, pos.east()))))
        		.setValue(SOUTH, Boolean.valueOf(block4.getBlock() == this || Block.isShapeFullBlock(block4.getCollisionShape(reader, pos.south()))))
        		.setValue(WEST, Boolean.valueOf(block5.getBlock() == this || Block.isShapeFullBlock(block5.getCollisionShape(reader, pos.west()))));
    }

     @Override
     public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
     {
    	Block block = facingState.getBlock();
     	boolean flag = block == this || Block.isShapeFullBlock(facingState.getCollisionShape(worldIn, facingPos));
     	return stateIn.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(flag));
     }

     @Override
     public void entityInside(BlockState state, Level worldIn, BlockPos pos, Entity entityIn)
     {
         if (entityIn instanceof Player)
         {
             Player playerEntity = (Player) entityIn;
             playerEntity.hurt(DamageSource.CACTUS, 1.0F);
         }
      }

     @Override
     protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
     {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
     }

     @Override
     public boolean isPathfindable(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, PathComputationType pathComputationType)
     {
        return false;
     }
}
