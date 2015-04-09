/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Map;

import net.minecraftforge.common.BiomeManager.BiomeType;
import biomesoplenty.api.biome.generation.GenerationManager;
import biomesoplenty.api.biome.generation.GeneratorStage;
import biomesoplenty.api.biome.generation.IGenerator;

public interface IExtendedBiome
{
    public BiomeOwner getBiomeOwner();
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator);
    public GenerationManager getGenerationManager();
    public Map<BiomeType, Integer> getWeightMap();
    public void clearWeights();
    public void addWeight(BiomeType type, int weight);
}
