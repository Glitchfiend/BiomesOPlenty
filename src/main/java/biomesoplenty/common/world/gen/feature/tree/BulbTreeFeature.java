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
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.DirectionalBlock;
import net.minecraft.block.material.Material;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;

import java.util.Random;
import java.util.Set;

public class BulbTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<BulbTreeFeature.Builder, BulbTreeFeature>
    {
        public Builder()
        {
            this.minHeight = 6;
            this.maxHeight = 12;
        }

        @Override
        public BulbTreeFeature create()
        {
            return new BulbTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight);
        }

    }

    protected BulbTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(notify, placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
    }

    public boolean setCocoa(IWorld world, BlockPos pos, Direction side)
    {
        BlockState cocoaState = Blocks.COCOA.getDefaultState().with(DirectionalBlock.FACING, side);
        if (this.replace.matches(world, pos))
        {
            this.setBlockState(world, pos, cocoaState);
            return true;
        }
        return false;
    }

    public boolean checkSpace(IWorld world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            // require 3x3 for the leaves, 1x1 for the trunk
            int radius = (y <= baseHeight ? 0 : 1);

            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
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
        return true;
    }

    // generates a 'branch' of a leaf layer
    public void generateBranch(IWorld world, Random random, BlockPos pos, Direction direction)
    {
        Direction sideways = direction.rotateY();
        this.setLeaves(world, pos.offset(direction, 1));
        this.setLeaves(world, pos.up().offset(direction, 1));
        if (random.nextInt(3) > 0)
        {
            this.setLeaves(world, pos.up().offset(direction, 1).offset(sideways, 1));
        }
    }

    // generates a layer of leafs (2 blocks high)
    public void generateLeafLayer(Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox, IWorld world, Random random, BlockPos pos)
    {
        for (Direction direction : Direction.Plane.HORIZONTAL)
        {
            this.generateBranch(world, random, pos, direction);
        }

        // add the trunk in the middle
        this.setLog(changedBlocks, world, pos, boundingBox);
        this.setLog(changedBlocks, world, pos.up(), boundingBox);
    }

    public void generateTop(Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox,  IWorld world, Random random, BlockPos pos, int topHeight)
    {
        for (int y = 0; y < topHeight; y++)
        {
            int radius = topHeight - 1 - y;

            for (int x = -radius; x <= radius; ++x)
            {
                for (int z = -radius; z <= radius; ++z)
                {
                    if (Math.abs(x) < radius || Math.abs(z) < radius || random.nextInt(2) == 0)
                    {
                        this.setLeaves(world, pos.add(x, y, z));
                    }
                }
            }
            if (y < topHeight - 1)
            {
                // add the trunk in the middle
                this.setLog(changedBlocks, world, pos.add(0, y, 0), boundingBox);
            } else {
                // add leaves on top for certain
                this.setLeaves(world, pos.add(0, y, 0));
            }
        }
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorld world, Random random, BlockPos startPos, MutableBoundingBox boundingBox)
    {

        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES) {startPos = startPos.down();}

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        // Choose heights
        int height = GeneratorUtil.nextIntBetween(random, this.minHeight, this.maxHeight);
        if (height < 6) {return false;}
        int topHeight = 3;
        int heightMinusTop = height - topHeight;
        int numBranches = heightMinusTop / 5;
        int baseHeight = heightMinusTop - (numBranches * 2);

        // Start on the space above ground
        BlockPos pos = startPos.up();

        if (!this.checkSpace(world, pos, baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Generate bottom of tree (trunk only)
        for(int i = 0; i < baseHeight; i++)
        {
            this.setLog(changedBlocks, world, pos, boundingBox);
            pos = pos.up();
        }

        // Generate middle of the tree - 2 steps at a time (trunk and leaves)
        for (int i = 0; i < numBranches; i++)
        {
            this.generateLeafLayer(changedBlocks, boundingBox, world, random, pos);
            pos = pos.up(2);
        }

        // Generate the top of the tree
        this.generateTop(changedBlocks, boundingBox, world, random, pos, topHeight);

        // Add vines
        this.addVines(world, random, startPos, baseHeight, height, 3, 10);

        // Add cocoa
        // this.addCocoa(world, random, startPos, baseHeight, 3);

        return true;
    }

    protected void addVines(IWorld world, Random rand, BlockPos startPos, int baseHeight, int height, int leavesRadius, int generationAttempts)
    {
        if (this.vine == null) {return;}
        for (int i = 0; i < generationAttempts; i++)
        {
            // choose a random direction
            Direction direction = Direction.Plane.HORIZONTAL.random(rand);
            Direction back = direction.getOpposite();
            Direction sideways = direction.rotateY();

            // choose a random starting point somewhere just outside the boundary of the tree leaves
            BlockPos pos = startPos.up(GeneratorUtil.nextIntBetween(rand, baseHeight + 1, height)).offset(direction, leavesRadius + 1).offset(sideways, GeneratorUtil.nextIntBetween(rand, -leavesRadius, leavesRadius));

            // move back towards the center until we meet a leaf, then stick a vine on it
            for (int l = 0; l < leavesRadius; l++)
            {
                if (world.getBlockState(pos.offset(back, 1 + l)) == this.leaves) {
                    this.setVine(world, rand, pos.offset(back, l), back, 4);
                    break;
                }
            }
        }
    }

    protected void addCocoa(IWorld world, Random rand, BlockPos startPos, int baseHeight, int generationAttempts)
    {
        for (int i = 0; i < generationAttempts; i++)
        {
            // choose a random direction
            Direction direction = Direction.Plane.HORIZONTAL.random(rand);
            Direction back = direction.getOpposite();

            // choose a random point next to the trunk
            BlockPos pos = startPos.up(GeneratorUtil.nextIntBetween(rand, 1, baseHeight)).offset(direction, 1);

            // stick a cocoa pod on it
            this.setCocoa(world, pos, back);
        }
    }
}
