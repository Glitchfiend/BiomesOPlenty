package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class LogBlockBOP extends LogBlock
{
   private final MaterialColor field_196504_b;

   public LogBlockBOP(MaterialColor p_i48367_1_, Block.Properties p_i48367_2_)
   {
      super(p_i48367_1_, p_i48367_2_);
      this.field_196504_b = p_i48367_1_;
   }

   @Override
   public MaterialColor getMaterialColor(BlockState state, IBlockReader worldIn, BlockPos pos)
   {
      return state.get(AXIS) == Direction.Axis.Y ? this.field_196504_b : this.materialColor;
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