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
    private WorldGenMinable generator;
    
    public GeneratorOreCluster()
    {
        // default
        this(Blocks.emerald_ore.getDefaultState(), 12, 4, 4, 32);
    }
    
    public GeneratorOreCluster(IBlockState state, int amountPerChunk, int clusterSize, int minHeight, int maxHeight)
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
