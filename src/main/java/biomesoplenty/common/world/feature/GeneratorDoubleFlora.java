/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.block.BlockDoubleDecoration;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;

public class GeneratorDoubleFlora extends BOPGeneratorBase
{
    private IBlockState bottomState;
    private IBlockState topState;
    private int generationAttempts;
    
    public GeneratorDoubleFlora()
    {
        // default
        this(1, BlockBOPDoublePlant.DoublePlantType.FLAX, 64);        
    }
    
    // convenient shortcut constructor for use with a BlockBOPDoublePlant variant
    public GeneratorDoubleFlora(float amountPerChunk, BlockBOPDoublePlant.DoublePlantType type, int generationAttempts)
    {
        this(amountPerChunk, BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockDoubleDecoration.Half.LOWER), BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockDoubleDecoration.Half.UPPER), generationAttempts);
    }
    
    // convenient shortcut constructor for use with a vanilla BlockDoublePlant variant
    public GeneratorDoubleFlora(float amountPerChunk, BlockDoublePlant.EnumPlantType type, int generationAttempts)
    {
        this(amountPerChunk, Blocks.double_plant.getStateFromMeta(type.getMeta()), Blocks.double_plant.getStateFromMeta(8), generationAttempts);
    }
    
    public GeneratorDoubleFlora(float amountPerChunk, IBlockState bottomState, IBlockState topState, int generationAttempts)
    {
        super(amountPerChunk);
        this.bottomState = bottomState;
        this.topState = topState;
        this.generationAttempts = generationAttempts;
    }
    
    public GeneratorDoubleFlora(float amountPerChunk, IBlockState bottomState, IBlockState topState)
    {
        this(amountPerChunk, bottomState, topState, 64);
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
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.bottomState = conf.getBlockState("bottomState", this.bottomState);
        this.topState = conf.getBlockState("topState", this.topState);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }
}
