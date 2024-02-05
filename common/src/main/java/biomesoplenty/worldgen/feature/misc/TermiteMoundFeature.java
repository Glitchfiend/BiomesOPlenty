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
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class TermiteMoundFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock;

    public TermiteMoundFeature(Codec<NoneFeatureConfiguration> deserializer)
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

        BlockPos pos = startPos.above();
        int height = 2 + rand.nextInt(3);
        for (int y = -1; y <= height + 1; y++)
        {
            this.setBlock(world, pos.above(y), Blocks.PACKED_MUD.defaultBlockState());
        }

        for (int x = -1; x <= 1; x++)
        {
            for (int z = -1; z <= 1; z++)
            {
                if (!((x == -1 || x == 1) && (z == -1 || z == 1)))
                {
                    int i = (height / 2) + (rand.nextInt(2) - rand.nextInt(2));
                    for (int y = -1; y <= i; y++)
                    {
                        this.setBlock(world, pos.offset(x, y, z), Blocks.PACKED_MUD.defaultBlockState());
                    }
                }
                else
                {
                    if (rand.nextInt(3) == 0)
                    {
                        for (int y = -1; y <= 0; y++)
                        {
                            this.setBlock(world, pos.offset(x, y, z), Blocks.PACKED_MUD.defaultBlockState());
                        }
                    }
                }
            }
        }

        int j = 2;

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
                            world.setBlock(blockpos, Blocks.PACKED_MUD.defaultBlockState(), 2);
                            break;
                        }
                    }
                }
            }
        }

        int m = j + 2;

        for (int k = pos.getX() - m; k <= pos.getX() + m; ++k)
        {
            for (int l = pos.getZ() - m; l <= pos.getZ() + m; ++l)
            {
                int i1 = k - pos.getX();
                int j1 = l - pos.getZ();
                if (i1 * i1 + j1 * j1 <= m * m)
                {
                    for (int k1 = pos.getY() - 2; k1 <= pos.getY() + 2; ++k1)
                    {
                        BlockPos blockpos = new BlockPos(k, k1, l);
                        BlockState blockstate = world.getBlockState(blockpos);

                        if (blockstate.getBlock() == Blocks.GRASS_BLOCK)
                        {
                            if (rand.nextInt(2) == 0)
                            {
                                world.setBlock(blockpos, Blocks.PACKED_MUD.defaultBlockState(), 2);
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
            for (int x = -1; x <= 1; x++)
            {
                for (int z = -1; z <= 1; z++)
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
