/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import java.util.Random;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IChunkGenSettings;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

public class FernGrassFeature extends Feature<NoFeatureConfig>
{
	   public BlockState chooseGrassState(Random rand)
	   {
	      return rand.nextInt(3) == 0 ? Blocks.FERN.getDefaultState() : Blocks.GRASS.getDefaultState();
	   }

	   @Override
	   public boolean place(IWorld world, IChunkGenerator<? extends IChunkGenSettings> generator, Random rand, BlockPos pos, NoFeatureConfig config)
	   {
	      BlockState BlockState = this.chooseGrassState(rand);

	      for (BlockState BlockState1 = world.getBlockState(pos); (BlockState1.isAir(world, pos) || BlockState1.isIn(BlockTags.LEAVES)) && pos.getY() > 0; BlockState1 = world.getBlockState(pos))
	      {
	         pos = pos.down();
	      }

	      int i = 0;

	      for (int j = 0; j < 128; ++j)
	      {
	         BlockPos blockpos = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
	         if (world.isAirBlock(blockpos) && BlockState.isValidPosition(world, blockpos))
	         {
	            world.setBlockState(blockpos, BlockState, 2);
	            ++i;
	         }
	      }

	      return i > 0;
	   }
	}