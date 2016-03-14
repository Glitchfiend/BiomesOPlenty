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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPDirt;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.entities.EntitySnail;
import biomesoplenty.common.enums.*;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.*;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;

public class BiomeGenDeadSwamp extends BOPBiome
{
    
    public BiomeGenDeadSwamp()
    {
        super("dead_swamp", new PropsBuilder("Dead Swamp").withGuiColour(0x8BAF48).withTemperature(0.55F).withRainfall(0.9F).withWaterColor(0xA2AD51));
        
        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(6, 3);

        //this.skyColor = 0x627268;
        this.topBlock = BOPBlocks.grass.getDefaultState().withProperty(BlockBOPGrass.VARIANT, BlockBOPGrass.BOPGrassType.LOAMY);
        this.fillerBlock = BOPBlocks.dirt.getDefaultState().withProperty(BlockBOPDirt.VARIANT, BlockBOPDirt.BOPDirtType.LOAMY);
        
        this.canSpawnInBiome = false;
        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.beachBiomeLocation = null;
        
        this.addWeight(BOPClimates.COLD_SWAMP, 3);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        this.spawnableCreatureList.add(new SpawnListEntry(EntitySnail.class, 8, 1, 2));

        // mud
        this.addGenerator("mud", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(8).maxRadius(7).with(BOPBlocks.mud.getDefaultState()).create());
        this.addGenerator("mud_patches", GeneratorStage.SAND_PASS2, (new GeneratorSplotches.Builder()).amountPerChunk(1).splotchSize(12).replace(this.topBlock).with(BOPBlocks.mud.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dying_tree", 2, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(1).log(BOPWoods.DEAD).leaves(BOPTrees.DEAD).create());
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.air.getDefaultState()).create());

        // other plants
        this.addGenerator("koru", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.KORU).create());
        
        // water plants
        this.addGenerator("river_cane", GeneratorStage.FLOWERS,(new GeneratorColumns.Builder()).amountPerChunk(0.3F).generationAttempts(24).placeOn(BlockQueries.litFertileWaterside).with(BlockBOPPlant.paging.getVariantState(BOPPlants.RIVERCANE)).minHeight(1).maxHeight(3).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.REED).generationAttempts(32).create());
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).generationAttempts(32).scatterYMethod(ScatterYMethod.AT_GROUND).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 4, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 3, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        grassGenerator.add("dampgrass", 2, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("doublegrass", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.GRASS).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
 
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
        
        if (!settings.generateBopSoils) {this.topBlock = Blocks.grass.getDefaultState(); this.fillerBlock = Blocks.dirt.getDefaultState();}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopWaterPlants) {this.removeGenerator("algae"); this.removeGenerator("water_reeds"); this.removeGenerator("medium_lily"); this.removeGenerator("small_lily"); this.removeGenerator("tiny_lily");}
        
        GeneratorWeighted treeGen = (GeneratorWeighted)this.getGenerator("trees");
        if (!settings.generateBopTrees) {this.removeGenerator("trees");
        
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.6F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dying_tree", 2, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(1).log(BlockPlanks.EnumType.DARK_OAK).leaves(BlockPlanks.EnumType.DARK_OAK).create());
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BlockPlanks.EnumType.DARK_OAK).leaves(Blocks.air.getDefaultState()).create());
        }
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x66704C;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x66704C;
    }
    
    
    
    
    /* TODO
    @Override
    public int getFogColour(int x, int y, int z)
    {
        return 9219993;
    }
    
    @Override
    public float getFogDensity(int x, int y, int z)
    {
        // TODO Auto-generated method stub
        return 0.6F;
    }
    */
}