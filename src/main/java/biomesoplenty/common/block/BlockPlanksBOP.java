package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockPlanksBOP extends Block
{
   public BlockPlanksBOP(Block.Properties p_i48367_2_)
   {
      super(p_i48367_2_);
   }
   
   @Override
   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
   {
   	return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
   {
       return Blocks.OAK_LOG.getFireSpreadSpeed(state,world, pos, face);
   }
}