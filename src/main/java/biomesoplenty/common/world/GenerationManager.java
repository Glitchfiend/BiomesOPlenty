/*******************************************************************************
 * Copyright 2015-2016, the Biomes O' Plenty Team
 * 
 * This work is licensed under a Creative Commons Attribution-NonCommercial-NoDerivatives 4.0 International Public License.
 * 
 * To view a copy of this license, visit http://creativecommons.org/licenses/by-nc-nd/4.0/.
 ******************************************************************************/

package biomesoplenty.common.world;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.google.common.collect.ImmutableCollection;
import com.google.common.collect.ImmutableList;

import biomesoplenty.api.config.IConfigObj;
import biomesoplenty.api.generation.GeneratorStage;
import biomesoplenty.api.generation.IGenerationManager;
import biomesoplenty.api.generation.IGenerator;

public class GenerationManager implements IGenerationManager
{
    private Map<String, IGenerator> generators = new HashMap<String, IGenerator>();
    
    public void addGenerator(String name, GeneratorStage stage, IGenerator generator)
    {
        if (this.generators.containsKey(name))
        {
            throw new RuntimeException("A generator with name " + name + " already exists!");
        }
        generator.setName(name);
        generator.setStage(stage);
        this.generators.put(name, generator);
    }
    
    public ImmutableCollection<IGenerator> getGeneratorsForStage(GeneratorStage stage)
    {
        ArrayList<IGenerator> out = new ArrayList<IGenerator>();
        for (IGenerator generator : this.generators.values())
        {
            if (generator.getStage() == stage)
            {
                out.add(generator);
            }
        }        
        return ImmutableList.copyOf(out);
    }
    
    public void removeGenerator(String name)
    {
        this.generators.remove(name);
    }
    
    public IGenerator getGenerator(String name)
    {
        return this.generators.get(name);
    }

    public void configure(IConfigObj generatorsObj)
    {
        Iterator<String> genKeysItr = generators.keySet().iterator();

        // iterate over all registered generators
        while (genKeysItr.hasNext())
        {
            String name = genKeysItr.next();
            IConfigObj currentObj = generatorsObj.getObject(name);

            // there was previously no generator of this name - attempt to add it
            if (generatorsObj.has(name))
            {
                IGenerator generator = GeneratorRegistry.createGenerator(currentObj);
                if (generator != null) {
                    this.generators.put(name, generator);
                }
            }

            // configure the generator
            // always attempt to do this so defaults are generated
            if (currentObj.getBool("enable", true)) {
                this.generators.get(name).configure(currentObj);
            } else {
                // remove this generator
                genKeysItr.remove();
            }
        }
    }

}