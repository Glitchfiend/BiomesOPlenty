/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import net.minecraft.block.Block;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryState;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;

public class GeneratorGrass extends GeneratorFlora
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorGrass>
    {
        
        protected float amountPerChunk = 1.0F;
        protected int generationAttempts = 96;
        protected IBlockState with = Blocks.tallgrass.getDefaultState();
        protected IBlockPosQuery replace = new BlockQueryMaterial(Material.air);
                
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQuery.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}         
        public Builder with(IBlockState a) {this.with = a; return this;}
        public Builder with(BOPPlants a) {this.with = BlockBOPPlant.paging.getVariantState(a); return this;}
        public Builder with(BlockTallGrass.EnumType a) {this.with = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, a); return this;}               
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this;}
        
        @Override
        public GeneratorGrass create()
        {
            return new GeneratorGrass(this.amountPerChunk, this.replace, this.with, this.generationAttempts);
        }
    }
    
    public GeneratorGrass(float amountPerChunk, IBlockPosQuery replace, IBlockState with, int generationAttempts)
    {
        super(amountPerChunk, replace, with, generationAttempts);
    }

}
