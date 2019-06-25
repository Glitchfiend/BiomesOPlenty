/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.datafixers.Dynamic;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.FlowersFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.function.Function;

public class MysticGroveFlowersFeature extends FlowersFeature
{
	private static final Block[] FLOWERS = new Block[]{BOPBlocks.blue_hydrangea, BOPBlocks.glowflower, BOPBlocks.pink_daffodil, Blocks.OXEYE_DAISY, Blocks.LILY_OF_THE_VALLEY, Blocks.ALLIUM};

	public MysticGroveFlowersFeature(Function<Dynamic<?>, ? extends NoFeatureConfig> deserializer)
	{
		super(deserializer);
	}

	@Override
	public BlockState getRandomFlower(Random p_202355_1_, BlockPos p_202355_2_)
	{
		double d0 = MathHelper.clamp((1.0D + Biome.INFO_NOISE.getValue((double)p_202355_2_.getX() / 48.0D, (double)p_202355_2_.getZ() / 48.0D)) / 2.0D, 0.0D, 0.9999D);
		Block block = FLOWERS[(int)(d0 * (double)FLOWERS.length)];
		return block.getDefaultState();
	}
}