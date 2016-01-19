/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
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
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenCrag extends BOPBiome
{    
    public BiomeGenCrag()
    {
        // terrain
        this.terrainSettings.avgHeight(80).heightVariation(80, 200).minHeight(40).sidewaysNoise(0.7F);
        
        this.setColor(5209457);
        this.setTemperatureRainfall(1.0F, 0.0F);

        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.addWeight(BOPClimates.WET_TEMPERATE, 3);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.topBlock = BOPBlocks.crag_rock.getDefaultState();
        this.fillerBlock = BOPBlocks.crag_rock.getDefaultState();
        this.waterColorMultiplier = 944693;
        this.skyColor = 4944498;
        
        // gem
        this.addGenerator("emeralds", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(Blocks.emerald_ore.getDefaultState()).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("emeralds");}
    }
    
}
