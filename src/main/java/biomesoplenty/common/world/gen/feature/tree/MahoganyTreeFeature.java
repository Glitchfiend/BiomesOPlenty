/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.biome.GeneratorUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class MahoganyTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<MahoganyTreeFeature.Builder, MahoganyTreeFeature>
    {
        public Builder()
        {
            this.log = BOPBlocks.mahogany_log.defaultBlockState();
            this.leaves = BOPBlocks.mahogany_leaves.defaultBlockState();
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
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, IWorld world, Random random, BlockPos pos, MutableBoundingBox boundingBox)
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
                boolean isSoil = soil.canSustainPlant(world.getBlockState(soilPos), world, soilPos, Direction.UP, (SaplingBlock) Blocks.OAK_SAPLING);

                if (this.placeOn.matches(world, soilPos) && isSoil && pos.getY() < 256 - height - 1)
                {
                    soil.onPlantGrow(world.getBlockState(soilPos), world, soilPos, pos);

                    this.generateTrunk(changedLogs, changedLeaves, boundingBox, world, pos, height);

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

    protected void generateTrunk(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, MutableBoundingBox boundingBox, IWorld world, BlockPos start, int height)
    {
        int endHeight = height;

        for (int layer = 0; layer <= endHeight - 3; layer++)
        {
            BlockPos middlePos = start.above(layer);

            if (this.replace.matches(world, middlePos))
            {
                this.placeLog(world, middlePos, changedLogs, boundingBox);
            }
        }

        //Generate upper branches
        BlockPos branchStartPos = start.above(endHeight - 3);

        generateBranch(changedLogs, changedLeaves, boundingBox, world, branchStartPos, Direction.NORTH);
        generateBranch(changedLogs, changedLeaves, boundingBox, world, branchStartPos, Direction.EAST);
        generateBranch(changedLogs, changedLeaves, boundingBox, world, branchStartPos, Direction.SOUTH);
        generateBranch(changedLogs, changedLeaves, boundingBox, world, branchStartPos, Direction.WEST);
    }

    private void generateBranch(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, MutableBoundingBox boundingBox, IWorld world, BlockPos middle, Direction direction)
    {
        BlockPos pos = middle;
        int length = 1 + world.getRandom().nextInt(2);

        for (int i = 0; i <= length - 1; i++)
        {
            if (replace.matches(world, pos.relative(direction, i+1)))
            {
                this.placeLog(world, pos.relative(direction, i+1), direction.getAxis(), changedLogs, boundingBox);
            }
        }

        int height = 1 + world.getRandom().nextInt(2);
        for (int i = 0; i <= height; i++)
        {
            if (replace.matches(world, pos.relative(direction, length+1).above(i+1)))
            {
                this.placeLog(world, pos.relative(direction, length+1).above(i+1), Direction.Axis.Y, changedLogs, boundingBox);
            }
        }


        pos = pos.relative(direction, length+1).above(height+2);

        int radius = 2;
        for (int x = -(radius-1); x <= (radius-1); x++)
        {
            for (int z = -(radius - 1); z <= (radius - 1); z++)
            {
                this.placeLeaves(world, pos.offset(x,0,z), changedLeaves, boundingBox);
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
                    this.placeLeaves(world, pos.offset(x,-1,z), changedLeaves, boundingBox);
                }
            }
        }
    }
}
