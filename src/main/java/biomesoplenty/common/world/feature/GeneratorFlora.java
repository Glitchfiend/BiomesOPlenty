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
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.World;
import biomesoplenty.api.biome.generation.BOPGeneratorBase;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockDecoration;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils;
import biomesoplenty.common.util.block.BlockQueryUtils;
import biomesoplenty.common.util.block.BlockQueryUtils.*;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorFlora extends BOPGeneratorBase
{
    
    public static class Builder implements IGeneratorBuilder<GeneratorFlora>
    {
        protected float amountPerChunk = 1.0F;
        protected IBlockPosQuery replace = new BlockQueryMaterial(Material.air);
        protected IBlockState with = Blocks.red_flower.getDefaultState();
        protected int generationAttempts = 20;
                
        public Builder amountPerChunk(float a) {this.amountPerChunk = a; return this;}
        public Builder replace(IBlockPosQuery a) {this.replace = a; return this;}
        public Builder replace(String a) throws BlockQueryParseException {this.replace = BlockQueryUtils.parseQueryString(a); return this;}
        public Builder replace(Block a) {this.replace = new BlockQueryBlock(a); return this;}
        public Builder replace(IBlockState a) {this.replace = new BlockQueryState(a); return this;}         
        public Builder with(IBlockState a) {this.with = a; return this;}
        public Builder with(BOPPlants a) {this.with = BlockBOPPlant.paging.getVariantState(a); return this;}
        public Builder with(BOPFlowers a) {this.with = BlockBOPFlower.paging.getVariantState(a); return this;}
        public Builder with(BlockBOPMushroom.MushroomType a) {this.with = BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, a); return this;}
        public Builder with(BlockBOPLilypad.LilypadType a) {this.with = BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, a); return this;}
        public Builder with(BlockFlower.EnumFlowerType a)
        {
            BlockFlower flowerBlock = a.getBlockType().getBlock();
            this.with = flowerBlock.getDefaultState().withProperty(flowerBlock.getTypeProperty(), a);
            return this;
        }
        public Builder with(BlockTallGrass.EnumType a) {this.with = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, a); return this;}
        public Builder generationAttempts(int a) {this.generationAttempts = a; return this;}
        
        @Override
        public GeneratorFlora create()
        {
            return new GeneratorFlora(this.amountPerChunk, this.replace, this.with, this.generationAttempts);
        }
    }
    
    
    protected IBlockPosQuery replace;
    protected IBlockState with;
    protected int generationAttempts;
    
    public GeneratorFlora(float amountPerChunk, IBlockPosQuery replace, IBlockState with, int generationAttempts)
    {
        super(amountPerChunk);
        this.replace = replace;
        this.with = with;
        this.generationAttempts = generationAttempts;
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
        Block block = this.with.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (this.replace.matches(world, genPos) && genPos.getY() < 255)
            {
                boolean canStay;
                if (block instanceof BlockDecoration)
                {
                    canStay = ((BlockDecoration)block).canBlockStay(world, genPos, this.with);
                } else if (block instanceof BlockBush) {
                    canStay = ((BlockBush)block).canPlaceBlockAt(world, genPos);
                } else {
                    canStay = block.canPlaceBlockAt(world, genPos);
                }
                
                if (canStay)
                {
                    world.setBlockState(genPos, this.with, 2);
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
        this.generationAttempts = conf.getInt("generationAttempts", this.generationAttempts);
    }

}
