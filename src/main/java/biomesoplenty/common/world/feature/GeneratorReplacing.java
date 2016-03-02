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
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public abstract class GeneratorReplacing extends BOPGeneratorBase
{
    protected IBlockPosQuery placeOn;
    protected IBlockPosQuery replace;
    protected IBlockState with;
    protected ScatterYMethod scatterYMethod;
    
    protected GeneratorReplacing(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod) {
        super(amountPerChunk);
        this.placeOn = placeOn;
        this.replace = replace;
        this.with = with;
        this.scatterYMethod = scatterYMethod;
    }

    protected static abstract class InnerBuilder<T extends InnerBuilder<T, G>, G extends GeneratorReplacing> extends BOPGeneratorBase.InnerBuilder<T, G>
    {        
        protected IBlockPosQuery placeOn;
        protected IBlockPosQuery replace;
        protected IBlockState with;
        protected ScatterYMethod scatterYMethod;
        
        public T placeOn(IBlockPosQuery a) {this.placeOn = a; return this.self();}
        public T placeOn(String a) throws BlockQueryParseException {this.placeOn = BlockQuery.parseQueryString(a); return this.self();}
        public T placeOn(Block a) {this.placeOn = new BlockQueryBlock(a); return this.self();}
        public T placeOn(IBlockState a) {this.placeOn = new BlockQueryState(a); return this.self();}
        public T placeOn(Material... a) {this.placeOn = new BlockQueryMaterial(a); return this.self();}
    
        public T replace(IBlockPosQuery a) {this.replace = a; return this.self();}
        public T replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this.self();}
        public T replace(Block a) {this.replace = new BlockQueryBlock(a); return this.self();}
        public T replace(IBlockState a) {this.replace = new BlockQueryState(a); return this.self();}
        public T replace(Material... a) {this.replace = new BlockQueryMaterial(a); return this.self();}
        
        public T with(IBlockState a) {this.with = a; return this.self();}
        
        public T scatterYMethod(ScatterYMethod a) {this.scatterYMethod = a; return this.self();}
    
    }
    
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        return this.scatterYMethod.getBlockPos(world, random, x, z);
    }
    
}