/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.CarvedPumpkinBlock;
import net.minecraft.world.level.block.LeavesBlock;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import com.mojang.serialization.MapCodec;

public class PumpkinPatchFeature implements Feature
{
    public static final MapCodec<PumpkinPatchFeature> CODEC = MapCodec.unit(PumpkinPatchFeature::new);

    @Override
    public MapCodec<PumpkinPatchFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos pos)
    {
        int i = 0;

        for(int j = 0; j < 64; ++j)
        {
            BlockPos blockpos = pos.offset(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (TreeFeature.isAirOrLeaves(world, blockpos) && world.getBlockState(blockpos.below()).getBlock() == Blocks.GRASS_BLOCK)
            {

                if (rand.nextInt(3) == 0)
                {
                    int num = rand.nextInt(50);

                    if (num > 10)
                    {
                        world.setBlock(blockpos, Blocks.PUMPKIN.defaultBlockState(), 2);
                    }
                    else if (num > 1)
                    {
                        world.setBlock(blockpos, Blocks.CARVED_PUMPKIN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.from3DDataValue(2 + rand.nextInt(4))), 2);
                    }
                    else
                    {
                        world.setBlock(blockpos, Blocks.JACK_O_LANTERN.defaultBlockState().setValue(CarvedPumpkinBlock.FACING, Direction.from3DDataValue(2 + rand.nextInt(4))), 2);
                    }
                }
                else
                {
                    world.setBlock(blockpos, Blocks.OAK_LEAVES.defaultBlockState().setValue(LeavesBlock.PERSISTENT, true), 2);
                }

                ++i;
            }
        }

        return i > 0;
    }
}
