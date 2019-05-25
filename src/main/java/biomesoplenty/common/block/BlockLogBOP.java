package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockLog;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockLogBOP extends BlockLog
{
   private final MaterialColor field_196504_b;

   public BlockLogBOP(MaterialColor p_i48367_1_, Block.Properties p_i48367_2_)
   {
      super(p_i48367_1_, p_i48367_2_);
      this.field_196504_b = p_i48367_1_;
   }

   /**
    * Get the MapColor for this Block and the given BlockState
    * @deprecated call via {@link IBlockState#getMapColor(IBlockAccess,BlockPos)} whenever possible.
    * Implementing/overriding is fine.
    */
   public MaterialColor getMapColor(IBlockState state, IBlockReader worldIn, BlockPos pos)
   {
      return state.get(AXIS) == EnumFacing.Axis.Y ? this.field_196504_b : this.materialColor;
   }
   
   @Override
   public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
   	return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
       return Blocks.OAK_LOG.getFireSpreadSpeed(state,world, pos, face);
   }
}