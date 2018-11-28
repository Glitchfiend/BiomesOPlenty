/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
 
public class BiomeGenGravelBeach extends BOPOverworldBiome
{
    public BiomeGenGravelBeach()
    {
        super("gravel_beach", new PropsBuilder("Gravel Beach").withGuiColour(0x908884).withTemperature(0.6F).withRainfall(0.5F));

        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(3, 4);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.hasBiomeEssence = false;
        
        this.spawnableCreatureList.clear();

        this.decorator.deadBushPerChunk = 0;
        this.decorator.reedsPerChunk = 0;
        this.decorator.cactiPerChunk = 0;
        
        clearWeights();
        
        this.topBlock = Blocks.GRAVEL.getDefaultState();
        this.fillerBlock = Blocks.GRAVEL.getDefaultState();
    }
}
