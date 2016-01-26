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
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;

public class BiomeGenShield extends BOPBiome
{    
    public BiomeGenShield()
    {
        
        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(10, 10).sidewaysNoise(0.1D).octaves(1, 4, 3, 1, 1, 0);
        
        this.setColor(0x647F38);
        this.setTemperatureRainfall(0.5F, 0.8F);
        
        this.canGenerateVillages = false;
        
        this.addWeight(BOPClimates.BOREAL, 7);
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(12).maxRadius(7).with(Blocks.gravel.getDefaultState()).create());
        
        // stone patches
        this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(4).splotchSize(15).replace(this.topBlock).with(Blocks.stone.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        
        // trees & logs
        GeneratorWeighted treeGenerator = new GeneratorWeighted(9);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("spruce", 4, (new GeneratorTaigaTree.Builder()).maxHeight(13).create()); // TODO: implement pine cones
        treeGenerator.add("oak_bush", 3, (new GeneratorBush.Builder()).maxHeight(2).create());
        treeGenerator.add("pine", 2, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(18).log(BOPWoods.PINE).leaves(BOPTrees.PINE).create());  
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // other plants
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SHRUB).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("pumpkins", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.015625F).with(Blocks.pumpkin.getDefaultState()).create());
        
        // shrooms
        this.addGenerator("flat_mushroom", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BlockBOPMushroom.MushroomType.FLAT_MUSHROOM).create());
        this.addGenerator("toadstools", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPMushroom.MushroomType.TOADSTOOL).create());
        this.addGenerator("brown_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(Blocks.brown_mushroom.getDefaultState()).create());
        this.addGenerator("red_mushrooms", GeneratorStage.SHROOM,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(Blocks.red_mushroom.getDefaultState()).create());
        
        // water plants
        this.addGenerator("algae", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).replace(Blocks.water).with(BOPBlocks.coral.getDefaultState().withProperty(BlockBOPCoral.VARIANT, BlockBOPCoral.CoralType.ALGAE)).scatterYMethod(ScatterYMethod.AT_GROUND).create());
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.4F).with(BOPPlants.REED).generationAttempts(32).create());
        
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
        return 0x647F38;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x789643;
    }
    
}
