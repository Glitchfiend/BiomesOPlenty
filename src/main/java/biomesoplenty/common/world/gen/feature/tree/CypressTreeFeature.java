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
import net.minecraft.block.*;
import net.minecraft.block.material.Material;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class CypressTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<Builder, CypressTreeFeature>
    {
        protected int trunkWidth;

        public Builder trunkWidth(int a) {this.trunkWidth = a; return this;}

        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 15;
            this.placeOn = (world, pos) -> world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING);
            this.replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getMaterial() == Material.WATER || world.getBlockState(pos).getBlock().isIn(BlockTags.SAPLINGS) || world.getBlockState(pos).getBlock() == Blocks.VINE || world.getBlockState(pos).getBlock() == BOPBlocks.willow_vine || world.getBlockState(pos).getBlock() instanceof BushBlock;
            this.log = BOPBlocks.willow_log.getDefaultState();
            this.leaves = BOPBlocks.willow_leaves.getDefaultState();
            this.vine = BOPBlocks.willow_vine.getDefaultState();
            this.trunkWidth = 1;
        }

        @Override
        public CypressTreeFeature create()
        {
            return new CypressTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.trunkWidth);
        }

    }

    private int trunkWidth = 1;

    protected CypressTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight, int trunkWidth)
    {
        super(notify, placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
        this.trunkWidth = trunkWidth;
    }

    public boolean checkSpace(IWorld world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            int trunkWidth = (this.trunkWidth * (height - y) / height) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);

            // require 3x3 for the leaves, 1x1 for the trunk
            int start = (y <= baseHeight ? trunkStart : trunkStart - 1);
            int end = (y <= baseHeight ? trunkEnd : trunkEnd + 1);

            for (int x = start; x <= end; x++)
            {
                for (int z = start; z <= end; z++)
                {
                    BlockPos pos1 = pos.add(x, y, z);
                    // note, there may be a sapling on the first layer - make sure this.replace matches it!
                    if (pos1.getY() >= 255 || !this.replace.matches(world, pos1))
                    {
                        return false;
                    }
                }
            }
        }

        BlockPos pos2 = pos.add(0, height - 2,0);
        if (!world.getBlockState(pos2).canBeReplacedByLeaves(world, pos2))
        {
            return false;
        }

        return true;
    }

    // generates a layer of leafs
    public void generateLeafLayer(IWorld world, Random rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd)
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
                if (distFromTrunk <= 2)
                {
                    this.setLeaves(world, pos.add(x, 0, z));
                }
            }
        }
    }

    public void generateBranch(Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox, IWorld world, Random rand, BlockPos pos, Direction direction, int length)
    {
        Direction.Axis axis = direction.getAxis();
        Direction sideways = direction.rotateY();
        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.offset(direction, i);
            int r = (i == 1 || i == length) ? 1 : 2;
            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.setLeaves(world, pos1.offset(sideways, j));
                }
            }
            if (length - i > 2)
            {
                this.setLeaves(world, pos1.up());
                this.setLeaves(world, pos1.up().offset(sideways, -1));
                this.setLeaves(world, pos1.up().offset(sideways, 1));
                this.setLog(changedBlocks, world, pos1, axis, boundingBox);
            }
        }
    }


    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorld world, Random random, BlockPos startPos, MutableBoundingBox boundingBox)
    {
        // Move down until we reach the ground
        while (startPos.getY() > 1 && this.replace.matches(world, startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.down();}

        for (int x = 0; x <= this.trunkWidth - 1; x++)
        {
            for (int z = 0; z <= this.trunkWidth - 1; z++)
            {
		        if (!this.placeOn.matches(world, startPos.add(x, 0, z)))
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

        if (!this.checkSpace(world, startPos.up(), baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Start at the top of the tree
        BlockPos pos = startPos.up(height);

        // Leaves at the top
        this.setLeaves(world, pos);
        pos.down();

        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {

            int trunkWidth = (this.trunkWidth * i / height) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);


            int radius = MathHelper.clamp(i, 0, 2);
            if (i == leavesHeight - 1)
            {
                radius = 1;
            }

            if (radius == 0)
            {
                this.setLeaves(world, pos);
            }
            else if (radius < 2)
            {
                this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd);
            }
            else
            {
	            this.generateBranch(changedBlocks, boundingBox, world, random, pos.add(trunkStart, 0, trunkStart), Direction.NORTH, radius);
	            this.generateBranch(changedBlocks, boundingBox, world, random, pos.add(trunkEnd, 0, trunkStart), Direction.EAST, radius);
	            this.generateBranch(changedBlocks, boundingBox, world, random, pos.add(trunkEnd, 0, trunkEnd), Direction.SOUTH, radius);
	            this.generateBranch(changedBlocks, boundingBox, world, random, pos.add(trunkStart, 0, trunkEnd), Direction.WEST, radius);
            }
            pos = pos.down();
        }

        // Generate the trunk
        for (int y = 0; y < height - 1; y++)
        {
            int trunkWidth = (this.trunkWidth * ((baseHeight + 5) - y) / (baseHeight + 5)) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);

            if (trunkWidth < 1)
            {
                trunkStart = 0;
                trunkEnd = 0;
            }

            for (int x = trunkStart; x <= trunkEnd; x++)
            {
                for (int z = trunkStart; z <= trunkEnd; z++)
                {
                    this.setLog(changedBlocks, world, startPos.add(x, y, z), boundingBox);
                }
            }
        }

        return true;
    }

    @Override
    public boolean setLeaves(IWorld world, BlockPos pos)
    {
        if (world.getBlockState(pos).canBeReplacedByLeaves(world, pos))
        {
            this.setBlockState(world, pos, this.leaves);
            return true;
        }
        return false;
    }
}
