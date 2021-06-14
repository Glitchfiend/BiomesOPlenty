package biomesoplenty.common.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CarvedPumpkinBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.tags.FluidTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ISeedReader;
import net.minecraft.world.gen.ChunkGenerator;
import net.minecraft.world.gen.Heightmap;
import net.minecraft.world.gen.feature.BlockClusterFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;

public class RandomPatchAboveGroundFeature extends Feature<BlockClusterFeatureConfig> {
	public RandomPatchAboveGroundFeature(Codec<BlockClusterFeatureConfig> p_i231979_1_) {
		super(p_i231979_1_);
	}

	public boolean place(ISeedReader p_241855_1_, ChunkGenerator p_241855_2_, Random p_241855_3_, BlockPos p_241855_4_, BlockClusterFeatureConfig p_241855_5_) {
		BlockState blockstate = p_241855_5_.stateProvider.getState(p_241855_3_, p_241855_4_);
		BlockPos blockpos;
		if (p_241855_5_.project) {
			blockpos = p_241855_1_.getHeightmapPos(Heightmap.Type.WORLD_SURFACE_WG, p_241855_4_);
		} else {
			blockpos = p_241855_4_;
		}

		if (blockpos.getY() < 60)
		{
			return false;
		}

		int i = 0;
		BlockPos.Mutable blockpos$mutable = new BlockPos.Mutable();

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