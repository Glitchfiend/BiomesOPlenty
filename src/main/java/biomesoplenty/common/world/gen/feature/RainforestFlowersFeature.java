/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RainforestFlowersFeature extends DefaultFlowersFeatureNoConfig
{
    @Override
	public BlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_, NoFeatureConfig config)
	{
         int j = p_202355_1_.nextInt(3);
         switch(j)
         {
         case 0:
            return BOPBlocks.orange_cosmos.defaultBlockState();
         case 1:
            return Blocks.POPPY.defaultBlockState();
         case 2:
         default:
            return Blocks.DANDELION.defaultBlockState();
         }
	}
}