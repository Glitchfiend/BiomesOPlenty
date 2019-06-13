package biomesoplenty.common.block;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.StairsBlock;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockReader;

public class BlockStairsBOP extends StairsBlock
{
	private BlockState modelState;
	private Block modelBlock;

	public BlockStairsBOP(BlockState state, Block.Properties properties)
	   {
	      super(state, properties);
	      this.modelBlock = state.getBlock();
	      this.modelState = state;
	   }
   
   @Override
   public int getFlammability(BlockState state, IBlockReader world, BlockPos pos, Direction face)
   {
	   Block block = state.getBlock();
	    
	   if (block == BOPBlocks.mud_brick_stairs || block == BOPBlocks.white_sandstone_stairs)
	   {
		   return 0;
	   }
	   
	   return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
   
   @Override
   public int getFireSpreadSpeed(BlockState state, IBlockReader world, BlockPos pos, Direction face)
   {
	   Block block = state.getBlock();
	    
	   if (block == BOPBlocks.mud_brick_stairs || block == BOPBlocks.white_sandstone_stairs)
	   {
		   return 0;
	   }
	   
	   return Blocks.OAK_LOG.getFlammability(state, world, pos, face);
   }
}