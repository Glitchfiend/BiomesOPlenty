/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.enums.BOPFlowers;
import biomesoplenty.api.enums.BOPGems;
import biomesoplenty.api.enums.BOPPlants;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.common.world.generator.GeneratorDoubleFlora;
import biomesoplenty.common.world.generator.GeneratorFlora;
import biomesoplenty.common.world.generator.GeneratorGrass;
import biomesoplenty.common.world.generator.GeneratorOreSingle;
import biomesoplenty.common.world.generator.GeneratorWeighted;
import biomesoplenty.common.world.generator.tree.GeneratorProfileTree;
import biomesoplenty.common.world.generator.tree.GeneratorTwigletTree;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.material.Material;
import net.minecraft.block.BlockDoublePlant;
import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;

public class BiomeGenFlowerField extends BOPOverworldBiome
{    
    public BiomeGenFlowerField()
    {
        super("flower_field", new PropsBuilder("Flower Field").withGuiColour(4044093).withTemperature(0.7F).withRainfall(0.7F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 5).octaves(0, 1, 2, 2, 1, 0);

        this.addWeight(BOPClimates.WARM_TEMPERATE, 2);
        
        this.canGenerateVillages = false;
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(3.0F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("small_bush", 1, (new GeneratorFlora.Builder()).placeOn(this.topBlock).replace(Material.AIR).withNonDecayingLeaf(BlockPlanks.EnumType.OAK).create());
       
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(17.5F);
        this.addGenerator("flowers", GeneratorStage.FLOWERS, flowerGenerator);
        flowerGenerator.add("pink_tulip", 6, (new GeneratorFlora.Builder().with(EnumFlowerType.PINK_TULIP).create()));
        flowerGenerator.add("white_tulip", 9, (new GeneratorFlora.Builder().with(EnumFlowerType.WHITE_TULIP).create()));
        flowerGenerator.add("orange_tulip", 11, (new GeneratorFlora.Builder().with(EnumFlowerType.ORANGE_TULIP).create()));
        flowerGenerator.add("red_tulip", 14, (new GeneratorFlora.Builder().with(EnumFlowerType.RED_TULIP).create()));
        flowerGenerator.add("oxeye_daisy", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.OXEYE_DAISY).create()));
        flowerGenerator.add("dandelion", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.DANDELION).create()));
        flowerGenerator.add("poppy", 3, (new GeneratorFlora.Builder().with(EnumFlowerType.POPPY).create()));
        flowerGenerator.add("white_anemone", 2, (new GeneratorFlora.Builder().with(BOPFlowers.WHITE_ANEMONE)).create());
        flowerGenerator.add("houstonia", 2, (new GeneratorFlora.Builder().with(EnumFlowerType.HOUSTONIA).create()));
        flowerGenerator.add("rose", 1, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).with(BlockDoublePlant.EnumPlantType.ROSE).create());
        flowerGenerator.add("syringa", 1, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).with(BlockDoublePlant.EnumPlantType.SYRINGA).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        //grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        //grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());

        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create()); 
        
    }
}
