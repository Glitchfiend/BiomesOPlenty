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
    
    public static class Builder extends GeneratorOreBase.InnerBuilder<Builder, GeneratorOreCluster> implements IGeneratorBuilder<GeneratorOreCluster>
    {
        protected int clusterSize;
        
        public Builder clusterSize(int a) {this.clusterSize = a; return this.self();}
        
        public Builder()
        {
            this.amountPerChunk = 1.0F;
            this.with = Blocks.emerald_ore.getDefaultState();
            this.minHeight = 4;
            this.maxHeight = 32;
            this.clusterSize = 4;
        }

        @Override
        public GeneratorOreCluster create()
        {
            return new GeneratorOreCluster(this.amountPerChunk, this.minHeight, this.maxHeight, this.with, this.clusterSize);
        }
    }
    
    
    private WorldGenMinable generator;
    
    public GeneratorOreCluster(float amountPerChunk, int minHeight, int maxHeight, IBlockState with, int clusterSize)
    {
        super(amountPerChunk, minHeight, maxHeight);
        this.generator = new WorldGenMinable(with, clusterSize);
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
        
        this.generator.oreBlock = conf.getBlockState("with", this.generator.oreBlock);
        this.generator.numberOfBlocks = conf.getInt("clusterSize", this.generator.numberOfBlocks);
    }
    
}
