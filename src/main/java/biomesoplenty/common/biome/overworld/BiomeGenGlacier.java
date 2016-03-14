/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import net.minecraft.init.Blocks;

public class BiomeGenGlacier extends BOPBiome
{    
    
    public BiomeGenGlacier()
    {
        super("glacier", new PropsBuilder("Glacier").withTemperature(-0.5F).withRainfall(0.2F).withGuiColour(11582425));

        // terrain
        this.terrainSettings.avgHeight(88).heightVariation(8, 10).octaves(0, 1, 1, 3, 1, 0);

        this.topBlock = Blocks.ice.getDefaultState();
        this.fillerBlock = BOPBlocks.hard_ice.getDefaultState();
        
        this.avgDirtDepth = 16;
        
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.spawnableCreatureList.clear();
        
        clearWeights();
          
        // gem
        this.addGenerator("tanzanite", GeneratorStage.SAND, (new GeneratorOreSingle.Builder()).amountPerChunk(12).with(BOPGems.TANZANITE).create());
        
    }
    
    @Override
    public void applySettings(BOPWorldSettings settings)
    {
        if (!settings.generateBopGems) {this.removeGenerator("malachite");}
    }
    
}
