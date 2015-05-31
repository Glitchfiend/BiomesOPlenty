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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.block.BlockBOPMushroom;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
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
        treeGenerator.add("oak_large", 1, new GeneratorBigTree(1, false, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        treeGenerator.add("oak", 9, new GeneratorBasicTree(1, false, 5, 8, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(14);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        grassGenerator.add("tallgrass", 2, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));

        // big mushrooms
        GeneratorWeighted mushroomGenerator = new GeneratorWeighted(1);
        this.addGenerator("big_mushrooms", GeneratorStage.TREE, mushroomGenerator);
        mushroomGenerator.add("brown_mushroom", 1, new GeneratorBigMushroom(1, GeneratorBigMushroom.BigMushroomType.BROWN));
        mushroomGenerator.add("red_mushroom", 1, new GeneratorBigMushroom(1, GeneratorBigMushroom.BigMushroomType.RED));
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, new GeneratorWaterside(4, 7, Blocks.gravel.getDefaultState()));
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(5);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 1, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.ROSE, 64));

        // other plants
        this.addGenerator("toadstools", GeneratorStage.FLOWERS, new GeneratorFlora(3, BOPBlocks.mushroom.getDefaultState().withProperty(BlockBOPMushroom.VARIANT, BlockBOPMushroom.MushroomType.TOADSTOOL)));
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(20, BlockBOPPlant.paging.getVariantState(BOPPlants.SHRUB)));
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.CLOVERPATCH)));
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.LEAFPILE), 256));
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.DEADLEAFPILE), 256));
        this.addGenerator("flax", GeneratorStage.FLOWERS, new GeneratorDoubleFlora(1, BlockBOPDoublePlant.DoublePlantType.FLAX, 64));
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(BOPPlants.REED), 128));
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, new GeneratorFlora(5, BOPBlocks.waterlily.getDefaultState().withProperty(BlockBOPLilypad.VARIANT, BlockBOPLilypad.LilypadType.DUCKWEED), 128));
        
        // logs
        this.addGenerator("logs", GeneratorStage.TREE, new GeneratorLogs(8, Blocks.log.getDefaultState(), 3, 5, Blocks.grass));
        
        // gem
        this.addGenerator("amber", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, BOPGems.AMBER), 15, 4, 32));
   
    }
    
}