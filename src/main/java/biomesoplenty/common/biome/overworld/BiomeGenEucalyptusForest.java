/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockPlanks;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityOcelot;
import net.minecraft.util.BlockPos;
import net.minecraft.world.biome.BiomeGenBase.SpawnListEntry;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorMixedLily;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBulbTree;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenEucalyptusForest extends BOPBiome
{
    
    public BiomeGenEucalyptusForest()
    {        
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(10, 20); 
        
        this.setColor(0x9DCC70);
        this.setTemperatureRainfall(0.9F, 0.1F);
        
        this.canGenerateVillages = false;

        this.addWeight(BOPClimates.TROPICAL, 5);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityOcelot.class, 2, 1, 1));
                            
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(5);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("eucalyptus_bush", 4, (new GeneratorBush.Builder()).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).maxHeight(2).create());        
        treeGenerator.add("tall_eucalyptus", 1, (new GeneratorBulbTree.Builder()).minHeight(10).maxHeight(25).log(BlockPlanks.EnumType.JUNGLE).leaves(BlockPlanks.EnumType.JUNGLE).vine(null).create());
        // TODO: Add Eucalyptus wood as a new wood type - the bark is quite special
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(6);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.MEDIUMGRASS).create());        
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("tallgrass", 4, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("fern", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.FERN).create());

        // other plants
        this.addGenerator("sprouts", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).with(BOPPlants.SPROUT).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.SHRUB).create());

        // water plants
        this.addGenerator("mixed_lily", GeneratorStage.LILYPAD, (new GeneratorMixedLily.Builder()).amountPerChunk(0.8F).create());
        
        // gem
        this.addGenerator("topaz", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TOPAZ).create());
   
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("topaz");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x84D168;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x67CE52;
    }
    
    /*
    public int getFogColour(int x, int y, int z)
    {
        return 14805212;
    }
    */
    
}