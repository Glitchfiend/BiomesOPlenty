/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.api.block.IBlockPosQuery;
import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IBOPWorldSettings.GeneratorType;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.enums.BOPTrees;
import biomesoplenty.api.enums.BOPWoods;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.world.generator.GeneratorColumns;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorSplatter;
import biomesoplenty.common.world.generator.GeneratorWaterside;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorBayouTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenBayou extends BOPBiome
{    
    
    public BiomeGenBayou()
    {
        super("bayou", new PropsBuilder("Bayou").withGuiColour(0x7DAD51).withTemperature(0.85F).withRainfall(0.9F).withWaterColor(0xFFD932));
        
        // terrain
        this.terrainSettings.avgHeight(62).heightVariation(6, 3).octaves(1, 1, 1, 1, 0, 0).sidewaysNoise(0.0F);
        
        //this.skyColor = 0xACC4BC;
        this.seaFloorBlock = BOPBlocks.mud.getDefaultState();
        
        this.canSpawnInBiome = false;
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;

        this.addWeight(BOPClimates.HOT_SWAMP, 10);
        
        this.spawnableWaterCreatureList.clear();
        this.spawnableMonsterList.add(new SpawnListEntry(EntitySlime.class, 10, 1, 3));
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnail.class, 8, 1, 2));
        
        // mud
        IBlockPosQuery emptyGrassOrDirt = BlockQuery.buildAnd().withAirAbove().states(this.topBlock, this.fillerBlock).create();
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(1).maxRadius(3).with(BOPBlocks.mud.getDefaultState()).create());
        this.addGenerator("mud_splatter", GeneratorStage.SAND, (new GeneratorSplatter.Builder()).amountPerChunk(0.2F).replace(emptyGrassOrDirt).with(BOPBlocks.mud.getDefaultState()).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(8);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow", 3, (new GeneratorBayouTree.Builder()).log(BOPWoods.WILLOW).leaves(BOPTrees.WILLOW).minHeight(6).maxHeight(12).minLeavesRadius(1).leavesGradient(2).create());
        treeGenerator.add("willow_large", 1, (new GeneratorBayouTree.Builder()).log(BOPWoods.WILLOW).leaves(BOPTrees.WILLOW).minHeight(10).maxHeight(18).minLeavesRadius(2).leavesGradient(3).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(10.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
        
        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.KORU).create());
        this.addGenerator("cattail", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(6.0F).with(BOPPlants.CATTAIL).create());
        this.addGenerator("double_cattail", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(8.0F).with(BlockBOPDoublePlant.DoublePlantType.TALL_CATTAIL).create());
        this.addGenerator("sugar_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(3.0F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(Blocks.REEDS.getDefaultState()).minHeight(1).maxHeight(3).create());
        
        // water plants
        this.addGenerator("lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.5F).with(Blocks.WATERLILY.getDefaultState()).create());
        this.addGenerator("medium_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(2.0F).with(BlockBOPLilypad.LilypadType.MEDIUM).create());
        this.addGenerator("flower_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.25F).with(BlockBOPLilypad.LilypadType.FLOWER).create());
        this.addGenerator("small_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BlockBOPLilypad.LilypadType.SMALL).create());
        this.addGenerator("tiny_lily", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BlockBOPLilypad.LilypadType.TINY).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(4.0F).replace(Blocks.WATER).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.8F).with(BOPPlants.REED).generationAttempts(32).create());

        // shrooms
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(Blocks.RED_MUSHROOM.getDefaultState()).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(Blocks.BROWN_MUSHROOM.getDefaultState()).create());

        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public void applySettings(IBOPWorldSettings settings)
    {
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("glowshrooms");}
        
        if (!settings.isEnabled(GeneratorType.FLOWERS)) {this.removeGenerator("miners_delight");}
        
        if (!settings.isEnabled(GeneratorType.ROCK_FORMATIONS)) {this.removeGenerator("stone_formations");}
        
        if (!settings.isEnabled(GeneratorType.GEMS)) {this.removeGenerator("malachite");}
        
        if (!settings.isEnabled(GeneratorType.MUSHROOMS)) {this.removeGenerator("toadstools"); this.removeGenerator("flat_mushroom"); this.removeGenerator("blue_milk_caps"); this.removeGenerator("portobellos");}
        
        if (!settings.isEnabled(GeneratorType.FOLIAGE)) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.isEnabled(GeneratorType.PLANTS)) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.isEnabled(GeneratorType.WATER_PLANTS)) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily"); this.removeGenerator("flower_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.isEnabled(GeneratorType.TREES)) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(8);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("willow", 3, (new GeneratorBayouTree.Builder()).minHeight(6).maxHeight(12).minLeavesRadius(1).leavesGradient(2).create());
        treeGenerator.add("willow_large", 1, (new GeneratorBayouTree.Builder()).minHeight(10).maxHeight(18).minLeavesRadius(2).leavesGradient(3).create());
        }
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.isEnabled(GeneratorType.GRASSES)) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x7DAD51;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x9DDD66;
    }
    
    // TODO: These 2 are copied from 1.7 - but are they used ever?
    public int getFogColour(BlockPos pos)
    {
        return 0x90AF95;
    }
    public float getFogDensity(BlockPos pos)
    {
        return 0.99F;
    }
    
   
}
