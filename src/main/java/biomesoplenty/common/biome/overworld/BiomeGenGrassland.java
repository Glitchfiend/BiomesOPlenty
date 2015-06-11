/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.material.Material;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;

public class BiomeGenGrassland extends BOPBiome {
    
    public BiomeGenGrassland() {
        
        // terrain
        this.bopMinHeight = 58;
        this.bopMaxHeight = 85;
        this.sidewaysNoiseAmount = 0.1D;
        this.setOctaveWeights(0, 1, 1, 1, 2, 3);
        
        this.setColor(0x7FDB7D);
        this.setTemperatureRainfall(0.6F, 0.7F);
        
        this.addWeight(BiomeType.COOL, 10);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).waterLakeForBiome(this).create());
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
                
        // other plants
        IBlockPosQuery canPlaceRiverCane = BlockQuery.buildAnd().byWater().materials(Material.ground, Material.grass).create();
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.0F).generationAttempts(24).placeOn(canPlaceRiverCane).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        IBlockPosQuery canPlaceSugarCane = BlockQuery.buildAnd().byWater().materials(Material.ground, Material.grass, Material.sand).create();
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(4.0F).generationAttempts(24).placeOn(canPlaceSugarCane).with(Blocks.reeds.getDefaultState()).minHeight(1).maxHeight(3).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.REED).generationAttempts(32).create());  
        
        // flowers
        this.addGenerator("flowers", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(EnumFlowerType.ALLIUM).create());

        // shrooms
        this.addGenerator("portobellos", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPMushroom.MushroomType.PORTOBELLO).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create());
        
        
    }

}
