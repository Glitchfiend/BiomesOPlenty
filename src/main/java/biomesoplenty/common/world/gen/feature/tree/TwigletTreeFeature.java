/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCocoa;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
import net.minecraft.world.World;

import java.util.Random;
import java.util.Set;

public class TwigletTreeFeature extends TreeFeatureBase
{
    public static class Builder extends BuilderBase<TwigletTreeFeature.Builder, TwigletTreeFeature>
    {
        protected float leafChanceEven;
        protected float leafChanceOdd;

        public Builder leafChance(float a)
        {
            this.leafChanceEven = a;
            this.leafChanceOdd = a;
            return this;
        }

        public Builder leafChance(float a, float b)
        {
            this.leafChanceEven = a;
            this.leafChanceOdd = b;
            return this;
        }

        public Builder()
        {
            this.minHeight = 2;
            this.maxHeight = 6;
            this.leafChanceEven = 0.2F;
            this.leafChanceOdd = 0.9F;
        }

        @Override
        public TwigletTreeFeature create()
        {
            return new TwigletTreeFeature(this.updateNeighbours, this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.leafChanceEven, this.leafChanceOdd);
        }
    }

    private float leafChanceEven;
    private float leafChanceOdd;

    protected TwigletTreeFeature(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState altLeaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, int minHeight, int maxHeight, float leafChanceEven, float leafChanceOdd)
    {
        super(notify, placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
        this.leafChanceEven = leafChanceEven;
        this.leafChanceOdd = leafChanceOdd;
    }


    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorld world, Random random, BlockPos startPos)
    {

        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES)
        {
            startPos = startPos.down();
        }

        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }

        // choose a random height
        int height = this.minHeight + random.nextInt(1 + this.maxHeight - this.minHeight);
        int baseHeight = height / 3;

        // start from the block above the ground block
        BlockPos pos = startPos.up();

        // add log and leaves on each level
        float leafChance;
        for (int y = 0; y < height; y++)
        {
            if (!this.setLog(changedBlocks, world, pos.up(y)))
            {
                // abandon if the log can't grow
                return true;
            }
            leafChance = ((height - y) % 2 == 0) ? this.leafChanceEven : this.leafChanceOdd;
            if (y <= baseHeight)
            {
                continue;
            } // no leaves below base height
            if (random.nextFloat() < leafChance)
            {
                this.setLeaves(world, pos.add(1, y, 0));
            }
            if (random.nextFloat() < leafChance)
            {
                this.setLeaves(world, pos.add(-1, y, 0));
            }
            if (random.nextFloat() < leafChance)
            {
                this.setLeaves(world, pos.add(0, y, 1));
            }
            if (random.nextFloat() < leafChance)
            {
                this.setLeaves(world, pos.add(0, y, -1));
            }

            if (this.trunkFruit != Blocks.AIR.getDefaultState())
            {
                if (random.nextInt(3) == 0)
                {
                    for (int l3 = 0; l3 < 2; ++l3)
                    {
                        for (EnumFacing enumfacing : EnumFacing.Plane.HORIZONTAL)
                        {
                            if (random.nextInt(4 - l3) == 0)
                            {
                                EnumFacing enumfacing1 = enumfacing.getOpposite();
                                this.generateTrunkFruit(world, random.nextInt(3), pos.add(enumfacing1.getXOffset(), 0, enumfacing1.getZOffset()), enumfacing);
                            }
                        }
                    }
                }
            }
        }
        // finish with leaves on top
        this.setLeaves(world, pos.add(0, height, 0));

        return true;
    }

    private void generateTrunkFruit(IWorld world, int age, BlockPos pos, EnumFacing direction)
    {
        if (this.trunkFruit == Blocks.COCOA.getDefaultState())
        {
            this.setBlockState(world, pos, this.trunkFruit.with(BlockCocoa.AGE, Integer.valueOf(age)).with(BlockCocoa.HORIZONTAL_FACING, direction));
        }
        else
        {
            this.setBlockState(world, pos, this.trunkFruit.with(BlockCocoa.HORIZONTAL_FACING, direction));
        }
    }
}
