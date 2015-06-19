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
import net.minecraft.util.BlockPos;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPStone;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorDoubleFlora;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;
import biomesoplenty.common.world.feature.tree.GeneratorBush;
import biomesoplenty.common.world.feature.tree.GeneratorPineTree;

public class BiomeGenJadeCliffs extends BOPBiome
{
        
    public BiomeGenJadeCliffs()
    {        
        // terrain
        this.terrainSettings.avgHeight(110).heightVariation(80, 80).minHeight(40).sidewaysNoise(0.7D); 
        
        this.setColor(0x8ACC6A);
        this.skyColor = 0xB7CCAD;
        this.setTemperatureRainfall(0.8F, 0.9F);
        
        this.addWeight(BiomeType.WARM, 3);
        
        this.addGenerator("limestone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(26).splotchSize(25).replace(Blocks.stone).with(BOPBlocks.stone.getDefaultState().withProperty(BlockBOPStone.VARIANT, BlockBOPStone.StoneType.LIMESTONE)).create());
                    
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(10);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("pine", 3, (new GeneratorPineTree.Builder()).minHeight(6).maxHeight(20).create());
        treeGenerator.add("oak_bush", 1, (new GeneratorBush.Builder()).create());        

        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(1.0F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("tallgrass", 2, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.GRASS).create());
        grassGenerator.add("dampgrass", 1, (new GeneratorGrass.Builder()).with(BOPPlants.DAMPGRASS).create());

        // other plants
        this.addGenerator("ferns", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.5F).with(BlockTallGrass.EnumType.FERN).create());
        this.addGenerator("leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.6F).with(BOPPlants.LEAFPILE).create());
        this.addGenerator("dead_leaf_piles", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.3F).with(BOPPlants.DEADLEAFPILE).create());
        this.addGenerator("wild_carrots", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(0.1F).with(BOPPlants.WILDCARROT).create());
        this.addGenerator("syringa", GeneratorStage.FLOWERS, (new GeneratorDoubleFlora.Builder()).amountPerChunk(0.4F).with(BlockDoublePlant.EnumPlantType.SYRINGA).create());
                
        // gem
        this.addGenerator("emerald", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
   
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("emeralds");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x7CA568;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x8ACC6A;
    }
    
}