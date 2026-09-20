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
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;

public class SparseDuneGrassFeature implements Feature
{
    public static final MapCodec<SparseDuneGrassFeature> CODEC = MapCodec.unit(SparseDuneGrassFeature::new);

    @Override
    public MapCodec<SparseDuneGrassFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
    {
        int i = 0;

        for(int j = 0; j < 128; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(2) - rand.nextInt(2), rand.nextInt(4) - rand.nextInt(4));
            if (world.getBlockState(blockpos).isAir())
            {
                if (world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.ORANGE_SAND)
                {
                    world.setBlock(blockpos, BOPBlocks.DUNE_GRASS.defaultBlockState(), 2);
                }
                else if (world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.ORANGE_SANDSTONE)
                {
                    if (rand.nextInt(4) == 0)
                    {
                        world.setBlock(blockpos, BOPBlocks.SPROUT.defaultBlockState(), 2);
                    }
                }

                ++i;
            }
        }

        return i > 0;
    }
}
