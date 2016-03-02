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
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
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
        
        public Builder minHeight(int a) {this.minHeight = a; return this.self();}
        public Builder maxHeight(int a) {this.maxHeight = a; return this.self();}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = new BlockQueryMaterial(Material.ground, Material.grass);
            this.replace = BlockQueries.airOrLeaves;
            this.with = Blocks.cobblestone.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.minHeight = 2;
            this.maxHeight = 4;
            this.generationAttempts = 12;
        }

        @Override
        public GeneratorColumns create()
        {
            return new GeneratorColumns(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.minHeight, this.maxHeight, this.generationAttempts);
        }
    }
    
    protected int minHeight;
    protected int maxHeight;
    protected int generationAttempts;

    public GeneratorColumns(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int minHeight, int maxHeight, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(rand.nextInt(4) - rand.nextInt(4), rand.nextInt(3) - rand.nextInt(3), rand.nextInt(4) - rand.nextInt(4));
                
            // see if we can place the column
            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos))
            {
                // choose random target height
                int targetHeight = GeneratorUtils.nextIntBetween(rand, this.minHeight, this.maxHeight);
                
                // keep placing blocks upwards (if there's room)
                for (int height = targetHeight; height >= 0 && replace.matches(world, genPos); height--)
                {
                    world.setBlockState(genPos, this.with);
                    genPos = genPos.up();
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
        this.placeOn = conf.getBlockPosQuery("placeOn", this.placeOn);
    }
    

}