/*******************************************************************************
 * Copyright 2022, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.worldgen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.worldgen.feature.configurations.TaigaTreeConfiguration;
import com.mojang.serialization.Codec;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Mth;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;

import java.util.function.BiConsumer;

public class UmbranTreeFeature extends BOPTreeFeature<TaigaTreeConfiguration>
{
    public UmbranTreeFeature(Codec<TaigaTreeConfiguration> codec)
    {
        super(codec);
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height, TaigaTreeConfiguration config)
    {
        for (int y = 0; y <= height; y++)
        {
            int trunkWidth = (config.trunkWidth * (height - y) / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            // require 3x3 for the leaves, 1x1 for the trunk
            int start = (y <= baseHeight ? trunkStart : trunkStart - 1);
            int end = (y <= baseHeight ? trunkEnd : trunkEnd + 1);

            for (int x = start; x <= end; x++)
            {
                for (int z = start; z <= end; z++)
                {
                    BlockPos pos1 = pos.offset(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.canReplace(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // generates a layer of leafs
    public void generateLeafLayer(LevelAccessor world, RandomSource rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd, FoliagePlacer.FoliageSetter leaves, TaigaTreeConfiguration config)
    {
        int start = trunkStart - leavesRadius;
        int end = trunkEnd + leavesRadius;

        for (int x = start; x <= end; x++)
        {
            for (int z = start; z <= end; z++)
            {
                // skip corners
                if ((leavesRadius > 0 ) && (x == start || x == end) && (z == start || z == end)) {continue;}
                int distFromTrunk = (x < 0 ? trunkStart - x : x - trunkEnd) + (z < 0 ? trunkStart - z : z - trunkEnd);

                // set leaves as long as it's not too far from the trunk to survive
                if (distFromTrunk < 4 || (distFromTrunk == 4 && rand.nextInt(2) == 0))
                {
                    this.placeLeaves(world, pos.offset(x, 0, z), leaves, config);

                    if ((x == start || x == end || z == start || z == end) && rand.nextInt(3) != 0)
                    {
                        this.placeLeaves(world, pos.offset(x, -1, z), leaves, config);
                    }
                }
            }
        }
    }

    public void generateBranch(LevelAccessor world, RandomSource rand, BlockPos pos, Direction direction, int length, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TaigaTreeConfiguration config)
    {
        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.getClockWise();
        boolean slant = true;
        int slantOffset = 0;

        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.relative(direction, i-(int)Math.floor(slantOffset)).below((int)Math.floor(slantOffset));
            int r = (i == 1 || i == length) ? 1 : 2;

            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.placeLeaves(world, pos1.relative(sideways, j), leaves, config);
                }
            }
            if (length - i > 2)
            {
                this.placeLeaves(world, pos1.above(), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), leaves, config);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), leaves, config);
                //this.placeLog(world, pos1, axis, logs, config);
            }

            if (slant && i > 2) {slantOffset += 1;}
        }
    }

    @Override
    protected boolean doPlace(WorldGenLevel world, RandomSource random, BlockPos startPos, BiConsumer<BlockPos, BlockState> roots, BiConsumer<BlockPos, BlockState> logs, FoliagePlacer.FoliageSetter leaves, TreeConfiguration configBase)
    {
        TaigaTreeConfiguration config = (TaigaTreeConfiguration)configBase;

        // Move down until we reach the ground
        while (startPos.getY() >= world.getMinBuildHeight()+1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).is(BlockTags.LEAVES))
        {
            startPos = startPos.below();
        }

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, config.minHeight, config.maxHeight);
        int baseHeight = GeneratorUtil.nextIntBetween(random, height / 5, height / 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}

        if (!this.checkSpace(world, startPos.above(), baseHeight, height, config))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        boolean topLeaves = height <= 20 ? true : random.nextInt(3) == 0;
        if (topLeaves) { this.placeLeaves(world, pos, leaves, config); }
        pos.below();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {

            int trunkWidth = (config.trunkWidth * i / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            int radius = Math.min(Math.min((i + 2) / 3, 3 + (leavesHeight - i)), 6);
            if (radius == 0)
            {
                if (topLeaves)
                {
                    this.placeLeaves(world, pos, leaves, config);
                }
                else
                {
                    this.placeLog(world, pos, logs, config);
                }
            }
            else if (radius < 4)
            {
                // for smallish radius, do simple leaf layers
                if (i % 3 == 0)
                {
                    this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd, leaves, config);
                }
                else
                {
                    if (topLeaves)
                    {
                        this.placeLeaves(world, pos, leaves, config);
                    }
                    else
                    {
                        this.placeLog(world, pos, logs, config);
                    }
                }
            }
            else
            {
                // for bigger radius, need branches
                if (i % 3 == 0)
                {
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius, logs, leaves, config);
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius, logs, leaves, config);
                }
            }
            pos = pos.below();
        }

        // Generate the trunk
        for (int y = 0; y < height - 1; y++)
        {
            int trunkWidth = (config.trunkWidth * (height - y) / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            if (trunkWidth < 1 || trunkWidth > config.trunkWidth)
            {
                trunkStart = 0;
                trunkEnd = 0;
            }

            for (int x = trunkStart; x <= trunkEnd; x++)
            {
                for (int z = trunkStart; z <= trunkEnd; z++)
                {
                    if (y == 1)
                    {
                        world.setBlock(startPos.offset(x, y - 1, z), Blocks.DIRT.defaultBlockState(), 3);
                    }

                    this.placeLog(world, startPos.offset(x, y, z), logs, config);
                }
            }
        }

        return true;
    }
}