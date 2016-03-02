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
import biomesoplenty.common.block.BlockBOPFlower;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryMaterial;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockLeaves;
import net.minecraft.block.BlockNewLeaf;
import net.minecraft.block.BlockOldLeaf;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class GeneratorFlora extends GeneratorReplacing
{
    
    protected static abstract class InnerBuilder<T extends GeneratorReplacing.InnerBuilder<T, G>, G extends GeneratorFlora> extends GeneratorReplacing.InnerBuilder<T, G>
    {
        protected int generationAttempts;

        public T with(BOPPlants a) {this.with = BlockBOPPlant.paging.getVariantState(a); return this.self();}
        public T with(BOPFlowers a) {this.with = BlockBOPFlower.paging.getVariantState(a); return this.self();}
        public T with(BlockBOPMushroom.MushroomType a) {this.with = BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, a); return this.self();}
        public T with(BlockBOPLilypad.LilypadType a) {this.with = BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, a); return this.self();}
        public T with(BlockFlower.EnumFlowerType a)
        {
            BlockFlower flowerBlock = a.getBlockType().getBlock();
            this.with = flowerBlock.getDefaultState().withProperty(flowerBlock.getTypeProperty(), a);
            return this.self();
        }
        public T with(BlockTallGrass.EnumType a) {this.with = Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, a); return this.self();}
        public T withNonDecayingLeaf(BlockPlanks.EnumType a)
        {
            IBlockState leafState;
            if (a.getMetadata() < 4)
            {
                leafState = Blocks.leaves.getDefaultState().withProperty(BlockOldLeaf.VARIANT, a);
            } else {
                leafState = Blocks.leaves2.getDefaultState().withProperty(BlockNewLeaf.VARIANT, a);
            }
            this.with = leafState.withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(false));
            return this.self();
        }
        public T withNonDecayingLeaf(BOPTrees a)
        {
            this.with = BlockBOPLeaves.paging.getVariantState(a).withProperty(BlockLeaves.CHECK_DECAY, Boolean.valueOf(false)).withProperty(BlockLeaves.DECAYABLE, Boolean.valueOf(false));
            return this.self();
        }

        public T generationAttempts(int a) {this.generationAttempts = a; return this.self();}
    
    }
    
    public static class Builder extends InnerBuilder<Builder, GeneratorFlora> implements IGeneratorBuilder<GeneratorFlora>
    {
        
        public Builder()
        {
            // defaults
            this.amountPerChunk = 1.0F;
            this.placeOn = BlockQueries.anything;
            this.replace = new BlockQueryMaterial(Material.air);
            this.with = Blocks.red_flower.getDefaultState();
            this.scatterYMethod = ScatterYMethod.AT_SURFACE;
            this.generationAttempts = 32;
        }

        @Override
        public GeneratorFlora create()
        {
            return new GeneratorFlora(this.amountPerChunk, this.placeOn, this.replace, this.with, this.scatterYMethod, this.generationAttempts);
        }
    }
    
    protected int generationAttempts;
    
    public GeneratorFlora(float amountPerChunk, IBlockPosQuery placeOn, IBlockPosQuery replace, IBlockState with, ScatterYMethod scatterYMethod, int generationAttempts)
    {
        super(amountPerChunk, placeOn, replace, with, scatterYMethod);
        this.generationAttempts = generationAttempts;
    }

    @Override
    public boolean generate(World world, Random random, BlockPos pos)
    {
        Block block = this.with.getBlock();
        
        for (int i = 0; i < this.generationAttempts; ++i)
        {
            BlockPos genPos = pos.add(random.nextInt(8) - random.nextInt(8), random.nextInt(4) - random.nextInt(4), random.nextInt(8) - random.nextInt(8));

            if (this.placeOn.matches(world, genPos.down()) && this.replace.matches(world, genPos) && genPos.getY() < 255)
            {
                boolean canStay;
                if (block instanceof BlockBOPDecoration)
                {
                    canStay = ((BlockBOPDecoration)block).canBlockStay(world, genPos, this.with);
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
        this.scatterYMethod = conf.getEnum("scatterYMethod", this.scatterYMethod, ScatterYMethod.class);
    }

}
