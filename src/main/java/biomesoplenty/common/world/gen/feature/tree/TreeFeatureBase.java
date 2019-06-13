/*******************************************************************************
 * Copyright 2014-2019, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.world.gen.feature.tree;

import biomesoplenty.common.util.block.BlockUtil;
import biomesoplenty.common.util.block.IBlockPosQuery;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.VineBlock;
import net.minecraft.state.IProperty;
import net.minecraft.util.Direction;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MutableBoundingBox;
import net.minecraft.world.IWorld;
import net.minecraft.world.gen.IWorldGenerationReader;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.NoFeatureConfig;

import java.util.Random;
import java.util.Set;

public abstract class TreeFeatureBase extends AbstractTreeFeature<NoFeatureConfig>
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
        protected boolean updateNeighbours;

        public BuilderBase()
        {
            this.placeOn = (world, pos) -> world.getBlockState(pos).canSustainPlant(world, pos, Direction.UP, (SaplingBlock)Blocks.OAK_SAPLING);
            this.replace = (world, pos) -> world.getBlockState(pos).canBeReplacedByLeaves(world, pos);
            this.log = Blocks.OAK_LOG.getDefaultState();
            this.leaves = Blocks.OAK_LEAVES.getDefaultState();
            this.vine = Blocks.AIR.getDefaultState();
            this.hanging = Blocks.AIR.getDefaultState();
            this.trunkFruit = Blocks.AIR.getDefaultState();
            this.altLeaves = Blocks.AIR.getDefaultState();
            this.updateNeighbours = false;
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

        public T updateNeighbours(boolean a) {this.updateNeighbours = a; return (T)this;}

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

    protected IProperty logAxisProperty;

    protected TreeFeatureBase(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, BlockState log, BlockState leaves, BlockState altLeaves, BlockState vine, BlockState hanging, BlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(NoFeatureConfig::deserialize, notify);

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

    public boolean setLeaves(IWorld world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            this.setBlockState(world, pos, this.leaves);
            return true;
        }
        return false;
    }

    public boolean setLog(Set<BlockPos> changedBlocks, IWorld world, BlockPos pos, MutableBoundingBox boundingBox)
    {
        return this.setLog(changedBlocks, world, pos, null, boundingBox);
    }

    public boolean setLog(Set<BlockPos> changedBlocks, IWorld world, BlockPos pos, Direction.Axis axis, MutableBoundingBox boundingBox)
    {
        BlockState directedLog = (axis != null && this.logAxisProperty != null) ? this.log.with(this.logAxisProperty, axis) : this.log;
        if (this.replace.matches(world, pos))
        {
            // Logs must be added to the "changedBlocks" so that the leaves have their distance property updated,
            // preventing incorrect decay
            this.func_208520_a(changedBlocks, world, pos, directedLog, boundingBox);
            return true;
        }
        return false;
    }

    public boolean setVine(IWorld world, Random rand, BlockPos pos, Direction side, int length)
    {
        BlockState vineState = this.vine.getBlock() instanceof VineBlock ? this.vine.with(VineBlock.NORTH, Boolean.valueOf(side == Direction.NORTH)).with(VineBlock.EAST, Boolean.valueOf(side == Direction.EAST)).with(VineBlock.SOUTH, Boolean.valueOf(side == Direction.SOUTH)).with(VineBlock.WEST, Boolean.valueOf(side == Direction.WEST)) : this.vine;
        boolean setOne = false;
        while (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos) && length > 0 && rand.nextInt(12) > 0)
        {
            this.setBlockState(world, pos, vineState);
            setOne = true;
            length--;
            pos = pos.down();
        }
        return setOne;
    }

    public boolean setHanging(IWorld world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            this.setBlockState(world, pos, this.hanging);
        }
        return false;
    }

    public boolean setTrunkFruit(IWorld world, BlockPos pos)
    {
        if (this.trunkFruit == null) {return false;}
        if (this.replace.matches(world, pos))
        {
            this.setBlockState(world, pos, this.trunkFruit);
        }
        return false;
    }

    public boolean setAltLeaves(IWorld world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            this.setBlockState(world, pos, this.altLeaves);
            return true;
        }
        return false;
    }

    @Override
    protected boolean place(Set<BlockPos> changedBlocks, IWorldGenerationReader world, Random rand, BlockPos position, MutableBoundingBox boundingBox)
    {
        return place(changedBlocks, world, rand, position, boundingBox);
    }

    protected boolean place(Set<BlockPos> changedBlocks, IWorld world, Random rand, BlockPos position, MutableBoundingBox boundingBox)
    {
        return false;
    }
}
