/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.AmethystClusterBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class SmallCrystalFeature extends Feature<NoneFeatureConfiguration>
{
    public SmallCrystalFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        int i = 0;

        for(int j = 0; j < 128; ++j)
        {
            Direction direction = Direction.getRandom(rand);

            BlockState cluster_state;
            switch (rand.nextInt(6))
            {
                case 3:
                    cluster_state = BOPBlocks.ROSE_QUARTZ_CLUSTER.get().defaultBlockState();
                    break;

                case 2:
                default:
                    cluster_state = BOPBlocks.LARGE_ROSE_QUARTZ_BUD.get().defaultBlockState();
                    break;

                case 1:
                    cluster_state = BOPBlocks.MEDIUM_ROSE_QUARTZ_BUD.get().defaultBlockState();
                    break;

                case 0:
                    cluster_state = BOPBlocks.SMALL_ROSE_QUARTZ_BUD.get().defaultBlockState();
                    break;
            }

            BlockState state = cluster_state.setValue(AmethystClusterBlock.FACING, direction);
            BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(4) - rand.nextInt(4));
            Block ground = world.getBlockState(blockpos.relative(direction.getOpposite())).getBlock();

            if (world.isEmptyBlock(blockpos) && state.canSurvive(world, blockpos) && ground != Blocks.BEDROCK)
            {
                world.setBlock(blockpos, state, 2);

                ++i;
            }
        }

        return i > 10;
    }
}
