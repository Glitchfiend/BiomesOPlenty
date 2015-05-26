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
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPDoublePlant;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockGem;
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
        
        this.addGenerator("gravel", GeneratorStage.SAND_PASS2, new GeneratorWaterside(4, 7, Blocks.gravel.getDefaultState()));
        this.addGenerator("bushes", GeneratorStage.FLOWERS, new GeneratorFlora(7, BlockBOPPlant.paging.getVariantState(BOPPlants.BUSH)));
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.paging.getVariantState(BOPPlants.SHRUB)));
        this.addGenerator("flax", GeneratorStage.FLOWERS, new GeneratorDoubleFlora(1, BlockBOPDoublePlant.DoublePlantType.FLAX, 24));
        this.addGenerator("water_reeds", GeneratorStage.LILYPAD, new GeneratorFlora(3, BlockBOPPlant.paging.getVariantState(BOPPlants.REED), 128));
        
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(1, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        this.addGenerator("flowers", GeneratorStage.FLOWERS, new GeneratorFlora(5, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.ALLIUM)));
        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1);
        grassGenerator.add("shortgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.SHORTGRASS)));
        grassGenerator.add("mediumgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.MEDIUMGRASS)));
        grassGenerator.add("wheatgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.WHEATGRASS)));
        grassGenerator.add("dampgrass", 1, new GeneratorGrass(1, BlockBOPPlant.paging.getVariantState(BOPPlants.DAMPGRASS)));
        grassGenerator.add("tallgrass", 1, new GeneratorGrass(2, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, BOPGems.PERIDOT), 12, 4, 32));
    }
}
