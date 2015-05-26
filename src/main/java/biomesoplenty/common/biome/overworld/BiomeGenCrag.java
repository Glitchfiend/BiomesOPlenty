/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.biome.overworld;

import net.minecraft.init.Blocks;
import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.BOPBiome;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.block.BOPBlocks;
import biomesoplenty.common.world.feature.GeneratorOreSingle;

public class BiomeGenCrag extends BOPBiome
{
    private static final Height biomeHeight = new Height(2.0F, 3.0F);
    
    public BiomeGenCrag()
    {
        this.setHeight(biomeHeight);
        this.setColor(5209457);
        this.setTemperatureRainfall(1.0F, 0.0F);

        this.addWeight(BiomeType.COOL, 3);

        this.spawnableCreatureList.clear();
        this.spawnableWaterCreatureList.clear();
        
        this.topBlock = BOPBlocks.crag_rock.getDefaultState();
        this.fillerBlock = BOPBlocks.crag_rock.getDefaultState();
        this.waterColorMultiplier = 944693;
        this.skyColor = 4944498;
        
        this.addGenerator("emeralds", GeneratorStage.SAND, new GeneratorOreSingle(Blocks.emerald_ore.getDefaultState(), 12, 4, 32));
    }
    
}
