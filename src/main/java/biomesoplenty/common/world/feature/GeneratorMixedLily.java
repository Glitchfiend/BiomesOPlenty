/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.BlockLilyPad;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorMixedLily extends BOPGeneratorBase
{

    // list of possibly lilypads
    protected static IBlockState[] lilies = new IBlockState[] {
        BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, BlockBOPLilypad.LilypadType.TINY),
        BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, BlockBOPLilypad.LilypadType.SMALL),
        BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, BlockBOPLilypad.LilypadType.MEDIUM),
        Blocks.waterlily.getDefaultState()
    };
    protected static IBlockPosQuery replace = new BlockQueryMaterial(Material.air);
    
    protected int generationAttempts;

    public static class Builder extends BOPGeneratorBase.InnerBuilder<Builder, GeneratorMixedLily> implements IGeneratorBuilder<GeneratorMixedLily>
    {
        protected int generationAttempts;

        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.generationAttempts = 64;
        }
        
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}
        
        @Override
        public GeneratorMixedLily create() {
            return new GeneratorMixedLily(this.amountPerChunk, this.generationAttempts);
        }
    }
    
    public GeneratorMixedLily(float amountPerChunk, int generationAttempts)
    {
        super(amountPerChunk);
        this.generationAttempts = generationAttempts;
        
        // make sure that all of the liles are the right type
        for (IBlockState lily : lilies) {
            if (!(lily.getBlock() instanceof BlockLilyPad))
            {
                throw new RuntimeException("GeneratorMixedLily liles must all be instances of BlockLilyPad");
            }
        }
    }
    
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));
            
            // pick a random lily to place
            IBlockState lily = lilies[random.nextInt(lilies.length)];
            
            // note unchecked cast below should be safe because lily types are all checked during class initialisation
            if (replace.matches(world, genPos) && ((BlockLilyPad)lily.getBlock()).canBlockStay(world, genPos, lily)) {
                world.setBlockState(genPos, lily, 2);
            }
        }
        return true;
    }

    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }
    
}