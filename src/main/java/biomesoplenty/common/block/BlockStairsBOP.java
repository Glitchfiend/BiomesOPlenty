package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockStairsBOP extends BlockStairs
{
	private IBlockState modelState;
	private Block modelBlock;

	public BlockStairsBOP(IBlockState state, Block.Properties properties)
	   {
	      super(state, properties);
	      this.modelBlock = state.getBlock();
	      this.modelState = state;
	   }
   
   @Override
   public int getFlammability(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
	   Block block = state.getBlock();
	    
	   if (block == BOPBlocks.mud_brick_stairs || block == BOPBlocks.white_sandstone_stairs)
	   {
		   return 0;
	   }
	   
	   return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(IBlockState state, IBlockReader world, BlockPos pos, EnumFacing face)
   {
	   Block block = state.getBlock();
	    
	   if (block == BOPBlocks.mud_brick_stairs || block == BOPBlocks.white_sandstone_stairs)
	   {
		   return 0;
	   }
	   
	   return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
}