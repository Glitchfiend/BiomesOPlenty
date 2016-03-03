/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockLog;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockVine;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GeneratorTreeBase extends BOPGeneratorBase
{
    protected IBlockPosQuery placeOn;
    protected IBlockPosQuery replace;
    protected IBlockState log;
    protected IBlockState leaves;
    protected IBlockState vine;
    protected IBlockState hanging;
    protected IBlockState altLeaves;
    protected int minHeight;
    protected int maxHeight;
    protected IProperty logAxisProperty;
    
    protected GeneratorTreeBase(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight) {
        super(amountPerChunk);
        this.placeOn = placeOn;
        this.replace = replace;
        this.log = log;
        this.leaves = leaves;
        this.vine = vine;
        this.hanging = hanging;
        this.altLeaves = altLeaves;
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.logAxisProperty = GeneratorUtils.getAxisProperty(log);
    }

    protected static abstract class InnerBuilder<T extends InnerBuilder<T, G>, G extends GeneratorTreeBase> extends BOPGeneratorBase.InnerBuilder<T, G>
    {        
        protected IBlockPosQuery placeOn;
        protected IBlockPosQuery replace;
        protected IBlockState log;
        protected IBlockState leaves;
        protected IBlockState vine;
        protected IBlockState hanging;
        protected IBlockState altLeaves;
        protected int minHeight;
        protected int maxHeight;
        
        public T placeOn(IBlockPosQuery a) {this.placeOn = a; return this.self();}
        public T placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this.self();}
        public T placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this.self();}
        public T placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this.self();}
        public T placeOn(Material... a) {this.placeOn = new BlockQueryMaterial(a); return this.self();}
    
        public T replace(IBlockPosQuery a) {this.replace = a; return this.self();}
        public T replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this.self();}
        public T replace(Block a) {this.replace = new BlockQueryBlock(a); return this.self();}
        public T replace(IBlockState a) {this.replace = new BlockQueryState(a); return this.self();}
        public T replace(Material... a) {this.replace = new BlockQueryMaterial(a); return this.self();}

        public T log(IBlockState a) {this.log = a; return this.self();}
        public T log(BOPWoods a) {this.log = BlockBOPLog.paging.getVariantState(a); return this.self();}
        public T log(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.log = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, a);
            } else {
                this.log = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, a);
            }
            return this.self();
        }
        public T leaves(IBlockState a) {this.leaves = a; return this.self();}
        public T leaves(BOPTrees a) {this.leaves = BlockBOPLeaves.paging.getVariantState(a).withProperty(BlockLeaves.CHECK_DECAY, false); return this.self();}
        public T leaves(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.leaves = Blocks.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.leaves = Blocks.leaves2.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this.self();
        }
        public T vine(IBlockState a)
        {
            this.vine = a;
            return this.self();
        }
        public T hanging(IBlockState a)
        {
            this.hanging = a;
            return this.self();
        }
        
        public T altLeaves(IBlockState a) {this.altLeaves = a; return this.self();}
        public T altLeaves(BOPTrees a) {this.altLeaves = BlockBOPLeaves.paging.getVariantState(a).withProperty(BlockLeaves.CHECK_DECAY, false); return this.self();}
        public T altLeaves(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.altLeaves = Blocks.leaves.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.altLeaves = Blocks.leaves2.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false).withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this.self();
        }
        
        public T minHeight(int a) {this.minHeight = a; return this.self();}
        public T maxHeight(int a) {this.maxHeight = a; return this.self();}
    
    }
    
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }
    
    public boolean setLeaves(World world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.leaves, 2);
            return true;
        }
        return false;
    }
    
    public boolean setLog(World world, BlockPos pos)
    {
        return this.setLog(world, pos, null);
    }
    
    public boolean setLog(World world, BlockPos pos, EnumFacing.Axis axis)
    {
        IBlockState directedLog = (axis != null && this.logAxisProperty != null) ? this.log.withProperty(this.logAxisProperty, BlockLog.EnumAxis.fromFacingAxis(axis)) : this.log;
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, directedLog, 2);
            return true;
        }
        return false;
    }
    
    public boolean setVine(World world, Random rand, BlockPos pos, EnumFacing side, int length)
    {
        if (this.vine == null) {return false;}
        IBlockState vineState = this.vine.getBlock() instanceof BlockVine ? this.vine.withProperty(BlockVine.NORTH, Boolean.valueOf(side == EnumFacing.NORTH)).withProperty(BlockVine.EAST, Boolean.valueOf(side == EnumFacing.EAST)).withProperty(BlockVine.SOUTH, Boolean.valueOf(side == EnumFacing.SOUTH)).withProperty(BlockVine.WEST, Boolean.valueOf(side == EnumFacing.WEST)) : this.vine;
        boolean setOne = false;
        while (world.getBlockState(pos).getBlock().isAir(world.getBlockState(pos), world, pos) && length > 0 && rand.nextInt(12) > 0)
        {
            world.setBlockState(pos, vineState, 2);
            setOne = true;
            length--;
            pos = pos.down();
        }
        return setOne;
    }
    
    public boolean setHanging(World world, BlockPos pos)
    {
        if (this.hanging == null) {return false;}
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.hanging, 2);
        }
        return false;
    }
    
    public boolean setAltLeaves(World world, BlockPos pos)
    {
        if (this.replace.matches(world, pos))
        {
            world.setBlockState(pos, this.altLeaves, 2);
            return true;
        }
        return false;
    }
}