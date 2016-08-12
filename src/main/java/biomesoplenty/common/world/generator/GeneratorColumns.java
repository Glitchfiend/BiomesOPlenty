/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorColumns extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorColumns> implements IGeneratorBuilder<GeneratorColumns>
    {
        protected int minHeight;
        protected int maxHeight;
        protected int generationAttempts;
        protected boolean randomDirection;
        
        public Builder minHeight(int a) {this.minHeight = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        public Builder randomDirection(boolean a) {this.randomDirection = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.GROUND, Material.GRASS);
            this.replace = BlockQueries.airOrLeaves;
            this.with = Blocks.COBBLESTONE.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.minHeight = 2;
            this.maxHeight = 4;
            this.generationAttempts = 12;
            this.randomDirection = false;
        }

        @Override
        public GeneratorColumns create()
        {
            return new GeneratorColumns(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minHeight, this.maxHeight, this.generationAttempts, this.randomDirection);
        }
    }
    
    protected int minHeight;
    protected int maxHeight;
    protected int generationAttempts;
    protected boolean randomDirection;

    public GeneratorColumns(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minHeight, int maxHeight, int generationAttempts, boolean randomDirection)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.generationAttempts = generationAttempts;
        this.randomDirection = randomDirection;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
                
            if (this.randomDirection == true)
            {
            	int randDirection = rand.nextInt(3);
            	
            	if (randDirection == 0)
            	{
	            	// see if we can place the column
		            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos))
		            {
		                // choose random target height
		                int targetHeight = GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);
		                
		                // keep placing blocks upwards (if there's room)
		                for (int height = targetHeight; height >= 0 && replace.matches(world, genPos); height--)
		                {
		                	if (this.with.getBlock().canPlaceBlockAt(world, genPos))
		                	{
			                    world.setBlockState(genPos, this.with);
			                    genPos = genPos.up();
		                	}
		                	else
		                	{
		                		return false;
		                	}
		                }
		            }
            	}
            	else
            	{
            		// see if we can place the column
    	            if (this.placeOn.matches(world, genPos.up()) && this.replace.matches(world, genPos))
    	            {
    	                // choose random target height
    	                int targetHeight = GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);
    	                
    	                // keep placing blocks upwards (if there's room)
    	                for (int height = 0; height <= targetHeight && replace.matches(world, genPos); height++)
    	                {
    	                	if (this.with.getBlock().canPlaceBlockAt(world, genPos))
		                	{
	    	                    world.setBlockState(genPos, this.with);
	    	                    genPos = genPos.down();
		                	}
    	                	else
    	                	{
    	                		return false;
    	                	}
    	                }
    	            }
            	}
            }
            else
            {
            	// see if we can place the column
	            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos))
	            {
	                // choose random target height
	                int targetHeight = GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);
	                
	                // keep placing blocks upwards (if there's room)
	                for (int height = targetHeight; height >= 0 && replace.matches(world, genPos); height--)
	                {
	                	if (this.with.getBlock().canPlaceBlockAt(world, genPos))
	                	{
		                    world.setBlockState(genPos, this.with);
		                    genPos = genPos.up();
	                	}
	                	else
	                	{
	                		return false;
	                	}
	                }
	            }
            }
        }
        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {          
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.with = conf.getBlockState("with", this.with);
        this.minHeight = conf.getInt("minHeight", this.minHeight);
        this.maxHeight = conf.getInt("maxHeight", this.maxHeight);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        this.randomDirection = conf.getBool("randomDirection", this.randomDirection);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
    

}