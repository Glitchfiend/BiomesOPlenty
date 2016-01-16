/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.world.BOPWorldSettings;
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
        
    public BiomeGenWoodland()
    {
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(6, 25);
        
        this.setColor(0x84A92D);
        this.setTemperatureRainfall(0.6F, 0.4F);
        
        this.addWeight(BOPClimates.COOL_TEMPERATE, 10);
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("oak_large", 1, (new GeneratorBigTree.Builder()).create());
        treeGenerator.add("oak", 9, (new GeneratorBasicTree.Builder()).minHeight(5).maxHeight(8).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.4F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
 
        // big mushrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("big_mushrooms", GeneratorStage.BIG_SHROOM, mushroomGenerator);
        mushroomGenerator.add("brown_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.BROWN).create());
        mushroomGenerator.add("red_mushroom", 1, (new GeneratorBigMushroom.Builder()).mushroomType(GeneratorBigMushroom.BigMushroomType.RED).create());
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.ROSE).create());

        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.SHRUB).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BOPPlants.DEADLEAFPILE).generationAttempts(64).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).create());
      
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        
        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        
        // logs
        GeneratorWeighted logsGenerator = new GeneratorWeighted(2.5F);
        this.addGenerator("logs", GeneratorStage.TREE, logsGenerator);
        logsGenerator.add("oak_logs", 1, (new GeneratorLogs.Builder()).create());
        logsGenerator.add("dead_logs", 1, (new GeneratorLogs.Builder()).with(BOPWoods.DEAD).create());
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.AMBER).create());
   
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("amber");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xB0BA51;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x9CA825;
    }
    
}