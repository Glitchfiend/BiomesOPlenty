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
import biomesoplenty.api.block.BlockQueries;
import biomesoplenty.common.block.BlockBOPCoral;
import biomesoplenty.common.enums.BOPGems;
import biomesoplenty.common.util.biome.GeneratorUtils.ScatterYMethod;
import biomesoplenty.common.world.BOPWorldSettings;
import biomesoplenty.common.world.feature.GeneratorColumns;
import biomesoplenty.common.world.feature.GeneratorFlora;
import biomesoplenty.common.world.feature.GeneratorOreSingle;
import net.minecraft.init.Blocks;
 
public class BiomeGenGravelBeach extends BOPBiome
{
    public BiomeGenGravelBeach()
    {
        // terrain
        this.terrainSettings.avgHeight(64).heightVariation(3, 4); 
        
        this.setColor(0x908884);
    
        this.canSpawnInBiome = false;
        this.canGenerateVillages = false;
        this.canGenerateRivers = false;
        
        this.spawnableCreatureList.clear();
        
        this.theBiomeDecorator.treesPerChunk = -999;
        this.theBiomeDecorator.deadBushPerChunk = 0;
        this.theBiomeDecorator.reedsPerChunk = 0;
        this.theBiomeDecorator.cactiPerChunk = 0;
        
        clearWeights();
        
        this.topBlock = Blocks.gravel.getDefaultState();
        this.fillerBlock = Blocks.gravel.getDefaultState();
    }
}
