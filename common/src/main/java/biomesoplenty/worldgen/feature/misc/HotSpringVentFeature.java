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
import com.mojang.serialization.MapCodec;

public class HotSpringVentFeature implements Feature
{
    public static final MapCodec<HotSpringVentFeature> CODEC = MapCodec.unit(HotSpringVentFeature::new);

    @Override
    public MapCodec<HotSpringVentFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
    {
        int i = 0;

        for(int j = 0; j < 96; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.getBlockState(blockpos).getBlock() == Blocks.WATER && world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.SPHALERITE)
            {
                world.setBlock(blockpos.below(), BOPBlocks.POTENT_SPHALERITE.defaultBlockState(), 2);

                ++i;
            }
        }

        return i > 0;
    }
}
