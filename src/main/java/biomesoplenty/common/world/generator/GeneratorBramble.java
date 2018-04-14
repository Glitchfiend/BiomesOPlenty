/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.generator;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorBramble extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorBramble> implements IGeneratorBuilder<GeneratorBramble>
    {
        protected int minLength;
        protected int maxLength;
        protected int maxHeight;
        protected int generationAttempts;
        
        public Builder minLength(int a) {this.minLength = a; return this.self();}
        public Builder maxLength(int a) {this.maxLength = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.GROUND, Material.GRASS);
            this.replace = BlockQueries.airOrLeaves;
            this.with = BOPBlocks.bramble_plant.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.minLength = 15;
            this.maxLength = 30;
            this.maxHeight = 6;
            this.generationAttempts = 128;
        }

        @Override
        public GeneratorBramble create()
        {
            return new GeneratorBramble(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minLength, this.maxLength, this.maxHeight, this.generationAttempts);
        }
    }
    
    protected int minLength;
    protected int maxLength;
    protected int maxHeight;
    protected int generationAttempts;

    public GeneratorBramble(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minLength, int maxLength, int maxHeight, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minLength = minLength;
        this.maxLength = maxLength;
        this.maxHeight = maxHeight;
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
                
            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos))
            {
                int targetLength = GeneratorUtils.nextIntBetween(rand, this.minLength, this.maxLength);
                int height = 0;
                int direction = rand.nextInt(4) + 2;
                
                for (int length = 0; length <= targetLength && replace.matches(world, genPos); length++)
                {  
                	if (this.with.getBlock().canPlaceBlockAt(world, genPos))
                	{
	                    world.setBlockState(genPos, this.with);
	                    
	                    if (rand.nextInt(2) == 0)
	                	{
	            	    	direction = rand.nextInt(4) + 2;
	                	}
	                    
	                    if (rand.nextInt(2) == 0)
	                    {
	                    	int leafDirection = rand.nextInt(6);
	                    	if (world.isAirBlock(genPos.offset(EnumFacing.values()[leafDirection])))
	                    	{
	                    		world.setBlockState(genPos.offset(EnumFacing.values()[leafDirection]), Blocks.LEAVES.getDefaultState().withProperty(BlockLeaves.CHECK_DECAY, false));
	                    	}
	                    }
	                	
            	    	switch (rand.nextInt(6))
            	    	{
            	    		case 0: case 1:
            	    			if (height <= this.maxHeight)
            	    			{
	            	    			genPos = genPos.up();
	            	    			height++;
            	    			}
            	    			break;
            	    		case 2:
            	    			if (height >= 0)
            	    			{
	            	    			genPos = genPos.down();
	            	    			height--;
            	    			}
            	    			break;
            	    		default:
            	    			genPos = genPos.offset(EnumFacing.values()[direction]);
            	    			break;
            	    	}
                	}
                	else
                	{
                		return false;
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
        this.minLength = conf.getInt("minLength", this.minLength);
        this.maxLength = conf.getInt("maxLength", this.maxLength);
        this.maxHeight = conf.getInt("maxHeight", this.maxHeight);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
    

}