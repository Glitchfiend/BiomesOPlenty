/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
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

public class RedwoodTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<Builder, RedwoodTreeFeature>
    {
        protected int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 10;
            this.maxHeight = 30;
            this.log = BOPBlocks.REDWOOD_LOG.defaultBlockState();
            this.leaves = BOPBlocks.REDWOOD_LEAVES.defaultBlockState();
            this.vine = Blocks.VINE.defaultBlockState();
            this.trunkWidth = 1;
        }

        @Override
        public RedwoodTreeFeature create()
        {
            return new RedwoodTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.trunkWidth);
        }

    }

    private int trunkWidth = 1;

    protected RedwoodTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight, int trunkWidth)
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
        int baseHeight = GeneratorUtil.nextIntBetween(random, (int)(height * 0.6F), (int)(height * 0.4F));
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


            int radius = Math.min(Math.min((i + 2) / 4, 2 + (leavesHeight - i)), 4);
            if (radius == 0)
            {
                this.placeLeaves(world, pos, leaves);
            }
            else if (radius < 2)
            {
                this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd, leaves);
            }
            else
            {
                this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkStart), Direction.NORTH, radius, logs, leaves);
                this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkStart), Direction.EAST, radius, logs, leaves);
                this.generateBranch(world, random, pos.offset(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius, logs, leaves);
                this.generateBranch(world, random, pos.offset(trunkStart, 0, trunkEnd), Direction.WEST, radius, logs, leaves);
            }
            pos = pos.below();
        }

        // Create the trunk widths scales
        double[] scalingFactors = new double[]{
                (0.35 + random.nextDouble() * 0.15),
                (0.07 + random.nextDouble() * 0.1),
                (random.nextDouble() * 0.06)
        };

        if (this.trunkWidth == 3) {
            scalingFactors = new double[]{
                    (0.6 + random.nextDouble() * 0.2),
                    (0.1 + random.nextDouble() * 0.2),
                    (0.03 + random.nextDouble() * 0.09)
            };
        }

        // Generate the trunk
        for (int x = -this.trunkWidth; x <= this.trunkWidth; x++) {
            for (int z = -this.trunkWidth; z <= this.trunkWidth; z++) {
                int dist = Math.abs(x) + Math.abs(z);

                int heightHere = height - 2;

                // If we're not the center of the trunk on a single trunk width, give up
                if (this.trunkWidth == 1 && dist > 0) {
                    continue;
                }

                // Scale bigger widths
                if (dist == 1) {
                    heightHere = (int) (height * scalingFactors[0]);
                } else if (dist == 2) {
                    heightHere = (int) (height * scalingFactors[1]);
                } else if (dist == 3) {
                    heightHere = (int) (height * scalingFactors[2]);
                } else if (dist > 3) {
                    continue;
                }

                heightHere += random.nextInt(2);

                for (int y = 0; y < heightHere; y++)
                {
                    BlockPos local = startPos.offset(x, y, z);
                    this.placeLog(world, local, logs);

                    if (dist > 0 && y > 4 && y < (baseHeight - 2) && random.nextInt(10) == 0) {
                        double theta;
                        if (x == 0 && z == 0) {
                            // Prevents bushes originating from the center from generating too low
                            if (y < 10) {
                                continue;
                            }

                            theta = Math.PI * random.nextDouble() * 2;
                        } else {
                            // Make sure the branches only go in the same direction of the current trunk position from the center
                            double angleFromCenter = Math.atan2(x, z);

                            theta = angleFromCenter + (Math.PI * (random.nextDouble() * 0.5 - 0.25));
                        }

                        int branchLength = (3 - dist) + 1 + random.nextInt(2);

                        BlockPos branchPos = null;
                        for (int i = 0; i < branchLength; i++) {
                            branchPos = local.offset(Math.cos(theta) * i, i / 2, Math.sin(theta) * i);

                            this.placeLog(world, branchPos, logs);
                        }

                        this.generateBush(logs, leaves, world, random, branchPos);
                    }
                }
            }
        }

        return true;
    }

    protected boolean generateBush(BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, LevelAccessor world, Random random, BlockPos pos)
    {
        //Generate a bush 3 blocks tall, with the center block set to a log
        for (int y = -1; y < 2; ++y)
        {
            // log in the center
            if (y == 0)
            {
                this.placeLog(world, pos.offset(0, y, 0), logs);
            }

            //Reduces the radius closer to the top of the bush
            int leavesRadius = y == 0 ? 2 : 1;

            for (int x = -leavesRadius; x <= leavesRadius; ++x)
            {
                for (int z = -leavesRadius; z <= leavesRadius; ++z)
                {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < leavesRadius || Math.abs(z) < leavesRadius || random.nextInt(2) != 0)
                    {
                        if (this.altLeaves != Blocks.AIR.defaultBlockState())
                        {
                            if (random.nextInt(4) == 0)
                            {
                                this.placeAltLeaves(world, pos.offset(x, y, z), leaves);
                            }
                            else
                            {
                                this.placeLeaves(world, pos.offset(x, y, z), leaves);
                            }
                        }
                        else
                        {
                            this.placeLeaves(world, pos.offset(x, y, z), leaves);
                        }
                    }
                }
            }
        }

        return true;
    }
}