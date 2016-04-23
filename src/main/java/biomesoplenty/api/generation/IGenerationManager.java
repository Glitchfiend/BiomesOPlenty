/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.generation;

import com.google.common.collect.ImmutableCollection;

import biomesoplenty.api.config.IConfigObj;

public interface IGenerationManager
{
    void addGenerator(String name, GeneratorStage stage, IGenerator generator);
    ImmutableCollection<IGenerator> getGeneratorsForStage(GeneratorStage stage);
    void removeGenerator(String name);
    IGenerator getGenerator(String name);
    void configureWith(String name, IConfigObj conf);
}
