/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import com.mojang.datafixers.Dynamic;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.DefaultFlowersFeature;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class ConiferousForestFlowersFeature extends DefaultFlowersFeatureNoConfig
{
    @Override
	public BlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_, NoFeatureConfig config)
	{
         int j = p_202355_1_.nextInt(4);
         switch(j)
         {
         case 0:
             return Blocks.CORNFLOWER.defaultBlockState();
         case 1:
            return Blocks.OXEYE_DAISY.defaultBlockState();
         case 2:
            return Blocks.POPPY.defaultBlockState();
         case 3:
         default:
            return Blocks.DANDELION.defaultBlockState();
         }
	}
}