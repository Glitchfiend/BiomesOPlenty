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
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldReader;
import net.minecraft.world.World;

import java.util.Random;

public class SaplingBlockBOP extends SaplingBlock implements IGrowable
{
   public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
   public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
   private final Tree tree;

   public SaplingBlockBOP(Tree tree, Block.Properties properties)
   {
      super(tree, properties);
      this.tree = tree;
      this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, Integer.valueOf(0)));
   }

   @Override
   public VoxelShape getShape(BlockState state, IBlockReader worldIn, BlockPos pos, ISelectionContext selectionContext)
   {
      return SHAPE;
   }

   @Override
   public void tick(BlockState state, World worldIn, BlockPos pos, Random random)
   {
      super.tick(state, worldIn, pos, random);
      if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
      if (worldIn.getLight(pos.up()) >= 9 && random.nextInt(7) == 0) {
         this.grow(worldIn, pos, state, random);
      }

   }

   @Override
   public void grow(IWorld worldIn, BlockPos pos, BlockState state, Random rand)
   {
      if (state.get(STAGE) == 0)
      {
         worldIn.setBlockState(pos, state.cycle(STAGE), 4);
      }
      else
      {
         if (!net.minecraftforge.event.ForgeEventFactory.saplingGrowTree(worldIn, rand, pos)) return;
         this.tree.spawn(worldIn, pos, state, rand);
      }

   }

   /**
    * Whether this IGrowable can grow
    */
   @Override
   public boolean canGrow(IBlockReader worldIn, BlockPos pos, BlockState state, boolean isClient)
   {
      return true;
   }

   @Override
   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, BlockState state)
   {
      return (double)worldIn.rand.nextFloat() < 0.45D;
   }

   @Override
   public void grow(World worldIn, Random rand, BlockPos pos, BlockState state)
   {
      this.grow(worldIn, pos, state, rand);
   }
   
   @Override
   public boolean isValidPosition(BlockState state, IWorldReader worldIn, BlockPos pos)
   {
       Block ground = worldIn.getBlockState(pos.down()).getBlock();

       if (this == BOPBlocks.palm_sapling)
       {
           return ground == BOPBlocks.white_sand || ground == Blocks.RED_SAND || ground == Blocks.SAND || super.isValidPosition(state, worldIn, pos);
       }
       if (this == BOPBlocks.hellbark_sapling)
       {
           return ground == Blocks.NETHERRACK || super.isValidPosition(state, worldIn, pos);
       }
       if (this == BOPBlocks.ethereal_sapling)
       {
           return ground == Blocks.END_STONE || super.isValidPosition(state, worldIn, pos);
       }

       return super.isValidPosition(state, worldIn, pos);
   }

   @Override
   public void fillStateContainer(StateContainer.Builder<Block, BlockState> builder)
   {
      builder.add(STAGE);
   }
}
