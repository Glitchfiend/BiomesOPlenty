/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world.feature;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPDecoration;
import biomesoplenty.common.block.BlockBOPDoubleDecoration;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorDoubleFlora extends GeneratorReplacing
{
    
    public static class Builder extends GeneratorReplacing.InnerBuilder<Builder, GeneratorDoubleFlora> implements IGeneratorBuilder<GeneratorDoubleFlora>
    {
        protected int generationAttempts;
        protected IBlockState withTop;
        
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this.self();}

        @Override
        public Builder with(IBlockState a) {this.with = a; this.withTop = a; return this.self();}
        public Builder with(IBlockState bottom, IBlockState top) {this.with = bottom; this.withTop = top; return this.self();}
        public Builder with(BlockBOPDoublePlant.DoublePlantType type)
        {
            this.with = BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockBOPDoubleDecoration.Half.LOWER);
            this.withTop = BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, type).withProperty(BlockBOPDoublePlant.HALF, BlockBOPDoubleDecoration.Half.UPPER);
            return this.self();
        }
        public Builder with(BlockDoublePlant.EnumPlantType type)
        {
            this.with = Blocks.double_plant.getStateFromMeta(type.getMeta());
            this.withTop = Blocks.double_plant.getStateFromMeta(8);
            return this;
        }
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, BlockBOPDoublePlant.DoublePlantType.FLAX).withProperty(BlockBOPDoublePlant.HALF, BlockBOPDoubleDecoration.Half.LOWER);
            this.withTop = BOPBlocks.double_plant.getDefaultState().withProperty(BlockBOPDoublePlant.VARIANT, BlockBOPDoublePlant.DoublePlantType.FLAX).withProperty(BlockBOPDoublePlant.HALF, BlockBOPDoubleDecoration.Half.UPPER);
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.generationAttempts = 32;
        }

        @Override
        public GeneratorDoubleFlora create() {
            return new GeneratorDoubleFlora(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.withTop, this.generationAttempts);
        }
    
    }
    
    
    protected IBlockState withTop;
    protected int generationAttempts;
    
    public GeneratorDoubleFlora(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, IBlockState withTop, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.withTop = withTop;
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block bottomBlock = this.with.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos) && this.replace.matches(world, genPos.up()) && genPos.getY() < 254)
            {
                boolean canStay;
                if (bottomBlock instanceof BlockBOPDecoration)
                {
                    canStay = ((BlockBOPDecoration)bottomBlock).canBlockStay(world, genPos, this.with);
                } else if (bottomBlock instanceof BlockBush) {
                    canStay = ((BlockBush)bottomBlock).canPlaceBlockAt(world, genPos);
                } else {
                    canStay = bottomBlock.canPlaceBlockAt(world, genPos);
                }
                    
                if (canStay)
                {
                    world.setBlockState(genPos, this.with, 2);
                    world.setBlockState(genPos.up(), this.withTop, 2);
                }
            }
        }

        return true;
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        this.amountPerChunk = conf.getFloat("amountPerChunk", this.amountPerChunk);
        this.replace = conf.getBlockPosQuery("replace", this.replace);
        this.with = conf.getBlockState("with", this.with);
        this.withTop = conf.getBlockState("withTop", this.withTop);
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }
}
