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
import net.minecraft.block.BlockSapling;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.state.IProperty;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;
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
        protected IBlockState log;
        protected IBlockState leaves;
        protected IBlockState vine;
        protected IBlockState hanging;
        protected IBlockState trunkFruit;
        protected IBlockState altLeaves;
        protected int minHeight;
        protected int maxHeight;
        protected boolean updateNeighbours;

        public BuilderBase()
        {
            this.placeOn = (world, pos) -> world.getBlockState(pos).canSustainPlant(world, pos, EnumFacing.UP, (BlockSapling)Blocks.OAK_SAPLING);
            this.replace = (world, pos) -> world.getBlockState(pos).getMaterial() == Material.AIR;
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

        public T log(IBlockState a) {this.log = a; return (T)this;}

        public T leaves(IBlockState a) {this.leaves = a; return (T)this;}

        public T vine(IBlockState a)
        {
            this.vine = a;
            return (T)this;
        }
        public T hanging(IBlockState a)
        {
            this.hanging = a;
            return (T)this;
        }
        public T trunkFruit(IBlockState a)
        {
            this.trunkFruit = a;
            return (T)this;
        }

        public T altLeaves(IBlockState a) {this.altLeaves = a; return (T)this;}

        public T minHeight(int a) {this.minHeight = a; return (T)this;}
        public T maxHeight(int a) {this.maxHeight = a; return (T)this;}

        public T updateNeighbours(boolean a) {this.updateNeighbours = a; return (T)this;}

        abstract F create();
    }

    protected final IBlockPosQuery placeOn;
    protected final IBlockPosQuery replace;

    protected final IBlockState log;
    protected final IBlockState leaves;
    protected final IBlockState altLeaves;
    protected final IBlockState vine;
    protected final IBlockState hanging;
    protected final IBlockState trunkFruit;

    protected final int minHeight;
    protected final int maxHeight;

    protected IProperty logAxisProperty;

    protected TreeFeatureBase(boolean notify, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState altLeaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, int minHeight, int maxHeight)
    {
        super(notify);

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

    public boolean setLog(Set<BlockPos> changedBlocks, IWorld world, BlockPos pos)
    {
        return this.setLog(changedBlocks, world, pos, null);
    }

    public boolean setLog(Set<BlockPos> changedBlocks, IWorld world, BlockPos pos, EnumFacing.Axis axis)
    {
        IBlockState directedLog = (axis != null && this.logAxisProperty != null) ? this.log.with(this.logAxisProperty, axis) : this.log;
        if (this.replace.matches(world, pos))
        {
            // Logs must be added to the "changedBlocks" so that the leaves have their distance property updated,
            // preventing incorrect decay
            this.func_208520_a(changedBlocks, world, pos, directedLog);
            return true;
        }
        return false;
    }

    public boolean setVine(IWorld world, Random rand, BlockPos pos, EnumFacing side, int length)
    {
        IBlockState vineState = this.vine.getBlock() instanceof BlockVine ? this.vine.with(BlockVine.NORTH, Boolean.valueOf(side == EnumFacing.NORTH)).with(BlockVine.EAST, Boolean.valueOf(side == EnumFacing.EAST)).with(BlockVine.SOUTH, Boolean.valueOf(side == EnumFacing.SOUTH)).with(BlockVine.WEST, Boolean.valueOf(side == EnumFacing.WEST)) : this.vine;
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
}
