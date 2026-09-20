/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import com.mojang.serialization.MapCodec;

public class ScatteredRocksFeature implements Feature
{
    public static final MapCodec<ScatteredRocksFeature> CODEC = MapCodec.unit(ScatteredRocksFeature::new);

    @Override
    public MapCodec<ScatteredRocksFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
    {
        int i = 0;

        for(int j = 0; j < 32; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (TreeFeature.isAirOrLeaves(world, blockpos) && world.getBlockState(blockpos.below()).getBlock() == Blocks.GRASS_BLOCK)
            {

                if (rand.nextInt(3) == 0)
                {
                    world.setBlock(blockpos, Blocks.MOSSY_COBBLESTONE.defaultBlockState(), 2);
                }
                else
                {
                    world.setBlock(blockpos, Blocks.COBBLESTONE.defaultBlockState(), 2);
                }

                ++i;
            }
        }

        return i > 0;
    }
}
