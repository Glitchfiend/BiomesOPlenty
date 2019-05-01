/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class ShortGrassFeature extends Feature<NoFeatureConfig>
{
	   public IBlockState chooseGrassState(Random rand)
	   {
	      return BOPBlocks.short_grass.getDefaultState();
	   }

	   @Override
	   public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	   {
	      IBlockState iblockstate = this.chooseGrassState(rand);

	      for (IBlockState iblockstate1 = world.getBlockState(pos); (iblockstate1.isAir(world, pos) || iblockstate1.isIn(BlockTags.LEAVES)) && pos.getY() > 0; iblockstate1 = world.getBlockState(pos))
	      {
	         pos = pos.down();
	      }

	      int i = 0;

	      for (int j = 0; j < 128; ++j)
	      {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (world.isAirBlock(blockpos) && iblockstate.isValidPosition(world, blockpos))
	         {
	            world.setBlockState(blockpos, iblockstate, 2);
	            ++i;
	         }
	      }

	      return i > 0;
	   }
	}