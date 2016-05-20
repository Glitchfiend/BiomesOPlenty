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
    public void applySettings(IBOPWorldSettings settings);
    public void configure(IConfigObj conf);
    
    public BiomeOwner getBiomeOwner();
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator);
    public IGenerationManager getGenerationManager();
    public Map<BOPClimates, Integer> getWeightMap();
    public void clearWeights();
    public void addWeight(BOPClimates climate, int weight);
    
    public ResourceLocation getBeachLocation();
    
    /**Get the base biome associated with this extension**/
    public Biome getBaseBiome();
    public ResourceLocation getResourceLocation();
}
