/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.block;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.IGrowable;
import net.minecraft.block.state.IBlockState;
import net.minecraft.block.trees.AbstractTree;
import net.minecraft.init.Blocks;
import net.minecraft.state.IntegerProperty;
import net.minecraft.state.StateContainer;
import net.minecraft.state.properties.BlockStateProperties;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.world.IBlockReader;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

public class BlockSaplingBOP extends BlockSapling implements IGrowable
{
   public static final IntegerProperty STAGE = BlockStateProperties.STAGE_0_1;
   public static final VoxelShape SHAPE = Block.makeCuboidShape(2.0D, 0.0D, 2.0D, 14.0D, 12.0D, 14.0D);
   private final AbstractTree tree;

   public BlockSaplingBOP(AbstractTree tree, Block.Properties properties)
   {
      super(tree, properties);
      this.tree = tree;
      this.setDefaultState(this.stateContainer.getBaseState().with(STAGE, Integer.valueOf(0)));
   }

   @Override
   public VoxelShape getShape(IBlockState state, IBlockReader worldIn, BlockPos pos)
   {
      return SHAPE;
   }

   @Override
   public void tick(IBlockState state, World worldIn, BlockPos pos, Random random)
   {
      super.tick(state, worldIn, pos, random);
      if (!worldIn.isAreaLoaded(pos, 1)) return; // Forge: prevent loading unloaded chunks when checking neighbor's light
      if (worldIn.getLight(pos.up()) >= 9 && random.nextInt(7) == 0) {
         this.grow(worldIn, pos, state, random);
      }

   }

   @Override
   public void grow(IWorld worldIn, BlockPos pos, IBlockState state, Random rand)
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
   public boolean canGrow(IBlockReader worldIn, BlockPos pos, IBlockState state, boolean isClient)
   {
      return true;
   }

   @Override
   public boolean canUseBonemeal(World worldIn, Random rand, BlockPos pos, IBlockState state)
   {
      return (double)worldIn.rand.nextFloat() < 0.45D;
   }

   @Override
   public void grow(World worldIn, Random rand, BlockPos pos, IBlockState state)
   {
      this.grow(worldIn, pos, state, rand);
   }

   @Override
   public void fillStateContainer(StateContainer.Builder<Block, IBlockState> builder)
   {
      builder.add(STAGE);
   }
   
   @Override
   public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
   	return Blocks.OAK_SAPLING.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
       return Blocks.OAK_SAPLING.getFireSpreadSpeed(state,world, pos, face);
   }
}
