package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.FluidTags;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;

import java.util.Random;

public class RandomPatchAboveGroundFeature extends Feature<RandomPatchConfiguration> {
	public RandomPatchAboveGroundFeature(Codec<RandomPatchConfiguration> p_i231979_1_) {
		super(p_i231979_1_);
	}

	@Override
	public boolean place(FeaturePlaceContext<RandomPatchConfiguration> featurePlaceContext)
	{
		WorldGenLevel level = featurePlaceContext.level();
		ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
		Random rand = featurePlaceContext.random();
		BlockPos pos = featurePlaceContext.origin();
		RandomPatchConfiguration config = featurePlaceContext.config();
		BlockState blockstate = config.stateProvider.getState(rand, pos);
		BlockPos blockpos;
		if (config.project) {
			blockpos = level.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, pos);
		} else {
			blockpos = pos;
		}

		if (blockpos.getY() < 60)
		{
			return false;
		}

		int i = 0;
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for(int j = 0; j < config.tries; ++j) {
			blockpos$mutable.setWithOffset(blockpos, rand.nextInt(config.xspread + 1) - rand.nextInt(config.xspread + 1), rand.nextInt(config.yspread + 1) - rand.nextInt(config.yspread + 1), rand.nextInt(config.zspread + 1) - rand.nextInt(config.zspread + 1));
			BlockPos blockpos1 = blockpos$mutable.below();
			BlockState blockstate1 = level.getBlockState(blockpos1);
			if ((level.isEmptyBlock(blockpos$mutable) || config.canReplace && level.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.canSurvive(level, blockpos$mutable) && (config.whitelist.isEmpty() || config.whitelist.contains(blockstate1.getBlock())) && !config.blacklist.contains(blockstate1) && (!config.needWater || level.getFluidState(blockpos1.west()).is(FluidTags.WATER) || level.getFluidState(blockpos1.east()).is(FluidTags.WATER) || level.getFluidState(blockpos1.north()).is(FluidTags.WATER) || level.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
				config.blockPlacer.place(level, blockpos$mutable, blockstate, rand);
				++i;
			}
		}

		return i > 0;
	}
}