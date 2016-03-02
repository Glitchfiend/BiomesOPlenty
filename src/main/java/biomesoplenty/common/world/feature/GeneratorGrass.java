/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class GeneratorGrass extends GeneratorFlora
{
    
    public static class Builder extends GeneratorFlora.InnerBuilder<Builder, GeneratorGrass> implements IGeneratorBuilder<GeneratorGrass>
    {
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = Blocks.tallgrass.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.generationAttempts = 96;
        }

        @Override
        public GeneratorGrass create()
        {
            return new GeneratorGrass(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.generationAttempts);
        }
    }
    
    public GeneratorGrass(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod, generationAttempts);
    }

}
