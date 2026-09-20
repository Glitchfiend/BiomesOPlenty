/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.worldgen.feature.misc;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.util.SimpleBlockPredicate;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.VegetationBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.chunk.ChunkGenerator;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import com.mojang.serialization.MapCodec;

public class MonolithFeature implements Feature
{
    protected SimpleBlockPredicate placeOn = (world, pos) -> world.getBlockState(pos).getBlock() == BOPBlocks.UNMAPPED_END_STONE;
    protected SimpleBlockPredicate replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).is(BlockTags.REPLACEABLE_BY_TREES) || world.getBlockState(pos).getBlock() instanceof VegetationBlock || world.getBlockState(pos).getBlock() == BOPBlocks.NULL_END_STONE;

    public static final MapCodec<MonolithFeature> CODEC = MapCodec.unit(MonolithFeature::new);

    @Override
    public MapCodec<MonolithFeature> codec()
    {
        return CODEC;
    }


    @Override
    public boolean place(WorldGenLevel world, ChunkGenerator chunkGenerator, RandomSource rand, BlockPos startPos)
    {
        while (startPos.getY() >= world.getMinY()+1 && this.replace.matches(world, startPos)) {startPos = startPos.below();}

        if (!this.placeOn.matches(world, startPos.offset(0, 0, 0)))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        int sizeX = 1 + rand.nextInt(3);
        int sizeZ = 1 + rand.nextInt(3);
        int height = 5 + rand.nextInt(7);

        if (!this.checkSpace(world, startPos.above(), sizeX, sizeZ, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        BlockPos pos = startPos.above();

        for (int x = 0; x <= sizeX; x++)
        {
            for (int y = -6; y <= height; y++)
            {
                for (int z = 0; z <= sizeZ; z++)
                {
                    if (y == height - 1)
                    {
                        if ((x == 0 || x == sizeX) && (z == 0 || z == sizeZ))
                        {
                            this.setBlock(world, pos.offset(x,y,z), Blocks.OBSIDIAN.defaultBlockState());
                        }
                    }
                    else
                    {
                        this.setBlock(world, pos.offset(x,y,z), Blocks.OBSIDIAN.defaultBlockState());
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
            Feature.super.setBlock(world, pos, state);
            return true;
        }
        return false;
    }

    public boolean checkSpace(WorldGenLevel world, BlockPos pos, int sizeX, int sizeZ, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            for (int x = 0; x <= sizeX; x++)
            {
                for (int z = 0; z <= sizeZ; z++)
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
}
