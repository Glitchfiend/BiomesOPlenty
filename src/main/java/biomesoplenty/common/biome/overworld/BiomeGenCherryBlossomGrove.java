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
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
 
public class BiomeGenCherryBlossomGrove extends BOPBiome
{
    public BiomeGenCherryBlossomGrove()
    {
        // terrain
        this.terrainSettings.avgHeight(63).heightVariation(5, 25).sidewaysNoise(0.8F);
        
        this.setColor(0xF88F8F);
        this.setTemperatureRainfall(0.6F, 0.8F);
    
        this.addWeight(BOPClimates.COOL_TEMPERATE, 3);
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(6.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("pink_daffodil", 4, (new GeneratorFlora.Builder().with(BOPFlowers.PINK_DAFFODIL).create()));
        flowerGenerator.add("white_anemone", 3, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("clover", 2, (new GeneratorFlora.Builder().with(BOPFlowers.CLOVER).create()));
        flowerGenerator.add("syringa", 2, (new GeneratorDoubleFlora.Builder().with(BlockDoublePlant.EnumPlantType.SYRINGA).create()));

        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("pink_cherry", 3, (new GeneratorBasicTree.Builder()).log(BOPWoods.CHERRY).leaves(BOPTrees.PINK_CHERRY).create());
        treeGenerator.add("white_cherry", 3, (new GeneratorBasicTree.Builder()).log(BOPWoods.CHERRY).leaves(BOPTrees.WHITE_CHERRY).create());
        treeGenerator.add("large_pink_cherry", 1, (new GeneratorBigTree.Builder()).log(BOPWoods.CHERRY).leaves(BOPTrees.PINK_CHERRY).create());
        treeGenerator.add("large_white_cherry", 1, (new GeneratorBigTree.Builder()).log(BOPWoods.CHERRY).leaves(BOPTrees.WHITE_CHERRY).create());
        
        // other plants
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(0.2F).with(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(6).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(2.5F).with(BOPPlants.CLOVERPATCH).create());
        
        // water plants
        this.addGenerator("duckweed", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(1.5F).with(BlockBOPLilypad.LilypadType.DUCKWEED).generationAttempts(32).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
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
        return 0xA3FFAA;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0xA3FFAA;
    }
}
