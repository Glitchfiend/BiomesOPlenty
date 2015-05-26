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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.block.BlockDoubleDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.ConfigHelper.WrappedJsonObject;
import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorDoubleFlora extends GeneratorCustomizable
{
    private int amountPerChunk;
    private IBlockState bottomState;
    private IBlockState topState;
    private int generationAttempts;
    
    public GeneratorDoubleFlora()
    {
        // default
        this(1, BlockBOPDoublePlant.DoublePlantType.FLAX, 64);        
    }
    
    // convenient shortcut constructor for use with a BlockBOPDoublePlant variant
    public GeneratorDoubleFlora(int amountPerChunk, BlockBOPDoublePlant.DoublePlantType type, int generationAttempts)
    {
        this(amountPerChunk, BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockDoubleDecoration.Half.LOWER), BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockDoubleDecoration.Half.UPPER), generationAttempts);
    }
    
    public GeneratorDoubleFlora(int amountPerChunk, IBlockState bottomState, IBlockState topState, int generationAttempts)
    {
        this.amountPerChunk = amountPerChunk;
        this.bottomState = bottomState;
        this.topState = topState;
        this.generationAttempts = generationAttempts;
    }
    
    public GeneratorDoubleFlora(int amountPerChunk, IBlockState bottomState, IBlockState topState)
    {
        this(amountPerChunk, bottomState, topState, 64);
    }

    @Override
    public void scatter(World world, Random random, BlockPos pos)
    {
        for (int i = 0; i < amountPerChunk; i++)
        {
            int x = random.nextInt(16) + 8;
            int z = random.nextInt(16) + 8;
            BlockPos genPos = pos.add(x, 0, z);
            int y = GeneratorUtils.safeNextInt(random, world.getHeight(genPos).getY() + 32);
            genPos = genPos.add(0, y, 0);

            generate(world, random, genPos);
        }
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block bottomBlock = this.bottomState.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            boolean canStay = bottomBlock instanceof BlockDecoration ? ((BlockDecoration)bottomBlock).canBlockStay(world, genPos, this.bottomState) : bottomBlock.canPlaceBlockAt(world, genPos);
            
            if (world.isAirBlock(genPos) && world.isAirBlock(genPos.up()) && (!world.provider.getHasNoSky() || genPos.getY() < 255) && canStay)
            {
                world.setBlockState(genPos, this.bottomState, 2);
                world.setBlockState(genPos.up(), this.topState, 2);
            }
        }

        return true;
    }
    
    @Override
    public void configure(WrappedJsonObject conf)
    {
        this.amountPerChunk = conf.getInt("amountPerChunk", this.amountPerChunk);
        this.bottomState = conf.getBlockState("bottomState", this.bottomState);
        this.topState = conf.getBlockState("topState", this.topState);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }
}
