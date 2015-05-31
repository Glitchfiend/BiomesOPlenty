/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorFlora extends BOPGeneratorBase
{
    protected IBlockState state;
    protected int generationAttempts;
    
    public GeneratorFlora()
    {
        // default
        this(1, Blocks.red_flower.getDefaultState(), 64);
    }
    
    public GeneratorFlora(float amountPerChunk, IBlockState state, int generationAttempts)
    {
        super(amountPerChunk);
        this.state = state;
        this.generationAttempts = generationAttempts;
    }
    
    public GeneratorFlora(float amountPerChunk, IBlockState state)
    {
        this(amountPerChunk, state, 64);
    }
    
    @Override
    public BlockPos getScatterY(World world, Random random, int x, int z)
    {
        // always at world surface
        return GeneratorUtils.ScatterYMethod.AT_SURFACE.getBlockPos(world, random, x, z);
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block = this.state.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            boolean canStay = block instanceof BlockDecoration ? ((BlockDecoration)block).canBlockStay(world, genPos, this.state) : block.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && (!world.provider.getHasNoSky() || genPos.getY() < 255) && canStay)
            {
                world.setBlockState(genPos, this.state, 2);
            }
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.state = conf.getBlockState("state", this.state);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }

}
