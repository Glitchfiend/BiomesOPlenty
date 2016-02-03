/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockSand;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.*;
import biomesoplenty.common.world.feature.tree.*;

public class BiomeGenOutback extends BOPBiome
{    
    public BiomeGenOutback()
    {
        // terrain
        this.terrainSettings.avgHeight(72).heightVariation(8, 10).octaves(0, 1, 2, 1, 0, 2);
        
        this.setTemperatureRainfall(1.3F, 0.05F);
        this.setColor(0xA57644);
        this.topBlock = BOPBlocks.hard_sand.getDefaultState();
        this.fillerBlock = BOPBlocks.hard_sand.getDefaultState();
        
        this.addWeight(BOPClimates.HOT_DESERT, 7);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        // splatter top blocks
        IBlockPosQuery emptyHardSand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        this.addGenerator("grass_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(8.0F).generationAttempts(128).replace(emptyHardSand).with(BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.SANDY)).create());
        this.addGenerator("red_sand_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(6.0F).replace(emptyHardSand).with(Blocks.sand.getDefaultState().withProperty(BlockSand.VARIANT, BlockSand.EnumType.RED_SAND)).create());        
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("acacia_bush", 1, (new GeneratorBush.Builder()).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).maxHeight(2).create());
        treeGenerator.add("acacia_twiglet", 1, (new GeneratorTwigletTree.Builder()).minHeight(2).maxHeight(2).log(BlockPlanks.EnumType.ACACIA).leaves(BlockPlanks.EnumType.ACACIA).create());
        
        // grasses (note weighting must be quite high as the grasses will only grow on the splattered grass blocks)
        GeneratorWeighted grassGenerator = new GeneratorWeighted(16.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.SHRUB).create());
        this.addGenerator("tiny_cacti", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.TINYCACTUS).create());
        this.addGenerator("dead_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(Blocks.deadbush.getDefaultState()).create());
        this.addGenerator("cacti", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.5F).generationAttempts(24).placeOn(this.topBlock).with(Blocks.cactus.getDefaultState()).minHeight(1).maxHeight(2).create());
        
        // gem
        this.addGenerator("ruby", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.RUBY).create());        
        
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("ruby");}
        
        IBlockPosQuery emptyHardSand = BlockQuery.buildAnd().withAirAbove().states(this.topBlock).create();
        if (!settings.generateBopSoils) {this.removeGenerator("grass_splatter"); this.addGenerator("grass_splatter_new", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(8.0F).generationAttempts(128).replace(emptyHardSand).with(Blocks.grass.getDefaultState()).create());}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("caveweed"); this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }

}