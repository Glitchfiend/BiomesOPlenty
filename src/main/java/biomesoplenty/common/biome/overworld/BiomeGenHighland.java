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
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBlobs;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenHighland extends BOPBiome
{
        
    public BiomeGenHighland()
    {
        // terrain
        this.terrainSettings.avgHeight(120).heightVariation(25, 25);
        
        this.setColor(0x7CAD66);
        this.setTemperatureRainfall(0.5F, 0.8F);
        
        this.canGenerateVillages = false;
        
        this.addWeight(BOPClimates.COOL_TEMPERATE, 7);
        
        // other plants
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.WILDCARROT).create());
        this.addGenerator("pumpkins", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).with(Blocks.pumpkin.getDefaultState()).create());
        
        // boulders
        this.addGenerator("boulders", GeneratorStage.SAND, (new GeneratorBlobs.Builder()).amountPerChunk(0.5F).placeOn(Blocks.grass).with(Blocks.cobblestone.getDefaultState()).minRadius(0.3F).maxRadius(1.2F).numBalls(1).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        this.addGenerator("big_boulders", GeneratorStage.SAND, (new GeneratorBlobs.Builder()).amountPerChunk(0.1F).placeOn(Blocks.grass).with(Blocks.cobblestone.getDefaultState()).minRadius(0.3F).maxRadius(4.0F).numBalls(3).scatterYMethod(ScatterYMethod.AT_SURFACE).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("doublegrass", 4, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
 
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
   
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("emerald");}
    }
    
}