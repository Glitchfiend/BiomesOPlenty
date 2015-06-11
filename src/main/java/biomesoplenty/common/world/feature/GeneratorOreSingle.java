/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.block.state.pattern.BlockHelper;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGem;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

import com.google.common.base.Predicate;

public class GeneratorOreSingle extends GeneratorOreBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorOreSingle>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockState with = Blocks.emerald_ore.getDefaultState();
        protected int minHeight = 4;
        protected int maxHeight = 32;
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder with(IBlockState a) {this.with = a; return this;}
        public Builder with(BOPGems a) {this.with = BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, a); return this;}
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}

        @Override
        public GeneratorOreSingle create()
        {
            return new GeneratorOreSingle(this.amountPerChunk, this.with, this.minHeight, this.maxHeight);
        }
    }
    
    
    
    private IBlockState with;
    private Predicate replace;
    
    public GeneratorOreSingle(float amountPerChunk, IBlockState state, int minHeight, int maxHeight)
    {
        super(amountPerChunk, minHeight, maxHeight);
        
        this.with = state;
        this.replace = BlockHelper.forBlock(Blocks.stone);
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock().isReplaceableOreGen(world, pos, this.replace))
        {
            return world.setBlockState(pos, this.with, 2);
        }
        
        return false;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.with = conf.getBlockState("with", this.with);
    }
    
}
