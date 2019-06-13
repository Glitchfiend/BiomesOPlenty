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
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.AbstractFlowersFeature;

public class LushGrasslandFlowersFeature extends AbstractFlowersFeature
{
	@Override
	public IBlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_)
	{
         int j = p_202355_1_.nextInt(2);
         switch(j)
         {
         case 0:
            return BOPBlocks.orange_cosmos.getDefaultState();
         case 1:
         default:
            return Blocks.OXEYE_DAISY.getDefaultState();
         }
	}
}