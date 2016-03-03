/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature.tree;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorTwigletTree extends GeneratorTreeBase
{
    
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorTwigletTree> implements IGeneratorBuilder<GeneratorTwigletTree>
    {
        protected float leafChanceEven;
        protected float leafChanceOdd;
        
        public Builder leafChance(float a) {this.leafChanceEven = a; this.leafChanceOdd = a; return this.self();}
        public Builder leafChance(float a, float b) {this.leafChanceEven = a; this.leafChanceOdd = b; return this.self();}
        
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
            this.hanging = null;
            this.altLeaves = null;
            this.leafChanceEven = 0.2F;
            this.leafChanceOdd = 0.9F;
        }

        @Override
        public GeneratorTwigletTree create() {
            return new GeneratorTwigletTree(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight, this.leafChanceEven, this.leafChanceOdd);
        }
    }
    
    private float leafChanceEven;
    private float leafChanceOdd;
    
    public GeneratorTwigletTree(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight, float leafChanceEven, float leafChanceOdd)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
        this.leafChanceEven = leafChanceEven;
        this.leafChanceOdd = leafChanceOdd;
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
        
        // choose a random height
        int height = this.minHeight + random.nextInt(1 + this.maxHeight - this.minHeight);
        int baseHeight = height / 3;
        
        // start from the block above the ground block
        BlockPos pos = startPos.up();
        
        // add log and leaves on each level
        float leafChance;
        for (int y = 0; y < height; y++)
        {
            if (!this.setLog(world, pos.up(y)))
            {
                // abandon if the log can't grow
                return true;
            }
            leafChance = ((height - y) % 2 == 0) ? this.leafChanceEven : this.leafChanceOdd;
            if (y <= baseHeight) {continue;} // no leaves below base height
            if (random.nextFloat() < leafChance) {this.setLeaves(world, pos.add(1, y, 0));}
            if (random.nextFloat() < leafChance) {this.setLeaves(world, pos.add(-1, y, 0));}
            if (random.nextFloat() < leafChance) {this.setLeaves(world, pos.add(0, y, 1));}
            if (random.nextFloat() < leafChance) {this.setLeaves(world, pos.add(0, y, -1));}
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
        this.leafChanceEven = conf.getFloat("leafChance", this.leafChanceEven);
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
    
}
