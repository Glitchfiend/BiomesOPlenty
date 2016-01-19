/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.monster.EntitySlime;
import net.minecraft.init.Blocks;
import net.minecraft.util.BlockPos;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.GeneratorWeighted;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.block.BlockBOPGrass;
import biomesoplenty.common.block.BlockBOPLilypad;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.enums.BOPPlants;
import biomesoplenty.common.enums.BOPTrees;
import biomesoplenty.common.enums.BOPWoods;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorGrass;
import biomesoplenty.common.world.feature.GeneratorLakes;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSpike;
import biomesoplenty.common.world.feature.GeneratorSplatter;
import biomesoplenty.common.world.feature.tree.GeneratorBasicTree;
import biomesoplenty.common.world.feature.tree.GeneratorBigTree;
import biomesoplenty.common.world.feature.tree.GeneratorTaigaTree;

public class BiomeGenWasteland extends BOPBiome
{    
    
    public BiomeGenWasteland()
    {
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 5);
        
        this.setColor(0x5A5440);
        this.setTemperatureRainfall(0.9F, 0.05F);
        this.topBlock = BOPBlocks.dried_dirt.getDefaultState();
        this.fillerBlock = BOPBlocks.dried_dirt.getDefaultState();
        this.waterColorMultiplier = 0xE5FF00;
        this.skyColor = 0x909E70;
        this.setDisableRain();
        this.seaFloorBlock = BOPBlocks.dried_dirt.getDefaultState();

        this.canGenerateRivers = false;
        this.canGenerateVillages = false;
        
        this.addWeight(BOPClimates.HOT_DESERT, 2);
        
        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        // trees
        GeneratorWeighted treeGenerator = new GeneratorWeighted(0.5F);
        this.addGenerator("trees", GeneratorStage.TREE, treeGenerator);
        treeGenerator.add("dead_tree", 1, (new GeneratorBigTree.Builder()).amountPerChunk(0.5F).minHeight(5).maxHeight(12).foliageHeight(0).foliageDensity(0.5D).log(BOPWoods.DEAD).leaves(Blocks.air.getDefaultState()).create());
        
        // grasses
        GeneratorWeighted grassGenerator = new GeneratorWeighted(0.2F);
        this.addGenerator("grass", GeneratorStage.GRASS, grassGenerator);
        grassGenerator.add("dead_bushes", 1, (new GeneratorGrass.Builder()).with(BlockTallGrass.EnumType.DEAD_BUSH).create());
        
        // other plants
        this.addGenerator("dead_grass", GeneratorStage.FLOWERS,(new GeneratorFlora.Builder()).amountPerChunk(1.0F).with(BOPPlants.DEADGRASS).create());
        
        // lakes
        this.addGenerator("lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.5F).waterLakeForBiome(this).create());
        this.addGenerator("poison_lakes", GeneratorStage.SAND, (new GeneratorLakes.Builder()).amountPerChunk(0.2F).waterLakeForBiome(this).liquid(BOPBlocks.poison).frozenLiquid((IBlockState)null).create());
        
        // spikes
        this.addGenerator("spikes", GeneratorStage.PRE, (new GeneratorSpike.Builder()).amountPerChunk(0.6F).create());
        
        // gem
        this.addGenerator("malachite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.MALACHITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
    }
    
    @Override
    public int getGrassColorAtPos(BlockPos pos)
    {
        return 0x9DA078;
    }
    
    @Override
    public int getFoliageColorAtPos(BlockPos pos)
    {
        return 0x999E55;
    }
    
    // TODO: These 2 are copied from 1.7 - but are they used ever?
    public int getFogColour(BlockPos pos)
    {
        return 0xB8BC85;
    }
    public float getFogDensity(BlockPos pos)
    {
        return 0.3F;
    }
    
   
}
