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
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBush extends GeneratorTreeBase
{
    public static class Builder extends GeneratorTreeBase.InnerBuilder<Builder, GeneratorBush> implements IGeneratorBuilder<GeneratorBush>
    {   
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.minHeight = 2;
            this.maxHeight = 4;
            this.placeOn = BlockQueries.fertile;
            this.replace = BlockQueries.airOrLeaves;
            this.log = Blocks.log.getDefaultState();
            this.leaves = Blocks.leaves.getDefaultState();
            this.vine = null;
            this.hanging = null;
            this.altLeaves = null;
        }        

        @Override
        public GeneratorBush create() {
            return new GeneratorBush(this.amountPerChunk, this.placeOn, this.replace, this.log, this.leaves, this.vine, this.hanging, this.altLeaves, this.minHeight, this.maxHeight);
        }
    }
    
    
    public GeneratorBush(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState log, IBlockState leaves, IBlockState vine, IBlockState hanging, IBlockState altLeaves, int minHeight, int maxHeight)
    {
        super(amountPerChunk, placeOn, replace, log, leaves, vine, hanging, altLeaves, minHeight, maxHeight);
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
        int height = GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight);
        
        // start from the block above the ground block
        BlockPos pos = startPos.up();        

        //Generate a bush 3 blocks tall, with the bottom block already set to a log
        for (int y = 0; y < height; ++y)
        {
            //Reduces the radius closer to the top of the bush
            int leavesRadius = (height - y > 1 ? 2 : 1);

            for (int x = -leavesRadius; x <= leavesRadius; ++x)
            {
                for (int z = -leavesRadius; z <= leavesRadius; ++z)
                {
                    //Randomly prevent the generation of leaves on the corners of each layer
                    if (Math.abs(x) < leavesRadius || Math.abs(z) < leavesRadius || random.nextInt(2) != 0)
                    {
                    	if (this.altLeaves != null)
                    	{
	                    	if (random.nextInt(4) == 0)
	                    	{
	                    		this.setAltLeaves(world, pos.add(x, y, z));
	                    	}
	                    	else
	                    	{
	                    		this.setLeaves(world, pos.add(x, y, z));
	                    	}
                    	}
                    	else
                    	{
                    		this.setLeaves(world, pos.add(x, y, z));
                    	}
                    }
                }
            }
            
            // log in the center
            if (height - y > 1)
            {
                this.setLog(world, pos.add(0, y, 0));
            }            
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);        
        this.log = conf.getBlockState("logState", this.log);
        this.leaves = conf.getBlockState("leavesState", this.leaves);
    }
    
}
