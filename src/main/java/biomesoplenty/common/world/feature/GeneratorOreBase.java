/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

import org.apache.commons.lang3.tuple.Pair;

import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;

public abstract class GeneratorOreBase extends GeneratorCustomizable
{
    protected int amountPerChunk;
    protected int minHeight;
    protected int maxHeight;
    
    protected GeneratorOreBase(int amountPerChunk, int minHeight, int maxHeight)
    {
        this.amountPerChunk = amountPerChunk;
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            BlockPos generatedPos = pos.add(random.nextInt(16), random.nextInt(maxHeight - minHeight) + minHeight, random.nextInt(16));
            
            generate(world, random, generatedPos);
        }
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        int minHeight = conf.getInt("minHeight", this.minHeight).intValue();
        int maxHeight = conf.getInt("maxHeight", this.maxHeight).intValue();
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
    }
    
}
