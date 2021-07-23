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
import biomesoplenty.common.world.gen.BOPFeatureUtil;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.LevelSimulatedRW;
import net.minecraft.world.level.LevelWriter;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.BushBlock;
import net.minecraft.world.level.block.SaplingBlock;
import net.minecraft.world.level.block.VineBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.properties.Property;
import net.minecraft.world.level.levelgen.feature.TreeFeature;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.structure.BoundingBox;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

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
            this.placeOn = BOPFeatureUtil::isSoil;
            this.replace = (world, pos) -> TreeFeature.isAirOrLeaves(world, pos) || world.getBlockState(pos).is(BlockTags.SAPLINGS) || world.getBlockState(pos).getBlock() == Blocks.VINE || world.getBlockState(pos).getBlock() == BOPBlocks.WILLOW_VINE || world.getBlockState(pos).getBlock() == BOPBlocks.DEAD_BRANCH || world.getBlockState(pos).getBlock() instanceof BushBlock;
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
        super(TreeConfiguration.CODEC.stable());

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

    public boolean placeLeaves(LevelAccessor world, BlockPos pos, Set<BlockPos> changedBlocks, BoundingBox boundingBox)
    {
        if (this.replace.matches(world, pos))
        {
            this.placeBlock(world, pos, this.leaves, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    public boolean placeLog(LevelAccessor world, BlockPos pos, Set<BlockPos> changedBlocks, BoundingBox boundingBox)
    {
        return this.placeLog(world, pos, null, changedBlocks, boundingBox);
    }

    public boolean placeLog(LevelAccessor world, BlockPos pos, Direction.Axis axis, Set<BlockPos> changedBlocks, BoundingBox boundingBox)
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

    public boolean setVine(LevelAccessor world, Random rand, BlockPos pos, Direction side, int length)
    {
        BlockState vineState = this.vine.getBlock() instanceof VineBlock ? this.vine.setValue(VineBlock.NORTH, Boolean.valueOf(side == Direction.NORTH)).setValue(VineBlock.EAST, Boolean.valueOf(side == Direction.EAST)).setValue(VineBlock.SOUTH, Boolean.valueOf(side == Direction.SOUTH)).setValue(VineBlock.WEST, Boolean.valueOf(side == Direction.WEST)) : this.vine;
        boolean setOne = false;
        while (world.getBlockState(pos).isAir() && length > 0 && rand.nextInt(12) > 0)
        {
            setBlock(world, pos, vineState);
            setOne = true;
            length--;
            pos = pos.below();
        }
        return setOne;
    }

    public boolean setHanging(LevelAccessor world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            setBlock(world, pos, this.hanging);
        }
        return false;
    }

    public boolean setTrunkFruit(LevelAccessor world, BlockPos pos)
    {
        if (this.trunkFruit == null) {return false;}
        if (this.replace.matches(world, pos))
        {
            setBlock(world, pos, this.trunkFruit);
        }
        return false;
    }

    public boolean setAltLeaves(LevelAccessor world, BlockPos pos, Set<BlockPos> changedBlocks, BoundingBox boundingBox)
    {
        if (this.replace.matches(world, pos))
        {
            this.placeBlock(world, pos, this.altLeaves, changedBlocks, boundingBox);
            return true;
        }
        return false;
    }

    @Override
    public boolean doPlace(WorldGenLevel reader, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> biConsumer, BiConsumer<BlockPos, BlockState> biConsumer2, TreeConfiguration config)
    {
        // TODO: sets, add properly
        return place(new HashSet<>(), new HashSet<>(), reader, random, pos, BoundingBox.infinite());
    }



    protected boolean place(Set<BlockPos> changedLogs, Set<BlockPos> changedLeaves, LevelAccessor world, Random rand, BlockPos position, BoundingBox boundingBox)
    {
        return false;
    }

    protected boolean placeBlock(LevelAccessor world, BlockPos pos, BlockState state, Set<BlockPos> changedBlocks, BoundingBox boundingBox)
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

    protected static void setBlock(LevelWriter world, BlockPos pos, BlockState state, BoundingBox boundingBox)
    {
        world.setBlock(pos, state, 19);
        boundingBox.encapsulate(new BoundingBox(pos));
    }
}
