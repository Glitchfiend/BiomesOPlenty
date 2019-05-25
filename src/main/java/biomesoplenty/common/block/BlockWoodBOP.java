package biomesoplenty.common.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockRotatedPillar;
import net.minecraft.block.material.MaterialColor;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockWoodBOP extends BlockRotatedPillar
{
   public BlockWoodBOP(Block.Properties p_i48367_2_)
   {
      super(p_i48367_2_);
   }
   
   @Override
   public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
   	return Blocks.OAK_WOOD.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
       return Blocks.OAK_WOOD.getFireSpreadSpeed(state,world, pos, face);
   }
}