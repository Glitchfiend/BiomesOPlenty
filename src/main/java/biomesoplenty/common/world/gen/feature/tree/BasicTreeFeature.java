/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.block.IBlockPosQuery;
import biomesoplenty.common.world.gen.BOPFeatureUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.structure.BoundingBox;
import net.minecraft.world.level.material.Material;

import java.util.Random;
import java.util.Set;

public class BasicTreeFeature extends TreeFeatureBase
{
    public static class Builder extends InnerBuilder<Builder, BasicTreeFeature>
    {
        @Override
        public BasicTreeFeature create()
        {
            return new BasicTreeFeature(this.placeOn, this.replace, this.log, this.leaves, this.altLeaves, this.vine, this.hanging, this.trunkFruit, this.minHeight, this.maxHeight, this.leafLayers, this.leavesOffset, this.maxLeavesRadius, this.leavesLayerHeight, this.placeVinesOn, this.hangingChance);
        }
    }

    protected static abstract class InnerBuilder<T extends BuilderBase, F extends TreeFeatureBase> extends BuilderBase<T, F>
    {
        protected int leafLayers;
        protected int leavesOffset;
        protected int maxLeavesRadius;
        protected int leavesLayerHeight;
        protected IBlockPosQuery placeVinesOn;
        protected float hangingChance;

        public T leafLayers(int a) {this.leafLayers = a; return (T)this;}
        public T leavesOffset(int a) {this.leavesOffset = a; return (T)this;}
        public T leavesLayerHeight(int a) {this.leavesLayerHeight = a; return (T)this;}
        public T maxLeavesRadius(int a) {this.maxLeavesRadius = a; return (T)this;}

        public T placeVinesOn(IBlockPosQuery a) {this.placeVinesOn = a; return (T)this;}

        public T hangingChance(float a) {this.hangingChance = a; return (T)this;}

        public InnerBuilder()
        {
            this.placeOn = (world, pos) ->
            {
                return world.getBlockState(pos).canOcclude();
            };
            this.minHeight = 4;
            this.maxHeight = 7;
            this.leafLayers = 4;
            this.leavesOffset = 1;
            this.maxLeavesRadius = 1;
            this.leavesLayerHeight = 2;
            this.placeVinesOn = (world, pos) ->
            {
                Material mat = world.getBlockState(pos).getMaterial();
                return mat == Material.AIR;
            };
            this.hangingChance = 0.0F;
        }
    }

    protected int leafLayers;
    protected int leavesOffset;
    protected int maxLeavesRadius;
    protected int leavesLayerHeight;
    protected IBlockPosQuery placeVinesOn;
    protected float hangingChance;

    protected BasicTreeFeature(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log,
        BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit,
        int minHeight, int maxHeight, int leafLayers, int leavesOffset, int maxLeavesRadius, int leavesLayerHeight,
        IBlockPosQuery placeVinesOn, float hangingChance)
    {
        super(placeOn, replace, log, leaves, altLeaves, vine, hanging, trunkFruit, minHeight, maxHeight);

        this.leafLayers = leafLayers;
        this.leavesOffset = leavesOffset;
        this.maxLeavesRadius = maxLeavesRadius;
        this.leavesLayerHeight = leavesLayerHeight;
        this.placeVinesOn = placeVinesOn;
        this.hangingChance = hangingChance;
    }

