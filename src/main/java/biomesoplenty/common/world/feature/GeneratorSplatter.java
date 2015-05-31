/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.GeneratorCustomizable;
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryParseException;
import biomesoplenty.common.util.block.BlockQueryUtils.IBlockQuery;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryAny;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQueryUtils.BlockQueryState;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorSplatter extends GeneratorCustomizable
{
    private static IBlockQuery isLeavesOrAir = new BlockQueryAny(new BlockQueryMaterial(Material.leaves), new BlockQueryMaterial(Material.air));
    
    protected int amountPerChunk;
    protected IBlockQuery placeOn;
    protected int generationAttempts;
    protected IBlockState to;
        
    public GeneratorSplatter()
    {
        // default
        this(1, Blocks.stone.getDefaultState(), 64, Blocks.grass); 
    }
    
    public GeneratorSplatter(int amountPerChunk, IBlockState to, int splotchSize, String placeOn) throws BlockQueryParseException
    {
        this(amountPerChunk, to, splotchSize, BlockQueryUtils.parseQueryString(placeOn));
    }
    
    public GeneratorSplatter(int amountPerChunk, IBlockState to, int splotchSize, Block placeOn)
    {
        this(amountPerChunk, to, splotchSize, new BlockQueryBlock(placeOn));
    }
    
    public GeneratorSplatter(int amountPerChunk, IBlockState to, int splotchSize, IBlockState placeOn)
    {
        this(amountPerChunk, to, splotchSize, new BlockQueryState(placeOn));
    }
    
    public GeneratorSplatter(int amountPerChunk, IBlockState to, int generationAttempts, IBlockQuery placeOn)
    {
        this.amountPerChunk = amountPerChunk;
        this.to = to;
        this.generationAttempts = generationAttempts;
        this.placeOn = placeOn;
    }
    
    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            int y = random.nextInt(256);
            BlockPos genPos = pos.add(x, y, z);
            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos)
    {         
        // if we're in the air, move down until we're not
        while (pos.getY() > 0 && isLeavesOrAir.matches(world.getBlockState(pos)))
        {
            pos = pos.down();
        }

        // look for blocks on the surface matching this.placeOn and randomly put this.to on top of them
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos pos1 = pos.add(rand.nextInt(8) - rand.nextInt(8), rand.nextInt(4) - rand.nextInt(4), rand.nextInt(8) - rand.nextInt(8));
            if (world.isAirBlock(pos1) && this.placeOn.matches(world.getBlockState(pos1.down())))
            {
                world.setBlockState(pos1, this.to);
            }
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        this.to = conf.getBlockState("to", this.to);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
        String placeOnString = conf.getString("placeOn", null);
        if (placeOnString != null)
        {
            try {
                IBlockQuery placeOn = BlockQueryUtils.parseQueryString(placeOnString);
                this.placeOn = placeOn;
            } catch (BlockQueryParseException e) {
                conf.addMessage("placeOn", e.getMessage());
            }
        }
    }
    
    
}