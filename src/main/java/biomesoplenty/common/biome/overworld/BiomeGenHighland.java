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
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.feature.GeneratorBlobs;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenHighland extends BOPBiome
{
    
   private static final Height biomeHeight = new Height(2.5F, 0.5F);
    
    public BiomeGenHighland()
    {
        this.setHeight(biomeHeight);
        this.setColor(0x7CAD66);
        this.setTemperatureRainfall(0.5F, 0.8F);
        
        this.addWeight(BiomeType.WARM, 7);
        
        // other plants
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS, new GeneratorFlora(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WILDCARROT)));        
        
        // boulders
        // TODO: make the generator only run at the surface?
        this.addGenerator("boulders", GeneratorStage.SAND, new GeneratorBlobs(4, Blocks.cobblestone.getDefaultState(), 0.3F, 1.2F, 0.5F, 1, Blocks.grass, ScatterYMethod.AT_OR_BELOW_SURFACE));
        this.addGenerator("big_boulders", GeneratorStage.SAND, new GeneratorBlobs(1, Blocks.cobblestone.getDefaultState(), 0.3F, 4.0F, 0.5F, 3, Blocks.grass, ScatterYMethod.AT_OR_BELOW_SURFACE));


        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(100);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        grassGenerator.add("tallgrass", 1, new GeneratorGrass(1, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        grassGenerator.add("doublegrass", 4, new GeneratorDoubleFlora(1, BlockDoublePlant.EnumPlantType.GRASS, 64));
        
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, new GeneratorOreSingle(Blocks.emerald_ore.getDefaultState(), 15, 4, 32));
   
    }
    
}