/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.Mth;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class TaigaTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<Builder, TaigaTreeFeature>
    {
        protected int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 12;
            this.log = Blocks.SPRUCE_LOG.defaultBlockState();
            this.leaves = Blocks.SPRUCE_LEAVES.defaultBlockState();
            this.vine = Blocks.VINE.defaultBlockState();
            this.trunkWidth = 1;
        }

        @Override
        public TaigaTreeFeature create()
        {
            return new TaigaTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.trunkWidth);
        }

    }

    private int trunkWidth = 1;

    protected TaigaTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight, int trunkWidth)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
        this.trunkWidth = trunkWidth;
    }

    public boolean checkSpace(LevelAccessor world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            int trunkWidth = (this.trunkWidth * (height - y) / height) + 1;
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
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    // generates a layer of leafs
    public void generateLeafLayer(LevelAccessor world, Random rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd, BiConsumer<BlockPos, BlockState> leaves)
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
                    this.placeLeaves(world, pos.offset(x, 0, z), leaves);
                }
            }
        }
    }

    public void generateBranch(LevelAccessor world, Random rand, BlockPos pos, Direction direction, int length, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves)
    {
        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.getClockWise();
        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.relative(direction, i);
            int r = (i == 1 || i == length) ? 1 : 2;
            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.placeLeaves(world, pos1.relative(sideways, j), leaves);
                }
            }
            if (length - i > 2)
            {
                this.placeLeaves(world, pos1.above(), leaves);
                this.placeLeaves(world, pos1.above().relative(sideways, -1), leaves);
                this.placeLeaves(world, pos1.above().relative(sideways, 1), leaves);
                this.placeLog(world, pos1, axis, logs);
            }
        }
    }


    @Override
    protected boolean place(LevelAccessor world, Random random, BlockPos startPos, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves)
    {
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.below();}

        for (int x = 0; x <= this.trunkWidth - 1; x++)
        {
            for (int z = 0; z <= this.trunkWidth - 1; z++)
            {
		        if (!this.placeOn.matches(world, startPos.offset(x, 0, z)))
		        {
		            // Abandon if we can't place the tree on this block
		            return false;
		        }
            }
        }

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        int baseHeight = GeneratorUtil.nextIntBetween(random, height / 5, height / 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}

        if (!this.checkSpace(world, startPos.above(), baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.above(height);

        // Leaves at the top
        this.placeLeaves(world, pos, leaves);
        pos.below();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {

            int trunkWidth = (this.trunkWidth * i / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);


            int radius = Math.min(Math.min((i + 2) / 3, 3 + (leavesHeight - i)), 6);
            if (radius == 0)
            {
                this.placeLeaves(world, pos, leaves);
            }
            else if (radius < 4)
            {
                // for smallish radius, do simple leaf layers
                if (i % 2 == 0)
                {
                    this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd, leaves);
                }
                else
                {
                    this.generateLeafLayer(world, random, pos, radius / 2, trunkStart, trunkEnd, leaves);
                }
            }
            else
            {
                // for bigger radius, need branches
                if (i % 2 == 0)
                {
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius, logs, leaves);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius, logs, leaves);
                    this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius, logs, leaves);
                    this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius, logs, leaves);
                }
            }
            pos = pos.below();
        }

        // Generate the trunk
        for (int y = 0; y < height - 1; y++)
        {
            int trunkWidth = (this.trunkWidth * (height - y) / height) + 1;
            int trunkStart = Mth.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = Mth.floor(0.25D + trunkWidth / 2.0D);

            if (trunkWidth < 1 || trunkWidth > this.trunkWidth)
            {
                trunkStart = 0;
                trunkEnd = 0;
            }

            for (int x = trunkStart; x <= trunkEnd; x++)
            {
                for (int z = trunkStart; z <= trunkEnd; z++)
                {
                    this.placeLog(world, startPos.offset(x, y, z), logs);
                }
            }
        }

        return true;
    }
}
