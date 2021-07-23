/*******************************************************************************
 * Copyright 2021, the Glitchfiend Team.
 * All rights reserved.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.IBlockPosQuery;
import biomesoplenty.common.world.gen.BOPFeatureUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class MahoganyTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<MahoganyTreeFeature.Builder, MahoganyTreeFeature>
    {
        public Builder()
        {
            this.log = BOPBlocks.MAHOGANY_LOG.defaultBlockState();
            this.leaves = BOPBlocks.MAHOGANY_LEAVES.defaultBlockState();
            this.minHeight = 8;
            this.maxHeight = 14;
        }

        @Override
        public MahoganyTreeFeature create()
        {
            return new MahoganyTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }
    }

    protected MahoganyTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log,
                               BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit,
                               int minHeight, int maxHeight)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    @Override
    protected boolean place(LevelAccessor world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves)
    {
        int height = random.nextInt(this.maxHeight - this.minHeight) + this.minHeight;
        boolean hasSpace = true;

        //Generate only if we are above the lowest bedrock level (1) and reach less than the world height
        //There must be a gap of 1 between the top leaf block and the world height
        if (pos.getY() >= 1 && pos.getY() + height + 1 <= 256)
        {
            int radius;

            for (int y = pos.getY(); y <= pos.getY() + 1 + height; y++)
            {
                radius = 1;

                //Don't check for space on the first level, if we are a sapling then there will
                //already be a block here (the sapling itself)
                if (y == pos.getY())
                {
                    radius = 0;
                }

                //At and above the top log block, require a radius of 2 to be empty
                if (y >= pos.getY() + 1 + height - 2)
                {
                    radius = 2;
                }

                for (int x = pos.getX() - radius; x <= pos.getX() + radius && hasSpace; ++x)
                {
                    for (int z = pos.getZ() - radius; z <= pos.getZ() + radius && hasSpace; ++z)
                    {
                        if (y >= 0 && y < 256)
                        {
                            if (!this.replace.matches(world, new BlockPos(x, y, z)))
                            {
                                hasSpace = false;
                            }
                        }
                        else
                        {
                            hasSpace = false;
                        }
                    }
                }
            }

            if (!hasSpace)
            {
                return false;
            }
            else
            {
                BlockPos soilPos = pos.below();
                Block soil = world.getBlockState(soilPos).getBlock();
                boolean isSoil = BOPFeatureUtil.isSoil(world, soilPos);

                if (this.placeOn.matches(world, soilPos) && isSoil && pos.getY() < 256 - height - 1)
                {
                    world.setBlock(soilPos, Blocks.DIRT.defaultBlockState(), 3);

                    this.generateTrunk(logs, leaves, world, pos, height);

                    return true;
                }
                else
                {
                    return false;
                }
            }
        }
        else
        {
            return false;
        }
    }

    protected void generateTrunk(BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, LevelAccessor world, BlockPos start, int height)
    {
        int endHeight = height;

        for (int layer = 0; layer <= endHeight - 3; layer++)
        {
            BlockPos middlePos = start.above(layer);

            if (this.replace.matches(world, middlePos))
            {
                this.placeLog(world, middlePos, logs);
            }
        }

        //Generate upper branches
        BlockPos branchStartPos = start.above(endHeight - 3);

        generateBranch(logs, leaves, world, branchStartPos, Direction.NORTH);
        generateBranch(logs, leaves, world, branchStartPos, Direction.EAST);
        generateBranch(logs, leaves, world, branchStartPos, Direction.SOUTH);
        generateBranch(logs, leaves, world, branchStartPos, Direction.WEST);
    }

    private void generateBranch(BiConsumer<BlockPos, BlockState> logs, BiConsumer<BlockPos, BlockState> leaves, LevelAccessor world, BlockPos middle, Direction direction)
    {
        BlockPos pos = middle;
        int length = 1 + world.getRandom().nextInt(2);

        for (int i = 0; i <= length - 1; i++)
        {
            if (replace.matches(world, pos.relative(direction, i+1)))
            {
                this.placeLog(world, pos.relative(direction, i+1), direction.getAxis(), logs);
            }
        }

        int height = 1 + world.getRandom().nextInt(2);
        for (int i = 0; i <= height; i++)
        {
            if (replace.matches(world, pos.relative(direction, length+1).above(i+1)))
            {
                this.placeLog(world, pos.relative(direction, length+1).above(i+1), Direction.Axis.Y, logs);
            }
        }


        pos = pos.relative(direction, length+1).above(height+2);

        int radius = 2;
        for (int x = -(radius-1); x <= (radius-1); x++)
        {
            for (int z = -(radius - 1); z <= (radius - 1); z++)
            {
                this.placeLeaves(world, pos.offset(x,0,z), leaves);
            }
        }

        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                if ((x == -radius || x == radius) && (z == -radius || z == radius))
                {
                    continue;
                }
                else
                {
                    this.placeLeaves(world, pos.offset(x,-1,z), leaves);
                }
            }
        }
    }
}
