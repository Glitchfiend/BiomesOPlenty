/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.block;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.block.trees.BOPTreeGrower;
import biomesoplenty.block.trees.BOPTreeGrowers;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelReader;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BonemealableBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.grower.AbstractTreeGrower;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.BlockStateProperties;
import net.minecraft.world.level.block.state.properties.IntegerProperty;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.VoxelShape;

public class SaplingBlockBOP extends SaplingBlock implements BonemealableBlock
{
   public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
   public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
   private final BOPTreeGrower tree;

   public SaplingBlockBOP(BOPTreeGrower tree, Block.Properties properties)
   {
      super((AbstractTreeGrower) tree, properties);
      this.tree = tree;
      this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
   }

   @Override
   public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
   {
      return SHAPE;
   }

   @Override
   public void performBonemeal(ServerLevel world, RandomSource rand, BlockPos pos, BlockState state)
   {
      if (state.getValue(STAGE) == 0)
      {
         world.setBlock(pos, state.cycle(STAGE), 4);
      }
      else
      {
        ((AbstractTreeGrower) this.tree).growTree(world, world.getChunkSource().getGenerator(), pos, state, rand);
      }

   }
   /**
    * Whether this IGrowable can grow
    */
   @Override
   public boolean isValidBonemealTarget(LevelReader worldIn, BlockPos pos, BlockState state, boolean b)  {
      return true;
   }

   @Override
   public boolean isBonemealSuccess(Level worldIn, RandomSource rand, BlockPos pos, BlockState state)
   {
      return (double)worldIn.random.nextFloat() < 0.45D;
   }

   @Override
   public void advanceTree(ServerLevel world, BlockPos pos, BlockState state, RandomSource rand)
   {
      this.performBonemeal(world, rand, pos, state);
   }
   
   @Override
   public boolean canSurvive(BlockState state, LevelReader worldIn, BlockPos pos)
   {
       Block ground = worldIn.getBlockState(pos.below()).getBlock();

       if (this == BOPBlocks.PALM_SAPLING)
       {
           return ground == BOPBlocks.WHITE_SAND || ground == BOPBlocks.ORANGE_SAND || ground == BOPBlocks.BLACK_SAND || ground == Blocks.RED_SAND || ground == Blocks.SAND || super.canSurvive(state, worldIn, pos);
       }
       else if (this == BOPBlocks.HELLBARK_SAPLING)
       {
           return ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
       }
       else if (this == BOPBlocks.DEAD_SAPLING)
       {
          return ground == BOPBlocks.DRIED_SALT || super.canSurvive(state, worldIn, pos);
       }
       else if (this == BOPBlocks.EMPYREAL_SAPLING)
       {
           return ground == Blocks.END_STONE || ground == BOPBlocks.UNMAPPED_END_STONE || ground == BOPBlocks.NULL_END_STONE || ground == BOPBlocks.ALGAL_END_STONE || super.canSurvive(state, worldIn, pos);
       }

       return super.canSurvive(state, worldIn, pos);
   }

   @Override
   public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
   {
      builder.add(STAGE);
   }
}
