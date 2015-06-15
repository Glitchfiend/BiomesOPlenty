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
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorCypressTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorCypressTree> implements IGeneratorBuilder<GeneratorCypressTree>
    {
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 6;
            this.maxHeight = 12;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = Blocks.vine.getDefaultState();  
        }

        @Override
        public GeneratorCypressTree create() {
            return new GeneratorCypressTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.minHeight, this.maxHeight);
        }
        
    }
    
    
    public GeneratorCypressTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, minHeight, maxHeight);
    }
    
    
    public boolean checkSpace(World world, BlockPos pos, int baseHeight, int height)
    {
        for (int y = 0; y <= height; y++)
        {
            // require 3x3 for the leaves, 1x1 for the trunk
            int radius = (y <= baseHeight ? 0 : 1);
            
            for (int x = -radius; x <= radius; x++)
            {
                for (int z = -radius; z <= radius; z++)
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
    
    // generates a layer of leafs, starting with radius minRadius and increasing to maxRadius, returns the next blockpos
    public void generateLeafLayer(World world, Random rand, BlockPos pos, int radius)
    {
        for (int x = -radius; x <= radius; x++)
        {
            for (int z = -radius; z <= radius; z++)
            {
                this.setLeaves(world, pos.add(x, 0, z));
            }
        }
        // add the trunk in the middle
        this.setLog(world, pos.add(0, 0, 0));
    }
    
    
    // generate the top of the tree (3 blocks)
    public void generateTop(World world, BlockPos pos)
    {
        for(int x = -1; x <= 1; x++)
        {
            for(int z = -1; z <= 1; z++)
            {
                this.setLeaves(world, pos.add(x, 0, z));
            }
        }
        this.setLog(world, pos);
        
        pos = pos.up();
        this.setLeaves(world, pos);
        this.setLeaves(world, pos.north());
        this.setLeaves(world, pos.east());
        this.setLeaves(world, pos.south());
        this.setLeaves(world, pos.west());
        this.setLeaves(world, pos.up());
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
        int topHeight = 3;
        int baseHeight = 1 + random.nextInt(3);
        int leavesHeight = height - topHeight - baseHeight;
        if (leavesHeight < 0) {return false;}
        int leavesRadius = 1;
        
        // Start on the space above ground
        BlockPos pos = startPos.up();
        
        if (!this.checkSpace(world, pos, baseHeight, height))
        {
            // Abandon if there isn't enough room
            return false;
        }

        // Generate bottom of tree (trunk only)
        for(int i = 0; i < baseHeight; i++)
        {
            this.setLog(world, pos);
            pos = pos.up();
        }
        
        // Generate middle of tree
        int minRadius = 0;
        int radius = random.nextInt(2);
        int sectionRadius = 1;
        for (int y = 0; y < leavesHeight; y++)
        {
            this.generateLeafLayer(world, random, pos, radius);
            if (radius >= sectionRadius)
            {
                radius = minRadius;
                if (minRadius == 0) {minRadius = 1;}
                if (sectionRadius < leavesRadius) {sectionRadius++;}
            } else {
                radius++;
            }
            pos = pos.up();
        }
        
        // Generate top of tree
        this.generateTop(world, pos);
        
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
    }

}