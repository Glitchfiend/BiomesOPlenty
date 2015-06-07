/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockPosQueryAnything;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockPosQuery;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockPosQueryOr;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryState;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorSplatter extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorSplatter>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockPosQuery placeOn = new BlockPosQueryAnything();
        protected IBlockPosQuery replace = new BlockPosQueryAnything();
        protected IBlockState with = Blocks.stone.getDefaultState();
        protected int generationAttempts = 32;
        protected ScatterYMethod scatterYMethod = ScatterYMethod.AT_SURFACE;
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder placeOn(IBlockPosQuery a) {this.placeOn = a; return this;}
        public Builder placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQueryUtils.parseQueryString(a); return this;}
        public Builder placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this;}
        public Builder placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQueryUtils.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}
        public Builder with(IBlockState a) {this.with = a; return this;}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this;}
        public Builder scatterYMethod(ScatterYMethod a) {this.scatterYMethod = a; return this;}

        @Override
        public GeneratorSplatter create()
        {
            return new GeneratorSplatter(this.amountPerChunk, this.placeOn, this.replace, this.with, this.generationAttempts, this.scatterYMethod);
        }
    }
    
    
    private static IBlockPosQuery isLeavesOrAir = new BlockPosQueryOr(new BlockQueryMaterial(Material.leaves), new BlockQueryMaterial(Material.air));
    
    protected IBlockPosQuery placeOn;
    protected IBlockPosQuery replace;
    protected IBlockState with;
    protected int generationAttempts;
    protected ScatterYMethod scatterYMethod;
    
    public GeneratorSplatter(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, int generationAttempts, ScatterYMethod scatterYMethod)
    {
        super(amountPerChunk);
        this.placeOn = placeOn;
        this.replace = replace;
        this.with = with;
        this.generationAttempts = generationAttempts;
        this.scatterYMethod = scatterYMethod;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return this.scatterYMethod.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {         
        // if we're in the air, move down until we're not
        while (pos.getY() > 0 && isLeavesOrAir.matches(world, pos))
        {
            pos = pos.down();
        }

        // look for blocks on the surface matching this.placeOn and randomly put this.to on top of them
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