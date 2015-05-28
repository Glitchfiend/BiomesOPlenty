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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenChaparral extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);
    
    public BiomeGenChaparral()
    {
        this.setHeight(biomeHeight);
        this.setColor(12638301);
        this.setTemperatureRainfall(0.8F, 0.6F);
        
        this.addWeight(BiomeType.WARM, 10);
        
        // this.spawnableCreatureList.clear(); TODO: really? nothing at all except horses here?
        // this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 5, 2, 6));
        
        /*
         * TODO: not sure how to implement these     
        this.theBiomeDecorator.grassPerChunk = 10;
        this.theBiomeDecorator.bopFeatures.generateStoneInGrass = true;
        this.theBiomeDecorator.bopFeatures.generatePumpkins = false;
         */
        
        // this.theBiomeDecorator.bopFeatures.bopFlowersPerChunk = 5;
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(5);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        // this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(vanillameta 4, groupcount 5), weight 8);
        flowerGenerator.add("rose", 8, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.ROSE, 64));
        // this.theBiomeDecorator.bopFeatures.weightedFlowerGen.put(new WorldGenBOPDoubleFlora(vanillameta 1, groupcount 5), weight 4);
        flowerGenerator.add("syringa", 4, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.SYRINGA, 64));
        
        
        // this.theBiomeDecorator.bopFeatures.bopGrassPerChunk = 20;
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        // this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(Blocks.tallgrass, 1), 1D);
        grassGenerator.add("tallgrass", 2, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        // this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 2=mediumgrass), 0.5D);
        grassGenerator.add("mediumgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.MEDIUMGRASS)));
        // this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 10=wheatgrass), 0.5D);
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        // this.theBiomeDecorator.bopFeatures.weightedGrassGen.put(new WorldGenBOPTallGrass(BOPCBlocks.foliage, 11=dampgrass), 0.5D);
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        
        
        // this.theBiomeDecorator.treesPerChunk = 8;
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(8, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));

        // this.theBiomeDecorator.bopFeatures.bushesPerChunk = 10;
        this.addGenerator("bushes", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.BUSH)));

        // this.theBiomeDecorator.bopFeatures.shrubsPerChunk = 10;
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.SHRUB)));
        
        // this.theBiomeDecorator.bopFeatures.berryBushesPerChunk = 2;
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS, new GeneratorFlora(2, BlockBOPPlant.paging.getVariantState(BOPPlants.BERRYBUSH)));
        
        // this.theBiomeDecorator.bopFeatures.wildCarrotsPerChunk = 1;
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS, new GeneratorFlora(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WILDCARROT)));
        
        // this.theBiomeDecorator.bopFeatures.leafPilesPerChunk = 10;
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.LEAFPILE)));

        // this.theBiomeDecorator.bopFeatures.deadLeafPilesPerChunk = 5;
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(BOPPlants.DEADLEAFPILE)));
        
        // this.theBiomeDecorator.bopFeatures.waterReedsPerChunk = 2;
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, new GeneratorFlora(2, BlockBOPPlant.paging.getVariantState(BOPPlants.REED), 128));

        
        // world.setBlock(x, y, z, BOPCBlocks.gemOre, 4, 2);
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, BOPGems.PERIDOT), 12, 4, 32));
   
    }
    
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 12638301;
    }
    
}
