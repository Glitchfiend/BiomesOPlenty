/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorWaterside;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenShrubland extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.1F, 0.1F);
    
    public BiomeGenShrubland()
    {
        this.setHeight(biomeHeight);
        this.setColor(8168286);
        this.setTemperatureRainfall(0.6F, 0.05F);
        
        this.addWeight(BiomeType.COOL, 10);
        
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        // gravel
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, (new GeneratorWaterside.Builder()).amountPerChunk(4).maxRadius(7).to(Blocks.gravel.getDefaultState()).create());
        
        // other plants
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(7).flora(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(5).flora(BOPPlants.SHRUB).create());
        this.addGenerator("flax", GeneratorStage.FLOWERS,(new GeneratorDoubleFlora.Builder()).amountPerChunk(1).flora(BlockBOPDoublePlant.DoublePlantType.FLAX).generationAttempts(24).create());
        
        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD,(new GeneratorFlora.Builder()).amountPerChunk(3).flora(BOPPlants.REED).generationAttempts(128).create());  
        
        // trees
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(1, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        
        // flowers
        this.addGenerator("flowers", GeneratorStage.FLOWERS, (new GeneratorFlora.Builder()).amountPerChunk(5).flora(EnumFlowerType.ALLIUM).create());

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("shortgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.SHORTGRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.DAMPGRASS).create());
        grassGenerator.add("tallgrass", 1, (new GeneratorGrass.Builder()).grass(BlockTallGrass.EnumType.GRASS).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).gemOre(BOPGems.PERIDOT).create());
    }
}
