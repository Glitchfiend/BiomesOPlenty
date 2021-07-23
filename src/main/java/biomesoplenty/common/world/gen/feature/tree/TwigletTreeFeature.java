/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.CocoaBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.Material;

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
            return new TwigletTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.leafChanceEven, this.leafChanceOdd);
        }
    }

    private float leafChanceEven;
    private float leafChanceOdd;

    protected TwigletTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight, float leafChanceEven, float leafChanceOdd)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);
        this.leafChanceEven = leafChanceEven;
        this.leafChanceOdd = leafChanceOdd;
    }

    @Override
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, LevelAccessor world, Random random, BlockPos startPos, BoundingBox boundingBox)
    {
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isEmptyBlock(startPos) || world.getBlockState(startPos).getMaterial() == Material.LEAVES)
        {
            startPos = startPos.below();
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
        BlockPos pos = startPos.above();

        // add log and leaves on each level
        float leafChance;
        for (int y = 0; y < height; y++)
        {
            if (!this.placeLog(world, pos.above(y), changedLogs, boundingBox))
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
                this.placeLeaves(world, pos.offset(1, y, 0), changedLeaves, boundingBox);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(-1, y, 0), changedLeaves, boundingBox);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(0, y, 1), changedLeaves, boundingBox);
            }
            if (random.nextFloat() < leafChance)
            {
                this.placeLeaves(world, pos.offset(0, y, -1), changedLeaves, boundingBox);
            }

            if (this.trunkFruit != Blocks.AIR.defaultBlockState())
            {
                for (Direction Direction : Direction.Plane.HORIZONTAL)
                {
                    if (random.nextInt(4) == 0)
                    {
                        if (this.trunkFruit.getBlock() == Blocks.COCOA)
                        {
                            this.generateTrunkFruit(world, random.nextInt(3), pos.offset(Direction.getOpposite().getStepX(), 0, Direction.getOpposite().getStepZ()), Direction);
                        }
                        else
                        {
                            this.generateTrunkFruit(world, random.nextInt(3), pos.offset(Direction.getStepX(), y, Direction.getStepZ()), Direction);
                        }
                    }
                }
            }
        }
        // finish with leaves on top
        this.placeLeaves(world, pos.offset(0, height, 0), changedLeaves, boundingBox);

        return true;
    }

    private void generateTrunkFruit(LevelAccessor world, int age, BlockPos pos, Direction direction)
    {
        if (this.trunkFruit == Blocks.COCOA.defaultBlockState())
        {
            if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() instanceof BushBlock)
            {
                this.setBlock(world, pos, this.trunkFruit.setValue(CocoaBlock.AGE, Integer.valueOf(age)).setValue(CocoaBlock.FACING, direction));
            }
        }
        else
        {
            if (world.getBlockState(pos).getBlock() == Blocks.AIR || world.getBlockState(pos).getBlock() instanceof BushBlock)
            {
                this.setBlock(world, pos, this.trunkFruit.setValue(CocoaBlock.FACING, direction));
            }
        }
    }
}
