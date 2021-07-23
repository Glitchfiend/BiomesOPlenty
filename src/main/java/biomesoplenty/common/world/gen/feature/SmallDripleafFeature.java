package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class SmallDripleafFeature extends Feature<NoneFeatureConfiguration>
{
	public SmallDripleafFeature(Codec<NoneFeatureConfiguration> deserializer)
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
			if (world.getBlockState(blockpos).getMaterial() == Material.WATER && world.getBlockState(blockpos.below()).is(BlockTags.DIRT))
			{
				DoublePlantBlock.placeAt(world, Blocks.SMALL_DRIPLEAF.defaultBlockState().setValue(SmallDripleafBlock.FACING, Direction.Plane.HORIZONTAL.getRandomDirection(rand)), blockpos, 2);

				++i;
			}
		}

		return i > 0;
	}
}