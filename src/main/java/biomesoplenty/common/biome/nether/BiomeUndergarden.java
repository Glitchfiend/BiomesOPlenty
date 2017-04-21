/*******************************************************************************
 * Copyright 2014-2017, the Biomes O' Plenty Team
 *
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 *
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.common.biome.nether;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLeaves;
import biomesoplenty.common.block.BlockBOPLog;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPVine;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorVines;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;

public class BiomeUndergarden extends BOPHellBiome
{
    
    public BiomeUndergarden()
    {
        super("undergarden", new PropsBuilder("Undergarden").withGuiColour(0xA93C3E).withTemperature(2.0F).withRainfall(0.0F).withRainDisabled());

        this.addWeight(BOPClimates.HELL, 15);
        
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK);
        
        // splatter top blocks
        IBlockPosQuery emptyOvergrownNetherrack = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("netherrack_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(7.0F).generationAttempts(128).scatterYMethod(ScatterYMethod.NETHER_SURFACE).replace(emptyOvergrownNetherrack).with(Blocks.NETHERRACK.getDefaultState()).create());
    
        IBlockPosQuery suitableNetherrackPosition = BlockQuery.buildAnd().withAltitudeBetween(70, 120).states(Blocks.NETHERRACK.getDefaultState()).create();
        this.addGenerator("ivy", GeneratorStage.FLOWERS,(new GeneratorVines.Builder()).amountPerChunk(25.0F).generationAttempts(128).placeOn(suitableNetherrackPosition).with(BOPBlocks.ivy.getDefaultState()).minHeight(8).maxHeight(20).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("burning_blossom", 4, (new GeneratorFlora.Builder().scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BOPFlowers.BURNING_BLOSSOM).create()));
        
        // trees
        IBlockPosQuery surfaceBlocks = BlockQuery.buildOr().states(this.topBlock, BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.OVERGROWN_NETHERRACK)).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(15.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("twiglet", 5, (new GeneratorTwigletTree.Builder()).scatterYMethod(ScatterYMethod.NETHER_SURFACE).placeOn(surfaceBlocks).minHeight(2).maxHeight(2).log(BlockBOPLog.paging.getVariantState(BOPWoods.HELLBARK)).leaves(BlockBOPLeaves.paging.getVariantState(BOPTrees.HELLBARK)).create());
    
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(15.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("devilweed", 5, (new GeneratorGrass.Builder()).with(BOPPlants.DEVILWEED).scatterYMethod(ScatterYMethod.NETHER_SURFACE).create());
        
        // shrooms
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).scatterYMethod(ScatterYMethod.NETHER_SURFACE).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());
    }
}
