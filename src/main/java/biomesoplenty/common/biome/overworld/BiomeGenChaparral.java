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
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenChaparral extends BOPBiome
{    
    public BiomeGenChaparral()
    {
        
        // terrain
        this.bopMinHeight = 59;
        this.bopMaxHeight = 93;
        this.sidewaysNoiseAmount = 0.1D;
        this.setOctaveWeights(1, 4, 3, 1, 1, 0);
        
        this.setColor(0xC0D85D);
        this.setTemperatureRainfall(0.8F, 0.6F);
        
        this.addWeight(BiomeType.WARM, 10);
        
        this.spawnableCreatureList.clear(); // none of your regular farmyard critters here
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 1, 2, 6));
        
        // stone patches
        // TODO: make the generator only run at the surface?
        this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(15).from(Blocks.grass).to(Blocks.stone.getDefaultState()).splotchSize(15).create());
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 8, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).flora(BlockDoublePlant.EnumPlantType.ROSE).create());
        flowerGenerator.add("syringa", 4, (new GeneratorDoubleFlora.Builder()).amountPerChunk(1).flora(BlockDoublePlant.EnumPlantType.SYRINGA).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(2.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).grass(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("mediumgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.MEDIUMGRASS).create());
        grassGenerator.add("wheatgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.WHEATGRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).grass(BOPPlants.DAMPGRASS).create());
        
        // trees
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(8, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));

        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).flora(BOPPlants.BERRYBUSH).create());
        this.addGenerator("bushes", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).flora(BOPPlants.BUSH).create());
        this.addGenerator("shrubs", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.2F).flora(BOPPlants.SHRUB).create());
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).flora(BOPPlants.WILDCARROT).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).flora(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).flora(BOPPlants.DEADLEAFPILE).create());

        // water plants
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, (new GeneratorFlora.Builder()).amountPerChunk(0.2F).flora(BOPPlants.REED).generationAttempts(32).create());
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).gemOre(BOPGems.PERIDOT).create());
        
    }
    
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xC0D85D;
    }
    
}
