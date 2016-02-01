/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockFlower;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockBOPVine;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;

public class BiomeGenLushSwamp extends BOPBiome
{    
    
    public BiomeGenLushSwamp()
    {
        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(5, 10).octaves(1, 1, 1, 1, 0, 0).sidewaysNoise(0.0F);
        
        this.setColor(0x57AE34);
        this.setTemperatureRainfall(0.7F, 1.0F);

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 7);
        
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnail.class, 8, 1, 2));
        
        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(5).maxRadius(8).with(BOPBlocks.mud.getDefaultState()).create());
        
        // sand
        this.addGenerator("sand", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(3).maxRadius(7).with(Blocks.sand.getDefaultState()).create());
        
        // lakes
        this.addGenerator("poison_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.1F).waterLakeForBiome(this).liquid(BOPBlocks.poison).frozenLiquid((IBlockState)null).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("swamp", 5, (new GeneratorBasicTree.Builder()).minHeight(8).maxHeight(12).minLeavesRadius(2).vine(BOPBlocks.ivy.getDefaultState()).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.7F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("blue_hydrangeas", 3, (new GeneratorFlora.Builder().with(BOPFlowers.BLUE_HYDRANGEA).create()));
        flowerGenerator.add("swampflower", 2, (new GeneratorFlora.Builder()).with(BOPFlowers.SWAMPFLOWER).create());
        flowerGenerator.add("blue_orchid", 1, (new GeneratorFlora.Builder().with(EnumFlowerType.BLUE_ORCHID).create()));
        flowerGenerator.add("houstonia", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.HOUSTONIA).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.KORU).create());
        this.addGenerator("cattail", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(3.0F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("double_cattail", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(3.0F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(Blocks.reeds.getDefaultState()).minHeight(1).maxHeight(3).create());
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(1.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        
        // water plants
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.DUCKWEED).create());
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.waterlily.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.TINY).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.7F).with(BOPPlants.REED).generationAttempts(32).create());

        // shrooms
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("blue_milk_caps", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.BLUE_MILK_CAP).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(Blocks.red_mushroom.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.brown_mushroom.getDefaultState()).create());

        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("algae"); this.removeGenerator("duckweed"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
   
}
