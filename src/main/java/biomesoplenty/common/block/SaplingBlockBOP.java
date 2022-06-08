/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
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
   private final AbstractTreeGrower tree;

   public SaplingBlockBOP(AbstractTreeGrower tree, Block.Properties properties)
   {
      super(tree, properties);
      this.tree = tree;
      this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
   }

   @Override
   public VoxelShape getShape(BlockState blockState, BlockGetter blockGetter, BlockPos blockPos, CollisionContext collisionContext)
   {
      return SHAPE;
   }

   @Override
   public void tick(BlockState state, ServerLevel world, BlockPos pos, RandomSource random)
   {
      super.tick(state, world, pos, random);
      if (!world.isAreaLoaded(pos, 1)) return;
      if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
         this.performBonemeal(world, random, pos, state);
      }

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
         if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(world, rand, pos)) return;
         this.tree.growTree(world, world.getChunkSource().getGenerator(), pos, state, rand);
      }

   }

   /**
    * Whether this IGrowable can grow
    */
   @Override
   public boolean isValidBonemealTarget(BlockGetter worldIn, BlockPos pos, BlockState state, boolean isClient)
   {
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

       if (this == BOPBlocks.PALM_SAPLING.get())
       {
           return ground == BOPBlocks.WHITE_SAND.get() || ground == BOPBlocks.ORANGE_SAND.get() || ground == BOPBlocks.BLACK_SAND.get() || ground == BOPBlocks.ROOTED_SAND.get() || ground == Blocks.RED_SAND || ground == Blocks.SAND || super.canSurvive(state, worldIn, pos);
       }
       if (this == BOPBlocks.HELLBARK_SAPLING.get())
       {
           return ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
       }

       return super.canSurvive(state, worldIn, pos);
   }

   @Override
   public void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder)
   {
      builder.add(STAGE);
   }
}
