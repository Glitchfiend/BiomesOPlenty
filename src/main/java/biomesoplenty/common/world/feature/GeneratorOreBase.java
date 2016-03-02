/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPGem;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GeneratorOreBase extends BOPGeneratorBase
{
    
    protected static abstract class InnerBuilder<T extends BOPGeneratorBase.InnerBuilder<T, G>, G extends GeneratorOreBase> extends BOPGeneratorBase.InnerBuilder<T, G>
    {
        protected IBlockState with;
        protected int minHeight;
        protected int maxHeight;

        public T with(IBlockState a) {this.with = a; return this.self();}
        public T with(BOPGems a) {this.with = BOPBlocks.gem_ore.getDefaultState().withProperty(BlockBOPGem.VARIANT, a); return this.self();}
        public T minHeight(int a) {this.minHeight = a; return this.self();}
        public T maxHeight(int a) {this.maxHeight = a; return this.self();}
    
    }
    
    protected int minHeight;
    protected int maxHeight;
    
    protected GeneratorOreBase(float amountPerChunk, int minHeight, int maxHeight)
    {
        super(amountPerChunk);
        this.minHeight = minHeight;
        this.maxHeight = maxHeight;
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // between max and min
        return new BlockPos(x, GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight), z);
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        int minHeight = conf.getInt("minHeight", this.minHeight).intValue();
        int maxHeight = conf.getInt("maxHeight", this.maxHeight).intValue();
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
    }
    
}
