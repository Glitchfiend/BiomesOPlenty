/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import static biomesoplenty.common.block.BlockBOPDoublePlant.VARIANT;
import static biomesoplenty.common.block.BlockDoubleDecoration.HALF;
import net.minecraft.block.BlockFlower.EnumFlowerType;
import net.minecraft.block.BlockTallGrass;
import net.minecraft.entity.passive.EntityHorse;
import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.api.block.BOPPlantEnums.AllPlants;
import biomesoplenty.common.block.BlockBOPDoublePlant.FoliageType;
import biomesoplenty.common.block.BlockBOPPlant;
import biomesoplenty.common.block.BlockDoubleDecoration.Half;
import biomesoplenty.common.block.BlockGem;
import biomesoplenty.common.block.BlockGem.GemType;
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
        this.addGenerator("shrubs", GeneratorStage.FLOWERS, new GeneratorFlora(5, BlockBOPPlant.getVariantState(AllPlants.SHRUB)));
        this.addGenerator("flax", GeneratorStage.FLOWERS, new GeneratorDoubleFlora(1, BOPBlocks.double_plant.getDefaultState().withProperty(VARIANT, FoliageType.FLAX).withProperty(HALF, Half.LOWER), BOPBlocks.double_plant.getDefaultState().withProperty(VARIANT, FoliageType.FLAX).withProperty(HALF, Half.UPPER), 24));
        
        this.addGenerator("trees", GeneratorStage.TREE, new GeneratorBush(1, Blocks.log.getDefaultState(), Blocks.leaves.getDefaultState()));
        this.addGenerator("flowers", GeneratorStage.FLOWERS, new GeneratorFlora(5, Blocks.red_flower.getDefaultState().withProperty(Blocks.red_flower.getTypeProperty(), EnumFlowerType.ALLIUM)));
        
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1);
        grassGenerator.add(1, new GeneratorGrass(1, BlockBOPPlant.getVariantState(AllPlants.SHORTGRASS)));
        grassGenerator.add(1, new GeneratorGrass(1, BlockBOPPlant.getVariantState(AllPlants.MEDIUMGRASS)));
        grassGenerator.add(1, new GeneratorGrass(1, BlockBOPPlant.getVariantState(AllPlants.WHEATGRASS)));
        grassGenerator.add(1, new GeneratorGrass(1, BlockBOPPlant.getVariantState(AllPlants.DAMPGRASS)));
        grassGenerator.add(1, new GeneratorGrass(2, Blocks.tallgrass.getDefaultState().withProperty(BlockTallGrass.TYPE, BlockTallGrass.EnumType.GRASS)));
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        
        this.addGenerator("peridot", GeneratorStage.SAND, new GeneratorOreSingle(BOPBlocks.gem_ore.getDefaultState().withProperty(BlockGem.VARIANT, GemType.PERIDOT), 12, 4, 32));
    }
}
