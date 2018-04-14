/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import biomesoplenty.api.block.BOPBlocks;
 
public class BiomeGenWhiteBeach extends BOPOverworldBiome
{
    public BiomeGenWhiteBeach()
    {
        super("white_beach", new PropsBuilder("White Beach").withTemperature(1.0F).withRainfall(0.95F).withGuiColour(0xF3F1E4));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(1, 1);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.hasBiomeEssence = false;
        
        this.spawnableMonsterList.clear();
        this.spawnableCreatureList.clear();
        
        this.decorator.treesPerChunk = -999;
        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
        
        this.skyColor = 507391;
        this.fogColor = 0xB5F8FF;
        
        clearWeights();
        
        this.topBlock = BOPBlocks.white_sand.getDefaultState();
        this.fillerBlock = BOPBlocks.white_sand.getDefaultState();
    }
}
