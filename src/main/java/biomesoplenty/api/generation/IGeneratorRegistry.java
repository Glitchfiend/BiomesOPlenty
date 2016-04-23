/*******************************************************************************
 * Copyright 2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/
package biomesoplenty.api.generation;

import biomesoplenty.api.config.IConfigObj;

public interface IGeneratorRegistry
{
    <T extends IGenerator> void registerGenerator(String identifier, Class<T> generatorClass, IGenerator.IGeneratorBuilder<T> builder);
    String getIdentifier(Class<? extends IGenerator> generatorClass);
    Class<? extends IGenerator> getGeneratorClass(String identifier);
    IGenerator.IGeneratorBuilder<?> getGeneratorBuilder(String identifier);
    boolean generatorExists(String identifier);
    IGenerator createGenerator(IConfigObj conf);
}
