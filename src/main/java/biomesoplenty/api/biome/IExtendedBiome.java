/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Map;

import biomesoplenty.api.biome.generation.GenerationManager;
import net.minecraftforge.common.BiomeManager.BiomeType;

public interface IExtendedBiome
{
    public BiomeOwner getBiomeOwner();
    public GenerationManager getGenerationManager();
    public Map<BiomeType, Integer> getWeightMap();
    public void clearWeights();
    public void addWeight(BiomeType type, int weight);
}
