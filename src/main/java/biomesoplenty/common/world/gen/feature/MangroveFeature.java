package biomesoplenty.common.world.gen.feature;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.DoublePlantBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;
import net.minecraft.world.level.material.Material;

import java.util.Random;

public class MangroveFeature extends Feature<NoneFeatureConfiguration> {

	public MangroveFeature(Codec<NoneFeatureConfiguration> deserializer)
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
		BlockState blockstate = BOPBlocks.MANGROVE_ROOT.defaultBlockState();

		for(int j = 0; j < 64; ++j) {
			BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
			if (world.getBlockState(blockpos).getMaterial() == Material.WATER && world.isEmptyBlock(blockpos.above()) && (!world.dimensionType().hasCeiling() || blockpos.getY() < world.getLevel().getHeight() - 1) && blockstate.canSurvive(world, blockpos)) {
				world.setBlock(blockpos, blockstate, 2);
				((DoublePlantBlock)blockstate.getBlock()).placeAt(world, blockstate, blockpos, 2);

				if (rand.nextInt(5) != 0)
				{
					BlockPos leaves1 = blockpos.above().above();
					BlockPos leaves2 = leaves1.north();
					BlockPos leaves3 = leaves1.south();
					BlockPos leaves4 = leaves1.east();
					BlockPos leaves5 = leaves1.west();
					BlockPos leaves6 = leaves1.above();

					if (TreeFeature.isAirOrLeaves(world, leaves1))
					{
						world.setBlock(leaves1, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
					}

					if (rand.nextInt(2) == 0)
					{
						if (TreeFeature.isAirOrLeaves(world, leaves2))
						{
							world.setBlock(leaves2, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
						}
						if (TreeFeature.isAirOrLeaves(world, leaves3))
						{
							world.setBlock(leaves3, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
						}
						if (TreeFeature.isAirOrLeaves(world, leaves4))
						{
							world.setBlock(leaves4, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
						}
						if (TreeFeature.isAirOrLeaves(world, leaves5))
						{
							world.setBlock(leaves5, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
						}
						if (TreeFeature.isAirOrLeaves(world, leaves6))
						{
							world.setBlock(leaves6, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
						}
					}
				}
				++i;
			}
		}

		return i > 0;
	}
}