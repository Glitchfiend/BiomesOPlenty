/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.api.biome.generation;

import java.util.HashMap;
import java.util.Map;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import biomesoplenty.common.util.config.BOPConfig.IConfigObj;

public class GeneratorRegistry
{
    private static BiMap<String, Class<? extends IGenerator>> generatorClasses = HashBiMap.create();
    private static Map<String, IGenerator.IGeneratorBuilder> generatorBuilders = new HashMap<String, IGenerator.IGeneratorBuilder>();
    
    public static <T extends IGenerator> void registerGenerator(String identifier, Class<T> generatorClass, IGenerator.IGeneratorBuilder<T> builder)
    {
        generatorClasses.put(identifier, generatorClass);
        generatorBuilders.put(identifier, builder);
    }
    
    public static String getIdentifier(Class<? extends IGenerator> generatorClass)
    {
        return generatorClasses.inverse().get(generatorClass);
    }
    
    public static Class<? extends IGenerator> getGeneratorClass(String identifier)
    {
        return generatorClasses.get(identifier);
    }
    
    public static IGenerator.IGeneratorBuilder getGeneratorBuilder(String identifier)
    {
        return generatorBuilders.get(identifier);
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
        
        IGenerator.IGeneratorBuilder builder = getGeneratorBuilder(identifier);
        if (builder == null)
        {
            conf.addMessage("No generator is registered with type name " + identifier);
            return null;           
        }
        IGenerator generator = builder.create();
        generator.setStage(stage);
        generator.configure(conf);
        return generator;
    }
}
