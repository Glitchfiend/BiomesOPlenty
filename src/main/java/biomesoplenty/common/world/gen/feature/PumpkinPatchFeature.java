package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockCarvedPumpkin;
import net.minecraft.block.BlockLeaves;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class PumpkinPatchFeature extends Feature<NoFeatureConfig>
{
   @Override
   public boolean place(IWorld p_212245_1_, IChunkGenerator<? extends IChunkGenSettings> p_212245_2_, Random p_212245_3_, BlockPos p_212245_4_, NoFeatureConfig p_212245_5_)
   {
      int i = 0;

      for(int j = 0; j < 64; ++j)
      {
         BlockPos blockpos = p_212245_4_.add(p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8), p_212245_3_.nextInt(4) - p_212245_3_.nextInt(4), p_212245_3_.nextInt(8) - p_212245_3_.nextInt(8));
         if (p_212245_1_.getBlockState(blockpos).canBeReplacedByLeaves(p_212245_1_, blockpos) && p_212245_1_.getBlockState(blockpos.down()).getBlock() == Blocks.GRASS_BLOCK)
         {
        	
        	if (p_212245_3_.nextInt(3) == 0)
        	{
        		int rand = p_212245_3_.nextInt(50);
        		
        		if (rand > 10)
        		{
        			p_212245_1_.setBlockState(blockpos, Blocks.PUMPKIN.getDefaultState(), 2);
        		}
        		else if (rand > 1)
        		{
        			p_212245_1_.setBlockState(blockpos, Blocks.CARVED_PUMPKIN.getDefaultState().with(BlockCarvedPumpkin.FACING, EnumFacing.byIndex(2 + p_212245_3_.nextInt(4))), 2);
        		}
        		else
        		{
        			p_212245_1_.setBlockState(blockpos, Blocks.JACK_O_LANTERN.getDefaultState().with(BlockCarvedPumpkin.FACING, EnumFacing.byIndex(2 + p_212245_3_.nextInt(4))), 2);
        		}
        	}
        	else
        	{
        		p_212245_1_.setBlockState(blockpos, Blocks.OAK_LEAVES.getDefaultState().with(BlockLeaves.PERSISTENT, true), 2);
        	}
            
            ++i;
         }
      }

      return i > 0;
   }
}