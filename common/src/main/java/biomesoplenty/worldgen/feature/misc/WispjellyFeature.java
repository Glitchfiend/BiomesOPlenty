/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.init.ModTags;
import biomesoplenty.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.FeaturePlaceContext;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.NoneFeatureConfiguration;

public class WispjellyFeature extends Feature<NoneFeatureConfiguration>
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).is(ModTags.Blocks.TIDEPOOL_REPLACEABLE);
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).getBlock() instanceof BushBlock || world.getBlockState(pos).is(ModTags.Blocks.DEAD_CORALS);

    public WispjellyFeature(Codec<NoneFeatureConfiguration> deserializer)
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

        int height = 8 + rand.nextInt(56);
        if (!this.checkSpace(world, startPos.above(), height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above(height);

        if (rand.nextInt(4) == 0)
        {
            createLargeJelly(world, pos);
        }
        else if (rand.nextInt(2) == 0)
        {
            createSmallJelly(world, pos);
        }
        else
        {
            createTinyJelly(world, pos);
        }

        return true;
    }

    public void createTinyJelly(WorldGenLevel world, BlockPos pos)
    {
        this.setBlock(world, pos, BOPBlocks.WISPJELLY.defaultBlockState());
        for (Direction direction : Direction.values())
        {
            this.setBlock(world, pos.relative(direction), BOPBlocks.WISPJELLY.defaultBlockState());
        }
    }

    public void createSmallJelly(WorldGenLevel world, BlockPos pos)
    {
        for (int x = -1; x <= 1; x++)
        {
            for (int y = 0; y <= 2; y++)
            {
                for (int z = -1; z <= 1; z++)
                {
                    this.setBlock(world, pos.offset(x, y, z), BOPBlocks.WISPJELLY.defaultBlockState());
                }
            }
        }

        for (int x = -2; x <= 2; x++)
        {
            for (int z = -2; z <= 2; z++)
            {
                if (!((x == -2 || x == 2) && (z == -2 || z == 2)))
                {
                    this.setBlock(world, pos.offset(x, 0, z), BOPBlocks.WISPJELLY.defaultBlockState());
                }
            }
        }

        int length = 2;
        for (int i = 0; i < length; i++)
        {
            this.setBlock(world, pos.offset(1, -(i+1), 1), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(1, -(i+1), -1), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(-1, -(i+1), 1), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(-1, -(i+1), -1), BOPBlocks.WISPJELLY.defaultBlockState());

            this.setBlock(world, pos.offset(1, -(i+2), 1), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(-1, -(i+2), -1), BOPBlocks.WISPJELLY.defaultBlockState());
        }
    }

    public void createLargeJelly(WorldGenLevel world, BlockPos pos)
    {
        for (int x = -2; x <= 2; x++)
        {
            for (int y = 0; y <= 3; y++)
            {
                for (int z = -2; z <= 2; z++)
                {
                    if (!((x == -2 || x == 2) && (z == -2 || z == 2)))
                    {
                        if (y == 3)
                        {
                            if (x != -2 && x != 2 && z != -2 && z != 2)
                            {
                                this.setBlock(world, pos.offset(x, y, z), BOPBlocks.WISPJELLY.defaultBlockState());
                            }
                        }
                        else
                        {
                            this.setBlock(world, pos.offset(x, y, z), BOPBlocks.WISPJELLY.defaultBlockState());
                        }
                    }
                }
            }
        }

        for (int x = -3; x <= 3; x++)
        {
            for (int z = -3; z <= 3; z++)
            {
                if (Mth.abs(x) + Mth.abs(z) < 5)
                {
                    this.setBlock(world, pos.offset(x, 0, z), BOPBlocks.WISPJELLY.defaultBlockState());
                }
            }
        }

        int length = 3;
        for (int i = 0; i < length; i++)
        {
            this.setBlock(world, pos.offset(-1, -(i+1), -2), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(1, -(i+1), -2), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(-1, -(i+1), 2), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(1, -(i+1), 2), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(-2, -(i+1), 0), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(2, -(i+1), 0), BOPBlocks.WISPJELLY.defaultBlockState());

            this.setBlock(world, pos.offset(-2, -(i+2), 0), BOPBlocks.WISPJELLY.defaultBlockState());
            this.setBlock(world, pos.offset(2, -(i+2), 0), BOPBlocks.WISPJELLY.defaultBlockState());
        }
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

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, int height)
    {
        for (int y = -3; y <= 3; y++)
        {
            for (int x = -3; x <= 3; x++)
            {
                for (int z = -3; z <= 3; z++)
                {
                    BlockPos pos1 = pos.offset(x, y + height, z);
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }
}
