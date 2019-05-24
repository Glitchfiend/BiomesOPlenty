package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class MangroveFeature extends Feature<NoFeatureConfig> {
	   public boolean place(IWorld p_212245_1_, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_) {
	      int i = 0;
	      IBlockState iblockstate = BOPBlocks.mangrove_root.getDefaultState();

	      for(int j = 0; j < 64; ++j) {
	         BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
	         if (p_212245_1_.isAirBlock(blockpos) && (!p_212245_1_.getDimension().isNether() || blockpos.getY() < p_212245_1_.getWorld().getHeight() - 1) && iblockstate.isValidPosition(p_212245_1_, blockpos)) {
	            p_212245_1_.setBlockState(blockpos, iblockstate, 2);
	            
	            BlockPos leaves1 = blockpos.up();
	            BlockPos leaves2 = leaves1.north();
	            BlockPos leaves3 = leaves1.south();
	            BlockPos leaves4 = leaves1.east();
	            BlockPos leaves5 = leaves1.west();
	            BlockPos leaves6 = leaves1.up();
	            
	            if (p_212245_1_.getBlockState(leaves1).canBeReplacedByLeaves(p_212245_1_, leaves1))
	            {
	            	p_212245_1_.setBlockState(leaves1, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
	            }
	            
	            if (p_212245_3_.nextInt(2) == 0)
	            {
		            if (p_212245_1_.getBlockState(leaves2).canBeReplacedByLeaves(p_212245_1_, leaves2))
		            {
		            	p_212245_1_.setBlockState(leaves2, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
		            }
		            if (p_212245_1_.getBlockState(leaves3).canBeReplacedByLeaves(p_212245_1_, leaves3))
		            {
		            	p_212245_1_.setBlockState(leaves3, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
		            }
		            if (p_212245_1_.getBlockState(leaves4).canBeReplacedByLeaves(p_212245_1_, leaves4))
		            {
		            	p_212245_1_.setBlockState(leaves4, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
		            }
		            if (p_212245_1_.getBlockState(leaves5).canBeReplacedByLeaves(p_212245_1_, leaves5))
		            {
		            	p_212245_1_.setBlockState(leaves5, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
		            }
		            if (p_212245_1_.getBlockState(leaves6).canBeReplacedByLeaves(p_212245_1_, leaves6))
		            {
		            	p_212245_1_.setBlockState(leaves6, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
		            }
	            }
	            ++i;
	         }
	      }

	      return i > 0;
	   }
	}