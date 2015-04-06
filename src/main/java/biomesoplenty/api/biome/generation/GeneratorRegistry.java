/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class GeneratorRegistry
{
    private static BiMap<String, Class<? extends IGenerator<?>>> generatorClasses = HashBiMap.create();
    
    public static void registerGenerator(String identifier, Class<? extends IGenerator<?>> generatorClass)
    {
        generatorClasses.put(identifier, generatorClass);
    }
    
    public static String getIdentifier(Class<? extends IGenerator<?>> generatorClass)
    {
        return generatorClasses.inverse().get(generatorClass);
    }
    
    public static Class<? extends IGenerator<?>> getGeneratorClass(String identifier)
    {
        return generatorClasses.get(identifier);
    }

    public static boolean generatorExists(String identifier)
    {
        return generatorClasses.containsValue(identifier);
    }
}
