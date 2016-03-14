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
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBulbTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.init.Blocks;

public class BiomeGenEucalyptusForest extends BOPBiome
{
    
    public BiomeGenEucalyptusForest()
    {
        super("eucalyptus_forest", new PropsBuilder("Eucalyptus Forest").withGuiColour(0x9DCC70).withTemperature(0.85F).withRainfall(0.9F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(10, 20);
        
        this.canGenerateVillages = false;

        this.addWeight(BOPClimates.TROPICAL, 5);
        
        this.spawnableMonsterList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityButterfly.class, 6, 2, 4));
                            
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_bush", 8, (new GeneratorBush.Builder()).maxHeight(2).create());        
        treeGenerator.add("tall_eucalyptus", 1, (new GeneratorBulbTree.Builder()).minHeight(15).maxHeight(30).log(BOPWoods.EUCALYPTUS).leaves(BOPTrees.EUCALYPTUS).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(6);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SPROUT).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());
        this.addGenerator("rafflesia", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.RAFFLESIA).create());
        this.addGenerator("melons", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).placeOn(this.topBlock).with(Blocks.melon_block.getDefaultState()).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.waterlily.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.TINY).create());
        
        // gem
        this.addGenerator("topaz", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TOPAZ).create());
   
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("topaz");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("algae"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_bush", 8, (new GeneratorBush.Builder()).maxHeight(2).create());        
        treeGenerator.add("tall_eucalyptus", 1, (new GeneratorBulbTree.Builder()).minHeight(15).maxHeight(30).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).create());
        }
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    /*
    public int getFogColour(int x, int y, int z)
    {
        return 14805212;
    }
    */
    
}