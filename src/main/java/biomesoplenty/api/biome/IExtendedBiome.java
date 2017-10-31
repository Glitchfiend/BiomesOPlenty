/*******************************************************************************
 * Copyright 2014-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Map;

import biomesoplenty.api.config.IBOPWorldSettings;
import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.enums.BOPClimates;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.biome.Biome;

public interface IExtendedBiome
{
    void applySettings(IBOPWorldSettings settings);
    void configure(IConfigObj conf);
    
    BiomeOwner getBiomeOwner();
    void addGenerator(String name, GeneratorStage stage, IGenerator generator);
    IGenerationManager getGenerationManager();
    Map<BOPClimates, Integer> getWeightMap();
    void clearWeights();
    void addWeight(BOPClimates climate, int weight);
    
    ResourceLocation getBeachLocation();
    
    /**Get the base biome associated with this extension**/
    Biome getBaseBiome();
    ResourceLocation getResourceLocation();
}
