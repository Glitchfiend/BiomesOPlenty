/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.damagesource.BOPDamageTypes;
import com.mojang.serialization.MapCodec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
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
    public static final MapCodec<BrambleBlock> CODEC = simpleCodec(BrambleBlock::new);

    public BrambleBlock(Block.Properties builder)
    {
        super(0.25F, builder);
        this.registerDefaultState(this.stateDefinition.any().setValue(NORTH, Boolean.valueOf(false)).setValue(EAST, Boolean.valueOf(false)).setValue(SOUTH, Boolean.valueOf(false)).setValue(WEST, Boolean.valueOf(false)).setValue(UP, Boolean.valueOf(false)).setValue(DOWN, Boolean.valueOf(false)));
    }

    @Override
    public MapCodec<BrambleBlock> codec()
    {
        return CODEC;
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
        		.setValue(DOWN, Boolean.valueOf(block.getBlock() == this || (block.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block.getValue(BrambleLeavesBlock.FACING) == Direction.DOWN) || Block.isShapeFullBlock(block.getCollisionShape(reader, pos.below()))))
        		.setValue(UP, Boolean.valueOf(block1.getBlock() == this || (block1.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block1.getValue(BrambleLeavesBlock.FACING) == Direction.UP) || Block.isShapeFullBlock(block1.getCollisionShape(reader, pos.above()))))
        		.setValue(NORTH, Boolean.valueOf(block2.getBlock() == this || (block2.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block2.getValue(BrambleLeavesBlock.FACING) == Direction.NORTH) || Block.isShapeFullBlock(block2.getCollisionShape(reader, pos.north()))))
        		.setValue(EAST, Boolean.valueOf(block3.getBlock() == this || (block3.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block3.getValue(BrambleLeavesBlock.FACING) == Direction.EAST) || Block.isShapeFullBlock(block3.getCollisionShape(reader, pos.east()))))
        		.setValue(SOUTH, Boolean.valueOf(block4.getBlock() == this || (block4.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block4.getValue(BrambleLeavesBlock.FACING) == Direction.SOUTH) || Block.isShapeFullBlock(block4.getCollisionShape(reader, pos.south()))))
        		.setValue(WEST, Boolean.valueOf(block5.getBlock() == this || (block5.getBlock() == BOPBlocks.BRAMBLE_LEAVES && block5.getValue(BrambleLeavesBlock.FACING) == Direction.WEST) || Block.isShapeFullBlock(block5.getCollisionShape(reader, pos.west()))));
    }

     @Override
     public BlockState updateShape(BlockState stateIn, Direction facing, BlockState facingState, LevelAccessor worldIn, BlockPos currentPos, BlockPos facingPos)
     {
    	Block block = facingState.getBlock();
     	boolean flag = block == this || (block == BOPBlocks.BRAMBLE_LEAVES && facingState.getValue(BrambleLeavesBlock.FACING) == facing) || Block.isShapeFullBlock(facingState.getCollisionShape(worldIn, facingPos));
     	return stateIn.setValue(PROPERTY_BY_DIRECTION.get(facing), Boolean.valueOf(flag));
     }

     @Override
     public void entityInside(BlockState state, Level level, BlockPos pos, Entity entityIn)
     {
         if (entityIn instanceof Player)
         {
             Player playerEntity = (Player) entityIn;
             playerEntity.hurt(level.damageSources().source(BOPDamageTypes.BRAMBLE), 1.0F);
         }
      }

     @Override
     protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
     {
        builder.add(NORTH, EAST, SOUTH, WEST, UP, DOWN);
     }

     @Override
     protected boolean isPathfindable(BlockState state, PathComputationType computationType)
     {
        return false;
     }
}
