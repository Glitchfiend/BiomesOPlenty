/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;
import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockOldLog;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.util.MathHelper;
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
            this.log = Blocks.log.getDefaultState().withProperty(BlockOldLog.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.leaves = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, BlockPlanks.EnumType.SPRUCE);
            this.vine = Blocks.vine.getDefaultState();
            this.trunkWidth = 1;
        }

        @Override
        public GeneratorTaigaTree create() {
            return new GeneratorTaigaTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.minHeight, this.maxHeight, this.trunkWidth);
        }
        
    }
    
    private int trunkStart;
    private int trunkEnd;
    
    public GeneratorTaigaTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, int minHeight, int maxHeight, int trunkWidth)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, minHeight, maxHeight);
        this.setTrunkWidth(trunkWidth);
    }
    
    private void setTrunkWidth(int trunkWidth)
    {
        this.trunkStart = MathHelper.ceiling_double_int(0.25D - trunkWidth / 2.0D);
        this.trunkEnd = MathHelper.floor_double(0.25D + trunkWidth / 2.0D);
    }
    
    public boolean checkSpace(World world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            
            // require 3x3 for the leaves, 1x1 for the trunk
            int start = (y <= baseHeight ? this.trunkStart : this.trunkStart - 1);
            int end = (y <= baseHeight ? this.trunkEnd : this.trunkEnd + 1);
            
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
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int leavesRadius)
    {
        int start = this.trunkStart - leavesRadius;
        int end = this.trunkEnd + leavesRadius;
        
        for (int x = start; x <= end; x++)
        {
            for (int z = start; z <= end; z++)
            {
                // skip corners
                if ((leavesRadius > 0 ) && (x == start || x == end) && (z == start || z == end)) {continue;}
                int distFromTrunk = (x < 0 ? this.trunkStart - x : x - this.trunkEnd) + (z < 0 ? this.trunkStart - z : z - this.trunkEnd);
                
                // set leaves as long as it's not too far from the trunk to survive
                if (distFromTrunk <=4) {this.setLeaves(world, pos.add(x, 0, z));}
            }
        }
    }
    
    
    @Override
    public boolean generate(World world, Random random, BlockPos startPos)
    {
        
        // Move down until we reach the ground
        while (startPos.getY() > 1 && world.isAirBlock(startPos) || world.getBlockState(startPos).getBlock().isLeaves(world, startPos)) {startPos = startPos.down();}
        
        if (!this.placeOn.matches(world, startPos))
        {
            // Abandon if we can't place the tree on this block
            return false;
        }
        
        // Choose heights
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        int baseHeight = GeneratorUtils.nextIntBetween(random, height / 6, height / 3);
        int leavesHeight = height - baseHeight;
        if (leavesHeight < 3) {return false;}
        int leavesMaxRadius = (leavesHeight > 6 ? 3 : 2);
        
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
        int localMinRadius = 0;
        int radius = 0;
        int localMaxRadius = 1;
        for (int i = 0; i < leavesHeight; i++)
        {
            this.generateLeafLayer(world, random, pos, radius);
            if (radius < localMaxRadius)
            {
                radius++; 
            } else {
                radius = localMinRadius;
                if (localMinRadius == 0) {localMinRadius = 1;}
                if (localMaxRadius < leavesMaxRadius) {localMaxRadius++;}
            }
            pos = pos.down();
        }
        
        // Generate the trunk
        for (int y = 0; y < height - 1; y++)
        {
            for (int x = this.trunkStart; x <= this.trunkEnd; x++)
            {
                for (int z = this.trunkStart; z <= this.trunkEnd; z++)
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
        this.setTrunkWidth(conf.getInt("trunkWidth", this.trunkEnd - this.trunkStart));
    }

}