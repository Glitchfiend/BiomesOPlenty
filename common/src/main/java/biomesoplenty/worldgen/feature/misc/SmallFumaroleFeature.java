/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

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
        RandomSource rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        int i = 0;

        for(int j = 0; j < 64; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if ((TreeFeature.isAirOrLeaves(world, blockpos) || world.getBlockState(pos).getBlock() == BOPBlocks.BRIMSTONE_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.BRIMSTONE_CLUSTER) && world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.BRIMSTONE)
            {
                if (rand.nextInt(5) == 0)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        world.setBlock(blockpos, BOPBlocks.BRIMSTONE.defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), BOPBlocks.BRIMSTONE_FUMAROLE.defaultBlockState(), 2);
                    }
                    else
                    {
                        world.setBlock(blockpos, BOPBlocks.BRIMSTONE_FUMAROLE.defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);
                    }
                }

                ++i;
            }
        }

        return i > 0;
    }
}