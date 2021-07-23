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
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class WastelandGrassFeature extends Feature<NoneFeatureConfiguration>
{
	public WastelandGrassFeature(Codec<NoneFeatureConfiguration> deserializer)
	{
		super(deserializer);
	}

	public BlockState chooseGrassState(Random rand)
	{
		return rand.nextInt(3) == 0 ? BOPBlocks.DESERT_GRASS.defaultBlockState() : BOPBlocks.DEAD_GRASS.defaultBlockState();
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
	{
		WorldGenLevel world = featurePlaceContext.level();
		ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
		Random rand = featurePlaceContext.random();
		BlockPos pos = featurePlaceContext.origin();
		NoneFeatureConfiguration config = featurePlaceContext.config();
		BlockState BlockState = this.chooseGrassState(rand);

		for (BlockState BlockState1 = world.getBlockState(pos); (this.isAir(world, pos) || BlockState1.is(BlockTags.LEAVES)) && pos.getY() > 0; BlockState1 = world.getBlockState(pos))
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