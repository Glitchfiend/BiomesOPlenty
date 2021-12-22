/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

import java.util.Random;

public class BlackSandSplatterFeature extends Feature<NoneFeatureConfiguration>
{
    public BlackSandSplatterFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel worldIn = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        Random rand = featurePlaceContext.random();
        BlockPos pos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        int i = 0;
        int j = rand.nextInt(2) + 1;

        for (int k = pos.getX() - j; k <= pos.getX() + j; ++k)
        {
            for (int l = pos.getZ() - j; l <= pos.getZ() + j; ++l)
            {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= j * j)
                {
                    for (int k1 = pos.getY() - 2; k1 <= pos.getY() + 2; ++k1)
                    {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = worldIn.getBlockState(blockpos);
                        BlockState blockstate1 = worldIn.getBlockState(blockpos.above());

                        if (blockstate.getBlock() == Blocks.GRASS_BLOCK && this.isAir(worldIn, blockpos.above()))
                        {
                            worldIn.setBlock(blockpos, BOPBlocks.BLACK_SAND.defaultBlockState(), 2);
                            if (rand.nextInt(4) == 0)
                            {
                                worldIn.setBlock(blockpos.above(), Blocks.DEAD_BUSH.defaultBlockState(), 2);
                            }

                            ++i;
                            break;
                        }
                    }
                }
            }
        }

        return i > 0;
    }
}
