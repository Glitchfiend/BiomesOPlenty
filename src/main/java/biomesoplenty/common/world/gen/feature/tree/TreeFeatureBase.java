/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.util.block.BlockUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.*;
import net.minecraft.state.Property;
import net.minecraft.tags.BlockTags;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.IWorldWriter;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.BaseTreeFeatureConfig;
import net.minecraft.world.gen.feature.TreeFeature;

import java.util.Random;
import java.util.Set;

public abstract class TreeFeatureBase extends TreeFeature
{
    protected static abstract class BuilderBase<T extends BuilderBase, F extends TreeFeatureBase>
    {
        protected IBlockPosQuery placeOn;
        protected IBlockPosQuery replace;
        protected BlockState log;
        protected BlockState leaves;
        protected BlockState vine;
        protected BlockState hanging;
        protected BlockState trunkFruit;
        protected BlockState altLeaves;
        protected int minHeight;
        protected int maxHeight;

        public BuilderBase()
        {
            this.placeOn = (world, pos) -> world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING);
            this.replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos) || world.getBlockState(pos).getBlock().is(BlockTags.SAPLINGS) || world.getBlockState(pos).getBlock() == Blocks.VINE || world.getBlockState(pos).getBlock() == BOPBlocks.willow_vine || world.getBlockState(pos).getBlock() == BOPBlocks.dead_branch || world.getBlockState(pos).getBlock() instanceof BushBlock;
            this.log = Blocks.OAK_LOG.defaultBlockState();
            this.leaves = Blocks.OAK_LEAVES.defaultBlockState();
            this.vine = Blocks.AIR.defaultBlockState();
            this.hanging = Blocks.AIR.defaultBlockState();
            this.trunkFruit = Blocks.AIR.defaultBlockState();
            this.altLeaves = Blocks.AIR.defaultBlockState();
        }

        public T placeOn(IBlockPosQuery a) {this.placeOn = a; return (T)this;}

        public T replace(IBlockPosQuery a) {this.replace = a; return (T)this;}

        public T log(BlockState a) {this.log = a; return (T)this;}

        public T leaves(BlockState a) {this.leaves = a; return (T)this;}

        public T vine(BlockState a)
        {
            this.vine = a;
            return (T)this;
        }
        public T hanging(BlockState a)
        {
            this.hanging = a;
            return (T)this;
        }
        public T trunkFruit(BlockState a)
        {
            this.trunkFruit = a;
            return (T)this;
        }

        public T altLeaves(BlockState a) {this.altLeaves = a; return (T)this;}

        public T minHeight(int a) {this.minHeight = a; return (T)this;}
        public T maxHeight(int a) {this.maxHeight = a; return (T)this;}

        abstract F create();
    }

    protected final IBlockPosQuery placeOn;
    protected final IBlockPosQuery replace;

    protected final BlockState log;
    protected final BlockState leaves;
    protected final BlockState altLeaves;
    protected final BlockState vine;
    protected final BlockState hanging;
    protected final BlockState trunkFruit;

    protected final int minHeight;
    protected final int maxHeight;

    protected Property logAxisProperty;

    protected TreeFeatureBase(IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(BaseTreeFeatureConfig.CODEC.stable());

        this.placeOn = placeOn;
        this.replace = replace;
        this.log = log;
        this.leaves = leaves;
        this.logAxisProperty = BlockUtil.getAxisProperty(log);
        this.altLeaves = altLeaves;
        this.vine = vine;
        this.hanging = hanging;
        this.trunkFruit = trunkFruit;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }

    public boolean placeLeaves(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        if (this.replace.matches(world, pos))
        {
            this.placeBlock(world, pos, this.leaves, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    public boolean placeLog(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        return this.placeLog(world, pos, null, changedBlocks, boundingBox);
    }

    public boolean placeLog(IWorld world, BlockPos pos, Direction.Axis axis, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? this.log.setValue(this.logAxisProperty, axis) : this.log;
        if (this.replace.matches(world, pos))
        {
            // Logs must be added to the "changedBlocks" so that the leaves have their distance property updated,
            // preventing incorrect decay
            this.placeBlock(world, pos, directedLog, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    public boolean setVine(IWorld world, Random rand, BlockPos pos, Direction side, int length)
    {
        BlockState vineState = this.vine.getBlock() instanceof VineBlock ? this.vine.setValue(VineBlock.NORTH, Boolean.valueOf(side == Direction.NORTH)).setValue(VineBlock.EAST, Boolean.valueOf(side == Direction.EAST)).setValue(VineBlock.SOUTH, Boolean.valueOf(side == Direction.SOUTH)).setValue(VineBlock.WEST, Boolean.valueOf(side == Direction.WEST)) : this.vine;
        boolean setOne = false;
        while (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos) && length > 0 && rand.nextInt(12) > 0)
        {
            setBlock(world, pos, vineState);
            setOne = true;
            length--;
            pos = pos.below();
        }
        return setOne;
    }

    public boolean setHanging(IWorld world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            setBlock(world, pos, this.hanging);
        }
        return false;
    }

    public boolean setTrunkFruit(IWorld world, BlockPos pos)
    {
        if (this.trunkFruit == null) {return false;}
        if (this.replace.matches(world, pos))
        {
            setBlock(world, pos, this.trunkFruit);
        }
        return false;
    }

    public boolean setAltLeaves(IWorld world, BlockPos pos, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        if (this.replace.matches(world, pos))
        {
            this.placeBlock(world, pos, this.altLeaves, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    @Override
    public boolean doPlace(IWorldGenerationReader reader, Random random, BlockPos pos, Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, MutableBoundingBox boundingBox, BaseTreeFeatureConfig config)
    {
        return place(changedLogs, changedLeaves, (IWorld)reader, random, pos, boundingBox);
    }

    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, IWorld world, Random rand, BlockPos position, MutableBoundingBox boundingBox)
    {
        return false;
    }

    protected boolean placeBlock(IWorld world, BlockPos pos, BlockState state, Set<BlockPos> changedBlocks, MutableBoundingBox boundingBox)
    {
        if (!isFree(world, pos))
        {
            return false;
        }
        else
        {
            setBlock(world, pos, state, boundingBox);
            changedBlocks.add(pos.immutable());
            return true;
        }
    }

    protected static void setBlock(IWorldWriter world, BlockPos pos, BlockState state, MutableBoundingBox boundingBox)
    {
        setBlockKnownShape(world, pos, state);
        boundingBox.expand(new MutableBoundingBox(pos, pos));
    }
}
