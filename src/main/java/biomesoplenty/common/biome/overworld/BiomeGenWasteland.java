/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenWasteland extends BOPBiome
{    
    
    public BiomeGenWasteland()
    {
        super("wasteland", new PropsBuilder("Wasteland").withGuiColour(0x5A5440).withTemperature(2.0F).withRainfall(0.0F).withWaterColor(0xE5FF00).withRainDisabled());

        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(1,5);
        
        this.topBlock = BOPBlocks.dried_sand.getDefaultState();
        this.fillerBlock = BOPBlocks.dried_sand.getDefaultState();
        this.skyColor = 0x909E70;
        this.seaFloorBlock = BOPBlocks.dried_sand.getDefaultState();

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        this.canSpawnInBiome = false;
        
        this.beachBiomeLocation = null;
        
        this.addWeight(BOPClimates.WASTELAND, 50);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        // trees
        IBlockPosQuery emptyDriedSand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dead_tree", 3, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.air.getDefaultState()).create());
        treeGenerator.add("dying_tree", 1, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(1).log(BOPWoods.DEAD).leaves(BOPTrees.DEAD).create());
        
        // other plants
        this.addGenerator("dead_grass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.DEADGRASS).create());
        this.addGenerator("desertgrass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DESERTGRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.05F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("wilted_lily", 1, (new GeneratorFlora.Builder()).with(BOPFlowers.WILTED_LILY).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.05F).waterLakeForBiome(this).create());
        this.addGenerator("poison_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.05F).waterLakeForBiome(this).liquid(BOPBlocks.poison).frozenLiquid((IBlockState)null).create());
        
        // spikes
        //this.addGenerator("spikes", GeneratorStage.PRE, (new GeneratorSpike.Builder()).amountPerChunk(0.2F).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
        
        if (!settings.generateLiquidPoison) {this.removeGenerator("poison_lakes");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopFlowers) {this.removeGenerator("flowers");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        IBlockPosQuery emptyDriedSand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.3F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dead_tree", 3, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BlockPlanks.EnumType.OAK).leaves(Blocks.air.getDefaultState()).create());
        treeGenerator.add("dying_tree", 1, (new GeneratorBigTree.Builder()).placeOn(emptyDriedSand).minHeight(5).maxHeight(12).foliageHeight(1).log(BlockPlanks.EnumType.DARK_OAK).leaves(BlockPlanks.EnumType.DARK_OAK).create());
        }
        
        if (!settings.generateBopGrasses) {this.removeGenerator("dead_grass"); this.removeGenerator("desertgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x9DA078;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x999E55;
    }
    
    // TODO: These 2 are copied from 1.7 - but are they used ever?
    public int getFogColour(BlockPos pos)
    {
        return 0xB8BC85;
    }
    public float getFogDensity(BlockPos pos)
    {
        return 0.3F;
    }
    
   
}
