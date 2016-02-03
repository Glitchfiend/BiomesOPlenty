/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import java.util.Random;

import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPClimates;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.util.block.BlockQuery.BlockQueryBlock;
import biomesoplenty.common.util.block.BlockQuery.IBlockPosQuery;
import biomesoplenty.common.util.config.BOPConfig.IConfigObj;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorBlobs;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import biomesoplenty.common.world.feature.GeneratorSplotches;

public class BiomeGenColdDesert extends BOPBiome
{    
    
	public IBlockState usualTopBlock;
    public IBlockState alternateTopBlock;
	
    public static enum ColdDesertType {FROZEN, COLD;}
    
    public BiomeGenColdDesert(ColdDesertType type)
    {
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(5, 10).sidewaysNoise(0.7D);
        
        this.topBlock = Blocks.gravel.getDefaultState();
        this.fillerBlock = Blocks.stone.getDefaultState();
        
        this.canGenerateRivers = false;
        
        this.usualTopBlock = this.topBlock;
        
        this.setDisableRain();
        this.enableSnow = false;
        
        if (type == ColdDesertType.FROZEN)
        {
        	this.alternateTopBlock = BOPBlocks.hard_ice.getDefaultState();
            this.setTemperatureRainfall(0.0F, 0.0F);
            this.addWeight(BOPClimates.FROZEN_DESERT, 10);
            this.setColor(0xB3D7E3);
        }
        else
        {
        	this.alternateTopBlock = Blocks.snow.getDefaultState();
            this.setTemperatureRainfall(0.2F, 0.0F);
            this.addWeight(BOPClimates.COLD_DESERT, 10);
            this.setColor(0xB3AF9B);
        }

        this.spawnableCreatureList.clear();
        
        // gravel, stone and boulders
        IBlockPosQuery surface = new BlockQueryBlock(Blocks.stone, Blocks.gravel);
        if (type == ColdDesertType.FROZEN)
        {
            this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(3).splotchSize(16).placeOn(surface).replace(surface).with(Blocks.stone.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        }
        else
        {
            this.addGenerator("stone_patches", GeneratorStage.SAND, (new GeneratorSplotches.Builder()).amountPerChunk(6).splotchSize(24).placeOn(surface).replace(surface).with(Blocks.stone.getDefaultState()).scatterYMethod(ScatterYMethod.AT_SURFACE).create());           
        }
        this.addGenerator("boulders", GeneratorStage.SAND_PASS2, (new GeneratorBlobs.Builder()).amountPerChunk(0.2F).placeOn(surface).with(Blocks.cobblestone.getDefaultState()).minRadius(0.3F).maxRadius(3.2F).numBalls(4).scatterYMethod(ScatterYMethod.AT_SURFACE).create());
        
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
    }
    
    @Override
    public void configure(IConfigObj conf)
    {
        super.configure(conf);
        
        this.usualTopBlock = this.topBlock;
        this.alternateTopBlock = conf.getBlockState("alternateTopBlock", this.alternateTopBlock);
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
    	if (!settings.generateBopFoliage) {this.removeGenerator("caveweed"); this.removeGenerator("bushes"); this.removeGenerator("koru"); this.removeGenerator("shrubs"); this.removeGenerator("leaf_piles"); this.removeGenerator("dead_leaf_piles"); this.removeGenerator("clover_patches"); this.removeGenerator("sprouts");}
    	
        if (!settings.generateBopGems) {this.removeGenerator("tanzanite");}
        
        if (!settings.generateBopPlants) {this.removeGenerator("cattail"); this.removeGenerator("double_cattail"); this.removeGenerator("river_cane"); this.removeGenerator("tiny_cacti"); this.removeGenerator("roots"); this.removeGenerator("rafflesia"); this.removeGenerator("desert_sprouts");}
    }
    
    @Override
    public void genTerrainBlocks(World world, Random rand, ChunkPrimer primer, int x, int z, double noise)
    {
        this.topBlock = (noise + rand.nextDouble() * 3.0D > 1.8D) ? this.alternateTopBlock : this.usualTopBlock;
        super.genTerrainBlocks(world, rand, primer, x, z, noise);
    }
    
}
