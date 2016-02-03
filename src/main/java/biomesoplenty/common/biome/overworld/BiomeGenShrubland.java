/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenShrubland extends BOPBiome
{
    
    public BiomeGenShrubland()
    {
        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(8, 15).octaves(0, 1, 2, 2, 1, 0).sidewaysNoise(0.1D);
        
        this.setColor(8168286);
        this.setTemperatureRainfall(0.6F, 0.05F);
        
        this.addWeight(BOPClimates.DRY_TEMPERATE, 10);
        this.addWeight(BOPClimates.MEDITERANEAN, 5);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityButterfly.class, 6, 2, 4));
        
        // sand and gravel
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.sand.getDefaultState()).create());
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        
        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.7F).with(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SHRUB).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.REED).generationAttempts(32).create());  
        
        // trees
        this.addGenerator("trees", GeneratorStage.TREE, (new GeneratorBush.Builder()).amountPerChunk(1).maxHeight(2).create());
        
        // flowers
        this.addGenerator("flowers", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(EnumFlowerType.ALLIUM).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create());
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
        if (!settings.generateFlax) {this.removeGenerator("flax");}
        if (!settings.generateBerryBushes) {this.removeGenerator("berry_bushes");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("caveweed"); this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("algae"); this.removeGenerator("duckweed"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
}
