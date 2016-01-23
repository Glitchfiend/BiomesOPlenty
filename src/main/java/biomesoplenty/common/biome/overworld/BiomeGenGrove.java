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
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPFlowers;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorProfileTree;

public class BiomeGenGrove extends BOPBiome
{
    
    public BiomeGenGrove()
    {
        // terrain
        this.terrainSettings.avgHeight(66).heightVariation(8, 20).octaves(0, 1, 2, 2, 1, 0).sidewaysNoise(0.1D);
        
        this.setColor(0x517F51);
        this.setTemperatureRainfall(0.7F, 0.8F);
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 7);
        this.addWeight(BOPClimates.COOL_TEMPERATE, 7);
                 
        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SPROUT).create());
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.4F).with(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.SHRUB).create());
        this.addGenerator("clover_patches", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(3.0F).with(BOPPlants.CLOVERPATCH).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BOPPlants.LEAFPILE).create());
       
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dark_poplar", 1, (new GeneratorProfileTree.Builder()).minHeight(6).maxHeight(14).log(BlockPlanks.EnumType.DARK_OAK).leaves(BlockPlanks.EnumType.DARK_OAK).profile(GeneratorProfileTree.TreeProfile.POPLAR).create());
        treeGenerator.add("poplar", 1, (new GeneratorProfileTree.Builder()).minHeight(8).maxHeight(18).log(BlockPlanks.EnumType.BIRCH).leaves(BlockPlanks.EnumType.BIRCH).profile(GeneratorProfileTree.TreeProfile.POPLAR).create());
        treeGenerator.add("bush", 1, (new GeneratorBush.Builder()).maxHeight(2).create());

        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(5.0F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("clover", 4, (new GeneratorFlora.Builder().with(BOPFlowers.CLOVER).create()));
        flowerGenerator.add("white_anemones", 2, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE).create()));
        flowerGenerator.add("paeonias", 1, (new GeneratorDoubleFlora.Builder()).with(BlockDoublePlant.EnumPlantType.PAEONIA).create());
        flowerGenerator.add("oxeye_daisy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("dandelion", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 1, (new GeneratorFlora.Builder().with(BlockFlower.EnumFlowerType.POPPY).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        
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
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0x517F51 : 0x609E58;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        double noise = GRASS_COLOR_NOISE.func_151601_a((double)pos.getX() * 0.0225D, (double)pos.getZ() * 0.0225D);
        return noise < -0.1D ? 0x619961 : 0x75B569;
    }
    
}
