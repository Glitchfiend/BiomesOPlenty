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

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public abstract class GeneratorOreBase extends BOPGeneratorBase
{
    protected int minHeight;
    protected int maxHeight;
    
    protected GeneratorOreBase(float amountPerChunk, int minHeight, int maxHeight)
    {
        super(amountPerChunk);
        
        Pair<Integer, Integer> heights = GeneratorUtils.validateMinMaxHeight(minHeight, maxHeight);
        this.minHeight = heights.getLeft();
        this.maxHeight = heights.getRight();
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // between max and min
        return world.getHeight(new BlockPos(x, GeneratorUtils.nextIntBetween(random, this.minHeight, this.maxHeight), z));
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
