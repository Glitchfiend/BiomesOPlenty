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
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.tree.GeneratorBush;

public class BiomeGenChaparral extends BOPBiome
{
    private static final Height biomeHeight = new Height(0.2F, 0.3F);
    
    public BiomeGenChaparral()
    {
        this.setHeight(biomeHeight);
        this.setColor(0xC0D85D);
        this.setTemperatureRainfall(0.8F, 0.6F);
        
        this.addWeight(BiomeType.WARM, 10);
        
        this.spawnableCreatureList.clear(); // none of your regular farmyard critters here
        this.spawnableCreatureList.add(new SpawnListEntry(EntityHorse.class, 1, 2, 6));
        
        // stone patches
        // TODO: make the generator only run at the surface?
        this.addGenerator("stone_patches", GeneratorStage.SAND, new GeneratorSplotches(15, Blocks.stone.getDefaultState(), 32, Blocks.grass, ScatterYMethod.AT_OR_BELOW_SURFACE));
        
        // flowers
        GeneratorWeighted flowerGenerator = new GeneratorWeighted(5);
        this.addGenerator("flowers", GeneratorStage.GRASS, flowerGenerator);
        flowerGenerator.add("rose", 8, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.ROSE, 64));
        flowerGenerator.add("syringa", 4, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.SYRINGA, 64));
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(20);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        grassGenerator.add("mediumgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.MEDIUMGRASS)));
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        
        // trees
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(8, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));

        // other plants
        this.addGenerator("berry_bushes", GeneratorStage.FLOWERS, new GeneratorFlora(2, BlockBOPPlant.paging.getVariantState(BOPPlants.BERRYBUSH)));
        this.addGenerator("bushes", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.BUSH)));
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.SHRUB)));        
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS, new GeneratorFlora(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WILDCARROT)));        
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(10, BlockBOPPlant.paging.getVariantState(BOPPlants.LEAFPILE)));
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(BOPPlants.DEADLEAFPILE)));        
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, new GeneratorFlora(2, BlockBOPPlant.paging.getVariantState(BOPPlants.REED), 128));
        
        // gem
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, BOPGems.PERIDOT), 12, 4, 32));
   
    }
    
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0xC0D85D;
    }
    
}
