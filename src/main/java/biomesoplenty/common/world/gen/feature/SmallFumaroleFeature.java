package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class SmallFumaroleFeature extends Feature<NoneFeatureConfiguration>
{
	public SmallFumaroleFeature(Codec<NoneFeatureConfiguration> deserializer)
	{
		super(deserializer);
	}

	@Override
	public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
	{
		WorldGenLevel world = featurePlaceContext.level();
		ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
		Random rand = featurePlaceContext.random();
		BlockPos pos = featurePlaceContext.origin();
		NoneFeatureConfiguration config = featurePlaceContext.config();
		int i = 0;

		for(int j = 0; j < 64; ++j)
		{
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if ((TreeFeature.isAirOrLeaves(world, blockpos) || world.getBlockState(pos).getBlock() == BOPBlocks.BRIMSTONE_BUD) && world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.BRIMSTONE)
			{
				if (rand.nextInt(5) == 0)
				{
					if (rand.nextInt(3) == 0)
					{
						world.setBlock(blockpos, BOPBlocks.BRIMSTONE.defaultBlockState(), 2);
						world.setBlock(blockpos.above(), BOPBlocks.BRIMSTONE_FUMAROLE.defaultBlockState(), 2);
					}
					else
					{
						world.setBlock(blockpos, BOPBlocks.BRIMSTONE_FUMAROLE.defaultBlockState(), 2);
					}
				}

				++i;
			}
		}

		return i > 0;
	}
}