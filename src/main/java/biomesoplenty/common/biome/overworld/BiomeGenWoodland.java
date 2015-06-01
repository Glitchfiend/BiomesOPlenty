/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorBigMushroom;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLogs;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;

public class BiomeGenWoodland extends BOPBiome
{
    
   private static final Height biomeHeight = new Height(0.1F, 0.2F);
    
    public BiomeGenWoodland()
    {
        this.setHeight(biomeHeight);
        this.setColor(0x84A92D);
        this.setTemperatureRainfall(0.6F, 0.4F);
        
        this.addWeight(BiomeType.COOL, 10);
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_large", 1, (new GeneratorBigTree.Builder()).create());
        treeGenerator.add("oak", 9, (new GeneratorBasicTree.Builder()).minHeight(5).maxHeight(8).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(14);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).grass(BlockTallGrass.EnumType.GRASS).create());
 
        // big mushrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(1);
        this.addGenerator("big_mushrooms", GeneratorStage.TREE, mushroomGenerator);
        mushroomGenerator.add("brown_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).create());
        mushroomGenerator.add("red_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).to(Blocks.gravel.getDefaultState()).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(5);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 1, (new GeneratorDoubleFlora.Builder()).flora(BlockDoublePlant.EnumPlantType.ROSE).generationAttempts(64).create());

        // other plants
        this.addGenerator("toadstools", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(3).flora(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(20).flora(BOPPlants.SHRUB).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(10).flora(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(10).flora(BOPPlants.LEAFPILE).generationAttempts(256).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(10).flora(BOPPlants.DEADLEAFPILE).generationAttempts(256).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).flora(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(64).create());
      
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(5).flora(BOPPlants.REED).generationAttempts(128).create());
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(5).flora(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(128).create());
        
        // logs
        this.addGenerator("logs", GeneratorStage.TREE, (new GeneratorLogs.Builder()).amountPerChunk(8).create());
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).gemOre(BOPGems.AMBER).create());
   
    }
    
}