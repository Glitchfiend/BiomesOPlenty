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
import biomesoplenty.common.entities.EntityButterfly;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.util.math.BlockPos;

public class BiomeGenFlowerField extends BOPBiome
{    
    public BiomeGenFlowerField()
    {
        super("flower_field", new PropsBuilder("Flower Field").withGuiColour(4044093).withTemperature(0.6F).withRainfall(0.7F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(4, 12);

        this.addWeight(BOPClimates.WARM_TEMPERATE, 2);
        
        this.canGenerateVillages = false;
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityButterfly.class, 6, 2, 4));
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(50);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("pink_tulip", 2, (new GeneratorFlora.Builder().with(EnumFlowerType.PINK_TULIP).create()));
        flowerGenerator.add("white_tulip", 5, (new GeneratorFlora.Builder().with(EnumFlowerType.WHITE_TULIP).create()));
        flowerGenerator.add("orange_tulip", 7, (new GeneratorFlora.Builder().with(EnumFlowerType.ORANGE_TULIP).create()));
        flowerGenerator.add("red_tulip", 10, (new GeneratorFlora.Builder().with(EnumFlowerType.RED_TULIP).create()));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(15.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());

        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.PERIDOT).create()); 
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("peridot");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
        
        if (!settings.generateBopFoliage) {this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
        
        GeneratorWeighted grassGen = (GeneratorWeighted)this.getGenerator("grass");
        if (!settings.generateBopGrasses) {grassGen.removeGenerator("shortgrass"); grassGen.removeGenerator("mediumgrass"); grassGen.removeGenerator("wheatgrass"); grassGen.removeGenerator("dampgrass");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 7390273;
    }

    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 7390273;
    }
}
