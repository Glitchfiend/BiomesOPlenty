/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockNewLog;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSapling;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorTwigletTree extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorTwigletTree>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockState log = Blocks.log.getDefaultState();
        protected IBlockState leaves = Blocks.leaves.getDefaultState();
        protected int minHeight = 2;
        protected int maxHeight = 3;
        protected float leafChance = 0.9F;
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}
        public Builder leafChance(float a) {this.leafChance = a; return this;}
        public Builder log(IBlockState a) {this.log = a; return this;}
        public Builder log(BOPWoods a) {this.log = BlockBOPLog.paging.getVariantState(a); return this;}
        public Builder log(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.log = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, a);
            } else {
                this.log = Blocks.log2.getDefaultState().withProperty(BlockNewLog.VARIANT, a);
            }
            return this;
        }
        public Builder leaves(IBlockState a) {this.leaves = a; return this;}
        public Builder leaves(BOPTrees a) {this.leaves = BlockBOPLeaves.paging.getVariantState(a); return this;}
        public Builder leaves(BlockPlanks.EnumType a)
        {
            if (a.getMetadata() < 4)
            {
                this.leaves = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                this.leaves = Blocks.leaves2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, a);
            }
            return this;
        }

        @Override
        public GeneratorTwigletTree create()
        {
            return new GeneratorTwigletTree(this.amountPerChunk, this.minHeight, this.maxHeight, this.leafChance, this.log, this.leaves);
        }
    }
    
    private int minHeight;
    private int maxHeight;
    private float leafChance;
    private IBlockState log;
    private IBlockState leaves;
    
    public GeneratorTwigletTree(float amountPerChunk, int minHeight, int maxHeight, float leafChance, IBlockState log, IBlockState leaves)
    {
        super(amountPerChunk);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.leafChance = leafChance;
        this.log = log;
        this.leaves = leaves;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }
    
    public boolean setLeaves(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock().canBeReplacedByLeaves(world, pos))
        {
            world.setBlockState(pos, this.leaves, 2);
            return true;
        }
        return false;
    }
    
    public boolean setLog(World world, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock().canBeReplacedByLeaves(world, pos))
        {
            world.setBlockState(pos, this.log, 2);
            return true;
        }
        return false;
    }
    

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block;

        //Move down until we reach the ground
        do
        {
            block = world.getBlockState(pos).getBlock();
            if (!block.isAir(world, pos) && !block.isLeaves(world, pos)) break;
            pos = pos.down();
        } 
        while (pos.getY() > 0);

        //Check if the ground block can sustain a sapling before generating above it
        if (!block.canSustainPlant(world, pos, EnumFacing.UP, ((BlockSapling)Blocks.sapling)))
        {
            return false;
        }
        
        // choose a random height
        int height = this.minHeight + random.nextInt(1 + this.maxHeight - this.minHeight);
        // start from the block above the ground block
        pos = pos.up();
        
        // add log and leaves on each level
        for (int y = 0; y < height; y++)
        {
            if (!this.setLog(world, pos.up(y)))
            {
                // abandon if the log can't grow
                return true;
            }
            if (y == 0) {continue;} // no leaves on bottom level
            if (random.nextFloat() < this.leafChance) {this.setLeaves(world, pos.add(1, y, 0));}
            if (random.nextFloat() < this.leafChance) {this.setLeaves(world, pos.add(-1, y, 0));}
            if (random.nextFloat() < this.leafChance) {this.setLeaves(world, pos.add(0, y, 1));}
            if (random.nextFloat() < this.leafChance) {this.setLeaves(world, pos.add(0, y, -1));}
        }
        // finish with leaves on top
        this.setLeaves(world, pos.add(0, height, 0));

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("maxHeight", this.maxHeight);
        this.leafChance = conf.getFloat("leafChance", this.leafChance);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
    
}