    @Override
    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, LevelAccessor world, Random random, BlockPos pos, BoundingBox boundingBox)
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
                    int leavesLayers = (this.leafLayers - 1);

                    //Generates leaves at the top of the tree, going one block above the top log (<= rather than <)
                    for (int y = pos.getY() + height - leavesLayers; y <= pos.getY() + height; y++)
                    {
                        //Determines the distance from the top of the tree as a negative number
                        int currentLayer = y - (pos.getY() + height);
                        //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                        //the radius closer to the top of the tree. (2, 2, 1, 1)
                        int leavesRadius =  this.maxLeavesRadius - currentLayer / this.leavesLayerHeight;

                        for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                        {
                            int xDiff = x - pos.getX();

                            for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; ++z)
                            {
                                int zDiff = z - pos.getZ();

                                //Randomly prevent the generation of leaves on the corners of each layer
                                //If the layer is the top layer, never generate the corners
                                if (Math.abs(xDiff) != leavesRadius || Math.abs(zDiff) != leavesRadius || random.nextInt(2) != 0 && currentLayer != 0)
                                {
                                    BlockPos leavesPos = new BlockPos(x, y, z);
                                    if (this.replace.matches(world, leavesPos))
                                    {
                                        if (this.altLeaves != Blocks.AIR.defaultBlockState())
                                        {
                                            if (random.nextInt(4) == 0)
                                            {
                                                this.setAltLeaves(world, leavesPos, changedLeaves, boundingBox);
                                            }
                                            else
                                            {
                                                this.placeLeaves(world, leavesPos, changedLeaves, boundingBox);
                                            }
                                        }
                                        else
                                        {
                                            this.placeLeaves(world, leavesPos, changedLeaves, boundingBox);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    this.generateTrunk(changedLogs, boundingBox, world, pos, height);

                    if (this.vine != Blocks.AIR.defaultBlockState())
                    {
                        for (int y = pos.getY() - leavesLayers + height; y <= pos.getY() + height; y++)
                        {
                            //Determines the distance from the top of the tree as a negative number
                            int currentLayer = y - (pos.getY() + height);
                            //Uses integer division truncation (-3 / 2 = -1, -2 / 2 = -1) to reduce
                            //the radius closer to the top of the tree. (3, 3, 2, 2)
                            int leavesRadius = (this.maxLeavesRadius + this.leavesOffset) - currentLayer / this.leavesLayerHeight;

                            for (int x = pos.getX() - leavesRadius; x <= pos.getX() + leavesRadius; x++)
                            {
                                for (int z = pos.getZ() - leavesRadius; z <= pos.getZ() + leavesRadius; z++)
                                {
                                    BlockPos blockpos3 = new BlockPos(x, y, z);

                                    //Surround leaves on the edge of the tree with vines and extend them downwards
                                    if (world.getBlockState(blockpos3).getMaterial() == Material.LEAVES)
                                    {
                                        BlockPos westPos = blockpos3.west();
                                        BlockPos eastPos = blockpos3.east();
                                        BlockPos northPos = blockpos3.north();
                                        BlockPos southPos = blockpos3.south();

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, westPos))
                                        {
                                            this.extendVines(world, westPos, Direction.EAST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, eastPos))
                                        {
                                            this.extendVines(world, eastPos, Direction.WEST);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, northPos))
                                        {
                                            this.extendVines(world, northPos, Direction.SOUTH);
                                        }

                                        if (random.nextInt(4) == 0 && this.placeVinesOn.matches(world, southPos))
                                        {
                                            this.extendVines(world, southPos, Direction.NORTH);
                                        }
                                    }
                                }
                            }
                        }
                    }

                    //Generate fruit or any other blocks that may hang off of the tree
                    if (this.hanging != Blocks.AIR.defaultBlockState()) this.generateHanging(world, pos, random, height);

                    if (this.trunkFruit != Blocks.AIR.defaultBlockState())
                    {
                        if (random.nextInt(5) == 0 && height > 5)
                        {
                            for (int l3 = 0; l3 < 2; ++l3)
                            {
                                for (Direction Direction : Direction.Plane.HORIZONTAL)
                                {
                                    if (random.nextInt(4 - l3) == 0)
                                    {
                                        Direction Direction1 = Direction.getOpposite();
                                        this.generateTrunkFruit(world, random.nextInt(3), pos.offset(Direction1.getStepX(), height - 5 + l3, Direction1.getStepZ()), Direction);
                                    }
                                }
                            }
                        }
                    }

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

    protected void generateTrunk(Set<BlockPos> changedBlocks, BoundingBox boundingBox, LevelAccessor world, BlockPos start, int height)
    {
        //Create the trunk from the bottom up, using < to ensure it is covered with one layer of leaves
        for (int layer = 0; layer < height; ++layer)
        {
            BlockPos blockpos2 = start.above(layer);
            if (this.replace.matches(world, blockpos2))
            {
                this.placeLog(world, start.above(layer), changedBlocks, boundingBox);
            }
        }
    }

    protected void generateHanging(LevelAccessor world, BlockPos start, Random rand, int height)
    {
        //Generate below the bottom layer of leaves
        int y = start.getY() + (height - this.leafLayers);

        for (int x = start.getX() - (maxLeavesRadius + 1); x <= start.getX() + (maxLeavesRadius + 1); x++)
        {
            for (int z = start.getZ() - (maxLeavesRadius + 1); z <= start.getZ() + (maxLeavesRadius + 1); z++)
            {
                BlockPos hangingPos = new BlockPos(x, y, z);

                if (!world.isEmptyBlock(hangingPos.above()) && (world.isEmptyBlock(hangingPos)) && rand.nextFloat() <= this.hangingChance)
                {
                    this.setHanging(world, hangingPos);
                }
            }
        }
    }

    private void generateTrunkFruit(LevelAccessor world, int age, BlockPos pos, Direction direction)
    {
        if (this.trunkFruit == Blocks.COCOA.defaultBlockState())
        {
            this.setBlock(world, pos, this.trunkFruit.setValue(CocoaBlock.AGE, Integer.valueOf(age)).setValue(CocoaBlock.FACING, direction));
        }
        else
        {
            this.setBlock(world, pos, this.trunkFruit.setValue(CocoaBlock.FACING, direction));
        }
    }

    private BlockState getVineStateForSide(Direction side)
    {
        return this.vine.getBlock() instanceof VineBlock ? this.vine.setValue(VineBlock.getPropertyForFace(side), Boolean.valueOf(true)) : this.vine;
    }

    private void extendVines(LevelAccessor world, BlockPos pos, Direction side)
    {
        BlockState vineState = this.getVineStateForSide(side);
        this.setBlock(world, pos, vineState);

        int length = 4;

        //Extend vine downwards for a maximum of 4 blocks
        for (pos = pos.below(); this.placeVinesOn.matches(world, pos) && length > 0; length--)
        {
            this.setBlock(world, pos, vineState);
            pos = pos.below();
        }
    }
}
