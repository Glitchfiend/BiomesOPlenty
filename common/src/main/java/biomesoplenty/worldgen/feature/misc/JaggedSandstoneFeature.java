/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.Feature;
import com.mojang.serialization.MapCodec;
import net.minecraft.world.level.chunk.ChunkGenerator;

public class JaggedSandstoneFeature implements Feature
{
    public static final MapCodec<JaggedSandstoneFeature> CODEC = MapCodec.unit(JaggedSandstoneFeature::new);

    @Override
    public MapCodec<JaggedSandstoneFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel worldgenlevel, ChunkGenerator chunkGenerator, RandomSource randomsource, BlockPos blockpos)
    {
        if (isInvalidPlacementLocation(worldgenlevel, blockpos))
        {
            return false;
        }
        else
        {
            int i = 5;
            int j = 3;
            BlockPos.MutableBlockPos blockpos$mutableblockpos = new BlockPos.MutableBlockPos();

            for (int l = 0; l <= i; ++l)
            {
                for (int k = 0; k <= i; ++k)
                {
                    blockpos$mutableblockpos.set(blockpos).move(l, Mth.nextInt(randomsource, -j, j), k);
                    if (findFirstAirBlockAboveGround(worldgenlevel, blockpos$mutableblockpos) && !isInvalidPlacementLocation(worldgenlevel, blockpos$mutableblockpos))
                    {
                        placeWeepingVinesColumn(worldgenlevel, randomsource, blockpos$mutableblockpos, 2 + randomsource.nextInt(6));
                    }
                }
            }

            return true;
        }
    }

    private static boolean findFirstAirBlockAboveGround(LevelAccessor p_67294_, BlockPos.MutableBlockPos p_67295_) {
        do {
            p_67295_.move(0, -1, 0);
            if (p_67294_.isOutsideBuildHeight(p_67295_)) {
                return false;
            }
        } while (p_67294_.getBlockState(p_67295_).isAir());

        p_67295_.move(0, 1, 0);
        return true;
    }

    public static void placeWeepingVinesColumn(LevelAccessor p_225301_, RandomSource p_225302_, BlockPos.MutableBlockPos p_225303_, int p_225304_) {
        for (int i = 1; i <= p_225304_; ++i) {
            if (p_225301_.isEmptyBlock(p_225303_)) {
                if (i == p_225304_ || !p_225301_.isEmptyBlock(p_225303_.above())) {
                    p_225301_.setBlock(p_225303_, BOPBlocks.WHITE_SANDSTONE.defaultBlockState(), 2);
                    break;
                }

                p_225301_.setBlock(p_225303_, BOPBlocks.WHITE_SANDSTONE.defaultBlockState(), 2);
            }

            p_225303_.move(Direction.UP);
        }

    }

    private static boolean isInvalidPlacementLocation(LevelAccessor p_67297_, BlockPos p_67298_) {
        if (!p_67297_.isEmptyBlock(p_67298_)) {
            return true;
        }
        else
        {
            BlockState blockstate = p_67297_.getBlockState(p_67298_.below());
            return !blockstate.is(BOPBlocks.WHITE_SAND);
        }
    }
}