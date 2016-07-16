/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiomes;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.generator.GeneratorBlobs;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorLakes;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBush;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenTundra extends BOPBiome
{    
    public BiomeGenTundra()
    {
        super("tundra", new PropsBuilder("Tundra").withGuiColour(0xA09456).withTemperature(0.2F).withRainfall(0.5F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 10).minHeight(59).octaves(2, 2, 1, 0, 1, 1);
        
        this.addWeight(BOPClimates.TUNDRA, 10);
        
        this.canGenerateRivers = false;
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        if (BOPBiomes.gravel_beach.isPresent())
        {
        	this.beachBiomeLocation = ((BOPBiome)BOPBiomes.gravel_beach.get()).getResourceLocation();
        }
        
        this.spawnableCreatureList.clear();
        
        // boulders
        this.addGenerator("boulders", GeneratorStage.SAND, (new GeneratorBlobs.Builder()).amountPerChunk(0.5F).placeOn(Blocks.GRASS).with(Blocks.COBBLESTONE.getDefaultState()).minRadius(0.3F).maxRadius(1.2F).numBalls(1).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(12).maxRadius(7).with(Blocks.GRAVEL.getDefaultState()).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.3F).waterLakeForBiome(this).create());        
        
        // trees
        this.addGenerator("trees", GeneratorStage.TREE, (new GeneratorBush.Builder()).amountPerChunk(2).maxHeight(2).create());
        
        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.6F).with(BOPPlants.LEAFPILE).generationAttempts(64).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.6F).with(BOPPlants.DEADLEAFPILE).generationAttempts(64).create());
 
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.REED).generationAttempts(32).create());

        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.1F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("violet", 1, (new GeneratorFlora.Builder().with(BOPFlowers.VIOLET).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());

        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());

    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("tanzanite");}
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.isEnabled(GeneratorType.WATER_PLANTS)) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("flowers");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.isEnabled(GeneratorType.GRASSES)) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xAD8456;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xBF664E;
    }

}