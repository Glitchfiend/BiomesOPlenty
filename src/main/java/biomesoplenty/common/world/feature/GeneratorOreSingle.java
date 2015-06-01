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
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

import com.google.common.base.Predicate;

public class GeneratorOreSingle extends GeneratorOreBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorOreSingle>
    {
        protected float amountPerChunk = 1.0F;
        protected int minHeight = 4;
        protected int maxHeight = 32;
        protected IBlockState state = Blocks.emerald_ore.getDefaultState();
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder state(IBlockState a) {this.state = a; return this;}
        public Builder gemOre(BOPGems a) {this.state = BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, a); return this;}
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}

        @Override
        public GeneratorOreSingle create()
        {
            return new GeneratorOreSingle(this.state, this.amountPerChunk, this.minHeight, this.maxHeight);
        }
    }
    
    
    
    private IBlockState state;
    private Predicate replace;
    
    public GeneratorOreSingle(IBlockState state, float amountPerChunk, int minHeight, int maxHeight)
    {
        super(amountPerChunk, minHeight, maxHeight);
        
        this.state = state;
        this.replace = BlockHelper.forBlock(Blocks.stone);
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        if (world.getBlockState(pos).getBlock().isReplaceableOreGen(world, pos, this.replace))
        {
            return world.setBlockState(pos, this.state, 2);
        }
        
        return false;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.state = conf.getBlockState("state", this.state);
        this.replace = BlockHelper.forBlock(Blocks.stone);
    }
    
}
