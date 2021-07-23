/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class FernFeature extends Feature<NoneFeatureConfiguration>
{
	public FernFeature(Codec<NoneFeatureConfiguration> deserializer)
	{
		super(deserializer);
	}

	public BlockState chooseGrassState(Random rand)
	{
		return Blocks.FERN.defaultBlockState();
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