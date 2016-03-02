/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorSplatter extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorSplatter> implements IGeneratorBuilder<GeneratorSplatter>
    {
        protected int generationAttempts;

        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = BlockQueries.breakable;
            this.with = Blocks.stone.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.generationAttempts = 64;
        }

        @Override
        public GeneratorSplatter create()
        {
            return new GeneratorSplatter(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.generationAttempts);
        }
    }    
    
    protected int generationAttempts;
    
    public GeneratorSplatter(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {         
        // look for blocks on the matching this.placeOn and randomly put this.to on top of them
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (this.replace.matches(world, pos1) && this.placeOn.matches(world, pos1.down()))
            {
                world.setBlockState(pos1, this.with);
            }
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.with = conf.getBlockState("with", this.with);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
    }
    
    
}