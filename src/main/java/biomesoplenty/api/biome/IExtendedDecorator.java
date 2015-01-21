/*******************************************************************************
 * Copyright 2014-2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome;

import java.util.Map;

public interface IExtendedDecorator
{
    public void addGenerator(String key, IGenerator<?> generator);

    public void configureGenerators(Map<String, IGenerator<?>> generatorMap);

    public Map<String, IGenerator<?>> getGeneratorMap();
}
