/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.util.SimpleBlockPredicate;
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
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class RootedStumpFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos);

    public RootedStumpFeature(Codec<NoneFeatureConfiguration> deserializer)
    {
        super(deserializer);
    }

    @Override
    public boolean place(FeaturePlaceContext<NoneFeatureConfiguration> featurePlaceContext)
    {
        WorldGenLevel world = featurePlaceContext.level();
        ChunkGenerator chunkGenerator = featurePlaceContext.chunkGenerator();
        RandomSource rand = featurePlaceContext.random();
        BlockPos startPos = featurePlaceContext.origin();
        NoneFeatureConfiguration config = featurePlaceContext.config();
        while (startPos.getY() >= world.getMinBuildHeight()+1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        if (!this.checkSpace(world, startPos.above()))
        {
            // Abandon if there isn't enough room
            return false;
        }

        if (world.getBlockState(startPos).getBlock() == Blocks.GRASS_BLOCK)
        {
            world.setBlock(startPos, Blocks.ROOTED_DIRT.defaultBlockState(), 2);
        }
        if (world.getBlockState(startPos.east()).getBlock() == Blocks.GRASS_BLOCK)
        {
            world.setBlock(startPos.east(), Blocks.ROOTED_DIRT.defaultBlockState(), 2);
        }
        if (world.getBlockState(startPos.west()).getBlock() == Blocks.GRASS_BLOCK)
        {
            world.setBlock(startPos.west(), Blocks.ROOTED_DIRT.defaultBlockState(), 2);
        }
        if (world.getBlockState(startPos.north()).getBlock() == Blocks.GRASS_BLOCK)
        {
            world.setBlock(startPos.north(), Blocks.ROOTED_DIRT.defaultBlockState(), 2);
        }
        if (world.getBlockState(startPos.south()).getBlock() == Blocks.GRASS_BLOCK)
        {
            world.setBlock(startPos.south(), Blocks.ROOTED_DIRT.defaultBlockState(), 2);
        }

        BlockPos pos = startPos.above();
        int height = 1;
        if (rand.nextInt(4) == 0)
        {
            height = 2;

            if (rand.nextInt(4) == 0)
            {
            height = 3;
            }
        }

        for (int i = 0; i < height; i++)
        {
            this.setBlock(world, pos.above(i), Blocks.OAK_LOG.defaultBlockState());
        }

        int j = rand.nextInt(3) + 2;

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
                        BlockState blockstate = world.getBlockState(blockpos);

                        if (blockstate.getBlock() == Blocks.GRASS_BLOCK)
                        {
                            if (rand.nextInt(2) > 0)
                            {
                                BlockState block = Blocks.ROOTED_DIRT.defaultBlockState();
                                if (rand.nextInt(2) == 0)
                                {
                                    block = Blocks.COARSE_DIRT.defaultBlockState();
                                }

                                world.setBlock(blockpos, block, 2);
                                if (rand.nextInt(2) == 0 && this.isAir(world, blockpos.above())) {
                                    world.setBlock(blockpos.above(), Blocks.AIR.defaultBlockState(), 2);
                                }
                                if (rand.nextInt(8) == 0 && this.isAir(world, blockpos.above())) {
                                    world.setBlock(blockpos.above(), Blocks.DEAD_BUSH.defaultBlockState(), 2);
                                }
                            }

                            break;
                        }
                    }
                }
            }
        }

        return true;
    }

    public boolean setBlock(WorldGenLevel world, BlockPos pos, BlockState state)
    {
        if (this.replace.matches(world, pos))
        {
            super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos)
    {
        for (int y = 0; y <= 6; y++)
        {
            for (int x = 0; x <= 5; x++)
            {
                for (int z = 0; z <= 5; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    public static boolean isAir(LevelSimulatedReader p_65811_, BlockPos p_65812_) {
        return p_65811_.isStateAtPosition(p_65812_, BlockBehaviour.BlockStateBase::isAir);
    }
}
