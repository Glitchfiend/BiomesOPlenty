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
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;

public class ObsidianSplatterFeature implements Feature
{
    public static final MapCodec<ObsidianSplatterFeature> CODEC = MapCodec.unit(ObsidianSplatterFeature::new);

    @Override
    public MapCodec<ObsidianSplatterFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel worldIn, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
    {
        int i = 0;
        int j = rand.nextInt(8 - 2) + 2;

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

                        if (blockstate.getBlock() == Blocks.BLACKSTONE)
                        {
                            if (rand.nextInt(2) == 0)
                            {
                                worldIn.setBlock(blockpos, Blocks.OBSIDIAN.defaultBlockState(), 2);
                            }
                            if (rand.nextInt(15) == 0)
                            {
                                worldIn.setBlock(blockpos, Blocks.CRYING_OBSIDIAN.defaultBlockState(), 2);
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
