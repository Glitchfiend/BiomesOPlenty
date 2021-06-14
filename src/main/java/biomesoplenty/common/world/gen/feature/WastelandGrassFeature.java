/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;
import net.minecraft.world.gen.feature.structure.StructureManager;

import java.util.Random;

public class WastelandGrassFeature extends Feature<NoFeatureConfig>
{
	public WastelandGrassFeature(Codec<NoFeatureConfig> deserializer)
	{
		super(deserializer);
	}

	public BlockState chooseGrassState(Random rand)
	{
		return rand.nextInt(3) == 0 ? BOPBlocks.desert_grass.defaultBlockState() : BOPBlocks.dead_grass.defaultBlockState();
	}

	@Override
	public boolean place(ISeedReader world, ChunkGenerator chunkGenerator, Random rand, BlockPos pos, NoFeatureConfig config)
	{
		BlockState BlockState = this.chooseGrassState(rand);

		for (BlockState BlockState1 = world.getBlockState(pos); (BlockState1.isAir(world, pos) || BlockState1.is(BlockTags.LEAVES)) && pos.getY() > 0; BlockState1 = world.getBlockState(pos))
		{
			pos = pos.below();
		}

		int i = 0;

		for (int j = 0; j < 128; ++j)
		{
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (world.isEmptyBlock(blockpos) && BlockState.canSurvive(world, blockpos))
			{
				world.setBlock(blockpos, BlockState, 2);
				++i;
			}
		}

		return i > 0;
	}
}