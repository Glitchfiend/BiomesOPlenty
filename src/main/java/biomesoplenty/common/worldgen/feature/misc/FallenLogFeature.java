/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class FallenLogFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == Blocks.GRASS_BLOCK || world.getBlockState(pos).getBlock() == Blocks.DIRT;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock;

    public FallenLogFeature(Codec<NoneFeatureConfiguration> deserializer)
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
        while (startPos.getY() > 1 && this.replace.matches(world, startPos)) {
            startPos = startPos.below();
        }

        int length = 2 + rand.nextInt(4);
        Direction direction = Direction.Plane.HORIZONTAL.getRandomDirection(rand);

        int overhang = 0;
        if (length > 2)
        {
            overhang = 1;
        }
        if (length > 4)
        {
            overhang = 2;
        }

        for (int i = 0; i <= length-overhang; i++)
        {
            if (!this.placeOn.matches(world, startPos.relative(direction, i)))
            {
                // Abandon if we can't place the tree on this block
                return false;
            }
        }

        if (!this.checkSpace(world, startPos.above(), direction, length))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        for (int i = 0; i <= length; i++)
        {
            this.setBlock(world, pos.relative(direction, i), Blocks.OAK_LOG.defaultBlockState().setValue(RotatedPillarBlock.AXIS, direction.getAxis()));
            if (rand.nextInt(4) == 0)
            {
                this.setBlock(world, pos.above().relative(direction, i), BOPBlocks.TOADSTOOL.get().defaultBlockState());
            }
            else
            {
                this.setBlock(world, pos.above().relative(direction, i), Blocks.MOSS_CARPET.defaultBlockState());
            }

            if (this.replace.matches(world, pos.below().relative(direction, i)))
            {
                this.setBlock(world, pos.below().relative(direction, i), Blocks.HANGING_ROOTS.defaultBlockState());
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

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, Direction direction, int length)
    {
        for (int y = 0; y <= 2; y++)
        {
            for (int i = 0; i <= length; i++)
            {
                BlockPos pos1 = pos.above(y).relative(direction, i);
                if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                {
                    return false;
                }
            }
        }
        return true;
    }
}
