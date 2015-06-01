/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenMinable;

public class GeneratorOreCluster extends GeneratorOreBase
{
    public static class Builder implements IGeneratorBuilder<GeneratorOreCluster>
    {
        protected float amountPerChunk = 1.0F;
        protected int minHeight = 4;
        protected int maxHeight = 32;
        protected int clusterSize = 4;
        protected IBlockState state = Blocks.emerald_ore.getDefaultState();
        
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder state(IBlockState a) {this.state = a; return this;}        
        public Builder minHeight(int a) {this.minHeight = a; return this;}
        public Builder maxHeight(int a) {this.maxHeight = a; return this;}
        public Builder clusterSize(int a) {this.clusterSize = a; return this;}

        @Override
        public GeneratorOreCluster create()
        {
            return new GeneratorOreCluster(this.state, this.amountPerChunk, this.clusterSize, this.minHeight, this.maxHeight);
        }
    }
    
    
    private WorldGenMinable generator;
    
    public GeneratorOreCluster(IBlockState state, float amountPerChunk, int clusterSize, int minHeight, int maxHeight)
    {
        super(amountPerChunk, minHeight, maxHeight);
        this.generator = new WorldGenMinable(state, clusterSize);
    }
    
    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        return this.generator.generate(world, random, pos);
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.generator.oreBlock = conf.getBlockState("state", this.generator.oreBlock);
        this.generator.numberOfBlocks = conf.getInt("clusterSize", this.generator.numberOfBlocks);
    }
    
}
