/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelSimulatedReader;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;

public class GrassSplatterFeature implements Feature
{
    public static final MapCodec<GrassSplatterFeature> CODEC = MapCodec.unit(GrassSplatterFeature::new);

    @Override
    public MapCodec<GrassSplatterFeature> codec()
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
                        BlockState blockstate1 = worldIn.getBlockState(blockpos.above());

                        if (blockstate.getBlock() == BOPBlocks.BLACK_SAND && this.isAir(worldIn, blockpos.above()))
                        {
                            if (rand.nextInt(2) > 0)
                            {
                                worldIn.setBlock(blockpos, BOPBlocks.MOSSY_BLACK_SAND.defaultBlockState(), 2);

                                if (rand.nextInt(3) == 0)
                                {
                                    worldIn.setBlock(blockpos.above(), Blocks.FERN.defaultBlockState(), 2);
                                }
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

    public static boolean isAir(LevelSimulatedReader level, BlockPos pos)
    {
        return level.isStateAtPosition(pos, BlockBehaviour.BlockStateBase::isAir);
    }
}
