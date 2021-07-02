package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.Heightmap;
import net.minecraft.world.level.levelgen.feature.configurations.RandomPatchConfiguration;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RandomPatchAboveGroundFeature extends Feature<RandomPatchConfiguration> {
	public RandomPatchAboveGroundFeature(Codec<RandomPatchConfiguration> p_i231979_1_) {
		super(p_i231979_1_);
	}

	public boolean place(WorldGenLevel p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, RandomPatchConfiguration p_241855_5_) {
		BlockState blockstate = p_241855_5_.stateProvider.getState(p_241855_3_, p_241855_4_);
		BlockPos blockpos;
		if (p_241855_5_.project) {
			blockpos = p_241855_1_.getHeightmapPos(Heightmap.Types.WORLD_SURFACE_WG, p_241855_4_);
		} else {
			blockpos = p_241855_4_;
		}

		if (blockpos.getY() < 60)
		{
			return false;
		}

		int i = 0;
		BlockPos.MutableBlockPos blockpos$mutable = new BlockPos.MutableBlockPos();

		for(int j = 0; j < p_241855_5_.tries; ++j) {
			blockpos$mutable.setWithOffset(blockpos, p_241855_3_.nextInt(p_241855_5_.xspread + 1) - p_241855_3_.nextInt(p_241855_5_.xspread + 1), p_241855_3_.nextInt(p_241855_5_.yspread + 1) - p_241855_3_.nextInt(p_241855_5_.yspread + 1), p_241855_3_.nextInt(p_241855_5_.zspread + 1) - p_241855_3_.nextInt(p_241855_5_.zspread + 1));
			BlockPos blockpos1 = blockpos$mutable.below();
			BlockState blockstate1 = p_241855_1_.getBlockState(blockpos1);
			if ((p_241855_1_.isEmptyBlock(blockpos$mutable) || p_241855_5_.canReplace && p_241855_1_.getBlockState(blockpos$mutable).getMaterial().isReplaceable()) && blockstate.canSurvive(p_241855_1_, blockpos$mutable) && (p_241855_5_.whitelist.isEmpty() || p_241855_5_.whitelist.contains(blockstate1.getBlock())) && !p_241855_5_.blacklist.contains(blockstate1) && (!p_241855_5_.needWater || p_241855_1_.getFluidState(blockpos1.west()).is(FluidTags.WATER) || p_241855_1_.getFluidState(blockpos1.east()).is(FluidTags.WATER) || p_241855_1_.getFluidState(blockpos1.north()).is(FluidTags.WATER) || p_241855_1_.getFluidState(blockpos1.south()).is(FluidTags.WATER))) {
				p_241855_5_.blockPlacer.place(p_241855_1_, blockpos$mutable, blockstate, p_241855_3_);
				++i;
			}
		}

		return i > 0;
	}
}