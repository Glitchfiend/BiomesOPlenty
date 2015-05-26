/*******************************************************************************
 * Copyright 2015, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

public class GeneratorRegistry
{
    private static BiMap<String, Class<? extends IGenerator>> generatorClasses = HashBiMap.create();
    
    public static void registerGenerator(String identifier, Class<? extends IGenerator> generatorClass)
    {
        generatorClasses.put(identifier, generatorClass);
    }
    
    public static String getIdentifier(Class<? extends IGenerator> generatorClass)
    {
        return generatorClasses.inverse().get(generatorClass);
    }
    
    public static Class<? extends IGenerator> getGeneratorClass(String identifier)
    {
        return generatorClasses.get(identifier);
    }

    public static boolean generatorExists(String identifier)
    {
        return generatorClasses.containsValue(identifier);
    }
    
    public static IGenerator createGenerator(IConfigObj conf)
    {
        GeneratorStage stage = conf.getEnum("stage", GeneratorStage.class);
        String identifier = conf.getString("type");
        if (stage == null || identifier == null) {return null;}
        Class<? extends IGenerator> clazz = getGeneratorClass(identifier);
        if (clazz == null)
        {
            conf.addMessage("No generator is registered with type name " + identifier);
            return null;
        }
        IGenerator generator;
        try {
            generator = clazz.newInstance();
        } catch (Exception e) {
            conf.addMessage("Failed to create instance of generator " + identifier + " - " + e.getMessage());
            return null;
        }
        generator.setStage(stage);
        generator.configure(conf);
        return generator;
    }
}
