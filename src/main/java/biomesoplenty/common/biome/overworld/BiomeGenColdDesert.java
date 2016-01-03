/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBlobs;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;

public class BiomeGenColdDesert extends BOPBiome
{    
    
    public static enum ColdDesertType {FROZEN, COLD;}
    
    public BiomeGenColdDesert(ColdDesertType type)
    {
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        // terrain
        this.terrainSettings.avgHeight(70).heightVariation(10, 25).sidewaysNoise(0.7D);
        
        this.topBlock = Blocks.gravel.getDefaultState();
        this.fillerBlock = Blocks.stone.getDefaultState();
        
        this.enableRain = false;
        this.enableSnow = false;
        
        if (type == ColdDesertType.FROZEN)
        {
            this.setTemperatureRainfall(0.0F, 0.0F);
            this.addWeight(BOPClimates.FROZEN_DESERT, 20);
            this.setColor(0xB3D7E3);
        }
        else
        {
            this.setTemperatureRainfall(0.2F, 0.0F);
            this.addWeight(BOPClimates.COLD_DESERT, 20);
            this.setColor(0xB3AF9B);
        }

        this.spawnableCreatureList.clear();
        
        // gravel, stone and boulders
        IBlockPosQuery surface = new BlockQueryBlock(Blocks.stone, Blocks.gravel, BOPBlocks.hard_ice);
        if (type == ColdDesertType.FROZEN)
        {
            this.addGenerator("hard_ice_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(6).splotchSize(24).placeOn(surface).replace(surface).with(BOPBlocks.hard_ice.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
            this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(3).splotchSize(16).placeOn(surface).replace(surface).with(Blocks.stone.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        }
        else
        {
            this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(6).splotchSize(24).placeOn(surface).replace(surface).with(Blocks.stone.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());           
        }
        this.addGenerator("boulders", GeneratorStage.SAND_PASS2, (new GeneratorBlobs.Builder()).amountPerChunk(0.5F).placeOn(surface).with(Blocks.stone.getDefaultState()).minRadius(0.3F).maxRadius(3.2F).numBalls(4).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("tanzanite");}
    }
    
}
