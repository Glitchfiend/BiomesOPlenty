/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorTwigletTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorTwigletTree> implements IGeneratorBuilder<GeneratorTwigletTree>
    {
        protected float leafChance;
        
        public Builder leafChance(float a) {this.leafChance = a; return this.self();}
        
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 2;
            this.maxHeight = 6;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = null;
            this.leafChance = 0.9F;
        }        

        @Override
        public GeneratorTwigletTree create() {
            return new GeneratorTwigletTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.minHeight, this.maxHeight, this.leafChance);
        }
    }
    
    private float leafChance;
    
    public GeneratorTwigletTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, int minHeight, int maxHeight, float leafChance)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, minHeight, maxHeight);
        this.leafChance = leafChance;
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
        
        // choose a random height
        int height = this.minHeight + random.nextInt(1 + this.maxHeight - this.minHeight);
        int baseHeight = height / 3;
        
        // start from the block above the ground block
        BlockPos pos = startPos.up();
        
        // add log and leaves on each level
        for (int y = 0; y < height; y++)
        {
            if (!this.setLog(world, pos.up(y)))
            {
                // abandon if the log can't grow
                return true;
            }
            if (y <= baseHeight) {continue;} // no leaves below base height
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
