/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.*;
import net.minecraft.block.trees.Tree;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;
import net.minecraft.world.server.ServerWorld;

import java.util.Random;

public class SaplingBlockBOP extends SaplingBlock implements IGrowable
{
   public static final IntegerProperty STAGE = BlockStateProperties.STAGE;
   public static final VoxelShape SHAPE = Block.box(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
   private final Tree tree;

   public SaplingBlockBOP(Tree tree, Block.Properties properties)
   {
      super(tree, properties);
      this.tree = tree;
      this.registerDefaultState(this.stateDefinition.any().setValue(STAGE, Integer.valueOf(0)));
   }

   @Override
   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
   {
      return SHAPE;
   }

   @Override
   public void tick(BlockState state, ServerWorld world, BlockPos pos, Random random)
   {
      super.tick(state, world, pos, random);
      if (!world.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
      if (world.getMaxLocalRawBrightness(pos.above()) >= 9 && random.nextInt(7) == 0) {
         this.performBonemeal(world, random, pos, state);
      }

   }

   @Override
   public void performBonemeal(ServerWorld world, Random rand, BlockPos pos, BlockState state)
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
   public boolean isValidBonemealTarget(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
   {
      return true;
   }

   @Override
   public boolean isBonemealSuccess(World worldIn, Random rand, BlockPos pos, BlockState state)
   {
      return (double)worldIn.random.nextFloat() < 0.45D;
   }

   @Override
   public void advanceTree(ServerWorld world, BlockPos pos, BlockState state, Random rand)
   {
      this.performBonemeal(world, rand, pos, state);
   }
   
   @Override
   public boolean canSurvive(BlockState state, IWorldReader worldIn, BlockPos pos)
   {
       Block ground = worldIn.getBlockState(pos.below()).getBlock();

       if (this == BOPBlocks.palm_sapling)
       {
           return ground == BOPBlocks.white_sand || ground == BOPBlocks.orange_sand || ground == BOPBlocks.black_sand || ground == Blocks.RED_SAND || ground == Blocks.SAND || super.canSurvive(state, worldIn, pos);
       }
       if (this == BOPBlocks.hellbark_sapling)
       {
           return ground == Blocks.NETHERRACK || super.canSurvive(state, worldIn, pos);
       }

       return super.canSurvive(state, worldIn, pos);
   }

   @Override
   public void createBlockStateDefinition(StateContainer.Builder<Block, BlockState> builder)
   {
      builder.add(STAGE);
   }
}
