/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import java.util.Random;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockDirt;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

public class BiomeUndergarden extends BOPHellBiome
{
    public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
    
    public BiomeUndergarden()
    {
        super("undergarden", new PropsBuilder("Undergarden").withGuiColour(0xA93C3E));

        this.addWeight(BOPClimates.HELL, 20);
        
        // splatter top blocks
        IBlockPosQuery emptyNetherrack = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("overgrown_netherrack_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(1.0F).generationAttempts(128).replace(emptyNetherrack).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create());
    
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("burning_blossom", 4, (new GeneratorFlora.Builder().with(BOPFlowers.BURNING_BLOSSOM).scatterYMethod(ScatterYMethod.BELOW_GROUND).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("mediumgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("twiglet", 3, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockBOPLeaves.paging.getVariantState(BOPTrees.HELLBARK)).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.HELLBARK)).create());
    
        // shrooms
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.6F).with(Blocks.RED_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(Blocks.BROWN_MUSHROOM.getDefaultState()).scatterYMethod(ScatterYMethod.BELOW_GROUND).create());
    }
}
