/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator.tree;
import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.biome.GeneratorUtils;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;

public class GeneratorTaigaTree extends GeneratorTreeBase
{
    // TODO: fruit
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorTaigaTree> implements IGeneratorBuilder<GeneratorTaigaTree>
    {
        protected int trunkWidth;
        
        public Builder trunkWidth(int a) {this.trunkWidth = a; return this.self();}
        
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 6;
            this.maxHeight = 12;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.LOG.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.leaves = Blocks.LEAVES.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.vine = Blocks.VINE.getDefaultState();
            this.hanging = null;
            this.trunkFruit = null;
            this.altLeaves = null;
            this.trunkWidth = 1;
        }

        @Override
        public GeneratorTaigaTree create() {
            return new GeneratorTaigaTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.trunkFruit, this.altLeaves, this.minHeight, this.maxHeight, this.trunkWidth);
        }
        
    }
    
    private int trunkWidth;
    
    public GeneratorTaigaTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState trunkFruit, IBlockState altLeaves, int minHeight, int maxHeight, int trunkWidth)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, trunkFruit, altLeaves, minHeight, maxHeight);
        this.trunkWidth = trunkWidth;
    }
    
    public boolean checkSpace(World world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            
            int trunkWidth = (this.trunkWidth * (height - y) / height) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);
            
            // require 3x3 for the leaves, 1x1 for the trunk
            int start = (y <= baseHeight ? trunkStart : trunkStart - 1);
            int end = (y <= baseHeight ? trunkEnd : trunkEnd + 1);
            
            for (int x = start; x <= end; x++)
            {
                for (int z = start; z <= end; z++)
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
    
    // generates a layer of leafs
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int leavesRadius, int trunkStart, int trunkEnd)
    {
        int start = trunkStart - leavesRadius;
        int end = trunkEnd + leavesRadius;
        
        for (int x = start; x <= end; x++)
        {
            for (int z = start; z <= end; z++)
            {
                // skip corners
                if ((leavesRadius > 0 ) && (x == start || x == end) && (z == start || z == end)) {continue;}
                int distFromTrunk = (x < 0 ? trunkStart - x : x - trunkEnd) + (z < 0 ? trunkStart - z : z - trunkEnd);
                
                // set leaves as long as it's not too far from the trunk to survive
                if (distFromTrunk < 4 || (distFromTrunk == 4 && rand.nextInt(2) == 0))
                {
                    this.setLeaves(world, pos.add(x, 0, z));
                }
            }
        }
    }
    
    public void generateBranch(World world, Random rand, BlockPos pos, EnumFacing direction, int length)
    {
        EnumFacing.Axis axis = direction.getAxis();
        EnumFacing sideways = direction.rotateY();
        for (int i = 1; i <= length; i++)
        {
            BlockPos pos1 = pos.offset(direction, i);
            int r = (i == 1 || i == length) ? 1 : 2;
            for (int j = -r; j <= r; j++)
            {
                if (i < length || rand.nextInt(2) == 0)
                {
                    this.setLeaves(world, pos1.offset(sideways, j));
                }
            }
            if (length - i > 2)
            {
                this.setLeaves(world, pos1.up());
                this.setLeaves(world, pos1.up().offset(sideways, -1));
                this.setLeaves(world, pos1.up().offset(sideways, 1));
                this.setLog(world, pos1, axis);
            }
        }
    }
    
    
    @Override
    public boolean generate(World world, Random random, BlockPos startPos)
    {
        
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getBlock().isLeaves(world.getBlockState(startPos), world, startPos)) {startPos = startPos.down();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        int baseHeight = GeneratorUtils.nextIntBetween(random, height / 5, height / 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}
        
        if (!this.checkSpace(world, startPos.up(), baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }
        
        // Start at the top of the tree
        BlockPos pos = startPos.up(height);
        
        // Leaves at the top
        this.setLeaves(world, pos);
        pos.down();
        
        // Add layers of leaves
        for (int i = 0; i < leavesHeight; i++)
        {
            
            int trunkWidth = (this.trunkWidth * i / height) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);
            
            
            int radius = Math.min(Math.min((i + 2) / 3, 3 + (leavesHeight - i)), 6);
            if (radius == 0)
            {
                this.setLeaves(world, pos);
            }
            else if (radius < 4)
            {
                // for smallish radius, do simple leaf layers
                if (i % 2 == 0)
                {
                    this.generateLeafLayer(world, random, pos, radius, trunkStart, trunkEnd);
                }
                else
                {
                    this.generateLeafLayer(world, random, pos, radius / 2, trunkStart, trunkEnd);
                }
            }
            else
            {
                // for bigger radius, need branches
                if (i % 2 == 0)
                {
                    this.generateBranch(world, random, pos.add(trunkStart, 0, trunkStart), EnumFacing.NORTH, radius);
                    this.generateBranch(world, random, pos.add(trunkEnd, 0, trunkStart), EnumFacing.EAST, radius);
                    this.generateBranch(world, random, pos.add(trunkEnd, 0, trunkEnd), EnumFacing.SOUTH, radius);
                    this.generateBranch(world, random, pos.add(trunkStart, 0, trunkEnd), EnumFacing.WEST, radius);
                }
            }
            pos = pos.down();
        }
        
        // Generate the trunk
        for (int y = 0; y < height - 1; y++)
        {
            int trunkWidth = (this.trunkWidth * (height - y) / height) + 1;
            int trunkStart = MathHelper.ceil(0.25D - trunkWidth / 2.0D);
            int trunkEnd = MathHelper.floor(0.25D + trunkWidth / 2.0D);
            
            // TODO: Temporary fix for trees generating larger than normal bases when in the sides of hills
            // Should look into doing this properly but i'm busy :P
            if (this.trunkWidth <= 1)
            {
                trunkStart = 0; 
                trunkEnd = 0;
            }
            
            for (int x = trunkStart; x <= trunkEnd; x++)
            {
                for (int z = trunkStart; z <= trunkEnd; z++)
                {
                    this.setLog(world, startPos.add(x, y, z));
                }
            }
        }
        
        return true;
    }
    
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("minHeight", this.maxHeight);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
        this.vine = conf.getBlockState("vinesState", this.vine);
        this.trunkWidth = conf.getInt("trunkWidth", this.trunkWidth);
    }

}