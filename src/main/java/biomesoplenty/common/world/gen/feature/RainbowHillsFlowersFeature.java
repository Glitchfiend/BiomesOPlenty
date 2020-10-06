/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RainbowHillsFlowersFeature extends DefaultFlowersFeatureNoConfig
{
	private static final Block[] FLOWERS = new Block[]{BOPBlocks.pink_daffodil, BOPBlocks.orange_cosmos, Blocks.ALLIUM, Blocks.AZURE_BLUET, Blocks.ORANGE_TULIP, Blocks.PINK_TULIP, Blocks.RED_TULIP, Blocks.WHITE_TULIP, Blocks.CORNFLOWER, Blocks.LILY_OF_THE_VALLEY, Blocks.BLUE_ORCHID, Blocks.OXEYE_DAISY, Blocks.DANDELION, Blocks.POPPY};

	@Override
	public BlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_, NoFeatureConfig config)
	{
		double d0 = MathHelper.clamp((1.0D + Biome.BIOME_INFO_NOISE.getValue((double)p_202355_2_.getX() / 48.0D, (double)p_202355_2_.getZ() / 48.0D, false)) / 2.0D, 0.0D, 0.9999D);
		Block block = FLOWERS[(int)(d0 * (double)FLOWERS.length)];
		return block.defaultBlockState();
	}
}