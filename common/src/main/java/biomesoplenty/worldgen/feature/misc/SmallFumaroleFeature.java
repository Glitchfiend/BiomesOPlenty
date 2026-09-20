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
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import com.mojang.serialization.MapCodec;

public class SmallFumaroleFeature implements Feature
{
    public static final MapCodec<SmallFumaroleFeature> CODEC = MapCodec.unit(SmallFumaroleFeature::new);

    @Override
    public MapCodec<SmallFumaroleFeature> codec()
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
            if ((TreeFeature.isAirOrLeaves(world, blockpos) || world.getBlockState(pos).getBlock() == BOPBlocks.ORPIMENT_BUD || world.getBlockState(pos).getBlock() == BOPBlocks.ORPIMENT_CLUSTER) && world.getBlockState(blockpos.below()).getBlock() == BOPBlocks.ORPIMENT)
            {
                if (rand.nextInt(5) == 0)
                {
                    if (rand.nextInt(2) == 0)
                    {
                        world.setBlock(blockpos, BOPBlocks.ORPIMENT.defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), BOPBlocks.ORPIMENT_FUMAROLE.defaultBlockState(), 2);
                    }
                    else
                    {
                        world.setBlock(blockpos, BOPBlocks.ORPIMENT_FUMAROLE.defaultBlockState(), 2);
                        world.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);
                    }
                }

                ++i;
            }
        }

        return i > 0;
    }
}